<#import "parts/common.ftl" as common>

<@common.page>

    <div class="header"><h2>Холодильники!</h2></div>

    <div class="sorting">
        <a href="/fridges?sortby=lowest" class="btn btn-light" role="button">Самые дешевые!</a>
        <a href="/fridges?sortby=highest" class="btn btn-light" role="button">Самые дорогие!</a>
        <a href="/fridges" class="btn btn-light" role="button">Сбросить</a>
    </div>

</@common.page>