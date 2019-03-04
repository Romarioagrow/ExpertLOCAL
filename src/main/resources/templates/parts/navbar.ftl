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
                <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                    <a class="dropdown-item" href="/electronics">Electronic</a>
                    <a class="dropdown-item" href="/computers">Computers</a>
                    <a class="dropdown-item" href="/mobile">Mobile</a>
                </div>
            </div>
        </ul>
    </div>
</nav>