$(document).ready(function(){
    $('#filter-button').on('click', collectFilters);
});
$(document).ready(function(){
    $('input[name="sort_options"]').on('change', collectFilters);
});
$(document).ready(function(){
    $('input[name="tv_resolution"]').on('change', collectFilters);
});
$(document).ready(function(){
    $('input[name="tv_params"]').on('change', collectFilters);
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
            console.log("Products:");
            console.log(products);

            /*var data = [];
            data = JSON.stringify(products);*/
            var response = JSON.parse(JSON.stringify(products));
            console.log("JSON.parse:");
            console.log(response);

            console.log("responseJSON:");
            console.log(response.responseJSON);
            $("#result").empty();
            $("#prod").empty();

            console.log('Total products: ' + response.responseJSON.length);
            for (var i = 0; i < response.responseJSON.length; i++) {
                console.log('Product ' + (i+1) +
                    '\nID: '         + response.responseJSON[i].product_id + ' ' +
                    '\nBrand: '      + response.responseJSON[i].brand + ' ' +
                    '\nModel: '      + response.responseJSON[i].model + ' ' +
                    '\nPrice: '      + response.responseJSON[i].price + ' ' +
                    '\nDiagonal: '   + response.responseJSON[i].productParams.diagonal + ' ' +
                    '\nResolution: ' + response.responseJSON[i].productParams.resolution + ' ' +
                    '\ntvFeatures: ' + response.responseJSON[i].productParams.tvFeatures + '\n'
                );

                $("#result").append(
                    '<div class="card product-card mr-3 mt-3">'+
                    '<img class="card-img-top" src="../img/tv1.jpg" alt="Card image cap">' +
                    '<div class="card-body">'+
                    '<h5 class="card-title">'+
                    '\n' + response.responseJSON[i].brand +
                    '\n' + response.responseJSON[i].model +
                    '</h5>'  +
                    '<p class="card-text">' +
                    '\n' + response.responseJSON[i].country +
                    '</p>' +
                    '<p class="card-text">' +
                    '<small>' +
                    '\nДиагональ: '   + '<strong>' + response.responseJSON[i].productParams.diagonal + '</strong>' +
                    '\nРазрешение: ' + '<strong>' + response.responseJSON[i].productParams.resolution + '</strong>' +
                     '<br>' + '\nОсобенности: ' + '<strong>' + response.responseJSON[i].productParams.tvFeatures + '</strong>' +
                    '</small>' +
                    '</p>' +
                    '</div>' +
                    '<div class="card-footer">' +
                    '<small class="text-muted">' +
                    '<strong><i>' + response.responseJSON[i].price + '</i></strong>' +
                    '</small>' +
                    '</div>' +
                    '</div>');
            }
        }
    });
}

function constructFiltersData(url) {
    if (url.includes("tv")) return collectTvFilters();
}

function collectTvFilters() {
    var sortBy          = [($('input[name="sort_options"]:checked').val())];
    var sortmin         = [($('#sortmin').val())];
    var sortmax         = [($('#sortmax').val())];
    var brand           = [($('#brand').val())];
    var country         = [($('#country').val())];
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

    return {
        'sortBy': sortBy,
        'sortmin': sortmin, 'sortmax': sortmax,
        'brands': brand, 'country': country,
        'diag_min': diag_min, 'diag_max': diag_max,
        'resolution': tv_resolution, 'params': tv_params
    };
}

function deleteNullParams(filters) {
    for (var key in filters) {
        if (filters[key][0] === '' || filters[key].length === 0) {
            delete filters[key];
        }
    }
    console.log(filters);
}


