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

function collectFilters(e) {
    e.preventDefault();

    var url = document.URL; ///В Глобальную переменную
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
        complete: function (products)
        {
            var response = JSON.parse(JSON.stringify(products));

            $("#products").empty();
            console.log('Received products: ' + response.responseJSON.length);

            for (var item = 0; item < response.responseJSON.length; item++) {
                let product = response.responseJSON[item];
                ///ОСНОВНОЙ ШАБЛОН ОДИН или
                ///selectRequiredDisplayMethod(product, url);
                if (url.includes("tv"))             displayTV(product); //if(product("tv"))
                else if (url.includes("stoves"))    displayStoves(product);
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
    if (url.includes("tv"))     return collectTvFilters(data);
    if (url.includes("stoves")) return collectStovesFilters(data);
}
function deleteEmptyParams(filters) {
    for (var fieldKey in filters)
    {
        for (var attrKey in filters[fieldKey])
        {
            if (filters[fieldKey][attrKey] === '' || filters[fieldKey][attrKey].length === 0) {
                delete filters[fieldKey][attrKey];
            }
        }
        if (Object.getOwnPropertyNames(filters[fieldKey]).length === 0) {
            delete filters[fieldKey];
        }
    }
}

function collectTvFilters(data) {
    var tv_resolution = [], tv_params = [];
    ($('input[type="checkbox"]').each(function()
    {
        if ($(this).is(':checked') && ($(this).prop("name") === 'tv_resolution'))
            tv_resolution.push(($(this).val()));
        else if ($(this).is(':checked') && ($(this).prop("name") === 'tv_params'))
            tv_params.push(($(this).val()));
    }));
    data.displayParams  = {'diag_min' : ($('#diag_min').val()), 'diag_max' : ($('#diag_max').val()), 'tv_resolution' : tv_resolution};
    data.tvParams       = {'tv_params' : tv_params};
    return data;
}
function collectStovesFilters() {
}

/// ЗАМЕНИТЬ НА ОДИН УНИВЕРСАЛЬНЫЙ
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
        '<small>' + ///displayParams() + ///ИЗМЕНЕНИЯ ВСТАВЛЯТЬ СЮДА
        ///НАЧАЛО
        '\nДиагональ: '   + '<strong>' + product.productParams.diagonal + '</strong>' +
        '\nРазрешение: ' + '<strong>' + product.productParams.resolution + '</strong>' +
        '<br>' + '\nОсобенности: ' + '<strong>' + product.productParams.tvFeatures + '</strong>' +
        ///КОНЕЦ
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
