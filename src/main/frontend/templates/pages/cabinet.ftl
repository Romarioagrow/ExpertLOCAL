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
                <ul class="nav nav-tabs nav-justified md-tabs indigo" style="background-color: #e52d00 !important;" id="myTabJust" role="tablist">
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
                            <a type="button" href="/" class="btn blue-gradient btn-lg btn-block">Сделать заказ</a>
                        </#if>
                    </div>
                    <div class="tab-pane fade" id="profile-just" role="tabpanel" aria-labelledby="profile-tab-just">
                        Настройки профиля
                    </div>
                    <div class="tab-pane fade" id="contact-just" role="tabpanel" aria-labelledby="contact-tab-just">
                        Мои акции и бонусы
                    </div>
                </div>
            </div>
        </div>
    </div>
</@t.template>
