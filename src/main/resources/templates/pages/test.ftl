<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Expert Store</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <link rel="stylesheet" href="css/main.css">
    <link rel="stylesheet" href="css/service.css">
    <link rel="stylesheet" href="css/main-menu.css">
</head>
<body>
<#include "../parts/navbar.ftl">
<div class="container-fluid">
    <div class="row">
        <div class="col-3 marker1">
            <div class="marker2">
                <span>Фильтры для категории техники</span>
            </div>
            <div class="marker2">
                <form method="get">
                    <div class="main-filter">
                        <button class="btn btn-secondary btn-lg btn-block filter-button"
                                type="button"
                                data-toggle="collapse"
                                data-target="#filter1"
                                aria-expanded="false">
                            <span>Цена</span>
                        </button>
                        <div class="collapse" id="filter1">
                            <div class="card card-body filter-filed">
                                <div class="input-group mb-3">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text">От</span>
                                    </div>
                                    <input type="text" id="sortmin" name="sortmin" placeholder="₽" class="form-control" aria-label="Amount (to the nearest dollar)">
                                </div>
                                <div class="input-group">
                                    <input type="text" id="sortmax" name="sortmax" placeholder="₽" class="form-control" aria-label="Amount (to the nearest dollar)">
                                    <div class="input-group-append">
                                        <span class="input-group-text">До</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div>
                        <button class="btn btn-secondary btn-lg btn-block filter-button"
                                type="button"
                                data-toggle="collapse"
                                data-target="#filter2"
                                aria-expanded="true">
                            Страна и бренд
                        </button>
                        <div class="collapse" id="filter2">
                            <div class="card card-body filter-filed">
                                <div class="input-group mb-3">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="filter-country">Страна</span>
                                    </div>
                                    <input type="text" id="country" name="country" class="form-control" aria-label="Default" aria-describedby="filter-country">
                                </div>
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="filter-brand">Бренд</span>
                                    </div>
                                    <input type="text" id="brand" name="brand" class="form-control" aria-label="Default" aria-describedby="filter-brand">
                                </div>
                            </div>
                        </div>
                    </div>
                    <button type="submit" class="btn btn-primary btn-lg btn-block filter-button">Показать</button>
                </form>
            </div>
        </div>
        <div class="col marker3">
            <div class="pl-4vw marker2">
                <#if appliedFilters??>
                    <h5>Телевизоры${appliedFilters}</h5>
                <#else>
                    <h5>Все телевизоры</h5></#if>
            </div>
            <div class="pl-4vw mt-2 marker2" id="sorting" >
                <#--<a href="?sortby=lowest" class="btn btn-light" role="button">Сначала дешевые</a>
                <a href="?sortby=highest" class="btn btn-light" role="button">Сначала дорогие</a>-->
                <div class="btn-group btn-group-toggle" data-toggle="buttons">
                    <label class="btn btn-secondary active">
                        <input type="radio" name="options" id="option1" autocomplete="off" checked> Дешевле
                    </label>
                    <label class="btn btn-secondary">
                        <input type="radio" name="options" id="option2" autocomplete="off"> Дороже
                    </label>
                    <label class="btn btn-secondary">
                        <input type="radio" name="options" id="option3" autocomplete="off"> По алфавиту
                    </label>
                </div>
            </div>
            <div class="flex-container marker2">
                <#if products??>
                <#list products as product>
                    <div class="card mr-3 mt-3">
                        <img class="card-img-top" src="img/tv1.jpg" alt="Card image cap">
                        <div class="card-body">
                            <h5 class="card-title">
                                ${product.brand}
                                ${product.model}
                            </h5>
                            <p class="card-text">
                                ${product.category}
                                ${product.country}
                            </p>
                            <p class="card-text">
                                <small class="text-muted">
                                    ${product.subCategory}
                                    <#if product.productParams.diagonal??>диагональ ${product.productParams.diagonal}</#if>
                                    <#if product.productParams.resolution??>${product.productParams.resolution}</#if>
                                    <#if product.productParams.hasSmartTV??>SmartTV</#if>
                                    <#if product.productParams.hasWifi??>Wi-Fi</#if>
                                    <#if product.productParams.has3D??>3D</#if>
                                    <#if product.productParams.curvedScreen??>изогнутый экран</#if>
                                </small>
                            </p>
                        </div>
                        <div class="card-footer">
                            <small class="text-muted">
                                <strong><i>${product.price}</i></strong>
                            </small>
                        </div>
                    </div>
                </#list>
            </div>
            <#else>
                <i>Нет подходящих товаров :(</i>
            </#if>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
</body>
</html>

<style>
    .filter-button {
        margin-top: 1vh;
    }

    .filter-filed {
        margin-top: 1vh;
    }

    .flex-container {
        display: flex;
        flex-wrap: wrap;
        justify-content: center;
    }

    .card {
        min-width: 20vw;
        max-width: 20vw;
        text-align: center;
    }
</style>


