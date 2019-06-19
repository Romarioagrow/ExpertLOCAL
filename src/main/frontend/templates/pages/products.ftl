<#import "../parts/template.ftl" as t>
<#import "../parts/pager.ftl" as p>

<@t.template>
    <div class="container-fluid">
        <div class="row mt-2">
            <div class="col-3"></div>
            <div class="col-3">
                <nav aria-label="breadcrumb">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="/">Каталог</a></li>
                        <li class="breadcrumb-item"><a href="/categories/${path[0]}">${path[0]}</a></li>
                        <li class="breadcrumb-item disabled" style="color: #7a7979;">${path[1]}</li>
                    </ol>
                </nav>
            </div>
        </div>
        <div class="row">
            <div class="col-3 marker1"></div>
            <div class="col-9">
                <div class="mt-1">
                    <h1>${path[1]?upper_case}</h1>
                </div>
                <div class="text-muted">
                    <small>Всего товаров: ${total}</small>
                </div>
                <@p.pager url page/>
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
            <div class="col-9 marker3">
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
                    <#if page?has_content>
                        <#list page.content as product>
                            <div class="card product-card">
                                <div class="view overlay">
                                    <#if product.pic??>
                                        <img class="img-fluid scale-pic" src="${product.pic}" alt="Product pic">
                                        <a href="#">
                                            <div class="mask rgba-white-slight"></div>
                                        </a>
                                    </#if>
                                </div>
                                <div class="card-body">
                                    <h5 class="card-title">
                                        <a href="/products/info/${product.productID}">
                                            <strong>
                                                ${product.fullName}
                                            </strong>
                                        </a>
                                    </h5>
                                    <p class="card-text">
                                        <strong><i>${product.type}</i></strong>
                                    </p>
                                    <h3><strong>${product.price} ₽</strong></h3>
                                    <div>
                                        <#if orderedProductsID?? && orderedProductsID?seq_contains('${product.productID}')>
                                            <a type="button" class="btn btn-danger btn-md" style="background-color: #e52d00 !important;" href="http://localhost:8080/order">Оформить заказ</button></a>
                                        <#else>
                                            <div id="addToOrderDiv${product.productID}">
                                                <button type="submit" onclick="addToOrder(this)" class="btn btn-rounded btn-outline-danger b-add" name="addToOrder" id="addToOrder${product.productID}" value="${product.productID}">
                                                    В корзину
                                                </button>
                                            </div>
                                        </#if>
                                    </div>
                                </div>
                            </div>
                        </#list>
                    </#if>
                </div>
                <@p.pager url page/>
            </div>
        </div>
    </div>
</@t.template>

<style>
    .product-card {
        min-width: 23rem;
        max-width: 23rem;

        min-height: 25rem;
        max-height: 25rem;

        text-align: center;
        margin-bottom: 2rem !important;
    }
    .scale-pic {
        height: 100% !important;
        width: 100% !important;
        object-fit: contain !important;
    }

    .b-add {
        border-color: #e52d00 !important;
        color: #e52d00 !important;
    }
    .b-add:hover {
        border-color: #e52d00 !important;
        background-color: #e52d00 !important;
        color: #ffffff !important;
    }
    .breadcrumb {
        background-color: #fbf4f4;
        padding-left: 0;
    }
</style>
