<#import "../parts/template.ftl" as t>
<#include "../parts/security.ftl">
<@t.template>
    <div class="container" style="margin-top: 6rem; width: 50rem">
        <div class="row">
            <div class="col">
                <div class="card">
                    <div class="card-body">
                        <h3>База товаров</h3>
                        <form method="post" action="/supplier/parse-catalog" enctype="multipart/form-data">
                            <div class="form-group">
                                <div class="custom-file">
                                    <input type="file" name="file" id="customFile">
                                    <label class="custom-file-label" for="customFile">CSV Файл!</label>
                                </div>
                            </div>
                            <div class="form-group">
                                <button type="submit" class="btn btn-danger btn-lg">Загрузить товары!</button>
                            </div>
                        </form>
                        <form method="post" action="/supplier/match-products">
                            <div class="form-group">
                                <button type="submit" class="btn btn-success btn-lg">Обработать товары!</button>
                            </div>
                        </form>

                    </div>
                </div>
            </div>
        </div>
        <div class="row" style="margin-top: 2rem">
            <div class="col">
                <div class="card" >
                    <div class="card-body">
                        <h3>Парсер картинок RUS</h3>
                        <form method="get" action="/supplier/pics">
                            <div class="form-group">
                                <button type="submit" class="btn btn-primary">Загрузить картинки!</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

    </div>
</@t.template>
