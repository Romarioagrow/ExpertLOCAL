$(document).ready(function(){
    $('#search-button').on('click', searchProducts);
});
$(document).ready(function(){
    $('#main-search').on('keyup', searchProducts);
});
$('body').click(function(){
    document.getElementById("display-result").style.display = "none";
});

function searchProducts(e) {
    e.preventDefault();

    var searchRequest = $('#main-search').val().trim();
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

            if (found !== undefined) {
                response.responseJSON.forEach(product => {
                    let singleType = product.singleType ? product.singleType : '';
                    $("#display-result").append(
                        '<p><a href="http://localhost:8080/products/info/'+product.productID+'">'        +
                        ''+singleType+' '+product.originalName +'<strong>'+'         '+product.finalPrice+'₽</strong></a></p>'
                    )
                });
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

