<#import "../parts/template.ftl" as t>
<@t.template>
    <div class="container" style="margin-top: 5rem">
        <div class="row mt-2">
            <nav aria-label="breadcrumb" style="padding-left: 1rem;margin-top: 1rem;">
                <#if product.productCategory??>
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="/categories/${product.productCategory?lower_case?replace(" ","_")}"><span style="font-size: 20px !important;">${product.productCategory}</a></li>
                        <li class="breadcrumb-item"><a href="/products/${product.productCategory?lower_case?replace(" ","_")}/${product.productGroup?lower_case}"><span style="font-size: 20px !important;">${product.productGroup}</a></li>
                        <li class="breadcrumb-item active" aria-current="page"><span style="font-size: 20px !important;">${product.groupBrand}</span></li>
                        <li class="breadcrumb-item active" aria-current="page"><span style="font-size: 20px !important;">${product.productType}</li>
                    </ol>
                <#else>
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="/categories/${product.originalCategory?keep_before(";")?lower_case?replace(" ","_")}"><span style="font-size: 20px !important;">${product.originalCategory?keep_before(";")}</a></li>
                        <li class="breadcrumb-item"><a href="/products/${product.originalCategory?keep_before(";")?lower_case?replace(" ","_")}/${product.originalType?lower_case?replace(" ","_")}"><span style="font-size: 20px !important;">${product.originalType}</a></li>
                    </ol>
                </#if>
            </nav>
        </div>
        <div class="row" style="margin-bottom: 1rem;">
            <div class="col">
                <h2>
                    <#if product.singleType??>
                        <span style="color: #e52d00;">${product.singleType}</span>
                        ${product.originalBrand?capitalize}
                        ${product.modelName}
                    <#else>
                        ${product.originalName?capitalize}
                    </#if>
                </h2>
            </div>
        </div>
        <div class="row">
            <div class="col-7" style="max-height: 30rem;">
                <div class="card" style="height: 100% !important; width: 100% !important; object-fit: contain !important;">
                    <div class="card-body" style="height: 100% !important; width: 100% !important; object-fit: contain !important;">
                        <#if product.originalPic??>
                            <a><img src="${product.originalPic}" id="image" onclick="im()" ondblclick="iii()" class="img-fluid scale-pic " alt="Responsive image" style="height: 100% !important; width: 100% !important; object-fit: contain !important;"></a>

                        <#else>
                            <img src="/../img/nophoto.jpg" class="img-fluid scale-pic" alt="Responsive image" style="height: 100% !important; width: 100% !important; object-fit: contain !important;">
                        </#if>
                    </div>
                </div>
            </div>
            <div class="col price">
                <div class="card">
                    <div class="card-body">
                        <div class="row">
                            <div class="col">
                                <h2><strong style="padding-left: 0.5rem;">${product.finalPrice} </strong><span style="font-size: .8rem">руб</span></h2>
                            </div>
                            <div class="col">
                                <#if product.isAvailable??>
                                    <p style="font-size: 1rem; padding-top: 0.5rem;">+<strong> ${product.getBonus()}</strong><i> баллов</i></p>
                                </#if>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <#if product.isAvailable??>
                                    <#if orderedProductsID?? && orderedProductsID?seq_contains('${product.productID}')>
                                        <a type="button" class="btn btn-danger btn-sm" style="background-color: #e52d00 !important;" href="http://localhost:8080/order">Оформить заказ</a>
                                    <#else>
                                        <div id="addToOrderDiv${product.productID?replace(".","")}">
                                            <button type="submit" onclick="addToOrder(this)" class="btn btn-rounded btn-outline-danger btn-sm b-add" name="addToOrder" id="addToOrder${product.productID}" value="${product.productID}">
                                                В корзину
                                            </button>
                                        </div>
                                    </#if>
                                <#else>
                                    <p style="color: #c40030; font-size: 1.5rem; padding-top: 0.5rem;"><strong>Нет в наличии!</strong></p>
                                </#if>
                            </div>
                            <div class="col">
                                <#if product.isAvailable??>
                                    <p style="color: #00c851; font-size: 1.5rem; padding-top: 0.5rem;"><strong>В наличии!</strong></p>
                                </#if>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row" style="margin-top: 2.5rem; width: auto !important;" >
            <div class="col">
                <div class="card" style="margin-bottom: 2rem">
                    <div class="card-body">
                        <h3 style="margin-bottom: 1rem">Характеристики товара</h3>
                        <#if anno??>
                            <div class="container">
                                <#list anno as key, val>
                                    <#if key?has_content>
                                        <div class="row">
                                            <div class="col">
                                                <h5><span>${key}</span></h5>
                                                <hr>
                                            </div>
                                            <div class="col">
                                                <span><strong>${val?cap_first}</strong></span>
                                            </div>
                                        </div>
                                    </#if>
                                </#list>
                            </div>
                        <#elseif listAnno??>
                            <ul class="list-group list-group-flush">
                                <#list listAnno as param>
                                    <li class="list-group-item"><strong>${param?lower_case?cap_first}</strong></li>
                                </#list>
                            </ul>
                        <#else>
                            <table class="table table table-borderless w-30">
                                <thead>
                                <tr>
                                    <#if product.originalAnnotation??>
                                        <strong>${product.originalAnnotation?replace(";","")!''}</strong>
                                    </#if>
                                </tr>
                                </thead>
                                <tbody>
                            </table>
                        </#if>
                    </div>
                </div>
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
