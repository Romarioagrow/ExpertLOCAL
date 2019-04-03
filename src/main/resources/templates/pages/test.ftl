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
<#include "../parts/components/navbar.ftl">
<div class="container-fluid">
    <div class="row">
        <div class="col-3 marker1">
            <div class="marker2">
                <#if currentProduct??><span>Фильтры для ${currentProduct}</span></#if>
            </div>
            <div class="marker2">
                <form method="get">
                    <#include "../parts/filters/main-filter.ftl">
                    <#if showTV??><#include "../parts/filters/tv-filter.ftl"></#if>

                    <button type="submit" class="btn btn-primary btn-block filter-button">Показать</button>
                </form>
            </div>
        </div>
        <div class="col marker3">
            <div>
                <div class="pl-4vw marker2">
                    <#if appliedFilters??>
                        <h5>${currentProduct} ${appliedFilters}</h5>
                    <#else>
                    <#if currentProduct??><h5>${currentProduct}</h5></#if></#if>
                </div>
                <div>
                    <#--подкатегории для товаров-->
                </div>
            </div>
            <#include "../parts/components/products.ftl">
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
    .product-card {
        min-width: 20vw;
        max-width: 20vw;
        text-align: center;
    }
</style>


