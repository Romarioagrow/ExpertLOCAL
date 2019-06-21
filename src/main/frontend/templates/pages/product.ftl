<#import "../parts/template.ftl" as t>
<@t.template>
    <div class="container">
        <div class="row mt-2">
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="/products/groups/${url}">${url}</a></li>
                    <li class="breadcrumb-item active" aria-current="page">${product.brand}</li>
                </ol>
            </nav>
        </div>
        <div class="row">
            <div class="col">
                <h2>${product.fullName}</h2>
            </div>
        </div>
        <div class="row">
            <div class="col-7">
                <#if product.pic??>
                    <div class="product-page-pic">
                        <div id="carousel-thumb" class="product-page-pic carousel slide carousel-fade carousel-thumbnails" data-ride="carousel">
                            <div class="carousel-inner" role="listbox">
                                <div class="carousel-item active">
                                    <img class="d-block scale-pic " src="${product.pic}" alt="First slide" style="max-height: 15rem !important;">
                                </div>
                                <div class="carousel-item">
                                    <img class="d-block scale-pic" src="${product.pic}" alt="Second slide" style="max-height: 15rem !important;">
                                </div>
                                <div class="carousel-item">
                                    <img class="d-block scale-pic" src="${product.pic}" alt="Third slide" style="max-height: 15rem !important;">
                                </div>
                                <a class="carousel-control-prev" href="#carousel-thumb" role="button" data-slide="prev">
                                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                    <span class="sr-only">Previous</span>
                                </a>
                            </div>
                            <a class="carousel-control-next" href="#carousel-thumb" role="button" data-slide="next">
                                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                <span class="sr-only">Next</span>
                            </a>
                            <ol class="carousel-indicators">
                                <li data-target="#carousel-thumb" data-slide-to="0" class="active">
                                    <img class="d-block scale-pic" src="${product.pic}" style="max-height: 5rem !important;">
                                </li>
                                <li data-target="#carousel-thumb" data-slide-to="1">
                                    <img class="d-block scale-pic" src="${product.pic}" style="max-height: 5rem !important;">
                                </li>
                                <li data-target="#carousel-thumb" data-slide-to="2">
                                    <img class="d-block scale-pic" src="${product.pic}" style="max-height: 5rem !important;">
                                </li>
                            </ol>
                        </div>
                    </div>
                </#if>
            </div>
            <div class="col price">
                <h2><strong>${product.price} ₽</strong></h2>
                <button type="button" class="btn btn-success waves-effect waves-light">Купить!</button>
                <#if orderedProductsID??>
                    <#if orderedProductsID?seq_contains('${product.productID}')>
                        <br><a type="button" class="btn btn-danger btn-md" style="background-color: #e52d00 !important;" href="http://localhost:8080/order">Оформить заказ</button></a>
                    <#else>
                        <div id="addToOrderDiv${product.productID}">
                            <button type="submit" onclick="addToOrder(this)" class="btn btn-rounded btn-outline-danger b-add" name="addToOrder" id="addToOrder${product.productID}" value="${product.productID}">
                                В корзину
                            </button>
                        </div>
                    </#if>
                <#else>
                    <div id="addToOrderDiv${product.productID}">
                        <button type="submit" onclick="addToOrder(this)" class="btn btn-rounded btn-outline-danger b-add" name="addToOrder" id="addToOrder${product.productID}" value="${product.productID}">
                            В корзину
                        </button>
                    </div>
                </#if>
            </div>
        </div>

        <div class="row">
            <div class="col">
                <hr>
                <h3>Параметры товара</h3>
                <table class="table table table-borderless w-30">
                    <thead>
                    <tr>${product.annotation}</tr>
                    </thead>
                    <tbody>
                    ${product.fullInfo}
                    <#--<#include "../parts/product-info.ftl">-->
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</@t.template>
<style>
    /*.product-page-pic {
        min-width: 20rem !important;
        max-width: 20rem !important;

        min-height: 5rem !important;
        max-height: 5rem !important;
    }

    .scale-pic {
        height: 100% !important;
        width: 100% !important;
        object-fit: contain !important;
    }*/
</style>
