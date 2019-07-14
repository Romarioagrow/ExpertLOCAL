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

    var buttonID = '#addToOrderDiv' + productID;
    buttonID = replaceAll(buttonID, ".", "");
    //console.log('Id блока кнопки ' + buttonID);

    $(buttonID).empty().append(
        '<a type="button" class="btn btn-danger btn-md" style="background-color: #e52d00 !important;" href="http://localhost:8080/order">Оформить заказ</button>'
    );

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

function applyDiscount(bonus, discount, orderID) {
    data = {
        'bonus'     : bonus,
        'discount'  : discount,
        'orderID'   : orderID
    };
    data = JSON.stringify(data);

    /*set hidden*/

    $.ajax({
        url: 'http://localhost:8080/order/discount',
        type: 'POST',
        dataType: 'json',
        data: data,
        processData: false,
        headers: {'Content-Type': 'application/json'},
        complete: function(payload)
        {
            console.info(payload);

            const order = payload.responseJSON[0];
            const user  = payload.responseJSON[1];

            $('#applyDiscount').empty().append
            (
                '<h5 style="margin-bottom: 1rem">Ваша скидка '+order.discountPercent+'%!</h5>'
            );
            /*1 ВЫ ПРИМЕНЛИ СКИДКУ В 15%! applyDiscount discountApplied*/

            $('#order-totalPrice').empty().append(
                '<span style="color: #007e33">'+(order.discountPrice).toLocaleString('ru')+'₽</span>'
            );
            /*2 ЗАКАЗ НА СУММУ */

            $('#userBonusUpper').empty().append(
                (user.bonus - order.bonusOff).toLocaleString('ru')+'₽'
            );
            /*3 ДОСТУПНО БОНУСОВ */
        }
    });
}

function acceptOrder() {
    var firstName 	= $('#firstName').val();
    var lastName    = $('#lastName').val();
    var username 	= $('#username').val();
    var email 	    = $('#email').val();

    $(".error").remove();
    function hasValidErrors(firstName, lastName, username, email) {
        return firstName.length === 0 || lastName.length === 0 || username.length === 0 || email.length === 0;
    }
    if (hasValidErrors(firstName, lastName, username, email)) {
        if (firstName.length < 2) {
            $('#firstName').after('<span class="error">Введите имя</span>');
        }
        if (lastName.length < 2) {
            $('#lastName').after('<span class="error">Введите фамилию</span>');
        }
        if (email.length < 1) {
            $('#email').after('<span class="error">Введите e-mail</span>');
        }
        else
        {
            var regEx = /^[A-Z0-9][A-Z0-9._%+-]{0,63}@(?:[A-Z0-9-]{1,63}.){1,125}[A-Z]{2,63}$/;
            var validEmail = regEx.test(email);
            if (!validEmail) {
                $('#email').after('<span class="error">Введите корректный e-mail</span>');
            }
        }
        if (username.length < 8) {
            $('#username').after('<span class="error">Введите номер в формате +7-999-666-14-88</span>');
        }
        return;
    }

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

    document.getElementById("orderLoader").style.display = "block";
    /*ОБНОВЛЯТЬ ИНФОРМАЦИЮ О БОНУСАХ*/
    $.ajax({
        url:     "http://localhost:8080/order/confirm",
        type:     "POST",
        data: contacts,
        headers: {'Content-Type': 'application/json'},
        success: function(response)
        {
            console.log(response);
            document.getElementById("orderLoader").style.display  = "none";
            if (response.length === 0)
            {
                $('#orderStatus').html
                (
                    '<div class="alert alert-success" role="alert" style="width: 60rem;margin-left: -1rem;">' +
                    '        <h4 class="alert-heading">Ваш заказ принят!</h4>' +
                    '        <p>Username, ваш заказ на сумму 25 148 ₽ принят!</p>' +
                    '        <p class="mb-0">Ваш заказ можно будет забрать после подтверждения готовности заказа!</p>' +
                    ' </div>'
                );
                document.getElementById("new-order-button").style.display = "block";
                document.getElementById("my-order-button").style.display  = "block";
            }
            else
            {
                var validErrors = response.toString();
                validErrors = validErrors.replace(",", "<br>");

                document.getElementById("order-deal").style.display = "block";
                document.getElementById("edit-order").style.display = "block";
                $('#orderStatus').html
                (
                    '<div class="alert alert-danger" role="alert" style="width: 60rem;margin-left: -1rem;">'+validErrors+'</div>'
                );
            }
        },
        error: function(response) {
            document.getElementById("orderLoader").style.display = "none";
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



