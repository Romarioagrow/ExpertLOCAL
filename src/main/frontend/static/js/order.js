$(document).ready(function(){
    $('button[type="submit"][name="addToOrder"]').on('click', addToOrder);
});
$(document).ready(function(){
    $('button[type="button"][name="remove-product"]').on('click', removeFromOrder);
});

function addToOrder(e) {
    e.preventDefault();

    const productID = ($(this).attr("value"));
    console.log(productID);

    $.ajax({
        url: 'http://localhost:8080/order',
        type: 'POST',
        dataType: 'json',
        data: productID,
        processData: false,
        headers: {'Content-Type': 'application/json'},
        complete: function(products) {
            alert("Add product with ID " + productID);
        }
    });
}

function removeFromOrder(e) {
    e.preventDefault();

    const productID = ($(this).attr("value"));
    console.log(productID);

    $.ajax({
        url: 'http://localhost:8080/order',
        type: 'DELETE',
        dataType: 'json',
        data: productID,
        processData: false,
        headers: {'Content-Type': 'application/json'},
        complete: function(products) {

            const response = JSON.parse(JSON.stringify(products));
            const productsMap = new Map(Object.entries(response.responseJSON));

            $("#bucket-products").empty();

            productsMap.forEach((value, key) => {
                $("#bucket-products").append(
                '<div class="card mb-4">' +
                '<div class="view overlay">' +
                '<img class="card-img-top" src="' + value.productParams.pic + '" alt="Card image cap">' +
                '<a href="#!">' +
                '<div class="mask rgba-white-slight"></div>' +
                '</a>' +
                '</div>' +
                '<div class="card-body">' +
                '<h4 class="card-title">' +
                    value.brand +
                    value.model +
                '<div>' +
                '<button type="button" class="btn btn-outline-danger waves-effect">-</button>' +
                '<span class="badge badge-primary badge-pill">'+key+'</span>' +
                '<button type="button" class="btn btn-outline-success waves-effect">+</button>' +
                '</div>' +
                '</h4>' +
                '<p class="card-text">' +
                '<strong>' + value.productParams.type + '</strong>, <strong><i>' + value.price + '</i></strong>' +
                '</p>' +
                '<button type="button" class="btn btn-primary btn-md" id="remove-product" value="'+value.productID+'">Удалить</button>' +
                '</div>' +
                '</div>'
                )
            });
        }
    });

}