<#import "../parts/template.ftl" as t>
<#include "../parts/security.ftl">
<@t.template>

<div class="container-fluid">
    <div class="row" style="margin-left: 10rem;">
        <#if order??>
            <#if !isUser>
                <h3 style="margin-top: 2rem; margin-bottom: 2rem">Ваш заказ</h3>
            <#else>
                <h3 style="margin-top: 2rem; margin-bottom: 2rem">Ваш заказ, ${user.firstName}</h3>
            </#if>
        <#else>
            <h3 style="margin-top: 2rem">Товаров пока нет</h3>
        </#if>
    </div>
    <div class="row">
        <div class="col-8">
            <div class="card-group" id="bucket-products" name="cards-layout" style="margin-left: 10rem; width: 65rem;">
                <#if orderedProducts?has_content>
                    <#list orderedProducts as product>
                        <div class="card ordered-card mb-4">
                            <div class="view overlay">
                                <img class="scale-pic" src="${product.pic}" alt="Card image cap">
                                <a href="#!">
                                    <div class="mask rgba-white-slight"></div>
                                </a>
                            </div>
                            <div class="card-body">
                                <h4 class="card-title">
                                    <div class="mb-3">${product.productType}</div>
                                    <a href="http://localhost:8080/info/${product.productID}">${product.productName}</a>
                                </h4>
                                <h4>
                                    <strong><i id="total-price${product.orderedID?c}">${product.totalPrice} ₽</i></strong> за <span id="prAm${product.orderedID?c}">${product.orderedAmount}</span> шт.
                                </h4>
                                <p id="productTotalBonus${product.orderedID?c}">
                                    <#assign productBonus = product.bonus * product.orderedAmount>
                                    Бонус за покупку: ${productBonus}
                                </p>
                                <p class="card-text" id="cart-text-buttons" name="cart-text-buttons">
                                    <button type="button" onclick="changeAmount(this)" id="${product.orderedID?c}" name="product-less" value=" ${product.productID}" class="btn btn-outline-danger waves-effect">-</button>
                                    <span class="badge badge-primary badge-pill" id="amount${product.orderedID?c}" name="${product.orderedID}">${product.orderedAmount}</span>
                                    <button type="button" onclick="changeAmount(this)" id="${product.orderedID?c}" name="product-more" value=" ${product.productID}" class="btn btn-outline-success waves-effect">+</button>
                                </p>
                            </div>
                            <button type="submit" onclick="removeFromOrder(this)" class="btn btn-danger btn-md" name="remove-product" id="remove-product" value="${product.orderedID?c}">Удалить</button>
                        </div>
                    </#list>
                </#if>
            </div>
            <div>
                <#if order?? && order.productsAmount != 0>
                    <button onclick="confirmOrderList()" id="order-button" type="button" class="btn btn-success btn-lg btn-block"  style="margin-bottom: 3rem;margin-left: 10rem; width: 60rem;">Заказ подтверждаю!</button>
                    <button onclick="editOrder()" id="edit-order" value="${order.orderID}" class="btn btn-indigo btn-lg btn-block mt-2" style="margin-bottom: 3rem;margin-left: 10rem; width: 60rem; display: none">Изменить заказ!</button>
                <#else>
                    <a type="button" href="/" class="btn blue-gradient btn-lg btn-block" style="margin-left: 10rem; width: 60rem;">Вернуться за покупками</a>
                </#if>
                <a type="button" id="goToBuy" href="/" class="btn blue-gradient btn-lg btn-block" style="margin-left: 10rem; width: 60rem; display: none">Вернуться за покупками</a>
            </div>
        </div>
        <div class="col-4">
            <#if order??>
                <#if order.totalAmount != 0>
                    <div class="row">
                        <div class="col" style="margin-top: -3rem;">
                            <div id="order-details">
                                <h3 class="mt-2" style="margin-bottom: 3vh">
                                    Заказ на сумму <strong id="order-totalPrice">${order.totalPrice} ₽</strong>
                                    <br class="mb-1">Товаров <b id="order-products">${order.productsAmount}</b>
                                    <br class="mb-1">Всего единиц <b id="order-amount">${order.totalAmount}</b>
                                </h3>
                                <#if !isUser>
                                    <h5 style="margin-bottom: 3rem">
                                        За заказ будет начисленно <strong id="bonusAmount">${order.totalBonus}</strong> бонусов!
                                        <br><a href="http://localhost:8080/user/login">Войдите</a>, что бы получить скидку
                                    </h5>
                                <#else>
                                    <h5 style="margin-bottom: 1rem">
                                        За заказ будет начисленно <strong id="bonusAmount">${order.totalBonus}</strong> бонусов!
                                    </h5>
                                <#--<h5 style="margin-bottom: 1rem">
                                    Доступно бонусов: <strong>${user.bonus}</strong>, Ваша скидка - <strong id="total-discount">${discount}%</strong>
                                </h5>
                                <button type="button" onclick="applyDiscount()" class="btn btn-default btn-sm" style="margin-bottom: 3rem">Применить скидку!</button-->
                                </#if>
                            </div>
                        </div>
                    </div>
                </#if>
            </#if>
        </div>
    </div>
    <div class="row order-deal" id="order-deal" style="width: 95rem;">
        <div class="row" style="margin-left: 10rem; width: 95rem;">
            <#if isUser>
                <div class="col">
                    <h5 style="margin-bottom: 1rem">
                        Доступно бонусов: <strong>${user.bonus}</strong>, Ваша скидка: <strong id="total-discount">${discount}%</strong>
                    </h5>
                    <button type="button" onclick="applyDiscount(${user.bonus}, ${discount}, ${order.orderID})" class="btn btn-default btn-sm" style="margin-bottom: 3rem">Применить скидку!</button
                </div>
            </#if>
            <div class="col-8" id="order-details">
                <section>
                    <h4 class="h4-responsive font-weight-bold text-center" id="contact-info">Ваши контактные данные</h4>
                    <div class="row">
                        <div class="col">
                            <div id="results"></div>
                            <#if !isUser><#--${firstName??"val", ""}-->
                                <form id="contact-session" name="contact-form" method="POST">
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="md-form mb-0">
                                                <input type="text" id="firstName" name="firstName" class="form-control" required>
                                                <label for="name" class="">Ваше имя</label>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="md-form mb-0">
                                                <input type="text" id="lastName" name="lastName" class="form-control" required>
                                                <label for="family" class="">Ваша фамилия</label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="md-form mb-0">
                                                <input type="text" id="username" name="username" class="form-control" required>
                                                <label for="mobile" class="">Ваш телефон</label>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="md-form mb-0">
                                                <input type="email" id="email" name="email" class="form-control" required>
                                                <label for="email" class="">Ваш e-mail</label>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            <#else><#--${firstName??"val", ""}-->
                                <form id="contact-user" name="contact-form" method="POST" required>
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="md-form mb-0">
                                                <input type="text" id="firstName" name="firstName" class="form-control" value="${firstName}" required>
                                                <label for="name" class="">Ваше имя</label>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="md-form mb-0">
                                                <input type="text" id="lastName" name="lastName" class="form-control" value="${lastName}" required>
                                                <label for="family" class="">Ваша фамилия</label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="md-form mb-0">
                                                <input type="text" id="username" name="username" class="form-control" value="${mobile}" required>
                                                <label for="mobile" class="">Ваш телефон</label>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="md-form mb-0">
                                                <input type="email" id="email" name="email" class="form-control" value="${email}" required>
                                                <label for="email" class="">Ваш e-mail</label>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </#if>
                            <h4 class="h4-responsive font-weight-bold text-center my-4">Способ получения товара</h4>
                            <ul class="nav md-pills pills-secondary">
                                <li class="nav-item">
                                    <a class="nav-link active" data-toggle="tab" href="#panel11" role="tab">Самовывоз со склада</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" data-toggle="tab" href="#panel12" role="tab">Доставка курьером</a>
                                </li>
                            </ul>
                            <div class="tab-content pt-0">
                                <div class="tab-pane fade in show active" id="panel11" role="tabpanel">
                                    <br><h5>Заберите ваш заказ со склада по адресу: город Чебаркуль, Ленина 32</h5>
                                </div>
                                <div class="tab-pane fade" id="panel12" role="tabpanel">
                                    <br>
                                    <h3>Адрес для доставки:</h3>
                                    <form id="self-delivery-block" name="contact-form" action="mail.php" method="POST">
                                        <div class="row">
                                            <div class="col">
                                                <div class="md-form">
                                                    <input type="text" id="city" name="city" class="form-control">
                                                    <label for="city" class="">Город</label>
                                                </div>
                                            </div>
                                            <div class="col">
                                                <div class="md-form">
                                                    <input type="text" id="street" name="street" class="form-control">
                                                    <label for="street" class="">Улица</label>
                                                </div>
                                            </div>
                                            <div class="col">
                                                <div class="md-form">
                                                    <input type="email" id="house" name="house" class="form-control">
                                                    <label for="house" class="">Дом</label>
                                                </div>
                                            </div>
                                            <div class="col">
                                                <div class="md-form">
                                                    <input type="email" id="apartment" name="apartment" class="form-control">
                                                    <label for="apartment" class="">Квартира</label>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                            <#if order?has_content>
                                <div class="text-center text-md-left">
                                    <button id="confirm-order" value="${order.orderID}" class="btn btn-primary btn-lg btn-block mt-2" style="background-color: #e52d00 !important; width: 60rem; margin-bottom: 5rem">Оформить заказ!</button>
                                </div>
                            </#if>
                        </div>
                    </div>
                </section>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col">
            <div id="orderSuccess"></div>
            <div></div>
        </div>
    </div>
    <div class="row">
        <div class="col">
            <a href="/" id="new-order-button" type="button" class="btn btn-deep-purple btn-lg btn-block" style="margin-bottom: 5rem; background-color: #e42c00 !important; width: 30rem; display: none;">Новый заказ</a>
        </div>
        <#if isUser>
            <div class="col">
                <a href="/user/cabinet" id="my-order-button" type="button" class="btn btn-elegant btn-lg btn-block" style="margin-bottom: 5rem; width: 30rem; display: none;">Мои заказы</a>
            </div>
        </#if>
    </div>
    <script src="/../lib/mobilemask.js"></script>
    </@t.template>


    <#--
    <#if orderedProducts?has_content>
        <div id="order-layout">
            <button type="button" class="btn btn-light" name="cards-layout-inp">Cards</button>
            <button type="button" class="btn btn-light" name="rows-layout-inp">Rows</button>
        </div>
    </#if>

    <ul class="list-group" id="bucket-products-rows" name="rows-layout" style="display: none">
        <#if orderedProducts??>
            <#list orderedProducts as product>
                <li class="list-group-item d-flex justify-content-between align-items-center">
                    <img class="ordered-product-img-line" src="${product.pic}" height="50" width="80"  alt="Card image cap">
                    <strong>${product.type}</strong> ${product.brand} ${product.model}
                    <button type="button" class="btn btn-outline-danger waves-effect" onclick="changeAmount(this)" id="${product.orderedID?c}" name="product-less" value="${product.productID}">-</button>
                    <div id="amount${product.orderedID?c}" name="${product.orderedID}"><span class="badge badge-primary badge-pill">${product.amount}</span></div>
                    <button type="button" class="btn btn-outline-success waves-effect" onclick="changeAmount(this)" id="${product.orderedID?c}" name="product-more" value="${product.productID}">+</button>
                    <strong><i id="total-price${product.orderedID}">${product.totalPrice} ₽</i></strong>
                    <button type="submit" onclick="removeFromOrder(this)" class="btn btn-danger btn-md" name="remove-product" id="remove-product" value="${product.orderedID?c}">Удалить</button>
                </li>
            </#list>
        <#else>
            <h3 style="margin-top: 4vh">Пока ничего нет...</h3>
            <a type="button" href="/" class="btn blue-gradient btn-lg btn-block">Вернуться за покупками</a>
        </#if>
    </ul>
    -->
