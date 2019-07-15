<#import "../parts/template.ftl" as t>
<#include "../parts/security.ftl">

<@t.template>
    <div class="container" style="margin-top: 5rem">
        <div class="row">
            <div class="col">
                <h1>База товаров</h1>

                <form method="post" enctype="multipart/form-data">
                    <div class="form-group">
                        <div class="custom-file">
                            <input type="file" name="file" id="customFile">
                            <label class="custom-file-label" for="customFile">Choose</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary">Post</button>
                    </div>
                </form>

                <form method="get" action="/supplier/pics">
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary">Check pics</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</@t.template>
<#--<div class="row mb-3">
    <div class="col">
        <h3><a href="/categories/electronics">Электроника</a></h3>
        <ul>
            <li><a href="/products/tv"><strong>Телевизоры</strong></a></li>
            <li><a href="/subcats/multimedia"><strong>Мультимедийные системы</strong></a></li>
            <li><a href="/subcats/sputnik"><strong>Спутниковая техника</strong></a></li>
        </ul>
    </div>
    <div class="col">
        <h3><a href="/categories/kitchen_equipment">Кухонная техника</a></h3>
        <ul>
            <li><a href="/subcats/kitchen_big"><strong>Крупная техника</strong></a></li>
            <li><a href="/subcats/kitchen_builtin"><strong>Встраиваемая техника</strong></a></li>
            <li><a href="/subcats/cooking_devices"><strong>Техника для приготовления пищи</strong></a></li>
            <li><a href="/subcats/kitchen_small"><strong>Маленькая техника</strong></a></li>
            <li><a href="/subcats/kitchen_accessory"><strong>Кухонные аксессуары</strong></a></li>
        </ul>
    </div>
</div>
<div class="row mb-3">
    <div class="col">
        <h3><a href="/categories/home_equipment">Техника для дома</a></h3>
        <ul>
            <li><a href="/subcats/home_washing"><strong>Для стирки</strong></a></li>
            <li><a href="/subcats/home_cleaning"><strong>Для уборки</strong></a></li>
            <li><a href="/subcats/home_clothes"><strong>Для ухода за одеждой</strong></a></li>
            <li><a href="/subcats/home_accessory"><strong>Аксессуары для дома</strong></a></li>
        </ul>
    </div>
    <div class="col">
        <h3><a href="/categories/climate_control">Климатическая техника</a></h3>
        <ul>
            <li><a href="/products/conditioners"><strong>Кондиционеры</strong></a></li>
            <li><a href="/products/water_heaters"><strong>Водонагреватели</strong></a></li>
            <li><a href="/products/gas_heaters"><strong>Газовые колонки</strong></a></li>
            <li><a href="/products/electric_heaters"><strong>Обогреватели</strong></a></li>
            <li><a href="/products/ventilators"><strong>Вентиляторы</strong></a></li>
        </ul>
    </div>
</div>
<div class="row mb-3">
    <div class="col">
        <h3><a href="/categories/computers">Компьютеры и офисная техника</a></h3>
        <ul>
            <li><a href="/subcats/computers_pc"><strong>ПК и переферия</strong></a></li>
            <li><a href="/notebooks"><strong>Ноутбуки</strong></a></li>
            <li><a href="/subcats/computers_office"><strong>Офисная техника</strong></a></li>
            <li><a href="/subcats/computer_parts"><strong>Компьютерные комплектующие</strong></a></li>
            <li><a href="/subcats/computers_accessories"><strong>Аксессуары</strong></a></li>
        </ul>
    </div>
    <div class="col">
        <h3><a href="/categories/portable">Портативная техника</a></h3>
        <ul>
            <li><a href="/products/photo"><strong>Фотоапараты</strong></a></li>
            <li><a href="/products/video"><strong>Камеры</strong></a></li>
            <li><a href="/products/radio"><strong>Рации</strong></a></li>
            <li><a href="/products/mobile_phones"><strong>Сотовые телефоны</strong></a></li>
        </ul>
    </div>
</div>
<div class="row mb-3">
    <div class="col">
        <h3><a href="/categories/smart">Smart техника</a></h3>
        <ul>
            <li class="list-head"><a href="/products/smartphones"><strong>Смартфоны</strong></a></li>
            <li class="list-head"><a href="/products/tablets"><strong>Планшеты</strong></a></li>
            <li class="list-head"><a href="/products/smart_headers"><strong>Умные колонки</strong></a></li>
            <li class="list-head"><a href="/products/bluetooth"><strong>Bluetooth устройства</strong></a></li>
        </ul>
    </div>
</div>&ndash;&gt;-->


<#--<div class="catalogue-info  catalogue-main__catalogue">--><#--
    <a class="image-link  catalogue-info__image" href="/cat/tovary_dlya_detey/">
        <span class="image image_format_index image_lazy">
<#--
            <img class="image__adaptive" data-original="//cdn.rbt.ru/images/gen/catalogue/image/2/41/179_r8372.jpg" src="//cdn.rbt.ru/images/gen/catalogue/image/2/41/179_r8372.jpg" alt="Товары для детей" style="display: inline;" width="180" height="180"></span></a><div class="catalogue-info__column"><a class="link link_size_21 link_underline_disabled catalogue-info__header" href="/cat/tovary_dlya_detey/"><span class="link__text">Товары для детей</span></a><div class="catalogue-info__subcategories"><a class="catalogue-info__subcategory" href="/cat/tovary_dlya_detey/detskie_igrushki/"><span class="link__text">Детские игрушки</span></a><a class="catalogue-info__subcategory" href="/cat/tovary_dlya_detey/razvivayuschie_igry/"><span class="link__text">Развивающие игры</span></a><a class="catalogue-info__subcategory" href="/cat/tovary_dlya_detey/hobbi_i_uvlechenie/"><span class="link__text">Хобби и увлечение</span></a><a class="link link_color_grey-blue link_underline_disabled catalogue-info__subcategory_last catalogue-info__subcategory" href="/cat/tovary_dlya_detey/tovary_dlya_novorozhdennyh/"><span class="link__text">Товары для новорождённых</span></a></div><a class="link button_theme_flat-index catalogue-info__button button" href="/cat/tovary_dlya_detey/"><span class="link__text">Все подкатегории</span></a></div>&ndash;&gt;</div><div class="catalogue-info catalogue-main__catalogue_shifted catalogue-main__catalogue"><a class="image-link  catalogue-info__image" href="/cat/aktivnyy_otdyh/"><span class="image image_format_index image_lazy"><img class="image__adaptive" data-original="//cdn.rbt.ru/images/gen/catalogue/image/3/41/201_r4274.jpg" src="//cdn.rbt.ru/images/gen/catalogue/image/3/41/201_r4274.jpg" alt="Активный отдых" style="display: inline;" width="180" height="180"></span></a><div class="catalogue-info__column"><a class="link link_size_21 link_underline_disabled catalogue-info__header" href="/cat/aktivnyy_otdyh/"><span class="link__text">Активный отдых</span></a><div class="catalogue-info__subcategories"><a class="catalogue-info__subcategory" href="/cat/aktivnyy_otdyh/naduvnaya_mebel/"><span class="link__text">Надувная мебель</span></a><a class="catalogue-info__subcategory" href="/cat/aktivnyy_otdyh/binokli_i_podzornye_truby/"><span class="link__text">Бинокли и подзорные трубы</span></a><a class="catalogue-info__subcategory" href="/cat/aktivnyy_otdyh/teleskopy/"><span class="link__text">Телескопы</span></a><a class="catalogue-info__subcategory" href="/cat/aktivnyy_otdyh/mangaly_i_barbeku/"><span class="link__text">Мангалы и барбекю</span></a><a class="catalogue-info__subcategory" href="/cat/aktivnyy_otdyh/fonari/"><span class="link__text">Фонари</span></a><a class="catalogue-info__subcategory" href="/cat/aktivnyy_otdyh/korziny_i_nabory_dlya_piknika/"><span class="link__text">Корзины и наборы для пикника</span></a><a class="link link_color_grey-blue link_underline_disabled catalogue-info__subcategory_last catalogue-info__subcategory" href="/cat/aktivnyy_otdyh/avtoholodilniki/"><span class="link__text">Автохолодильники</span></a></div><a class="link button_theme_flat-index catalogue-info__button button" href="/cat/aktivnyy_otdyh/"><span class="link__text">Все подкатегории</span></a></div></div><div class="catalogue-info  catalogue-main__catalogue"><a class="image-link  catalogue-info__image" href="/cat/sadovaya_tehnika/"><span class="image image_format_index image_lazy"><img class="image__adaptive" data-original="//cdn.rbt.ru/images/gen/catalogue/image/2/41/198_r9858.jpg" src="//cdn.rbt.ru/images/gen/catalogue/image/2/41/198_r9858.jpg" alt="Садовая техника" style="display: inline;" width="180" height="180"></span></a><div class="catalogue-info__column"><a class="link link_size_21 link_underline_disabled catalogue-info__header" href="/cat/sadovaya_tehnika/"><span class="link__text">Садовая техника</span></a><div class="catalogue-info__subcategories"><a class="catalogue-info__subcategory" href="/cat/sadovaya_tehnika/kultivatory_i_motobloki/"><span class="link__text">Культиваторы и мотоблоки</span></a><a class="catalogue-info__subcategory" href="/cat/sadovaya_tehnika/trimmery/"><span class="link__text">Триммеры</span></a><a class="catalogue-info__subcategory" href="/cat/sadovaya_tehnika/moiki_vysokogo_davleniya/"><span class="link__text">Мойки высокого давления</span></a><a class="catalogue-info__subcategory" href="/cat/sadovaya_tehnika/nasosy/"><span class="link__text">Насосы</span></a><a class="catalogue-info__subcategory" href="/cat/sadovaya_tehnika/gazonokosilki/"><span class="link__text">Газонокосилки</span></a><a class="catalogue-info__subcategory" href="/cat/sadovaya_tehnika/izmelchiteli/"><span class="link__text">Измельчители</span></a><a class="catalogue-info__subcategory" href="/cat/sadovaya_tehnika/sadovyy_inventar/"><span class="link__text">Садовый инвентарь</span></a><a class="link link_color_grey-blue link_underline_disabled catalogue-info__subcategory_last catalogue-info__subcategory" href="/cat/sadovaya_tehnika/snegouborochnye_mashiny/"><span class="link__text">Снегоуборочные машины</span></a></div><a class="link button_theme_flat-index catalogue-info__button button" href="/cat/sadovaya_tehnika/"><span class="link__text">Все подкатегории</span></a></div></div><div class="catalogue-info catalogue-main__catalogue_shifted catalogue-main__catalogue"><a class="image-link  catalogue-info__image" href="/cat/santehnika/"><span class="image image_format_index image_lazy"><img class="image__adaptive" data-original="//cdn.rbt.ru/images/gen/catalogue/image/5/41/495_r8193.jpg" src="//cdn.rbt.ru/images/gen/catalogue/image/5/41/495_r8193.jpg" alt="Сантехника" style="display: inline;" width="180" height="180"></span></a><div class="catalogue-info__column"><a class="link link_size_21 link_underline_disabled catalogue-info__header" href="/cat/santehnika/"><span class="link__text">Сантехника</span></a><div class="catalogue-info__subcategories"><a class="catalogue-info__subcategory" href="/cat/santehnika/smesiteli/"><span class="link__text">Смесители</span></a><a class="link link_color_grey-blue link_underline_disabled catalogue-info__subcategory_last catalogue-info__subcategory" href="/cat/santehnika/kuhonnye_moyki/"><span class="link__text">Кухонные мойки</span></a></div><a class="link button_theme_flat-index catalogue-info__button button" href="/cat/santehnika/"><span class="link__text">Все подкатегории</span></a></div></div><div class="catalogue-info  catalogue-main__catalogue"><a class="image-link  catalogue-info__image" href="/cat/tovary_dlya_doma/"><span class="image image_format_index image_lazy"><img class="image__adaptive" data-original="//cdn.rbt.ru/images/gen/catalogue/image/9/41/835_r179.jpg" src="//cdn.rbt.ru/images/gen/catalogue/image/9/41/835_r179.jpg" alt="Товары для дома" style="display: inline;" width="180" height="180"></span></a><div class="catalogue-info__column"><a class="link link_size_21 link_underline_disabled catalogue-info__header" href="/cat/tovary_dlya_doma/"><span class="link__text">Товары для дома</span></a><div class="catalogue-info__subcategories"><a class="catalogue-info__subcategory" href="/cat/tovary_dlya_doma/chasy/"><span class="link__text">Часы</span></a><a class="catalogue-info__subcategory" href="/cat/tovary_dlya_doma/svetilniki/"><span class="link__text">Светильники</span></a><a class="catalogue-info__subcategory" href="/cat/tovary_dlya_doma/lampochki/"><span class="link__text">Лампочки</span></a><a class="catalogue-info__subcategory" href="/cat/tovary_dlya_doma/sushilki_dlya_belya/"><span class="link__text">Сушилки для белья</span></a><a class="link link_color_grey-blue link_underline_disabled catalogue-info__subcategory_last catalogue-info__subcategory" href="/cat/tovary_dlya_doma/aksessuary_dlya_vannoy_i_tualetnoy_komnaty/"><span class="link__text">Аксессуары для ванной и туалетной комнаты</span></a></div><a class="link button_theme_flat-index catalogue-info__button button" href="/cat/tovary_dlya_doma/"><span class="link__text">Все подкатегории</span></a></div></div><div class="catalogue-info catalogue-main__catalogue_shifted catalogue-main__catalogue"><a class="image-link  catalogue-info__image" href="/cat/podarochnye_karty/"><span class="image image_format_index image_lazy"><img class="image__adaptive" data-original="//cdn.rbt.ru/images/gen/catalogue/image/5/41/484_r4778.jpg" src="//cdn.rbt.ru/images/gen/catalogue/image/5/41/484_r4778.jpg" alt="Подарочные карты" style="display: inline;" width="180" height="180"></span></a><div class="catalogue-info__column"><a class="link link_size_21 link_underline_disabled catalogue-info__header" href="/cat/podarochnye_karty/"><span class="link__text">Подарочные карты</span></a></div></div>-->
