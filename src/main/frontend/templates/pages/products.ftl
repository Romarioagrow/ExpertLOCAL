<#import "../parts/template.ftl" as t>
<#import "../parts/pager.ftl" as p>

<@t.template>
    <div class="container-fluid">
        <div class="row mt-2">
            <div class="col-3"></div>
            <div class="col-4">
                <nav aria-label="breadcrumb">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="/"><strong>Каталог</strong></a></li>
                        <li class="breadcrumb-item"><a href="/categories/${path[0]}"><strong>${path[0]}</strong></a></li>
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
                <div class="text-muted" id="products-found">
                    <small>Всего товаров: ${total}</small>
                </div>
                <div id="pageable">
                    <@p.pager url page/>
                </div>
            </div>
        </div>
        <div class="row mb-3">
            <div class="col-3 marker1 filters-col" style="margin-top: -9rem;">
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
                        <label class="btn btn-secondary" >
                            <input type="radio" name="sort_options" id="option2" value="highest" autocomplete="off"> Дороже
                        </label>
                        <label class="btn btn-secondary">
                            <input type="radio" name="sort_options" id="option3" value="alphabet"  autocomplete="off"> По алфавиту
                        </label>
                    </div>
                </div>
                <div class="card-group marker2" id="products"><#--<script>-->
                    <#if page?has_content>
                        <#list page.content as product>
                            <div class="card product-card">
                                <div class="view overlay">
                                    <#if product.originalPic??>
                                        <img class="img-fluid scale-pic" src="${product.originalPic}" alt="Фотографии пока нет">
                                        <a href="#">
                                            <div class="mask rgba-white-slight"></div>
                                        </a>
                                    </#if>
                                </div>
                                <div class="card-body">
                                    <h5 class="card-title">
                                        <a href="/products/info/${product.productID}">
                                            <strong>
                                                ${product.singleType}
                                                ${product.originalBrand?capitalize}
                                                ${product.modelName!''}
                                            </strong>
                                        </a>
                                    </h5>
                                    <p class="card-text">
                                        <#if product.productType??>
                                            <strong><i>${product.productType}</i></strong>
                                        <#else>
                                            <strong><i>${product.originalType}</i></strong>
                                        </#if>
                                    </p>
                                    <h3><strong>${product.finalPrice!''} ₽</strong></h3>
                                    <p>
                                        Бонус: ${product.bonus!''} ₽
                                    </p>
                                    <div>
                                        <#if product.isAvailable??>
                                            <#if orderedProductsID?? && orderedProductsID?seq_contains('${product.productID}')>
                                                <a type="button" class="btn btn-danger btn-md" style="background-color: #e52d00 !important;" href="http://localhost:8080/order">Оформить заказ</button></a>
                                            <#else>
                                                <div id="addToOrderDiv${product.productID?replace(".","")}">
                                                    <button type="submit" onclick="addToOrder(this)" class="btn btn-rounded btn-outline-danger b-add" name="addToOrder" id="addToOrder${product.productID}" value="${product.productID}">
                                                        В корзину
                                                    </button>
                                                </div>
                                            </#if>
                                        <#else>
                                            <button type="button" class="btn btn-warning btn-rounded" disabled>Нет в наличии!</button>
                                        </#if>
                                    </div>
                                </div>
                            </div>
                        </#list>
                    </#if>
                </div>
                <div id="pageable1">
                    <@p.pager url page/>
                </div>
            </div>
        </div>
    </div>
    <style>
        .btn-secondary {
            background-color: #6d7175 !important;
        }
    </style>
</@t.template>


