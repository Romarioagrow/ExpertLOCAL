<#import "../parts/template.ftl" as t>
<@t.template>
    <h2>
        <div class="container">
            <div class="row">
                <div class="col">
                    <form class="text-center border border-light p-5">
                        <p class="h4 mb-4">Вход в личный кабинет</p>
                        <input type="email" id="defaultLoginFormEmail" class="form-control mb-4" placeholder="E-mail">
                        <input type="password" id="defaultLoginFormPassword" class="form-control mb-4" placeholder="Password">
                        <div class="d-flex justify-content-around">
                            <div>
                                <a href="">Забыли пароль?</a>
                            </div>
                        </div>
                        <button class="btn btn-info btn-block my-4" type="submit">Войти</button>
                        <p>Зашли впервые?
                            <a href="/user/registration">Зарегистрируйтесь</a>
                        </p>
                        <p>Через социальные сети</p>
                        <a type="button" class="light-blue-text mx-2">
                            <i class="fab fa-facebook-f"></i>
                        </a>
                        <a type="button" class="light-blue-text mx-2">
                            <i class="fab fa-twitter"></i>
                        </a>
                        <a type="button" class="light-blue-text mx-2">
                            <i class="fab fa-linkedin-in"></i>
                        </a>
                        <a type="button" class="light-blue-text mx-2">
                            <i class="fab fa-github"></i>
                        </a>
                    </form>
                </div>
            </div>
        </div>
    </h2>
</@t.template>