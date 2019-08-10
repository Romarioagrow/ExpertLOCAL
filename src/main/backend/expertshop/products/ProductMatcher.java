package expertshop.products;

import expertshop.domain.BrandProduct;
import expertshop.domain.Product;
import expertshop.domain.ProductBase;
import expertshop.repos.BaseRepo;
import expertshop.repos.BrandRepo;
import expertshop.repos.ProductRepo;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Log
@Service
@AllArgsConstructor
public class ProductMatcher {
    private final BaseRepo      baseRepo;
    private final BrandRepo     brandRepo;
    private final ProductRepo   productRepo;
    private final ProductParser catalogParser;
    static int matchCounter = 0;

    public void updateProductDB(MultipartFile file)
    {
        try {

            ///ПРОВЕРИТЬ АДРЕСА КАРТИНОК И БАЗ
            catalogParser.parseProducts(file);
            matchProducts();
            resolveFinalPrice();
            resolveDuplicates();
            resolveAvailable();
            setPriceFromBrandsBase();

            log.info("Обновление БД завершено!");
        }
        catch (NullPointerException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private void resolveAvailable() {
        System.out.println();log.info("Проверка наличия товара...");

        AtomicInteger count = new AtomicInteger();
        List<Product> list = productRepo.findAllByProductGroupIsNotNull();
        list.forEach(product -> {
            product.setIsAvailable(null);
            productRepo.save(product);
        });

        list.forEach(product -> {
            if (product.getUpdate() != null && product.getUpdate().toString().equals(LocalDate.now().toString())) {
                product.setIsAvailable(true);
                productRepo.save(product);
                count.getAndIncrement();
            }
        });
        log.info("В наличии: " + count);
    }

    private void resolveFinalPrice() {
        AtomicInteger count = new AtomicInteger();
        System.out.println();log.info("Установка итоговой цены...");
        List<Product> list = productRepo.findAllByProductGroupNotNullAndFinalPriceIsNull();
        list.forEach(product ->
        {
            String brand = product.getOriginalBrand();
            int finalPrice, productBonus;

            if (StringUtils.equalsIgnoreCase(brand, "AMCV")         ||
                    StringUtils.equalsIgnoreCase(brand, "ARDIN")    ||
                    StringUtils.equalsIgnoreCase(brand, "BINATONE") ||
                    StringUtils.equalsIgnoreCase(brand, "DOFFLER")  ||
                    StringUtils.equalsIgnoreCase(brand, "LERAN")    ||
                    StringUtils.equalsIgnoreCase(brand, "SENTORE")
            ){
                BrandProduct brandProduct = brandRepo.findByProductID(product.getProductID());
                if (brandProduct != null) {
                    finalPrice = Integer.parseInt(brandProduct.getFinalPrice().replaceAll(" ", ""));
                    productBonus = matchBonus(finalPrice);
                    product.setFinalPrice(finalPrice);
                    product.setBonus(productBonus);
                    product.setBrandPrice(true);
                    count.getAndIncrement();
                    productRepo.save(product);
                }
                else
                {
                    finalPrice = roundPrice(product.getDefaultCoefficient(), (int) Double.parseDouble(product.getOriginalPrice().replaceAll(",", ".").replaceAll(" ", "")));
                    productBonus = matchBonus(finalPrice);
                    product.setFinalPrice(finalPrice);
                    product.setBonus(productBonus);
                    productRepo.save(product);
                }
            }
            else
            {
                finalPrice = roundPrice(product.getDefaultCoefficient(), (int) Double.parseDouble(product.getOriginalPrice().replaceAll(",", ".").replaceAll(" ", "")));
                productBonus = matchBonus(finalPrice);
                product.setFinalPrice(finalPrice);
                product.setBonus(productBonus);
                productRepo.save(product);
            }
        });
        productRepo.findAllByProductGroupIsNotNull().forEach(product -> {
            if (product.getFinalPrice() == null)
            {
                int origPrice = (int) Double.parseDouble(product.getOriginalPrice().replaceAll(" ", "").replaceAll(",", "."));
                int finalPrice = roundPrice(product.getDefaultCoefficient(), origPrice);
                int bonus = matchBonus(finalPrice);
                product.setFinalPrice(finalPrice);
                product.setBonus(bonus);
                productRepo.save(product);
            }
        });

        log.info("Установленно цен для брендов: " + count);
    }

    public void matchProducts()
    {
        List<Product> products = productRepo.findAllByProductGroupIsNull();
        System.out.println();log.info("Разметка товаров...");log.info("Товаров без разметки: " + products.size());
        for (Product product : products)
        {
            if (productValidToMatch(product))
            {
                /*АВТОТОВАРЫ*/
                matchProduct("10.01,Акустика"									                                , "Автоакустика"						,1.18	            , "Автоакустика"						, "Автотовары"					, product);
                matchProduct("10.02,Автомагнитолы"											                    , "Автомагнитолы"						,1.18	            , "Автомагнитола"						, "Автотовары"					, product);
                matchProduct("15.10.04.03,Видеорегистраторы"									                , "Видеорегистраторы"					,1.25	            , "Видеорегистратор"					, "Автотовары"					, product);
                matchProduct("15.10.04.01"													                    , "Радар детекторы"						,1.18	            , "Радар детектор"					, "Автотовары"					, product);
                matchProduct("Автоусилитель"													                , "Автоусилители"						,1.18	            , "Автоусилитель"					    , "Автотовары"					, product);

                /*ИНСТРУМЕНТЫ ДЛЯ ДОМА*/
                matchProduct("06.06.05_Строительные"														    , "Строительные пылесосы"				,1.16	            , "Строительный пылесосы"				, "Инструменты для дома"		    , product);
                matchProduct("06.06.02_Фены промышленные"														, "Промышленные фены"					,1.16	            , "Промышленный фен"					, "Инструменты для дома"		    , product);
                matchProduct("Сверла,15.06.03.04"														        , "Сверла"							    ,1.16	            , "Сверло"						    , "Инструменты для дома"		    , product);
                matchProduct("15.06.03.08"														                , "Буры"							    ,1.16	            , "Бур"						        , "Инструменты для дома"		    , product);
                matchProduct("06.06.07_Генераторы,Электрогенераторы"										    , "Генераторы"							,1.16	            , "Генератор"						    , "Инструменты для дома"		    , product);
                matchProduct("Бензиновые цепные пилы"										                    , "Бензопилы"							,1.16	            , "Бензопила"						    , "Инструменты для дома"		    , product);
                matchProduct("Паяльники"										                                , "Паяльники"							,1.16	            , "Паяльники"						    , "Инструменты для дома"		    , product);

                matchProduct("06.01.01,06.01.02,06.01.03,06.01.05,Шуруповерты,Дрели"				            , "Дрели-Шуруповерты"					,1.16	            , "Дрель-Шуруповерт"					, "Инструменты для дома"		    , product);
                matchProduct("06.02,Шлифовальные машины"										                , "Шлифовальные машины"					,1.16	            , "Шлифовальная машина"				, "Инструменты для дома"		    , product);
                matchProduct("06.06.08,Сварочные аппараты"									                    , "Сварочное оборудование"				,1.16	            , "Сварочный аппарат"					, "Инструменты для дома"		    , product);
                matchProduct("06.01.06,Перфораторы"											                , "Перфораторы"							,1.16	            , "Перфоратор"						, "Инструменты для дома"		    , product);
                matchProduct("06.03.01,Электрические цепные пилы,Дисковые пилы,06.10.08"						, "Электропилы"							,1.16	            , "Электропила"						, "Инструменты для дома"		    , product);
                matchProduct("06.03.02_Лобзики"														        , "Лобзики"							    ,1.16	            , "Электролобзик"						, "Инструменты для дома"		    , product);

                /*ПРИБОРЫ ПЕРСОНАЛЬНОГО УХОДА*/
                matchProduct("05.02.01., Плойки"										                        , "Плойки"						        ,1.22	            , "Плойки"					        , "Приборы персонального ухода"	, product);
                matchProduct("05.03.01.01, Машинки для стрижки"										        , "Машинки для стрижки"				    ,1.22	            , "Машинка для стрижки"				, "Приборы персонального ухода"	, product);
                matchProduct("15.05.01.05 Бритвенные станки"										            , "Бритвенные станки"					,1.22	            , "Бритвенный станок"					, "Приборы персонального ухода"	, product);
                matchProduct("05.09.01.0"										                                , "Зубные щетки"						,1.22	            , "Зубная щетка"					    , "Приборы персонального ухода"	, product);
                //15.05.01.05 Бритвенные станки!(1.5)
                matchProduct("05.10.01,Весы напольные"										                    , "Напольные весы"						,1.22	             , "Напольные весы"					, "Приборы персонального ухода"	, product);
                matchProduct("05.02.03,Наборы и стайлеры"												        , "Стайлеры"							,1.22	             , "Стайлер"							, "Приборы персонального ухода"	, product);
                matchProduct("05.01.01,Фен"													                , "Фены"								,1.22	             , "Фен"								, "Приборы персонального ухода"	, product);
                matchProduct("05.06.01.0,05.06.02_Сеточные бритвы,05.07.02_Бритвы для женщин,Бритвы роторные"	, "Бритвы"					            ,1.22	             , "Бритва"							, "Приборы персонального ухода"	, product);
                matchProduct("05.02.02,Щипцы для выпрямления"									                , "Щипцы для волос"					    ,1.22	             , "Бритвенный станок"				, "Приборы персонального ухода"	, product);
                matchProduct("05.03.02,Триммеры для лица "									                    , "Триммеры для бритья"				    ,1.22	             , "Триммер"							, "Приборы персонального ухода"	, product);
                matchProduct("05.07.01.02_Эпиляторы,Эпиляторы,05.07.03_Фотоэпиляторы"							, "Эпиляторы"							,1.22	             , "Эпиляторы"						, "Приборы персонального ухода"	, product);
                matchProduct("05.08.0"															                , "Массажеры"							,1.22	             , "Массажер"							, "Приборы персонального ухода"	, product);
                matchProduct("05.11,Маникюрные наборы"														    , "Маникюрные наборы"					,1.22               , "Маникюрный набор"					, "Приборы персонального ухода"	, product);
                matchProduct("Машинки для стрижки"															    , "Машинки для стрижки"				    ,1.22               , "Машинка для стрижки"				, "Приборы персонального ухода"	, product);

                /*КЛИМАТИЧЕСКАЯ ТЕХНИКА*/
                matchProduct("15.04.01.01"											                            , "Аксессуары для климатической техники",2	                , "Аксессуар для климатической техники", "Климатическая техника"		    , product);
                matchProduct("Термометры,Термометр"											                , "Термометры"						    ,1.15	            , "Термометр"						    , "Климатическая техника"		    , product);
                matchProduct("04.01,Кондиционеры"											                    , "Кондиционеры"						,1.15	            , "Кондиционер"						, "Климатическая техника"		    , product);
                matchProduct("04.05,Водонагреватели"											                , "Водонагреватели"					    ,1.20	            , "Водонагреватель"					, "Климатическая техника"		    , product);
                matchProduct("04.04.01,Увлажнители"											                , "Увлажнители воздуха"				    ,1.20	            , "Увлажнитель воздуха"				, "Климатическая техника"		    , product);
                matchProduct("04.02,Конвектор,Обогреватель,Тепловая пушка,Тепловентилятор"					    , "Обогреватели"						,1.20	            , "Обогреватель"						, "Климатическая техника"		    , product);
                matchProduct("04.03,Вентиляторы"												                , "Вентиляторы"						    ,1.20	            , "Вентилятор"						, "Климатическая техника"		    , product);
                matchProduct("04.04.03,Мойки воздуха"										                    , "Очистители воздуха"					,1.20	            , "Очиститель воздуха"				, "Климатическая техника"		    , product);

                /*КОМПЬЮТЕРЫ И ОРГТЕХНИКА*/
                //Моноблоки!
                matchProduct("15.08.01.01,15.08.01.03,Сумки и чехлы для ноутбуков"								, "Сумки для ноутбуков"					,2	                , "Сумка для ноутбука" 				, "Компьютеры и оргтехника"		, product);
                matchProduct("15.08.02.02,15.08.02.01,15.08.02.08"								                , "Аксессуары для ноутбуков"			,2	                , "Аксессуар для ноутбука" 			, "Компьютеры и оргтехника"		, product);
                matchProduct("15.08.02.12"								                                        , "Аккумуляторы для ноутбуков"			,2	                , "Аккумулятор для ноутбука" 			, "Компьютеры и оргтехника"		, product);
                matchProduct("15.08.04.02,15.08.04.05"								                            , "Картриджи струйные"					,2	                , "Картридж струйный" 				, "Компьютеры и оргтехника"		, product);
                matchProduct("15.08.03.01"								                                        , "Картриджи лазерные"					,2	                , "Картриджи лазерные" 				, "Компьютеры и оргтехника"		, product);
                matchProduct("15.24.02.19"								                                        , "Бумага для принтеров"				,2	                , "Бумага для принтеров" 				, "Компьютеры и оргтехника"		, product);
                matchProduct("15.08.07.01"								                                        , "Фотобумага"					        ,2	                , "Фотобумага" 						, "Компьютеры и оргтехника"		, product);

                matchProduct("15.08.13.01"															            , "Веб камеры"						    ,1.17	            , "Веб-камера" 						, "Компьютеры и оргтехника"		, product);
                matchProduct("08.03,Ноутбуки"															        , "Ноутбуки"							,1.17	            , "Ноутбук" 							, "Компьютеры и оргтехника"		, product);
                matchProduct("08.05,МФУ"														                , "Принтеры"							,1.17	            , "Принтер"							, "Компьютеры и оргтехника"		, product);
                matchProduct("15.08.36,Клавиатура"											                    , "Клавиатуры"							,1.30	            , "Клавиатура"						, "Компьютеры и оргтехника"		, product);
                matchProduct("15.08.37,Мышь"													                , "Мыши"								,1.30	            , "Мышь"								, "Компьютеры и оргтехника"		, product);
                matchProduct("08.04,Мониторы"												                    , "Мониторы"							,1.17	            , "Монитор"							, "Компьютеры и оргтехника"		, product);
                matchProduct("15.08.18,Флеш"													                , "Flash карты"						    ,1.40	            , "Flash карта"						, "Компьютеры и оргтехника"		, product);
                matchProduct("15.02.04,Сетевые фильтры"										                , "Сетевые фильтры"					    ,2	                , "Сетевой шнур"						, "Компьютеры и оргтехника"		, product);
                matchProduct("15.08.29"														                , "Роутеры и сетевое оборудование"		,1.20	            , "Роутер"							, "Компьютеры и оргтехника"		, product);
                matchProduct("15.08.02.04"											 		                    , "Внешние жесткие диски"				,1.18	            , "Внешний жесткий диск"				, "Компьютеры и оргтехника"		, product);
                matchProduct("08.01"														                    , "Готовые ПК"							,1.17	            , "Готовый ПК"						, "Компьютеры и оргтехника"		, product);

                /*ЦИФРОВЫЕ УСТРОЙСТВА*/
                //Планшеты Apple!
                matchProduct("15.24.02.,15.24.02.03,15.09.03.10,06.04.03"			                            , "Зарядные устройства"				    ,2			        , "Зарядное устройство"				, "Цифровые устройства"			, product);
                matchProduct("15.08.05.01,15.09.01.07,15.09.02.02"			                                    , "Сумки для техники"					,2			        , "Сумка для техники"					, "Цифровые устройства"			, product);
                matchProduct("09.06.01,09.06.02,09.06.03,09.06.04,Фотоаппараты"			                    , "Фотоаппараты"						,1.17				, "Фотоаппарат"						, "Цифровые устройства"			, product);
                matchProduct("09.01.02,Смартфоны"											                    , "Смартфоны"							,1.13				, "Смартфон"							, "Цифровые устройства"			, product);
                matchProduct("09.01.01,Сотовые телефоны"										                , "Сотовые телефоны"					,1.18				, "Сотовый телефон"					, "Цифровые устройства"			, product);
                matchProduct("08.02,Планшеты"												                    , "Планшеты"							,1.17				, "Планшет"							, "Цифровые устройства"			, product);
                matchProduct("15.08.33,15.08.34,15.16,Наушники"							                    , "Наушники и гарнитуры"				,1.30				, "Наушники"							, "Цифровые устройства"			, product);
                matchProduct("15.09.03.02,Внешние аккумуляторы"								                , "Внешние аккумуляторы"				,1.18				, "Внешний аккумулятор"				, "Цифровые устройства"			, product);
                matchProduct("15.09.03.01"								                                        , "Чехлы для телефонов"				    ,1.18				, "Чехол для телефона"				, "Цифровые устройства"			, product);
                matchProduct("09.02"															                , "Видеокамеры"						    ,1.13				, "Видеокамера"						, "Цифровые устройства"			, product);
                matchProduct("09.08"															                , "Электронные книги"					,1.17				, "Электронная книга"					, "Цифровые устройства"			, product);
                matchProduct("15.09.05,15.24.02.10,microSD"									                , "Карты памяти microSD"				,1.40				, "Карта памяти microSD"				, "Цифровые устройства"			, product);
                matchProduct("09.07.02"													                    , "MP3 плееры"							,1.17				, "MP3 плеер"							, "Цифровые устройства" 		    , product);
                matchProduct("09.07.01_"													                    , "Диктофоны"							,1.17				, "Диктофоны"							, "Цифровые устройства" 		    , product);
                matchProduct("15.02.29.01,Радиоприемники"													    , "Радиоприемники"						,1.17				, "Радиоприемники"					, "Цифровые устройства" 		    , product);
                matchProduct("09.05_Радиосвязь"													            , "Рации"						        ,1.17				, "Рации"							    , "Цифровые устройства" 		    , product);

                /*ГАДЖЕТЫ*/
                //15.25.04.12 B-бренды(1.2)!
                matchProduct("15.25.01,Умные спортивные часы"								                    , "Умные часы"							,1.15	            , "Умные часы"						, "Гаджеты"						, product);
                matchProduct("15.25.04,Портативная акустика"													, "Bluetooth колонки"					,1.15	            , "Bluetooth колонка"					, "Гаджеты"						, product);
                matchProduct("15.25.02.02"													                    , "VR системы"						    ,1.15	            , "VR система"						, "Гаджеты"						, product);
                matchProduct("15.25.01.02"													                    , "Фитнес браслеты"				        ,1.15	            , "Фитнес браслет"					, "Гаджеты"						, product);
                matchProduct("15.25.01.03"													                    , "Детские часы"					    ,1.15	            , "Детские часы"						, "Гаджеты"						, product);

                /*ТЕХНИКА ДЛЯ ДОМА*/
                matchProduct("Аксессуары к стиральным машинам,15.03.01.03,15.03.01.04,15.23.06.08,15.23.06.01"	, "Аксессуары к стиральным машинам"		,2				    , "Аксессуар к стиральным машинам"	, "Техника для дома"			    , product);
                matchProduct("Мешок-пылесборник,15.03.03.01"	                                                , "Пылесборники для пылесосов"		    ,2				    , "Пылесборник для пылесосов"			, "Техника для дома"			    , product);
                matchProduct("HEPA-фильтр,Моторный фильтр,15.03.03.02"	                                        , "Фильтры для пылесосов"		        ,2				    , "Фильтр для пылесосов"				, "Техника для дома"			    , product);
                matchProduct("Аксессуары для пылесосов,15.03.03.03,15.03.03.04"	                            , "Аксессуары для пылесосов"		    ,2				    , "Аксессуар для пылесосов"			, "Техника для дома"			    , product);
                matchProduct("15.03.07.03,Аксессуары для утюгов"	                                            , "Аксессуары для утюгов"		        ,2				    , "Аксессуар для утюгов"				, "Техника для дома"			    , product);
                matchProduct("15.03.09.01"	                                                                    , "Аксессуары для швейных машин"		,2				    , "ксессуар для швейных машин"		, "Техника для дома"			    , product);

                matchProduct("03.01.02,Центрифуги"						                                        , "Сушильные машины"		            ,1.13				, "Сушильная машина"					, "Техника для дома"			    , product);
                matchProduct("03.01.01,03.01.03, Ст/м"										                    , "Стиральные машины"		            ,1.13				, "Стиральная машина"					, "Техника для дома"			    , product);
                matchProduct("03.02.01,Пылесосы"												                , "Пылесосы"				            ,1.18				, "Пылесос"							, "Техника для дома" 			    , product);
                matchProduct("03.03.01,Утюги"												                    , "Утюги"					            ,1.20				, "Утюг"								, "Техника для дома"			    , product);
                matchProduct("03.07,03.08,Шв. машины,Оверлоки"									                , "Швейные машины"			            ,1.18				, "Швейная машина"					, "Техника для дома"			    , product);
                matchProduct("03.03.02,03.03.03,03.03.04,Отпариватели,Ручные отпариватели"					    , "Отпариватели"			            ,1.20				, "Отпариватель"						, "Техника для дома"			    , product);

                /*КУХОННАЯ ТЕХНИКА*/
                matchProduct("15.01.01.01,Аксессуары к холодильникам"											,"Аксессуары к холодильникам"	        ,2				    , "Аксессуар к холодильнику"			, "Кухонная техника"			    , product);
                matchProduct("15.01.06.01,Аксессуары для мультиварок"											,"Аксессуары для мультиварок"	        ,2				    , "Аксессуар для мультиварки"			, "Кухонная техника"			    , product);
                matchProduct("Аксессуары для мясорубок,15.01.07"											    ,"Аксессуары для мясорубок"	            ,2				    , "Аксессуар для мясорубки"			, "Кухонная техника"			    , product);
                matchProduct("Аксессуары для соковыжималок"											        ,"Аксессуары для соковыжималок"	        ,2				    , "Аксессуар для соковыжималок"		, "Кухонная техника"			    , product);
                matchProduct("15.01.02.04,15.23.01.,15.24.03.12,Сопла для перевода на сж. газ"				    ,"Аксессуары для плит"	                ,2				    , "Аксессуар для плит"				, "Кухонная техника"			    , product);
                matchProduct("15.23.02,15.24.03.11,Таблетки для посудомоечных машин,Аксессуары к встраиваемой технике"	,"Аксессуары для посудомоечных машин",2				, "Аксессуар для посудомоечных машин"	, "Кухонная техника"			    , product);
                matchProduct("15.01.05.01,Кронштейны для СВЧ,15.01.13.03,15.01.13.04,Крышки для СВЧ,Посуда для СВЧ"	,"Кронштейны для СВЧ"	        ,2				    , "Кронштейн для СВЧ"				    , "Кухонная техника"			    , product);
                matchProduct("15.01.13.03,15.01.13.04,Крышки для СВЧ, Посуда для СВЧ"		                    ,"Аксессуары для СВЧ"	                ,2				    , "Аксессуар для СВЧ"				    , "Кухонная техника"			    , product);
                matchProduct("15.01.20.,15.24.03."					                                            ,"Кухонные принадлежности"	            ,2				    , "Кухонная принадлежность"			, "Кухонная техника"			    , product);
                matchProduct("15.07.03.01"					                                                    ,"Фильтры для вытяжки"	                ,2				    , "Фильтр для вытяжки"				, "Кухонная техника"			    , product);
                matchProduct("15.07.03.02"					                                                    ,"Аксессуары для вытяжки"	            ,2				    , "Аксессуар для вытяжки"				, "Кухонная техника"			    , product);

                matchProduct("Тостеры"											                                , "Тостеры"	                            ,1.15				, "Тостер"						    , "Кухонная техника"			    , product);
                matchProduct("15.01.27.03"											                            , "Кулеры"	                            ,1.15				, "Кулер"						        , "Кухонная техника"			    , product);
                matchProduct("01.01.02,Холод."											                        , "Холодильники"	                    ,1.15				, "Холодильник"						, "Кухонная техника"			    , product);
                matchProduct("01.01.03.03,Мороз. верт."											            , "Морозильники"	                    ,1.15				, "Морозильник"						, "Кухонная техника"			    , product);
                matchProduct("01.01.03.04,Мороз. лари"											                , "Морозильные лари"	                ,1.15				, "Морозильный ларь"					, "Кухонная техника"			    , product);
                matchProduct("01.02.01,01.02.02,Плита эл."						                                , "Электрические плиты"		            ,1.17				, "Эликтрическая плита"				, "Кухонная техника"			    , product);
                matchProduct("01.02.03,Плитки эл. настольные"						                            , "Электрические плитки"	            ,1.17				, "Эликтрическая плитка"				, "Кухонная техника"			    , product);
                matchProduct("01.03.04,Плитки газ. настольные"						                            , "Газовые плитки"			            ,1.17			    , "Газовая плитка"				    , "Кухонная техника"			    , product);
                matchProduct("01.03.01,01.03.02,Плита газ."						                            , "Газовые плиты"			            ,1.17				, "Газовая плита"						, "Кухонная техника"			    , product);
                matchProduct("01.03.03,Комбинированная плита,Плита газ./эл"						            , "Комбинированные плиты"	            ,1.17				, "Комбинированная плита"				, "Кухонная техника"			    , product);
                matchProduct("01.05,Микр. печи"							                                    , "Микроволновые печи"		            ,1.18				, "Микроволновая печь"				, "Кухонная техника"			    , product);
                matchProduct("01.18,Чайники эл"							                                    , "Электрические чайники"	            ,1.20				, "Электрический чайник"				, "Кухонная техника"			    , product);
                matchProduct("01.19,Кофеварки, Кофемолки"												        , "Кофеварки"				            ,1.18				, "Кофеварка"							, "Кухонная техника"			    , product);
                matchProduct("01.12,Мультиварки"												                , "Мультиварки"				            ,1.20				, "Мультиварка"						, "Кухонная техника"			    , product);
                matchProduct("01.08.03,01.08.04,Блендеры"									                    , "Блендеры"				            ,1.20				, "Блендер"							, "Кухонная техника"			    , product);
                matchProduct("01.08.01,01.08.02,Миксеры"									                    , "Миксеры"					            ,1.20				, "Миксер"							, "Кухонная техника"			    , product);
                matchProduct("01.10.02,01.10.01,Мясорубки"									                    , "Мясорубки"				            ,1.20				, "Мясорубка"							, "Кухонная техника"			    , product);
                matchProduct("01.15,Хлебопечи"												                    , "Хлебопечи"				            ,1.15				, "Хлебопечь"							, "Кухонная техника"			    , product);
                matchProduct("15.15.02,Кастрюля"											                    , "Кастрюли"				            ,1.35				, "Кастрюля"							, "Кухонная техника"			    , product);
                matchProduct("01.04,Посудомоечная машина"									                    , "Посудомоечные машины"	            ,1.15				, "Посудомоечная машина"				, "Кухонная техника"			    , product);
                matchProduct("07.05.01,07.05.02,07.05.03,07.05.05,07.05.05,Вытяжка каминная,Вытяжка козырьковая,", "Вытяжки"					        ,1.15              , "Вытяжка"				            , "Кухонная техника"			    , product);
                matchProduct("01.09,Соковыжималка"				                                                , "Соковыжималки"			            ,1.18		        , "Соковыжималка"				        , "Кухонная техника"			    , product);
                matchProduct("01.12.01"				                                                        , "Фритюрницы"			                ,1.20		        , "Фритюрница"				        , "Кухонная техника"			    , product);
                matchProduct("01.12.02,Мультиварка"				                                            , "Мультиварки"			                ,1.20		        , "Мультиварка"				        , "Кухонная техника"			    , product);
                matchProduct("01.13,Пароварки"				                                                    , "Пароварки"			                ,1.20		        , "Пароварка"				            , "Кухонная техника"			    , product);
                matchProduct("01.14.01,Блинница,Вафельница"				                                    , "Электропечи"			                ,1.20		        , "Пароварка"				            , "Кухонная техника"			    , product);
                matchProduct("01.14.02,Аэрогриль"				                                                , "Аэрогрили"			                ,1.15		        , "Аэрогриль"				            , "Кухонная техника"			    , product);
                matchProduct("01.16.01,Йогуртница"				                                                , "Йогуртницы"			                ,1.15		        , "Йогуртница"				        , "Кухонная техника"			    , product);
                matchProduct("01.16.02,Электрические грили и барбекю"				                            , "Электрогрили"			            ,1.15		        , "Аэрогриль"				            , "Кухонная техника"			    , product);
                matchProduct("01.16.06,Электрошашлычницы"			                                            , "Шашлычницы"			                ,1.18		        , "Шашлычница"				        , "Кухонная техника"			    , product);
                matchProduct("01.17.02,Вафельницы"				                                                , "Вафельницы"			                ,1.18		        , "Вафельница"				        , "Кухонная техника"			    , product);
                matchProduct("01.17.03"				                                                        , "Мультипекари"			            ,1.18		        , "Мультипекарь"				        , "Кухонная техника"			    , product);
                matchProduct("01.17.04,Орешницы"				                                                , "Орешницы"			                ,1.18		        , "Орешница"				            , "Кухонная техника"			    , product);

                /*ВСТРАИВАЕМАЯ ТЕХНИКА*/
                matchProduct("07.01.01,Встраиваемый духовой шкаф"			                                    , "Встраиваемые духовые шкафы"			,1.15	            , "Встраиваемый духовой шкаф"			, "Встраиваемая техника"		    , product);
                matchProduct("07.03, 07.04,Встраиваемая варочная поверхность"	                                , "Встраиваемые варочные панели"		,1.15	            , "Встраиваемая варочная панель"		, "Встраиваемая техника"		    , product);
                matchProduct("07.05.04_Вытяжки встраиваемые,Вытяжка встраиваемая"								, "Встраиваемые вытяжки"				,1.15	            , "Встраиваемая вытяжка"				, "Встраиваемая техника"		    , product);
                matchProduct("07.07,Холодильники встраиваемые"								                    , "Встраиваемые холодильники"			,1.15	            , "Встраиваемый холодильник"			, "Встраиваемая техника"		    , product);
                matchProduct("07.08,свч печи встраиваемые"									                    , "Встраиваемые СВЧ"					,1.15	            , "Встраиваемая СВЧ"					, "Встраиваемая техника"		    , product);
                matchProduct("07.06,Посудомоечные машины встраиваемые"						                    , "Встраиваемые посудомоечные машины"	,1.15	            , "Встраиваемая посудомоечная машина"	, "Встраиваемая техника"		    , product);

                /*ТЕЛЕ-ВИДЕО-АУДИО*/
                matchProduct("Бытовые удлинители,15.24.01.12"												    , "Удлинители"				            ,2				    , "Удлинитель"						, "Теле-Видео-Аудио"			    , product);
                matchProduct("Батарейка,15.24.01."												                , "Батарейки"				            ,2				    , "Батарейка"						    , "Теле-Видео-Аудио"			    , product);
                matchProduct("Перезаряжаемые батарейки,15.24.01.,15.24.01."									, "Аккумуляторы"				        ,2				    , "Аккумулятор"						, "Теле-Видео-Аудио"			    , product);
                matchProduct("Зарядные устройства для перезаряжаемых"									        , "Зарядники батареек"				    ,2				    , "Зарядник батареек"					, "Теле-Видео-Аудио"			    , product);

                matchProduct("10.11.02"												                        , "Магнитолы"				            ,1.13				, "Магнитола"						    , "Теле-Видео-Аудио"			    , product);
                matchProduct("02.02,LED Телевизор"												                , "Телевизоры"				            ,1.13				, "Телевизор"							, "Теле-Видео-Аудио"			    , product);
                matchProduct("15.02.17.01,15.02.18.01,TV-тюнер"	                                            , "Ресиверы для тв"			            ,1.25				, "Цифровой ресивер"					, "Теле-Видео-Аудио"			    , product);
                matchProduct("15.08.24.03,15.02.08,15.02.09,15.02.10, Переходник"		                        , "Кабели ТВ"				            ,1.90				, "ТВ кабель"							, "Теле-Видео-Аудио"			    , product);
                matchProduct("15.02.01,Кронштейны для ТВ"						                                , "Кронштейны ТВ"			            ,2				    , "ТВ кронштейн"						, "Теле-Видео-Аудио"			    , product);
                matchProduct("15.02.17.02,Антенна телевизионная"			                                    , "Антенны ТВ"				            ,1.5				, "ТВ антенна"						, "Теле-Видео-Аудио"			    , product);
                matchProduct("10.10,Музыкальные центры"										                , "Музыкальные центры"		            ,1.15				, "Музыкальный центр"					, "Теле-Видео-Аудио"			    , product);
                matchProduct("15.02.07,Телемебель"											                    , "Телемебель"				            ,1.40				, "Телемебель"						, "Теле-Видео-Аудио"			    , product);
                matchProduct("10.17.01,Синтезаторы и цифровые фортепьяно"					                    , "Музыкальные инструменты"	            ,1.15				, "Музыкальный инструмент"			, "Теле-Видео-Аудио"			    , product);
            }
        }
        log.info("Размечено товаров: " + matchCounter);
        matchCounter = 0;
    }

    private void matchProduct(String alias, String productGroup, double coefficient, String single, String productCategory, Product product)
    {
        String[] matches = alias.split(",");
        String supp = product.getSupplier();

        for (String match : matches)
        {
            String annotation = "", modelName = "", fullName, groupBrand, productType, formAnno;
            try
            {
                match = StringUtils.trim(match);
                if (supp.equals("1RBT"))
                {
                    if (StringUtils.startsWithIgnoreCase(product.getOriginalType(), match))
                    {
                        String brand = product.getOriginalBrand();
                        /// Заданный параметры
                        product.setProductGroup(productGroup);
                        product.setSingleType(single);
                        product.setProductCategory(productCategory);

                        /// Вычисляемые параметры
                        modelName    = StringUtils.substringAfter(product.getOriginalName().toUpperCase(), brand.toUpperCase().replaceAll(" ", "").replaceAll("&", "")).trim();
                        fullName     = product.getSingleType().concat(" ").concat(StringUtils.capitalize(brand.toLowerCase())).concat(" ").concat(modelName);
                        productType  = resolveProductType(product.getOriginalType());
                        groupBrand   = single.concat(" ").concat(StringUtils.capitalize(brand.toLowerCase()));
                        formAnno     = formatAnnotation(product.getOriginalAnnotation());

                        product.setModelName(modelName);
                        product.setProductType(productType);
                        product.setGroupBrand(groupBrand);
                        product.setFullName(fullName);
                        product.setFormattedAnnotation(formAnno);
                        product.setDefaultCoefficient(coefficient);
                        productRepo.save(product);

                        resolveShortModel(product);
                        matchCounter++;
                        return;
                    }
                }

                if (supp.equals("2RUS-BT"))
                {
                    if (StringUtils.startsWithIgnoreCase(product.getOriginalType(), match) || StringUtils.startsWithIgnoreCase(product.getRName(), match))
                    {
                        product.setProductGroup(productGroup);
                        product.setSingleType(single);
                        product.setProductCategory(productCategory);

                        String originalBrand = product.getOriginalBrand().toUpperCase().replaceAll(" ", "").replaceAll("&", "");
                        String originalName = product.getRName().toUpperCase();

                        if (originalName.contains(", ") && originalName.contains(originalBrand))
                        {
                            annotation = StringUtils.substringAfter(originalName, ", ");
                            if ((StringUtils.substringAfter(originalName, originalBrand).contains(","))) modelName = StringUtils.substringBetween(originalName, originalBrand, ",").trim();
                            else modelName = StringUtils.substringAfter(originalName, originalBrand).trim();
                        }

                        originalName = StringUtils.capitalize(originalBrand.toLowerCase()).concat(" ").concat(modelName);//StringUtils.capitalize(originalBrand.concat(" ").concat(model).toLowerCase());
                        fullName = product.getSingleType().concat(" ").concat(StringUtils.capitalize(originalBrand.toLowerCase())).concat(" ").concat(modelName);
                        groupBrand = single.concat(" ").concat(StringUtils.capitalize(originalBrand.toLowerCase()));

                        product.setOriginalName(originalName);
                        product.setOriginalAnnotation(annotation);
                        product.setModelName(modelName);
                        product.setFullName(fullName);
                        product.setGroupBrand(groupBrand);
                        product.setProductType(product.getOriginalType()); /// resolveTypeName();
                        product.setDefaultCoefficient(coefficient);
                        productRepo.save(product);

                        resolveShortModel(product);
                        matchCounter++;
                        return;
                    }
                }
            }
            catch (NullPointerException e) {
                log.warning(e.getClass().getName());
            }
        }
    }

    public void resolveShortModel(Product product)
    {
        String modelName = product.getModelName();
        Pattern pattern = Pattern.compile("^[\\w\\W\\s?]+ [А-ЯA-Z\\W?]{3,20}$");
        Matcher matcher = pattern.matcher(modelName);

        String shortModel;
        String single = product.getSingleType();
        String brand  = product.getOriginalBrand().toLowerCase();

        if (matcher.matches())
        {
            shortModel = StringUtils.substringBeforeLast(modelName, " ").replaceAll(" ", "");
        }
        else shortModel = modelName.replaceAll(" ", "");

        shortModel = shortModel.replaceAll("-", "").replaceAll("_", "").replaceAll("_", "").replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("/", "").toLowerCase();

        String shortSearch = single.concat(brand).concat(shortModel).replaceAll(" ", "").toLowerCase();
        product.setShortSearchName(shortSearch);
        product.setShortModel(brand.concat(shortModel));
        productRepo.save(product);
    }

    public void resolveDuplicates() throws NullPointerException {
        System.out.println();
        log.info("Обработка дубликатов...");
        productRepo.findAllByProductGroupIsNotNull().forEach(product -> {product.setIsDuplicate(null);product.setHasDuplicates(null);productRepo.save(product);});

        List<Product> products = productRepo.findBySupplierAndProductGroupIsNotNull("1RBT");

        AtomicInteger count = new AtomicInteger();
        products.forEach(product ->
        {
            List<Product> duplicates = productRepo.findByShortModelEquals(product.getShortModel());
            if (duplicates.size() > 1)
            {
                duplicates = duplicates.stream().filter(duplicate -> duplicate.getSupplier().equals("2RUS-BT")).collect(Collectors.toList());
                if (duplicates.size() >= 1)
                {
                    duplicates.sort(Comparator.comparing(Product::getFinalPrice));
                    /*System.out.println();
                    log.info("Дубликаты для: " + product.getFullName() + "; цена: " + product.getFinalPrice());
                    duplicates.forEach(duplicate -> log.info(duplicate.getOriginalName() + " " + duplicate.getFinalPrice()));*/
                    count.getAndIncrement();

                    Product productOriginal = duplicates.stream().findFirst().get();
                    if (product.getFinalPrice() > productOriginal.getFinalPrice())
                    {
                        productOriginal.setFormattedAnnotation(product.getFormattedAnnotation());
                        productOriginal.setOriginalPic(product.getOriginalPic());
                        productOriginal.setHasDuplicates(true);
                        product.setIsDuplicate(true);
                        productRepo.save(product);
                        productRepo.save(productOriginal);

                        duplicates.stream().skip(1).forEach(duplicate ->
                        {
                            duplicate.setIsDuplicate(true);
                            productRepo.save(duplicate);
                        });
                        ///log.info("Оригинал: " + productOriginal.getSupplier() + " " + productOriginal.getFullName()+ " " + productOriginal.getFinalPrice());
                    }
                    else
                    {
                        product.setHasDuplicates(true);
                        duplicates.forEach(duplicate ->
                        {
                            duplicate.setIsDuplicate(true);
                            productRepo.save(duplicate);
                        });
                        ///log.info("Оригинал: " + product.getSupplier() + " " + product.getFullName()+ " " + product.getFinalPrice());
                    }
                }
            }
        });
        log.info("Товаров с дубликатами: " + count.toString());
    }

    /*public void createShortSearchName() {
        productRepo.findAllByModelNameNotNull().forEach(product -> {
            product.setShortSearchName(null);
            productRepo.save(product);
        });

        List<Product> products = productRepo.findAllByModelNameNotNull();
        products.forEach(product ->
        {
            if (product.getShortSearchName() == null)
            {
                String single = product.getSingleType().replaceAll(" ", "").toLowerCase();
                String brand  = product.getOriginalBrand().replaceAll(" ", "").toLowerCase();
                String model  = product.getModelName().replaceAll(" ", "").toLowerCase();
                String searchName = single.concat(brand).concat(model).replaceAll("-", "");
                product.setShortSearchName(searchName);
                productRepo.save(product);
                log.info(product.getShortSearchName());
            }
        });
    }*/

    public int roundPrice(double coefficient, int price) {
        int finalPrice = (int) (price * coefficient);
        String val = String.valueOf(finalPrice);

        if (finalPrice > 0 && finalPrice <= 10) {
            return 10;
        }
        else if (finalPrice > 10 && finalPrice < 1000) {
            val = val.substring(0, val.length()-1).concat("9");
            return Integer.parseInt(val);
        }
        else if (finalPrice > 1000) {
            val = val.substring(0, val.length()-2).concat("90");
            return Integer.parseInt(val);
        }
        else return finalPrice;
    }
    public Integer matchBonus(int price) {
        int bonus = price * 3 / 100;
        String val = String.valueOf(bonus);

        if (bonus > 0 && bonus <= 10) {
            return 10;
        }
        else {
            val = val.substring(0, val.length()-1).concat("0");
            return Integer.parseInt(val);
        }
    }

    private boolean productValidToMatch(Product product) {
        return product.getProductGroup() == null && !product.getOriginalBrand().isEmpty() && StringUtils.containsIgnoreCase(product.getOriginalName().replaceAll(" ", "").replaceAll("&", ""), product.getOriginalBrand().replaceAll(" ", "")); /// В ЗАПРОС К БД
    }

    public String formatAnnotation(String anno) {
        if (!anno.isEmpty() && anno.contains(";")) {
            return anno.replaceAll(";", "<br>");
        }
        return null;
    }

    public String resolveProductType(String originalType)
    {
        if (originalType.contains(" ") && originalType.contains("_")) {
            return StringUtils.substringAfter(originalType, "_");
        }
        else if (originalType.contains("_")) {
            return StringUtils.substringAfter(originalType, "_");
        }
        else if (originalType.contains(" ")) {
            return StringUtils.substringAfter(originalType, " ");
        }
        return originalType;
    }

    public void trimBigBase() {
        List<ProductBase> bases = baseRepo.findAll();
        log.info(bases.size() + "");
        bases.forEach(productBase ->
        {
            String model = StringUtils.substringAfter(productBase.getFullName(), productBase.getBrand()).trim().toLowerCase();
            String brand = productBase.getBrand().toLowerCase();
            String shortModel = brand.concat(model).replaceAll("\\W", "");
            productBase.setShortModel(shortModel);
            baseRepo.save(productBase);
            log.info(productBase.getShortModel());
        });
    }

    public void setPriceFromBrandsBase() {
        AtomicInteger countTotalBrands = new AtomicInteger();
        AtomicInteger countMatch = new AtomicInteger();
        List<Product> list = productRepo.findAllByProductGroupIsNotNull();
        list.forEach(product ->
        {
            try {
                if (product.getPriceMod() == null)
                {
                    String brand = product.getOriginalBrand();
                    if (StringUtils.equalsIgnoreCase(brand, "AMCV")         ||
                            StringUtils.equalsIgnoreCase(brand, "ARDIN")    ||
                            StringUtils.equalsIgnoreCase(brand, "BINATONE") ||
                            StringUtils.equalsIgnoreCase(brand, "DOFFLER")  ||
                            StringUtils.equalsIgnoreCase(brand, "LERAN")    ||
                            StringUtils.equalsIgnoreCase(brand, "SENTORE")
                    ){
                        countTotalBrands.getAndIncrement();
                        BrandProduct brandProduct = brandRepo.findByProductID(product.getProductID());
                        if (brandProduct != null)
                        {
                            countMatch.getAndIncrement();
                            int oldPrice = product.getFinalPrice();
                            product.setFinalPrice(Integer.parseInt(brandProduct.getFinalPrice().replaceAll(" ", "")));
                            if (product.getOriginalPic() == null) {
                                product.setOriginalPic(brandProduct.getOriginalPrice());
                            }
                            product.setBrandPrice(true);
                            productRepo.save(product);
                        }
                        else
                        {
                            brandProduct = brandRepo.findByShortModel(product.getShortModel());
                            if (brandProduct != null)
                            {
                                countMatch.getAndIncrement();
                                int oldPrice = product.getFinalPrice();
                                product.setFinalPrice(Integer.parseInt(brandProduct.getFinalPrice().replaceAll(" ", "")));
                                if (product.getOriginalPic() == null) {
                                    product.setOriginalPic(brandProduct.getOriginalPrice());
                                }
                                product.setBrandPrice(true);
                                productRepo.save(product);
                            }
                        }
                    }
                }
            }
            catch (NullPointerException e) {
                e.printStackTrace();
            }
        });
    }

    public void uploadProductPic(MultipartFile file, String productID) {
        log.info(productID);

        Product product = productRepo.findByProductID(productID);

        if (!file.isEmpty()) {
            try {
                log.info("file is ok " + file.getOriginalFilename());
                File file1 = new File("D:\\OneDrive\\IT\\Projects\\expertshopLOCAL\\src\\main\\frontend\\static\\img\\products\\" + file.getOriginalFilename());

                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(file1.getAbsoluteFile().toString()));
                stream.write(file.getBytes());
                stream.close();

                String localPath = StringUtils.substringAfterLast(file1.getPath(), "\\");
                String localPic  = "/../img/products/".concat(localPath);

                log.info("check " + localPic);
                product.setOriginalPic(localPic);
                productRepo.save(product);

                log.info(product.getOriginalPic());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else log.warning("File empty");
    }

    public void xxx() {

        ///ДЛЯ RUSBT
        productRepo.findBySupplier("2RUS-BT").forEach(product -> {
            String group = StringUtils.substringAfter(product.getOriginalCategory(), ";");
            product.setOriginalGroup(group);
            productRepo.save(product);
        });

        /*productRepo.findAllByProductGroupIsNotNull().forEach(product -> {
            String anno = product.getOriginalAnnotation();
            if (anno != null && !anno.endsWith(";")) product.setOriginalAnnotation(product.getOriginalAnnotation().concat(";"));
            productRepo.save(product);
        });*/

        /*List<Product> list = productRepo.findAllByProductGroupIsNotNull();
        list.forEach(product -> {
            String brand = product.getOriginalBrand();
            if (product.getBrandPrice() == null &&
                    (StringUtils.equalsIgnoreCase(brand, "AMCV")     ||
                            StringUtils.equalsIgnoreCase(brand, "ARDIN")    ||
                            StringUtils.equalsIgnoreCase(brand, "BINATONE") ||
                            StringUtils.equalsIgnoreCase(brand, "DOFFLER")  ||
                            StringUtils.equalsIgnoreCase(brand, "LERAN")    ||
                            StringUtils.equalsIgnoreCase(brand, "SENTORE"))
            ){
                log.info(product.getProductID() + "; " + product.getOriginalName());
            }
        });
*/
        /*int count;
        List<Product> products = productRepo.findAllByModelNameNotNull();
        count = (int) products.stream().filter(product -> product.getOriginalPic() != null).count();
        log.info(count + " из " + products.size());*/

        /*productRepo.findAllByModelNameNotNull().forEach(product -> {
            product.setShortSearchName(null);
            productRepo.save(product);
        });

        List<Product> products = productRepo.findAllByModelNameNotNull();
        products.forEach(product ->
        {
            if (product.getShortSearchName() == null)
            {
                String single = product.getSingleType().replaceAll(" ", "").toLowerCase();
                String brand  = product.getOriginalBrand().replaceAll(" ", "").toLowerCase();
                String model  = product.getModelName().replaceAll(" ", "").toLowerCase();
                String searchName = single.concat(brand).concat(model).replaceAll("-", "");
                product.setShortSearchName(searchName);
                productRepo.save(product);
                log.info(product.getShortSearchName());
            }
        });*/

        /*List<Product> list = productRepo.findAllByModelNameNotNull();
        list.forEach(product -> {
            String modelName = product.getModelName();
            Pattern pattern = Pattern.compile("^[\\w\\W\\s?]+ [А-ЯA-Z\\W?]{3,20}$");
            Matcher matcher = pattern.matcher(modelName);

            String shortModel;
            String single = product.getSingleType();
            String brand = product.getOriginalBrand().toLowerCase();

            if (matcher.matches())
            {
                shortModel = StringUtils.substringBeforeLast(modelName, " ").replaceAll("\\W", "");
            }
            else shortModel = modelName.replaceAll("\\W", "");

            String shortSearch = single.concat(brand).concat(shortModel)
                    .replaceAll(" ", "")
                    .replaceAll("-", "")
                    .replaceAll("_", "")
                    .replaceAll("\\(", "")
                    .replaceAll("\\)", "").toLowerCase();
            product.setShortSearchName(shortSearch);
            product.setShortModel(brand.concat(shortModel));
            //productRepo.save(product);

            System.out.println();
            log.info(product.getOriginalName());
            log.info(product.getSingleType());
            log.info(brand);
            log.info(shortModel);
            log.info("Search " + shortSearch);
            product.setShortSearchName(shortSearch);
            product.setShortModel(brand.concat(shortModel));
            productRepo.save(product);
        });*/
        /*List<Product> products = productRepo.findAllByModelNameNotNull();
        products.forEach(product -> {
            String anno = product.getOriginalAnnotation();
            if (!anno.endsWith(";")) {
                anno = anno.concat(";");
                product.setOriginalAnnotation(anno);
                //productRepo.save(product);
            }
            log.info(product.getSupplier() + " " + product.getOriginalAnnotation());
        });*/

        /*List<Product> list = productRepo.findAllByModelNameNotNull();
        list.forEach(product -> {
            String modelName = product.getModelName();
            Pattern pattern = Pattern.compile("^[\\w\\W\\s?]+ [А-ЯA-Z\\W?]{3,20}$"); /// WHITE LIST
            Matcher matcher = pattern.matcher(modelName);

            String shortModel;
            String brand = product.getOriginalBrand().toLowerCase();

            if (matcher.matches())
            {
                log.info(modelName);
            }
            else log.info("lol");
        });*/

        /*
        List<Product> products = productRepo.findAllByModelNameNotNull();
        products.forEach(product -> {
            if (product.getSupplier().startsWith("2")) {
                product.setPics(null);
                productRepo.save(product);
            }
        });*/

        /*int count = (int) products.stream().filter(product -> product.getOriginalPic() != null).count();
        log.info(count + " from " + products.size());*/

        /*products.forEach(product -> {
            if (product.getFormattedAnnotation() != null && (product.getFormattedAnnotation().isEmpty() || product.getFormattedAnnotation().equals("<br>"))) {
                product.setFormattedAnnotation(null);
                productRepo.save(product);
            }
        });*/
        /*productRepo.findAll().forEach(product -> {
            String[] cutters = {" ","(",",  ",", "," - "," , ","-"};
            for (String cut : cutters) {
                String anno = product.getOriginalAnnotation();
                if (anno != null) {
                    //System.out.println();
                    //log.info(anno);
                    if (!anno.isEmpty() && anno.startsWith(cut)) {
                        String newAnno = StringUtils.substringAfter(anno, cut);
                        product.setOriginalAnnotation(newAnno);
                        productRepo.save(product);
                        log.info(product.getOriginalAnnotation());
                    }
                }
            }
        });*/
    }

}
    /*public void resolveDuplicates(String request)
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
    }*/



    /*public void findDuplicates(List<Product> products, String modelName)
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
    }*/

    /*public void showModelName(String request)
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
    }*/



    /*private boolean checkBrandAndGroup(Product product) {
        return !product.getOriginalBrand().isEmpty() && product.getProductGroup() != null;
    }

    public void resolveProductModel()
    {
        List<Product> products1 = productRepo.findAll();//findBySupplier("1RBT");
        products1.forEach(product ->
        {
            if (product.getModelName() == null)
            {
                if (checkBrandAndGroup(product)) trimModelNameAfterBrand(product);
            }

        });

        *//*
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
        *//*
    }*/

    /*private void trimModelNameAfterBrand(Product product)
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
    }*/


    /*public void resolveOriginalPrice() {
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
    }*/

    /*public void resolveTypeBrand() {
        List<Product> products = productRepo.findAll();
        products.forEach(product ->
        {
            if (product.getSingleType() != null)
            {
                String typeBrand = product.getSingleType().concat(" ").concat(StringUtils.capitalize(product.getOriginalBrand().toLowerCase()));
                product.setGroupBrand(typeBrand);
                productRepo.save(product);
            }
        });
    }*/



