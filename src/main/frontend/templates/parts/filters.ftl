<div>
    <button class="btn <#----> btn-block filter-button"
            type="button" data-toggle="collapse" data-target="#filter1" aria-expanded="false"><span>Цена и бренды</span>
    </button>
    <div class="collapse show" id="filter1">
        <div class="card card-body filter-filed">
            <div class="md-form input-group">
                <div class="input-group-prepend">
                    <span class="input-group-text md-addon">Цена</span>
                </div>
                <div class="row">
                    <div class="col-5">
                        <input type="text" class="form-control" id="sortmin" placeholder="От ₽">
                    </div>
                    <div class="col-5">
                        <input type="text" class="form-control" id="sortmax" placeholder="До ₽">
                    </div>
                </div>
            </div>
            <div class="md-form input-group">
                <div class="input-group-prepend">
                    <span class="input-group-text md-addon">Бренды</span>
                </div>
                <div class="form-check">
                    <div class="container" id="brands-view">
                        <#--<#include "../parts/brands.ftl">-->
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<#if url?contains("телевизоры")>
    <div>
        <button class="btn  btn-block filter-button" type="button" data-toggle="collapse" data-target="#tv-type" aria-expanded="false">
            <span>Тип телевизора</span>
        </button>
        <div class="collapse" id="tv-type">
            <div class="card card-body filter-filed" >
                <div class="md-form input-group">
                    <div class="container" style="background-color: #f0f4f9">
                        <div class="row">
                            <div class="col">
                                <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="form-check-input" name="Cont-tvType" id="ЖК" value="Тип: ЖК">
                                    <label class="custom-control-label" for="ЖК">ЖК</label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="form-check-input" name="Cont-tvType" id="LCD" value="Тип: LCD">
                                    <label class="custom-control-label" for="LCD">LCD</label>

                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="form-check-input" name="Cont-tvType" id="LED" value="Тип: LED">
                                    <label class="custom-control-label" for="LED">LED</label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="form-check-input" name="Cont-tvType" id="QLED" value="Тип: QLED">
                                    <label class="custom-control-label" for="QLED">QLED</label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <button class="btn  btn-block filter-button" type="button" data-toggle="collapse" data-target="#tv-filter1" aria-expanded="false">
            <span>Характеристики экрана</span>
        </button>
        <div class="collapse" id="tv-filter1">
            <div class="card card-body filter-filed">
                <div class="md-form input-group" style="background-color: #f0f4f9">
                    <div class="input-group-prepend">
                        <span class="input-group-text md-addon">Диагональ</span>
                    </div>
                    <div class="row">
                        <div class="col-5">
                            <input type="text" class="form-control" id="Comp-Min-Диагональ:" placeholder="От дюйм">
                        </div>
                        <div class="col-5">
                            <input type="text" class="form-control" id="Comp-Min-Диагональ:" placeholder="До дюйм">
                        </div>
                    </div>
                </div>
                <div class="md-form input-group" style="background-color: #f0f4f9">
                    <div class="input-group-prepend">
                        <span class="input-group-text md-addon">Разрешение экрана</span>
                    </div>
                    <div class="container">
                        <div class="row">
                            <div class="col">
                                <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="form-check-input" name="Cont-tvResolution" id="720p" value="Разрешение: 1366x768 (HD Ready)">
                                    <label class="custom-control-label" for="720p">720p HD</label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="form-check-input" name="Cont-tvResolution" id="1080p" value="1920x1080 (Full HD)">
                                    <label class="custom-control-label" for="1080p">1080p Full HD</label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="form-check-input" name="Cont-tvResolution" id="4K" value="3840x2160 (4K UHD)">
                                    <label class="custom-control-label" for="4K">4K UHD</label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="md-form input-group" style="background-color: #f0f4f9">
                    <div class="input-group-prepend">
                        <span class="input-group-text md-addon">Частота обновления экрана</span>
                    </div>
                    <div class="row">
                        <div class="col-5">
                            <input type="text" class="form-control" id="Comp-Min-Индекс частоты обновления:" placeholder="От Гц">
                        </div>
                        <div class="col-5">
                            <input type="text" class="form-control" id="Comp-Max-Индекс частоты обновления:" placeholder="До Гц">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div>
        <button class="btn  btn-block filter-button" type="button" data-toggle="collapse" data-target="#tv-filter2" aria-expanded="false">
            Особенности TV
        </button>
        <div class="collapse" id="tv-filter2">
            <div class="card card-body filter-filed">
                <div class="form-group">
                    <div class="custom-control custom-checkbox">
                        <input type="checkbox" class="form-check-input" name="Cont-MultiParams-tvParams" id="SmartTV" value="Smart TV: есть">
                        <label class="custom-control-label" for="SmartTV">SmartTV</label>
                    </div>
                    <div class="custom-control custom-checkbox">
                        <input type="checkbox" class="form-check-input" name="Cont-MultiParams-tvParams" id="DVB-T2" value="Приём DVB-T2: есть">
                        <label class="custom-control-label" for="DVB-T2">Приём DVB-T2</label>
                    </div>
                    <div class="custom-control custom-checkbox">
                        <input type="checkbox" class="form-check-input" name="Cont-tMultiParams-vParams" id="curved" value="Изогнутый экран: есть">
                        <label class="custom-control-label" for="curved">Изогнутый экран</label>
                    </div>
                </div>
            </div>
        </div>
    </div>
</#if>

<#if url?contains("кабели_тв")>
    <div>
        <button class="btn  btn-block filter-button" type="button" data-toggle="collapse" data-target="#tvCables1" aria-expanded="false">
            <span>Тип кабеля</span>
        </button>
        <div class="collapse" id="tvCables1">
            <div class="card card-body filter-filed">
                <div class="form-group">
                    <div class="custom-control custom-checkbox">
                        <input type="checkbox" class="form-check-input mb-1" name="Cont-tvCablesType" id="Кабель" value="Тип: кабель">
                        <label class="custom-control-label" for="Кабель">Кабель</label>
                    </div>
                    <div class="custom-control custom-checkbox">
                        <input type="checkbox" class="form-check-input mb-1" name="Cont-tvCablesType" id="Переходник" value="Тип: переходник">
                        <label class="custom-control-label" for="Переходник">Переходник</label>
                    </div>
                    <div class="custom-control custom-checkbox">
                        <input type="checkbox" class="form-check-input mb-1" name="Cont-tvCablesType" id="Удлинитель" value="Тип: удлинитель">
                        <label class="custom-control-label" for="Удлинитель">Удлинитель</label>
                    </div>
                </div>
            </div>
        </div>

        <button class="btn  btn-block filter-button" type="button" data-toggle="collapse" data-target="#tvCables2" aria-expanded="false">
            <span>Разъемы</span>
        </button>
        <div class="collapse" id="tvCables2">
            <div class="card card-body filter-filed">
                <div class="form-group">
                    <div class="custom-control custom-checkbox">
                        <input type="checkbox" class="form-check-input mb-1" name="Cont-tvCables" id="HDMI" value="HDMI - HDMI">
                        <label class="custom-control-label" for="HDMI">HDMI</label>
                    </div>
                    <div class="custom-control custom-checkbox">
                        <input type="checkbox" class="form-check-input mb-1" name="Cont-tvCables" id="Jack 3.5 (m)" value="Jack 3.5 (m) - Jack 3.5 (m)">
                        <label class="custom-control-label" for="Jack 3.5 (m)">AUX</label>
                    </div>
                    <div class="custom-control custom-checkbox">
                        <input type="checkbox" class="form-check-input mb-1" name="Cont-tvCables" id="Jack 3.5" value="Jack 3.5 (m) - Jack 3.5 (f)">
                        <label class="custom-control-label" for="Jack 3.5">Jack 3.5 (m) - Jack 3.5 (f)</label>
                    </div>
                    <div class="custom-control custom-checkbox">
                        <input type="checkbox" class="form-check-input mb-1" name="Cont-tvCables" id="Coax" value="Coax - Coax">
                        <label class="custom-control-label" for="Coax">Coax - Coax</label>
                    </div>
                    <div class="custom-control custom-checkbox">
                        <input type="checkbox" class="form-check-input mb-1" name="Cont-tvCables" id="SCART" value="SCART - SCART">
                        <label class="custom-control-label" for="SCART">SCART - SCART</label>
                    </div>
                    <div class="custom-control custom-checkbox">
                        <input type="checkbox" class="form-check-input mb-1" name="Cont-tvCables" id="SCART - 3xRCA" value="SCART - 3xRCA">
                        <label class="custom-control-label" for="SCART - 3xRCA">SCART - 3xRCA</label>
                    </div>
                    <div class="custom-control custom-checkbox">
                        <input type="checkbox" class="form-check-input mb-1" name="Cont-tvCables" id="Coax - F-штекер" value="Coax - F-штекер">
                        <label class="custom-control-label" for="Coax - F-штекер">Coax - F-штекер</label>
                    </div>
                    <div class="custom-control custom-checkbox">
                        <input type="checkbox" class="form-check-input mb-1" name="Cont-tvCables" id="3xRCA - Jack 3.5" value="3xRCA - Jack 3.5">
                        <label class="custom-control-label" for="3xRCA - Jack 3.5">3xRCA - Jack 3.5</label>
                    </div>
                    <div class="custom-control custom-checkbox">
                        <input type="checkbox" class="form-check-input mb-1" name="Cont-tvCables" id="2xJack 3.5 (f) - Jack 3.5 (m)" value="2xJack 3.5 (f) - Jack 3.5 (m)">
                        <label class="custom-control-label" for="2xJack 3.5 (f) - Jack 3.5 (m)">2xJack 3.5 (f) - Jack 3.5 (m)</label>
                    </div>
                    <div class="custom-control custom-checkbox">
                        <input type="checkbox" class="form-check-input mb-1" name="Cont-tvCables" id="ODT Toslink - ODT Toslink" value="ODT Toslink - ODT Toslink">
                        <label class="custom-control-label" for="ODT Toslink - ODT Toslink">ODT Toslink - ODT Toslink</label>
                    </div>
                </div>
            </div>
        </div>

        <button class="btn  btn-block filter-button" type="button" data-toggle="collapse" data-target="#tvCables3" aria-expanded="false">
            <span>Длинна кабеля</span>
        </button>
        <div class="collapse" id="tvCables3">
            <div class="card card-body filter-filed">
                <div class="form-group">
                    <div class="custom-control custom-checkbox">
                        <input type="checkbox" class="form-check-input mb-1" name="Cont-tvCablesLength" id="1 Метр" value="Длина кабеля: 1;">
                        <label class="custom-control-label" for="1 Метр">1 метр</label>
                    </div>
                    <div class="custom-control custom-checkbox">
                        <input type="checkbox" class="form-check-input mb-1" name="Cont-tvCablesLength" id="1.5 Метра" value="Длина кабеля: 1,5;">
                        <label class="custom-control-label" for="1.5 Метра">1.5 метра</label>
                    </div>
                    <div class="custom-control custom-checkbox">
                        <input type="checkbox" class="form-check-input mb-1" name="Cont-tvCablesLength" id="1.8 Метра" value="Длина кабеля: 1,8;">
                        <label class="custom-control-label" for="1.8 Метра">1.8 метра</label>
                    </div>
                    <div class="custom-control custom-checkbox">
                        <input type="checkbox" class="form-check-input mb-1" name="Cont-tvCablesLength" id="2 Метра" value="Длина кабеля: 2;">
                        <label class="custom-control-label" for="2 Метра">2 метра</label>
                    </div>
                    <div class="custom-control custom-checkbox">
                        <input type="checkbox" class="form-check-input mb-1" name="Cont-tvCablesLength" id="3 Метра" value="Длина кабеля: 3;">
                        <label class="custom-control-label" for="3 Метра">3 метра</label>
                    </div>
                    <div class="custom-control custom-checkbox">
                        <input type="checkbox" class="form-check-input mb-1" name="Cont-tvCablesLength" id="5 Метра" value="Длина кабеля: 5;">
                        <label class="custom-control-label" for="5 Метра">5 метров</label>
                    </div>
                </div>
            </div>
        </div>
    </div>
</#if>

<#if url?contains("кронштейны_тв")>
    <div>
        <button class="btn  btn-block filter-button" type="button" data-toggle="collapse" data-target="#tv-brackets1" aria-expanded="false">
            <span>Тип кронштейна</span>
        </button>
        <div class="collapse" id="tv-brackets1">
            <div class="card card-body filter-filed">
                <div class="md-form input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text md-addon">Тип</span>
                    </div>
                    <div class="container">
                        <div class="row">
                            <div class="col">
                                <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="form-check-input" name="Cont-tvBracketsType" id="Наклонно-поворотный" value="Тип кронштейна: наклонно-поворотный">
                                    <label class="custom-control-label" for="Наклонно-поворотный">Наклонно-поворотный</label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="form-check-input" name="Cont-tvBracketsType" id="Наклонный" value="Тип кронштейна: наклонный">
                                    <label class="custom-control-label" for="Наклонный">Наклонный</label>

                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="form-check-input" name="Cont-tvBracketsType" id="Поворотный" value="Тип кронштейна: поворотный">
                                    <label class="custom-control-label" for="Поворотный">Поворотный</label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="form-check-input" name="Cont-tvBracketsType" id="Фиксированный" value="Тип кронштейна: фиксированный">
                                    <label class="custom-control-label" for="Фиксированный">Фиксированный</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="input-group-prepend">
                        <span class="input-group-text md-addon">Способ крепления</span>
                    </div>
                    <div class="container">
                        <div class="row">
                            <div class="col">
                                <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="form-check-input" name="Cont-tvBracketsMount" id="Настенный" value="Способ крепления: настенный">
                                    <label class="custom-control-label" for="Настенный">Настенный</label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="form-check-input" name="Cont-tvBracketsMount" id="Настольный" value="Способ крепления: настольный">
                                    <label class="custom-control-label" for="Настольный">Настольный</label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <button class="btn  btn-block filter-button" type="button" data-toggle="collapse" data-target="#tv-brackets2" aria-expanded="false">
            <span>Нагрузка и габариты</span>
        </button>
        <div class="collapse" id="tv-brackets2">
            <div class="card card-body filter-filed">
                <div class="md-form input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text md-addon">Нагрузка</span>
                    </div>
                    <div class="row">
                        <div class="col-5">
                            <input type="text" class="form-control" id="Comp-Min-Нагрузка:" name="tvBracketsLoadMin" placeholder="От кг">
                        </div>
                        <div class="col-5">
                            <input type="text" class="form-control" id="Comp-Max-Нагрузка:" name="tvBracketsLoadMax" placeholder="До кг">
                        </div>
                    </div>
                </div>
                <div class="md-form input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text md-addon">Максимальная диагональ</span>
                    </div>
                    <div class="row">
                        <div class="col-5">
                            <input type="text" class="form-control" id="Comp-Min-Максимальная диагональ ТВ:" placeholder="От дюйм">
                        </div>
                        <div class="col-5">
                            <input type="text" class="form-control" id="Comp-Max-Максимальная диагональ ТВ:" placeholder="До дюйм">
                        </div>
                    </div>
                </div>
                <div class="md-form input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text md-addon">Расстояние от стены</span>
                    </div>
                    <div class="row">
                        <div class="col-5">
                            <input type="text" class="form-control" id="Comp-Min-Расстояние от стены:" placeholder="От мм">
                        </div>
                        <div class="col-5">
                            <input type="text" class="form-control" id="Comp-Max-Расстояние от стены:" placeholder="До мм">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</#if>

<#if url?contains("музыкальные_центры")>
    <div>
        <button class="btn  btn-block filter-button" type="button" data-toggle="collapse" data-target="#tv-brackets1" aria-expanded="false">
            <span>Тип музыкального центра</span>
        </button>
        <div class="collapse" id="tv-brackets1">
            <div class="card card-body filter-filed">
                <div class="md-form input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text md-addon">Тип устройства</span>
                    </div>
                    <div class="container">
                        <div class="row">
                            <div class="col">
                                <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="form-check-input" name="Cont-muzCenterType" id="мидисистема" value="Тип: мидисистема">
                                    <label class="custom-control-label" for="мидисистема">Миди-система</label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="form-check-input" name="Cont-muzCenterType" id="микросистема" value="Тип: микросистема">
                                    <label class="custom-control-label" for="микросистема">Микро-система</label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="form-check-input" name="Cont-muzCenterType" id="минисистема" value="Тип: минисистема">
                                    <label class="custom-control-label" for="минисистема">Мини-система</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="input-group-prepend">
                        <span class="input-group-text md-addon">Основной блок</span>
                    </div>
                    <div class="container">
                        <div class="row">
                            <div class="col">
                                <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="form-check-input" name="Cont-muzCenterMainBlock" id="компонентная система" value="Основной блок: компонентная система">
                                    <label class="custom-control-label" for="компонентная система">Компонентная система</label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="form-check-input" name="Cont-muzCenterMainBlock" id="моноблок" value="Основной блок: моноблок">
                                    <label class="custom-control-label" for="моноблок">Моноблок</label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="form-check-input" name="Cont-muzCenterMainBlock" id="одноблочная система" value="Основной блок: одноблочная система">
                                    <label class="custom-control-label" for="одноблочная система">Одноблочная система</label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <button class="btn  btn-block filter-button" type="button" data-toggle="collapse" data-target="#tv-brackets2" aria-expanded="false">
            <span>Акустика и звук</span>
        </button>
        <div class="collapse" id="tv-brackets2">
            <div class="card card-body filter-filed">
                <div class="md-form input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text md-addon">Мощность звука</span>
                    </div>
                    <div class="row">
                        <div class="col-5">
                            <input type="text" class="form-control" id="Comp-Special-Min-Полная выходная мощность (RMS):" placeholder="От Ватт">
                        </div>
                        <div class="col-5">
                            <input type="text" class="form-control" id="Comp-Special-Max-Полная выходная мощность (RMS):" placeholder="До Ватт">
                        </div>
                    </div>
                </div>
                <div class="md-form input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text md-addon">Аккустическая система</span>
                    </div>
                    <div class="container">
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-muzCenterAcoustic" id="аккустика1.0" value="Комплект акустических систем: 1.0">
                                <label class="custom-control-label" for="аккустика1.0">1.0</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-muzCenterAcoustic" id="аккустика2.0" value="Комплект акустических систем: 2.0">
                                <label class="custom-control-label" for="аккустика2.0">2.0</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-muzCenterAcoustic" id="аккустика2.1" value="Комплект акустических систем: 2.1">
                                <label class="custom-control-label" for="аккустика2.1">2.1</label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <button class="btn  btn-block filter-button" type="button" data-toggle="collapse" data-target="#tv-brackets3" aria-expanded="false">
            <span>Особенности музыкального центра</span>
        </button>
        <div class="collapse" id="tv-brackets3">
            <div class="card card-body filter-filed">
                <div class="md-form input-group">
                    <div class="container">
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-MultiParams-muzCenterParams" id="Эквалайзер" value="Эквалайзер: есть">
                                <label class="custom-control-label" for="Эквалайзер">Эквалайзер</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-MultiParams-muzCenterParams" id="Чтение с USB" value="Воспроизведение с USB-накопителей: есть">
                                <label class="custom-control-label" for="Чтение с USB">Чтение с USB</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-MultiParams-muzCenterParams" id="Поддержка Wi-Fi" value="Wi-Fi: есть">
                                <label class="custom-control-label" for="Поддержка Wi-Fi">Поддержка Wi-Fi</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-MultiParams-muzCenterParams" id="Наличие Bluetooth" value="Bluetooth: есть">
                                <label class="custom-control-label" for="Наличие Bluetooth">Наличие Bluetooth</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-MultiParams-muzCenterParams" id="Караоке" value="Караоке: есть">
                                <label class="custom-control-label" for="Караоке">Караоке</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-MultiParams-muzCenterParams" id="Часы" value="Часы: есть">
                                <label class="custom-control-label" for="Часы">Часы</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-MultiParams-muzCenterParams" id="Таймер" value="Таймер: есть">
                                <label class="custom-control-label" for="Таймер">Таймер</label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</#if>

<#if url?contains("телемебель")>
    <div>
        <button class="btn  btn-block filter-button" type="button" data-toggle="collapse" data-target="#tv-mebel1" aria-expanded="false">
            <span>Тип мебели</span>
        </button>
        <div class="collapse" id="tv-mebel1">
            <div class="card card-body filter-filed">
                <div class="md-form input-group">
                    <#--<div class="input-group-prepend">
                        <span class="input-group-text md-addon">Тип</span>
                    </div>-->
                    <div class="container">
                        <div class="row">
                            <div class="col">
                                <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="form-check-input" name="Cont-tvMebelType" id="С подвесом" value="Тип: тумба с подвесом">
                                    <label class="custom-control-label" for="С подвесом">Тумба с подвесом</label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="form-check-input" name="Cont-tvMebelType" id="Тумба без подвеса" value="Тип: тумба без подвеса">
                                    <label class="custom-control-label" for="Тумба без подвеса">Тумба без подвеса</label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <button class="btn  btn-block filter-button" type="button" data-toggle="collapse" data-target="#tv-mebel2" aria-expanded="false">
            <span>Габариты и нагрузка</span>
        </button>
        <div class="collapse" id="tv-mebel2">
            <div class="card card-body filter-filed">
                <div class="md-form input-group">
                    <div>
                        <div class="input-group-prepend">
                            <span class="input-group-text md-addon">Ширина</span>
                        </div>
                        <div class="container">
                            <div class="row">
                                <div class="col">
                                    <div class="custom-control custom-checkbox">
                                        <input type="checkbox" class="form-check-input" name="Cont-tvMebelWidth" id="80 см" value="Ширина: 80">
                                        <label class="custom-control-label" for="80 см">80 см</label>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col">
                                    <div class="custom-control custom-checkbox">
                                        <input type="checkbox" class="form-check-input" name="Cont-tvMebelWidth" id="90 см" value="Ширина: 89,4">
                                        <label class="custom-control-label" for="90 см">90 см</label>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col">
                                    <div class="custom-control custom-checkbox">
                                        <input type="checkbox" class="form-check-input" name="Cont-tvMebelWidth" id="110 см" value="Ширина: 110">
                                        <label class="custom-control-label" for="110 см">110 см</label>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col">
                                    <div class="custom-control custom-checkbox">
                                        <input type="checkbox" class="form-check-input" name="Cont-tvMebelWidth" id="150 см" value="Ширина: 150">
                                        <label class="custom-control-label" for="150 см">150 см</label>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="md-form input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text md-addon">Нагрузка</span>
                        </div>
                        <div class="container">
                            <div class="row">
                                <div class="col">
                                    <div class="custom-control custom-checkbox">
                                        <input type="checkbox" class="form-check-input" name="Cont-tvMebelLoad" id="До 30 кг" value="Нагрузка: 30">
                                        <label class="custom-control-label" for="До 30 кг">До 30 кг</label>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col">
                                    <div class="custom-control custom-checkbox">
                                        <input type="checkbox" class="form-check-input" name="Cont-tvMebelLoad" id="До 40 кг" value="Нагрузка: 40">
                                        <label class="custom-control-label" for="До 40 кг">До 40 кг</label>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col">
                                    <div class="custom-control custom-checkbox">
                                        <input type="checkbox" class="form-check-input" name="Cont-tvMebelLoad" id="До 50 кг" value="Нагрузка: 50">
                                        <label class="custom-control-label" for="До 50 кг">До 50 кг</label>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="md-form input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text md-addon">Под диагональ ТВ</span>
                    </div>
                    <div class="container">
                        <div class="row">
                            <div class="col-5">
                                <input type="text" class="form-control" id="Comp-Special-Min-Диагональ:" placeholder="От дюйм">
                            </div>
                            <div class="col-5">
                                <input type="text" class="form-control" id="Comp-Special-Max-Диагональ:" placeholder="До дюйм">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</#if>



<#if url?contains("lll")>
    <div>
        <button class="btn  btn-block filter-button" type="button" data-toggle="collapse" data-target="#tv-mebel1" aria-expanded="false">
            <span>Тип мебели</span>
        </button>
        <div class="collapse" id="tv-mebel1">
            <div class="card card-body filter-filed">
                <div class="md-form input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text md-addon">Тип чего-то</span>
                    </div>
                    <div class="container">
                        <div class="row">
                            <div class="col">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</#if>

<#if url?contains("холодильники")>
    <div>
        <button class="btn  btn-block filter-button" type="button" data-toggle="collapse" data-target="#fridge1" aria-expanded="false">
            <span>Тип холодильника</span>
        </button>
        <div class="collapse" id="fridge1">
            <div class="card card-body filter-filed">
                <div class="md-form input-group">
                    <div class="container">
                        <div class="row">
                            <div class="col">
                                <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="form-check-input" name="Cont-fridgeType" id="однокамерный" value="Тип: однокамерный">
                                    <label class="custom-control-label" for="однокамерный">Однокамерный</label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="form-check-input" name="Cont-fridgeType" id="двухкамерный" value="Тип: двухкамерный">
                                    <label class="custom-control-label" for="двухкамерный">Двухкамерный</label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="form-check-input" name="Cont-fridgeType" id="многокамерный" value="Тип: многокамерный">
                                    <label class="custom-control-label" for="многокамерный">Многокамерный</label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="form-check-input" name="Cont-fridgeType" id="витрина" value="Тип: витрина">
                                    <label class="custom-control-label" for="витрина">Холодильник-витрина</label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="form-check-input" name="Cont-fridgeType" id="винный шкаф" value="Тип: винный шкаф">
                                    <label class="custom-control-label" for="винный шкаф">Винный шкаф</label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="form-check-input" name="Cont-fridgeType" id="Side-by-Side" value="Тип: Side-by-Side">
                                    <label class="custom-control-label" for="Side-by-Side">Side-by-Side</label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <button class="btn  btn-block filter-button" type="button" data-toggle="collapse" data-target="#fridge2" aria-expanded="false">
            <span>Габариты</span>
        </button>
        <div class="collapse" id="fridge2">
            <div class="card card-body filter-filed">
                <div class="md-form input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text md-addon">Ширина</span>
                    </div>
                    <div class="container">
                        <div class="row">
                            <div class="col-5">
                                <input type="text" class="form-control" id="Comp-Min-Ширина:" placeholder="От см">
                            </div>
                            <div class="col-5">
                                <input type="text" class="form-control" id="Comp-Max-Ширина:" placeholder="До см">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="md-form input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text md-addon">Глубина</span>
                    </div>
                    <div class="container">
                        <div class="row">
                            <div class="col-5">
                                <input type="text" class="form-control" id="Comp-Min-Глубина:" placeholder="От см">
                            </div>
                            <div class="col-5">
                                <input type="text" class="form-control" id="Comp-Max-Глубина:" placeholder="До см">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="md-form input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text md-addon">Высота</span>
                    </div>
                    <div class="container">
                        <div class="row">
                            <div class="col-5">
                                <input type="text" class="form-control" id="Comp-Min-Высота:" name="fridgeHeightMin"  placeholder="От см">
                            </div>
                            <div class="col-5">
                                <input type="text" class="form-control" id="Comp-Max-Высота:" name="fridgeHeightMax"  placeholder="До см">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <button class="btn  btn-block filter-button" type="button" data-toggle="collapse" data-target="#fridge3" aria-expanded="false">
            <span>Морозильная камера и NoFrost</span>
        </button>
        <div class="collapse" id="fridge3">
            <div class="card card-body filter-filed">
                <div class="md-form input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text md-addon">Расположение морохильной камеры</span>
                    </div>
                    <div class="container">
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-fridgeFrozen" id="Снизу" value="Расположение морозильной камеры: снизу">
                                <label class="custom-control-label" for="Снизу">Снизу</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-fridgeFrozen" id="Сверху" value="Расположение морозильной камеры: сверху">
                                <label class="custom-control-label" for="Сверху">Сверху</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-fridgeFrozen" id="Сбоку" value="Расположение морозильной камеры: боковое">
                                <label class="custom-control-label" for="Сбоку">Сбоку</label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="md-form input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text md-addon">Система NoFrost</span>
                    </div>
                    <div class="container">
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-fridgeNoFrost" id="без системы NoFrost" value="Система NoFrost: без системы NoFrost">
                                <label class="custom-control-label" for="без системы NoFrost">Без системы NoFrost</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-fridgeNoFrost" id="в морозильном отделении" value="Система NoFrost: в морозильном отделении">
                                <label class="custom-control-label" for="в морозильном отделении">В морозильном отделении</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-fridgeNoFrost" id="в холодильном и морозильном отделении" value="Система NoFrost: в холодильном и морозильном отделении">
                                <label class="custom-control-label" for="в холодильном и морозильном отделении">В холодильном и морозильном отделении</label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <button class="btn  btn-block filter-button" type="button" data-toggle="collapse" data-target="#fridge4" aria-expanded="false">
            <span>Объемы камер</span>
        </button>
        <div class="collapse" id="fridge4">
            <div class="card card-body filter-filed">
                <div class="md-form input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text md-addon">Общий объем</span>
                    </div>
                    <div class="container">
                        <div class="row">
                            <div class="col-5">
                                <input type="text" class="form-control" id="Comp-Min-Общий объем:" placeholder="От литров">
                            </div>
                            <div class="col-5">
                                <input type="text" class="form-control" id="Comp-Max-Общий объем:" placeholder="До литров">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="md-form input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text md-addon">Объем холодильной камеры</span>
                    </div>
                    <div class="container">
                        <div class="row">
                            <div class="col-5">
                                <input type="text" class="form-control" id="Comp-Min-Объем холодильной камеры:" placeholder="От литров">
                            </div>
                            <div class="col-5">
                                <input type="text" class="form-control" id="Comp-Max-Объем холодильной камеры:" placeholder="До литров">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="md-form input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text md-addon">Объем морозильной камеры</span>
                    </div>
                    <div class="container">
                        <div class="row">
                            <div class="col-5">
                                <input type="text" class="form-control" id="Comp-Min-Объем морозильной камеры:" placeholder="От литров">
                            </div>
                            <div class="col-5">
                                <input type="text" class="form-control" id="Comp-Max-Объем морозильной камеры:" placeholder="До литров">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <button class="btn  btn-block filter-button" type="button" data-toggle="collapse" data-target="#fridge5" aria-expanded="false">
            <span>Энергопотребление</span>
        </button>
        <div class="collapse" id="fridge5">
            <div class="card card-body filter-filed">
                <div class="md-form input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text md-addon">Класс энергопотребления</span>
                    </div>
                    <div class="container">
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-fridgeEnergyClass" id="A" value="Класс энергопотребления: A;">
                                <label class="custom-control-label" for="A">A</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-fridgeEnergyClass" id="A+" value="Класс энергопотребления: A+;">
                                <label class="custom-control-label" for="A+">A+</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-fridgeEnergyClass" id="A++" value="Класс энергопотребления: A++;">
                                <label class="custom-control-label" for="A++">A++</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-fridgeEnergyClass" id="B" value="Класс энергопотребления: B;">
                                <label class="custom-control-label" for="B">B</label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <button class="btn  btn-block filter-button" type="button" data-toggle="collapse" data-target="#fridge6" aria-expanded="false">
            <span>Цвет</span>
        </button>
        <div class="collapse" id="fridge6">
            <div class="card card-body filter-filed">
                <div class="md-form input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text md-addon">Цвет холодильника</span>
                    </div>
                    <div class="container">
                        <div class="row">
                            <div class="col-6">
                                <input type="checkbox" class="form-check-input" name="Cont-fridgeColor" id="Белый" value="Цвет: белый">
                                <label class="form-check-label" for="Белый">Белый</label>
                            </div>
                            <div class="col-6">
                                <input type="checkbox" class="form-check-input" name="Cont-fridgeColor" id="бежевый" value="Цвет: бежевый">
                                <label class="form-check-label" for="бежевый">Бежевый</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-6">
                                <input type="checkbox" class="form-check-input" name="Cont-fridgeColor" id="бордовый" value="Цвет: бордовый">
                                <label class="form-check-label" for="бордовый">Бордовый</label>
                            </div>
                            <div class="col-6">
                                <input type="checkbox" class="form-check-input" name="Cont-fridgeColor" id="графит" value="Цвет: графит">
                                <label class="form-check-label" for="графит">Графит</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-6">
                                <input type="checkbox" class="form-check-input" name="Cont-fridgeColor" id="золотой" value="Цвет: золотой">
                                <label class="form-check-label" for="золотой">Золотой</label>
                            </div>
                            <div class="col-6">
                                <input type="checkbox" class="form-check-input" name="Cont-fridgeColor" id="золотистый" value="Цвет: золотистый">
                                <label class="form-check-label" for="золотистый">Золотистый</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-6">
                                <input type="checkbox" class="form-check-input" name="Cont-fridgeColor" id="красный" value="Цвет: красный">
                                <label class="form-check-label" for="красный">Красный</label>
                            </div>
                            <div class="col-6">
                                <input type="checkbox" class="form-check-input" name="Cont-fridgeColor" id="нержавеющая сталь" value="Цвет: нержавеющая сталь">
                                <label class="form-check-label" for="нержавеющая сталь">Стальной</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-6">
                                <input type="checkbox" class="form-check-input" name="Cont-fridgeColor" id="оранжевый" value="Цвет: оранжевый">
                                <label class="form-check-label" for="оранжевый">Оранжевый</label>
                            </div>
                            <div class="col-6">
                                <input type="checkbox" class="form-check-input" name="Cont-fridgeColor" id="серебристый" value="Цвет: серебристый">
                                <label class="form-check-label" for="серебристый">Серебристый</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-6">
                                <input type="checkbox" class="form-check-input" name="Cont-fridgeColor" id="серый" value="Цвет: серый">
                                <label class="form-check-label" for="серый">Серый</label>
                            </div>
                            <div class="col-6">
                                <input type="checkbox" class="form-check-input" name="Cont-fridgeColor" id="фиолетовый" value="Цвет: фиолетовый">
                                <label class="form-check-label" for="фиолетовый">Фиолетовый</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-6">
                                <input type="checkbox" class="form-check-input" name="Cont-fridgeColor" id="черный" value="Цвет: черный">
                                <label class="form-check-label" for="черный">Черный</label>
                            </div>
                            <div class="col-6">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <button class="btn  btn-block filter-button" type="button" data-toggle="collapse" data-target="#fridge7" aria-expanded="false">
            <span>Особенности холодильника</span>
        </button>
        <div class="collapse" id="fridge7">
            <div class="card card-body filter-filed">
                <div class="md-form input-group">
                    <div class="container">
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-MultiParams-fridgeParam" id="Внешний дисплей" value="Внешний дисплей: есть">
                                <label class="custom-control-label" for="Внешний дисплей">Внешний дисплей</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-MultiParams-fridgeParam" id="Суперзаморозка" value="Суперзаморозка: есть">
                                <label class="custom-control-label" for="Суперзаморозка">Суперзаморозка</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-MultiParams-fridgeParam" id="Инверторный" value="Инверторный: да">
                                <label class="custom-control-label" for="Инверторный">Инверторный</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-fridgeParam" id="Перевешиваемые двери" value="Перевешиваемые двери: есть">
                                <label class="custom-control-label" for="Перевешиваемые двери">Перевешиваемые двери</label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</#if>
<#if url?contains("морозильники")>
    <div>
        <button class="btn  btn-block filter-button" type="button" data-toggle="collapse" data-target="#freezer1" aria-expanded="false">
            <span>Тип морозильника</span>
        </button>
        <div class="collapse" id="freezer1">
            <div class="card card-body filter-filed">
                <div class="md-form input-group">
                    <div class="container">
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-freezerType" id="Вертикальная морозильная камера" value="Морозилки вертикальные">
                                <label class="custom-control-label" for="Вертикальная морозильная камера">Вертикальная морозильная камера</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-freezerType" id="Морозильный ларь" value="Морозильные лари">
                                <label class="custom-control-label" for="Морозильный ларь">Морозильный ларь</label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <button class="btn  btn-block filter-button" type="button" data-toggle="collapse" data-target="#freezer2" aria-expanded="false">
            <span>Объем и габариты</span>
        </button>
        <div class="collapse" id="freezer2">
            <div class="card card-body filter-filed">
                <div class="md-form input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text md-addon">Общий объем</span>
                    </div>
                    <div class="container">
                        <div class="row">
                            <div class="col-5">
                                <input type="text" class="form-control" id="Comp-Min-Общий объем:" placeholder="От литров">
                            </div>
                            <div class="col-5">
                                <input type="text" class="form-control" id="Comp-Max-Общий объем:" placeholder="До литров">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="md-form input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text md-addon">Глубина</span>
                    </div>
                    <div class="container">
                        <div class="row">
                            <div class="col-5">
                                <input type="text" class="form-control" id="Comp-Min-Глубина:" placeholder="От см">
                            </div>
                            <div class="col-5">
                                <input type="text" class="form-control" id="Comp-Max-Глубина:" placeholder="До см">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="md-form input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text md-addon">Ширина</span>
                    </div>
                    <div class="container">
                        <div class="row">
                            <div class="col-5">
                                <input type="text" class="form-control" id="Comp-Min-Ширина:" placeholder="От см">
                            </div>
                            <div class="col-5">
                                <input type="text" class="form-control" id="Comp-Max-Ширина:" placeholder="До см">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="md-form input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text md-addon">Высота</span>
                    </div>
                    <div class="container">
                        <div class="row">
                            <div class="col-5">
                                <input type="text" class="form-control" id="Comp-Min-Высота:" placeholder="От см">
                            </div>
                            <div class="col-5">
                                <input type="text" class="form-control" id="Comp-Max-Высота:" placeholder="До см">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <button class="btn  btn-block filter-button" type="button" data-toggle="collapse" data-target="#fridge5" aria-expanded="false">
            <span>Энергопотребление</span>
        </button>
        <div class="collapse" id="fridge5">
            <div class="card card-body filter-filed">
                <div class="md-form input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text md-addon">Класс энергопотребления</span>
                    </div>
                    <div class="container">
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-freezerEnergyClass" id="A" value="Класс энергопотребления: A;">
                                <label class="custom-control-label" for="A">A</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-freezerEnergyClass" id="A+" value="Класс энергопотребления: A+;">
                                <label class="custom-control-label" for="A+">A+</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-freezerEnergyClass" id="B" value="Класс энергопотребления: B;">
                                <label class="custom-control-label" for="B">B</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-freezerEnergyClass" id="C" value="Класс энергопотребления: C;">
                                <label class="custom-control-label" for="C">C</label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</#if>
<#if url?contains("электрические_плиты")>
    <div>
        <button class="btn  btn-block filter-button" type="button" data-toggle="collapse" data-target="#group1" aria-expanded="false">
            <span>Тип варочной поверхности</span>
        </button>
        <div class="collapse" id="group1">
            <div class="card card-body filter-filed">
                <div class="md-form input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text md-addon">Варочная поверхность</span>
                    </div>
                    <div class="container">
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-elStoveType" id="стеклокерамика" value="Тип варочной панели: стеклокерамика;">
                                <label class="custom-control-label" for="стеклокерамика">Стеклокерамика</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-elStoveType" id="металлические" value="Тип варочной панели: металлические конфорки;">
                                <label class="custom-control-label" for="металлические">Металлические конфорки</label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <button class="btn  btn-block filter-button" type="button" data-toggle="collapse" data-target="#group2" aria-expanded="false">
            <span>Количество конфорок</span>
        </button>
        <div class="collapse" id="group2">
            <div class="card card-body filter-filed">
                <div class="md-form input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text md-addon">Варочная поверхность</span>
                    </div>
                    <div class="container">
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-elStoveConf" id="2 конфорки" value="Количество конфорок: 2;">
                                <label class="custom-control-label" for="2 конфорки">2 конфорки</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-elStoveConf" id="3 конфорки" value="Количество конфорок: 3;">
                                <label class="custom-control-label" for="3 конфорки">3 конфорки</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-elStoveConf" id="4 конфорки" value="Количество конфорок: 4;">
                                <label class="custom-control-label" for="4 конфорки">4 конфорки</label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <button class="btn  btn-block filter-button" type="button" data-toggle="collapse" data-target="#group3" aria-expanded="false">
            <span>Габариты</span>
        </button>
        <div class="collapse" id="group3">
            <div class="card card-body filter-filed">
                <div class="md-form input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text md-addon">Ширина</span>
                    </div>
                    <div class="container">
                        <div class="row">
                            <div class="col-5">
                                <input type="text" class="form-control" id="Comp-Min-Ширина:" placeholder="От см">
                            </div>
                            <div class="col-5">
                                <input type="text" class="form-control" id="Comp-Max-Ширина:" placeholder="До см">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <button class="btn  btn-block filter-button" type="button" data-toggle="collapse" data-target="#group4" aria-expanded="false">
            <span>Цвет</span>
        </button>
        <div class="collapse" id="group4">
            <div class="card card-body filter-filed">
                <div class="md-form input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text md-addon">Цвет плиты</span>
                    </div>
                    <div class="container">
                        <div class="row">
                            <div class="col-6">
                                <input type="checkbox" class="form-check-input" name="Cont-StoveColor" id="Бежевый" value="Цвет: бежевый">
                                <label class="form-check-label" for="Бежевый">Бежевый</label>
                            </div>
                            <div class="col-6">
                                <input type="checkbox" class="form-check-input" name="Cont-StoveColor" id="белый" value="Цвет: белый">
                                <label class="form-check-label" for="белый">Белый</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-6">
                                <input type="checkbox" class="form-check-input" name="Cont-StoveColor" id="коричневый" value="Цвет: коричневый">
                                <label class="form-check-label" for="коричневый">Коричневый</label>
                            </div>
                            <div class="col-6">
                                <input type="checkbox" class="form-check-input" name="Cont-StoveColor" id="нержавеющая сталь" value="Цвет: нержавеющая сталь">
                                <label class="form-check-label" for="нержавеющая сталь">Стальной</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-6">
                                <input type="checkbox" class="form-check-input" name="Cont-StoveColor" id="серебристый" value="Цвет: серебристый">
                                <label class="form-check-label" for="серебристый">Серебристый</label>
                            </div>
                            <div class="col-6">
                                <input type="checkbox" class="form-check-input" name="Cont-StoveColor" id="серый" value="Цвет: серый">
                                <label class="form-check-label" for="серый">Серый</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-6">
                                <input type="checkbox" class="form-check-input" name="Cont-StoveColor" id="черный" value="Цвет: черный">
                                <label class="form-check-label" for="черный">Черный</label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <button class="btn  btn-block filter-button" type="button" data-toggle="collapse" data-target="#group5" aria-expanded="false">
            <span>Особенности плиты</span>
        </button>
        <div class="collapse" id="group5">
            <div class="card card-body filter-filed">
                <div class="md-form input-group">
                    <div class="container">
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-MultiParams-StoveParam" id="Конвекция" value="Конвекция: есть">
                                <label class="custom-control-label" for="Конвекция">Конвекция</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-MultiParams-StoveParam" id="Индукция" value="Индукция: есть">
                                <label class="custom-control-label" for="Индукция">Индукция</label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</#if>
<#if url?contains("газовые_плиты")>
    <div>
        <button class="btn  btn-block filter-button" type="button" data-toggle="collapse" data-target="#group1" aria-expanded="false">
            <span>Варочная панель</span>
        </button>
        <div class="collapse" id="group1">
            <div class="card card-body filter-filed">
                <div class="md-form input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text md-addon">Тип варочной панели</span>
                    </div>
                    <div class="container">
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-gasStoveType" id="газовая" value="Тип варочной панели: газовая">
                                <label class="custom-control-label" for="газовая">Газовая</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-gasStoveType" id="комбинированная" value="Тип варочной панели: комбинированная">
                                <label class="custom-control-label" for="комбинированная">Комбинированная</label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="md-form input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text md-addon">Электроподжиг</span>
                    </div>
                    <div class="container">
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-gasStoveElectroBurn" id="Автоматический" value="Электроподжиг варочной панели: автоматический">
                                <label class="custom-control-label" for="Автоматический">Автоматический поджиг</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-gasStoveElectroBurn" id="кнопкой" value="Электроподжиг варочной панели: механический">
                                <label class="custom-control-label" for="кнопкой">Поджиг кнопкой</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-gasStoveElectroBurn" id="Без" value="Электроподжиг варочной панели: нет">
                                <label class="custom-control-label" for="Без">Без поджига</label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <button class="btn  btn-block filter-button" type="button" data-toggle="collapse" data-target="#group2" aria-expanded="false">
            <span>Конфорки</span>
        </button>
        <div class="collapse" id="group2">
            <div class="card card-body filter-filed">
                <div class="md-form input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text md-addon">Количество газовых конфорок</span>
                    </div>
                    <div class="container">
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-gasStoveConf" id="2 конфорки" value="Количество газовых конфорок: 2">
                                <label class="custom-control-label" for="2 конфорки">2 конфорки</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-gasStoveConf" id="3 конфорки" value="Количество газовых конфорок: 3">
                                <label class="custom-control-label" for="3 конфорки">3 конфорки</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-gasStoveConf" id="4 конфорки" value="Количество газовых конфорок: 4">
                                <label class="custom-control-label" for="4 конфорки">4 конфорки</label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="md-form input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text md-addon">Контроль газа конфорок</span>
                    </div>
                    <div class="container">
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-gasStoveGasControlConf" id="Контроль газа конфорок" value="Газ-контроль конфорок: да">
                                <label class="custom-control-label" for="Контроль газа конфорок">Есть</label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <button class="btn  btn-block filter-button" type="button" data-toggle="collapse" data-target="#group3" aria-expanded="false">
            <span>Духовка</span>
        </button>
        <div class="collapse" id="group3">
            <div class="card card-body filter-filed">
                <div class="md-form input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text md-addon">Тип духовки</span>
                    </div>
                    <div class="container">
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-gasStoveD" id="Газовая" value="Тип духовки: газовая">
                                <label class="custom-control-label" for="Газовая">Газовая духовка</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-gasStoveD" id="Электрическая" value="Тип духовки: электрическая">
                                <label class="custom-control-label" for="Электрическая">Электрическая духовка</label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="md-form input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text md-addon">Контроль газа духовки</span>
                    </div>
                    <div class="container">
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-gasStoveGasControlD" id="Контроль газа духовки" value="Газ-контроль духовки: да">
                                <label class="custom-control-label" for="Контроль газа духовки">Есть</label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <button class="btn  btn-block filter-button" type="button" data-toggle="collapse" data-target="group4" aria-expanded="false">
            <span>Габариты</span>
        </button>
        <div class="collapse" id="group4">
            <div class="card card-body filter-filed">
                <div class="md-form input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text md-addon">Ширина</span>
                    </div>
                    <div class="container">
                        <div class="row">
                            <div class="col-5">
                                <input type="text" class="form-control" id="Comp-Min-Ширина:" placeholder="От см">
                            </div>
                            <div class="col-5">
                                <input type="text" class="form-control" id="Comp-Max-Ширина:" placeholder="До см">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="md-form input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text md-addon">Высота</span>
                    </div>
                    <div class="container">
                        <div class="row">
                            <div class="col-5">
                                <input type="text" class="form-control" id="Comp-Min-Высота:" placeholder="От см">
                            </div>
                            <div class="col-5">
                                <input type="text" class="form-control" id="Comp-Max-Высота:" placeholder="До см">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="md-form input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text md-addon">Глубина</span>
                    </div>
                    <div class="container">
                        <div class="row">
                            <div class="col-5">
                                <input type="text" class="form-control" id="Comp-Min-Глубина:" placeholder="От см">
                            </div>
                            <div class="col-5">
                                <input type="text" class="form-control" id="Comp-Max-Глубина:" placeholder="До см">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <button class="btn  btn-block filter-button" type="button" data-toggle="collapse" data-target="#group5" aria-expanded="false">
            <span>Цвет</span>
        </button>
        <div class="collapse" id="group5">
            <div class="card card-body filter-filed">
                <div class="md-form input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text md-addon">Цвет плиты</span>
                    </div>
                    <div class="container">
                        <div class="row">
                            <div class="col-6">
                                <input type="checkbox" class="form-check-input" name="Cont-StoveColor" id="Бежевый" value="Цвет: бежевый">
                                <label class="form-check-label" for="Бежевый">Бежевый</label>
                            </div>
                            <div class="col-6">
                                <input type="checkbox" class="form-check-input" name="Cont-StoveColor" id="белый" value="Цвет: белый">
                                <label class="form-check-label" for="белый">Белый</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-6">
                                <input type="checkbox" class="form-check-input" name="Cont-StoveColor" id="коричневый" value="Цвет: коричневый">
                                <label class="form-check-label" for="коричневый">Коричневый</label>
                            </div>
                            <div class="col-6">
                                <input type="checkbox" class="form-check-input" name="Cont-StoveColor" id="серебристый" value="Цвет: серебристый">
                                <label class="form-check-label" for="серебристый">Серебристый</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-6">
                                <input type="checkbox" class="form-check-input" name="Cont-StoveColor" id="серый" value="Цвет: серый">
                                <label class="form-check-label" for="серый">Серый</label>
                            </div>
                            <div class="col-6">
                                <input type="checkbox" class="form-check-input" name="Cont-StoveColor" id="черный" value="Цвет: черный">
                                <label class="form-check-label" for="черный">Черный</label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</#if>
<#if url?contains("микроволновые_печи")>
    <div>
        <button class="btn  btn-block filter-button" type="button" data-toggle="collapse" data-target="#group1" aria-expanded="false">
            <span>Тип печи</span>
        </button>
        <div class="collapse" id="group1">
            <div class="card card-body filter-filed">
                <div class="md-form input-group">
                    <div class="container">
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-MicroWaveType" id="с грилем" value="Тип: с грилем;">
                                <label class="custom-control-label" for="с грилем">С грилем</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-MicroWaveType" id="с грилем и конвекцией" value="Тип: с грилем и конвекцией;">
                                <label class="custom-control-label" for="с грилем и конвекцией">С грилем и конвекцией</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-MicroWaveType" id="соло" value="Тип: соло">
                                <label class="custom-control-label" for="соло">Соло</label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <button class="btn  btn-block filter-button" type="button" data-toggle="collapse" data-target="#group2" aria-expanded="false">
            <span>Управление и кнопки</span>
        </button>
        <div class="collapse" id="group2">
            <div class="card card-body filter-filed">
                <div class="md-form input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text md-addon">Тип управления</span>
                    </div>
                    <div class="container">
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-MicroWaveControl" id="сенсорное" value="Тип управления: сенсорное">
                                <label class="custom-control-label" for="сенсорное">Сенсорное</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-MicroWaveControl" id="электронное" value="Тип управления: электронное">
                                <label class="custom-control-label" for="электронное">Электронное</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-MicroWaveControl" id="механическое" value="Тип управления: механическое">
                                <label class="custom-control-label" for="механическое">Механическое</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-MicroWaveControl" id="электромеханическое" value="Тип управления: электромеханическое">
                                <label class="custom-control-label" for="электромеханическое">Электромеханическое</label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="md-form input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text md-addon">Переключатели</span>
                    </div>
                    <div class="container">
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-MicroWaveSwitchers" id="сенсорные" value="Переключатели: сенсорные;">
                                <label class="custom-control-label" for="сенсорные">Сенсорные</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-MicroWaveSwitchers" id="кнопочные" value="Переключатели: кнопочные;">
                                <label class="custom-control-label" for="кнопочные">Кнопочные</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-MicroWaveSwitchers" id="поворотные" value="Переключатели: поворотные;">
                                <label class="custom-control-label" for="поворотные">Поворотные</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-MicroWaveSwitchers" id="поворотные/кнопочные" value="Переключатели: поворотные/кнопочные;">
                                <label class="custom-control-label" for="поворотные/кнопочные">Поворотные/кнопочные</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-MicroWaveType" id="сенсорные/кнопочные" value="Переключатели: сенсорные/кнопочные;">
                                <label class="custom-control-label" for="сенсорные/кнопочные">Сенсорные/кнопочные</label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <button class="btn  btn-block filter-button" type="button" data-toggle="collapse" data-target="#group3" aria-expanded="false">
            <span>Мощность и объем</span>
        </button>
        <div class="collapse" id="group3">
            <div class="card card-body filter-filed">
                <div class="md-form input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text md-addon">Мошность печи</span>
                    </div>
                    <div class="container">
                        <div class="row">
                            <div class="col-5">
                                <input type="text" class="form-control" id="Comp-Min-Мощность микроволн:" placeholder="От ватт">
                            </div>
                            <div class="col-5">
                                <input type="text" class="form-control" id="Comp-Max-Мощность микроволн:" placeholder="До ватт">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="md-form input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text md-addon">Объем</span>
                    </div>
                    <div class="container">
                        <div class="row">
                            <div class="col-5">
                                <input type="text" class="form-control" id="Comp-Min-Объем:" placeholder="От литров">
                            </div>
                            <div class="col-5">
                                <input type="text" class="form-control" id="Comp-Max-Объем:" placeholder="До литров">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <button class="btn  btn-block filter-button" type="button" data-toggle="collapse" data-target="#group4" aria-expanded="false">
            <span>Внутреннее покрытие камеры</span>
        </button>
        <div class="collapse" id="group4">
            <div class="card card-body filter-filed">
                <div class="md-form input-group">
                    <div class="container">
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-MicroWaveInner" id="Керамическое" value="Внутреннее покрытие камеры: керамика">
                                <label class="custom-control-label" for="Керамическое">Керамическое</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-MicroWaveInner" id="Эмалевое" value="Внутреннее покрытие камеры: эмаль">
                                <label class="custom-control-label" for="Эмалевое">Эмалевое</label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <button class="btn  btn-block filter-button" type="button" data-toggle="collapse" data-target="#group6" aria-expanded="false">
            <span>Цвет</span>
        </button>
        <div class="collapse" id="group6">
            <div class="card card-body filter-filed">
                <div class="md-form input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text md-addon">Цвет плиты</span>
                    </div>
                    <div class="container">
                        <div class="row">
                            <div class="col-6">
                                <input type="checkbox" class="form-check-input" name="Cont-MicroWaveColor" id="Бежевый" value="Цвет: бежевый">
                                <label class="form-check-label" for="Бежевый">Бежевый</label>
                            </div>
                            <div class="col-6">
                                <input type="checkbox" class="form-check-input" name="Cont-MicroWaveColor" id="белый" value="Цвет: белый">
                                <label class="form-check-label" for="белый">Белый</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-6">
                                <input type="checkbox" class="form-check-input" name="Cont-MicroWaveColor" id="серебристый" value="Цвет: серебристый">
                                <label class="form-check-label" for="серебристый">Серебристый</label>
                            </div>
                            <div class="col-6">
                                <input type="checkbox" class="form-check-input" name="Cont-MicroWaveColor" id="черный" value="Цвет: черный">
                                <label class="form-check-label" for="черный">Черный</label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <button class="btn  btn-block filter-button" type="button" data-toggle="collapse" data-target="#group5" aria-expanded="false">
            <span>Особенности</span>
        </button>
        <div class="collapse" id="group5">
            <div class="card card-body filter-filed">
                <div class="md-form input-group">
                    <div class="container">
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-MultiParams-MicroWaveParams" id="Автоматическая разморозка " value="Автоматическая разморозка: есть">
                                <label class="custom-control-label" for="Автоматическая разморозка ">Автоматическая разморозка </label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</#if>
<#if url?contains("электрические_чайники")>
    <div>
        <button class="btn  btn-block filter-button" type="button" data-toggle="collapse" data-target="#group1" aria-expanded="false">
            <span>Тип и материал</span>
        </button>
        <div class="collapse" id="group1">
            <div class="card card-body filter-filed">
                <div class="md-form input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text md-addon">Тип чайника</span>
                    </div>
                    <div class="container">
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-TeapotType" id="чайник" value="Тип: чайник электрический">
                                <label class="custom-control-label" for="чайник">Чайник</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-TeapotType" id="термопот" value="Тип: термопот">
                                <label class="custom-control-label" for="термопот">Термопот</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-TeapotType" id="самовар" value="Тип: самовар">
                                <label class="custom-control-label" for="самовар">Самовар</label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="md-form input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text md-addon">Материал чайника</span>
                    </div>
                    <div class="container">
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-TeapotMaterial" id="керамика" value="Материал корпуса: керамика;">
                                <label class="custom-control-label" for="керамика">Керамика</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-TeapotMaterial" id="металл" value="Материал корпуса: металл;">
                                <label class="custom-control-label" for="металл">Металл</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-TeapotMaterial" id="стекло" value="Материал корпуса: стекло;">
                                <label class="custom-control-label" for="стекло">Стекло</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-TeapotMaterial" id="нержавеющая сталь" value="Материал корпуса: нержавеющая сталь;">
                                <label class="custom-control-label" for="нержавеющая сталь">Нержавеющая сталь</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-TeapotMaterial" id="металл, пластик" value="Материал корпуса: металл, пластик;">
                                <label class="custom-control-label" for="металл, пластик">Металл, пластик</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-TeapotMaterial" id="металл, стекло" value="Материал корпуса: металл, стекло;">
                                <label class="custom-control-label" for="металл, стекло">Металл, стекло</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-TeapotMaterial" id="пластик, стекло" value="Материал корпуса: пластик, стекло;">
                                <label class="custom-control-label" for="пластик, стекло">Пластик, стекло</label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <button class="btn  btn-block filter-button" type="button" data-toggle="collapse" data-target="#group2" aria-expanded="false">
            <span>Мощность и объем</span>
        </button>
        <div class="collapse" id="group2">
            <div class="card card-body filter-filed">
                <div class="md-form input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text md-addon">Мошность</span>
                    </div>
                    <div class="container">
                        <div class="row">
                            <div class="col-5">
                                <input type="text" class="form-control" id="Comp-Min-Мощность:" placeholder="От ватт">
                            </div>
                            <div class="col-5">
                                <input type="text" class="form-control" id="Comp-Max-Мощность:" placeholder="До ватт">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="md-form input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text md-addon">Объем</span>
                    </div>
                    <div class="container">
                        <div class="row">
                            <div class="col-5">
                                <input type="text" class="form-control" id="Comp-Min-Объем:" placeholder="От литров">
                            </div>
                            <div class="col-5">
                                <input type="text" class="form-control" id="Comp-Max-Объем:" placeholder="До литров">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <button class="btn  btn-block filter-button" type="button" data-toggle="collapse" data-target="#group6" aria-expanded="false">
            <span>Цвет</span>
        </button>
        <div class="collapse" id="group6">
            <div class="card card-body filter-filed">
                <div class="md-form input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text md-addon">Цвет</span>
                    </div>
                    <div class="container">
                        <div class="row">
                            <div class="col-6">
                                <input type="checkbox" class="form-check-input" name="Cont-Color" id="Бежевый" value="Цвет: бежевый">
                                <label class="form-check-label" for="Бежевый">Бежевый</label>
                            </div>
                            <div class="col-6">
                                <input type="checkbox" class="form-check-input" name="Cont-Color" id="белый" value="Цвет: белый">
                                <label class="form-check-label" for="белый">Белый</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-6">
                                <input type="checkbox" class="form-check-input" name="Cont-Color" id="бордовый" value="Цвет: бордовый">
                                <label class="form-check-label" for="бордовый">Бордовый</label>
                            </div>
                            <div class="col-6">
                                <input type="checkbox" class="form-check-input" name="Cont-Color" id="голубой" value="Цвет: голубой">
                                <label class="form-check-label" for="голубой">Голубой</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-6">
                                <input type="checkbox" class="form-check-input" name="Cont-Color" id="желтый" value="Цвет: желтый">
                                <label class="form-check-label" for="желтый">Желтый</label>
                            </div>
                            <div class="col-6">
                                <input type="checkbox" class="form-check-input" name="Cont-Color" id="зеленый" value="Цвет: зеленый">
                                <label class="form-check-label" for="зеленый">Зеленый</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-6">
                                <input type="checkbox" class="form-check-input" name="Cont-Color" id="красный" value="Цвет: красный">
                                <label class="form-check-label" for="красный">Красный</label>
                            </div>
                            <div class="col-6">
                                <input type="checkbox" class="form-check-input" name="Cont-Color" id="оранжевый" value="Цвет: оранжевый">
                                <label class="form-check-label" for="оранжевый">Оранжевый</label>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-6">
                                <input type="checkbox" class="form-check-input" name="Cont-Color" id="прозрачный" value="Цвет: прозрачный">
                                <label class="form-check-label" for="прозрачный">Прозрачный</label>
                            </div>
                            <div class="col-6">
                                <input type="checkbox" class="form-check-input" name="Cont-Color" id="рисунок" value="Цвет: рисунок">
                                <label class="form-check-label" for="рисунок">Рисунок</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-6">
                                <input type="checkbox" class="form-check-input" name="Cont-Color" id="розовый" value="Цвет: розовый">
                                <label class="form-check-label" for="розовый">Розовый</label>
                            </div>
                            <div class="col-6">
                                <input type="checkbox" class="form-check-input" name="Cont-Color" id="серебристый" value="Цвет: серебристый">
                                <label class="form-check-label" for="серебристый">Серебристый</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-6">
                                <input type="checkbox" class="form-check-input" name="Cont-Color" id="серый" value="Цвет: серый">
                                <label class="form-check-label" for="серый">Серый</label>
                            </div>
                            <div class="col-6">
                                <input type="checkbox" class="form-check-input" name="Cont-Color" id="синий" value="Цвет: синий">
                                <label class="form-check-label" for="синий">Синий</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-6">
                                <input type="checkbox" class="form-check-input" name="Cont-Color" id="сиреневый" value="Цвет: сиреневый">
                                <label class="form-check-label" for="сиреневый">Сиреневый</label>
                            </div>
                            <div class="col-6">
                                <input type="checkbox" class="form-check-input" name="Cont-Color" id="фиолетовый" value="Цвет: фиолетовый">
                                <label class="form-check-label" for="фиолетовый">Фиолетовый</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-6">
                                <input type="checkbox" class="form-check-input" name="Cont-Color" id="черный" value="Цвет: черный">
                                <label class="form-check-label" for="черный">Черный</label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <button class="btn  btn-block filter-button" type="button" data-toggle="collapse" data-target="#group5" aria-expanded="false">
            <span>Особенности</span>
        </button>
        <div class="collapse" id="group5">
            <div class="card card-body filter-filed">
                <div class="md-form input-group">
                    <div class="container">
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-MultiParams-TeapotParams" id="Двойные стенки" value="Двойные стенки: есть">
                                <label class="custom-control-label" for="Двойные стенки">Двойные стенки</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-MultiParams-TeapotParams" id="Подсветка" value="Подсветка: есть">
                                <label class="custom-control-label" for="Подсветка">Подсветка</label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</#if>
<#if url?contains("стиральные_машины")>
    <div>
        <button class="btn  btn-block filter-button" type="button" data-toggle="collapse" data-target="#group1" aria-expanded="false">
            <span>Загрузка белья</span>
        </button>
        <div class="collapse" id="group1">
            <div class="card card-body filter-filed">
                <div class="md-form input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text md-addon">Тип загрузки</span>
                    </div>
                    <div class="container">
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-ЗагрузкаБелья" id="вертикальная" value="Тип загрузки: вертикальная">
                                <label class="custom-control-label" for="вертикальная">Вертикальная</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-ЗагрузкаБелья" id="фронтальная" value="Тип загрузки: фронтальная">
                                <label class="custom-control-label" for="фронтальная">Фронтальная</label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="md-form input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text md-addon">Вес загружаемого белья</span>
                    </div>
                    <div class="container">
                        <div class="row">
                            <div class="col-5">
                                <input type="text" class="form-control" id="Comp-Min-Загрузка белья:" placeholder="От кг">
                            </div>
                            <div class="col-5">
                                <input type="text" class="form-control" id="Comp-Max-Загрузка белья:" placeholder="До кг">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <button class="btn  btn-block filter-button" type="button" data-toggle="collapse" data-target="#group2" aria-expanded="false">
            <span>Отжим</span>
        </button>
        <div class="collapse" id="group2">
            <div class="md-form input-group">
                <div class="input-group-prepend">
                    <span class="input-group-text md-addon">Скорость отжима</span>
                </div>
                <div class="container">
                    <div class="row">
                        <div class="col-5">
                            <input type="text" class="form-control" id="Comp-Min-Скорость отжима:" placeholder="От оборот/мин">
                        </div>
                        <div class="col-5">
                            <input type="text" class="form-control" id="Comp-Max-Скорость отжима:" placeholder="До оборот/мин">
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <button class="btn  btn-block filter-button" type="button" data-toggle="collapse" data-target="#group3" aria-expanded="false">
            <span>Габариты</span>
        </button>
        <div class="collapse" id="group3">
            <div class="card card-body filter-filed">
                <div class="md-form input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text md-addon">Ширина</span>
                    </div>
                    <div class="container">
                        <div class="row">
                            <div class="col-5">
                                <input type="text" class="form-control" id="Comp-Min-Ширина:" placeholder="От см">
                            </div>
                            <div class="col-5">
                                <input type="text" class="form-control" id="Comp-Max-Ширина:" placeholder="До см">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="md-form input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text md-addon">Глубина</span>
                    </div>
                    <div class="container">
                        <div class="row">
                            <div class="col-5">
                                <input type="text" class="form-control" id="Comp-Min-Глубина:" placeholder="От см">
                            </div>
                            <div class="col-5">
                                <input type="text" class="form-control" id="Comp-Max-Глубина:" placeholder="До см">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <button class="btn  btn-block filter-button" type="button" data-toggle="collapse" data-target="#group4" aria-expanded="false">
            <span>Особенности</span>
        </button>
        <div class="collapse" id="group4">
            <div class="card card-body filter-filed">
                <div class="md-form input-group">
                    <div class="container">
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-MultiParams-ОсобенностиСтирМаш" id="Сушка" value="Сушка: да">
                                <label class="custom-control-label" for="Сушка">Сушка</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-MultiParams-ОсобенностиСтирМаш" id="Дисплей" value="Дисплей: есть">
                                <label class="custom-control-label" for="Дисплей">Дисплей</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-MultiParams-ОсобенностиСтирМаш" id="Подача пара" value="Подача пара: есть">
                                <label class="custom-control-label" for="Подача пара">Подача пара</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-MultiParams-ОсобенностиСтирМаш" id="Экономичная программа" value="Экономичная программа: есть">
                                <label class="custom-control-label" for="Экономичная программа">Экономичная программа</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-MultiParams-ОсобенностиСтирМаш" id="Отложенный старт" value="Отложенный старт: есть">
                                <label class="custom-control-label" for="Отложенный старт">Отложенный старт</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-MultiParams-ОсобенностиСтирМаш" id="Защита от протечек" value="Защита от протечек: да">
                                <label class="custom-control-label" for="Защита от протечек">Защита от протечек</label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</#if>
<#if url?contains("пылесосы")>
    <div>
        <button class="btn  btn-block filter-button" type="button" data-toggle="collapse" data-target="#group1" aria-expanded="false">
            <span>Тип пылесоса</span>
        </button>
        <div class="collapse" id="group1">
            <div class="card card-body filter-filed">
                <div class="md-form input-group">
                    <div class="container">
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-ТипПылесоса" id="вертикальный" value="Тип: вертикальный">
                                <label class="custom-control-label" for="вертикальный">Вертикальный</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-ТипПылесоса" id="моющий" value="Тип: моющий">
                                <label class="custom-control-label" for="моющий">Моющий</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-ТипПылесоса" id="обычный" value="Тип: обычный">
                                <label class="custom-control-label" for="обычный">Обычный</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-ТипПылесоса" id="ручной" value="Тип: ручной">
                                <label class="custom-control-label" for="ручной">Ручной</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-ТипПылесоса" id="робот" value="Тип: робот">
                                <label class="custom-control-label" for="робот">Робот</label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <button class="btn  btn-block filter-button" type="button" data-toggle="collapse" data-target="#group2" aria-expanded="false">
            <span>Пылесборник</span>
        </button>
        <div class="collapse" id="group2">
            <div class="card card-body filter-filed">
                <div class="md-form input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text md-addon">Тип пылесборника</span>
                    </div>
                    <div class="container">
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-ТипПылесборника" id="без мешка" value="Тип пылесборника: без мешка">
                                <label class="custom-control-label" for="без мешка">Без мешка</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-ТипПылесборника" id="мешок" value="Тип пылесборника: мешок">
                                <label class="custom-control-label" for="мешок">С мешоком</label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="md-form input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text md-addon">Объём пылесборника</span>
                    </div>
                    <div class="container">
                        <div class="row">
                            <div class="col-5">
                                <input type="text" class="form-control" id="Comp-Min-Объём пылесборника:" placeholder="От Литров">
                            </div>
                            <div class="col-5">
                                <input type="text" class="form-control" id="Comp-Max-Объём пылесборника:" placeholder="До Литров">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <button class="btn  btn-block filter-button" type="button" data-toggle="collapse" data-target="#group3" aria-expanded="false">
            <span>Мощность пылесоса</span>
        </button>
        <div class="collapse" id="group3">
            <div class="card card-body filter-filed">
                <div class="md-form input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text md-addon">Мощность всасывания</span>
                    </div>
                    <div class="container">
                        <div class="row">
                            <div class="col-5">
                                <input type="text" class="form-control" id="Comp-Min-Мощность всасывания:" placeholder="От Ватт">
                            </div>
                            <div class="col-5">
                                <input type="text" class="form-control" id="Comp-Max-Мощность всасывания:" placeholder="До Ватт">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="md-form input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text md-addon">Потребляемая мощность</span>
                    </div>
                    <div class="container">
                        <div class="row">
                            <div class="col-5">
                                <input type="text" class="form-control" id="Comp-Min-Потребляемая мощность:" placeholder="От Ватт">
                            </div>
                            <div class="col-5">
                                <input type="text" class="form-control" id="Comp-Max-Потребляемая мощность:" placeholder="До Ватт">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <button class="btn  btn-block filter-button" type="button" data-toggle="collapse" data-target="#group4" aria-expanded="false">
            <span>Особенности</span>
        </button>
        <div class="collapse" id="group4">
            <div class="card card-body filter-filed">
                <div class="md-form input-group">
                    <div class="container">
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-MultiParams-ОсобенностиПылесоса" id="Аквафильтр" value="Аквафильтр: есть">
                                <label class="custom-control-label" for="Аквафильтр">Аквафильтр</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-MultiParams-ОсобенностиПылесоса" id="Турбощетка в комплекте" value="Турбощетка в комплекте: есть">
                                <label class="custom-control-label" for="Турбощетка в комплекте">Турбощетка в комплекте</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-MultiParams-ОсобенностиПылесоса" id="Управление мощностью на рукоятке" value="Управление мощностью на рукоятке: есть">
                                <label class="custom-control-label" for="Управление мощностью на рукоятке">Управление мощностью на рукоятке</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-MultiParams-ОсобенностиПылесоса" id="Регулятор мощности на корпусе" value="Регулятор мощности на корпусе: есть">
                                <label class="custom-control-label" for="Регулятор мощности на корпусе">Регулятор мощности на корпусе</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-MultiParams-ОсобенностиПылесоса" id="Турбощетка в комплекте" value="Турбощетка в комплекте: есть">
                                <label class="custom-control-label" for="Турбощетка в комплекте">Турбощетка в комплекте</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-MultiParams-ОсобенностиПылесоса" id="Автомобильный пылесос" value="Автомобильный: да">
                                <label class="custom-control-label" for="Автомобильный пылесос">Автомобильный пылесос</label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</#if>
<#if url?contains("утюги")>
    <div>
        <button class="btn  btn-block filter-button" type="button" data-toggle="collapse" data-target="#group1" aria-expanded="false">
            <span>Мощность</span>
        </button>
        <div class="collapse" id="group1">
            <div class="card card-body filter-filed">
                <div class="md-form input-group">
                    <div class="container">
                        <div class="row">
                            <div class="col-5">
                                <input type="text" class="form-control" id="Comp-Min-Мощность:" placeholder="От Ватт">
                            </div>
                            <div class="col-5">
                                <input type="text" class="form-control" id="Comp-Max-Мощность:" placeholder="До Ватт">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <button class="btn  btn-block filter-button" type="button" data-toggle="collapse" data-target="#group" aria-expanded="false">
            <span>Материал подошвы</span>
        </button>
        <div class="collapse" id="group">
            <div class="card card-body filter-filed">
                <div class="md-form input-group">
                    <div class="container">
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-МатериалПодошвы" id="алюминий" value="Материал подошвы: алюминий">
                                <label class="custom-control-label" for="алюминий">Алюминий</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-МатериалПодошвы" id="керамика" value="Материал подошвы: керамика">
                                <label class="custom-control-label" for="керамика">Керамика</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-МатериалПодошвы" id="нержавеющая сталь" value="Материал подошвы: нержавеющая сталь">
                                <label class="custom-control-label" for="нержавеющая сталь">Нержавеющая сталь</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-МатериалПодошвы" id="тефлон" value="Материал подошвы: тефлон">
                                <label class="custom-control-label" for="тефлон">Тефлон</label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <button class="btn  btn-block filter-button" type="button" data-toggle="collapse" data-target="#group10" aria-expanded="false">
            <span>Особенности утюга</span>
        </button>
        <div class="collapse" id="group10">
            <div class="card card-body filter-filed">
                <div class="md-form input-group">
                    <div class="container">
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-MultiParams-Утюг" id="Автоматическое отключение" value="Автоматическое отключение: есть">
                                <label class="custom-control-label" for="Автоматическое отключение">Автоматическое отключение</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-MultiParams-Утюг" id="Парогенератор" value="Парогенератор: есть">
                                <label class="custom-control-label" for="Парогенератор">Парогенератор</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-MultiParams-Утюг" id="Противокапельная система" value="Противокапельная система: есть">
                                <label class="custom-control-label" for="Противокапельная система">Противокапельная система</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-MultiParams-Утюг" id="Беспроводное использование" value="Беспроводное использование: да">
                                <label class="custom-control-label" for="Беспроводное использование">Беспроводное использование</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-MultiParams-Утюг" id="Вертикальное отпаривание" value="Вертикальное отпаривание: есть">
                                <label class="custom-control-label" for="Вертикальное отпаривание">Вертикальное отпаривание</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-MultiParams-Утюг" id="Система самоочистки" value=" Система самоочистки: есть">
                                <label class="custom-control-label" for="Система самоочистки">Система самоочистки</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-MultiParams-Утюг" id="Паровой удар" value="Паровой удар: есть">
                                <label class="custom-control-label" for="Паровой удар">Паровой удар</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-MultiParams-Утюг" id="Дорожный" value="Дорожный: да">
                                <label class="custom-control-label" for="Дорожный">Дорожный утюг</label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</#if>
<#if url?contains("швейные_машины")>
    <div>
        <button class="btn  btn-block filter-button" type="button" data-toggle="collapse" data-target="#group1" aria-expanded="false">
            <span>Тип машины</span>
        </button>
        <div class="collapse" id="group1">
            <div class="card card-body filter-filed">
                <div class="md-form input-group">
                    <div class="container">
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-ТипШвМш" id="компьютеризированная" value="Тип машины: компьютеризированная">
                                <label class="custom-control-label" for="компьютеризированная">компьютеризированная</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-ТипШвМш" id="электромеханическая" value="Тип машины: электромеханическая">
                                <label class="custom-control-label" for="электромеханическая">электромеханическая</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-ТипШвМш" id="электронная" value="Тип машины: электронная">
                                <label class="custom-control-label" for="электронная">электронная</label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <button class="btn  btn-block filter-button" type="button" data-toggle="collapse" data-target="#group2" aria-expanded="false">
            <span>Тип челнока</span>
        </button>
        <div class="collapse" id="group2">
            <div class="card card-body filter-filed">
                <div class="md-form input-group">
                    <div class="container">
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-ШвМшЧелн" id="вертикальный" value="Тип челнока: вертикальный">
                                <label class="custom-control-label" for="вертикальный">вертикальный</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-ШвМшЧелн" id="горизонтальный" value="Тип челнока: горизонтальный">
                                <label class="custom-control-label" for="горизонтальный">горизонтальный</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-ШвМшЧелн" id="качающийся" value="Тип челнока: качающийся">
                                <label class="custom-control-label" for="качающийся">качающийся</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-ШвМшЧелн" id="ротационный" value="Тип челнока: ротационный горизонтальный">
                                <label class="custom-control-label" for="ротационный">ротационный</label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <button class="btn  btn-block filter-button" type="button" data-toggle="collapse" data-target="#group3" aria-expanded="false">
            <span>Выполнение петли</span>
        </button>
        <div class="collapse" id="group3">
            <div class="card card-body filter-filed">
                <div class="md-form input-group">
                    <div class="container">
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-ШвМшПетл" id="автоматическое" value="Выполнение петли: автоматическое">
                                <label class="custom-control-label" for="автоматическое">автоматическое</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-ШвМшПетл" id="полуавтомат" value="Выполнение петли: полуавтомат">
                                <label class="custom-control-label" for="полуавтомат">полуавтомат</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-ШвМшПетл" id="ручное" value="Выполнение петли: ручное">
                                <label class="custom-control-label" for="ручное">ручное</label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <button class="btn  btn-block filter-button" type="button" data-toggle="collapse" data-target="#group4" aria-expanded="false">
            <span>Количество швейных операций</span>
        </button>
        <div class="collapse" id="group4">
            <div class="card card-body filter-filed">
                <div class="md-form input-group">
                    <div class="container">
                        <div class="row">
                            <div class="col-5">
                                <input type="text" class="form-control" id="Comp-Min-Количество швейных операций:" placeholder="От штук">
                            </div>
                            <div class="col-5">
                                <input type="text" class="form-control" id="Comp-Max-Количество швейных операций:" placeholder="До штук">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <button class="btn  btn-block filter-button" type="button" data-toggle="collapse" data-target="#group10" aria-expanded="false">
            <span>Особенности</span>
        </button>
        <div class="collapse" id="group10">
            <div class="card card-body filter-filed">
                <div class="md-form input-group">
                    <div class="container">
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-MultiParams-ШвМш" id="Кнопка реверса" value="Кнопка реверса: есть">
                                <label class="custom-control-label" for="Кнопка реверса">Кнопка реверса</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-MultiParams-ШвМш" id="Регулировка длины стежка" value="Регулировка длины стежка: есть">
                                <label class="custom-control-label" for="Регулировка длины стежка">Регулировка длины стежка</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-MultiParams-ШвМш" id="Лапка для пришивания пуговиц" value="Кнопка реверса: есть">
                                <label class="custom-control-label" for="Лапка для пришивания пуговиц">Лапка для пришивания пуговиц</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-MultiParams-ШвМш" id="Потайная строчка" value="Потайная строчка: есть">
                                <label class="custom-control-label" for="Потайная строчка">Потайная строчка</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-MultiParams-ШвМш" id="Оверлочная строчка" value="Оверлочная строчка: есть">
                                <label class="custom-control-label" for="Оверлочная строчка">Оверлочная строчка</label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</#if>

<#if url?contains("встраиваемые_духовые_шкафы")>
    <div>
        <button class="btn  btn-block filter-button" type="button" data-toggle="collapse" data-target="#group1" aria-expanded="false">
            <span>Тип духовки</span>
        </button>
        <div class="collapse" id="group1">
            <div class="card card-body filter-filed">
                <div class="md-form input-group">
                    <div class="container">
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-ТипДух" id="электрический" value="Тип духовки: электрический">
                                <label class="custom-control-label" for="электрический">Электрический</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-ТипДух" id="газовый" value="Тип духовки: газовый">
                                <label class="custom-control-label" for="газовый">Газовый</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-ТипДух" id="комбинированный" value="Тип духовки: комбинированный">
                                <label class="custom-control-label" for="комбинированный">Комбинированный</label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <button class="btn  btn-block filter-button" type="button" data-toggle="collapse" data-target="#group66" aria-expanded="false">
            <span>Количество режимов</span>
        </button>
        <div class="collapse" id="group66">
            <div class="card card-body filter-filed">
                <div class="md-form input-group">
                    <div class="container">
                        <div class="row">
                            <div class="col-5">
                                <input type="text" class="form-control" id="Comp-Min-Количество режимов:" placeholder="От штук">
                            </div>
                            <div class="col-5">
                                <input type="text" class="form-control" id="Comp-Max-Количество режимов:" placeholder="До штук">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <button class="btn  btn-block filter-button" type="button" data-toggle="collapse" data-target="#group55" aria-expanded="false">
            <span>Объем духовки</span>
        </button>
        <div class="collapse" id="group55">
            <div class="card card-body filter-filed">
                <div class="md-form input-group">
                    <div class="container">
                        <div class="row">
                            <div class="col-5">
                                <input type="text" class="form-control" id="Comp-Min-Объем духовки:" placeholder="От Литров">
                            </div>
                            <div class="col-5">
                                <input type="text" class="form-control" id="Comp-Max-Объем духовки:" placeholder="До Литров">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <button class="btn  btn-block filter-button" type="button" data-toggle="collapse" data-target="#group14" aria-expanded="false">
            <span>Внутреннее покрытие</span>
        </button>
        <div class="collapse" id="group14">
            <div class="card card-body filter-filed">
                <div class="md-form input-group">
                    <div class="container">
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-" id="керамика" value="Внутреннее покрытие: керамика">
                                <label class="custom-control-label" for="керамика">Керамика</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-" id="биокерамика" value="Внутреннее покрытие: биокерамика">
                                <label class="custom-control-label" for="биокерамика">Биокерамика</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-" id="нержавеющая сталь" value="Внутреннее покрытие: нержавеющая сталь">
                                <label class="custom-control-label" for="нержавеющая сталь">Нержавеющая сталь</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-" id="эмаль" value="Внутреннее покрытие: эмаль">
                                <label class="custom-control-label" for="эмаль">Эмаль</label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <button class="btn  btn-block filter-button" type="button" data-toggle="collapse" data-target="#group13" aria-expanded="false">
            <span>Переключатели</span>
        </button>
        <div class="collapse" id="group13">
            <div class="card card-body filter-filed">
                <div class="md-form input-group">
                    <div class="container">
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-Переключатели" id="сенсорные" value="Переключатели: сенсорные">
                                <label class="custom-control-label" for="сенсорные">Сенсорные</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-Переключатели" id="поворотные" value="Переключатели: поворотные">
                                <label class="custom-control-label" for="поворотные">Поворотные</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-Переключатели" id="NULL2" value="Переключатели: Нажимные">
                                <label class="custom-control-label" for="NULL">Нажимные</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-Переключатели" id="утапливаемые" value="Переключатели: поворотные/сенсорные">
                                <label class="custom-control-label" for="утапливаемые">Поворотные и сенсорные</label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <button class="btn  btn-block filter-button" type="button" data-toggle="collapse" data-target="#fridge55" aria-expanded="false">
            <span>Энергопотребление</span>
        </button>
        <div class="collapse" id="fridge55">
            <div class="card card-body filter-filed">
                <div class="md-form input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text md-addon">Класс энергопотребления</span>
                    </div>
                    <div class="container">
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-fridgeEnergyClass" id="A" value="Класс энергопотребления: A;">
                                <label class="custom-control-label" for="A">A</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-fridgeEnergyClass" id="A+" value="Класс энергопотребления: A+;">
                                <label class="custom-control-label" for="A+">A+</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-fridgeEnergyClass" id="A++" value="Класс энергопотребления: A++;">
                                <label class="custom-control-label" for="A++">A++</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-fridgeEnergyClass" id="B" value="Класс энергопотребления: B;">
                                <label class="custom-control-label" for="B">B</label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <button class="btn  btn-block filter-button" type="button" data-toggle="collapse" data-target="#group11" aria-expanded="false">
            <span>Очистка духовки</span>
        </button>
        <div class="collapse" id="group11">
            <div class="card card-body filter-filed">
                <div class="md-form input-group">
                    <div class="container">
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-" id="AquaClean" value="Очистка духовки: AquaClean">
                                <label class="custom-control-label" for="AquaClean">AquaClean</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-" id="EcoClean" value="Очистка духовки: EcoClean;">
                                <label class="custom-control-label" for="EcoClean">EcoClean</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-" id="EcoClean Direct" value="Очистка духовки: EcoClean Direct;">
                                <label class="custom-control-label" for="EcoClean Direct">EcoClean Direct</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-" id="EasyClean" value="Очистка духовки: EasyClean">
                                <label class="custom-control-label" for="EasyClean">EasyClean</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-" id="традиционная" value="Очистка духовки: традиционная">
                                <label class="custom-control-label" for="традиционная">Традиционная</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-" id="гидролизная" value="Очистка духовки: гидролизная">
                                <label class="custom-control-label" for="гидролизная">Гидролизная</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-" id="каталитическая" value="Очистка духовки: каталитическая">
                                <label class="custom-control-label" for="каталитическая">Каталитическая</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-" id="очистка паром" value="Очистка духовки: очистка паром">
                                <label class="custom-control-label" for="очистка паром">Очистка паром</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-" id="пиролитическая" value="Очистка духовки: пиролитическая">
                                <label class="custom-control-label" for="пиролитическая">Пиролитическая</label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <button class="btn  btn-block filter-button" type="button" data-toggle="collapse" data-target="#group40" aria-expanded="false">
            <span>Габариты</span>
        </button>
        <div class="collapse" id="group40">
            <div class="card card-body filter-filed">
                <div class="md-form input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text md-addon">Ширина встраивания</span>
                    </div>
                    <div class="container">
                        <div class="row">
                            <div class="col-5">
                                <input type="text" class="form-control" id="Comp-Min-Ширина встраивания:" placeholder="От мм">
                            </div>
                            <div class="col-5">
                                <input type="text" class="form-control" id="Comp-Max-Ширина встраивания:" placeholder="До мм">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="md-form input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text md-addon">Высота встраивания</span>
                    </div>
                    <div class="container">
                        <div class="row">
                            <div class="col-5">
                                <input type="text" class="form-control" id="Comp-Min-Высота встраивания:" placeholder="От мм">
                            </div>
                            <div class="col-5">
                                <input type="text" class="form-control" id="Comp-Max-Высота встраивания:" placeholder="До мм">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="md-form input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text md-addon">Ширина</span>
                    </div>
                    <div class="container">
                        <div class="row">
                            <div class="col-5">
                                <input type="text" class="form-control" id="Comp-Min-Ширина:" placeholder="От мм">
                            </div>
                            <div class="col-5">
                                <input type="text" class="form-control" id="Comp-Max-Ширина:" placeholder="До мм">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="md-form input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text md-addon">Глубина</span>
                    </div>
                    <div class="container">
                        <div class="row">
                            <div class="col-5">
                                <input type="text" class="form-control" id="Comp-Min-Глубина:" placeholder="От см">
                            </div>
                            <div class="col-5">
                                <input type="text" class="form-control" id="Comp-Max-Глубина:" placeholder="До см">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <button class="btn  btn-block filter-button" type="button" data-toggle="collapse" data-target="#group10" aria-expanded="false">
            <span>Особенности</span>
        </button>
        <div class="collapse" id="group10">
            <div class="card card-body filter-filed">
                <div class="md-form input-group">
                    <div class="container">
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-MultiParams-XXX" id="Гриль" value="Гриль: есть">
                                <label class="custom-control-label" for="Гриль">Гриль</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-MultiParams-XXX" id="Конвекция" value="Конвекция: есть">
                                <label class="custom-control-label" for="Конвекция">Конвекция</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-MultiParams-XXX" id="Телескопические направляющие" value="Телескопические направляющие: есть">
                                <label class="custom-control-label" for="Телескопические направляющие">Телескопические направляющие</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-MultiParams-XXX" id="Съемные направляющие" value="Съемные направляющие: есть">
                                <label class="custom-control-label" for="Съемные направляющие">Съемные направляющие</label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <button class="btn  btn-block filter-button" type="button" data-toggle="collapse" data-target="#fridge55" aria-expanded="false">
            <span>Цвет</span>
        </button>
        <div class="collapse" id="fridge55">
            <div class="card card-body filter-filed">
                <div class="md-form input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text md-addon">Класс энергопотребления</span>
                    </div>
                    <div class="container">
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-fridgeEnergyClass" id="бежевый" value="Цвет: бежевый">
                                <label class="custom-control-label" for="бежевый">Бежевый</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-fridgeEnergyClass" id="белый" value="Цвет: белый">
                                <label class="custom-control-label" for="белый">Белый</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-fridgeEnergyClass" id="коричневый" value="Цвет: коричневый">
                                <label class="custom-control-label" for="коричневый">Коричневый</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-fridgeEnergyClass" id="серебристый" value="Цвет: серебристый">
                                <label class="custom-control-label" for="серебристый">Серебристый</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-fridgeEnergyClass" id="черный" value="Цвет: черный">
                                <label class="custom-control-label" for="черный">Черный</label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</#if>
<#if url?contains("встраиваемые_варочные_панели")>
    <div>
        <button class="btn  btn-block filter-button" type="button" data-toggle="collapse" data-target="#group11" aria-expanded="false">
            <span>Тип варочной поверхности</span>
        </button>
        <div class="collapse" id="group11">
            <div class="card card-body filter-filed">
                <div class="md-form input-group">
                    <div class="container">
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-" id="электрическая" value="Тип поверхности: электрическая">
                                <label class="custom-control-label" for="электрическая">Электрическая</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-" id="газовая" value="Тип поверхности: газовая">
                                <label class="custom-control-label" for="газовая">Газовая</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-" id="комбинированная" value="Тип поверхности: комбинированная">
                                <label class="custom-control-label" for="комбинированная">Комбинированная</label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <button class="btn  btn-block filter-button" type="button" data-toggle="collapse" data-target="#group1" aria-expanded="false">
            <span>Конфорки</span>
        </button>
        <div class="collapse" id="group1">
            <div class="card card-body filter-filed">
                <div class="md-form input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text md-addon">Количество конфорок</span>
                    </div>
                    <div class="container">
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-Конф" id="1" value="Всего конфорок: 1">
                                <label class="custom-control-label" for="1">1 конфорка</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-Конф" id="2" value="Всего конфорок: 2">
                                <label class="custom-control-label" for="2">2 конфорки</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-Конф" id="3" value="Всего конфорок: 3">
                                <label class="custom-control-label" for="3">3 конфорки</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-Конф" id="4" value="Всего конфорок: 4">
                                <label class="custom-control-label" for="4">4 конфорки</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-Конф" id="5" value="Всего конфорок: 5">
                                <label class="custom-control-label" for="5">5 конфорок</label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="md-form input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text md-addon">Количество двухконтурных конфорок</span>
                    </div>
                    <div class="container">
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-КонфДвух" id="1двух" value="Конфорок двухконтурных: 1">
                                <label class="custom-control-label" for="1двух">1 конфорка</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-КонфДвух" id="2двух" value="Конфорок двухконтурных: 2">
                                <label class="custom-control-label" for="2двух">2 конфорки</label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="md-form input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text md-addon">Количество индукционных конфорок</span>
                    </div>
                    <div class="container">
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-КонфИнд" id="1инд" value="Конфорок индукционных: 1">
                                <label class="custom-control-label" for="1инд">1 конфорка</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-КонфИнд" id="2инд" value="Конфорок индукционных: 2">
                                <label class="custom-control-label" for="2инд">2 конфорки</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-КонфИнд" id="3инд" value="Конфорок индукционных: 3">
                                <label class="custom-control-label" for="3инд">3 конфорки</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-КонфИнд" id="4инд" value="Конфорок индукционных: 4">
                                <label class="custom-control-label" for="4инд">4 конфорки</label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <button class="btn  btn-block filter-button" type="button" data-toggle="collapse" data-target="#group33" aria-expanded="false">
            <span>Тип управления</span>
        </button>
        <div class="collapse" id="group33">
            <div class="card card-body filter-filed">
                <div class="md-form input-group">
                    <div class="container">
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-Упр" id="сенсорное" value="Тип управления: сенсорный">
                                <label class="custom-control-label" for="сенсорное">Сенсорное управление</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-Упр" id="Механическое" value="Тип управления: механический">
                                <label class="custom-control-label" for="Механическое">Механическое управление</label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <button class="btn  btn-block filter-button" type="button" data-toggle="collapse" data-target="#group40" aria-expanded="false">
            <span>Габариты</span>
        </button>
        <div class="collapse" id="group40">
            <div class="card card-body filter-filed">
                <div class="md-form input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text md-addon">Ширина встраивания</span>
                    </div>
                    <div class="container">
                        <div class="row">
                            <div class="col-5">
                                <input type="text" class="form-control" id="Comp-Min-Ширина встраивания:" placeholder="От мм">
                            </div>
                            <div class="col-5">
                                <input type="text" class="form-control" id="Comp-Max-Ширина встраивания:" placeholder="До мм">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="md-form input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text md-addon">Глубина встраивания</span>
                    </div>
                    <div class="container">
                        <div class="row">
                            <div class="col-5">
                                <input type="text" class="form-control" id="Comp-Min-Глубина встраивания:" placeholder="От мм">
                            </div>
                            <div class="col-5">
                                <input type="text" class="form-control" id="Comp-Max-Глубина встраивания:" placeholder="До мм">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="md-form input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text md-addon">Ширина</span>
                    </div>
                    <div class="container">
                        <div class="row">
                            <div class="col-5">
                                <input type="text" class="form-control" id="Comp-Min-Ширина:" placeholder="От мм">
                            </div>
                            <div class="col-5">
                                <input type="text" class="form-control" id="Comp-Max-Ширина:" placeholder="До мм">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="md-form input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text md-addon">Глубина</span>
                    </div>
                    <div class="container">
                        <div class="row">
                            <div class="col-5">
                                <input type="text" class="form-control" id="Comp-Min-Глубина:" placeholder="От см">
                            </div>
                            <div class="col-5">
                                <input type="text" class="form-control" id="Comp-Max-Глубина:" placeholder="До см">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <button class="btn  btn-block filter-button" type="button" data-toggle="collapse" data-target="#group" aria-expanded="false">
            <span>Цвет и материал</span>
        </button>
        <div class="collapse" id="group">
            <div class="card card-body filter-filed">
                <div class="md-form input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text md-addon">Материал поверхности</span>
                    </div>
                    <div class="container">
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-" id="закаленное стекло" value="Рабочая поверхность: закаленное стекло">
                                <label class="custom-control-label" for="закаленное стекло">Закаленное стекло</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-" id="нержавеющая сталь" value="Рабочая поверхность: нержавеющая сталь">
                                <label class="custom-control-label" for="нержавеющая сталь">Нержавеющая сталь</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-" id="стеклокерамика" value="Рабочая поверхность: стеклокерамика">
                                <label class="custom-control-label" for="стеклокерамика">Стеклокерамика</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-" id="эмалированный металл" value="Рабочая поверхность: эмалированный металл">
                                <label class="custom-control-label" for="эмалированный металл">Эмалированный металл</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-" id="эмаль" value="Рабочая поверхность: эмаль">
                                <label class="custom-control-label" for="эмаль">Эмаль</label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="md-form input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text md-addon">Цвет поверхности</span>
                    </div>
                    <div class="container">
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-" id="бежевый" value="Цвет панели конфорок: бежевый">
                                <label class="custom-control-label" for="бежевый">Бежевый</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-" id="белый" value="Цвет панели конфорок: белый">
                                <label class="custom-control-label" for="белый">Белый</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-" id="коричневый" value="Цвет панели конфорок: коричневый">
                                <label class="custom-control-label" for="коричневый">Коричневый</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-" id="рисунок" value="Цвет панели конфорок: рисунок">
                                <label class="custom-control-label" for="рисунок">Рисунок</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-" id="серебристый" value="Цвет панели конфорок: серебристый">
                                <label class="custom-control-label" for="серебристый">Серебристый</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-" id="серый" value="Цвет панели конфорок: серый">
                                <label class="custom-control-label" for="серый">Серый</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-" id="черный" value="Цвет панели конфорок: черный">
                                <label class="custom-control-label" for="черный">Черный</label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <button class="btn  btn-block filter-button" type="button" data-toggle="collapse" data-target="#group10" aria-expanded="false">
            <span>Особенности</span>
        </button>
        <div class="collapse" id="group10">
            <div class="card card-body filter-filed">
                <div class="md-form input-group">
                    <div class="container">
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-MultiParams-ВстрПовр" id="Индукционная" value="Индукционная: да">
                                <label class="custom-control-label" for="Индукционная">Индукционная</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-MultiParams-ВстрПовр" id="Газ-контроль конфорок" value="Газ-контроль конфорок: есть">
                                <label class="custom-control-label" for="Газ-контроль конфорок">Газ-контроль конфорок</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-MultiParams-ВстрПовр" id="Таймер конфорок" value="Таймер конфорок: есть">
                                <label class="custom-control-label" for="Таймер конфорок">Таймер конфорок</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-MultiParams-ВстрПовр" id="Автоматика закипания" value="Автоматика закипания: есть">
                                <label class="custom-control-label" for="Автоматика закипания">Автоматика закипания</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-MultiParams-ВстрПовр" id="Конфорка WOK" value="Конфорка WOK: есть">
                                <label class="custom-control-label" for="Конфорка WOK">Конфорка WOK</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="Cont-MultiParams-ВстрПовр" id="Индикатор остаточного тепла" value="Индикатор остаточного тепла: есть">
                                <label class="custom-control-label" for="Индикатор остаточного тепла">Индикатор остаточного тепла</label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</#if>


<button type="submit" id="filter-button" class="btn btn-primary btn-block filter-button search">Показать</button>