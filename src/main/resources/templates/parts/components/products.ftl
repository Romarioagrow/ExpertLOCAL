<div class="pl-4vw mt-2 marker2">
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

<div class="flex-container marker3" id="result">
    <#--<div class="card product-card" ></div>-->
</div>

<div class="flex-container marker2" id="prod">
    <#if products??>
    <#list products as product>
        <div class="card product-card mr-3 mt-3">
            <img class="card-img-top" src="/img/tv1.jpg" alt="Card image cap">
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
                        Диагональ:<#if product.diagonal??><strong>${product.diagonal}</strong></#if>
                        Разрешение:<#if product.resolution??><strong>${product.resolution}</strong></#if>
                        <br>Особенности:<#if product.tvFeatures??><strong>${product.tvFeatures}</strong></#if>
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
