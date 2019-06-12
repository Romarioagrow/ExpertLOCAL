$(document).ready(function(){
    $('button[name="cards-layout-inp"]').on('click', showCardsLayout);
});
$(document).ready(function(){
    $('button[name="rows-layout-inp"]').on('click', showRowsLayout);
});

function showCardsLayout() {
    console.log('cards');
    document.getElementById("bucket-products").style.display      = "flex";
    document.getElementById("bucket-products-rows").style.display = "none";
}

function showRowsLayout() {
    console.log('rows');
    document.getElementById("bucket-products").style.display 	    = "none";
    document.getElementById("bucket-products-rows").style.display 	= "block";
}

function addToOrder(button) {
    const productID = button.value;
    console.log(productID);

    $.ajax({
        url: 'http://localhost:8080/order',
        type: 'POST',
        dataType: 'json',
        data: productID,
        processData: false,
        headers: {'Content-Type': 'application/json'},
        complete: function(productsAmount)
        {
            //console.log(productsAmount.responseText);
            console.log("Add product with ID " + productID);
            var buttonID = '#addToOrderDiv'+productID;

            $(buttonID).empty().append(
                '<a type="button" class="btn btn-danger btn-md" style="background-color: #e52d00 !important;" href="http://localhost:8080/order">Оформить заказ</button>'
            );

            $("#productsAmount-Div").empty().append(
            '<a id="productAmount-Order" href="/order"><h5 style="color: black !important; margin-top: 1.5rem!important;">Товаров:  <span class="badge badge-primary">'+productsAmount.responseText+'</span></h5></a>'
            )
        }
    });
}

function changeAmount(product) {
    var data = {
        productID : product.value,
        orderedID : product.id,
        action    : product.name
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

function removeFromOrder(button) {
    const productID = button.value;

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
                    '<div class="mt-3">'+product.type+', <strong><i id="total-price'+product.id+'">' + product.totalPrice + '₽' + '</i></strong></div>' +
                    '<div>' +
                    '</div>' +
                    '</h4>' +
                    '<p class="card-text">' +
                    '<button type="button" onclick="changeAmount(this)" id="'+product.id+'" name="product-less" value="'+product.productID+'" class="btn btn-outline-danger waves-effect">-</button>' +
                    '<span class="badge badge-primary badge-pill" id="amount'+product.id+'">'+(product.amount).toLocaleString('ru')+'</span>' +
                    '<button type="button" onclick="changeAmount(this)" id="'+product.id+'" name="product-more" value="'+product.productID+'" class="btn btn-outline-success waves-effect">+</button>' +
                    '</p>' +
                    '</div>' +
                    '<button type="submit" onclick="removeFromOrder(this)" name="remove-product" class="btn btn-danger btn-md"  id="remove-product" value="'+product.id+'">Удалить</button>' +
                    '</div>'
                );
            }
            
            $('#order-price').empty().append(
                (order.totalPrice).toLocaleString('ru')+'₽'
            );
            $('#order-products').empty().append(
                order.productsAmount
            );
            $('#order-amount').empty().append(
                order.totalAmount
            );
            $('#productAmount-Order').empty().append(
                '<a id="productAmount-Order" href="/order" class="mt-4 mb-3"><h5 style="color: black !important;">Товаров:  <span class="badge badge-primary">'+order.productsAmount+'</span></h5></a>'
            );
            
            if (order.productsAmount === 0)
            {
                document.getElementById("order-deal-form").style.display   = "none";
                document.getElementById("order-layout").style.display   = "none";

                $('#bucket-products').empty().append(
                '<h3 style="margin-top: 4vh">Товаров пока нет </h3>'+
                '<a type="button" href="/" class="btn blue-gradient btn-lg btn-block">Вернуться за покупками</a>'
                )
            }
        }
    });
}

function displayOrderDeal() {
    document.getElementById("order-deal").style.display 	= "block";
    document.getElementById("order-button").style.display 	= "none";
    document.getElementById("edit-order").style.display 	= "block";

    $([document.documentElement, document.body]).animate({
        scrollTop: $("#contact-info").offset().top
    }, 1000);

    ///
    $("button[name='product-less']").each(function() {
        this.disabled = true;
    });
    $("button[name='product-more']").each(function() {
        this.disabled = true;
    });
    $("button[id='remove-product']").each(function() {
        this.disabled = true;
    });
}

function editOrder() {
    document.getElementById("order-deal").style.display 	= "none";
    document.getElementById("edit-order").style.display 	= "none";
    document.getElementById("order-button").style.display 	= "block";

    $("button[name='product-less']").each(function() {
        this.disabled = false;
    });
    $("button[name='product-more']").each(function() {
        this.disabled = false;
    });
    $("button[id='remove-product']").each(function() {
        this.disabled = false;
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
    var name 	= $('#name').val();
    var surname = $('#surname').val();
    var mobile 	= $('#mobile').val();
    var email 	= $('#email').val();

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
                document.getElementById("edit-order").style.display = "none";

                document.getElementById("new-order-button").style.display = "block";
                document.getElementById("my-order-button").style.display = "block";

                $('#orderSuccess').html('<h3>Заказ подтвержден</h3>'+'');
            }
            else
            {
                $('#orderSuccess').html('<h3>Данные не верны!</h3>'+'');
            }
        },
        error: function(response) {
            $('#results').html('Ошибка. Данные не отправлены.');
        }
    });
}

$(document).ready(function() {
    $('input[type="radio"][id="delivery-button"]').on('change', function () {
        document.getElementById("delivery-block").style.display = "block";
        document.getElementById("self-delivery-block").style.display = "none";
    });
});

$(document).ready(function() {
    $('input[type="radio"][id="self-delivery-button"]').on('change', function () {
        document.getElementById("delivery-block").style.display = "none";
        document.getElementById("self-delivery-block").style.display = "block";
    });
});



