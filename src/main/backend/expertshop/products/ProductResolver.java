package expertshop.products;
import expertshop.domain.Product;
import expertshop.repos.ProductRepo;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Log
@Service
@AllArgsConstructor
public class ProductResolver {
    private final ProductRepo productRepo;

    public void resolveProductGroups()
    {
        List<Product> products = productRepo.findAll();

        /*ТЕЛЕ-ВИДЕО-АУДИО*/
        String[] tv             = {"Телевизоры", "телевизоры", "телевизор"};
        String[] receivers      = {"ресиверы", "TV-тюнер", "Цифровая тв приставка"};
        String[] tvCables       = {"разъемы для ТВА", "15.08.24.03 Кабели питания"};
        String[] tvKronshteyn   = {"Кронштейны", "Кронштейны для ТВ", "кронштейны"};
        String[] antennas       = {"Комнатные антенны", "Антенны телевизионные"};

        /*ВСТРАИВАЕМАЯ ТЕХНИКА*/
        String[] builtInOvens       = {"Встраиваемые духовые шкафы", "Духовые шкафы"};
        String[] builtInCookSurf    = {"Встраиваемые варочные поверхности", "варочные поверхности"};
        String[] builtInHoods       = {"Вытяжки встраиваемые", "вытяжки", "07.05.02_Вытяжки", "07.05.03_Вытяжки", "07.05.04_Вытяжки", "07.05.05_Вытяжки"};
        String[] builtInFridges     = {"07.07_Встраиваемые", "Холодильники встраиваемые"};
        String[] builtInMicroWaves  = {"07.08"};

        /*КУХОННАЯ ТЕХНИКА*/
        String[] fridges        = {"01.01.02", "Холодильники"};
        String[] freezers       = {"01.01.03", "Морозильники"};
        String[] elStoves       = {"Эл/плиты", "Плиты стационарные электрические", "Электрическая плита"};
        String[] gasStoves      = {"01.03", "Плиты стационарные газовые", "Газовая плита"};
        String[] microWaves     = {"01.05", "Микроволновые печи"};
        String[] teaPods        = {"01.18", "Чайники электрические", "чайники"};
        String[] coffeeMakers   = {"01.19", "Кофеварки"};
        String[] multiCooker    = {"01.12", "Мультиварки"};
        String[] blenders       = {"01.08.03", "01.08.04" , "Блендеры"};
        String[] mixers         = {"01.08.01", "01.08.02" , "Миксеры"};
        String[] meatGrinders   = {"01.10.02", "01.10.01" , "Мясорубки"};
        String[] breadMaker     = {"01.15", "Хлебопечи"};
        String[] pans           = {"15.15.02.02", "Кастрюли"};
        String[] dishWashers    = {"01.04", "Посудомоечные машины"};

        /*ТЕХНИКА ДЛЯ ДОМА*/
        String[] washingMachines = {"03.01", "Стиральные машины"};
        String[] vacuumCleaners  = {"03.02.01", "Пылесосы"};
        String[] irons           = {"03.03.01", "Утюги"};
        String[] sewingMachines  = {"03.07", "Швейные машины"};
        String[] steamers        = {"03.03.02", "03.03.03", "03.03.04", "Отпариватели"};

        main: for (Product product : products)
        {
            String type  = product.getOriginalType();
            String name  = product.getOriginalName();
            String group = product.getOriginalGroup();
            String subCategory = product.getOriginalSubCategory() != null ? product.getOriginalSubCategory() : "n/a";

            if (product.getProductGroup() == null)
            {
                /*ТЕХНИКА ДЛЯ ДОМА*/
                if (type.startsWith("03.01.02")) {
                    setProductGroup(product, "Сушильные барабаны", "Сушильный барабан");
                }
                for (String alias : steamers) {
                    if (type.startsWith(alias) || (group.startsWith(alias) && !type.startsWith("Аксессуары для"))) {
                        setProductGroup(product,"Отпариватели", "Отпариватель");
                        continue main;
                    }}
                for (String alias : sewingMachines) {
                    if (type.startsWith(alias) || StringUtils.equalsIgnoreCase(group, alias)) {
                        setProductGroup(product,"Швейные машины", "Швейная машина");
                        continue main;
                    }}
                for (String alias : irons) {
                    if (type.startsWith(alias) || StringUtils.startsWithIgnoreCase(type, alias)) {
                        setProductGroup(product,"Утюги", "Утюг");
                        continue main;
                    }}
                for (String alias : vacuumCleaners) {
                    if (type.startsWith(alias) || (group.equals(alias) && !type.contains("для пылесосов"))) {
                        setProductGroup(product,"Пылесосы", "Пылесос");
                        continue main;
                    }}
                for (String alias : washingMachines) {
                    if ((type.startsWith(alias) && !type.contains("03.01.02")) || StringUtils.startsWithIgnoreCase(group, alias)) {
                        setProductGroup(product,"Стиральные машины", "Стиральная машина");
                        continue main;
                    }
                }

                /*КУХОННАЯ ТЕХНИКА*/
                for (String alias : dishWashers) {
                    if (type.startsWith(alias) || StringUtils.equalsIgnoreCase(group, alias)) {
                        setProductGroup(product,"Посудомоечные машины", "Посудомоечная машина");
                        continue main;
                    }}
                for (String alias : pans) {
                    if (type.startsWith(alias) || StringUtils.equalsIgnoreCase(type, alias)) {
                        setProductGroup(product,"Кастрюли", "Кастрюля");
                        continue main;
                    }}
                for (String alias : breadMaker) {
                    if (type.startsWith(alias) || StringUtils.equalsIgnoreCase(type, alias)) {
                        setProductGroup(product,"Хлебопечи", "Хлебопечь");
                        continue main;
                    }}
                for (String alias : meatGrinders) {
                    if (type.startsWith(alias) || StringUtils.equalsIgnoreCase(type, alias)) {
                        setProductGroup(product,"Мясорубки", "Мясорубка");
                        continue main;
                    }}
                for (String alias : mixers) {
                    if (type.startsWith(alias) || group.equals(alias) || StringUtils.equalsIgnoreCase(type, alias)) {
                        setProductGroup(product,"Миксеры", "Миксер");
                        continue main;
                    }}
                for (String alias : blenders) {
                    if (type.startsWith(alias) || group.startsWith(alias) || (type.startsWith(alias) && StringUtils.equalsIgnoreCase(type, alias))) {
                        setProductGroup(product,"Блендеры", "Блендер");
                        continue main;
                    }}
                for (String alias : multiCooker) {
                    if (StringUtils.containsIgnoreCase(type, alias)) {
                        setProductGroup(product,"Мультиварки", "Мультиварка");
                        continue main;
                    }}
                for (String alias : coffeeMakers) {
                    if (type.startsWith(alias) || group.startsWith(alias)) {
                        setProductGroup(product,"Кофеварки", "Кофеварка");
                        continue main;
                    }}
                for (String alias : teaPods) {
                    if (type.contains(alias) || group.startsWith(alias)) {
                        setProductGroup(product,"Электрические чайники", "Электрический чайник");
                        continue main;
                    }}
                for (String alias : microWaves) {
                    if (type.startsWith(alias) || group.startsWith(alias) || name.startsWith(alias)) {
                        setProductGroup(product,"Микроволновые печи", "Микроволновая печь");
                        continue main;}}
                for (String alias : gasStoves) {
                    if (type.startsWith(alias) || group.startsWith(alias) || name.contains(alias)) {
                        setProductGroup(product,"Газовые плиты", "Газовая плита");
                        continue main;
                    }}
                for (String alias : elStoves) {
                    if (type.startsWith(alias) || group.startsWith(alias) || name.contains(alias)) {
                        setProductGroup(product,"Электрические плиты", "Эликтрическая плита");
                        continue main;
                    }}
                for (String alias : freezers) {
                    if (type.startsWith(alias) || StringUtils.containsIgnoreCase(subCategory, alias)) {
                        setProductGroup(product,"Морозильники", "Морозильник");
                        continue main;
                    }}
                for (String alias : fridges) {
                    if (type.startsWith(alias) || group.startsWith(alias) || subCategory.startsWith(alias)) {
                        setProductGroup(product,"Холодильники", "Холодильник");
                        continue main;
                    }
                }

                /*ВСТРАИВАЕМАЯ ТЕХНИКА*/
                if (type.startsWith("07.06")) {
                    setProductGroup(product,"Встраиваемые посудомоечные машины", "Встраиваемая посудомоечная машина");
                }
                for (String alias : builtInMicroWaves) {
                    if (type.contains(alias) || group.contains(alias)) {
                        setProductGroup(product,"Встраиваемые СВЧ", "Встраиваемая СВЧ");
                        continue main;
                    }}
                for (String alias : builtInFridges) {
                    if (type.contains(alias) || group.contains(alias)) {
                        setProductGroup(product,"Встраиваемые холодильники", "Холодильник встраиваемый");
                        continue main;
                    }}
                for (String alias : builtInOvens) {
                    if (group.contains(alias) || subCategory.contains(alias)) {
                        setProductGroup(product,"Встраиваемые духовые шкафы", "Встраиваемый духовой шкаф");
                        continue main;
                    }}
                for (String alias : builtInCookSurf) {
                    if (group.contains(alias) || subCategory.contains(alias)) {
                        setProductGroup(product,"Встраиваемые варочные панели", "Встраиваемая варочная панель");
                        continue main;
                    }}
                for (String alias : builtInHoods) {
                    if (group.contains(alias) || type.contains(alias) && (!type.startsWith("15.07.03.02 Аксессуары") && !type.startsWith("15.07.03.01 Фильтры"))) {
                        setProductGroup(product,"Встраиваемые вытяжки", "Встраиваемая вытяжка");
                        continue main;
                    }
                }

                /*ТЕЛЕ-ВИДЕО-АУДИО*/
                for (String alias : tv) {
                    if (type.contains(alias) || group.contains(alias) || name.contains(alias) && !type.contains("Клавиатуры")) {
                        setProductGroup(product,"Телевизоры", "Телевизор");
                        continue main;
                    }}
                for (String alias : receivers) {
                    if (type.contains(alias) || name.contains(alias)) {
                        setProductGroup(product,"Ресиверы для тв", "Цифровой ресивер");
                        continue main;
                    }}
                for (String alias : tvCables) {
                    if (type.contains(alias)) {
                        setProductGroup(product,"Кабели ТВ", "ТВ кабель");
                        continue main;
                    }}
                for (String alias : tvKronshteyn) {
                    if (type.contains(alias) && !type.contains("AV") && !type.contains("СВЧ")) {
                        setProductGroup(product,"Кронштейны ТВ", "ТВ кронштейн");
                        continue main;
                    }}
                for (String alias : antennas) {
                    if (type.contains(alias)) {
                        setProductGroup(product,"Антенны ТВ", "ТВ антенна");
                        continue main;
                    }}
                if (type.contains("Телемебель")) {
                    setProductGroup(product,"Телемебель", "Телемебель");
                }
                if (type.contains("Музыкальные центры") || group.contains("Музыкальные центры")) {
                    setProductGroup(product,"Музыкальные центры", "Музыкальный центр");
                }
                if (type.contains("Синтезаторы и цифровые фортепьяно")) {
                    setProductGroup(product,"Синтезаторы", "Синтезатор");
                }
            }
        }
    }

    private void setProductGroup(Product product, String group, String single)
    {
        product.setProductGroup(group);
        product.setSingleType(single);
        productRepo.save(product);
        log.info(product.getProductID() + " " + product.getSingleType());
    }

    public void resolveOriginalPrice()
    {
        List<Product> products= productRepo.findAll();

        for (Product product : products)
        {
            if (product.getOriginalPrice().contains(","))
            {
                product.setPrice(Integer.parseInt(StringUtils.substringBefore(product.getOriginalPrice().replaceAll(" ", ""), ",")));
            }
            else
            {
                product.setPrice(Integer.parseInt(product.getOriginalPrice().replaceAll(" ", "")));
            }
            productRepo.save(product);
        }
    }




    //////////////////////////////
    private String removeAllSpacesAfterBrand(String trimGarbage, String productBrand) {
        String result = "";
        String temp = "";

        if (productBrand.contains(" ")) productBrand = productBrand.replaceAll(" ", "_");

        if (trimGarbage.contains(" ")) {
            temp = trimGarbage.substring(trimGarbage.indexOf(" "));
            temp = org.springframework.util.StringUtils.trimAllWhitespace(temp);
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

            int occurrence = org.springframework.util.StringUtils.countOccurrencesOf("result", " ");
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
                    if (!result.startsWith(brand.trim()))
                    {
                        result = result.substring(result.indexOf(" ")).trim();
                        if (!result.startsWith(brand.trim()))
                        {
                            result = result.substring(result.indexOf(" ")).trim();
                            if (!result.startsWith(brand.trim()))
                            {
                                result = result.substring(result.indexOf(" ")).trim();
                                if (!result.startsWith(brand.trim()))
                                {
                                    result = result.substring(result.indexOf(" ")).trim();
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
                types.add("");
                types.add("");
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

    public void resolveTvResol() {
        /*List<Product> products = productService.findProductsByRequestType("телевизоры");

        for (Product product : products)
        {
            String annotation = product.getAnnotation();

            if (product.get_tv_resol() == null || !product.get_tv_resol().equals("n/a"))
            {
                if (StringUtils.containsIgnoreCase(annotation, "HD")///В REGEX1, REGEX2
                        && !StringUtils.containsIgnoreCase(annotation, "Full HD")
                        && !StringUtils.containsIgnoreCase(annotation, "Ultra HD")
                        && !StringUtils.containsIgnoreCase(annotation, "UHD")
                        || StringUtils.containsIgnoreCase(annotation, "1366 x 768")
                        || StringUtils.containsIgnoreCase(annotation, "1366х768"))
                {
                    product.set_tv_resol("HD_Ready");
                }
                else if (StringUtils.containsIgnoreCase(annotation, "Full HD")///В REGEX1, REGEX2
                        || StringUtils.containsIgnoreCase(annotation, "1920 x 1080")
                        || StringUtils.containsIgnoreCase(annotation, "1920x1080"))
                {
                    product.set_tv_resol("Full_HD");
                }
                else if (StringUtils.containsIgnoreCase(annotation, "4K")///В REGEX1, REGEX2
                        || StringUtils.containsIgnoreCase(annotation, "Ultra HD")
                        || StringUtils.containsIgnoreCase(annotation, "3840 x 2160")
                        || StringUtils.containsIgnoreCase(annotation, "3840x2160")
                        || StringUtils.containsIgnoreCase(annotation, "UHD"))
                {
                    product.set_tv_resol("4K");
                }
                else product.set_tv_resol("n/a");
                productRepo.save(product);
            }

            if (product.get_tv_diag() == null || !product.get_tv_resol().equals("n/a"))///В REGEX1, REGEX2
            {
                if (annotation.contains("<br>") && annotation.contains("диагональ"))
                {
                    product.set_tv_diag(StringUtils.substringBetween(annotation, "диагональ", "<br>"));
                }
                else if (annotation.contains("Диагональ:") && annotation.contains("\""))
                {
                    product.set_tv_diag(StringUtils.substringBetween(annotation, "Диагональ:", "\""));
                }
                else if (annotation.contains("Диагональ экрана") && annotation.contains("\"."))
                {
                    product.set_tv_diag(StringUtils.substringBetween(annotation, "Диагональ экрана", "\"."));
                }
                else if (annotation.contains("Диагональ экрана") && annotation.contains("\" ."))
                {
                    product.set_tv_diag(StringUtils.substringBetween(annotation, "Диагональ экрана", "\" ."));
                }
                else if (annotation.contains("Диагональ") && annotation.contains("<br>"))
                {
                    product.set_tv_diag(StringUtils.substringBetween(annotation, "Диагональ", "<br>"));
                }
                else if (annotation.contains("диагональ") && annotation.contains("\""))
                {
                    product.set_tv_diag(StringUtils.substringBetween(annotation, "диагональ", "\""));
                }
                else if (StringUtils.substring(annotation, 0, 1).matches("[\\d ]")) {
                    product.set_tv_diag(StringUtils.substring(annotation, 0, 2));
                }
                else product.set_tv_diag("n/a");

                if (product.get_tv_diag() != null)
                {
                    String diag = product.get_tv_diag();
                    if (product.get_tv_diag().contains("\""))///В REGEX
                    {
                        product.set_tv_diag(StringUtils.substringBefore(diag, "\"").trim());
                    }
                    else if (product.get_tv_diag().contains("-"))
                    {
                        product.set_tv_diag(diag.replaceAll("-", "").trim());
                    }
                    else if (product.get_tv_diag().contains(":"))
                    {
                        product.set_tv_diag(diag.replaceAll(":", "").trim());
                    }
                    else if (product.get_tv_diag().contains(" экрана: "))
                    {
                        product.set_tv_diag(diag.replaceAll(" экрана: ", "").trim());
                    }
                }

                productRepo.save(product);
            }
        }*/
    }

    /*public void brandModel() {
        List<Product> products = productRepo.findAll();
        for (Product product : products)
        {
            *//*product.setGroupAndBrand(org.apache.commons.lang3.StringUtils.capitalize(product.getBrand().toLowerCase()).concat(" ").concat(product.getProductGroup()));*//*
            product.setGroupAndBrand(product.getProductGroup().concat(" ").concat(StringUtils.capitalize(product.getBrand().toLowerCase())));
            productRepo.save(product);
        }
        log.info("Capitalize complete!");
    }*/
}
