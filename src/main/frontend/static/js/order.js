$(document).ready(function(){
    $('button[type="submit"][name="addToOrder"]').on('click', addToOrder);
});

$(document).ready(function(){
    $('button[type="submit"][name="remove-product"]').on('click', removeFromOrder);
});
/*$(document).ajaxComplete(function() {
    $('button[type="submit"][name="remove-product"]').on('click', removeFromOrder);
});*/

$(document).ready(function(){
    $('button[type="button"][id="product-more"]').on('click', changeAmount);
});
$(document).ready(function(){
    $('button[type="button"][id="product-less"]').on('click', changeAmount);
});
/*$(document).ajaxComplete(function() {
    $(document).ready(function(){
        $('button[type="button"][id="product-more"]').on('click', changeAmount);
    });
});*/

//$('button[type="button"][id="product-more"]').on('click', changeAmount);

//$('button[type="button"][id="product-more"]').click(changeAmount());

///!!!
/*$('body').click(function(){
    /// if элеиентПодСтрелкой.name != search
    document.getElementById("display-result").style.display = "none";
});*/

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

function changeAmount(e) {
    e.preventDefault();

    var data = {
        'productID' : ($(this).attr("value")),
        'orderedID' : ($(this).attr("name")),
        'action'    : ($(this).attr("id"))
    };

    let amountID     = '#amount'      + data.orderedID;
    let totalPriceID = '#total-price' + data.orderedID;

    data = JSON.stringify(data);

    $.ajax({
        url: 'http://localhost:8080/order',
        type: 'PUT',
        dataType: 'json',
        data: data,
        processData: false,
        headers: {'Content-Type': 'application/json'},
        complete: function(orderedProduct)
        {
            $(amountID).empty().append(
                orderedProduct.responseJSON.amount
            );
            $(totalPriceID).empty().append(
                orderedProduct.responseJSON.totalPrice
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
                    '<div class="card ordered-card mb-4">' +
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
                    '<button type="button" class="btn btn-outline-danger waves-effect" id="product-less" name="'+product.id+'" value="'+product.productID+'">-</button>' +
                    '<span class="badge badge-primary badge-pill" id="amount'+product.id+'">' + product.amount + '</span>' +
                    '<button type="button" class="btn btn-outline-success waves-effect" id="product-more" name="'+product.id+'" value="'+product.productID+'">+</button>' +
                    '</div>' +
                    '</h4>' +
                    '<p class="card-text">' +
                    '<strong>'+product.type+'</strong>, <strong><i>' + product.totalPrice + '₽' + '</i></strong>' +
                    '</p>' +
                    '<button type="submit" class="btn btn-primary btn-md" name="remove-product" id="remove-product" value="'+product.id+'">Удалить</button>' +
                    '</div>' +
                    '</div>'
                )
            }
        }
    });
}

function displayOrderDeal() {
    document.getElementById("order-deal").style.display = "block";

    $([document.documentElement, document.body]).animate({
        scrollTop: $("#contact-info").offset().top
    }, 1000);

    /*(document.getElementById("product-less").each(function() {
        document.getElementById("product-less").disabled = true;
    }));*/

    /*(document.getElementById("product-less").each(function() {
        $(this).disabled = true;
    }));*/

    document.getElementById("product-less").disabled = true;
    document.getElementById("product-more").disabled = true;
    document.getElementById("remove-product").disabled = true;
}

function confirmOrder() {
    var contacts = {
        orderID : $('#confirm-order').val(),
        name    : $('#name').val(),
        surname : $('#family').val(),
        mobile  : $('#mobile').val(),
        email   : $('#email').val(),
    };
    console.log(contacts);

    contacts = JSON.stringify(contacts);

    $.ajax({
        url: 'http://localhost:8080/order/confirm',
        type: 'POST',
        dataType: 'json',
        data: contacts,
        processData: false,
        headers: {'Content-Type': 'application/json'},
        complete: function(order)
        {

        }
    });

}