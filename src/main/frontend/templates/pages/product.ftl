<#import "../parts/template.ftl" as t>
<@t.template>
    <div class="container" style="margin-top: 5rem">
        <div class="row mt-2">
            <nav aria-label="breadcrumb" style="padding-left: 1rem;margin-top: 1rem;">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="/products/${product.productCategory?lower_case?replace(" ","_")}/${product.productGroup?lower_case}"><span style="font-size: 20px !important;">${product.productGroup}</a></li>
                    <li class="breadcrumb-item active" aria-current="page"><span style="font-size: 20px !important;">${product.groupBrand}</span></li>
                    <li class="breadcrumb-item active" aria-current="page"><span style="font-size: 20px !important;">${product.productType}</li>
                </ol>
            </nav>
        </div>
        <div class="row" style="margin-bottom: 1rem;">
            <div class="col">
                <h2>
                    <span style="color: #e52d00;">${product.singleType}</span>
                    ${product.originalBrand?capitalize}
                    ${product.modelName}
                </h2>
            </div>
        </div>
        <div class="row">
            <div class="col-7" style="max-height: 30rem;">
                <div class="card" style="height: 100% !important; width: 100% !important; object-fit: contain !important;">
                    <div class="card-body" style="height: 100% !important; width: 100% !important; object-fit: contain !important;">
                        <#if product.originalPic??>
                            <img src="${product.originalPic}" class="img-fluid scale-pic" alt="Responsive image" style="height: 100% !important; width: 100% !important; object-fit: contain !important;">
                        <#else>
                            <h5>Изображение отсутствует :(</h5>
                        </#if>
                    </div>
                </div>
            </div>
            <div class="col price">
                <div class="card">
                    <div class="card-body">
                        <div class="row">
                            <div class="col">
                                <h2><strong style="padding-left: 0.5rem;">${product.finalPrice} ₽</strong></h2>
                            </div>
                            <div class="col">
                                <#if product.isAvailable!>
                                    <p style="font-size: 1rem; padding-top: 0.5rem;">+<strong> ${product.getBonus()}</strong><i> баллов</i></p>
                                </#if>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <#if product.isAvailable!>
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
                                    <p style="color: #c40030; font-size: 1.5rem; padding-top: 0.5rem;"><strong>Нет в наличии!</strong></p>
                                </#if>
                            </div>
                            <div class="col">
                                <#if product.isAvailable!>
                                    <p style="color: #00c851; font-size: 1.5rem; padding-top: 0.5rem;"><strong>В наличии!</strong></p>
                                </#if>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row" style="margin-top: 2.5rem">
            <div class="col">
                <div class="card" style="margin-bottom: 2rem">
                    <div class="card-body">
                        <h3>Параметры товара</h3>
                        <hr>
                        <table class="table table table-borderless w-30">
                            <thead>
                            <tr>
                                <#if product.formattedAnnotation?has_content>
                                    ${product.formattedAnnotation}
                                <#else>
                                    ${product.originalAnnotation!''}
                                </#if>
                            </tr>
                            </thead>
                            <tbody>
                    </div>
                </div>
                <#--${product.fullInfo}
                &lt;#&ndash;<#include "../parts/product-info.ftl">&ndash;&gt;
                </tbody>-->
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
