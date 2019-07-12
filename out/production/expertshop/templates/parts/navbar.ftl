<#include "security.ftl">

<nav class="navbar navbar-expand-lg navbar-light bg-light mark1" style="padding: 0;">
    <a class="navbar-brand" href="/"><img src="/../img/logo.png" width="220" height="60" alt=""></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <div class="selector">
                <#include "menu.ftl">
            </div>
            <div class="ml-5 search-field">
                <form class="form-inline">
                    <i class="fas fa-search" aria-hidden="true"></i>
                    <input class="form-control form-control-sm ml-3 main-search" name="main-search" id="main-search" type="text" placeholder="Поиск товаров" aria-label="Search">
                    <button class="btn btn-flat" style="padding-right: 1.14rem;padding-left: 1.14rem;" id="search-button" type="submit">Искать!</button>
                    <div class="flex-container display-result" id="display-result" name="display-result"></div>
                </form>
            </div>
            <div id="productsAmount-Div">
                <#if order?has_content>
                    <a id="productAmount-Order" href="/order" class="mt-4 mb-3">
                        <h5 style="color: black !important;">Товаров:
                            <span class="badge badge-primary">
                            ${order.productsAmount}
                        </span>
                        </h5>
                    </a>
                <#else>
                    <a href="/order"><h5 style="color: black !important; margin-top: 1.5rem!important;">Закзаз пуст<span class="badge badge-primary"></span></h5></a>
                </#if>
            </div>
            <#if !isUser>
                <div class="chip chip-md" style="margin-left: 1rem;margin-top: 1rem;">
                    <a href="/user/login">Войти</a>
                </div>
            <#else>
                <div class="chip chip-md" style="margin-left: 1rem;margin-top: 1rem;">
                    <a href="/user/cabinet">Личный кабинет</a>
                </div>
            </#if>
            <div style="padding-top: 1rem;">
                <a href="http://localhost:8080/supplier">Поставщики</a>
            </div>
        </ul>
    </div>
</nav>
<style>
    .badge-primary {
        background-color: #e52d00 !important;
    }
    .main-search {
        width: 50rem !important;
        min-height: 3rem;
        margin-left: 4vw;
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
        width: 50rem !important;;
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