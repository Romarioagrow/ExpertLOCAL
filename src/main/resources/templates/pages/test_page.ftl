<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Expert Store</title>
    <!-- Плотные шрифты-->
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!--Bootstrap и CSS-->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <link rel="stylesheet" href="../main.css">
    <link rel="stylesheet" href="../service.css">
</head>
<body>
<#include "../parts/navbar.ftl">
<#include "../parts/components/about.ftl">
<div class="nested">
    <div class="m2">
        <div class="sorting m3" id="sorting">
            <a href="?sortby=lowest" class="btn btn-light" role="button">Сначала дешевые</a>
            <a href="?sortby=highest" class="btn btn-light" role="button">Сначала дорогие</a>
            <a href="/" class="btn btn-light" role="button">Сбросить</a>
        </div>
        <div class="container">
            <#list productsWithParams as bigproduct>
                <div class="card-deck">
                    <div class="card">
                        <#--<img src="#" class="card-img-top" alt="#">-->
                        <div class="card-body">
                            <h6 class="card-title type">
                                <strong>${bigproduct.product.type}</strong>
                            </h6>
                            <h5 class="card-title">
                                ${bigproduct.product.type}
                                ${bigproduct.product.model}
                            </h5>
                            <p class="card-text">
                                ${bigproduct.product.category}
                                ${bigproduct.product.country}
                                ${bigproduct.productParams.resolutionType}
                            </p>
                        </div>
                        <div class="card-footer">
                            <small class="text-muted">
                                <strong><i>${bigproduct.product.price}</i></strong>
                            </small>
                        </div>
                    </div>
                </div>
            </#list>
        </div>
    </div>

</div>
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
</body>
</html>