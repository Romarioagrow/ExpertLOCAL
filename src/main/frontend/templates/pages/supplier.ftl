<#import "../parts/template.ftl" as t>
<#include "../parts/security.ftl">
<@t.template>
    <div class="container-fluid" style="margin-top: 6rem; /*width: 50rem*/">
        <div class="row">
            <div class="col">
                <ul class="nav md-pills pills-secondary">
                    <li class="nav-item">
                        <a class="nav-link <#if url?contains("db")>active</#if>" data-toggle="tab" href="#cat1" role="tab">Обновление БД</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link <#if url?contains("products")>active</#if>" data-toggle="tab" href="#cat2" role="tab">Редактирование товаров</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link <#if url?contains("orders")>active</#if>" data-toggle="tab" href="#cat3" role="tab">Заказы</a>
                    </li>
                </ul>
                <div class="tab-content pt-0" style="margin-top: 2rem">
                    <div class="tab-pane fade <#if url?contains("db")>in show active</#if>" id="cat1" role="tabpanel">
                        <div class="card">
                            <div class="card-body">
                                <h3>База товаров</h3>
                                <form method="post" action="/supplier/updateDB" enctype="multipart/form-data">
                                    <div class="form-group">
                                        <div class="custom-file">
                                            <input type="file" name="file" id="customFile">
                                            <label class="custom-file-label" for="customFile">CSV Файл!</label>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <button type="submit" class="btn btn-success btn-lg">Обновить базу!</button>
                                    </div>
                                </form>
                                <form method="post" action="/supplier/match-products">
                                    <div class="form-group">
                                        <button type="submit" class="btn btn-secondary btn-sm secc">Обработать товары!</button>
                                    </div>
                                </form>
                                <form method="post" action="/supplier/xxx">
                                    <div class="form-group">
                                        <button type="submit" class="btn btn-danger btn-sm secc" style="background-color: #c40030 !important;">xxx</button>
                                    </div>
                                </form>
                                <h3>Парсер картинок RUS</h3>
                                <form method="POST" action="/supplier/pics">
                                    <div class="form-group">
                                        <button type="submit" class="btn btn-primary">Спарсить картинки!</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="tab-pane fade <#if url?contains("products")>in show active</#if>" id="cat2" role="tabpanel">
                        <div class="card">
                            <div class="card-body">
                                <form method="get" action="/supplier/products">
                                    <div class="row">
                                        <div class="col-10">
                                            <input class="form-control" type="text" placeholder="Группа, категория, наименование, поставщик" id="request" name="request" aria-label="Search" value="${request!''}">
                                        </div>
                                        <div class="col-2">
                                            <button href="#!" class="btn btn-primary btn-md" type="submit" style="margin-top: -0.2rem">Найти</button>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col" style="margin-top: 0.5rem; margin-left: 0.5rem">
                                            <div class="form-check form-check-inline">
                                                <input type="checkbox" class="form-check-input" id="mapped" name="mapped" checked>
                                                <label class="form-check-label" for="mapped">На сайте</label>
                                            </div>
                                            <div class="form-check form-check-inline">
                                                <input type="checkbox" class="form-check-input" id="withpic" name="withpic">
                                                <label class="form-check-label" for="withpic">С картинкой</label>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <div class="card" style="margin-top: 2rem">
                            <div class="card-body">
                                <#if products??>
                                    <div class="row">
                                        <div class="col">
                                            <button onclick="saveProduct()" type="submit" class="btn btn-success btn-block btn-md">Сохранить</button>
                                        </div>
                                    </div>
                                </#if>
                                <form method="post" action="/supplier/products/save">
                                    <table class="table table-hover">
                                        <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Поставщик</th>
                                            <th>Наименование</th>
                                            <th>Категория</th>
                                            <th>Группа</th>
                                            <th>Бренд</th>
                                            <th>Модель</th>
                                            <th>Цена</th>
                                            <th>Цена итог.</th>
                                            <th>Кофф.</th>
                                            <th>Бонус</th>
                                            <th>Обновление</th>
                                            <th>Изобр.</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <#if products??>
                                            <#list products as product>
                                                <tr>
                                                    <th scope="row">${product.productID}</th>
                                                    <td>${product.supplier!'-'}</td>
                                                    <td><a href="/products/info/${product.productID}"><strong>${product.originalName!'-'}</strong></a></td>
                                                    <td>${product.productCategory!'-'}</td>
                                                    <td>${product.productGroup!'Нет'}</td>
                                                    <td>${product.originalBrand!'-'}</td>
                                                    <td>${product.modelName!'-'}</td>
                                                    <td style="width: 6rem;">${product.originalPrice!'-'}</td>
                                                    <td ondblclick="editProduct(this)" id="finalPrice${product.productID?replace(".","")}" name="${product.productID};${product.finalPrice!'-'}"><strong>${product.finalPrice!'-'}</strong></td>
                                                    <td>${product.coefficient!'-'}</td>
                                                    <td>${product.bonus!'-'}</td>
                                                    <td>${product.update!'-'}</td>
                                                    <#if product.originalPic??>
                                                        <td>Есть</td>
                                                    <#else>
                                                        <td>Нет</td>
                                                    </#if>
                                                </tr>
                                            </#list>
                                        </#if>
                                        </tbody>
                                    </table>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="tab-pane fade <#if url?contains("orders")>in show active</#if>" id="cat3" role="tabpanel">
                        <div class="card">
                            <div class="card-body">
                                <h3>Принятые заказы</h3>
                                <form method="get" action="/supplier/orders">
                                    <button type="submit" class="btn btn-success">Показать заказы</button>
                                </form>
                            </div>
                        </div>
                        <div class="card-desk" style="margin-top: 2rem">
                            <#if orders?has_content>
                                <#list orders as order>
                                    <div class="card" style="margin-bottom: 1rem">
                                        <div class="card-body">
                                            <div class="row">
                                                <div class="col">
                                                    <h4 class="card-title font-weight-bold">
                                                        <#assign
                                                        date = order.orderOpenDate.toString()
                                                        time = date?substring(11, 16)
                                                        date = date?substring(0, 10)
                                                        date = date?replace("-", ".")
                                                        >
                                                        Заказ №${order.orderID?c} от ${date} ${time}
                                                    </h4>
                                                    <p>Заказчик: <strong>${order.name} ${order.surname}</strong>, Тел:  <strong>${order.email}</strong></p>
                                                </div>
                                            </div>
                                            <p class="mb-2">
                                                На сумму
                                                <strong>${order.totalPrice} ₽</strong>
                                                <#if order.discountApplied>
                                                    <strong>/ ${order.discountPrice}</strong> ₽ с учетом скидки
                                                </#if>
                                            </p>
                                            <div class="card-desk">
                                                <table class="table table-hover">
                                                    <thead>
                                                    <tr>
                                                        <th>Артикул</th>
                                                        <th>Поставщик</th>
                                                        <th>Тип</th>
                                                        <th>Наименование</th>
                                                        <th>Цена за единицу</th>
                                                        <th>Цена всего</th>
                                                        <th>Кол-во</th>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                    <#if order.orderedProducts?has_content>
                                                        <#list order.orderedProducts as product>
                                                            <tr>
                                                                <th scope="row">${product.productID}</th>
                                                                <td>${product.supplier}</td>
                                                                <td>${product.productType}</td>
                                                                <td><a href="/products/info/${product.productID}"><strong>${product.productName}</strong></a></td>
                                                                <td>${product.productPrice}</td>
                                                                <td>${product.totalPrice}</td>
                                                                <td>${product.orderedAmount}</td>
                                                            </tr>
                                                        </#list>
                                                    </#if>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </#list>
                            </#if>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>

</@t.template>
<style>
    .secc {
        width: 15rem !important;
    }
</style>
