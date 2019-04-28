<#import "template.ftl" as t>
<@t.template>
    <div class="container-fluid">
        <div class="row">
            <div class="col-3 marker1">
                <div class="marker2">
                    <#--<#if currentProduct??><span>Фильтры для ${currentProduct}</span></#if>-->
                </div>
            </div>
            <div class="col marker3">
                <div class="marker2">
                    <div>
                        <h3>Выбираем ${url}</h3>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-3 marker1 filters-col">
                <div class="marker2">
                    <form method="get" name="filters" id="filters">
                        <#include "../parts/filters/filters.ftl">
                    </form>
                </div>
            </div>
            <div class="col marker3">
                <div>
                </div>
                <#include "../parts/components/products.ftl">
            </div>
        </div>
    </div>
</@t.template>