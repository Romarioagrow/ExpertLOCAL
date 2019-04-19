$(document).ready(function(){
    $('#filter-button').on('click', collectFilters);
});

function collectFilters(e) {
    e.preventDefault();

    /// методы наполнения переменных взависимости от типа товара
    /// function collectTvFilters, collectFridgesFilters и тд
    var sortmin         = [];
    var sortmax         = [];
    var brand           = [];
    var country         = [];
    var diag_min        = [];
    var diag_max        = [];
    var tv_resolution   = [];
    var tv_params       = [];

    sortmin.push    ($('#sortmin').val());
    sortmax.push    ($('#sortmax').val());
    brand.push      ($('#brand').val());
    country.push    ($('#country').val());
    diag_min.push   ($('#diag_min').val());
    diag_max.push   ($('#diag_max').val());

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

    var tv_data = {
        'sortmin':sortmin, 'sortmax': sortmax,
        'brands': brand, 'country': country,
        'diag_min': diag_min, 'diag_max': diag_max,
        'resolution': tv_resolution, 'params': tv_params
    };
    console.log(tv_data);

    if (tv_data.sortmin[0]  === '') delete tv_data.sortmin;
    if (tv_data.sortmax[0]  === '') delete tv_data.sortmax;
    if (tv_data.brands[0]   === '') delete tv_data.brands;
    if (tv_data.country[0]  === '') delete tv_data.country;
    if (tv_data.diag_min[0] === '' || tv_data.diag_min[0] === undefined) delete tv_data.diag_min;
    if (tv_data.diag_max[0] === '' || tv_data.diag_max[0] === undefined) delete tv_data.diag_max;
    if (tv_data.resolution.length === 0) delete tv_data.resolution;
    if (tv_data.params.length     === 0) delete tv_data.params;

    tv_data = JSON.stringify(tv_data);
    console.log(tv_data);

    var url = document.URL;
    console.log(url);

    $.ajax({
        url: url,
        type: 'POST',
        dataType: 'json',
        data: tv_data,
        processData: false,
        async: true,
        headers: {'Content-Type': 'application/json'},
        complete: function (products)
        {
            console.log("Products:");
            console.log(products);

            var data = [];
            data= JSON.stringify(products);
            var response = JSON.parse(data);
            console.log("JSON.parse:");
            console.log(response);

            console.log("responseJSON:");
            console.log(response.responseJSON);
            $("#result").empty();
            $("#prod").empty();

            console.log('Total products: ' + response.responseJSON.length);
            for (var i = 0; i < response.responseJSON.length; i++) {
                console.log('Product ' + (i+1) +
                    '\nID: ' + response.responseJSON[i].product_id + ' ' +
                    '\nBrand: ' + response.responseJSON[i].brand + ' ' +
                    '\nModel: ' + response.responseJSON[i].model + ' ' +
                    '\nPrice: ' + response.responseJSON[i].price + ' ' +
                    '\nDiagonal: ' + response.responseJSON[i].productParams.diagonal + ' ' +
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
                            '</h5>' +
                            '<p class="card-text">' +
                                '\n' + response.responseJSON[i].country +
                            '</p>' +
                            '<p class="card-text">' +
                                '<small class="text-muted">' +
                                    '\nDiagonal: ' + response.responseJSON[i].productParams.diagonal +
                                    '\nResolution: ' + response.responseJSON[i].productParams.resolution +
                                    '\ntvFeatures: ' + response.responseJSON[i].productParams.tvFeatures +
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
