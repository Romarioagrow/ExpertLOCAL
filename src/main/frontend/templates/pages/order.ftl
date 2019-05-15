<#import "../parts/template.ftl" as t>
<@t.template>
    <div class="container">
        <div class="row">
            <h3 style="margin-top: 4vh">Ваш заказ</h3>
        </div>
        <div class="row">
            <div class="col">

                <div class="card-group" id="bucket-products">
                    <#if orderedProducts??>
                        <#list orderedProducts as product>
                            <div class="card ordered-card mb-4">
                                <div class="view overlay">
                                    <img class="card-img-top" src="${product.pic}" alt="Card image cap">
                                    <a href="#!">
                                        <div class="mask rgba-white-slight"></div>
                                    </a>
                                </div>
                                <div class="card-body">
                                    <h4 class="card-title">
                                        ${product.brand}
                                        ${product.model}
                                        <div>
                                            <button type="button" class="btn btn-outline-danger waves-effect" id="product-less" name="${product.id?c}" value="${product.productID?c}">-</button>
                                            <span  class="badge badge-primary badge-pill" id="amount${product.id}" name="${product.id}">${product.amount}</span>
                                            <button type="button" class="btn btn-outline-success waves-effect" id="product-more" name="${product.id?c}" value="${product.productID?c}">+</button>
                                        </div>
                                    </h4>
                                    <p class="card-text">
                                        <strong>${product.type}</strong>, <strong><i id="total-price${product.id}">${product.totalPrice}</i></strong>
                                    </p>
                                    <button type="submit" class="btn btn-primary btn-md" name="remove-product" id="remove-product" value="${product.id?c}">Удалить</button>
                                </div>
                            </div>
                        </#list>
                    <#else>
                        <h3 style="margin-top: 4vh">Пока ничего нет...</h3>
                    </#if>
                </div>

                <ul class="list-group">
                    <#if orderedProducts??>
                        <#list orderedProducts as product>

                            <li class="list-group-item d-flex justify-content-between align-items-center">
                                <img class="ordered-product-img-line" src="${product.pic}" height="50" width="80"  alt="Card image cap">
                                <strong>${product.type}</strong> ${product.brand} ${product.model}
                                <button type="button" class="btn btn-outline-danger waves-effect" id="product-less" name="${product.id?c}" value="${product.productID?c}">-</button>
                                <div id="amount${product.id}" name="${product.id}"><span class="badge badge-primary badge-pill">${product.amount}</span></div>
                                <button type="button" class="btn btn-outline-success waves-effect" id="product-more" name="${product.id?c}" value="${product.productID?c}">+</button>
                                <strong><i id="total-price${product.id}">${product.totalPrice} ₽</i></strong>
                                <button type="submit" class="btn btn-primary btn-md" name="remove-product" id="remove-product" value="${product.id?c}">Удалить</button>
                            </li>
                        </#list>
                    <#else>
                        <h3 style="margin-top: 4vh">Пока ничего нет...</h3>
                    </#if>
                </ul>

            </div>
        </div>
    </div>
</@t.template>
<style>
    .ordered-card {
        min-width: 20vw;
        max-width: 20vw;

        min-height: 50vh;
        max-height: 80vw;
    }
</style>