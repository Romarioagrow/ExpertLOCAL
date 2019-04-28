<#include "main-filter.ftl">

<#if url?contains("tv")>      <#include "tv-filter.ftl"></#if>
<#if url?contains("fridges")> <#include "fridges-filter.ftl"></#if>
<#if url?contains("stoves")>  <#include "stoves-filter.ftl"></#if>

<button type="submit" id="filter-button" class="btn btn-primary btn-block filter-button search">Показать</button>