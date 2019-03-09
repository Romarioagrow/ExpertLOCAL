<#import "main.ftl" as common>

<@common.page>

        <div class="header"><h2>Плиты!</h2></div>
        <div class="sorting">
            <a href="/ovens?sortby=lowest" class="btn btn-light" role="button">Самые дешевые!</a>
            <a href="/ovens?sortby=highest" class="btn btn-light" role="button">Самые дорогие!</a>
            <a href="/ovens" class="btn btn-light" role="button">Сбросить</a>
        </div>

</@common.page>