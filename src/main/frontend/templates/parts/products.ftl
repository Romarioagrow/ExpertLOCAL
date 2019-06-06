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
<div class="card-group marker2" id="products">
    <#if products??>
        <#list products as product>
            <div class="card product-card mr-3 mt-3">
                <#if product.productParams.pic??><img class="card-img-top" src="${product.pic}" alt="Card image cap"></#if>
                <div class="card-body">
                    <h5 class="card-title">
                        ${product.brand}
                        ${product.model}
                    </h5>
                    <p class="card-text">
                        <small>
                            <#include "params.ftl">
                        </small>
                    </p>
                </div>
                <div class="card-footer">
                    <small class="text-muted">
                        <a class="btn btn-info btn-blue-grey" style="margin-left: -1vw;" href="/info/${product.productID?c}" role="button" >О товаре</a>
                        <strong><i>${product.price}₽</i></strong>
                        <button type="submit" class="btn btn-success btn-mdb-color" name="addToOrder" id="addToOrder${product.productID?c}" value="${product.productID?c}" style="margin-right: -1vw;">В корзину</button>
                        <#--<button type="submit" class="btn btn-success btn-mdb-color" id="removeToOrder${product.productID?c}" value="${product.productID?c}" style="margin-right: 1vw; display: none">Удалить</button>-->
                    </small>
                </div>
            </div>
        </#list>
    </#if>
</div>

