<#import "parts/products.ftl" as p>

<@p.info>
        <div class="header"><h2>Вся электроника!</h2></div>
</@p.info>

<@p.sort>
        <div class="sorting">
            <a href="/electronics?sortby=lowest" class="btn btn-light" role="button">Самые дешевые!</a>
            <a href="/electronics?sortby=highest" class="btn btn-light" role="button">Самые дорогие!</a>
            <a href="/electronics" class="btn btn-light" role="button">Сбросить</a>
        </div>
</@p.sort>

<!-- вставляет в имя название страницы -->