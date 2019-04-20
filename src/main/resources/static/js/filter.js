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
    console.log(filters);

    deleteNullParams(filters);

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
            console.log(products);

            var response = JSON.parse(JSON.stringify(products));

            console.log(response.responseJSON);

            $("#products").empty();

            console.log('Total products: ' + response.responseJSON.length);

            for (var i = 0; i < response.responseJSON.length; i++) {
                let product = response.responseJSON[i];
                if (url.includes("tv")) displayTV(product);
                if (url.includes("stoves")) displayStoves(product);
            }
        }
    });
}

function constructFiltersData(url) {
    var sortBy          = [($('input[name="sort_options"]:checked').val())];
    var sortmin         = [($('#sortmin').val())];
    var sortmax         = [($('#sortmax').val())];
    var brand           = [($('#brand').val())];
    var country         = [($('#country').val())];

    const data = {
        'sortBy': sortBy,
        'sortmin': sortmin, 'sortmax': sortmax,
        'brands': brand, 'country': country,
    };
    if (url.includes("tv")) return collectTvFilters(data);
    if (url.includes("stoves")) return collectStovesFilters(data);
}
function collectTvFilters(data) {
    var diag_min        = [($('#diag_min').val())];
    var diag_max        = [($('#diag_max').val())];
    var tv_resolution   = [];
    var tv_params       = [];
    $(document.getElementsByName('tv_resolution')).each(function() {
        if ($(this).is(':checked')) {
            tv_resolution.push($(this).val());
        }
    });
    $(document.getElementsByName('tv_params')).each(function() {
        if ($(this).is(':checked')) {
            tv_params.push($(this).val());
        }
    });
    data['diag_min'] = diag_min; data['diag_max'] = diag_max;
    data['resolution'] = tv_resolution; data['params'] = tv_params;
    return data;
}
function collectStovesFilters() {

}

function deleteNullParams(filters) {
    for (var key in filters) {
        if (filters[key][0] === '' || filters[key].length === 0) {
            delete filters[key];
        }
    }
    console.log(filters);
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
        '<small>' +
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
