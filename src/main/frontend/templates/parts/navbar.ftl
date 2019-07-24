<#include "security.ftl">
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top" id="mainNav" style="background-color: #343a40 !important;">
    <div class="container-fluid">
        <a class="navbar-brand js-scroll-trigger" href="#page-top">
            <a class="navbar-brand" href="/" style="margin-left: 20rem;">
                <img src="/../img/logo.png" width="220" height="60" alt="" style="margin-left: -20rem;">
            </a>
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <div class="selector" style="margin-left: -4rem">
                <#include "menu.ftl">
            </div>
            <form class="form-inline ml-auto">
                <input class="form-control" type="text" name="main-search" id="main-search" placeholder="Введите что нибудь..." aria-label="Search" style="width: 50rem;">
                <button href="#!" class="btn btn-outline-white btn-md my-0 ml-sm-2" type="submit" id="search-button">Найти</button>
                <div class="flex-container display-result" id="display-result" name="display-result"></div>
            </form>
            <ul class="navbar-nav ml-auto">
                <#if order?has_content>
                    <li class="nav-item" id="productsAmount-Div">
                        <a class="nav-link js-scroll-trigger" id="productAmount-Order" href="/order">
                            Товаров: <span class="badge badge-primary">${order.productsAmount}</span>
                        </a>
                    </li>
                <#else>
                    <li class="nav-item" id="productsAmount-Div">
                        <a class="nav-link js-scroll-trigger" href="/order">Закзаз пуст</a>
                    </li>
                </#if>
                <li class="nav-item">
                    <#if !isUser>
                        <a class="nav-link js-scroll-trigger" href="/user/login">Войти</a>
                    <#else>
                       <#--<i class="fas fa-user-tie"></i>-->
                        <a class="nav-link js-scroll-trigger" href="/user/cabinet">Личный кабинет</a>
                    </#if>
                </li>
            </ul>
        </div>
    </div>
</nav>
<style>
    .badge-primary {
        background-color: #e52d00 !important;
    }
    .selector {
        padding-top: 1vh;
        margin-left: 2vw;
    }
    .display-result {
        position: absolute;
        color: black;
        width: 50rem !important;;
        top: 5.5rem;
        left: 34rem;
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
