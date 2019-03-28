<div class="m2">

    <div class="sorting m3" id="sorting">
        <a href="?sortby=lowest" class="btn btn-light" role="button">Сначала дешевые</a>
        <a href="?sortby=highest" class="btn btn-light" role="button">Сначала дорогие</a>
        <a href="/" class="btn btn-light" role="button">Сбросить</a>
    </div>
    <div class="container">
        <#if products??>
            <#list products as product>
                <div class="card-deck">
                    <div class="card">
                        <#--<img src="#" class="card-img-top" alt="#">-->
                        <div class="card-body">
                            <h6 class="card-title type">
                                <strong>${product.type}</strong>
                            </h6>
                            <h5 class="card-title">
                                ${product.brand}
                                ${product.model}
                            </h5>
                            <p class="card-text">
                                ${product.category}
                                ${product.country}
                            </p>
                        </div>
                        <div class="card-footer">
                            <small class="text-muted">
                                <strong><i>${product.price}</i></strong>
                            </small>
                        </div>
                    </div>
                </div>
            </#list>
        <#else>
            <#list productsWithParams as item>
                <div class="card-deck">
                    <div class="card">
                        <#--<img src="#" class="card-img-top" alt="#">-->
                        <div class="card-body">
                            <h6 class="card-title type">
                                <strong>${item.product.type}</strong>
                            </h6>
                            <h5 class="card-title">
                                ${item.product.brand}
                                ${item.product.model}
                            </h5>
                            <p class="card-text">
                                ${item.product.category}
                                ${item.product.country}
                            </p>
                        </div>
                        <div class="card-footer">
                            <small class="text-muted">
                                ${item.productParams.resolution}
                                <strong><i>${item.product.price}</i></strong>
                            </small>
                        </div>
                    </div>
                </div>
            </#list>
        </#if>
    </div>
</div>
