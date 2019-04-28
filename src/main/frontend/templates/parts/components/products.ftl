<div class="mt-2 marker2">
    <div class="btn-group btn-group-toggle" data-toggle="buttons" id="sorting">
        <label class="btn btn-secondary active">
            <input type="radio" name="sort_options" id="option1" value="lowest" autocomplete="off" checked> Дешевле
        </label>
        <label class="btn btn-secondary">
            <input type="radio" name="sort_options" id="option2" value="highest" autocomplete="off"> Дороже
        </label>
        <label class="btn btn-secondary">
            <input type="radio" name="sort_options" id="option3" value="alphabet"  autocomplete="off"> По алфавиту
        </label>
    </div>
</div>

<div class="flex-container marker2" id="products">
    <#if products??>
        <#list products as product>
            <div class="card product-card mr-3 mt-3">
                <#if product.pic??><img class="card-img-top" src="${product.pic}" alt="Card image cap"></#if>
                <div class="card-body">
                    <h5 class="card-title">
                        ${product.brand}
                        ${product.model}
                    </h5>
                    <p class="card-text">
                        ${product.country}
                    </p>
                    <p class="card-text">
                        <small>
                            <#include "params.ftl">
                        </small>
                    </p>
                </div>
                <div class="card-footer">
                    <small class="text-muted">
                        <a class="btn btn-outline-info btn-rounded waves-effect" href="/product/${product.productID?c}" role="button">О товаре</a>
                        <strong><i>${product.price}</i></strong>
                        <button type="button" class="btn btn-flat about_product click-right">В корзину</button>
                    </small>
                </div>
            </div>
        </#list>
    </#if>
</div>
<style>
    .click-right {
        margin-right: -35px;
    }
</style>
