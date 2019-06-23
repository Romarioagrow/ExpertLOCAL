$(document).ready(function(){
    $('#filter-button')         .on('click', collectFilters);
});
$(document).ready(function(){
    $('input[type="radio"]')    .on('change', collectFilters);
});
$(document).ready(function(){
    $('input[type="checkbox"]') .on('change', collectFilters);
});
$(document).ready(function(){
    $('input[type="text"]')     .on('keyup', collectFilters);
});

/*$(document).ready(function() {
    $('.mdb-select').materialSelect();
});*/

var url = document.URL;

function collectFilters(e) {
    e.preventDefault();

    var filters = constructFiltersData(url);
    deleteEmptyParams(filters);

    filters = JSON.stringify(filters);

    $.ajax({
        url: url/*+'?page=2'*/,
        type: 'POST',
        dataType: 'json',
        data: filters,
        processData: false,
        headers: {'Content-Type': 'application/json'},
        complete: function(productsAndOrderedID)
        {
            const response  = JSON.parse(JSON.stringify(productsAndOrderedID));
            const products  = response.responseJSON.content;
            const total     = response.responseJSON.totalElements;

            var currentPage = response.responseJSON.number;
            var nextPage = url+'?page='+currentPage+1;
            var prevPage = url+'?page='+currentPage-1;

            /*console.log(response);
            console.log(products);
            console.log(currentPage);*/

            $("#products").empty();

            $('#pageable').empty().append(
                '<ul class="pagination" style="margin-left: -0.80rem; margin-bottom: 0; margin-top: 1rem;">'+
                '<li class="page-item disabled">'+
                '<a class="page-link" href="#" tabindex="-1">Страницы</a></li>'+
                '<li class="page-item">'+
                '<a class="page-link" href="#/*'+prevPage+'*/">Назад</a></li>'+
                '<li class="page-item"><a class="page-link" href="#/*'+nextPage+'*/">Вперед</a></li></ul>'
            );
            $('#pageable1').empty().append(/*новый пагинатор*/);//html.load

            $('#products-found').empty().append(
                '<small>Всего товаров: '+total+'</small>'
            );

            for (var item in products)
            {
                let product = products[item];
                //let displayParams = resolveDisplayType(product);
                //let orderButton = resolveOrderButton(product, orderedID);

                var productCard = constructProductCard(product/*, displayParams, orderButton*/);

                $("#products").append(productCard); ///load html block
            }
        }
    });
}

function constructProductCard(product/*, displayParams, orderButton*/) {
    return '<div class="card product-card">'+
        '<div class="view overlay">'+
        /*<#if product.pic??>*/
        '<img class="img-fluid scale-pic" src="'+product.originalPic+'" alt="Product pic">'+
        '<a href="#">'+
        '<div class="mask rgba-white-slight"></div>'+
        '</a>'+
        /*</#if>*/
        '</div>'+
        '<div class="card-body">'+
        '<h5 class="card-title">'+
        '<a href="/products/info/'+product.productID+'">'+
        '<strong>'+
        product.originalName+
        '</strong>'+
        '</a>'+
        '</h5>'+
        '<p class="card-text">'+
        '<strong><i>'+product.originalType+'</i></strong>'+
        '</p>'+
        '<h3><strong>'+product.originalPrice+' ₽</strong></h3>'+
        '<div>'+
        '<button type="submit" onclick="addToOrder(this)" class="btn btn-rounded btn-outline-danger b-add" name="addToOrder" id="addToOrder${product.productID}" value="${product.productID}">'+
        'В корзину'+
        '</button>'+
        /*<#if orderedProductsID?? && orderedProductsID?seq_contains('${product.productID}')>
            <a type="button" class="btn btn-danger btn-md" style="background-color: #e52d00 !important;" href="http://localhost:8080/order">Оформить заказ</button></a>
        <#else>
        <div id="addToOrderDiv${product.productID}">
            <button type="submit" onclick="addToOrder(this)" class="btn btn-rounded btn-outline-danger b-add" name="addToOrder" id="addToOrder${product.productID}" value="${product.productID}">
            В корзину
        </button>
        </div>
        </#if>*/
        '</div>'+
        '</div>'+
        '</div>';
}

function resolveOrderButton(product, productsID) {
    if (productsID.includes(product.productID.toString())) {
        return '<br><a type="button" class="btn btn-danger btn-md" style="background-color: #e52d00 !important;" href="http://localhost:8080/order">Оформить заказ</button></a>';
    }
    else return '<div orderedID="addToOrderDiv'+product.productID.toString()+'">'+
        '<button type="submit" onclick="addToOrder(this)" class="btn btn-rounded btn-outline-danger b-add" modelName="addToOrder" orderedID="addToOrder'+product.productID+'" value="'+product.productID+'">'+
        'В корзину'+
        '</button>'+
        '</div>';
}

function constructFiltersData(url)
{
    var brand = [];
    $('input:checked').each(function()
    {
        if ($(this).attr('name') === 'brand') {
            brand.push($(this).val());
            console.log($(this).val());
        }
    });

    const data = {
        'sortmin' : $('#sortmin').val(),
        'sortmax' : $('#sortmax').val(),
        'brand'   : brand
    };

    if (selected(encodeURI("телевизоры"))) return collectTvFilters(data);
    //else if (selected("stoves"))      return collectStovesFilters(data);
    return data;

    function collectTvFilters(data)
    {
        console.log("tv filters");

        var tv_resolution = [], tv_params = [];
        ($('input:checked').each(function()
        {
            if      ($(this).is('[name="tv_resolution"]'))  tv_resolution.push(($(this).val()));
            else if ($(this).is('[name="tv_params"]'))      tv_params.push(($(this).val()));
        }));

        data.tvResolution = tv_resolution;
        data.tvParams = tv_params;
        data.diagMin = $('#diag_min').val();
        data.diagMax = $('#diag_max').val();

        console.log(data);
        return data;
    }
    /*function collectStovesFilters(data)
    {
        data.stoveDimensions = {'stove_width' : $('#stove_width').val()};
        return data;
    }*/
}

function resolveDisplayType(product) {
    if      (selected("телевизоры"))    return displayTV(product);
    else if (selected("stoves"))        return displayStoves(product);

    function displayTV(product) {
        return disp =
            'Диагональ: '            + '<strong>' + product.productParams.diagonal   + '</strong>' +
            '\nРазрешение: '         + '<strong>' + product.productParams.resolution + '</strong>' +
            '<br>'+'\nОсобенности: ' + '<strong>' + product.productParams.tvFeatures + '</strong>'
    }
    function displayStoves(product) {
        return disp = '\nГабариты: '   + '<strong>' + product.productParams.stoveDimensions + '</strong>'
    }
}

function deleteEmptyParams(filters)
{
    for (var fieldKey in filters)
    {
        if (filters[fieldKey] === '' || filters[fieldKey].length === 0)
        {
            delete filters[fieldKey];
        }
    }
}

function selected(type) {
    return url.includes(type);
}


