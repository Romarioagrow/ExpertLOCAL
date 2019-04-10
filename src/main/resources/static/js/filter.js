$(document).ready(function(){
    $('#filter-button').on('click', collectFilters);
});

function collectFilters(e) {
    e.preventDefault();

    var sortmin = $('#sortmin').val();
    var sortmax = $('#sortmax').val();
    var brand = $('#brand').val();
    var country = $('#country').val();

    var diag_min = $('#diag_min').val();
    var diag_max = $('#diag_max').val();
    var tv_resolution = [];
    var tv_params = [];

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

    //Указ контролеру какие товары наполнять
    /*var requiredType = $('#someFieldWithURL')*/

    var tv_data = {
        'sortmin':sortmin, 'sortmax': sortmax, 'brands': brand, 'country': country,
        'diag_min': diag_min, 'diag_max': diag_max, 'resolution': tv_resolution, 'params': tv_params
    };
    console.log(tv_data);
    tv_data = JSON.stringify(tv_data);

    var info = {'sortmin':sortmin, 'sortmax': sortmax, 'brand': brand, 'country': country};
    info = JSON.stringify(info);

    /// !!!
    $.ajax({
        url: 'http://localhost:8080/tv',
        type: 'POST',
        dataType: 'application/json',
        data: info,
        processData: false,
        headers: {'Content-Type': 'application/json'},
        success: function (response) {
            console.log(response);
        }
    })
}


