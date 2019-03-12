<#import "parts/common.ftl" as c>
<@c.page>
    <!--Боковой вертикальный контейнер с фильтрами-->
    <div class="filter-container m1">
        <#include "parts/components/some-info.ftl">

        <#include "parts/filters/main-filter.ftl">
    </div>

    <!--Главный блок отображения товаров-->
    <div class="products-container m1">
        <!--Инфо о товарах-->
        <div class="info-layout m2">
            <h2>Добро пожаловать в интернет-магазин Эксперт!</h2>
        </div>
        <!--Витрина товаров c сортировкой-->
        <div class="showcase m2">
            <#include "parts/products.ftl">
        </div>
    </div>
</@c.page>