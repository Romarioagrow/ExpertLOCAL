<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Expert Store</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="../css/main.css">
    <link rel="stylesheet" href="../css/service.css">
    <link rel="stylesheet" href="../css/main-menu.css">
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
                <form method="get" name="filters" id="filters">
                    <#include "../parts/filters/all-filters.ftl">
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
<script src="../lib/jquery-3.3.1.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<script src="../js/main.js"></script>
</body>
</html>


