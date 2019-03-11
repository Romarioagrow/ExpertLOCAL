<div>
    <div class="sorting m3" id="sorting">
        <a href="?sortby=lowest" class="btn btn-light" role="button">Самые дешевые!</a>
        <a href="?sortby=highest" class="btn btn-light" role="button">Самые дорогие!</a>
        <a href="/" class="btn btn-light" role="button">Сбросить</a>
    </div>
    <div class="card-columns m3">
        <#list products as product>
            <div class="card my-3">
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
    </div>
</div>