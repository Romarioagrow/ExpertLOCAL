<#import "../parts/template.ftl" as t>
<@t.template>

        <div class="container">
            <div class="row">
                <div class="col">
                    <div id="registration-error">
                    </div>
                    <form action="/user/registration" method="post" name="registration" id="registrationForm" class="text-center border border-light p-5">
                        <p class="h4 mb-4">Регистрация</p>
                        <div class="form-row mb-4">
                            <div class="col">
                                <input type="text" id="firstName" name="firstName" placeholder="Имя" class="form-control ${(firstNameError??)?string('is-invalid','')}" value="<#if user??>${user.firstName}</#if>">
                                <#if firstNameError??>
                                    <div class="invalid-feedback">
                                        ${firstNameError}
                                    </div>
                                </#if>
                            </div>

                            <div class="col">
                                <input type="text" id="lastName" name="lastName" placeholder="Фамилия"
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
                                <input type="password" id="password" name="password" placeholder="Ваш пароль" aria-describedby="registerPassword"
                                       class="form-control ${(passwordError??)?string('is-invalid','')}" value="<#if user??>${user.password}</#if>">
                                <#if passwordError??>
                                    <div class="invalid-feedback">
                                        ${passwordError}
                                    </div>
                                </#if>
                            </div>
                            <div class="col">
                                <input type="password" id="registerPasswordConfirm" class="form-control" placeholder="Повторите пароль" aria-describedby="registerPasswordConfirm">
                            </div>
                        </div>

                        <div class="form-row mb-4">
                            <div class="col">
                                <input type="email" id="username" name="username" placeholder="E-mail"
                                       class="form-control mb-4 ${(usernameError??)?string('is-invalid','')}" value="<#if user??>${user.username}</#if>">
                                <#if usernameError??>
                                    <div class="invalid-feedback">
                                        ${usernameError}
                                    </div>
                                </#if>
                            </div>
                        </div>

                        <div class="form-row mb-4">
                            <div class="col">
                                <input type="text" id="mobile" name="mobile" placeholder="Ваш мобильный телефон" aria-describedby="registerMobile"
                                       class="form-control ${(mobileError??)?string('is-invalid','')}" value="<#if user??>${user.mobile}</#if>">
                                <#if mobileError??>
                                    <div class="invalid-feedback">
                                        ${mobileError}
                                    </div>
                                </#if>
                            </div>
                        </div>

                        <button id="submitRegistration" class="btn btn-info my-4 btn-block" type="submit">Зарегестрироваться!</button>
                    </form>
                    <p>Регистрация через социальные сети</p>
                    <button type="button" class="btn btn-gplus"><i class="fab pr-1"></i> Google </button>
                    <button type="button" class="btn btn-so"><i class="fab pr-1"></i>Yandex</button>
                    <button type="button" class="btn btn-li"><i class="fab pr-1"></i>Mail.ru</button>
                </div>
            </div>
        </div>

</@t.template>