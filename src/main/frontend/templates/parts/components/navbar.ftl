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

            <div class="ml-5">
                <form class="form-inline">
                    <i class="fas fa-search" aria-hidden="true"></i>
                    <input class="form-control form-control-sm ml-3 w-75 main-search" type="text" placeholder="Search" aria-label="Search">
                </form>
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
</style>