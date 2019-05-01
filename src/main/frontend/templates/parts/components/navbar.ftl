<nav class="navbar navbar-expand-lg navbar-light bg-light mark1">
    <a class="navbar-brand" href="/"><img src="../img/logo.png" width="220" height="60" alt=""></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <!-- Кнопки меню -->
        <ul class="navbar-nav mr-auto">
            <div class="selector">
                <#include "menu.ftl">
            </div>
            <div class="ml-5 search-field">
                <form class="form-inline">
                    <i class="fas fa-search" aria-hidden="true"></i>
                    <input class="form-control form-control-sm ml-3 w-75 main-search" name="main-search" id="main-search" type="text" placeholder="Поиск товаров" aria-label="Search">
                    <button class="btn btn-elegant btn-rounded btn-sm my-0" id="search-button" type="submit">Search</button>
                    <div class="flex-container display-result" id="display-result" name="display-result"></div>
                </form>
            </div>
            <div>
                <a class="btn-floating peach-gradient" href="/order"><i class="fas fa-leaf"></i></a>
            </div>
        </ul>
    </div>
</nav>
<style>
    .main-search {
        min-width: 50vw;
        min-height: 5vh;
        margin-left: 4vw;
    }
    .peach-gradient {
        margin-left: 2vw;
    }
    .search-field {
        padding-top: 1vh;
    }
    .selector {
        padding-top: 1vh;
        margin-left: 2vw;
    }

    .display-result {
        position: absolute;
        color: black;
        width: 50vw;
        top: 7vh;
        left: 27.5vw;
        max-height: 300px;
        overflow: auto;
        box-shadow: 0 5px 13px 0 rgba(0,0,0,.2);
        right: 0;
        display: none;
        background-color: white;
        border: 1px solid #ececec;
        padding: 15px;
        z-index: 50;
    }
</style>