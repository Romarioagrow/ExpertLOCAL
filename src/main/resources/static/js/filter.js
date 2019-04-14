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

    /// !!!
    $.ajax({
        url: url,
        type: 'POST',
        dataType: 'application/json',
        data: tv_data,
        processData: false,
        async: true,
        headers: {'Content-Type': 'application/json'},
        complete: function (products, status, http) {

            /*var data = $.parseJSON(products);
            alert(data);*/

            $('.products_block').html(products);

            alert("Response: " + products + " , Size: " + products.length + ", Status: " + products.status);

            /*if(status === "success")
                alert(http.status);
            $('#products_block').html(products);
            if(status === "error")
                alert("Error: " + http.status + ": " + http.statusText);*/
        }
    });
}
/*
/!*$('#products_block').html(jQuery(products).find(url).html());*!/
$('#test_div').html(products);
/!*$('#test_div').html(products);*!/
alert(xhr.status)*/
