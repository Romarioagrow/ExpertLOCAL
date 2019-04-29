$(document).ready(function(){
    $('#search-button')           .on('click', searchProducts);
});
$(document).ready(function(){
    $('input[name="main-search"]').on('keyup', searchProducts);
});
$('body').click(function(evt){
    document.getElementById("display-result").style.display = "none";
});

function searchProducts(e) {
    e.preventDefault();

    var searchRequest = ($('#main-search').val());

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

            console.log('Received products: ' + response.responseJSON.length);
            for (var item = 0; item < response.responseJSON.length; item++) { ///ЗАМЕНИТЬ НА =>
                let product = response.responseJSON[item];
                $("#display-result").append
                (
                '<div class="one-product" style="margin-top: 3px!important;">'                  +
                    '<a href="http://localhost:8080/product/'+ product.productID +'" style="font-weight: bold;">'     +
                        product.productParams.type + " " + product.brand + " " + product.model  +
                    '</a>'                                                                      +
                '</div>'
                );
            }
        }
    });
}