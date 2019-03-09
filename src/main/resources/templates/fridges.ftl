<#import "parts/products.ftl" as info>
<#import "parts/products.ftl" as sort>

<@info.page>
        <div class="info-content"><h2>Холодильники!</h2></div>
</@info.page>
        <div class="sort-content">
            <a href="/fridges?sortby=lowest" class="btn btn-light" role="button">Самые дешевые!</a>
            <a href="/fridges?sortby=highest" class="btn btn-light" role="button">Самые дорогие!</a>
            <a href="/fridges" class="btn btn-light" role="button">Сбросить</a>
        </div>
