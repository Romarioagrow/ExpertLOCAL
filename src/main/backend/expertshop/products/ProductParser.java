package expertshop.products;
import com.opencsv.CSVReader;
import expertshop.domain.Product;
import expertshop.domain.ProductBase;
import expertshop.repos.BaseRepo;
import expertshop.repos.ProductRepo;
import expertshop.services.ProductService;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Log
@Service
@AllArgsConstructor
public class ProductParser {
    private final ProductService productService;
    private final ProductRepo productRepo;
    private final BaseRepo baseRepo;

    public void
    parseProducts(MultipartFile file)
    {
        if (file != null && !Objects.requireNonNull(file.getOriginalFilename()).isEmpty())
        {
            try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file.getInputStream())))
            {
                if (fileRBT(file))  parseRBT(file, bufferedReader);
                else                parseRUSBT(file, bufferedReader);

                productRepo.findAllByProductGroupIsNotNull().forEach(product -> {product.setIsAvailable(false); productRepo.save(product);});
                for (Product product : productRepo.findAllByProductGroupIsNotNull()) {
                    if (product.getUpdate().toString().equals(LocalDate.now().toString())) {
                        product.setIsAvailable(true);
                    }
                    else product.setIsAvailable(false);
                    productRepo.save(product);
                }
            }
            catch (IOException exp) {
                exp.getStackTrace();
            }
        }
    }

    private void parseRBT(MultipartFile file, BufferedReader bufferedReader) throws IOException {
        log.info("Parsing RBT file: " + file.getOriginalFilename());

        int countAdd = 0, countUpdate = 0; /// В СТАТИК
        String[] line;
        CSVReader reader = new CSVReader(bufferedReader, ';');

        List<Product> products = productRepo.findBySupplier("1RBT");
        products.forEach(product -> {product.setIsAvailable(false);productRepo.save(product);});

        while ((line = reader.readNext()) != null)
        {
            if (lineIsCorrect(line))
            {
                String productID = line[0];
                if (productAlreadyExists(productID))
                {
                    String productAmount            = line[6];
                    String productPrice             = line[7];

                    updateProduct(productID, productAmount, productPrice);

                    countUpdate++;
                    log.info("Updating file: " + line[3]);
                }
                else
                {
                    createProductFromRBT(line);
                    log.info("Creating new file: " + line[3]);
                    countAdd++;
                }
            }
            else log.info("!!! Пропуск некоректной строки " + file.getOriginalFilename());
        }
        log.info("Products add: "       + countAdd);
        log.info("Products updated: "   + countUpdate);
    }
    private void parseRUSBT(MultipartFile file, BufferedReader bufferedReader) throws IOException
    {
        log.info("Parsing RUS-BT file: " + file.getOriginalFilename());

        int countAdd = 0, countUpdate = 0;
        String[] line;
        CSVReader reader = new CSVReader(bufferedReader, ';');

        List<Product> products = productRepo.findBySupplier("2RUS-BT");
        products.forEach(product -> {product.setIsAvailable(false);productRepo.save(product);});

        while ((line = reader.readNext()) != null)
        {
            if (correctLineR(line))
            {
                String productID = line[5];
                if (productAlreadyExists(productID))
                {
                    String productAmount = line[7] + line[8];
                    String productPrice  = line[13];

                    updateProduct(productID, productAmount, productPrice);

                    countUpdate++;
                    log.info("Updating product: " + line[6]);
                }
                else
                {
                    createProductFromRUSBT(line);
                    log.info("Creating new product: " + line[6]);
                    countAdd++;
                }
            }
        }
        log.info("Products add: " + countAdd);
        log.info("Products updated: " + countUpdate);
    }

    private void updateProduct(String productID, String productAmount, String productPrice) {
        Product product = productRepo.findByProductID(productID);

        if (differentParams(productID, productAmount, productPrice)) {
            product.setOriginalAmount(productAmount);
            product.setOriginalPrice(productPrice);
        }
        product.setUpdate(LocalDate.now());
        productRepo.save(product);
    }

    private boolean correctLineR(String[] line) {
        return !line[6].contains("Уценка") && !line[3].contains("УЦЕНКА") && !line[0].contains("Код товара") & !line[0].isEmpty() & !line[0].startsWith(";") & !line[5].isEmpty() & !line[13].contains("Цена со скидкой");
    }

    ///ОБРАБАТЫВАТЬ В ОДНОМ МЕТОДЕ, ПОЛУЧАТЬ ДАННЫЕ В ПЕРЕМЕННЫЕ В РАЗНЫХ
    private void createProductFromRBT(String[] line) {
        Product product = new Product();
        product.setProductID(line[0]);

        product.setOriginalCategory(line[1]);
        product.setOriginalType(line[2]);

        product.setOriginalBrand(line[4]);
        product.setOriginalName(line[3]);
        product.setOriginalAnnotation(line[5]);

        product.setOriginalAmount(line[6]);
        product.setOriginalPrice(line[7].trim());

        product.setOriginalPic(line[10]);

        product.setSupplier("1RBT");
        product.setUpdate(LocalDate.now());
        productRepo.save(product);
    }
    private void createProductFromRUSBT(String[] line) {
        Product product = new Product();
        product.setProductID(line[5]);

        product.setOriginalCategory(line[0].concat("; ").concat(line[1]));
        product.setOriginalType(line[3]);

        product.setOriginalBrand(line[4]);
        product.setOriginalName(line[6]);
        product.setOriginalAnnotation(StringUtils.substringAfter(line[6], line[4]));
        product.setOriginalAmount(line[7]+line[8]);
        product.setOriginalPrice(line[13]);

        product.setLinkR(line[17]);
        product.setRType(line[3]);
        product.setRName(line[6]);

        product.setSupplier("2RUS-BT");
        product.setUpdate(LocalDate.now());
        product.setIsAvailable(true);
        productRepo.save(product);
    }

    private boolean productAlreadyExists(String productID) {
        return productRepo.findByProductID(productID) != null;
    }

    private void updateProductStatsAndDate(String productID, String amount, String price)
    {
        Product product = productRepo.findByProductID(productID);
        product.setOriginalAmount(amount);
        product.setOriginalPrice(price);
        product.setUpdate(LocalDate.now());
        productRepo.save(product);
        log.info("Updating " + productID);
    }

    private void updateProductDate(String productID) {
        Product product = productRepo.findByProductID(productID);
        product.setUpdate(LocalDate.now());
        productRepo.save(product);
        log.info("Updated : " + productID);
    }

    private boolean differentParams(String productID, String amount, String price) {
        Product product = productRepo.findByProductID(productID);
        return !product.getOriginalAmount().equals(amount) || !product.getOriginalPrice().equals(price);
    }

    private boolean fileRBT(MultipartFile file) {
        return Objects.requireNonNull(file.getOriginalFilename()).contains("СП2");
    }
    private boolean fileRUSBT(MultipartFile file) {
        return Objects.requireNonNull(file.getOriginalFilename()).contains("RUSBT");
    }

    private boolean lineIsCorrect(String[] line) {
        return !line[3].isEmpty() && !line[0].equals("Код товара") & !line[0].equals(";") & !line[0].contains("г. Челябинск") &
                !line[0].contains("8(351)")   & !line[0].startsWith(".")  & !line[0].startsWith(" ");
    }

    private boolean incorrectLineRBT(String[] line) {
        return line[0].equals("Код товара")     || line[0].equals(";")      || line[0].contains("г. Челябинск")
                || line[0].contains("8(351)")   || line[0].startsWith(".")  || line[0].startsWith(" ");
    }
    private boolean incorrectLineRUSBT(String[] line)
    {
        return line[0].equals("Код товара") || line[0].isEmpty() || line[0].startsWith(";") || line[5].isEmpty()
                || line[13].contains("Цена со скидкой");
    }

    public void parseBase(MultipartFile file)
    {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            CSVReader reader = new CSVReader(bufferedReader, ';');

            String[] line;
            int count = 0;
            while ((line = reader.readNext()) != null)
            {
                if (line[0].equals("Категория")) log.info("!!! Пропуск первой строки");
                else
                {
                    ProductBase product = new ProductBase();
                    product.setProductID(UUID.randomUUID().toString());
                    String productBrand = line[13];
                    product.setCategory(line[0]);
                    product.setSubCategory(line[1]);
                    if (line[2].isEmpty())  product.setProductGroup(line[1]);
                    else product.setProductGroup(line[2]);

                    product.setArticul(line[3]);
                    product.setArticulModification(line[4]);
                    product.setFullName(line[5]);
                    product.setFullCategory(line[6]);
                    product.setPrice(line[7]);
                    product.setAnnotation(line[11]);
                    product.setBrand(line[13]);
                    product.setPics(line[18]);
                    product.setParamsHTML(line[19]);
                    log.info(product.getFullName());
                    baseRepo.save(product);
                    count++;

                }
            }
            log.info("Products add: " + count);
        }
        catch (IOException | ArrayIndexOutOfBoundsException exp) {
            log.info("Something went wrong with Base!");
        }
    }

    public void parseRusBT()
    {
        /*productRepo.findBySupplier("2RUS-BT").forEach(product -> {
            if (product.getOriginalPic().startsWith("http://rusbt.ru/catalog")) {
                product.setLinkR(product.getOriginalPic());
                product.setOriginalPic(null);
            }
            else if (!product.getOriginalPic().startsWith("http://magazilla")) {
                product.setOriginalPic(null);
            }
            productRepo.save(product);
        });*/

        AtomicInteger count404 = new AtomicInteger(), countPic = new AtomicInteger(), countAnno = new AtomicInteger(), countInfo = new AtomicInteger();
        List<Product> list = productRepo.findBySupplierAndProductGroupIsNotNullAndOriginalPicIsNullAndLinkRIsNotNull("2RUS-BT");

        list.forEach(product ->
        {
            String link = product.getLinkR();
            try
            {
                if (!link.isEmpty() && !link.startsWith("http://magazilla")) ///!
                {
                    String pic;
                    Document page = Jsoup.connect(link).get();
                    if (product.getOriginalPic() == null)
                    {
                        Elements pics = page.select("img");
                        for (Element picElement : pics)
                        {
                            String picSrc = picElement.attr("src");
                            if (picSrc.startsWith("/upload"))
                            {
                                pic = "http://rusbt.ru".concat(picSrc);
                                if (product.getOriginalPic() == null) product.setOriginalPic(pic);
                                else
                                {
                                    if (product.getPics() == null) product.setPics(pic);
                                    else product.setPics(product.getPics().concat(";").concat(pic));
                                }

                                productRepo.save(product);
                                log.info("ПОЛНАЯ ССЫЛКА ДЛЯ " + product.getOriginalName() + ": " + pic);
                                countPic.getAndIncrement();
                            }
                            else log.info("Нет изображения товара на сайте!");
                        }
                    }

                    if (product.getFormattedAnnotation() == null || product.getFormattedAnnotation().isEmpty())
                    {
                        Elements props = page.getElementsByClass("one_prop");
                        for (Element element : props) {
                            String key = StringUtils.substringBetween(element.html(), "<span>", "</span>");
                            String val = StringUtils.substringBetween(element.html(), "<div class=\"left_value\">", "</div>");
                            String param = key.concat(":").concat(val).concat(";");

                            if (product.getFormattedAnnotation() == null || product.getFormattedAnnotation().isEmpty()) {
                                product.setFormattedAnnotation(param);
                            }
                            else product.setFormattedAnnotation(product.getFormattedAnnotation().concat(param));
                        }
                    }

                    /*if (product.getDescription() == null)
                    {
                        Elements description = page.getElementsByClass("bx_item_description");
                        for (Element el : description) {
                            product.setDescription(el.html());
                        }
                    }*/
                    productRepo.save(product);
                }
                else log.info("ССЫЛКА ОТСУТСТВУЕТ!");
                System.out.println();
                countInfo.getAndIncrement();
                log.info(countInfo + " products from " + list.size());
            }
            catch (HttpStatusException exp) {
                log.info("EXP Page is empty");
                count404.getAndIncrement();
                exp.printStackTrace();
            }
            catch (ConnectException exp) {
                log.info("Connection timed out");
                exp.printStackTrace();
            }
            catch (IOException | NullPointerException exp) {
                log.info("Exception");
                exp.printStackTrace();
            }
        });
        System.out.println();
        log.info("Всего товаров: "      + list.size());
        log.info("Успешно скачано: "    + countPic);
        log.info("404 на сайте: "       + count404);
    }


    ///!!!
    /*
    private String findPicAndParse(String requestName) throws IOException
    {
        log.info(requestName);
        if (!requestName.isEmpty())
        {
            Document page = Jsoup.connect(requestName).get();
            Elements elements = page.select("img");
            //Elements elements = page.getElementsByAttribute("img");
            //Elements elements = page.getElementsByClass("mimg");
            //Elements elements = page.getAllElements();
            for (Element element : elements) {
                log.info(element.toString());
            }
            //Element pic = page.select("class=\"serp-item__thumb justifier__thumb\"").first();
            //log.info(pics.toString());
        }
        return pic.toString();
    }*/

    /*public void countPics()
    {
        List<Product> products = productRepo.findAll();
        int totalPics = 0, picsFromSite = 0;

        for (Product product : products)
        {
            if (!product.getPic().equals("no pic"))
            {
                totalPics++;
                if (product.getPic().contains("top-tehnica.ru"))
                {
                    picsFromSite++;
                }
            }
        }
        log.info("Total pics: " + totalPics);
        log.info("Pics from site: " + picsFromSite);
    }*/

    /*public void checkProductPics()
    {
        String request = "телевизоры";
        //Set<Product> products = productRepo.findProductsByProductGroupContainingIgnoreCaseOrTypeContainingIgnoreCaseOrFullNameContainingIgnoreCase(request, request, request);
        List<Product> products = productRepo.findAll();
        int count = 0;

        for (Product product : products)
        {
            if (checkForParse(product))
            {
                String model = product.getModelName();//.replaceAll(" ", "-");
                String parseSearchRequest = "https://top-tehnica.ru/search?q=".concat(model.substring(0, model.length() - 3)); ///СТРОГАЯ ЛОГИКА ПОИСКА
                log.info("request " + parseSearchRequest);

                try {
                    Document page = Jsoup.connect(parseSearchRequest).get();
                    Elements links = page.select("a");

                    for (Element linkAttr : links)
                    {
                        String link = linkAttr.attr("href");
                        //log.info(link);

                        if (link.contains("https://top-tehnica.ru//catalog"))
                        {
                            System.out.println("\n");
                            log.info("Ссылка на товар " + link);
                            count++;

                            Document productPage = Jsoup.connect(link).get();
                            Elements pics = productPage.select("img"); ///УТОЧНИТЬ

                            for (Element pic : pics)
                            {
                                String picSrc = pic.attr("src");

                                if (picSrc.contains("image.webp") && product.getPic().equals("no pic"))
                                {
                                    product.setPic("https://top-tehnica.ru/".concat(picSrc));
                                    productRepo.save(product);
                                    log.info("Основная картинка для " + model + ":" + picSrc);
                                }
                                else if (picSrc.contains("/img/product"))
                                {
                                    String fullLink = "https://top-tehnica.ru/".concat(picSrc);

                                    String anotherPic = (product.getPics() == null) ? "" : product.getPics(); ///IF
                                    anotherPic = anotherPic.concat(" ").concat(fullLink);

                                    product.setPics(anotherPic);
                                    productRepo.save(product);
                                    log.info("Ссылка на картинку для " + model + ":" + picSrc);
                                }
                            }

                            if (product.getShortHtmlInfo() == null)
                            {
                                Elements params = productPage.getElementsByClass("n-product-spec-list");
                                for (Element param : params)
                                {
                                    String li = param.html();
                                    product.setShortHtmlInfo(li);
                                    productRepo.save(product);
                                    log.info("Short Info for " + product.getModelName());
                                }
                            }

                            if (product.getFullInfo() == null)
                            {
                                Document productParamsPage = Jsoup.connect(link.concat("/harakteristiki")).get();
                                Elements tab = productParamsPage.getElementsByClass("product-options-table");
                                for (Element element : tab)
                                {
                                    String table = element.html();
                                    product.setFullInfo(table);
                                    productRepo.save(product);
                                    log.info("Full Info for " + product.getModelName());
                                }
                            }
                        }
                    }
                }
                catch (IOException e) {///
                    e.getStackTrace();
                }
            }
            else log.info("Not required for product " + product.getModelName());
        }
        log.info("END OF SEQUENCE! " + count + " Products parsed!");
    }*/

    /*private boolean checkForParse(Product product) {
        return product.getPic().equals("no pic") || product.getShortHtmlInfo() == null || product.getFullInfo() == null;
    }*/

    /*private void resolvePics()
    {
        List<Product> products = productRepo.findAll();
        List<ProductBase> productsBase = baseRepo.findAll();

        for (Product product : products)
        {
            if (product.getPic() == null)
            {
                ProductBase productBase = baseRepo.findFirstByModelNameContaining(product.getModelName());

                if (productBase != null)
                {
                    product.setPic(productBase.getPics());
                    productRepo.save(product);
                    log.info(product.getModelName() + " URL: " + product.getPic());
                }
            }
        }

        *//*for (ProductBase productBase : productsBase)
        {
            Product product = productRepo.findFirstByModelNameContaining(productBase.getModelName());

            if (product != null && product.getPic() == null)
            {
                product.setPic(productBase.getPics());
                productRepo.save(product);
                log.info(product.getModelName() + " URL: " + product.getPic());
            }
        }*//*

        int count = 0;
        for (Product product : products)
        {
            if (product.getPic() != null) count++;
        }

        log.info("PICS SET: " + count + " from " + products.size());
    }*/
}
