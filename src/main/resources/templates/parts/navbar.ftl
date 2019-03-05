<!-- Подгонка навпанели под экран и цветовая схема-->
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <!-- Название проекта-->
    <a class="navbar-brand" href="/"><h1>Expert Store</h1></a>

    <!-- Конопка меню на маленьких экранах-->
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <!-- Основной блок навпанели -->
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <!-- Кнопки меню -->
        <ul class="navbar-nav mr-auto">

            <div class="dropdown">
                <button class="btn btn-secondary dropdown-toggle"
                        type="button"
                        id="dropdownMenuButton"
                        data-toggle="dropdown"
                        aria-haspopup="true"
                        aria-expanded="false">
                    Выберите товар!
                </button>

                <ul class="dropdown-menu">
                    <li class="dropdown-submenu">
                        <a class="test" tabindex="-1" href="#">Электроника <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a class="cat" tabindex="-1" href="/electronics"><h4>Вся электроника!</h4></a></li>
                            <li><a class="cat" tabindex="-1" href="/tv">ТВ</a></li>
                            <li><a class="cat" tabindex="-1" href="/projectors">Проекторы</a></li>
                        </ul>

                        <a class="test" tabindex="-1" href="#">Кухонная техника <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a class="cat" tabindex="-1" href="/kitchen"><h4>Вся кухонная техника!</h4></a></li>
                            <li><a class="cat" tabindex="-1" href="/fridges">Холодильники</a></li>
                            <li><a class="cat" tabindex="-1" href="/ovens">Плиты</a></li>
                        </ul>
                    </li>
                </ul>

                <style>
                    .dropdown-submenu .dropdown-menu {
                        top: 0;
                        left: 100%;
                        margin-top: -1px;
                    }
                    .cat {
                        text-align: left;
                    }
                </style>
            </div>
        </ul>
    </div>

    <script>
        $(document).ready(function(){
            $('.dropdown a.test').on("click", function(e){
                $(this).next('ul').toggle();
                e.stopPropagation();
                e.preventDefault();
            });
        });
    </script>

</nav>