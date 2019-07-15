<#import "../parts/template.ftl" as t>
<@t.template>
    <h2>
        <div class="container">
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
                        <h2 class="mb-4">Вход в личный кабинет</h2>
                        <input type="text" id="username" name="username" class="form-control mb-4" placeholder="Номер телефона">
                        <input type="password" id="password" name="password" class="form-control mb-4" placeholder="Пароль">

                        <button class="btn btn-elegant btn-block my-4" type="submit">Войти</button>
                        <h2>Зашли впервые?
                            <h3><a href="/user/registration">Зарегистрируйтесь!</a></h3>
                        </h2>
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