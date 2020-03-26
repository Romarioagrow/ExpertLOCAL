<#import "../parts/template.ftl" as t>
<#include "../parts/security.ftl">

<@t.template>
    <div class="container" style="margin-top: 6rem">
        <div class="row mt-3">
            <div class="col-10 ">
                <h1>${firstName}, добро пожаловать в личный кабинет!</h1>
            </div>
            <div class="col">
                <a type="button" class="btn btn-elegant btn-sm" href="/user/logout">Выйти</a>
            </div>
        </div>
        <div class="row">
            <div class="col">
                <h3 style="margin-top: 1rem">
                    <span>Доступно бонусов: <strong>${bonus}</strong></span>
                </h3>
            </div>
        </div>
        <div class="row mt-3">
            <div class="col">
                <ul class="nav md-pills pills-secondary">
                    <li class="nav-item">
                        <a class="nav-link active" data-toggle="tab" href="#panel1" role="tab"><#--Текущие заказы-->Мои заказы</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" data-toggle="tab" href="#panel2" role="tab">Завершенные заказы</a>
                    </li>
                </ul>
                <div class="tab-content pt-0">
                    <div class="tab-pane fade in show active" id="panel1" role="tabpanel">
                        <div style="width: 60rem;">
                            <div class="row">
                                <div class="col-6">
                                    <h3 style="margin-top: 2rem">Статус текущих заказов</h3>
                                </div>
                            </div>
                            <hr>
                            <div class="card-desk">
                                <#if orders?has_content>
                                    <#list orders as order>
                                        <div class="card" style="margin-bottom: 1rem">
                                            <div class="card-body">
                                                <div class="row">
                                                    <div class="col-8">
                                                        <h4 class="card-title font-weight-bold">
                                                            <#assign
                                                            date = order.orderOpenDate.toString()
                                                            time = date?substring(11, 16)
                                                            date = date?substring(0, 10)
                                                            date = date?replace("-", ".")
                                                            >
                                                            Заказ №${order.orderID?c} от ${date} ${time}
                                                        </h4>
                                                    </div>
                                                    <div class="col-4">
                                                        <#if order.accepted>
                                                            <span>Статус заказа: <strong style="color: #3f51b5">Принят</strong></span>
                                                        </#if>
                                                    </div>
                                                </div>
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
                                                                    <div class="row">
                                                                        <div class="col-6">
                                                                            <p class="card-text">
                                                                                <#if product.productType??>
                                                                                    <span style="color: #e42c00">${product.productType}</span> <strong style="color: #0d0d0d">${product.productName}</strong>
                                                                                <#else>
                                                                                    <strong style="color: #0d0d0d">${product.productName}</strong>
                                                                                </#if>
                                                                            </p>
                                                                        </div>
                                                                        <div class="col-6">
                                                                            <p class="card-text">
                                                                                <span>Количество:  <strong>${product.orderedAmount}</strong>, Цена: <strong>${product.totalPrice}</strong> ₽</span>
                                                                            </p>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </#list>
                                                    </#if>
                                                </div>
                                            </div>
                                        </div>
                                    </#list>
                                <#else>
                                    Нет активных заказов
                                </#if>
                            </div>
                        </div>
                    </div>
                    <div class="tab-pane fade" id="panel2" role="tabpanel">
                        <div class="card-desk">
                            <#if completedOrders?has_content>
                                <#list completedOrders as order>
                                    <div class="card" style="margin-bottom: 1rem">
                                        <div class="card-body">
                                            <div class="row">
                                                <div class="col-8">
                                                    <h4 class="card-title font-weight-bold">
                                                        <#assign
                                                        date = order.orderOpenDate.toString()
                                                        time = date?substring(11, 16)
                                                        date = date?substring(0, 10)
                                                        date = date?replace("-", ".")
                                                        >
                                                        Заказ №${order.orderID?c} от ${date} ${time}
                                                    </h4>
                                                </div>
                                                <div class="col-4">
                                                    <#if order.completed>
                                                        <br><span>Статус заказа: <strong style="color: #007e33">Выполнен</strong></span>
                                                    </#if>
                                                </div>
                                            </div>
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
                                                                <div class="row">
                                                                    <div class="col-6">
                                                                        <#if product.productType??>
                                                                            <span style="color: #e42c00">${product.productType}</span> <strong style="color: #0d0d0d">${product.productName}</strong>
                                                                        <#else>
                                                                            <strong style="color: #0d0d0d">${product.productName}</strong>
                                                                        </#if>
                                                                    </div>
                                                                    <div class="col-6">
                                                                        <p class="card-text">
                                                                            <span>Количество:  <strong>${product.orderedAmount}</strong>, Цена: <strong>${product.totalPrice}</strong> ₽</span>
                                                                        </p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </#list>
                                                </#if>
                                            </div>
                                        </div>
                                    </div>
                                </#list>
                            <#else>
                                Нет завершенных заказов
                            </#if>
                        </div>
                    </div>
                    <div class="tab-pane fade" id="panel3" role="tabpanel">
                        <div class="card" style="width: 60rem; height: 40rem">
                            <div class="card-body pb-3">
                                <p>
                                    Контактные данные
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col">
                <a type="button" href="/" class="btn btn-md btn-block" style="margin-bottom: 3rem; margin-left: 10rem;  background-color: #e42c00 !important; color: #e0e0e0; width: 40rem">Новый заказ</a>
            </div>
        </div>
    </div>
</@t.template>
