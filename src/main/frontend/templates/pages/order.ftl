<#import "../parts/template.ftl" as t>
<@t.template>
    <div class="container">
        <div class="row">
            <h3 style="margin-top: 4vh">Ваш заказ</h3>
        </div>
        <div class="row">
            <div class="col">
                <#if orderedProducts??>
                    <#list orderedProducts?keys as prop>
                        ${prop.brand}
                        ${prop.model}<br>
                    </#list>
                </#if>
            </div>
        </div>
    </div>
</@t.template>
