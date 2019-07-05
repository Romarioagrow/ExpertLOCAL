<#if product.type?contains("tv")>
    <tr>
        <th scope="row">Разрешение экрана:</th>
        <td><strong>${product.productParams.resolution}</strong></td>
    </tr>
    <tr>
        <th scope="row">Диагональ: </th>
        <td><strong>${product.productParams.diagonal}</strong></td>
    </tr>
    <tr>
        <th scope="row">SmartTV:</th>
        <td colspan="2"><strong><#if product.productParams.tvFeatures?contains("SmartTV")>Есть<#else>Нет</#if></strong></td>
    </tr>
    <tr>
        <th scope="row">Wi-Fi:</th>
        <td colspan="2"><strong><#if product.productParams.tvFeatures?contains("Wi-Fi")>Есть<#else>Нет</#if></strong></td>
    </tr>
    <tr>
        <th scope="row">Поддержка 3D:</th>
        <td colspan="2"><strong><#if product.productParams.tvFeatures?contains("3D")>Есть<#else>Нет</#if></strong></td>
    </tr>
    <tr>
        <th scope="row">Закругленный экран:</th>
        <td colspan="2"><strong><#if product.productParams.tvFeatures?contains("CurvedScreen")>Да<#else>Нет</#if></strong></td>
    </tr>
</#if>
<#if product.type?contains("stoves")>
    <tr>
        <th scope="row">Габариты плиты:</th>
        <td><strong>${product.productParams.stoveDimensions}</strong></td>
    </tr>
</#if>

<#--
<#if product.smthParam?contains("smthVal")>
    <tr>
        <th scope="row">smthParam</th>
        <td><strong>smthVal</strong></td>
    </tr>
</#if>
-->