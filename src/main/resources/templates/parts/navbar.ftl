<!-- Подгонка навпанели под экран и цветовая схема-->
<nav class="navbar navbar-expand-lg navbar-light bg-light mark1">
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
                <#include "components/menu.ftl">
            </div>
        </ul>
    </div>
</nav>