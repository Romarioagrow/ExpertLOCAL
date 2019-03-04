<#import "parts/common.ftl" as common>

<@common.page>

    <h2>Плиты!</h2>

    <a href="/ovens?sortby=lowest" class="btn btn-light" role="button">Самые дешевые!</a>
    <a href="/ovens?sortby=highest" class="btn btn-light" role="button">Самые дорогие!</a>
    <a href="/ovens" class="btn btn-light" role="button">Сбросить</a>

    <div class="card-columns">
    <#list ovens as product>
        <div class="card my-2">
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


</@common.page>