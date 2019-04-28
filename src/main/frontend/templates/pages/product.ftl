<#import "template.ftl" as t>
<@t.template>
    <div class="container">
        <div class="row mt-2">
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="/${url}">${product.productParams.type}</a></li>
                    <li class="breadcrumb-item active" aria-current="page">${product.brand} ${product.model}</li>
                </ol>
            </nav>
        </div>
        <div class="row">
            <div class="col">
                <h2>${product.productParams.type} ${product.brand} ${product.model}</h2>
            </div>
        </div>
        <div class="row">
            <div class="col-7">
                <#if product.pic??>
                    <div id="carousel-thumb" class="carousel slide carousel-fade carousel-thumbnails" data-ride="carousel">
                        <div class="carousel-inner" role="listbox">
                            <div class="carousel-item active">
                                <img class="d-block w-100" src="${product.pic}" alt="First slide">
                            </div>
                            <div class="carousel-item">
                                <img class="d-block w-100" src="${product.pic}" alt="Second slide">
                            </div>
                            <div class="carousel-item">
                                <img class="d-block w-100" src="${product.pic}" alt="Third slide">
                            </div>
                            <a class="carousel-control-prev" href="#carousel-thumb" role="button" data-slide="prev">
                                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                <span class="sr-only">Previous</span>
                            </a></div>

                        <a class="carousel-control-next" href="#carousel-thumb" role="button" data-slide="next">
                            <span class="carousel-control-next-icon" aria-hidden="true"></span>
                            <span class="sr-only">Next</span>
                        </a>
                        <ol class="carousel-indicators">
                            <li data-target="#carousel-thumb" data-slide-to="0" class="">
                                <img class="d-block w-100" src="${product.pic}">
                            </li>
                            <li data-target="#carousel-thumb" data-slide-to="1" class="active">
                                <img class="d-block w-100" src="${product.pic}">
                            </li>
                            <li data-target="#carousel-thumb" data-slide-to="2" class="">
                                <img class="d-block w-100" src="${product.pic}">
                            </li>
                        </ol>
                    </div>
                </#if>
            </div>
            <div class="col price">
                <h2><strong>${product.price} ₽</strong></h2>
                <button type="button" class="btn btn-success waves-effect waves-light">Купить!</button>
                <button type="button" class="btn btn-info waves-effect waves-light">В корзину</button>
            </div>
        </div>
        <hr>
        <div class="row">
            <div class="col">
                <h3>Параметры товара</h3>
                <table class="table table table-borderless w-30">
                    <thead>
                    <tr>
                        <th scope="col">Страна-производитель:</th>
                        <th scope="col">${product.country}</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#include "../parts/components/product-info.ftl">
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</@t.template>
