<#import "parts/common.ftl" as c>
<@c.page>
    <div class="filter-container m1">
        <div class="someinfo m2">Здесь что то будет</div>
        <div class="filters m2">
            <span class="filter-class">Фильтры для проекторов</span>
            <#include "parts/filters/filter-general.ftl">
        </div>
    </div>
    <!--Главный блок отображения товаров-->
    <div class="products-container m1">
        <div class="info-layout m2">
            <h2>Выберите проектор</h2>
        </div>
        <!--Витрина товаров c сортировкой-->
        <div class="showcase m2">
            <#include "parts/products.ftl">
        </div>
    </div>
</@c.page>