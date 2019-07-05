package expertshop.products;
import com.opencsv.CSVReader;
import expertshop.domain.Product;
import expertshop.repos.ProductBaseRepo;
import expertshop.repos.ProductRepo;
import expertshop.services.ProductService;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;

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
import java.util.Objects;

@Log
@Service
@AllArgsConstructor
public class CatalogParser {
    private final ProductService productService;
    private final ProductRepo productRepo;
    private final ProductBaseRepo baseRepo;

    public void processFile(MultipartFile file)
    {
        if (file != null && !Objects.requireNonNull(file.getOriginalFilename()).isEmpty())
        {
            parseFile(file);
        }
    }

    private void parseFile(MultipartFile file)
    {
        /*
        FileWriter fw = new FileWriter("filename.txt", Charset.forName("utf-8"));
        InputStream inputStream = file.getInputStream();
        */

        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file.getInputStream())))
        {
            if (fileRBT(file)) parseRBT(file, bufferedReader);
        }
        catch (Exception exp) {
            log.info("Something wrong!");
        }
    }

    private void parseRBT(MultipartFile file, BufferedReader bufferedReader) throws IOException {
        log.info("\nParsing RBT file: " + file.getOriginalFilename());

        int countAdd = 0, countUpdate = 0;
        String[] line;
        CSVReader reader = new CSVReader(bufferedReader, ';');

        while ((line = reader.readNext()) != null)
        {
            if (lineIsCorrect(line))
            {
                String productID = line[0];
                if (productAlreadyExists(productID))
                {
                    String productAmount          = line[6];
                    String productPrice           = line[7];
                    log.info("Updating file" + line[3]);

                    if (checkProductForUpdate(productID, productAmount, productPrice))
                    {
                        updateProductStats(productID, productAmount, productPrice);
                    }
                    else updateProductAvailable(productID);
                    countUpdate++;
                }
                else
                {
                    createProductFromRBT(line);
                    countAdd++;
                }
            }
            else log.info("!!! Пропуск некоректной строки " + file.getOriginalFilename());

            /*if (incorrectLineRBT(line)) log.info("!!! Пропуск некоректной строки " + file.getOriginalFilename());
            else
            {
                String productID        = line[0];
                if (productAlreadyExists(productID))
                {
                    String productAmount    = line[6];
                    String productPrice     = line[7];
                    log.info("Updating file" + line[3]);

                    if (checkProductForUpdate(productID, productAmount, productPrice))
                    {
                        updateProductStats(productID, productAmount, productPrice);
                        countUpdate++;
                    }
                    else updateProductAvailable(productID);
                }
                else
                {
                    createProductFromRBT(line);
                    countAdd++;
                }
            }*/
        }
        log.info("Products add: " + countAdd);
        log.info("Products updated: " + countUpdate);
    }

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
        product.setSupplier("1RBT");
        product.setUpdate(LocalDate.now());

        product.setOriginalPic(line[10]);
        productRepo.save(product);
    }

    private boolean productAlreadyExists(String productID) {
        return productRepo.findByProductID(productID) != null;
    }

    private void updateProductStats(String productID, String amount, String price)
    {
        Product product = productRepo.findByProductID(productID);
        product.setOriginalAmount(amount);
        product.setOriginalPrice(price);
        product.setUpdate(LocalDate.now());
        productRepo.save(product);
        log.info("Updating " + productID);
    }

    private void updateProductAvailable(String productID) {
        Product product = productRepo.findByProductID(productID);
        product.setUpdate(LocalDate.now());
        productRepo.save(product);
        log.info("Updated : " + productID);
    }

    private boolean checkProductForUpdate(String productID, String amount, String price) {
        Product product = productRepo.findByProductID(productID);
        return !product.getOriginalAmount().equals(amount) || !product.getOriginalPrice().equals(price);
    }

    private boolean fileRBT(MultipartFile file) {
        return Objects.requireNonNull(file.getOriginalFilename()).contains("СП2");
    }

    private boolean lineIsCorrect(String[] line) {
        return !line[0].equals("Код товара") & !line[0].equals(";")      & !line[0].contains("г. Челябинск") &
               !line[0].contains("8(351)")   & !line[0].startsWith(".")  & !line[0].startsWith(" ");
    }

    private boolean incorrectLineRBT(String[] line) {
        return line[0].equals("Код товара")     || line[0].equals(";")      || line[0].contains("г. Челябинск")
                || line[0].contains("8(351)")   || line[0].startsWith(".")  || line[0].startsWith(" ");
    }

    /*
    private void parseRUSBT(MultipartFile file, BufferedReader bufferedReader) throws IOException
    {
        log.info("Parsing RUS_BT file: " + file.getOriginalFilename());

        int countAdd = 0, countUpdate = 0;
        String[] line;
        CSVReader reader = new CSVReader(bufferedReader, ';');

        while ((line = reader.readNext()) != null)
        {
            if (incorrectLineRUSBT(line))
            {
                log.info("!!! Пропуск некоректной строки " + file.getOriginalFilename());
            }
            else
            {
                if (productAlreadyExists(line[5]))
                {
                    log.info(line[5] + " File already exists");
                    if (checkProductForUpdate(line[5],line[7]+line[8], line[13]))
                    {
                        updateProductStats(line[5], line[7]+line[8], line[13]);
                        countUpdate++;
                    }
                }
                else
                {
                    Product product = new Product();
                    product.setProductID(line[5]);

                    product.setOriginalCategory(line[0]);
                    *//*product.setOriginalSubCategory(line[1]);
                    product.setOriginalGroup(line[2]);*//*
                    product.setOriginalType(line[3]);

                    product.setOriginalBrand(line[4]);
                    product.setOriginalName(line[6]);
                    product.setOriginalAnnotation("n/a");
                    product.setOriginalAmount(line[7]+line[8]);
                    product.setOriginalPrice(line[13]);

                    product.setSupplier("2RUS-BT");
                    product.setUpdate(LocalDate.now());

                    product.setOriginalPic(line[17]);

                    countAdd++;
                    productRepo.save(product);
                }
            }
        }
        log.info("Products add: " + countAdd);
        log.info("Products updated: " + countUpdate);
    }

    private void parseMTRADE(MultipartFile file, BufferedReader bufferedReader) throws IOException
    {
        log.info("\nParsing M_TRADE file: " + file.getOriginalFilename());

        int countAdd = 0, countUpdate = 0;
        String[] line;
        CSVReader reader = new CSVReader(bufferedReader, ';');

        while ((line = reader.readNext()) != null)
        {
            if (incorrectLineMTRADE(line))
            {
                log.info("!!! Пропуск некоректной строки " + file.getOriginalFilename());
            }
            else
            {
                if (productAlreadyExists(line[0]))
                {
                    log.info(line[0] + " File already exists");
                    if (checkProductForUpdate(line[0], line[8], line[7]))
                    {
                        updateProductStats(line[0], line[8], line[7]);
                        countUpdate++;
                    }
                }
                else {
                    Product product = new Product();

                    product.setProductID(line[0]);

                    product.setOriginalCategory(line[1]);
                    *//*product.setOriginalSubCategory("n/a");
                    product.setOriginalGroup(line[2]);*//*
                    product.setOriginalType(line[4]);

                    product.setOriginalBrand(line[3]);
                    product.setOriginalName(line[5]);
                    product.setOriginalAnnotation(line[6]);
                    product.setOriginalAmount(line[8]);
                    product.setOriginalPrice(line[7]);

                    product.setSupplier("3M-TRADE");
                    product.setUpdate(LocalDate.now());

                    countAdd++;
                    productRepo.save(product);
                }
            }
        }
        log.info("Products add: " + countAdd);
        log.info("Products updated: " + countUpdate);

    private boolean fileMTRADE(MultipartFile file) {
        return Objects.requireNonNull(file.getOriginalFilename()).contains("Доставка");
    }
    private boolean fileRUSBT(MultipartFile file) {
        return Objects.requireNonNull(file.getOriginalFilename()).contains("RUSBT");
    }
    }*/


    /*
    private boolean incorrectLineRUSBT(String[] line)
    {
        return line[0].equals("Код товара") || line[0].isEmpty() || line[0].startsWith(";") || line[5].isEmpty()
                || line[13].contains("Цена со скидкой");
    }

    private boolean incorrectLineMTRADE(String[] line)
    {
        return (line[0].equals("Код товара") || line[0].startsWith("ПРАЙС") || line[0].isEmpty() || line[0].startsWith(";") || line[0].equals("КОД") || line[0].startsWith(" "));
    }
    */

    /*
    private void parseBase(MultipartFile file) throws IOException
    {
        try {
            *//*InputStream inputStream = file.getInputStream();*//*
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            CSVReader reader = new CSVReader(bufferedReader, ';');

            String[] line;
            List<ProductBase> products = new ArrayList<>();

            while ((line = reader.readNext()) != null)
            {
                if (line[0].equals("Категория")
                ){
                    log.info("!!! Пропуск первой строки");
                }
                else
                {
                    ProductBase product = new ProductBase();
                    product.setProductID(UUID.randomUUID().toString());

                    String productBrand = line[13];

                    try {
                        product.setCategory(line[0]);
                        product.setSubCategory(line[1]);

                        if (line[2].isEmpty()) {
                            product.setProductGroup(line[1]);
                        }
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

                        String trim = trimProductName(line[5], line[13]);
                        String finalName = removeAllSpacesAfterBrand(trim, productBrand);
                        product.setModelName(finalName);

                        products.add(product);
                        //log.info(product.getFullName());
                        baseRepo.save(product);
                    }
                    catch (ArrayIndexOutOfBoundsException e) {
                        //log.info("EXP!");
                    }

                }
            }
            log.info("Products add: " + products.size());
        }
        catch (Exception exp) {
            log.info("Something went wrong with Base!");
        }
    }
    */

    /*
    private String parsePicFromHTML(String link) throws IOException
    {
        if (!link.isEmpty() && !link.equals("В ячейке нет гиперссылки!")) {
            try {
                String pic;
                Document page = Jsoup.connect(link).get();

                Elements pics = page.select("img");

                for (Element picElement : pics)
                {
                    String picSrc = picElement.attr("src");
                    log.info(picSrc);

                    if (picSrc.startsWith("/upload"))
                    {
                        pic = "http://rusbt.ru".concat(picSrc);
                        log.info("ПОЛНАЯ ССЫЛКА ДЛЯ БД " + pic);
                        return pic;
                    }
                    else log.info("Нет изображения товара на сайте!");
                }
                return "no pic";
            }
            catch (HttpStatusException exp) {
                log.info("EXP Page is empty");
            }
            catch (ConnectException exp) {
                log.info("Connection timed out");
            }
        }
        else log.info("ССЫЛКА ОТСУТСТВУЕТ!");
        return "NO PIC";
    }
    */

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
