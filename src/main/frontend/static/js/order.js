$(document).ready(function(){
    $('button[type="submit"][name="addToOrder"]').on('click', addToOrder);
});

$(document).ready(function(){
    $('button[type="submit"][name="remove-product"]').on('click', removeFromOrder);
});
$(document).ajaxComplete(function() {
    $('button[type="submit"][name="remove-product"]').on('click', removeFromOrder);
});

$(document).ready(function(){
    $('button[type="button"][id="product-more"]').on('click', changeAmount);
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
            console.log("Add product with ID " + productID);
        }
    });
}

function changeAmount() {

    var data = {
        'productID' : ($(this).attr("value")),
        'orderedID' : ($(this).attr("name")),
        'action'    : ($(this).attr("id"))
    };

    let amountID = '#amount'+data.orderedID;

    data = JSON.stringify(data);

    $.ajax({
        url: 'http://localhost:8080/order',
        type: 'PUT',
        dataType: 'json',
        data: data,
        processData: false,
        headers: {'Content-Type': 'application/json'},
        complete: function(amount) {

            $(amountID).empty().append(
                '<span class="badge badge-primary badge-pill">'+amount.responseText+'</span>'
            )
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
        complete: function(products)
        {
            const response = JSON.parse(JSON.stringify(products));

            $("#bucket-products").empty();

            for (var item in response.responseJSON)
            {
                let product = response.responseJSON[item];
                console.log(product);

                $("#bucket-products").append
                (
                    '<div class="card mb-4">' +
                    '<div class="view overlay">' +
                    '<img class="card-img-top" src="'+product.pic+'" alt="Card image cap">' +
                    '<a href="#!">' +
                    '<div class="mask rgba-white-slight"></div>' +
                    '</a>' +
                    '</div>' +
                    '<div class="card-body">' +
                    '<h4 class="card-title">' +
                        product.brand + ' ' + product.model +
                    '<div>' +
                    '<button type="button" class="btn btn-outline-danger waves-effect" name="product-less" value="'+product.productID+'">-</button>' +
                    '<span class="badge badge-primary badge-pill" id="amount">' + product.amount + '</span>' +
                    '<button type="button" class="btn btn-outline-success waves-effect" name="product-more" value="'+product.productID+'">+</button>' +
                    '</div>' +
                    '</h4>' +
                    '<p class="card-text">' +
                    '<strong>'+product.type+'</strong>, <strong><i>' + product.totalPrice + '</i></strong>' +
                    '</p>' +
                    '<button type="submit" class="btn btn-primary btn-md" name="remove-product" id="remove-product" value="'+product.productID+'">Удалить</button>' +
                    '</div>' +
                    '</div>'
                )
            }
        }
    });
}