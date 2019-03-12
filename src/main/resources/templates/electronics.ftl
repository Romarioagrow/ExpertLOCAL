<#import "parts/common.ftl" as c>
<@c.page>
    <div class="filter-container m1">
        <#include "parts/components/some-info.ftl">

        <div class="filters m2">
            <h3 class="filter-class">Фильтры для электроники</h3>
            <#include "parts/filters/main-filter.ftl">
        </div>
    </div>

    <#--В отдельный шаблон для каждого типа-->
    <!--Главный блок отображения товаров-->
    <div class="products-container m1">
        <div class="info-layout m2">
            <h2>Вся электроника</h2>
        </div>
        <!--Витрина товаров c сортировкой-->
        <div class="showcase m2">
            <#include "parts/products.ftl">
        </div>
    </div>
</@c.page>