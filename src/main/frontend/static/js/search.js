$(document).ready(function(){
    $('#search-button').on('click', searchProducts);
});
$(document).ready(function(){
    //$('input[modelName="main-search"]').on('keyup', searchProducts);
    $('#main-search').on('keyup', searchProducts);
});
$('body').click(function(){
    /// if элеиентПодСтрелкой.modelName != search
    document.getElementById("display-result").style.display = "none";
});

function searchProducts(e) {
    e.preventDefault();

    var searchRequest = $('#main-search').val().trim()/*.toUpperCase()*/;
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

            let found = response.responseJSON.length;
            console.log('Found products: ' + found);

            if (found !== undefined)
            {
                response.responseJSON.forEach(product => $("#display-result").append
                (
                    '<p><a href="http://localhost:8080/products/info/'+product.productID+'">'        +
                    ''+product.singleType + ' ' + product.originalName+'</a></p>'
                ));
            }
            else
            {
                $("#display-result").append(
                    '<p>Ничего не нашлось!</p>'
                );
            }
        }
    });
}

