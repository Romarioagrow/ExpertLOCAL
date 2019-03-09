<#import "main.ftl" as common>

<@common.page>

    <div class="header"><h2>Вся кухонная техника!</h2></div>
    <div class="sorting">
        <a href="/kitchen?sortby=lowest" class="btn btn-light" role="button">Самые дешевые!</a>
        <a href="/kitchen?sortby=highest" class="btn btn-light" role="button">Самые дорогие!</a>
        <a href="/kitchen" class="btn btn-light" role="button">Сбросить</a>
    </div>

</@common.page>