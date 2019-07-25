<#import "../parts/template.ftl" as t>
<@t.template>
    <h2>
        <div class="container" style="margin-top: 5rem">
            <div class="row justify-content-center">
                <div class="col-6">
                    <#if wrongNameOrPass??>
                        <div class="row">
                            <div class="col">
                                <div class="alert alert-danger" role="alert">
                                    Неверное имя или пароль!
                                </div>
                            </div>
                        </div>
                    </#if>
                    <form action="/user/login" method="post" class="text-center mt-5">
                        <div class="card">
                            <div class="card-body">
                                <p class="mb-4">Вход в личный кабинет</p>
                                <div class="modal-body mx-3">
                                    <div class="md-form">
                                        <div class="md-form mb-5">
                                            <i class="fas fa-phone prefix grey-text" style="margin-left: -14.5rem;"></i>
                                            <input type="text" id="username" name="username" class="form-control">
                                            <label data-error="wrong" data-success="right" for="username">Номер телефона</label>
                                        </div>
                                        <div class="md-form">
                                            <i class="fas fa-lock prefix grey-text" style="margin-left: -14.5rem;"></i>
                                            <input type="password" id="password" name="password" class="form-control">
                                            <label data-error="wrong" data-success="right" for="password">Пароль</label>
                                        </div>
                                    </div>
                                </div>
                                <button class="btn btn-primary btn-block my-4" type="submit" style="background-color: #e52d00 !important;">Войти</button>
                                <p style="font-size: 1.5rem !important;">
                                    Зашли впервые?
                                    <p style="font-size: 1.5rem !important;"><a href="/user/registration">
                                        Зарегистрируйтесь!
                                    </a>
                                </p>
                            </div>
                        </div>
                        <#--<div style="margin-left: 45rem;">
                            <h3>Через социальные сети</h3>
                            <button type="button" class="btn btn-gplus"><i class="fab pr-1"></i> Google </button>
                            <button type="button" class="btn btn-so"><i class="fab pr-1"></i>Yandex</button>
                            <button type="button" class="btn btn-li"><i class="fab pr-1"></i>Mail.ru</button>
                        </div>-->
                    </form>
                </div>
            </div>
        </div>
    </h2>
    <script src="/../lib/mobilemask.js"></script>
</@t.template>