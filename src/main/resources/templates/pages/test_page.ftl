<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Expert Store</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <link rel="stylesheet" href="../main.css">
    <link rel="stylesheet" href="../service.css">
    <link rel="stylesheet" href="/static/css/main-menu.css">
</head>
<body>
<#include "../parts/navbar.ftl">
<div class="container-fluid">
    <div class="row">
        <div class="col-2 marker1">
            <div class="marker2">
                Фильтры для категории техники
            </div>
            <div class="marker2">
                <form id="main-filter" method="get">
                    <div>
                        <div class="form-group">
                            <label for="sortmin">Не дешевле:</label>
                            <br><input type="text" id="sortmin" name="sortmin">
                        </div>
                    </div>
                    <div>
                        <div class="form-group">
                            <br><label for="sortmax">Не дороже:</label>
                            <br><input type="text" id="sortmax" name="sortmax">
                        </div>
                    </div>
                    <div>
                        <div class="form-group">
                            <br><label for="brand">Производитель</label>
                            <br><input type="text" id="brand" name="brand">
                        </div>
                    </div>
                    <div>
                        <div class="form-group">
                            <br><label for="country">Страна</label>
                            <br><input type="text" id="country" name="country">
                        </div>
                    </div>
                    <br><div class="find-button"><input type="submit" value="Найти"></div>
                </form>
            </div>
        </div>
        <div class="col ml-2 marker1">
            <div class="marker2">
                Какая техника вас интересует?
            </div>
            <div id="sorting" class="marker2">
                <a href="?sortby=lowest" class="btn btn-light" role="button">Сначала дешевые</a>
                <a href="?sortby=highest" class="btn btn-light" role="button">Сначала дорогие</a>
                <a href="/" class="btn btn-light" role="button">Сбросить</a>
            </div>
            <div id="products" class="marker2">
                <#if products??>
                    <#list products as product>
                        <div class="card-deck">
                            <div class="card">
                                <#--<img src="#" class="card-img-top" alt="#">-->
                                <div class="card-body">
                                    <h6 class="card-title type">
                                        <strong>${product.type}</strong>
                                    </h6>
                                    <h5 class="card-title">
                                        ${product.brand}
                                        ${product.model}
                                    </h5>
                                    <p class="card-text">
                                        ${product.category}
                                        ${product.country}
                                    </p>
                                </div>
                                <div class="card-footer">
                                    <small class="text-muted">
                                        <strong><i>${product.price}</i></strong>
                                    </small>
                                </div>
                            </div>
                        </div>
                    </#list>
                <#else>
                    <#list productsWithParams as item>
                        <div class="card-deck">
                            <div class="card">
                                <#--<img src="#" class="card-img-top" alt="#">-->
                                <div class="card-body">
                                    <h6 class="card-title type">
                                        <strong>${item.product.type}</strong>
                                    </h6>
                                    <h5 class="card-title">
                                        ${item.product.brand}
                                        ${item.product.model}
                                    </h5>
                                    <p class="card-text">
                                        ${item.product.category}
                                        ${item.product.country}
                                    </p>
                                </div>
                                <div class="card-footer">
                                    <small class="text-muted">
                                        ${item.productParams.resolution}
                                        <strong><i>${item.product.price}</i></strong>
                                    </small>
                                </div>
                            </div>
                        </div>
                    </#list>
                </#if>
            </div>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
</body>
</html>


