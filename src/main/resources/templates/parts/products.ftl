<!--<div>
    <a href="/electronics?sortby=lowest" class="btn btn-light" role="button">Самые дешевые!</a>
    <a href="/electronics?sortby=highest" class="btn btn-light" role="button">Самые дорогие!</a>
    <a href="/electronics" class="btn btn-light" role="button">Сбросить</a>
</div>-->

<div class="shop">
    <div class="sort">
        <form class="pricesort" method="get">
            <label for="cheap">Не дешевле:</label>
            <input type="text" id="cheap" name="cheap">

            <label for="expen">Не дороже:</label>
            <input type="text" id="expen" name="expen">

            <input type="submit" value="Найти">
        </form>
    </div>
    <div class="products">
        <div class="card-columns">
            <#list products as product>
                <div class="card my-3">
                    <div class="card-body">
                        <h5 class="card-title">
                            ${product.brand}
                            ${product.model}
                        </h5>
                        <p class="card-text">
                            ${product.category}
                            ${product.type}
                        </p>
                        <p class="card-text">
                            ${product.price}
                        </p>
                    </div>
                </div>
            </#list>
        </div>
    </div>
    <div class="footer clearfix">Футер</div>
</div>
