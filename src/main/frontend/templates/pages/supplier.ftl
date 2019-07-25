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
                                        <th>Цена ориг.</th>
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
                                                <td><strong>${product.originalName!'-'}</strong></td>
                                                <td>${product.productCategory!'-'}</td>
                                                <td>${product.productGroup!'Нет'}</td>
                                                <td>${product.originalBrand!'-'}</td>
                                                <td>${product.modelName!'-'}</td>
                                                <td style="width: 6rem;">${product.originalPrice!'-'}</td>
                                                <td><strong><input type="text" value="${product.finalPrice!'-'}" style="width: 5rem;"></strong></td>
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
                            </div>
                        </div>
                        <#--<tr>
                            <th scope="row">1</th>
                            <td>Product 1</td>
                            <td>100$</td>
                            <td><a><i class="fas fa-times"></i></a></td>
                        </tr>
                        <tr>
                            <th scope="row">2</th>
                            <td>Product 2</td>
                            <td>100$</td>
                            <td><a><i class="fas fa-times"></i></a></td>
                        </tr>
                        <tr>
                            <th scope="row">3</th>
                            <td>Product 3</td>
                            <td>100$</td>
                            <td><a><i class="fas fa-times"></i></a></td>
                        </tr>
                        <tr>
                            <th scope="row">4</th>
                            <td>Product 4</td>
                            <td>100$</td>
                            <td><a><i class="fas fa-times"></i></a></td>
                        </tr>
                        <tr class="total">
                            <th scope="row">5</th>
                            <td>Total</td>
                            <td>400$</td>
                            <td></td>
                        </tr>-->


                        <#--<#list products as product>

                        </#list>-->
                    </div>
                </div>
            </div>
            <div class="tab-pane fade <#if url?contains("orders")>in show active</#if>" id="cat3" role="tabpanel">
                <div class="card">
                    <div class="card-body">
                    </div>
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
