package expertshop.services;
import com.opencsv.CSVReader;
import expertshop.domain.Order;
import expertshop.domain.OrderedProduct;
import expertshop.domain.Product;
import expertshop.domain.User;
import expertshop.repos.OrderRepo;
import expertshop.repos.ProductRepo;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

import java.util.*;
import java.util.stream.Collectors;

import static expertshop.controllers.ControllerService.getSessionID;

@Log
@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepo productRepo;
    private final OrderRepo orderRepo;

    public List<Product> findProducts(String requiredProduct)
    {
        log.info(requiredProduct);

        Set<Product> products = productRepo.findByProductGroupContainingIgnoreCase(requiredProduct);
        products.addAll(productRepo.findByTypeContainingIgnoreCase(requiredProduct));

        List<Product> sortedProducts = new ArrayList<>(products);
        sortedProducts.sort(Comparator.comparing(Product::getSupplier));

        products.forEach(productRBT -> log.info(productRBT.toString()));
        log.info("PRODUCTS FOUND: " + products.size());
        return sortedProducts;
    }

    public List<Product> searchProducts(String searchRequest)
    {
        log.info("Search request: " + searchRequest);

        List<Product> searchedProducts = productRepo.findAll().stream()
                .filter(product -> product.getName()/*getBrand().concat(" ").concat(product.getModel())*/.contains(searchRequest))
                .collect(Collectors.toList());
        log.info("Products found: " + searchedProducts.size());

        return searchedProducts;
    }

    public void getOrderedID(User user, Model model)
    {
        if (user != null && orderRepo.findByUserIDAndAcceptedFalse(user.getUserID()) != null)
        {
            Order order = orderRepo.findByUserIDAndAcceptedFalse(user.getUserID());
            collectID(order, model);
        }
        else if (orderRepo.findBySessionUUIDAndAcceptedFalse(getSessionID()) != null)
        {
            Order order = orderRepo.findBySessionUUIDAndAcceptedFalse(getSessionID());
            collectID(order, model);
        }
        else log.info("order empty");
    }

    void collectID(Order order, Model model) {
        Set<String> orderedProductsID = new HashSet<>();

        for (OrderedProduct product : order.getOrderedProducts())
            orderedProductsID.add(product.getProductID().toString());

        model.addAttribute("orderedProductsID", orderedProductsID);
    }

    public void processFile(MultipartFile file) throws IOException
    {
        if (file != null && !Objects.requireNonNull(file.getOriginalFilename()).isEmpty())
        {
            parseFile(file);
        }
    }

    private ArrayList<String> resolveProductRBT(String[] line)
    {
        /// В ОТДЕЛЬНЫЙ КЛАСС
        String[] matches = {
                ///ТЕЛЕ-ВИДЕО-АУДИО
                "Телевизоры","телевизоры","DVB-T2","Кронштейны","Телемебель",
                "Музыкальный ","CD-магнитолы","антенны" ,"Синтезаторы",

                ///ВСТРАЕВАЕМАЯ ТЕХНИКА
                "Электрические духовые шкафы независимые","поверхности независимые","Вытяжки встраиваемые в шкаф",
                "Встраиваемые посудомоечные машины","Встраиваемые холодильники","Встраиваемые микроволновые печи",

                ///КУХОННАЯ ТЕХНИКА
                "холодильники","Морозилка сверху","Морозилка снизу","Многокамерные","Холодильники","Винные шкафы",
                "Морозилки","Морозильные","Эл/плиты","Плиты газ","Микроволновые печи соло","Микроволновые печи гриль","конвекция"
        };

        int match;
        for(match = 0; match < matches.length; match++) {
            if(line[2].contains(matches[match])) break;
        }

        ArrayList<String> types = new ArrayList();

        /*/// В ОТДЕЛЬНЫЙ КЛАСС*/
        switch (match) {
            ///ТЕЛЕ-ВИДЕО-АУДИО
            case 0, 1 -> {
                types.add("Телевизор ");
                types.add("Телевизоры");
            }
            case 2 -> {
                types.add("DVB-T2 Ресивер ");
                types.add("DVB-T2 Ресиверы");}

            case 3 -> {
                types.add("Кронштейн ");
                types.add("Кронштейны");
            }
            case 4 -> {
                types.add(matches[match]+" ");
                types.add(matches[match]);
            }
            case 5 -> {
                types.add("Музыкальный центр ");
                types.add("Музыкальные центры");
            }
            case 6 -> {
                types.add("Магнитола ");
                types.add("Магнитолы");
            }
            case 7 -> {
                types.add("Антенна ");
                types.add("Антенны комнатные");
            }
            case 8 -> {
                types.add("Музыкальный инструмент ");
                types.add("Музыкальный инструмент");
            }

            ///ВСТРАЕВАЕМАЯ ТЕХНИКА
            case 9 -> {
                types.add("Встраиваемый духовой шкаф ");
                types.add("Встраиваемые духовые шкафы");
            }
            case 10 -> {
                types.add("Варочная панель ");
                types.add("Варочные панели");
            }
            case 11 -> {
                types.add("Встраиваемая вытяжка ");
                types.add("Встраиваемые вытяжки");
            }
            case 12 -> {
                types.add("Встраиваемая посудомоечная машина ");
                types.add(matches[match]);
            }
            case 13 -> {
                types.add("Встраиваемый холодильник ");
                types.add(matches[match]);
            }
            case 14 -> {
                types.add("Встраиваемая микроволновая печь ");
                types.add(matches[match]);
            }

            ///КУХОННАЯ ТЕХНИКА
            case 15,16,17,18,19,20 -> {
                types.add("Холодильник ");
                types.add("Холодильники");
            }
            case 21,22 -> {
                types.add("Морозильник ");
                types.add("Морозильники");
            }
            case 23 -> {
                types.add("Электрическая плита ");
                types.add("Электрические плиты");
            }
            case 24 -> {
                types.add("Газовая плита ");
                types.add("Газовые плиты");
            }
            case 25,26,27 -> {
                types.add("Микроволновая печь ");
                types.add("Микроволновые печи");
            }


            default -> {
                types.add("Type ");
                types.add("Group");
            }
        }
        return types;
    }

    private void parseFile(MultipartFile file) throws IOException
    {
        /*FileWriter fw = new FileWriter("filename.txt", Charset.forName("utf-8"));*/

        InputStream inputStream = file.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        CSVReader reader = new CSVReader(bufferedReader, ';');

        List<Product> products = new ArrayList<>();
        String[] line;

        //switch ()

        ////////// ЗАМЕНИТЬ ПРОВЕРКУ НАЗВАНИЙ НА SWITCH И РАЗБИТЬ НА МЕТОДЫ ДЛЯ КАЖДОЙ БАЗЫ
        if (Objects.requireNonNull(file.getOriginalFilename()).contains("СП2")) {
            log.info("\nParsing RBT file: " + file.getOriginalFilename());

            while ((line = reader.readNext()) != null)
            {
                if (line[0].equals("Код товара") ///checkFileRBT(line);
                        || line[0].equals(";")
                        || line[0].contains("г. Челябинск")
                        || line[0].contains("8(351)")
                        || line[0].startsWith(".")
                        || line[0].startsWith(" ")
                ){
                    log.info("!!! Пропуск некоректной строки a" + file.getOriginalFilename() + ": " + line[0]);
                }
                else
                {
                    Product product = new Product();
                    product.setProductID(line[0]);

                    ArrayList<String> typeRBT = resolveProductRBT(line);

                    String productType = typeRBT.get(0);
                    String productGroup = typeRBT.get(1);

                    product.setName(productType + line[3]);
                    product.setProductGroup(productGroup);

                    if (line[2].contains("_")) {
                        product.setType(line[2].substring(line[2].indexOf("_")+1));
                    }
                    else product.setType(line[2]);

                    product.setCategory(line[1]);

                    product.setBrand(line[4]);
                    product.setAnnotation(line[5]);

                    product.setAmount(line[6]);
                    product.setPrice(line[7]);
                    product.setPic(line[10]);

                    product.setSupplier("1RBT");

                    products.add(product);
                    productRepo.save(product);
                }
            }
        }
        else if (Objects.requireNonNull(file.getOriginalFilename()).contains("RUSBT")) {
            log.info("\nParsing RUS_BT file: " + file.getOriginalFilename());

            while ((line = reader.readNext()) != null)
            {
                if (line[0].equals("Код товара") ///checkFileRUSBT(line);
                        || line[0].equals(" ")
                        || line[0].startsWith(";")
                        /*|| line[0].contains(";")
                        || line[0].startsWith("Группа")
                        || line[0].startsWith(" ")
                        || line[6].startsWith(" ")
                        || line[6].contains("ПОДАР")
                        || line[6].contains("УЦЕНКА")
                        || line[6].contains("Уценка")*/
                        || line[5].isEmpty()
                ){
                    log.info("!!! Пропуск некоректной строки " + file.getOriginalFilename() + line[0] + " " + line[0]);
                }
                else
                {
                    if (alreadyAdd(line)) log.info("PRODUCT ALREADY EXISTS");
                    else {

                        Product product = new Product();
                        if (!line[6].isEmpty() && line[6].contains(","))
                        {
                            String name = line[6].substring(0, line[6].indexOf(","));
                            String info = line[6].substring(line[6].indexOf(",")+1);

                            product.setName(name);
                            product.setAnnotation(info);
                        }
                        else product.setName(line[6]);

                        product.setProductID(line[5]);
                        product.setCategory(line[0]);
                        product.setSubCategory(line[1]);

                        String productGroup = resolveProductRUS(line);
                        product.setProductGroup(productGroup);

                        product.setType(line[3]);
                        product.setBrand(line[4]);
                        product.setAmount(line[7]+line[8]);
                        product.setPrice(line[13]);

                        product.setSupplier("2RUS_BT");

                        if (picRequired(line)) {
                            String pic = parsePicFromHTML(line[17]);
                            product.setPic(pic);
                        }

                        products.add(product);
                        productRepo.save(product);
                    }
                }
            }
        }
        else if (Objects.requireNonNull(file.getOriginalFilename()).contains("Доставка")) {
            log.info("\nParsing M_TRADE file: " + file.getOriginalFilename());

            while ((line = reader.readNext()) != null)
            {
                if (line[0].equals("Код товара") ///checkFileMTRADE(line);
                        || line[0].startsWith(";")
                        || line[0].equals("КОД")
                        || line[0].startsWith(" ")
                ){
                    log.info("!!! Пропуск некоректной строки " + file.getOriginalFilename() + " " + line[0]);
                }
                else
                {
                    log.info(Arrays.toString(line));

                    Product product = new Product();

                    product.setProductID(line[0]);

                    product.setCategory(line[1]);
                    product.setType(line[4]);

                    product.setProductGroup(resolveTypeMT(line[2]));

                    product.setName(resolveNameMT(line[5]));
                    log.info(product.getName());

                    product.setBrand(line[3]);
                    product.setAnnotation(line[6]);

                    product.setPrice(line[7]);
                    product.setAmount(line[8]);

                    product.setSupplier("3M_TRADE");
                    /*product.setPic(line[10]);*/

                    products.add(product);
                    productRepo.save(product);
                }
            }
        }
        log.info("Products add: " + products.size());
        products.forEach(product -> log.info(product.toString()));
    }

    private boolean alreadyAdd(String[] line) {
        Product product = productRepo.findByProductID(line[5]);
        if (product == null) return false;
        else {
            return product.getPrice().equals(line[13]) && product.getAmount().equals(line[7]+line[8]);
        }
    }

    private boolean picRequired(String[] line) {
        if (productRepo.findByProductID(line[5]) != null && productRepo.findByProductID(line[5]).getPic() != null) {
            return false;
        }
        else if (productRepo.findByProductID(line[5]) != null && productRepo.findByProductID(line[5]).getPic() == null) {
            return true;
        }
        else return true;
    }

    private String parsePicFromHTML(String link) throws IOException {
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
        }
        else log.info("ССЫЛКА ОТСУТСТВУЕТ!");
        return null;
    }

    private String resolveProductRUS(String[] line) {
        if (line[2].contains("Холод.")) {
            return line[2].replaceAll("Холод.", "Холодильники");
        }
        else return line[2];
    }

    private String resolveNameMT(String line5) {
        if (line5.contains("телевизоры")) {
            return "Телевизор ".concat(line5.replaceAll("телевизоры", ""));
        }
        else if (line5.contains("Жидкокристаллический телевизор")) {
            return "Телевизор ".concat(line5.replaceAll("Жидкокристаллический телевизор", ""));
        }
        else if (line5.contains("телевизор")) {
            return "Телевизор ".concat(line5.replaceAll("телевизор", ""));
        }
        else if (line5.contains("Телевизор")) {
            return "Телевизор ".concat(line5.replaceAll("Телевизор", ""));
        }
        else return line5;
    }

    private String resolveTypeMT(String line2) {
        if (line2.contains("LCD")) {
            return "Телевизоры";
        }
        else return line2;
    }
}







