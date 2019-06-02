$(document).ready(function(){
    $('button[type="submit"][name="addToOrder"]').on('click', addToOrder);
});

$(document).ready(function(){
    $('button[type="submit"][name="remove-product"]').on('click', removeFromOrder);
});
/*$(document).ajaxComplete(function() {
    $('button[type="submit"][fullName="remove-product"]').on('click', removeFromOrder);
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
    /// if элеиентПодСтрелкой.fullName != search
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
        complete: function(orderAndProduct)
        {
            orderAndProduct = JSON.parse(JSON.stringify(orderAndProduct));
            console.log(orderAndProduct);

            let order = orderAndProduct.responseJSON[0];
            let orderedProduct = orderAndProduct.responseJSON[1];

            $(amountID).empty().append(
                orderedProduct.amount
            );
            $(totalPriceID).empty().append(
                (orderedProduct.totalPrice).toLocaleString('ru')+'₽'
            );
            $('#order-price').empty().append(
                (order.totalPrice).toLocaleString('ru')+'₽'
            );
            $('#order-products').empty().append(
                order.productsAmount
            );
            $('#order-amount').empty().append(
                order.totalAmount
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
        complete: function(orderResponse)
        {
            const order = JSON.parse(JSON.stringify(orderResponse)).responseJSON;

            $("#bucket-products").empty();
            for (var item in order.orderedProducts)
            {
                let product = order.orderedProducts[item];

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
                        '<div class="mt-3">'+product.type+', <strong><i>' + product.totalPrice + '₽' + '</i></strong></div>' +
                    '<div>' +
                    '</div>' +
                    '</h4>' +
                    '<p class="card-text">' +
                    '<button type="button" class="btn btn-outline-danger waves-effect" id="product-less" fullName="'+product.id+'" value="'+product.productID+'">-</button>' +
                    '<span class="badge badge-primary badge-pill" id="amount'+product.id+'">' + product.amount + '</span>' +
                    '<button type="button" class="btn btn-outline-success waves-effect" id="product-more" fullName="'+product.id+'" value="'+product.productID+'">+</button>' +
                    '</p>' +
                    '</div>' +
                    '<button type="submit" class="btn btn-primary btn-md" fullName="remove-product" id="remove-product" value="'+product.id+'">Удалить</button>' +
                    '</div>'
                );

                $('#order-price').empty().append(
                    (order.totalPrice).toLocaleString('ru')+'₽'
                );
                $('#order-products').empty().append(
                    order.productsAmount
                );
                $('#order-amount').empty().append(
                    order.totalAmount
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


$( document ).ready(function() {
    $("#confirm-order").click(
        function(){
            acceptOrder($('#contact-session'));
        }
    );
});

function acceptOrder() {
    var contacts = {
        orderID : $('#confirm-order').val(),
        name    : $('#name').val(),
        surname : $('#surname').val(),
        mobile  : $('#mobile').val(),
        email   : $('#email').val(),
    };

    contacts = JSON.stringify(contacts);

    $.ajax({
        url:     "http://localhost:8080/order/confirm",
        type:     "POST",
        data: contacts,
        headers: {'Content-Type': 'application/json'},
        success: function(response) {
            //document.getElementById("order-deal").style.display = "none";
            //document.getElementById("order-button").style.display = "none";
            $('#results').html(response);
        },
        error: function(response) {
            $('#results').html('Ошибка. Данные не отправлены.');
        }
    });
}
