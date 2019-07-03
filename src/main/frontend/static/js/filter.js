$(document).ready(function(){
    $('#filter-button')         .on('click', collectFilters);
});
$(document).ready(function(){
    $('input[type="radio"]')    .on('change', collectFilters);
});
$(document).ready(function(){
    $('input[type="checkbox"]') .on('change', collectFilters);
});
/*$(document).ready(function(){
    $('input[type="text"]')     .on('keyup', collectFilters);
});*/

/*$(document).ready(function() {
    $('.mdb-select').materialSelect();
});*/


function resolveURL() {
    var url = decodeURI(document.URL);
    if (url.includes("?")) {
        url = url.substring(0, url.lastIndexOf('?'));
    }
    return url;
}

function collectFilters(e) {
    e.preventDefault();

    var url = resolveURL();

    var filters = constructFiltersData(url);
    deleteEmptyParams(filters);

    filters = JSON.stringify(filters);

    $.ajax({
        url: url/*+'?page=2'*/,
        type: 'POST',
        dataType: 'json',
        data: filters,
        processData: false,
        headers: {'Content-Type': 'application/json'},
        complete: function(productsAndOrderedID)
        {
            const response  = JSON.parse(JSON.stringify(productsAndOrderedID));
            const products  = response.responseJSON.content;
            const total     = response.responseJSON.totalElements;

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
                /*let displayParams = resolveDisplayType(product);
                let orderButton = resolveOrderButton(product, orderedID);*/

                var productCard = constructProductCard(product/*, displayParams, orderButton*/);

                $("#products").append(productCard); ///load html block
            }
        }
    });
}

function constructFiltersData(url) {
    var brands = [];
    $('input:checked').each(function()
    {
        if ($(this).attr('name') === 'brand')
        {
            brands.push($(this).val());
            console.log($(this).val());
        }
    });

    var filters = {
        'sortmin' : $('#sortmin').val(),
        'sortmax' : $('#sortmax').val(),
        'brand'   : brands
    };

    const request = url.substring(url.lastIndexOf('/')+1);
    switch (request)
    {
        case 'телевизоры'           : return tv        (filters);
        case 'кабели_тв'            : return tvCables  (filters);
        case 'кронштейны_тв'        : return tvBrackets(filters);
        case 'музыкальные_центры'   : return muzCenters(filters);
        case 'телемебель'           : return tvMebel (filters);
        default : return filters;
    }

    function tvMebel(filters) {
        var tvMebelType = [], tvMebelWidth = [], tvMebelLoad = [];
        ($('input:checkbox:checked').each(function()
        {
            switch (this.getAttribute("name"))
            {
                case 'tvMebelType'    : tvMebelType.push    ($(this).val());   break;
                case 'tvMebelWidth'   : tvMebelWidth.push   ($(this).val());   break;
                case 'tvMebelLoad'    : tvMebelLoad.push    ($(this).val());   break;
            }
        }));
        filters.tvMebelType      = tvMebelType;
        filters.tvMebelWidth     = tvMebelWidth;
        filters.tvMebelLoad      = tvMebelLoad;
        filters.tvMebelDiagMin   = $('#tvMebelDiagMin').val();
        filters.tvMebelDiagMax   = $('#tvMebelDiagMax').val();
        console.log(filters);
        return filters;
    }

    function muzCenters(filters) {
        var muzCenterType = [], muzCenterMainBlock = [], muzCenterAcoustic = [], muzCenterParams = [];
        ($('input:checkbox:checked').each(function()
        {
            switch (this.getAttribute("name"))
            {
                case 'muzCenterType'        : muzCenterType.push        ($(this).val());   break;
                case 'muzCenterMainBlock'   : muzCenterMainBlock.push   ($(this).val());   break;
                case 'muzCenterAcoustic'    : muzCenterAcoustic.push    ($(this).val());   break;
                case 'muzCenterParams'      : muzCenterParams.push      ($(this).val());   break;
            }
        }));
        filters.muzCenterType       = muzCenterType;
        filters.muzCenterMainBlock  = muzCenterMainBlock;
        filters.muzCenterAcoustic   = muzCenterAcoustic;
        filters.muzCenterParams     = muzCenterParams;
        filters.muzCenterPowerMin   = $('#muzCenterPowerMin').val();
        filters.muzCenterPowerMax   = $('#muzCenterPowerMax').val();
        console.log(filters);
        return filters;
    }

    function tvBrackets(filters) {
        var tvBracketsType = [], tvBracketsMount =[];
        ($('input:checkbox:checked').each(function()
        {
            switch (this.getAttribute("name"))
            {
                case 'tv-brackets-type'     : tvBracketsType.push   ($(this).val()); break;
                case 'tv-brackets-mount'    : tvBracketsMount.push  ($(this).val()); break;
            }
        }));
        filters.tvBracketsType         = tvBracketsType;
        filters.tvBracketsMount        = tvBracketsMount;
        filters.tvBracketsLoadMin      = $('#tv-brackets-load_min').val();
        filters.tvBracketsLoadMax      = $('#tv-brackets-load_max').val();
        filters.tvBracketsDiagMin      = $('#tv-brackets-diag_min').val();
        filters.tvBracketsDiagMax      = $('#tv-brackets-diag_max').val();
        filters.tvBracketsWallDistMin  = $('#tvBracketsWallDistMin').val();
        filters.tvBracketsWallDistMax  = $('#tvBracketsWallDistMax').val();
        console.log(filters);
        return filters;
    }

    function tv(filters){
        var tv_resolution = [], tv_params = [], tv_type = [];
        ($('input:checkbox:checked').each(function()
        {
            switch (this.getAttribute("name"))
            {
                case 'tv_resolution' : tv_resolution.push($(this).val()); break;
                case 'tv_params'     : tv_params.push($(this).val()); break;
                case 'tv_type'       : tv_type.push($(this).val()); break;
            }
        }));
        filters.tvResolution   = tv_resolution;
        filters.tvParams       = tv_params;
        filters.tvType         = tv_type;
        filters.diagMin        = $('#diag_min').val();
        filters.diagMax        = $('#diag_max').val();
        filters.hzMin          = $('#hz_min').val();
        filters.hzMax          = $('#hz_max').val();
        console.log(filters);
        return filters;
    }

    function tvCables(filters) {
        var tvCables = [], tvCablesTypes = [], tvCablesLength = [];
        $('input:checkbox:checked').each(function ()
        {
            switch (this.getAttribute("name")) {
                case 'tv-cables'            : tvCables.push($(this).val());         break;
                case 'tv-cables-type'       : tvCablesTypes.push($(this).val());    break;
                case 'tv-cables-length'     : tvCablesLength.push($(this).val());   break;
            }
        });
        filters.tvCables       = tvCables;
        filters.tvCablesType   = tvCablesTypes;
        filters.tvCablesLength = tvCablesLength;
        console.log(filters);
        return filters;
    }
}

function deleteEmptyParams(filters) {
    for (var fieldKey in filters)
    {
        if (filters[fieldKey] === undefined || filters[fieldKey].length === 0) {
            delete filters[fieldKey];
        }
    }
}

function selected(type) {
    return url.includes(encodeURI(type));
}

function constructProductCard(product/*, displayParams, orderButton*/) {
    const productName = product.singleType.concat(" ").concat(product.originalBrand).concat(" ").concat( product.modelName);
    return '<div class="card product-card">'+
        '<div class="view overlay">'+
        /*<#if product.pic??>*/
        '<img class="img-fluid scale-pic" src="'+product.originalPic+'" alt="Product pic">'+
        '<a href="#">'+
        '<div class="mask rgba-white-slight"></div>'+
        '</a>'+
        /*</#if>*/
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
        '<strong><i>'+product.productType+'</i></strong>'+
        '</p>'+
        '<h3><strong>'+(product.price).toLocaleString('ru')+' ₽</strong></h3>'+
        '<div>'+
        '<button type="submit" onclick="addToOrder(this)" class="btn btn-rounded btn-outline-danger b-add" name="addToOrder" id="addToOrder${product.productID}" value="${product.productID}">'+
        'В корзину'+
        '</button>'+
        /*<#if orderedProductsID?? && orderedProductsID?seq_contains('${product.productID}')>
            <a type="button" class="btn btn-danger btn-md" style="background-color: #e52d00 !important;" href="http://localhost:8080/order">Оформить заказ</button></a>
        <#else>
        <div id="addToOrderDiv${product.productID}">
            <button type="submit" onclick="addToOrder(this)" class="btn btn-rounded btn-outline-danger b-add" name="addToOrder" id="addToOrder${product.productID}" value="${product.productID}">
            В корзину
        </button>
        </div>
        </#if>*/
        '</div>'+
        '</div>'+
        '</div>';
}

function resolveOrderButton(product, productsID) {
    if (productsID.includes(product.productID.toString())) {
        return '<br><a type="button" class="btn btn-danger btn-md" style="background-color: #e52d00 !important;" href="http://localhost:8080/order">Оформить заказ</button></a>';
    }
    else return '<div orderedID="addToOrderDiv'+product.productID.toString()+'">'+
        '<button type="submit" onclick="addToOrder(this)" class="btn btn-rounded btn-outline-danger b-add" modelName="addToOrder" orderedID="addToOrder'+product.productID+'" value="'+product.productID+'">'+
        'В корзину'+
        '</button>'+
        '</div>';
}

/*function resolveDisplayType(product) {
if      (selected("телевизоры"))    return displayTV(product);
else if (selected("stoves"))        return displayStoves(product);

function displayTV(product) {
    return disp =
        'Диагональ: '            + '<strong>' + product.productParams.diagonal   + '</strong>' +
        '\nРазрешение: '         + '<strong>' + product.productParams.resolution + '</strong>' +
        '<br>'+'\nОсобенности: ' + '<strong>' + product.productParams.tvFeatures + '</strong>'
}
function displayStoves(product) {
    return disp = '\nГабариты: '   + '<strong>' + product.productParams.stoveDimensions + '</strong>'
}
}*/


