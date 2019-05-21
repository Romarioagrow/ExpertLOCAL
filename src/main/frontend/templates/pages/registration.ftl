<#import "../parts/template.ftl" as t>
<@t.template>
    <h2>
        <div class="container">
            <div class="row">
                <div class="col">
                    <div id="registration-error">
                    </div>

                    <form name="registration" id="registrationForm" class="text-center border border-light p-5">
                        <p class="h4 mb-4">Регистрация</p>
                        <div class="form-row mb-4">
                            <div class="col">
                                <input type="text" id="registerName" class="form-control" placeholder="Имя">
                            </div>
                            <div class="col">
                                <input type="text" id="registerLastName" class="form-control" placeholder="Фамилия">
                            </div>
                        </div>
                        <div class="form-row mb-4">
                            <div class="col">
                                <input type="password" id="registerPassword" class="form-control" placeholder="Ваш пароль" aria-describedby="registerPassword">

                            </div>
                            <div class="col">
                                <input type="password" id="registerPasswordConfirm" class="form-control" placeholder="Повторите пароль" aria-describedby="registerPasswordConfirm">

                            </div>
                        </div>
                        <input type="email" id="registerEmail" class="form-control mb-4" placeholder="E-mail">
                        <input type="text" id="registerMobile" class="form-control" placeholder="Ваш мобильный телефон" aria-describedby="registerMobile">
                        <button id="submitRegistration" class="btn btn-info my-4 btn-block" type="submit">Зарегестрироваться!</button>
                        <p>Регистрация через социальные сети</p>
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