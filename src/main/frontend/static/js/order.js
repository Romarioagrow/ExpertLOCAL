$(document).ready(function(){
    $('button[type="submit"][name="addToOrder"]').on('click', addToOrder);
});
$(document).ready(function(){
    $('button[type="button"][name="remove-product"]').on('click', removeFromOrder);
});
$(document).ready(function(){
    $('#remove-product').on('click', removeFromOrder);
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

            for(const [key, value] of productsMap) {
                console.log(`key: ${key}, value: ${value}`);

                let productID = key.substring(key.indexOf('productID='), key.indexOf(','));//.slice(key.indexOf("="));
                let productBrand = key.substring(key.indexOf('brand='), key.indexOf(', model'));//..slice(key.lastIndexOf("="));
                let productModel = key.substring(key.indexOf('model='), key.indexOf(', country'));//..slice(key.lastIndexOf("="));
                let productPrice = key.substring(key.indexOf('price='), key.indexOf(', orders'));//..slice(key.lastIndexOf("="));

                productID = productID.slice(productID.indexOf("=")+1);
                productBrand = productBrand.slice(productBrand.indexOf("=")+1);
                productModel = productModel.slice(productModel.indexOf("=")+1);
                productPrice = productPrice.slice(productPrice.indexOf("=")+1);

                console.log(productID);
                console.log(productBrand);
                console.log(productModel);
                console.log(productPrice);
                console.log('\n');

                $("#bucket-products").empty();

                $("#bucket-products").append(
                    '<div class="card mb-4">' +
                    '<div class="view overlay">' +
                    '<img class="card-img-top" src="#" alt="Card image cap">' +
                    '<a href="#!">' +
                    '<div class="mask rgba-white-slight"></div>' +
                    '</a>' +
                    '</div>' +
                    '<div class="card-body">' +
                    '<h4 class="card-title">' +
                    productBrand + ' ' + productModel +
                    '<div>' +
                    '<button type="button" class="btn btn-outline-danger waves-effect">-</button>' +
                    '<span class="badge badge-primary badge-pill">' + value + '</span>' +
                    '<button type="button" class="btn btn-outline-success waves-effect">+</button>' +
                    '</div>' +
                    '</h4>' +
                    '<p class="card-text">' +
                    '<strong>type </strong>, <strong><i>' + productPrice + '</i></strong>' +
                    '</p>' +
                    '<button type="button" class="btn btn-primary btn-md" name="remove-product" id="remove-product" value="'+productID+'">Удалить</button>' +
                    '</div>' +
                    '</div>'
                )
            }



            /*productsMap.forEach((value, key) => {
                    console.log(key);

                    //let keyObj = JSON.parse(key);

                    let productID = key.substring(key.indexOf('productID='), key.indexOf(','));//.slice(key.indexOf("="));
                    let productBrand = key.substring(key.indexOf('brand='), key.indexOf(', model'));//..slice(key.lastIndexOf("="));
                    let productModel = key.substring(key.indexOf('model='), key.indexOf(', country'));//..slice(key.lastIndexOf("="));
                    let productPrice = key.substring(key.indexOf('price='), key.indexOf(', orders'));//..slice(key.lastIndexOf("="));

                    productID = productID.slice(productID.indexOf("=")+1);
                    productBrand = productBrand.slice(productBrand.indexOf("=")+1);
                    productModel = productModel.slice(productModel.indexOf("=")+1);
                    productPrice = productPrice.slice(productPrice.indexOf("=")+1);

                    console.log(productID);
                    console.log(productBrand);
                    console.log(productModel);
                    console.log(productPrice);
                    console.log('\n');

                    $("#bucket-products").empty();

                    $("#bucket-products").append(
                        '<div class="card mb-4">' +
                        '<div class="view overlay">' +
                        '<img class="card-img-top" src="#" alt="Card image cap">' +
                        '<a href="#!">' +
                        '<div class="mask rgba-white-slight"></div>' +
                        '</a>' +
                        '</div>' +
                        '<div class="card-body">' +
                        '<h4 class="card-title">' +
                        productBrand + ' ' + productModel +
                        '<div>' +
                        '<button type="button" class="btn btn-outline-danger waves-effect">-</button>' +
                        '<span class="badge badge-primary badge-pill">' + value + '</span>' +
                        '<button type="button" class="btn btn-outline-success waves-effect">+</button>' +
                        '</div>' +
                        '</h4>' +
                        '<p class="card-text">' +
                        '<strong>type </strong>, <strong><i>' + productPrice + '</i></strong>' +
                        '</p>' +
                        '<button type="button" class="btn btn-primary btn-md" name="remove-product" id="remove-product" value="'+productID+'">Удалить</button>' +
                        '</div>' +
                        '</div>'
                    )

                //productsMap.forEach((value, key) => console.log(value + "Key" + key));

                /!*productsMap.forEach((value, key) => {
                    $("#bucket-products").append(
                        '<div class="card mb-4">' +
                        '<div class="view overlay">' +
                        '<img class="card-img-top" src="#" alt="Card image cap">' +
                        '<a href="#!">' +
                        '<div class="mask rgba-white-slight"></div>' +
                        '</a>' +
                        '</div>' +
                        '<div class="card-body">' +
                        '<h4 class="card-title">' +
                        productBrand + ' ' + productModel +
                        '<div>' +
                        '<button type="button" class="btn btn-outline-danger waves-effect">-</button>' +
                        '<span class="badge badge-primary badge-pill">' + value + '</span>' +
                        '<button type="button" class="btn btn-outline-success waves-effect">+</button>' +
                        '</div>' +
                        '</h4>' +
                        '<p class="card-text">' +
                        '<strong>${product.productParams.type}</strong>, <strong><i>' + productPrice + '</i></strong>' +
                        '</p>' +
                        '<button type="button" class="btn btn-primary btn-md" name="remove-product" id="remove-product" value="'+productID+'">Удалить</button>' +
                        '</div>' +
                        '</div>'
                    )
                })*!/
            });*/
        }
    });
}