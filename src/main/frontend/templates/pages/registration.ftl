<#import "../parts/template.ftl" as t>
<@t.template>

    <div class="container">
        <div class="row">
            <div class="col">
                <div id="registration-error">
                </div>
                <form action="/user/registration" method="post" name="registration" id="registrationForm" class="text-center<#-- border border-light--> p-5">
                    <p class="h4 mb-4">Регистрация</p>
                    <#if userExists??>
                    <div class="row">
                        <div class="col">
                            <div class="alert alert-danger" role="alert">
                                Пользователь уже существует!
                            </div>
                        </div>
                    </div>
                    </#if>
                    <div class="form-row mb-4">
                        <div class="col">
                            <input type="text" id="firstName" name="firstName" placeholder="Имя" required
                                   class="form-control ${(firstNameError??)?string('is-invalid','')}" value="<#if user??>${user.firstName}</#if>">
                            <#if firstNameError??>
                                <div class="invalid-feedback">
                                    ${firstNameError}
                                </div>
                            </#if>
                        </div>
                        <div class="col">
                            <input type="text" id="lastName" name="lastName" required placeholder="Фамилия"
                                   class="form-control ${(lastNameError??)?string('is-invalid','')}" value="<#if user??>${user.lastName}</#if>">
                            <#if lastNameError??>
                                <div class="invalid-feedback">
                                    ${lastNameError}
                                </div>
                            </#if>
                        </div>
                    </div>
                    <div class="form-row mb-4">
                        <div class="col">
                            <input type="password" id="password" name="password" required placeholder="Ваш пароль" aria-describedby="registerPassword"
                                   class="form-control ${(passwordError??)?string('is-invalid','')}" value="<#if user??>${user.password}</#if>">
                            <#if passwordError??>
                                <div class="invalid-feedback">
                                    ${passwordError}
                                </div>
                            </#if>
                        </div>
                        <div class="col">
                            <input type="password" id="registerPasswordConfirm" required class="form-control" placeholder="Повторите пароль" aria-describedby="registerPasswordConfirm">
                        </div>
                    </div>

                    <div class="form-row mb-4">
                        <div class="col">
                            <input type="email" id="username" name="username" required placeholder="E-mail"
                                   class="form-control ${(usernameError??)?string('is-invalid','')}" value="<#if user??>${user.username}</#if>">
                            <span class="form__error">Это поле должно содержать E-Mail в формате example@site.com</span>
                            <#if usernameError??>
                                <div class="invalid-feedback">
                                    ${usernameError}
                                </div>
                            </#if>
                        </div>
                    </div>

                    <div class="form-row mb-4">
                        <div class="col">
                            <input type="text" id="mobile" name="mobile" required placeholder="Ваш мобильный телефон" aria-describedby="registerMobile"
                                   class="form-control ${(mobileError??)?string('is-invalid','')}" value="<#if user??>${user.mobile}</#if>">
                            <span class="form__error">Это поле должно содержать телефон в формате +7 (123) 456-78-90</span>
                            <#if mobileError??>
                                <div class="invalid-feedback">
                                    ${mobileError}
                                </div>
                            </#if>
                        </div>
                    </div>
                    <button id="submitRegistration" class="btn btn-info my-4 btn-block" type="submit">Зарегестрироваться!</button>
                    <div>
                        <p>Регистрация через социальные сети</p>
                        <button type="button" class="btn btn-gplus"><i class="fab pr-1"></i> Google </button>
                        <button type="button" class="btn btn-so"><i class="fab pr-1"></i>Yandex</button>
                        <button type="button" class="btn btn-li"><i class="fab pr-1"></i>Mail.ru</button>
                    </div>
                </form>

            </div>
        </div>
    </div>

</@t.template>
<style>
    .form__error {
        color: red;
        text-align: left;
        font-size: 12px;
        display: block;
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