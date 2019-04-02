<!-- Подгонка навпанели под экран и цветовая схема-->
<nav class="navbar navbar-expand-lg navbar-light bg-light mark1">
    <a class="navbar-brand" href="/test"><img src="img/logo.png" width="220" height="60" alt=""></a>
    <!-- Конопка меню на маленьких экранах-->
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <!-- Основной блок навпанели -->
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <!-- Кнопки меню -->
        <ul class="navbar-nav mr-auto">
            <div class="selector">
                <#include "menu.ftl">
            </div>
        </ul>
    </div>
</nav>