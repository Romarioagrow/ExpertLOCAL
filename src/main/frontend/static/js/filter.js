$(document).ready(function(){
    $('#filter-button')         .on('click', filterProducts);
});
$(document).ready(function(){
    $('input[type="radio"]')    .on('change', filterProducts);
});
$(document).ready(function(){
    $('input[type="checkbox"]') .on('change', filterProducts);
});

$(document).ready(function(){
    displayBrands()
});

function displayBrands() {
    const url = resolveURL();
    const request = url.substring(url.lastIndexOf("/")+1);
    console.log(request);

    $.ajax({
        url: 'http://localhost:8080/brands/',
        type: 'POST',
        dataType: 'json',
        data: request,
        processData: false,
        headers: {'Content-Type': 'application/json'},
        complete: function(response)
        {
            const brands = response.responseJSON[0];
            const filters = response.responseJSON[1];

            console.log(brands);
            console.log(filters);

            $('#brands-view').empty();

            for (let i = 0; i < brands.length; i+=2) {
                let first = brands[i];
                let second = brands[i+1];

                $('#brands-view').append(
                    '<div class="row">' +
                    '        <div class="col-6">' +
                    '            <input type="checkbox" name="brand" class="form-check-input" id="'+first+'" value="'+first+'">' +
                    '            <label class="form-check-label" for="'+first+'">'+first+'</label>' +
                    '        </div>' +
                    '        <div class="col-6">' +
                    '            <input type="checkbox" name="brand" class="form-check-input" id="'+second+'" value="'+second+'">' +
                    '            <label class="form-check-label" for="'+second+'">'+second+'</label>' +
                    '        </div>' +
                    '    </div>'
                );
            }

            for (var key in filters) {
                if (filters.hasOwnProperty(key))
                {
                    let filter = key.replace(" ", "");
                    $('#filters').append(
                        '<button class="btn  btn-block filter-button" type="button" data-toggle="collapse" data-target="#'+filter+'" aria-expanded="false">' +
                        '         <span>'+key+'</span>' +
                        '</button>' +
                        '        <div class="collapse" id="'+filter+'">' +
                        '            <div class="card card-body filter-filed" >' +
                        '                <div class="md-form input-group">' +
                        '                    <div class="container">' +
                        '                        <div class="row">' +
                        '                            <div class="col">' +
                        '                                <div class="custom-control custom-checkbox">' +
                        '                                    <input type="checkbox" class="form-check-input" name="Cont-tvType" id="ЖК" value="Тип: ЖК">' +
                        '                                    <label class="custom-control-label" for="ЖК">Param</label>' +
                        '                                </div>' +
                        '                            </div>' +
                        '                        </div>' +
                        '                    </div>' +
                        '                </div>' +
                        '            </div>' +
                        '        </div>'
                    );
                }
            }

            /*$(filter).each(function()
            {

                $('#filters').append(
                    '<button class="btn  btn-block filter-button" type="button" data-toggle="collapse" data-target="#tv-type" aria-expanded="false">\n' +
                    '            <span>Тип телевизора</span>\n' +
                    '        </button>\n' +
                    '        <div class="collapse" id="tv-type">\n' +
                    '            <div class="card card-body filter-filed" >\n' +
                    '                <div class="md-form input-group">\n' +
                    '                    <div class="container" style="background-color: #f0f4f9">\n' +
                    '                        <div class="row">\n' +
                    '                            <div class="col">\n' +
                    '                                <div class="custom-control custom-checkbox">\n' +
                    '                                    <input type="checkbox" class="form-check-input" name="Cont-tvType" id="ЖК" value="Тип: ЖК">\n' +
                    '                                    <label class="custom-control-label" for="ЖК">ЖК</label>\n' +
                    '                                </div>\n' +
                    '                            </div>\n' +
                    '                        </div>\n' +
                    '                    </div>\n' +
                    '                </div>\n' +
                    '            </div>\n' +
                    '        </div>'
                );

            });*/

            /*$(brands).each(function()
            {
                //console.log('KOK ' + this);
                $('#brands-view').append(
                    '<div class="row">' +
                    '        <div class="col-6">' +
                    '            <input type="checkbox" name="brand" class="form-check-input" id="'+this+'" value="'+this+'">' +
                    '            <label class="form-check-label" for="'+this+'">'+this+'</label>' +
                    '        </div>' +
                    '</div>'
                );

            });*/
        }
    });
}

function resolveURL() {
    var url = decodeURI(document.URL);
    if (url.includes("?")) {
        url = url.substring(0, url.lastIndexOf('?'));
    }
    return url;
}

function filterProducts(e) {
    e.preventDefault();
    var filters = {};

    $('input:checkbox:checked').each(function()
    {
        let key = this.getAttribute("name");
        let val = $(this).val();

        if (!filters.hasOwnProperty(key)) {
            filters[key] = val;
        }
        else filters[key] = filters[key].concat(", ").concat(val);
    });

    $('input:text').each(function()
    {
        let inpKey = this.getAttribute("id");
        let inpVal = $(this).val();

        if (inpVal)
        {
            console.log(inpKey);
            console.log(inpVal);
            if (!filters.hasOwnProperty(inpKey))
            {
                filters[inpKey] = inpVal;
            }
            else filters[inpKey] = filters[inpKey].join(", ").concat(inpVal);
        }
    });
    console.log(filters);

    const url = resolveURL();
    filters = JSON.stringify(filters);

    $.ajax({
        url: url, ///+'?page=2'
        type: 'POST',
        dataType: 'json',
        data: filters,
        processData: false,
        headers: {'Content-Type': 'application/json'},
        complete: function(productsAndOrderedID)
        {
            const response   = JSON.parse(JSON.stringify(productsAndOrderedID));
            const products   = response.responseJSON[0].content;
            const total      = response.responseJSON[0].totalElements;
            const orderedIDs = response.responseJSON[1];

            var currentPage = response.responseJSON.number;
            var nextPage = url+'?page='+currentPage+1;
            var prevPage = url+'?page='+currentPage-1;

            $("#products").empty();

            $('#pageable').empty().append(
                '<ul class="pagination" style="margin-left: -0.80rem; margin-bottom: 0; margin-top: 1rem;">'+
                '<li class="page-item disabled">'+
                '<a class="page-link" href="#" tabindex="-1">Страницы</a></li>'+
                '<li class="page-item">'+
                '<a class="page-link" href="#/*'+prevPage+'*/">Назад</a></li>'+
                '<li class="page-item"><a class="page-link" href="#/*'+nextPage+'*/">Вперед</a></li></ul>'
            );
            $('#pageable1').empty().append(/*новый пагинатор*/);///html.load

            $('#products-found').empty().append(
                '<small>Всего товаров: '+total+'</small>'
            );

            for (var item in products)
            {
                let product = products[item];
                var productCard = constructProductCard(product, orderedIDs);
                $("#products").append(productCard); ///load html block
            }
        }
    });
}

function constructProductCard(product, orderedIDs) {
    const productName = product.singleType.concat(" ").concat(product.originalBrand).concat(" ").concat( product.modelName);

    var buttonID = 'addToOrderDiv' + product.productID;
    buttonID = replaceAll(buttonID, ".", "");

    let productButton;

    if (orderedIDs.length !== 0 && orderedIDs.includes(product.productID))
    {
        productButton = '<a type="button" class="btn btn-danger btn-md" style="background-color: #e52d00 !important;" href="http://localhost:8080/order">Оформить заказ</button></a>';
    }
    else
    {
        productButton = '<button type="submit" onclick="addToOrder(this)" class="btn btn-rounded btn-outline-danger b-add" name="addToOrder" id="addToOrder'+product.productID+'" value="'+product.productID+'">В корзину</button>'
    }

    return '<div class="card product-card">'+
        '<div class="view overlay">'+
        '<img class="img-fluid scale-pic" src="'+product.originalPic+'" alt="Product pic">'+
        '<a href="#">'+
        '<div class="mask rgba-white-slight"></div>'+
        '</a>'+
        '</div>'+
        '<div class="card-body">'+
        '<h5 class="card-title">'+
        '<a href="/products/info/'+product.productID+'">'+
        '<strong>'  +
        productName +
        '</strong>' +
        '</a>'+
        '</h5>'+
        '<p class="card-text">'+
        '<strong><i>'+product.productType+'</i></strong></p>'+
        '<h3><strong>'+(product.finalPrice).toLocaleString('ru')+' ₽</strong></h3>'+
        '<p>Бонус: '+product.bonus+' ₽</p>'+
        '<div id='+buttonID+'>'+
        productButton +
        '</div>'+
        '</div>'+
        '</div>';
}

