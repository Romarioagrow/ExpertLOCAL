$(document).ready(function(){
    $('button[type="submit"][name="addToOrder"]').on('click', addToOrder);
});
$(document).ready(function(){
    $('button[id="toBucket"]').on('click', addToOrder);
});

$(document).ready(function(){
    $('button[type="button"][id="product-more"]').on('click', changeAmount);
});
$(document).ready(function(){
    $('button[type="button"][id="product-less"]').on('click', changeAmount);
});

$(document).ready(function(){
    $('button[type="submit"][name="remove-product"]').on('click', removeFromOrder);
});

/*document.getElementById("product-less")*/

/*$(document).ready(function(){

    $('button[type="submit"][id="remove-product"]').on('click', removeFromOrder);

    $(document).ajaxComplete(function() {
        $("#remove-product").click(
            function(){
                removeFromOrder();
            }
        );
    });
});

$(document).ajaxComplete(function() {
    $("#remove-product").click(
        function(){
            removeFromOrder();
        }
    );
});

$(document).ajaxComplete(function() {
    $('button[type="button"][id="product-more"]').click(
        function(){
            changeAmount();
        }
    );
});*/

/*$(document).ready(function() {
    $("#remove-product").click(
        function(){
            removeFromOrder();
        }
    );
});*/

/*
$(document).ajaxComplete(function() {
    $('button[type="submit"][fullName="remove-product"]').on('click', removeFromOrder);
});

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

/*$(document).ready(function(){
    $('button[type="button"]').on('click', changeAmount); ///ОБЪЕДЕНИТЬ ДЛЯ ВСЕХ INPUT
});*/
/*$(document).ready(function(){
    $('button[type="submit"][id="product-less"]').on('click', changeAmount);
});
$(document).ready(function(){
    $('button[type="submit"][id="product-more"]').on('click', changeAmount);
});*/


function addToOrder(e) {
    e.preventDefault();

    const productID = $(this).attr("value");
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

function removeFromOrder() {
    const productID = ($(this).attr("value"));
    console.log(productID);

    $.ajax({
        url: 'http://localhost:8080/order',
        type: 'DELETE',
        data: productID,
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
                    '<button type="button" name="product-less" class="btn btn-outline-danger waves-effect" id="product-less"  value="'+product.productID+'">-</button>' +
                    '<span class="badge badge-primary badge-pill" id="amount'+product.id+'">' + product.amount + '</span>' +
                    '<button type="button" name="product-more" class="btn btn-outline-success waves-effect" id="product-more"  value="'+product.productID+'">+</button>' +
                    '</p>' +
                    '</div>' +
                    '<button type="submit" name="remove-product" class="btn btn-primary btn-md"  id="remove-product" value="'+product.id+'">Удалить</button>' +
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

    ///
    $("button[id='product-less']").each(function() {
        this.disabled = true;
    });
    $("button[id='product-more']").each(function() {
        this.disabled = true;
    });
    $("button[id='remove-product']").each(function() {
        this.disabled = true;
    });
}

$(document).ready(function() {
    $("#confirm-order").click(
        function(){
            acceptOrder();
        }
    );
});

function acceptOrder() {
    var name = $('#name').val();
    var surname = $('#surname').val();
    var mobile = $('#mobile').val();
    var email = $('#email').val();

    $(".error").remove();

    function hasValidErrors(name, surname, mobile, email) {
        return name.length === 0 || surname.length === 0 || mobile.length === 0 || email.length === 0;
    }

    if (hasValidErrors(name, surname, mobile, email)) {
        if (name.length < 1) {
            $('#name').after('<span class="error">Введите имя</span>');

        }
        if (surname.length < 1) {
            $('#surname').after('<span class="error">Введите фамилию</span>');
        }
        if (email.length < 1) {
            $('#email').after('<span class="error">Введите e-mail</span>');
        } else {
            var regEx = /^[A-Z0-9][A-Z0-9._%+-]{0,63}@(?:[A-Z0-9-]{1,63}.){1,125}[A-Z]{2,63}$/;
            var validEmail = regEx.test(email);
            if (!validEmail) {
                $('#email').after('<span class="error">Введите корректный e-mail</span>');
            }
        }
        if (mobile.length < 8) {
            $('#mobile').after('<span class="error">Введите номер в формате +7-999-666-14-88</span>');
        }
        return;
    }

    var contacts = {
        orderID : $('#confirm-order').val(),
        name    : name,
        surname : surname,
        mobile  : mobile,
        email   : email,
    };

    contacts = JSON.stringify(contacts);

    $.ajax({
        url:     "http://localhost:8080/order/confirm",
        type:     "POST",
        data: contacts,
        headers: {'Content-Type': 'application/json'},
        success: function(response)
        {
            console.log(response);

            if (response.length === 0)
            {
                console.log("Order accepted");

                document.getElementById("order-deal").style.display = "none";
                document.getElementById("order-button").style.display = "none";
                $('#orderSuccess').html('<h3>Заказ подтвержден</h3>'+'');
            }
            else $('#orderSuccess').html('<h3>Данные не верны!</h3>'+'');
        },
        error: function(response) {
            $('#results').html('Ошибка. Данные не отправлены.');
        }
    });
}
