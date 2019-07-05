package expertshop.products;

import expertshop.domain.Product;
import expertshop.repos.ProductRepo;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Log
@Service
@AllArgsConstructor
public class ProductResolver {
    private final ProductRepo productRepo;

    public void resolveDuplicates(String request)
    {
        List<Product> products = productRepo.findProductsByProductGroupEqualsIgnoreCase(request);
        products.sort(Comparator.comparing(Product::getModelName));

        for (Product product : products)
        {
            String modelName = product.getModelName();
            if (modelName != null)
            {
                findDuplicates(products, modelName);
            }
        }
    }

    public void findDuplicates(List<Product> products, String modelName)
    {
        List<Product> duplicates = products.stream()
                .filter(product -> StringUtils.containsIgnoreCase(product.getOriginalName(), modelName) || StringUtils.containsIgnoreCase(product.getModelName(), modelName))
                .collect(Collectors.toList());

        if (duplicates.size() > 1)
        {
            duplicates.sort(Comparator.comparing(Product::getModelName));

            System.out.println();
            log.info("MODEL: " + modelName);
            log.info("Size: " + duplicates.size());
            duplicates.forEach(product -> log.info(product.getModelName() + "; " + product.getSupplier() + "; " + product.getPrice()));
        }
    }

    public void showModelName(String request)
    {
        List<Product> products = productRepo.findProductsByProductGroupEqualsIgnoreCase(request);
        products.sort(Comparator.comparing(Product::getModelName));

        products.forEach(product ->
        {
            if (product.getModelName() != null)
            {
                System.out.println();
                log.info(product.getSupplier());
                log.info(product.getOriginalName());
            }
        });
    }

    public void clearModelName(String request)
    {
        //List<Product> products = productRepo.findAllByModelNameNotNull();
        List<Product> products = productRepo.findProductsByProductGroupEqualsIgnoreCase(request);
        //products.sort(Comparator.comparing(Product::getModelName));

        ///тв, ресиверы, кронштейныТВ, телемебель, музыкальные_центры
        Pattern pattern = Pattern.compile("[а-яёА-ЯЁ()]+");
        String[] notBrands = {"ORION", "Electriclight"};

        products.forEach(product ->
        {
            if (product.getModelName() != null && !Arrays.asList(notBrands).contains(product.getOriginalBrand()))
            {
                String modelName = product.getModelName();
                Matcher match = pattern.matcher(modelName);
                if (match.find())
                {
                    System.out.println();
                    log.info(product.getSupplier() + ": " + product.getOriginalName());

                    modelName = modelName.replaceAll(pattern.pattern(), "");

                    ///REGEX
                    if (modelName.contains(" ")) modelName = modelName.replaceAll(" ", "");
                    if (modelName.contains(".")) modelName = modelName.replaceAll(".", "");

                    product.setModelName(modelName);

                    productRepo.save(product);

                    log.info(product.getModelName());
                }
            }
            else log.info("NO ModelName for " + product.getOriginalName());
        });
    }

    private boolean checkBrandAndGroup(Product product) {
        return !product.getOriginalBrand().isEmpty() && product.getProductGroup() != null;
    }

    public void resolveProductModel()
    {
        List<Product> products1 = productRepo.findAll();//findBySupplier("1RBT");
        products1.forEach(product ->
        {
            if (checkBrandAndGroup(product))
            {
                trimModelNameAfterBrand(product);
            }
        });

        /*
        List<Product> products2 = productRepo.findBySupplier("2RUS-BT");
        for (Product product : products2)
        {
            if (checkBrandAndGroup(product))
            {
                try {
                    String originalName = product.getOriginalName();
                    String brand = product.getOriginalBrand();

                    product.setModelName(StringUtils.substringBetween(originalName, brand, ",").trim());
                    productRepo.save(product);

                    System.out.println();
                    log.info(originalName);
                    log.info(product.getModelName());
                }
                catch (NullPointerException e) {
                    log.info("NULL at " + product.getOriginalName());
                    e.printStackTrace();
                }
            }
        }
        List<Product> products3 = productRepo.findBySupplier("3M-TRADE");
        products3.forEach(product ->
        {
            if (checkBrandAndGroup(product))
            {
                trimModelNameAfterBrand(product);
            }
        });
        */
    }

    private void trimModelNameAfterBrand(Product product)
    {
        try
        {
            String modelName = StringUtils.substringAfter(product.getOriginalName().toUpperCase(), product.getOriginalBrand().toUpperCase()).trim();

            product.setModelName(modelName);
            productRepo.save(product);

            System.out.println();
            log.info(product.getOriginalName());
            log.info(product.getModelName());
        }
        catch (NullPointerException e) {
            log.info("NULL at " + product.getOriginalName());
            e.printStackTrace();
        }
    }

    public void resolveProductGroups()
    {
        List<Product> products = productRepo.findAll();

        for (Product product : products)
        {
            try
            {
                if (product.getProductGroup() == null)
                {
                    /*АВТОТОВАРЫ*/
                    matchProduct("10.01, Коаксиальная акустика"									, "Автоакустика"							, "Автоакустика"						, "Автотовары"					, product);
                    matchProduct("10.02, Автомагнитолы"											, "Автомагнитолы"							, "Автомагнитола"						, "Автотовары"					, product);
                    matchProduct("15.10.04.03, Видеорегистраторы"									, "Видеорегистраторы"						, "Видеорегистратор"					, "Автотовары"					, product);
                    matchProduct("15.10.04.01"													    , "Радар детекторы"							, "Радар детектор"					, "Автотовары"					, product);

                    /*ИНСТРУМЕНТЫ ДЛЯ ДОМА*/
                    matchProduct("06.01.01, 06.01.02, 06.01.03, 06.01.0, 06.01.05"				    , "Шуруповерты/Дрели"						, "Шуруповерт/Дрель"					, "Инструменты для дома"		    , product);
                    matchProduct("06.02, Шлифовальные машины"										, "Шлифовальные машины"						, "Шлифовальная машина"				, "Инструменты для дома"		    , product);
                    matchProduct("06.06.08, Сварочные аппараты"									, "Сварочное оборудование"					, "Сварочный аппарат"					, "Инструменты для дома"		    , product);
                    matchProduct("06.01.06, Перфораторы"											, "Перфораторы"								, "Перфоратор"						, "Инструменты для дома"		    , product);
                    matchProduct("06.03"															, "Электропилы"								, "Электропила"						, "Инструменты для дома"		    , product);

                    /*ГАДЖЕТЫ*/
                    matchProduct("15.25.01, Умные спортивные часы"								    , "Умные часы"								, "Умные часы"						, "Гаджеты"						, product);
                    matchProduct("15.25.04"														, "Bluetooth колонки"						, "Bluetooth колонка"					, "Гаджеты"						, product);
                    matchProduct("15.25.02.02"													    , "VR системы"								, "VR система"						, "Гаджеты"						, product);

                    /*ПРИБОРЫ ПЕРСОНАЛЬНОГО УХОДА*/
                    matchProduct("05.10.01, весы напольные"										, "Напольные весы"							, "Напольные весы"					, "Приборы персонального ухода"	, product);
                    matchProduct("05.02.03, стайлеры"												, "Стайлеры"								, "Стайлер"							, "Приборы персонального ухода"	, product);
                    matchProduct("05.01.01, фены"													, "Фены"									, "Фен"								, "Приборы персонального ухода"	, product);
                    matchProduct("15.05.01.05, 05.06, 05.07, Бритвенные станки"					, "Станки для бритья"						, "Бритва"							, "Приборы персонального ухода"	, product);
                    matchProduct("05.02.02, Щипцы для волос"									    , "Щипцы для волос"							, "Бритвенный станок"					, "Приборы персонального ухода"	, product);
                    matchProduct("05.03.02, Триммер для бритья"									, "Триммеры для бритья"						, "Триммер"							, "Приборы персонального ухода"	, product);
                    matchProduct("05.07.01, Эпиляторы"											    , "Эпиляторы"								, "Эпиляторы"							, "Приборы персонального ухода"	, product);
                    matchProduct("05.08"															, "Массажеры"								, "Массажер"							, "Приборы персонального ухода"	, product);
                    matchProduct("05.11"															, "Маникюрные наборы"						, "Маникюрный набор"					, "Приборы персонального ухода"	, product);

                    /*КЛИМАТИЧЕСКАЯ ТЕХНИКА*/
                    matchProduct("04.01, Кондиционеры"											    , "Кондиционеры"							, "Кондиционер"						, "Климатическая техника"		    , product);
                    matchProduct("04.05, Водонагреватели"											, "Водонагреватели"							, "Водонагреватель"					, "Климатическая техника"		    , product);
                    matchProduct("04.04.01, Увлажнители"											, "Увлажнители воздуха"						, "Увлажнитель воздуха"				, "Климатическая техника"		    , product);
                    matchProduct("04.02, Обогревательные приборы"									, "Обогреватели"							, "Обогреватель"						, "Климатическая техника"		    , product);
                    matchProduct("04.03, Вентиляторы"												, "Вентиляторы"								, "Вентилятор"						, "Климатическая техника"		    , product);
                    matchProduct("04.04.03, Мойки воздуха"										    , "Очистители воздуха"						, "Очиститель воздуха"				, "Климатическая техника"		    , product);

                    /*КОМПЬЮТЕРЫ И ОРГТЕХНИКА*/
                    matchProduct("08.03"															, "Ноутбуки"								, "Ноутбук" 							, "Компьютеры и оргтехника"		, product);
                    matchProduct("08.05, МФУ"														, "Принтеры"								, "Принтер"							, "Компьютеры и оргтехника"		, product);
                    matchProduct("15.08.36, Клавиатура"											, "Клавиатуры"								, "Клавиатуры"						, "Компьютеры и оргтехника"		, product);
                    matchProduct("15.08.37, Мышь"													, "Мыши"									, "Мышь"								, "Компьютеры и оргтехника"		, product);
                    matchProduct("08.04, Мониторы"												    , "Мониторы"								, "Монитор"							, "Компьютеры и оргтехника"		, product);
                    matchProduct("15.08.18, Флеш"													, "Flash карты"								, "Flash карта"						, "Компьютеры и оргтехника"		, product);
                    matchProduct("15.02.04, Сетевые фильтры"										, "Сетевые фильтры"							,"Сетевой шнур"						, "Компьютеры и оргтехника"		, product);
                    matchProduct("15.08.29"														, "Роутеры и сетевое оборудование"			, "Роутер"							, "Компьютеры и оргтехника"		, product);
                    matchProduct("15.08.02.04"											 		    , "Внешние жесткие диски"					, "Внешний жесткий диск"				, "Компьютеры и оргтехника"		, product);
                    matchProduct("08.01.01"														, "Готовые ПК"								, "Готовый ПК"						, "Компьютеры и оргтехника"		, product);

                    /*ЦИФРОВЫЕ УСТРОЙСТВА*/
                    matchProduct("09.06.01, 09.06.02, 09.06.03, 09.06.04, Фотоаппараты"			, "Фотоаппараты"							, "Фотоаппарат"						, "Цифровые устройства"			, product);
                    matchProduct("09.01.02, Смартфоны"											    , "Смартфоны"								, "Смартфон"							, "Цифровые устройства"			, product);
                    matchProduct("09.01.01, Сотовые телефоны"										, "Сотовые телефоны"						, "Сотовыq телефон"					, "Цифровые устройства"			, product);
                    matchProduct("08.02, Планшеты"												    , "Планшеты"								, "Планшет"							, "Цифровые устройства"			, product);
                    matchProduct("15.08.33, 15.08.34, 15.16, Наушники"							    , "Наушники и гарнитуры"					, "Наушники"							, "Цифровые устройства"			, product);
                    matchProduct("15.09.03.02, Внешние аккумуляторы"								, "Внешние аккумуляторы"					, "Внешний аккумулятор"				, "Цифровые устройства"			, product);
                    matchProduct("15.09.03.01 Чехлы для телефонов"								    , "Чехлы для телефонов"						, "Чехол для телефона"				, "Цифровые устройства"			, product);
                    matchProduct("09.02"															, "Видеокамеры"								, "Видеокамера"						, "Цифровые устройства"			, product);
                    matchProduct("09.08"															, "Электронные книги"						, "Электронная книга"					, "Цифровые устройства"			, product);
                    matchProduct("15.09.05, 15.24.02.10, microSD"									, "Карты памяти microSD"					, "Карта памяти microSD"				, "Цифровые устройства"			, product);
                    matchProduct("09.07.02"													    , "MP3 плееры"								, "MP3 плеер"							, "Цифровые устройства" 		    , product);

                    /*ТЕХНИКА ДЛЯ ДОМА*/
                    matchProduct("03.01, Стиральные машины"										, "Стиральные машины"						, "Стиральная машина"					, "Техника для дома"			    , product);
                    matchProduct("03.02.01, Пылесосы"												, "Пылесосы"								, "Пылесос"							, "Техника для дома" 			    , product);
                    matchProduct("03.03.01, Утюги"												    , "Утюги"									, "Утюг"								, "Техника для дома"			    , product);
                    matchProduct("03.07, Швейные машины"											, "Швейные машины"							, "Швейная машина"					, "Техника для дома"			    , product);
                    matchProduct("03.03.02, 03.03.03, 03.03.04, Отпариватели"						, "Отпариватели"							, "Отпариватель"						, "Техника для дома"			    , product);

                    /*КУХОННАЯ ТЕХНИКА*/
                    matchProduct("01.01.02, Холодильники"											, "Холодильники"							, "Холодильник"						, "Кухонная техника"			    , product);
                    matchProduct("01.01.03, Морозильники"											, "Морозильники"							, "Морозильник"						, "Кухонная техника"			    , product);
                    matchProduct("01.02, Эл/плиты, электрические"									, "Электрические плиты"						, "Эликтрическая плита"				, "Кухонная техника"			    , product);
                    matchProduct("01.03, Газовая плита, газовые"									, "Газовые плиты"							, "Газовая плита"						, "Кухонная техника"			    , product);
                    matchProduct("01.05, Микроволновые печи, СВЧ печи"							    , "Микроволновые печи"						, "Микроволновая печь"				, "Кухонная техника"			    , product);
                    matchProduct("01.18, Чайники электрические, чайники"							, "Электрические чайники"					, "Электрический чайник"				, "Кухонная техника"			    , product);
                    matchProduct("01.19, Кофеварки"												, "Кофеварки"								, "Кофеварка"							, "Кухонная техника"			    , product);
                    matchProduct("01.12, Мультиварки"												, "Мультиварки"								, "Мультиварка"						, "Кухонная техника"			    , product);
                    matchProduct("01.08.03, 01.08.04, Блендеры"									, "Блендеры"								, "Блендер"							, "Кухонная техника"			    , product);
                    matchProduct("01.08.01, 01.08.02, Миксеры"									    , "Миксеры"									, "Миксер"							, "Кухонная техника"			    , product);
                    matchProduct("01.10.02, 01.10.01, Мясорубки"									, "Мясорубки"								, "Мясорубка"							, "Кухонная техника"			    , product);
                    matchProduct("01.15, Хлебопечи"												, "Хлебопечи"								, "Хлебопечь"							, "Кухонная техника"			    , product);
                    matchProduct("15.15.02.02, Кастрюли"											, "Кастрюли"								, "Кастрюля"							, "Кухонная техника"			    , product);
                    matchProduct("01.04, Посудомоечные машины"									    , "Посудомоечные машины"					, "Посудомоечная машина"				, "Кухонная техника"			    , product);

                    /*ВСТРАИВАЕМАЯ ТЕХНИКА*/
                    matchProduct("07.01.01, Встраиваемые духовые шкафы, Духовые шкафы"			    , "Встраиваемые духовые шкафы"				, "Встраиваемый духовой шкаф"			, "Встраиваемая техника"		    , product);
                    matchProduct("07.03.01 , 07.03.03, 07.04, Встраиваемые варочные поверхности"	, "Встраиваемые варочные панели"			, "Встраиваемая варочная панель"		, "Встраиваемая техника"		    , product);
                    matchProduct("07.05, Вытяжки встраиваемые"									    , "Встраиваемые вытяжки"					, "Встраиваемая вытяжка"				, "Встраиваемая техника"		    , product);
                    matchProduct("07.07, Холодильники встраиваемые"								, "Встраиваемые холодильники"				, "Встраиваемый холодильник"			, "Встраиваемая техника"		    , product);
                    matchProduct("07.08, свч печи встраиваемые"									, "Встраиваемые СВЧ"						, "Встраиваемая СВЧ"					, "Встраиваемая техника"		    , product);
                    matchProduct("07.06, Посудомоечные машины встраиваемые"						, "Встраиваемые посудомоечные машины"		, "Встраиваемая посудомоечная машина"	, "Встраиваемая техника"		    , product);

                    /*ТЕЛЕ-ВИДЕО-АУДИО*/
                    matchProduct("02.02, Телевизоры"												, "Телевизоры"								, "Телевизор"							, "Теле-Видео-Аудио"			    , product);
                    matchProduct("15.02.17.01 Спутниковое ТВ, Цифровая тв приставка, TV-тюнер"	    , "Ресиверы для тв"							, "Цифровой ресивер"					, "Теле-Видео-Аудио"			    , product);
                    matchProduct("15.08.24.03 Кабели питания, 15.02.08, 15.02.09, 15.02.10"		, "Кабели ТВ"								, "ТВ кабель"							, "Теле-Видео-Аудио"			    , product);
                    matchProduct("15.02.01, Кронштейны, Кронштейны для ТВ"						    , "Кронштейны ТВ"							, "ТВ кронштейн"						, "Теле-Видео-Аудио"			    , product);
                    matchProduct("15.02.17.02, Комнатные антенны, Антенны телевизионные"			, "Антенны ТВ"								, "ТВ антенна"						, "Теле-Видео-Аудио"			    , product);
                    matchProduct("10.10, Музыкальные центры"										, "Музыкальные центры"						, "Музыкальный центр"					, "Теле-Видео-Аудио"			    , product);
                    matchProduct("15.02.07, Телемебель"											, "Телемебель"								, "Телемебель"						, "Теле-Видео-Аудио"			    , product);
                    matchProduct("10.17.01, Синтезаторы и цифровые фортепьяно"					    , "Музыкальные инструменты"					, "Музыкальный инструмент"			, "Теле-Видео-Аудио"			    , product);
                }
            }
            catch (NullPointerException exp) {
                log.info("NullPointer at: " + product.getOriginalName());
                exp.printStackTrace();
            }
        }
    }

    private void matchProduct(String alias, String productGroup, String single, String productCategory, Product product)
    {
        String[] matches = alias.split(",");
        String type = product.getOriginalType();

        for (String match : matches)
        {
            if (StringUtils.startsWithIgnoreCase(type, match))
            {
                product.setProductGroup(productGroup);
                product.setSingleType(single);
                product.setProductCategory(productCategory);
                productRepo.save(product);
                log.info("ProductGroup: " + product.getProductGroup() + " for product " + product.getOriginalName());
                return;
            }
        }
    }

    public void resolveOriginalPrice() {
        List<Product> products= productRepo.findAll();
        for (Product product : products)
        {
            try
            {
                if (product.getOriginalPrice().contains(",")) {
                    product.setPrice(Integer.parseInt(StringUtils.substringBefore(product.getOriginalPrice().replaceAll(" ", ""), ",")));
                }
                else {
                    product.setPrice(Integer.parseInt(product.getOriginalPrice().replaceAll(" ", "")));
                }
                productRepo.save(product);
            }
            catch (Exception exp) {
                exp.printStackTrace();
            }
        }
    }

    public void resolveTypeBrand() {
        List<Product> products = productRepo.findAll();
        products.forEach(product ->
        {
            if (product.getSingleType() != null)
            {
                String typeBrand = product.getSingleType().concat(" ").concat(StringUtils.capitalize(product.getOriginalBrand().toLowerCase()));
                product.setTypeBrand(typeBrand);
                productRepo.save(product);
            }
        });
    }

    public void resolveProductType()
    {
        List<Product> products = productRepo.findAll();
        products.forEach(product ->
        {
            if (product.getProductType() == null)
            {
                String origType = product.getOriginalType();
                if (origType.contains(" ") && origType.contains("_"))
                {
                    product.setProductType(StringUtils.substringAfter(product.getOriginalType(), "_"));
                }
                else if (origType.contains("_"))
                {
                    product.setProductType(StringUtils.substringAfter(product.getOriginalType(), "_"));
                }
                else if (origType.contains(" "))
                {
                    product.setProductType(StringUtils.substringAfter(product.getOriginalType(), " "));
                }

                productRepo.save(product);
                log.info(product.getProductType());
            }
        });
    }
}

