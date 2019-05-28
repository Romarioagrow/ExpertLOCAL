$(document).ready(function(){
    $('#search-button')           .on('click', searchProducts);
});
$(document).ready(function(){
    $('input[name="main-search"]').on('keyup', searchProducts);
});
$('body').click(function(){
    /// if элеиентПодСтрелкой.name != search
    document.getElementById("display-result").style.display = "none";
});

function searchProducts(e) {
    e.preventDefault();

    var searchRequest = ($('#main-search').val()).trim().toUpperCase();
    console.log(searchRequest);

    $.ajax({
        url: 'http://localhost:8080/search',
        type: 'POST',
        dataType: 'json',
        data: searchRequest,
        processData: false,
        headers: {'Content-Type': 'application/json'},
        complete: function(products)
        {
            const response = JSON.parse(JSON.stringify(products));

            document.getElementById("display-result").style.display = "block";
            $("#display-result").empty();

            console.log('Found products: ' + response.responseJSON.length);

            response.responseJSON.forEach(product => $("#display-result").append
            (
                '<div class="one-product" style="margin-top: 3px!important;">'                  +
                    '<a href="http://localhost:8080/products/info/'+ product.productID +'">'          +
                        product.productParams.type + " " + product.brand + " " + product.model  +
                    '</a>'                                                                      +
                '</div>'
            ));
        }
    });
}

