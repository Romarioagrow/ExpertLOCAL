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
                            <div class="card mb-4">
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
                                            <button type="button" class="btn btn-outline-danger waves-effect">-</button>
                                            <span class="badge badge-primary badge-pill">${product.amount}</span>
                                            <button type="button" class="btn btn-outline-success waves-effect">+</button>
                                        </div>
                                    </h4>
                                    <p class="card-text">
                                        <strong>${product.type}</strong>, <strong><i>${product.totalPrice}</i></strong>
                                    </p>
                                    <button type="submit" class="btn btn-primary btn-md" name="remove-product" id="remove-product" value="${product.productID?c}">Удалить</button>
                                </div>
                            </div>
                        </#list>
                    <#else>
                        <h3 style="margin-top: 4vh">Пока ничего нет...</h3>
                    </#if>
                </div>
            </div>
        </div>
    </div>
</@t.template>
