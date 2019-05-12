$(document).ready(function(){
    $('button[type="submit"][name="addToOrder"]').on('click', addToOrder);
});
$(document).ready(function(){
    $('button[type="button"][name="remove-product"]').on('click', removeFromOrder);
});
/*$(document).ready(function(){
    $('#remove-product').on('click', removeFromOrder);
});*/
/*$(document).ready(function(){
    $('#remove-product').on('click', lol);
});

function lol() {
    console.log('lol')
}*/

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

    String.prototype.replaceAt=function(index, replacement) {
        return this.substr(0, index) + replacement+ this.substr(index + replacement.length);
    };

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

            //console.info('bang');

            /*var prod = JSON.stringify(products);
            console.log(prod);

            var prs = JSON.parse(prod);
            console.log(prs.responseJSON);*/

            //var lol = new Map(prs.responseJSON);

            const response = JSON.parse(JSON.stringify(products));

            for (let i in response.responseJSON){
                //console.log(Object.keys(response.responseJSON[i]));
            }

            const productsMap = new Map(Object.entries(response.responseJSON));
            //productsMap.forEach((value, key) => console.log(key + " Количество: " + value));

            //productsMap.forEach((value, key, map) => console.log(map.entries()));

            /*console.log(productsMap.length);*/



            $("#bucket-products").empty();

            //productsMap.values();

            productsMap.forEach((value, key) => {
                /*$("#bucket-products").append(
                    key.brand
                )*/
                //console.log('key'+key);
                //console.log(key.brand);

                //let kkk = key.toString();
                let kkkk = JSON.stringify(key);
                //let kkkkk = JSON.parse(key);

                //console.log(key.toString().slice(string.indexOf("("),string.indexOf(")")));
                //console.log(kkk.toString().slice(string.indexOf("("),string.indexOf(")")));
                console.log(kkkk/*.slice(string.indexOf(0),string.indexOf(15))*/);
                //console.log(kkkkk.toString().slice(string.indexOf("("),string.indexOf(")")));
                var rrr = kkkk.substring(kkkk.indexOf("("), kkkk.indexOf("orders=[]"));

                console.log(kkkk.substring(kkkk.indexOf("("), kkkk.indexOf("orders=[]")));
                rrr = rrr.replace("(", "{");

                var s = rrr.lastIndexOf(",");

                var kok = rrr.replaceAt(s, "}");
                console.log(kok);

                var object = JSON.parse(kok);
                console.log(object);


                /*

                let ooo = kkk.slice(string.indexOf("("),string.indexOf(")"));
                console.log(ooo);
*/
                //let obj = JSON.parse(ooo);
                //key.replace('Product','');

                //console.log(obj);
                //console.log(Object.keys(key));
            });


            /*productsMap.forEach((amount, product) => {
                $("#bucket-products").append
                (
                    '<div class="card mb-4">' +
                    '<div class="view overlay">' +
                    '<img class="card-img-top" src="' + product.productParams.pic + '" alt="Card image cap">' +
                    '<a href="#!">' +
                    '<div class="mask rgba-white-slight"></div>' +
                    '</a>' +
                    '</div>' +
                    '<div class="card-body">' +
                    '<h4 class="card-title">' +
                    product.brand +
                    product.model +
                    '<div>' +
                    '<button type="button" class="btn btn-outline-danger waves-effect">-</button>' +
                    '<span class="badge badge-primary badge-pill">'+amount+'</span>' +
                    '<button type="button" class="btn btn-outline-success waves-effect">+</button>' +
                    '</div>' +
                    '</h4>' +
                    '<p class="card-text">' +
                    '<strong>' + product.productParams.type + '</strong>, <strong><i>' + product.price + '</i></strong>' +
                    '</p>' +
                    '<button type="button" class="btn btn-primary btn-md" id="remove-product" value="${product.productID?c}">Удалить</button>' +
                    '</div>' +
                    '</div>'
                );
            });*/

            /*for (var i in prs.responseJSON){
                console.log(prs.responseJSON);
            }*/

            /*console.log(lol.keys());*/

            /*for (var i in lol) {
                console.log(lol.keys())
            }*/

            //var mapResponse = new Map(Object.keys(prs.responseJSON));

            console.log('YES');
            /*mapResponse.forEach((value, key) => console.log(value + ' ' + key));*/

            //$("#bucket-products").empty();
            //console.log('Received ordered products: ' + response.responseJSON.length);



            /*for (var item in products.responseJSON) {
                console.log(products.responseJSON[item].getValue(item))
            }*/

            /*for (var item in response.responseJSON) {
                            let product = response.responseJSON[item].getKey();

                            //let display = resolveDisplayType(product);

                            //console.log(product);

                            /!*$("#bucket-products").append
                            (
                                '<div class="card mb-4">' +
                                '<div class="view overlay">' +
                                '<img class="card-img-top" src="' + product.productParams.pic + '" alt="Card image cap">' +
                                '<a href="#!">' +
                                '<div class="mask rgba-white-slight"></div>' +
                                '</a>' +
                                '</div>' +
                                '<div class="card-body">' +
                                '<h4 class="card-title">' +
                                product.brand +
                                product.model +
                                '<div>' +
                                '<button type="button" class="btn btn-outline-danger waves-effect">-</button>' +
                                '<span class="badge badge-primary badge-pill"></span>' +
                                '<button type="button" class="btn btn-outline-success waves-effect">+</button>' +
                                '</div>' +
                                '</h4>' +
                                '<p class="card-text">' +
                                '<strong>' + product.productParams.type + '</strong>, <strong><i>' + product.price + '</i></strong>' +
                                '</p>' +
                                '<button type="button" class="btn btn-primary btn-md" id="remove-product" value="${product.productID?c}">Удалить</button>' +
                                '</div>' +
                                '</div>'
                            );*!/
                        }*/

        }
    });

}