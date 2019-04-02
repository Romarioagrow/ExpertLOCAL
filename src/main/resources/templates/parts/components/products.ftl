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
<div class="flex-container marker2">
    <#if products??>
    <#list products as product>
        <div class="card product-card mr-3 mt-3">
            <img class="card-img-top" src="img/tv1.jpg" alt="Card image cap">
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
                        ${product.subCategory}
                        <#if product.productParams.diagonal??>диагональ ${product.productParams.diagonal}</#if>
                        <#if product.productParams.resolution??>${product.productParams.resolution}</#if>
                        <#if product.productParams.hasSmartTV??>SmartTV</#if>
                        <#if product.productParams.hasWifi??>Wi-Fi</#if>
                        <#if product.productParams.has3D??>3D</#if>
                        <#if product.productParams.curvedScreen??>изогнутый экран</#if>
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