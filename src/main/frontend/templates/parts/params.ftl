<#if product.type?contains("tv")>
    Диагональ:      <strong>${product.diagonal}</strong>
    Разрешение:     <strong>${product.resolution}</strong>
    <br>Особенности:<strong>${product.tvFeatures}</strong>
</#if>
<#if product.type?contains("stoves")>
    Габариты: <strong>${product.stoveDimensions}</strong>
</#if>
