<#import "../parts/template.ftl" as t>
<#include "../parts/security.ftl">

<@t.template>
    <div class="container">
        <div class="row mt-3">
            <div class="col-10 ">
                <h1>${firstName}, добро пожаловать в личный кабинет!</h1>
            </div>
            <div class="col">
                <a type="button" class="btn btn-elegant" href="/user/logout">Выйти</a>
            </div>
        </div>
        <div class="row mt-3">
            <div class="col">
                <ul class="nav md-pills pills-secondary">
                    <li class="nav-item">
                        <a class="nav-link active" data-toggle="tab" href="#panel1" role="tab">Бонусы и заказы</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" data-toggle="tab" href="#panel2" role="tab">Акции и скидки</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" data-toggle="tab" href="#panel3" role="tab">Личная информация</a>
                    </li>
                </ul>
                <div class="tab-content pt-0">
                    <div class="tab-pane fade in show active" id="panel1" role="tabpanel">
                        <div style="width: 60rem;">
                            <h3 style="margin-top: 1rem">
                                <span>Доступно бонусов: <strong>${user.bonus}</strong></span>
                            </h3>
                            <h3 style="margin-top: 2rem">Заказы</h3>
                            <div class="card-desk">
                                <#if orders?has_content>
                                    <#list orders as order>
                                        <div class="card" style="margin-bottom: 1rem">
                                            <div class="card-body">
                                                <h4 class="card-title font-weight-bold">
                                                    <#assign
                                                    date = order.orderOpenDate.toString()
                                                    time = date?substring(11, 16)
                                                    date = date?substring(0, 10)
                                                    date = date?replace("-", ".")
                                                    >
                                                    <a>Заказ №${order.orderID?c} от ${date} ${time}</a>
                                                </h4>
                                                <p class="mb-2">
                                                    На сумму
                                                    <#if order.discountApplied>
                                                        <strong>${order.discountPrice}</strong> ₽ с учетом скидки
                                                    <#else>
                                                        <strong>${order.totalPrice}</strong> ₽ без учета скидки
                                                    </#if>
                                                </p>
                                                <div class="card-desk">
                                                    <#if order.orderedProducts?has_content>
                                                        <#list order.orderedProducts as product>
                                                            <div class="card">
                                                                <div class="card-body">
                                                                    <p class="card-text">
                                                                        <span style="color: #e42c00">${product.productType}</span> <strong style="color: #0d0d0d">${product.productName}</strong>
                                                                    </p>
                                                                    <p class="card-text">
                                                                        Количество:  <strong style="color: #0d0d0d">${product.orderedAmount}</strong>, Цена: <strong style="color: #0d0d0d">${product.totalPrice}</strong> ₽
                                                                    </p>
                                                                </div>
                                                            </div>
                                                        </#list>
                                                    </#if>
                                                </div>
                                            </div>
                                        </div>
                                    </#list>
                                </#if>
                            </div>
                        </div>
                    </div>
                    <div class="tab-pane fade" id="panel2" role="tabpanel">
                        <div class="card" style="width: 60 rem; height: 40rem">
                            <div class="card-body pb-3">
                                <p>
                                    222 акции
                                </p>
                            </div>
                        </div>
                    </div>
                    <div class="tab-pane fade" id="panel3" role="tabpanel">
                        <div class="card" style="width: 60 rem; height: 40rem">
                            <div class="card-body pb-3">
                                <p>
                                    333 данные
                                </p>
                            </div>
                        </div>
                    </div>
                </div>



                <#--<ul class="nav nav-tabs nav-justified md-tabs indigo" style="background-color: #e52d00 !important;" id="myTabJust" role="tablist">
                    <li class="nav-item">
                        <a class="nav-link active" id="home-tab-just" data-toggle="tab" href="#home-just" role="tab" aria-controls="home-just"
                           aria-selected="true">Мои заказы</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" id="profile-tab-just" data-toggle="tab" href="#profile-just" role="tab" aria-controls="profile-just"
                           aria-selected="false">Мой профиль</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" id="contact-tab-just" data-toggle="tab" href="#contact-just" role="tab" aria-controls="contact-just"
                           aria-selected="false">Акции и бонусы</a>
                    </li>
                </ul>
                <div class="tab-content card pt-5" id="myTabContentJust">
                    <div class="tab-pane fade show active" id="home-just" role="tabpanel" aria-labelledby="home-tab-just">
                        <#if orders?has_content>
                            <#list orders as order>
                                <li class="list-group-item d-flex justify-content-between align-items-center">
                                    Номер заказа: ${order.orderID?c} | Итого ₽: ${order.totalPrice} | Всего наименований:${order.productsAmount} | Всего едениц: ${order.totalAmount}
                                </li>
                            </#list>
                        <#else>
                            <h3>Заказов пока нет</h3>
                        </#if>
                    </div>
                    <div class="tab-pane fade" id="profile-just" role="tabpanel" aria-labelledby="profile-tab-just">
                        Настройки профиля
                    </div>
                    <div class="tab-pane fade" id="contact-just" role="tabpanel" aria-labelledby="contact-tab-just">
                        Мои акции и бонусы
                    </div>
                </div>-->
            </div>
        </div>
        <div class="row">
            <div class="col">
                <a type="button" href="/" class="btn btn-lg btn-block" style="margin-bottom: 3rem; background-color: #e42c00 !important; color: #e0e0e0">Новый заказ</a>
            </div>
        </div>
    </div>
</@t.template>
