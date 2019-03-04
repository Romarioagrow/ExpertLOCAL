<#import "parts/common.ftl" as common>

<@common.page>

    <div class="card-columns">
    <#list products as product>
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