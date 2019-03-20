<!-- Подгонка навпанели под экран и цветовая схема-->
<nav class="navbar navbar-expand-lg navbar-light bg-light mark1 frozen">
    <!-- Название проекта-->
    <a class="navbar-brand" href="/"><h1>Expert Store</h1></a>
    <!-- Конопка меню на маленьких экранах-->
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <!-- Основной блок навпанели -->
    <div class="collapse navbar-collapse mark2" id="navbarSupportedContent">
        <!-- Кнопки меню -->
        <ul class="navbar-nav mr-auto">

            <div class="selector">
                <#--В ОТДЕЛЬНЫЙ DIV <#include components/main-menu.ftl>-->
                <ul class="main-menu">
                    <li><a href="#">Товар</a>
                        <ul class="main-sub-menu">
                            <div>
                                <ul class="hor-main-menu">
                                    <li><a href="http://localhost:8080/electronics">Электроника</a>
                                        <ul class="hor-sub-menu">
                                            <li><a href="http://localhost:8080/tv">Телевизоры</a></li>
                                            <li><a href="http://localhost:8080/monitors">Спутниковая техника</a></li>
                                            <li><a href="http://localhost:8080/projectors">Мультимедийные системы</a></li>
                                            <li><a href="http://localhost:8080/projectors">Портативная техника</a></li>
                                            <li><a href="http://localhost:8080/projectors">Кабели и шнуры</a></li>
                                        </ul>
                                    </li>

                                    <li><a href="http://localhost:8080/kitchen">Кухонная техника</a>
                                        <ul class="hor-sub-menu">
                                            <li><a href="http://localhost:8080/fridges">Крупная техника</a></li>
                                            <li><a href="http://localhost:8080/ovens">Встраиваемая техника</a></li>
                                            <li><a href="http://localhost:8080/ovens">Маленькая техника</a></li>
                                            <li><a href="http://localhost:8080/ovens">Техника для готовки</a></li>
                                            <li><a href="http://localhost:8080/ovens">Кухонные аксессуары</a></li>

                                        </ul>
                                    </li>

                                    <li><a href="http://localhost:8080/kitchen">Техника для дома</a>
                                        <ul class="hor-sub-menu">
                                            <li><a href="http://localhost:8080/fridges">Техника для стирки</a></li>
                                            <li><a href="http://localhost:8080/ovens">Техника для уборки</a></li>
                                            <li><a href="http://localhost:8080/ovens">Уход одежды</a></li>
                                            <li><a href="http://localhost:8080/ovens">Аксессуары для дома</a></li>
                                        </ul>
                                    </li>

                                    <li><a href="http://localhost:8080/kitchen">Компьютеры и офисная техника</a>
                                        <ul class="hor-sub-menu">
                                            <li><a href="http://localhost:8080/fridges">ПК и переферия</a></li>
                                            <li><a href="http://localhost:8080/ovens">Ноутбуки</a></li>
                                            <li><a href="http://localhost:8080/ovens">Комплектующие</a></li>
                                            <li><a href="http://localhost:8080/ovens">Офисная техника</a></li>
                                            <li><a href="http://localhost:8080/ovens">Аксессуары</a></li>
                                        </ul>
                                    </li>

                                    <li><a href="http://localhost:8080/kitchen">Климатическая техника</a>
                                        <ul class="hor-sub-menu">
                                            <li><a href="http://localhost:8080/fridges">Кондиционеры</a></li>
                                            <li><a href="http://localhost:8080/ovens">Вентиляторы</a></li>
                                            <li><a href="http://localhost:8080/ovens">Обогреватели</a></li>
                                            <li><a href="http://localhost:8080/ovens">Водонагреватели</a></li>
                                            <li><a href="http://localhost:8080/ovens">Газовые колонки</a></li>
                                        </ul>
                                    </li>

                                    <li><a href="http://localhost:8080/kitchen">Авто-электроника</a>
                                        <ul class="hor-sub-menu">
                                            <li><a href="http://localhost:8080/fridges">Автомагнитолы</a></li>
                                            <li><a href="http://localhost:8080/ovens">Акустика и автозвук</a></li>
                                            <li><a href="http://localhost:8080/ovens">Видеорегистраторы</a></li>
                                            <li><a href="http://localhost:8080/ovens">Навигация</a></li>
                                            <li><a href="http://localhost:8080/ovens">Автоаксессуары</a></li>
                                        </ul>
                                    </li>

                                    <#--<li><a href="http://localhost:8080/kitchen">Инструменты для дома</a>
                                        <ul class="hor-sub-menu">
                                            <li><a href="http://localhost:8080/fridges">Холодильники</a></li>
                                            <li><a href="http://localhost:8080/ovens">Плиты</a></li>
                                        </ul>
                                    </li>-->

                                    <li><a href="http://localhost:8080/kitchen">Приборы персонального ухода</a>
                                        <ul class="hor-sub-menu">
                                            <li><a href="http://localhost:8080/fridges">Для бритья и стрижки</a></li>
                                            <li><a href="http://localhost:8080/ovens">Для ухода за волосами</a></li>
                                            <li><a href="http://localhost:8080/ovens">Аксуссуары</a></li>
                                        </ul>
                                    </li>

                                    <#--<li><a href="http://localhost:8080/kitchen">Бытовая химия</a>
                                        <ul class="hor-sub-menu">
                                            <li><a href="http://localhost:8080/fridges">Холодильники</a></li>
                                            <li><a href="http://localhost:8080/ovens">Плиты</a></li>
                                        </ul>
                                    </li>

                                    <li><a href="http://localhost:8080/kitchen">Товары для отдыха</a>
                                        <ul class="hor-sub-menu">
                                            <li><a href="http://localhost:8080/fridges">Холодильники</a></li>
                                            <li><a href="http://localhost:8080/ovens">Плиты</a></li>
                                        </ul>
                                    </li>-->
                                </ul>
                            </div>
                        </ul>
                    </li>
                </ul>
            </div>
        </ul>
    </div>
</nav>