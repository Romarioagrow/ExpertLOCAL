<#import "../parts/template.ftl" as t>
<@t.template>
    <div class="container hello-list mt-5">
        <div class="row mb-3">
            <div class="col">
                <h3><a href="/categories/electronics">Электроника</a></h3>
                <ul>
                    <li><a href="/tv"><strong>Телевизоры</strong></a></li>
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
                    <li><a href="/conditioners"><strong>Кондиционеры</strong></a></li>
                    <li><a href="/water_heaters"><strong>Водонагреватели</strong></a></li>
                    <li><a href="/gas_heaters"><strong>Газовые колонки</strong></a></li>
                    <li><a href="/electric_heaters"><strong>Обогреватели</strong></a></li>
                    <li><a href="/ventilators"><strong>Вентиляторы</strong></a></li>
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
                    <li><a href="/photo"><strong>Фотоапараты</strong></a></li>
                    <li><a href="/video"><strong>Камеры</strong></a></li>
                    <li><a href="/radio"><strong>Рации</strong></a></li>
                    <li><a href="/mobile_phones"><strong>Сотовые телефоны</strong></a></li>
                </ul>
            </div>
        </div>
        <div class="row mb-3">
            <div class="col">
                <h3><a href="/categories/smart">Smart техника</a></h3>
                <ul>
                    <li class="list-head"><a href="/smartphones"><strong>Смартфоны</strong></a></li>
                    <li class="list-head"><a href="/tablets"><strong>Планшеты</strong></a></li>
                    <li class="list-head"><a href="/smart_headers"><strong>Умные колонки</strong></a></li>
                    <li class="list-head"><a href="/bluetooth"><strong>Bluetooth устройства</strong></a></li>
                </ul>
            </div>
        </div>
    </div>
</@t.template>
<style>
    a {
        color: #0d0d0d !important;
    }
    a:hover {
        color: #e52d00 !important;
    }
    ul {
        list-style: none;
    }
</style>