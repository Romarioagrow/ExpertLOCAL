<#import "../parts/template.ftl" as t>
<#include "../parts/security.ftl">

<@t.template>
    <div class="container">
        <div class="row mt-3">
            <div class="col-10 ">
                <h1>${firstName}, добро пожаловать в личный кабинет!</h1>
            </div>
            <div class="col">
                <a type="button" class="btn btn-elegant" href="/user/logout">Выйти</a>
            </div>
        </div>
    </div>
</@t.template>