<#import "../parts/template.ftl" as t>
<#include "../parts/security.ftl">
<@t.template>
    <div class="container" style="margin-top: 6rem; width: 50rem">
        <div class="row">
            <div class="col">
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
                                <button type="submit" class="btn btn-success btn-lg btn-block">Обновить базу!</button>
                            </div>
                        </form>
                        <form method="post" action="/supplier/match-products">
                            <div class="form-group">
                                <button type="submit" class="btn btn-secondary btn-sm secc">Обработать товары!</button>
                            </div>
                        </form>
                        <#--<form method="post" action="/supplier/match-models">
                            <div class="form-group">
                                <button type="submit" class="btn btn-secondary btn-sm secc">Match models!</button>
                            </div>
                        </form>
                        <form method="post" action="/supplier/match-duplicates">
                            <div class="form-group">
                                <button type="submit" class="btn btn-secondary btn-sm secc">Match duplicates!</button>
                            </div>
                        </form>-->

                    </div>
                </div>
            </div>
        </div>
        <div class="row" style="margin-top: 2rem">
            <div class="col">
                <div class="card" >
                    <div class="card-body">
                        <h3>Парсер картинок RUS</h3>
                        <form method="POST" action="/supplier/pics">
                            <div class="form-group">
                                <button type="submit" class="btn btn-primary">Спарсить картинки!</button>
                            </div>
                        </form>
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
