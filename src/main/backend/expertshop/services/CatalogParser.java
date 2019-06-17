package expertshop.services;
import com.opencsv.CSVReader;

import expertshop.domain.Product;
import expertshop.domain.ProductBase;
import expertshop.repos.ProductBaseRepo;
import expertshop.repos.ProductRepo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;

@Log
@Service
@AllArgsConstructor
public class CatalogParser {
    private final ProductRepo productRepo;
    private final ProductBaseRepo baseRepo;

    public void processFile(MultipartFile file) throws IOException
    {
        if (file != null && !Objects.requireNonNull(file.getOriginalFilename()).isEmpty())
        {
            parseFile(file);
            resolvePics();
        }
    }

    private void resolvePics()
    {
        List<Product> products = productRepo.findAll();
        List<ProductBase> productsBase = baseRepo.findAll();

        for (Product product : products) {
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

        for (ProductBase productBase : productsBase) {
            Product product = productRepo.findFirstByModelNameContaining(productBase.getModelName());

            if (product != null && product.getPic() == null)
            {
                product.setPic(productBase.getPics());
                productRepo.save(product);
                log.info(product.getModelName() + " URL: " + product.getPic());
            }
        }

        int count = 0;
        for (Product product : products)
        {
            if (product.getPic() != null) count++;
        }

        log.info("PICS SET: " + count + " from " + products.size());
    }

    private void parseFile(MultipartFile file) throws IOException
    {
        /*FileWriter fw = new FileWriter("filename.txt", Charset.forName("utf-8"));*/
        /*InputStream inputStream = file.getInputStream();*/

        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

            CSVReader reader = new CSVReader(bufferedReader, ';');

            String[] line;
            List<Product> products = new ArrayList<>();

            if (Objects.requireNonNull(file.getOriginalFilename()).contains("BASE")) {
                parseBase(file);
            }

            /// ЗАМЕНИТЬ ПРОВЕРКУ НАЗВАНИЙ НА SWITCH И РАЗБИТЬ НА МЕТОДЫ ДЛЯ КАЖДОЙ БАЗЫ
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

                        ArrayList<String> typeRBT = resolveProductRBT(line);

                        String productType = typeRBT.get(0);
                        String productGroup = typeRBT.get(1);
                        String productName = line[3];
                        String productBrand = line[4];

                        ///resolveModelName();
                        String trimName = trimProductName(productName, productBrand/*line[5], line[3]*/);
                        String trimAfterComma = trimAllAfterComma(trimName);
                        String noRussian = noCyrillicChars(trimAfterComma, productBrand/*line[3]*/);
                        String trimGarbage = trimGarbageChar(noRussian);
                        String finalName = removeAllSpacesAfterBrand(trimGarbage, productBrand);

                        product.setModelName(finalName);
                        //log.info("MODEL NAME: " + product.getModelName());

                        product.setFullName(productType + line[3]);
                        product.setProductGroup(productGroup);
                        product.setProductID(line[0]);

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

                    /*String trimName = trimProductName(line[3], line[4]);

                    product.setModelName(trimAllAfterComma(trimName));
                    log.info("|||| MODEL NAME: " + product.getModelName());*/

                        product.setSupplier("1RBT");
                        products.add(product);
                        productRepo.save(product);
                    }
                }
            }
            else if (Objects.requireNonNull(file.getOriginalFilename()).contains("RUSBT"))
            {
                log.info("Parsing RUS_BT file: " + file.getOriginalFilename());

                while ((line = reader.readNext()) != null)
                {
                    if (line[0].equals("Код товара") ///checkFileRUSBT(line);
                            || line[0].equals(" ")
                            || line[0].startsWith(";")
                            || line[5].isEmpty()
                    ){
                        log.info("!!! Пропуск некоректной строки " + file.getOriginalFilename() + line[0] + " " + line[0]);
                    }
                    else
                    {
                        if (alreadyAdd(line)) log.info("PRODUCT ALREADY EXISTS!");
                        else {

                            Product product = new Product();
                            String productName = line[6];
                            String productBrand = line[4];

                            String trimName = trimProductName(productName, productBrand/*line[5], line[3]*/);
                            String trimAfterComma = trimAllAfterComma(trimName);
                            String noRussian = noCyrillicChars(trimAfterComma, productBrand/*line[3]*/);
                            String trimGarbage = trimGarbageChar(noRussian);
                            String finalName = removeAllSpacesAfterBrand(trimGarbage, productBrand);
                            product.setModelName(finalName);
                            //log.info("MODEL NAME: " + product.getModelName());

                            if (!line[6].isEmpty() && line[6].contains(","))
                            {
                                String name = line[6].substring(0, line[6].indexOf(","));
                                String info = line[6].substring(line[6].indexOf(",")+1);

                                product.setFullName(name);
                                product.setAnnotation(info);
                            }
                            else product.setFullName(line[6]);

                            if (picRequiredRUS(line[5]))
                            {
                                String pic = parsePicFromHTML(line[17]);
                                product.setPic(pic);
                            }

                            String productGroup = resolveProductRUS(line);
                            product.setProductGroup(productGroup);

                            product.setProductID(line[5]);
                            product.setCategory(line[0]);
                            product.setSubCategory(line[1]);
                            product.setType(line[3]);
                            product.setBrand(line[4]);
                            product.setAmount(line[7]+line[8]);
                            product.setPrice(line[13]);

                            product.setSupplier("2RUS_BT");
                            products.add(product);
                            productRepo.save(product);
                        }
                    }
                }
            }
            else if (Objects.requireNonNull(file.getOriginalFilename()).contains("Доставка"))
            {
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
                        Product product = new Product();
                        String productName = line[5];
                        String productBrand = line[3];

                        ///resolveModelName()
                        String trimName = trimProductName(productName, productBrand);
                        String trimAfterComma = trimAllAfterComma(trimName);
                        String noRussian = noCyrillicChars(trimAfterComma, productBrand);
                        String trimGarbage = trimGarbageChar(noRussian);
                        String finalName = removeAllSpacesAfterBrand(trimGarbage, productBrand);
                        product.setModelName(finalName);
                        //log.info("MODEL NAME: " + product.getModelName());

                        product.setFullName(resolveNameMT(line[5]));

                        product.setProductID(line[0]);
                        product.setCategory(line[1]);
                        product.setType(line[4]);
                        product.setProductGroup(resolveTypeMT(line[2]));
                        product.setBrand(line[3]);
                        product.setAnnotation(line[6]);
                        product.setPrice(line[7]);
                        product.setAmount(line[8]);

                        product.setSupplier("3M_TRADE");
                        products.add(product);
                        productRepo.save(product);
                    }
                }
            }
            //products.forEach(product -> log.info(product.toString()));
            log.info("Products add: " + products.size());
        }
        catch (Exception exp) {
            log.info("Something wrong!");
        }
    }

    private String removeAllSpacesAfterBrand(String trimGarbage, String productBrand) {
        String result = "";
        String temp = "";

        if (productBrand.contains(" ")) productBrand = productBrand.replaceAll(" ", "_");

        if (trimGarbage.contains(" ")) {
            temp = trimGarbage.substring(trimGarbage.indexOf(" "));
            temp = StringUtils.trimAllWhitespace(temp);
            //String temp = trimGarbage.substring(trimGarbage.indexOf(" ")).replaceAll(" ", "").replaceAll("-", "");
        }

        if (temp.contains("-")) {
            temp = temp.replaceAll("-", "");
        }

        if (!temp.isEmpty()) {
            result = productBrand.toLowerCase().concat(" ").concat(temp);
        }

        return result.isEmpty() ? trimGarbage : result;
        /*String[] result;
        result = trimGarbage.split(productBrand);
        for (String s : result) {
            System.out.println("!!! " + s);
        }
        return trimGarbage;*/
    }

    private String trimGarbageChar(String string) {
        String result = "";

        if (string.contains("(")) {
            result = string.split("\\(")[0];
        }

        if (string.contains("/")) {
            result = string.split("/")[0];
        }

        if (!result.isEmpty()) {
            result = result.trim();

            if (result.endsWith(".") || result.endsWith("-") || result.endsWith("+")) {
                result = result.substring(0, result.length() - 2);
            }

            /*if (result.endsWith("."))  result = result.substring(0, result.length() - 1);*/

            int occurrence = StringUtils.countOccurrencesOf("result", " ");
            if (occurrence >= 4) return result.substring(0, result.lastIndexOf(" "));
        }

        /*if (!result.isEmpty()) {
            return result.substring(0, result.lastIndexOf(" "));
        }
        else return noRussian;*/

        return result.isEmpty() ? string : result;
    }

    private String noCyrillicChars(String trimAfterComma, String brand)
    {
        //String test = "jkb5!2@-=|/.;:af23ывапыв";
        //if (Regex.Match(inputString, "[а-яА-ЯёЁ]"))

        String regex = "[а-яёА-ЯЁ]+"; /*"[а-яА-Я]*"*/
        Pattern pattern = Pattern.compile(regex);
        Matcher match = pattern.matcher(brand);

        if (!match.find()){
            String result = trimAfterComma.replaceAll("[а-яёА-ЯЁ]*", "");
            //log.info("FINAL TRIM REGEX"+result);
            return result;
        }
        return trimAfterComma;
    }

    private String trimAllAfterComma(String trimName)
    {
        //log.info(trimName);
        String result = "";

        if (trimName.contains("\"")) {
            result = trimName.substring(0, trimName.indexOf("\""));
        }

        if (trimName.contains(",")) {
            result = trimName.substring(0, trimName.indexOf(","));
        }

        /*if (trimName.contains("(")) {
            result = trimName.substring(trimName.indexOf("("));
        }*/

        return result.isEmpty() ? trimName : result;
    }

    private void parseBase(MultipartFile file) throws IOException
    {
        try {


            /*InputStream inputStream = file.getInputStream();*/
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

    /*private String trimUntilBrandFirst(String fullName, String brand) {
        String result;
        try {
            if (!fullName.startsWith(brand)) {
                return fullName.substring(fullName.indexOf(" ")).trim();
                //log.info("Result1: " + result);
            }
        }
        catch (StringIndexOutOfBoundsException e) {
            log.info("Out of");
        }
        return "lol";
    }*/

    ///В РЕКУРСИЮ
    private String trimProductName(String fullNameCAT, String brandCAT)
    {
        String fullName = fullNameCAT.toLowerCase();
        String brand = brandCAT.toLowerCase();
        //log.info("BRAND: " + brand + " ||| NAME: " + fullName);

        String result;

        if (!brand.isEmpty() && fullName.contains(" ")) {
            try {
                if (!fullName.startsWith(brand) && fullName.contains(" "))
                {
                    result = fullName.substring(fullName.indexOf(" ")).trim();
                    //log.info("Result1: " + result);
                    if (!result.startsWith(brand.trim()))
                    {
                        result = result.substring(result.indexOf(" ")).trim();
                        //log.info("Result2: " +result);
                        if (!result.startsWith(brand.trim()))
                        {
                            result = result.substring(result.indexOf(" ")).trim();
                            //log.info("Result3: " +result);
                            if (!result.startsWith(brand.trim()))
                            {
                                result = result.substring(result.indexOf(" ")).trim();
                                //log.info("Result4: " +result);
                                if (!result.startsWith(brand.trim()))
                                {
                                    result = result.substring(result.indexOf(" ")).trim();
                                    //log.info("Result4: " +result);
                                }
                                else return result;
                            }
                            else return result;
                        }
                        else return result;
                    }
                    else return result;
                }
                else return fullName;
            }
            catch (StringIndexOutOfBoundsException e) {
                log.info("Out of");
            }
        }
        return "ZZZ";
    }

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
        return null;
    }

    /*private String findPicAndParse(String requestName) throws IOException
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

    ///ПАРАМЕТРЫ МЕТОДА В STRING
    private boolean alreadyAdd(String[] line)
    {
        Product product = productRepo.findByProductID(line[5]);
        if (product == null) return false;
        else {
            return product.getPrice().equals(line[13]) && product.getAmount().equals(line[7]+line[8]);
        }
    }

    ///ЛОГИКА
    private boolean picRequiredRUS(String line)
    {
        if (productRepo.findByProductID(line) != null && productRepo.findByProductID(line).getPic() != null) {
            return false;
        }
        else if (productRepo.findByProductID(line) != null && productRepo.findByProductID(line).getPic() == null) {
            return true;
        }
        else return true;
    }

    private ArrayList<String> resolveProductRBT(String[] line)
    {
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

    public void test() {
    }
}
