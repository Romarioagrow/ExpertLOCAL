<#import "../parts/template.ftl" as t>
<#import "../parts/pager.ftl" as p>

<@t.template>
    <div class="container-fluid">
        <div class="row" style="margin-top: 6rem; margin-left: 27.5rem;">
            <div class="col">
                <nav aria-label="breadcrumb">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="/"><strong>Каталог</strong></a></li>
                        <li class="breadcrumb-item"><a href="/categories/${path[0]?lower_case}"><strong>${path[0]?replace("_"," ")}</strong></a></li>
                        <li class="breadcrumb-item disabled">${path[1]}</li>
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
                    <button class="btn btn-block filter-button"
                            type="button" data-toggle="collapse" data-target="#filter1" aria-expanded="false"><span>Цена и бренды</span>
                    </button>
                    <div class="collapse show" id="filter1">
                        <div class="card card-body filter-filed">
                            <div class="md-form input-group">
                                <div class="input-group-prepend">
                                    <span class="input-group-text md-addon">Цена</span>
                                </div>
                                <div class="row">
                                    <div class="col-5">
                                        <input type="text" onkeyup="filterProducts()" class="form-control" id="sortmin" placeholder="От <#if price??>${price[0]!''}</#if> руб">
                                    </div>
                                    <div class="col-5">
                                        <input type="text" onkeyup="filterProducts()" class="form-control" id="sortmax" placeholder="До <#if price??>${price[1]!''}</#if> руб">
                                    </div>
                                </div>
                            </div>
                            <div class="md-form input-group">
                                <div class="input-group-prepend">
                                    <span class="input-group-text md-addon">Бренды</span>
                                </div>
                                <div class="form-check">
                                    <div class="container" id="brands-view">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <form method="get" name="filters" id="filters">
                        <button class="btn  btn-block filter-button" id="featButton" type="button" data-toggle="collapse" data-target="#feat1" aria-expanded="false" style="display: none">
                            Особенности
                        </button>
                        <div class="collapse" id="feat1">
                            <div class="card card-body filter-filed">
                                <div class="form-group" id="feat-element">
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="col-9 marker3">
                <div class="mt-2 marker2">
                    <div class="btn-group btn-group-toggle" data-toggle="buttons" id="sorting">
                        <label class="btn btn-secondary btn-sm">
                            <input type="radio" onchange="filterProducts()" name="sort_options" id="option1" value="lowest" autocomplete="off" checked> Дешевле
                        </label>
                        <label class="btn btn-secondary btn-sm" >
                            <input type="radio" onchange="filterProducts()" name="sort_options" id="option2" value="highest" autocomplete="off"> Дороже
                        </label>
                        <label class="btn btn-secondary btn-sm">
                            <input type="radio" onchange="filterProducts()" name="sort_options" id="option3" value="alphabet"  autocomplete="off"> По алфавиту
                        </label>
                    </div>
                </div>
                <div class="card-group marker2" id="products"><#--<script>-->
                    <#if page?has_content>
                        <#list page.content as product>
                            <div class="card product-card">
                                <div class="view overlay" style="min-height: 8rem !important; max-height: 8rem !important;">
                                    <#if product.originalPic??>
                                        <img class="img-fluid scale-pic" src="<#if product.originalPic??>${product.originalPic}<#else>/../img/nophoto.jpg<#--${product.originalPic}--></#if>" alt="Фотографии пока нет :(">
                                    <#--<a href="#" onclick="alertPic()">
                                        <div class="mask rgba-white-slight"></div>
                                    </a>-->
                                    <#else>
                                        <img class="img-fluid scale-pic" src="/../img/nophoto.jpg" alt="Фотографии пока нет">
                                    </#if>
                                </div>
                                <div class="card-body">
                                    <h5 class="card-title" style="height: auto !important;/*min-height: 3rem !important; max-height: 3rem !important;*/">
                                        <a href="/products/info/${product.productID?replace("\\","_")}">
                                            <#if product.productGroup??>
                                                <strong style="font-size: 1rem">
                                                    <span style="color: #e52d00;">${product.singleType!''}</span>
                                                    <span style="color: #222222;">${product.originalBrand?capitalize}</span>
                                                    <span style="color: #222222;">${product.modelName!''}</span>
                                                </strong>
                                            <#else>
                                                <span style="color: #222222;">${product.originalName!''}</span>
                                            </#if>
                                        </a>
                                    </h5>
                                    <p class="card-text" style="margin-top: 1rem">
                                        <#if product.productType??>
                                            <strong><i>${product.productType}</i></strong>
                                            <#else>
                                                <strong><i>${product.originalGroup}</i></strong>
                                        </#if>
                                    </p>
                                    <h3><strong>${product.finalPrice!''} </strong><span style="font-size: .9rem">руб</span></h3>
                                    <p>
                                        Бонус: ${product.bonus!''}
                                    </p>
                                    <div>
                                        <#if product.isAvailable!>
                                            <#if orderedProductsID?? && orderedProductsID?seq_contains('${product.productID}')>
                                                <a type="button" class="btn btn-danger btn-sm" style="background-color: #e52d00 !important;" href="http://localhost:8080/order">Оформить заказ</button></a>
                                            <#else>
                                                <div id="addToOrderDiv${product.productID?replace(".","")}">
                                                    <button type="submit" onclick="addToOrder(this)" class="btn btn-rounded btn-outline-danger btn-sm b-add" name="addToOrder" id="addToOrder${product.productID}" value="${product.productID}">
                                                        В корзину
                                                    </button>
                                                </div>
                                            </#if>
                                        <#else>
                                        <#--<button type="button" class="btn btn-warning btn-rounded" disabled>Нет в наличии!</button>-->
                                            <p style="color: #c40030; font-size: 1.5rem; padding-top: 0.5rem;"><strong>Нет в наличии!</strong></p>
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


