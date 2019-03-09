<#import "parts/common.ftl" as c>
<@c.page>
    <!--Боковой вертикальный контейнер с фильтрами-->
    <div class="filter-container m1">
        <#include "parts/filter-general.ftl">
    </div>
    <!--Главный блок отображения товаров-->
    <div class="products-container m1">
        <!--Инфо о товарах-->
        <div class="product-field info m2">
            <h2>Добро пожаловать в интернет-магазин Эксперт!</h2>
        </div>
        <!--Сортировка товаров-->
        <div class="sorting">
            <a href="?sortby=lowest" class="btn btn-light" role="button">Самые дешевые!</a>
            <a href="?sortby=highest" class="btn btn-light" role="button">Самые дорогие!</a>
            <a href="/" class="btn btn-light" role="button">Сбросить</a>
        </div>
        <!--Витрина товаров-->
        <div class="showcase m2">
            <div class="card-columns m3">
                <#list products as product>
                    <div class="card my-3">
                        <div class="card-body">
                            <h5 class="card-title">
                                ${product.brand}
                                ${product.model}
                            </h5>
                            <p class="card-text">
                                ${product.category}
                                ${product.type}
                            </p>
                            <p class="card-text">
                                ${product.price}
                            </p>
                        </div>
                    </div>
                </#list>
            </div>
        </div>
    </div>
</@c.page>