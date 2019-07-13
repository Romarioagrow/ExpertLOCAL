$(document).ready(function(){
    $('button[modelName="cards-layout-inp"]').on('click', showCardsLayout);
});
$(document).ready(function(){
    $('button[modelName="rows-layout-inp"]').on('click', showRowsLayout);
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

function escapeRegExp(str) {
    return str.replace(/([.*+?^=!:${}()|\[\]\/\\])/g, "\\$1");
}

function replaceAll(str, find, replace) {
    return str.replace(new RegExp(escapeRegExp(find), 'g'), replace);
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
        complete: function(payload)
        {
            console.info(payload);
            const productsAmount = payload.responseJSON[0];
            //console.log("Add product with ID " + productID);

            var buttonID = '#addToOrderDiv' + productID;
            buttonID = replaceAll(buttonID, ".", "");
            //console.log('Id блока кнопки ' + buttonID);

            $(buttonID).empty().append(
                '<a type="button" class="btn btn-danger btn-md" style="background-color: #e52d00 !important;" href="http://localhost:8080/order">Оформить заказ</button>'
            );

            $("#productsAmount-Div").empty().append(
                '<a orderedID="productAmount-Order" href="/order"><h5 style="color: black !important; margin-top: 1.5rem!important;">Товаров:  <span class="badge badge-primary">'+productsAmount+'</span></h5></a>'
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

    console.log(data);

    let amountID        = '#amount'             + data.orderedID;
    let totalPriceID    = '#total-price'        + data.orderedID;
    let productBonusID  = '#productTotalBonus'  + data.orderedID;
    let productPrAmID   = '#prAm'               + data.orderedID;

    data = JSON.stringify(data);

    $.ajax({
        url: 'http://localhost:8080/order',
        type: 'PUT',
        dataType: 'json',
        data: data,
        processData: false,
        headers: {'Content-Type': 'application/json'},
        complete: function(payload)
        {
            payload = JSON.parse(JSON.stringify(payload));

            const order             = payload.responseJSON[0];
            const orderedProduct    = payload.responseJSON[1];
            const discount          = payload.responseJSON[2];

            console.log(orderedProduct);
            console.log(order);

            var productBonus = 'Бонус за покупку: ' + (orderedProduct.bonus * orderedProduct.orderedAmount).toLocaleString('ru');

            $(productPrAmID).empty().append(
                orderedProduct.orderedAmount
            );
            $(amountID).empty().append(
                orderedProduct.orderedAmount
            );
            $(totalPriceID).empty().append(
                (orderedProduct.totalPrice).toLocaleString('ru')+' ₽'
            );
            $(productBonusID).empty().append(
                productBonus
            );

            $('#total-discount').empty().append(
                discount + '%'
            );
            $('#bonusAmount').empty().append(
                (order.totalBonus).toLocaleString('ru')
            );
            $('#order-totalPrice').empty().append(
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
    console.log(productID);

    $.ajax({
        url: 'http://localhost:8080/order',
        type: 'DELETE',
        data: productID,
        headers: {'Content-Type': 'application/json'},
        complete: function(payload)
        {
            const order     = JSON.parse(JSON.stringify(payload)).responseJSON[0];
            const discount  = JSON.parse(JSON.stringify(payload)).responseJSON[1];

            $("#bucket-products").empty();
            for (var item in order.orderedProducts)
            {
                let product = order.orderedProducts[item];
                var productBonus = 'Бонус за покупку: ' + product.bonus * product.orderedAmount;

                /// сервер загружает на элемент данные а ajax обновляет
                $("#bucket-products").append
                (
                    '<div class="card ordered-card mb-4">' +
                    '<div class="view overlay">' +
                    '<img class="scale-pic" src="'+product.pic+'" alt="Card image cap">' +
                    '<a href="#!">' +
                    '<div class="mask rgba-white-slight"></div>' +
                    '</a>' +
                    '</div>' +
                    '<div class="card-body">' +
                    '<h4 class="card-title">' +
                    '<div class="mb-3">'+product.productType+'</div>'+
                    '<a href="http://localhost:8080/info/'+product.productID+'">'+product.productName+'</a></h4>'+
                    '<h4><strong><i id="total-price'+product.orderedID+'">'+(product.totalPrice).toLocaleString('ru') +' ₽</i></strong> за <span id="prAm'+product.orderedID+'">'+product.orderedAmount+'</span> шт.</h4>' +
                    '<p id="productTotalBonus'+product.orderedID+'">' +
                    productBonus +
                    '</p>'+
                    '<p class="card-text">' +
                    '<button type="button" onclick="changeAmount(this)" id="'+product.orderedID+'" name="product-less" value="'+product.productID+'" class="btn btn-outline-danger waves-effect">-</button>' +
                    '<span class="badge badge-primary badge-pill"       id="amount'+product.orderedID+'">'+(product.orderedAmount).toLocaleString('ru')+'</span>' +
                    '<button type="button" onclick="changeAmount(this)" id="'+product.orderedID+'" name="product-more" value="'+product.productID+'" class="btn btn-outline-success waves-effect">+</button>' +
                    '</p>' +
                    '</div>' +
                    '<button type="submit" onclick="removeFromOrder(this)" class="btn btn-danger btn-md" name="remove-product" id="remove-product" value="'+product.orderedID+'">Удалить</button>' +
                    '</div>'
                );
            }

            $('#total-discount').empty().append(
                discount + '%'
            )
            $('#bonusAmount').empty().append(
                (order.totalBonus).toLocaleString('ru')
            );
            $('#order-totalPrice').empty().append(
                (order.totalPrice).toLocaleString('ru')+'₽'
            );
            $('#order-products').empty().append(
                order.productsAmount
            );
            $('#order-amount').empty().append(
                order.totalAmount
            );
            $('#productAmount-Order').empty().append(
                '<a orderedID="productAmount-Order" href="/order" class="mt-4 mb-3"><h5 style="color: black !important;">Товаров:  <span class="badge badge-primary">'+order.productsAmount+'</span></h5></a>'
            );

            if (order.productsAmount === 0)
            {
                document.getElementById("order-button").style.display   = "none";
                document.getElementById("order-details").style.display  = "none";
                document.getElementById("goToBuy").style.display        = "block";

            }
        }
    });
}

function confirmOrderList() {
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
    $("button[name='remove-product']").each(function() {
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
    $("button[name='remove-product']").each(function() {
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

function applyDiscount(discount, bonus, orderID) {
    data.push(discount, bonus, orderID);
    data = JSON.stringify(data);

    /*data = {
        'discount' : discount,
        'bonus'    : bonus
    };*/
    console.info(data);

    $.ajax({
        url: 'http://localhost:8080/order/discount',
        type: 'POST',
        dataType: 'json',
        data: data,
        processData: false,
        headers: {'Content-Type': 'application/json'},
        complete: function(payload)
        {
            /*console.info(payload);
            const productsAmount = payload.responseJSON[0];
            //console.log("Add product with ID " + productID);

            var buttonID = '#addToOrderDiv' + productID;
            buttonID = replaceAll(buttonID, ".", "");
            //console.log('Id блока кнопки ' + buttonID);

            $(buttonID).empty().append(
                '<a type="button" class="btn btn-danger btn-md" style="background-color: #e52d00 !important;" href="http://localhost:8080/order">Оформить заказ</button>'
            );

            $("#productsAmount-Div").empty().append(
                '<a orderedID="productAmount-Order" href="/order"><h5 style="color: black !important; margin-top: 1.5rem!important;">Товаров:  <span class="badge badge-primary">'+productsAmount+'</span></h5></a>'
            )*/
        }
    });
}

function acceptOrder() {
    var firstName 	= $('#firstName').val();
    var lastName    = $('#lastName').val();
    var username 	= $('#username').val();
    var email 	    = $('#email').val();

    /*$(".error").remove();

    function hasValidErrors(name, surname, mobile, email) {
        return name.length === 0 || surname.length === 0 || mobile.length === 0 || email.length === 0;
    }

    if (hasValidErrors(name, surname, mobile, email)) {
        if (name.length < 1) {
            $('#modelName').after('<span class="error">Введите имя</span>');
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
            $('#email').after('<span class="error">Введите номер в формате +7-999-666-14-88</span>');
        }
        return;
    }*/

    var contacts = {
        orderID     : $('#confirm-order').val(),
        firstName   : firstName,
        lastName    : lastName,
        username    : username,
        email       : email,
    };
    console.log(contacts);
    contacts = JSON.stringify(contacts);

    console.log("Order accepted");
    document.getElementById("order-deal").style.display = "none";
    document.getElementById("edit-order").style.display = "none";
    document.getElementById("new-order-button").style.display = "block";
    document.getElementById("my-order-button").style.display  = "block";

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
                /// ЗАМЕНИТЬ НА АЛЕРТ
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
    $('input[type="radio"][orderedID="delivery-button"]').on('change', function () {
        document.getElementById("delivery-block").style.display = "block";
        document.getElementById("self-delivery-block").style.display = "none";
    });
});

$(document).ready(function() {
    $('input[type="radio"][orderedID="self-delivery-button"]').on('change', function () {
        document.getElementById("delivery-block").style.display = "none";
        document.getElementById("self-delivery-block").style.display = "block";
    });
});



