<#import "../parts/template.ftl" as t>
<@t.template>
    <div class="container" style="margin-top: 5rem">
        <div class="row">
            <div class="col">
                <div id="registration-error"></div>
                <div class="card" style="width: 30rem; margin-left: 20rem; margin-top: 5rem;">
                    <div class="card-body">
                        <form action="/user/registration" method="post" name="registration" id="registrationForm" class="text-center">
                            <input type="hidden" name="sessionID" id="sessionID" value="${sessionID}">
                            <h1>Регистрация</h1>
                            <#if message??>
                                <div class="row">
                                    <div class="col">
                                        <div class="alert alert-danger" role="alert">
                                            ${message}
                                        </div>
                                    </div>
                                </div>
                            </#if>
                            <div class="form-row mb-4">
                                <div class="col">
                                    <input type="tel" value="" id="username" name="username" required
                                           placeholder="Ваш мобильный телефон"
                                           aria-describedby="registerMobile"
                                           class="form-control ${(usernameError??)?string('is-invalid','')}">
                                    <#if usernameError??>
                                        <div class="invalid-feedback">
                                            <span>Номер телефона в формате +7-9xx-xxx-xxxx</span>
                                        </div>
                                    </#if>
                                </div>
                            </div>
                            <div class="form-row mb-4">
                                <div class="col">
                                    <input type="password" id="password" name="password" required placeholder="Ваш пароль" aria-describedby="registerPassword"
                                           class="form-control ${(passwordError??)?string('is-invalid','')}">
                                    <#if passwordError??>
                                        <div class="invalid-feedback">
                                            ${passwordError}
                                        </div>
                                    </#if>
                                    <#if passwordsMatchError??>
                                        <div class="invalid-feedback">
                                            Пароли не совпадают!
                                        </div>
                                    </#if>
                                </div>
                                <div class="col">
                                    <input type="password" id="passwordConfirm" name="passwordConfirm" required class="form-control" placeholder="Повторите пароль" aria-describedby="registerPasswordConfirm">
                                </div>
                            </div>
                            <div class="form-row mb-4">
                                <div class="col">
                                    <input type="text" id="lastName" name="lastName" required placeholder="Фамилия"
                                           class="form-control ${(lastNameError??)?string('is-invalid','')}">
                                    <#if lastNameError??>
                                        <div class="invalid-feedback">
                                            ${lastNameError}
                                        </div>
                                    </#if>
                                </div>
                                <div class="col">
                                    <input type="text" id="firstName" name="firstName" placeholder="Имя" required
                                           class="form-control ${(firstNameError??)?string('is-invalid','')}">
                                    <#if firstNameError??>
                                        <div class="invalid-feedback">
                                            ${firstNameError}
                                        </div>
                                    </#if>
                                </div>
                                <div class="col">
                                    <input type="text" id="otchestvo" name="otchestvo" required placeholder="Отчество" class="form-control">
                                </div>
                            </div>
                            <div class="form-row mb-4">
                                <div class="col">
                                    <input type="email" id="email" name="email" placeholder="E-mail" class="form-control">
                                </div>
                            </div>
                            <button id="submitRegistration" class="btn btn-info my-4 btn-block" type="submit">Зарегестрироваться!</button>
                            <#--<div>
                                <p>Регистрация через социальные сети</p>
                                <button type="button" class="btn btn-gplus"><i class="fab pr-1"></i> Google </button>
                                <button type="button" class="btn btn-so"><i class="fab pr-1"></i>Yandex</button>
                                <button type="button" class="btn btn-li"><i class="fab pr-1"></i>Mail.ru</button>
                            </div>-->
                        </form>
                        <p>
                            Есть аккаунт?
                            <a href="/user/login">Войти!</a>
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="/../lib/mobilemask.js"></script>
</@t.template>
<style>
    .form__error {
        color: red;
        text-align: left;
        font-size: 12px;
        /*display: block;*/
        margin-top: 6px;
        display: none;
    }

    input:valid:not(:placeholder-shown) {
        border-color: green;
    }
    input:invalid:not(:placeholder-shown) {
        border-color: red;
    }
    input:invalid:not(:placeholder-shown) + .form__error {
        display: block;
    }
</style>