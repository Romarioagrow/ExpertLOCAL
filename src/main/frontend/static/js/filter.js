$(document).ready(function(){
    $('#filter-button')         .on('click', collectFilters);
});
$(document).ready(function(){
    $('input[type="radio"]')    .on('change', collectFilters); ///ОБЪЕДЕНИТЬ ДЛЯ ВСЕХ INPUT
});
$(document).ready(function(){
    $('input[type="checkbox"]') .on('change', collectFilters);
});
$(document).ready(function(){
    $('input[type="text"]')     .on('keyup', collectFilters);
});
$(document).ready(function() {
    $('.mdb-select').materialSelect();
});

var url = document.URL;

function collectFilters(e) {
    e.preventDefault();
    console.log('Current URL: ' + url);

    var filters = constructFiltersData(url);
    deleteEmptyParams(filters);

    filters = JSON.stringify(filters);

    $.ajax({
        url: url,
        type: 'POST',
        dataType: 'json',
        data: filters,
        processData: false,
        headers: {'Content-Type': 'application/json'},
        complete: function(products)
        {
            const response = JSON.parse(JSON.stringify(products));

            $("#products").empty();
            console.log('Received products: ' + response.responseJSON.length);

            for (var item = 0; item < response.responseJSON.length; item++) { ///ЗАМЕНИТЬ НА =>
                let product = response.responseJSON[item];
                let display = resolveDisplayType(product);

                $("#products").append
                (
                    '<div class="card product-card mr-3 mt-3">'                                 +
                        '<img class="card-img-top" src='+product.pic+' alt="Card image cap">'   +
                        '<div class="card-body">'                                               +
                            '<h5 class="card-title">'                                           +
                                '\n' + product.brand                                            +
                                '\n' + product.model                                            +
                            '</h5>'                                                             +
                            '<p class="card-text">'                                             +
                                '\n' + product.country                                          +
                            '</p>'                                                              +
                            '<p class="card-text">'                                             +
                                '<small>'                                                       +
                                    display                                                     +
                                '</small>'                                                      +
                            '</p>'                                                              +
                        '</div>'                                                                +
                        '<div class="card-footer">'                                             +
                            '<small class="text-muted">'                                        +
                                '<a class="btn btn-outline-info btn-rounded waves-effect" role="button" href="/product/'    + product.productID.toString() + '">О товаре</a>'     +
                                '<strong><i>' + product.price + '</i></strong>'                                             +
                                '<button type="button" class="btn btn-flat about_product click-right">В корзину</button>'   +
                            '</small>'                                                                                      +
                        '</div>'                                                                                            +
                    '</div>'
                );
            }
        }
    });
}

function constructFiltersData(url) {
    var country = [], brand = [];
    ($('option:checked').each(function() {
        if ($(this).is('[name="country"]')) country.push(($(this).val()));
        if ($(this).is('[name="brand"]'))   brand.push(($(this).val()));
    }));

    const data = {
        'sortBy'        : {'sortOrder'  : ($('input[name="sort_options"]:checked').val())},
        'price'         : {'sortmin'    : ($('#sortmin').val()),    'sortmax' : ($('#sortmax').val())},
        'manufacturer'  : {'brand'      : brand,      'country' : country},
    };

    if      (selected("tv"))     return collectTvFilters(data);
    else if (selected("stoves")) return collectStovesFilters(data);

    function collectTvFilters(data) {
        var tv_resolution = [], tv_params = [];
        ($('input:checked').each(function()
        {
            if      ($(this).is('[name="tv_resolution"]'))  tv_resolution.push(($(this).val()));
            else if ($(this).is('[name="tv_params"]'))      tv_params.push(($(this).val()));
        }));
        data.displayParams  = {'diag_min' : ($('#diag_min').val()), 'diag_max' : ($('#diag_max').val()), 'tv_resolution' : tv_resolution};
        data.tvParams       = {'tv_params' : tv_params};
        return data;
    }
    function collectStovesFilters(data) {
        data.stoveDimensions = {'stove_width' : $('#stove_width').val()};
        return data;
    }
}

function resolveDisplayType(product) {
    if      (selected("tv"))     return displayTV(product);
    else if (selected("stoves")) return displayStoves(product);

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

function deleteEmptyParams(filters) {
    for (var fieldKey in filters) {
        for (var attrKey in filters[fieldKey]) {
            if (filters[fieldKey][attrKey] === '' || filters[fieldKey][attrKey].length === 0) {
                delete filters[fieldKey][attrKey];
            }
        }
        if (Object.getOwnPropertyNames(filters[fieldKey]).length === 0) {
            delete filters[fieldKey];
        }
    }
}

function selected(type) {
    return url.includes(type);
}


