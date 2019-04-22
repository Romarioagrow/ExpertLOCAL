$(document).ready(function(){
    $('#filter-button').on('click', collectFilters);
});
$(document).ready(function(){
    $('input[type="radio"]').on('change', collectFilters);
});
$(document).ready(function(){
    $('input[type="checkbox"]').on('change', collectFilters);
});
$(document).ready(function(){
    $('input[type="text"]').on('keyup', collectFilters);
});

function collectFilters(e) {
    e.preventDefault();

    var url = document.URL;
    console.log(url);

    var filters = constructFiltersData(url);

    deleteNullParams(filters);
    //console.log(filters);

    filters = JSON.stringify(filters);

    $.ajax({
        url: url,
        type: 'POST',
        dataType: 'json',
        data: filters,
        processData: false,
        async: true,
        headers: {'Content-Type': 'application/json'},
        complete: function (products)
        {
            var response = JSON.parse(JSON.stringify(products));

            $("#products").empty();

            //console.log('Total products: ' + response.responseJSON.length);

            for (var i = 0; i < response.responseJSON.length; i++) {
                let product = response.responseJSON[i];
                if (url.includes("tv"))     displayTV(product); //if(product("tv"))
                if (url.includes("stoves")) displayStoves(product);
            }
        }
    });
}

function constructFiltersData(url) {
    const data = {
        'sortBy'        : {'sortOrder'  : ($('input[name="sort_options"]:checked').val())},
        'price'         : {'sortmin'    : ($('#sortmin').val()),    'sortmax' : ($('#sortmax').val())},
        'manufacturer'  : {'brand'      : ($('#brand').val()),      'country' : ($('#country').val())},
    };
    if (url.includes("tv")) return collectTvFilters(data);
    //if (url.includes("stoves")) return collectStovesFilters(data);
}

function collectTvFilters(data) {
    var tv_resolution = [], tv_params = [];
    ($(document.getElementsByName('tv_resolution')).each(function() {
        if ($(this).is(':checked')) {
            tv_resolution.push(($(this).val()));
        }
    }));
    $(document.getElementsByName('tv_params')).each(function() {
        if ($(this).is(':checked')) {
            tv_params.push(($(this).val()));
        }
    });
    data['displayParams'] = {
        'diag_min'      : ($('#diag_min').val()),
        'diag_max'      : ($('#diag_max').val()),
        'tv_resolution' : tv_resolution
    };
    data['tvParams'] = {
        'tv_params' : tv_params
    };
    return data;
}

function collectStovesFilters() {
}

function deleteNullParams(filters) {
    for (var firstKey in filters)
    {
        for (var innerKey in filters[firstKey])
        {
            if (filters[firstKey][innerKey] === '' || (Array.isArray(filters[firstKey][innerKey]) && filters[firstKey][innerKey].length === 0)) {
                delete filters[firstKey][innerKey];
            }
        }
        if (Object.getOwnPropertyNames(filters[firstKey]).length === 0) {
            delete filters[firstKey];
        }
    }
}

function displayTV(product) {
    $("#products").append(
        '<div class="card product-card mr-3 mt-3">'+
        '<img class="card-img-top" src='+product.pic+' alt="Card image cap">' +
        '<div class="card-body">'+
        '<h5 class="card-title">'+
        '\n' + product.brand +
        '\n' + product.model +
        '</h5>'  +
        '<p class="card-text">' +
        '\n' + product.country +
        '</p>' +
        '<p class="card-text">' +
        '<small>' + //showParams() +
        '\nДиагональ: '   + '<strong>' + product.productParams.diagonal + '</strong>' +
        '\nРазрешение: ' + '<strong>' + product.productParams.resolution + '</strong>' +
        '<br>' + '\nОсобенности: ' + '<strong>' + product.productParams.tvFeatures + '</strong>' +
        '</small>' +
        '</p>' +
        '</div>' +
        '<div class="card-footer">' +
        '<small class="text-muted">' +
        '<strong><i>' + product.price + '</i></strong>' +
        '</small>' +
        '</div>' +
        '</div>');
}
function displayStoves(product) {
    $("#products").append(
        '<div class="card product-card mr-3 mt-3">'+
        '<img class="card-img-top" src='+product.pic+' alt="Card image cap">' +
        '<div class="card-body">'+
        '<h5 class="card-title">'+
        '\n' + product.brand +
        '\n' + product.model +
        '</h5>'  +
        '<p class="card-text">' +
        '\n' + product.country +
        '</p>' +
        '<p class="card-text">' +
        '<small>' +
        '\nГабариты: '   + '<strong>' + product.productParams.stoveDimensions + '</strong>' +
        '</small>' +
        '</p>' +
        '</div>' +
        '<div class="card-footer">' +
        '<small class="text-muted">' +
        '<strong><i>' + product.price + '</i></strong>' +
        '</small>' +
        '</div>' +
        '</div>');
}
