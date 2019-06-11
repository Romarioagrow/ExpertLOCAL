<#import "../parts/template.ftl" as t>
<@t.template>
    <div class="container-fluid">
        <div class="row">
            <div class="col-3 marker1">
                <div class="marker2"></div>
            </div>
            <div class="col marker3">
                <div class="marker2">
                    <div>
                        <h3>Выбираем ${url}</h3>
                    </div>
                </div>
            </div>
        </div>
        <div class="row mb-3">
            <div class="col-3 marker1 filters-col">
                <div class="marker2">
                    <form method="get" name="filters" id="filters">
                        <#include "../parts/filters.ftl">
                    </form>
                </div>
            </div>
            <div class="col marker3">
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
                                        <a class="btn btn-outline-mdb-color btn-rounded waves-effect" href="/info/${product.productID?c}" role="button" >
                                            <strong>
                                                ${product.brand}
                                                ${product.model}
                                            </strong>
                                        </a>
                                    </h5>
                                    <p class="card-text">
                                        <small>
                                            <#include "../parts/params.ftl">
                                        </small>
                                    </p>
                                </div>
                                <div class="card-footer">
                                    <small class="text-muted">
                                        <strong><i>${product.price}₽</i></strong>
                                    </small>
                                    <#if orderedProductsID??>
                                        <#if orderedProductsID?seq_contains('${product.productID?c}')>
                                            <br><a type="button" class="btn btn-danger btn-md" style="background-color: #e52d00 !important;" href="http://localhost:8080/order">Оформить заказ</button></a>
                                        <#else>
                                            <div id="addToOrderDiv${product.productID?c}">
                                                <button type="submit" class="btn btn-rounded btn-outline-danger b-add" name="addToOrder" id="addToOrder${product.productID?c}" value="${product.productID?c}">
                                                    В корзину
                                                </button>
                                            </div>
                                        </#if>
                                    <#else>
                                        <div id="addToOrderDiv${product.productID?c}">
                                            <button type="submit" class="btn btn-rounded btn-outline-danger b-add" name="addToOrder" id="addToOrder${product.productID?c}" value="${product.productID?c}">
                                                В корзину
                                            </button>
                                        </div>
                                    </#if>
                                </div>
                            </div>
                        </#list>
                    </#if>
                </div>
            </div>
        </div>
    </div>
</@t.template>
<style>
    .b-add {
        border-color: #e52d00 !important;
        color: #e52d00 !important;
    }

    .b-add:hover {
        border-color: #e52d00 !important;
        background-color: #e52d00 !important;
        color: #ffffff !important;
    }
</style>