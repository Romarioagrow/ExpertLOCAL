<#import "../parts/template.ftl" as t>
<@t.template>
    <h2>
        <div class="container">
            <div class="row justify-content-center">
                <#--<div class="col-1"></div>-->
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
                    <form action="/user/login" method="post" class="text-center p-5 " <#--style="width: 50rem !important;"-->>
                        <p class="h4 mb-4">Вход в личный кабинет</p>
                        <input type="email" id="username" name="username" class="form-control mb-4" placeholder="E-mail">
                        <input type="password" id="password" name="password" class="form-control mb-4" placeholder="Password">

                        <button class="btn btn-elegant btn-block my-4" type="submit">Войти</button>
                        <p>Зашли впервые?
                            <a href="/user/registration">Зарегистрируйтесь</a>
                        </p>
                        <div>
                            <p>Через социальные сети</p>
                            <button type="button" class="btn btn-gplus"><i class="fab pr-1"></i> Google </button>
                            <button type="button" class="btn btn-so"><i class="fab pr-1"></i>Yandex</button>
                            <button type="button" class="btn btn-li"><i class="fab pr-1"></i>Mail.ru</button>
                        </div>
                    </form>
                    <#--<div>
                        <p>Через социальные сети</p>
                        <button type="button" class="btn btn-gplus"><i class="fab pr-1"></i> Google </button>
                        <button type="button" class="btn btn-so"><i class="fab pr-1"></i>Yandex</button>
                        <button type="button" class="btn btn-li"><i class="fab pr-1"></i>Mail.ru</button>
                    </div>-->
                </div>
            </div>
        </div>
    </h2>
</@t.template>