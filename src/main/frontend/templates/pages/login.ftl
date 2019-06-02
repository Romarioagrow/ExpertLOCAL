<#import "../parts/template.ftl" as t>
<@t.template>
    <h2>
        <div class="container">
            <div class="row">
                <div class="col">
                    <form action="/user/login" method="post" class="text-center border border-light p-5">
                        <p class="h4 mb-4">Вход в личный кабинет</p>
                        <input type="email" id="username" name="username" class="form-control mb-4" placeholder="E-mail">
                        <input type="password" id="password" name="password" class="form-control mb-4" placeholder="Password">

                        <button class="btn btn-elegant btn-block my-4" type="submit">Войти</button>
                        <p>Зашли впервые?
                            <a href="/user/registration">Зарегистрируйтесь</a>
                        </p>
                    </form>
                    <div>
                        <p>Через социальные сети</p>
                        <button type="button" class="btn btn-gplus"><i class="fab pr-1"></i> Google </button>
                        <button type="button" class="btn btn-so"><i class="fab pr-1"></i>Yandex</button>
                        <button type="button" class="btn btn-li"><i class="fab pr-1"></i>Mail.ru</button>
                    </div>
                </div>
            </div>
        </div>
    </h2>
</@t.template>