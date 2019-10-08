function displaySomeGroups(element) {
    var productCategory = element.getAttribute("name");
    console.log(productCategory);

    $.ajax({
        url: '/catalog',
        type: 'POST',
        dataType: 'json',
        data: productCategory,
        processData: false,
        headers: {'Content-Type': 'application/json'},
        complete: function(productGroups)
        {
            const groups = productGroups.responseJSON;
            console.log(groups);

            let divID = '#deck'+productCategory;
            console.log(divID);

            $(divID).empty();
            for (var group in groups)
            {
                let name, pic, link;
                if (groups[group].includes(';')) {
                    pic = groups[group].substr(groups[group].indexOf(';')+1);
                    name = groups[group].substr(0, groups[group].indexOf(';'));
                    link = replaceAll(name.toLowerCase(), " ", "_");
                }
                else {
                    name = groups[group];
                    pic = '/../img/nophoto.jpg';
                    link = replaceAll(groups[group].toLowerCase(), " ", "_");
                }

                $(divID).append(
                    '<div class="card catalog-card">\n' +
                    '<div class="view overlay catalog-pic">\n' +
                    '<img class="img-fluid scale-pic" src="'+pic+'" alt="Card image cap">\n' +
                    '<a href="/products/'+productCategory+'/'+link+'">\n' +
                    '<div class="mask rgba-white-slight"></div>\n' +
                    '</a>\n' +
                    '</div>\n' +
                    '<div class="card-body">\n' +
                    '<h4 class="card-title">'+name+'</h4>\n' +
                    '</div>\n' +
                    '</div>'
                )
            }
        }
    });
}

function removeModCoeff(e) {
    var request = e.name;
    $.post({
        url: '/supplier/products/removemodcoff',
        type: 'POST',
        dataType: 'json',
        data: request,
        processData: false,
        headers: {'Content-Type': 'application/json'},
        complete: function()
        {
            console.log('ok');
            location.reload();
        }
    });
}

function removePriceMod(e) {
    var productID = e.name;
    $.post({
        url: '/supplier/products/removemod',
        type: 'POST',
        dataType: 'json',
        data: productID,
        processData: false,
        headers: {'Content-Type': 'application/json'},
        complete: function()
        {
            console.log('ok');
            location.reload();
        }
    });
}

function addNewCoeff(label) {
    const val = label.getAttribute("name");
    console.log(val);
    $(newCoeff).empty().append(
        '<input type="text" id="newCoeffEdit" value="'+val+'" style="width: 3rem">'
    )
}

function saveNewCoeff(button) {
    console.log(button);
    const request  = button.getAttribute("name");
    const newCoeff = $(newCoeffEdit).val();

    data = [];
    data.push(request, newCoeff);
    data = JSON.stringify(data);

    $.post({
        url: '/supplier/products/defaultCoefficient',
        type: 'POST',
        dataType: 'json',
        data: data,
        processData: false,
        headers: {'Content-Type': 'application/json'},
        complete: function()
        {
            console.log('ok');
            location.reload();
        }
    });

}

function editProduct(product) {
    const productDivID = '#'+product.id;
    const productID = product.getAttribute("name").substr(0, product.getAttribute("name").indexOf(";"));
    const productFinalPrice = product.getAttribute("name").substr(product.getAttribute("name").indexOf(";")+1);

    $(productDivID).empty().append(
        '<input type="text" class="editPrice" id="'+productDivID+'" value="'+productFinalPrice+'" style="width: 5rem; background-color: #a0ffc8" name="'+productID+';'+productFinalPrice+'">'
    );
}

function saveProduct() {
    var data = {};

    $('input:text[class="editPrice"]').each(function()
    {
        const name =  this.getAttribute("name");
        let productID = name.substr(0, name.indexOf(";")).toString();
        let productFinalPriceDB = name.substr(name.indexOf(";")+1);
        let newPrice = this.value;

        data[productID] = newPrice;
    });

    data = JSON.stringify(data);

    $.ajax({
        url: '/supplier/products/save',
        type: 'POST',
        dataType: 'json',
        data: data,
        processData: false,
        headers: {'Content-Type': 'application/json'},
        complete: function(response)
        {
            console.log(response);
            if ((response.responseText) === 'true') {
                alert('Saved!');
                console.log('ok');
                location.reload();
            }
        }
    });

}

$(document).ready(function(){
    displayBrands()
});

function escapeRegExp(str) {
    return str.replace(/([.*+?^=!:${}()|\[\]\/\\])/g, "\\$1");
}
function replaceAll(str, find, replace) {
    return str.replace(new RegExp(escapeRegExp(find), 'g'), replace);
}

String.prototype.capitalize = function() {
    return this.charAt(0).toUpperCase() + this.slice(1);
};

function displayBrands() {
    const url = resolveURL();
    const request = url.substring(url.lastIndexOf("/")+1);

    $.ajax({
        url: 'http://localhost:8080/brands',
        type: 'POST',
        dataType: 'json',
        data: request,
        processData: false,
        headers: {'Content-Type': 'application/json'},
        complete: function(payload)
        {
            console.log(payload);
            const brands  = payload.responseJSON[0];
            const filters = payload.responseJSON[1];

            $('#brands-view').empty();

            for (let i = 0; i < brands.length; i+=2) {
                let first = brands[i];
                let second = brands[i+1];

                if (second !== undefined){
                    $('#brands-view').append(
                        '<div class="row">' +
                        '        <div class="col" style="margin-left: -1rem;">' +
                        '            <input type="checkbox" onclick="filterProducts()" name="brand" class="form-check-input" id="'+first+'" value="'+first+'">' +
                        '            <label class="form-check-label" for="'+first+'">'+first+'</label>' +
                        '        </div>' +
                        '        <div class="col" style="margin-left: -1.5rem; width: 23rem !important;" >' +
                        '            <input type="checkbox" onclick="filterProducts()" name="brand" class="form-check-input" id="'+second+'" value="'+second+'">' +
                        '            <label class="form-check-label" for="'+second+'">'+second+'</label>' +
                        '        </div>' +
                        '    </div>'
                    );
                }
                else {
                    $('#brands-view').append(
                        '<div class="row">' +
                        '        <div class="col" style="margin-left: -1rem;">' +
                        '            <input type="checkbox" onclick="filterProducts()" name="brand" class="form-check-input" id="'+first+'" value="'+first+'">' +
                        '            <label class="form-check-label" for="'+first+'">'+first+'</label>' +
                        '        </div>' +
                        '    </div>'
                    );
                }
            }

            for (var key in filters)
            {
                if (filters.hasOwnProperty(key))
                {
                    let filter = replaceAll(key," ","").replace("(","").replace(")","").replace('.','').replace('-','').replace('/','');
                    var onlyDigits = /^\d+[,]?\d*$/g.exec(filters[key][0]);

                    /*ФИЛЬТРЫ С МНОЖЕСТВОМ*/
                    if (filters[key].includes("есть") || filters[key].includes("да"))
                    {
                        document.getElementById("featButton").style.display = "block";
                        let val = filters[key];
                        console.log(val);

                        $('#feat-element').append(
                            '<div class="custom-control custom-checkbox">' +
                            '<input type="checkbox" onclick="filterProducts()" class="form-check-input" name="Cont-MultiParams-'+key+'" id="'+filter+'" value="'+key+': '+val+'">' +
                            '<label class="custom-control-label" for="'+filter+'"><strong>'+key+'</strong></label>' +
                            '</div>'
                        );
                    }

                    /*ФИЛЬТРЫ С ЧИСЛАМИ*/
                    else if (onlyDigits)
                    {
                        console.log(filters[key]);
                        for (let i = 0; i < filters[key].length; i++) {
                            filters[key][i] = parseFloat(filters[key][i].replace(",","."))
                        }
                        console.log(filters[key].sort(function(a,b) { return a - b;}));

                        let min = filters[key][0];
                        let max = filters[key][filters[key].length-1];

                        $('#filters').append(
                            '<button class="btn btn-block filter-button" type="button" data-toggle="collapse" data-target="#'+filter+'" aria-expanded="false">' +
                            '            <span>'+key+'</span>' +
                            '</button>' +
                            '        <div class="collapse" id="'+filter+'">' +
                            '           <div class="card"><div class="card-body">'+
                            '            <div class="md-form input-group">' +
                            '                <div class="container">' +
                            '                    <div class="row">' +
                            '                        <div class="col-5">' +
                            '                            <input type="text" onkeyup="filterProducts()" class="form-control" name="'+min+'" id="Comp-Min-'+key+';'+min+'/'+max+'" placeholder="От '+min+'">' +
                            '                        </div>' +
                            '                        <div class="col-5">' +
                            '                            <input type="text" onkeyup="filterProducts()" class="form-control" name="'+max+'" id="Comp-Max-'+key+';'+min+'/'+max+'" placeholder="До '+max+'">' +
                            '                        </div>' +
                            '                    </div>' +
                            '                </div>' +
                            '                </div>' +
                            '                </div>' +
                            '            </div>' +
                            '        </div>'
                        );
                    }

                    /*ФИЛЬТРЫ С СОДЕРАЖНИЕМ*/
                    else
                    {
                        let vals = '';
                        for (let i = 0; i < filters[key].length; i++)
                        {
                            id = replaceAll(filters[key][i], " ", "")+`f${(~~(Math.random()*1e8)).toString(16)}`;
                            filter = filter.replace("/","");
                            vals+=
                                '<div class="row" style="max-height: 55rem !important;">' +
                                '<div class="col" style="max-height: 55rem !important;">' +
                                '<input type="checkbox" onclick="filterProducts()" class="form-check-input" name="Cont-'+filter+'" id="'+id+'" value="'+key+': '+filters[key][i]+';">' +
                                '<label class="custom-control-label" for="'+id+'" style="height: auto !important; "><strong>'+filters[key][i].capitalize().replace(",",", ")+'</strong></label>' +
                                '</div>' +
                                '</div>'
                        }

                        $('#filters').append(
                            '<button class="btn btn-block filter-button" type="button" data-toggle="collapse" data-target="#'+filter+'" aria-expanded="false">' +
                            '         <span>'+key+'</span>' +
                            '</button>' +
                            '        <div class="collapse" id="'+filter+'">' +
                            '            <div class="card card-body filter-filed" >' +
                            '                <div class="md-form input-group">' +
                            '                    <div class="container">' +
                            vals +
                            '                    </div>' +
                            '                </div>' +
                            '            </div>' +
                            '        </div>'
                        );
                    }
                }
            }
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

function filterProducts() {
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

    $('input:radio:checked').each(function()
    {
        filters['sortOrder'] = $(this).val();
    });

    const url = resolveURL();
    filters = JSON.stringify(filters);

    $.ajax({
        url: url, /// +'?page=n'
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
            $('#pageable1').empty().append(/*новый пагинатор*/); /// html.load

            $('#products-found').empty().append(
                '<small>Всего товаров: '+total+'</small>'
            );

            for (var item in products)
            {
                let product = products[item];
                var productCard = constructProductCard(product, orderedIDs);
                $("#products").append(productCard); /// load html block
            }
        }
    });
}

function constructProductCard(product, orderedIDs) {
    let singleType = '<span style="color: #e52d00;">'+product.singleType+'</span>';
    let brand = '<span style="color: #222222;">'+product.originalBrand+'</span>';
    let model = '<span style="color: #222222;">'+product.modelName+'</span>';
    let productName = singleType.concat(' ').concat(brand).concat(' ').concat(model);
    if (product.singleType === null) {
        productName = '<span style="color: #222222;">'+product.originalName+'</span>';
    }

    var buttonID = 'addToOrderDiv' + product.productID;
    buttonID = replaceAll(buttonID, ".", "");

    let productButton;
    if (product.isAvailable)
    {
        if (orderedIDs.length !== 0 && orderedIDs.includes(product.productID)) {
            productButton = '<a type="button" class="btn btn-danger btn-sm" style="background-color: #e52d00 !important;" href="http://localhost:8080/order">Оформить заказ</button></a>';
        }
        else productButton = '<button type="submit" onclick="addToOrder(this)" class="btn btn-rounded btn-outline-danger btn-sm b-add" name="addToOrder" id="addToOrder'+product.productID+'" value="'+product.productID+'">В корзину</button>'

    }
    else productButton = '<p style="color: #c40030; font-size: 1.5rem; padding-top: 0.5rem;"><strong>Нет в наличии!</strong></p>';

    let pic;

    if (product.originalPic/*originalPic*/ !== null) {
        pic = product.originalPic/*originalPic*/
    }
    else pic = '/../img/nophoto.jpg';

    let productType = product.productType != null ? product.productType : product.originalGroup;

    return '<div class="card product-card">'+
        '<div class="view overlay" style="min-height: 8rem !important; max-height: 8rem !important;">'+
        '<img class="img-fluid scale-pic" src="'+pic+'" alt="Фото пока нет :(">'+
        '</div>'+
        '<div class="card-body">'+
        '<h5 class="card-title" style="height: auto !important;">'+
        '<a href="/products/info/'+product.productID.replace("\\","_")+'">'+
        '<strong style="font-size: 1rem">'  +
        productName +
        '</strong>' +
        '</a>'+
        '</h5>'+
        '<p class="card-text" style="margin-top: 1rem">'+
        '<strong><i>'+productType+'</i></strong></p>'+
        '<h3><strong>'+(product.finalPrice).toLocaleString('ru')+' </strong><span style="font-size: .9rem">руб</span></h3>'+
        '<p>Бонус: '+product.bonus+' </p>'+
        '<div id='+buttonID+'>'+
        productButton +
        '</div>'+
        '</div>'+
        '</div>';
}


function im() {
    $('#image').addClass('transition');
}
function iii() {
    $('#image').removeClass('transition');
}
