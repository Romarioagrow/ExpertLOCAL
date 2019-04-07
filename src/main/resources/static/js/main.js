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

    var tv_data = {
        sortmin:sortmin, sortmax: sortmax, brands: brand, country: country,
        diag_min: diag_min, diag_max: diag_max, resolution: tv_resolution, params: tv_params
    };

    alert(tv_data.resolution);
    alert(tv_data.params);

    $.ajax({
        url: 'http://localhost:8080/tv',
        method: 'get',
        dataType: 'json',
        data: tv_data,
        success: alert('OK'),
        error: alert('Lol')
    })
}

/*var checked = [];*/
// Наполнение chexbox
/*$('input:checkbox:checked').each(function() {
    checked.push($(this).val());
});*/

/*var count = $(':checkbox:checked').length;*/

/*if (checked.length > 0) {
    /!*alert(count);*!/
    for (var i = 0; i < checked.length; i++) {
        /!*alert( 'параметры ' + checked[i] );*!/
    }
}*/

/*alert(sortmin);
alert(sortmax);
alert(brand);
alert(country);*/
/////////////////////////////////


/*$(document).ready(function(){
    $('#filter-button').on('click', f_showAlert);
});
function f_showAlert() {
    alert('loool');
}*/

/*const test_button = document.querySelector("#test_button");
test_button.addEventListener("click", makeRequest);

function makeRequest() {

    var checked = [];

    $('#from-test:checkbox:checked').each(function() {
        checked.push($(this).val());
    });*/

/*$('#test_button').click(function () {
    $('#result').html('');
    $('#from-test:checkbox:checked').each(function(){
        $('#result').append($(this).val() + '<br>');
    });
});*/


/*if ($('#720p').is(':checked')){
    alert('Включен');
} else {
    alert('Выключен');
}*/


/*var params ={'user_ids[]' : []};
$(":checked").each(function () {
    params['user_ids[]'].push($(this).val());
});
$.post("http://localhost:8080/mda", params);*/

/*alert( "Привет" );*/
/*$.ajax({
    type: "GET",
    url: "http://localhost:8080/mda",

})*/

