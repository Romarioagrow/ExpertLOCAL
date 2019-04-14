<div class="pl-4vw mt-2 marker2" id="sorting" >
    <div class="btn-group btn-group-toggle" data-toggle="buttons">
        <label class="btn btn-secondary active">
            <input type="radio" name="options" id="option1" autocomplete="off" checked> Дешевле
        </label>
        <label class="btn btn-secondary">
            <input type="radio" name="options" id="option2" autocomplete="off"> Дороже
        </label>
        <label class="btn btn-secondary">
            <input type="radio" name="options" id="option3" autocomplete="off"> По алфавиту
        </label>
    </div>
</div>
<div class="flex-container marker2" id="prod">
    <#if products??>
    <#list products as product>
        <div class="card product-card mr-3 mt-3" id="products_block">
            <img class="card-img-top" src="../img/tv1.jpg" alt="Card image cap">
            <div class="card-body">
                <h5 class="card-title">
                    <i>${product.type}</i>
                    ${product.brand}
                    ${product.model}
                </h5>
                <p class="card-text">
                    ${product.category}
                    <#if product.country??>${product.country}</#if>
                </p>
                <p class="card-text">
                    <small class="text-muted">
                        <#include "params.ftl">
                    </small>
                </p>
            </div>
            <div class="card-footer">
                <small class="text-muted">
                    <strong><i>${product.price}</i></strong>
                </small>
            </div>
        </div>
    </#list>
</div>
<#else>
    <i>Нет подходящих товаров :(</i>
</#if>