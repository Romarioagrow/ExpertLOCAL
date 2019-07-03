<div>
    <button class="btn btn-secondary btn-block filter-button"
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
                        <input type="text" aria-label="First name" id="sortmin" name="sortmin" class="form-control" placeholder="От ₽">
                    </div>
                    <div class="col-5">
                        <input type="text" aria-label="Last name" id="sortmax" name="sortmax" class="form-control" placeholder="До ₽">
                    </div>
                </div>
            </div>
            <div class="md-form input-group">
                <div class="input-group-prepend">
                    <span class="input-group-text md-addon">Бренды</span>
                </div>
                <div class="form-check">
                    <div class="container">
                        <#include "../parts/brands.ftl">
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<#if url?contains("телевизоры")>
    <div>
        <button class="btn btn-secondary btn-block filter-button" type="button" data-toggle="collapse" data-target="#tv-type" aria-expanded="false">
            <span>Тип телевизора</span>
        </button>
        <div class="collapse" id="tv-type">
            <div class="card card-body filter-filed">
                <div class="md-form input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text md-addon">Тип экрана</span>
                    </div>
                    <div class="container">
                        <div class="row">
                            <div class="col">
                                <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="form-check-input" name="tv_type" id="ЖК" value="Тип: ЖК">
                                    <label class="custom-control-label" for="ЖК">ЖК</label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="form-check-input" name="tv_type" id="LCD" value="Тип: LCD">
                                    <label class="custom-control-label" for="LCD">LCD</label>

                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="form-check-input" name="tv_type" id="LED" value="Тип: LED">
                                    <label class="custom-control-label" for="LED">LED</label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="form-check-input" name="tv_type" id="QLED" value="Тип: QLED">
                                    <label class="custom-control-label" for="QLED">QLED</label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <button class="btn btn-secondary btn-block filter-button" type="button" data-toggle="collapse" data-target="#tv-filter1" aria-expanded="false">
            <span>Характеристики экрана</span>
        </button>
        <div class="collapse" id="tv-filter1">
            <div class="card card-body filter-filed">
                <div class="md-form input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text md-addon">Диагональ</span>
                    </div>
                    <div class="row">
                        <div class="col-5">
                            <input type="text" id="diag_min" name="diag_min" class="form-control" placeholder="От дюйм">
                        </div>
                        <div class="col-5">
                            <input type="text" id="diag_max" name="diag_max" class="form-control" placeholder="До дюйм">
                        </div>
                    </div>
                </div>
                <div class="md-form input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text md-addon">Разрешение</span>
                    </div>
                    <#--<div class="form-check">-->
                    <div class="container">
                        <div class="row">
                            <div class="col">
                                <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="form-check-input" name="tv_resolution" id="720p" value="HD Ready">
                                    <label class="custom-control-label" for="720p">720p HD</label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="form-check-input" name="tv_resolution" id="1080p" value="Full HD">
                                    <label class="custom-control-label" for="1080p">1080p Full HD</label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="form-check-input" name="tv_resolution" id="4K" value="4K UHD">
                                    <label class="custom-control-label" for="4K">4K UHD</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <#--</div>-->
                </div>
                <div class="md-form input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text md-addon">Частота обновления экрана</span>
                    </div>
                    <div class="row">
                        <div class="col-5">
                            <input type="text" id="hz_min" name="hz_min" class="form-control" placeholder="От Гц">
                        </div>
                        <div class="col-5">
                            <input type="text" id="hz_max" name="hz_max" class="form-control" placeholder="До Гц">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div>
        <button class="btn btn-secondary btn-block filter-button" type="button" data-toggle="collapse" data-target="#tv-filter2" aria-expanded="false">
            Особенности TV
        </button>
        <div class="collapse" id="tv-filter2">
            <div class="card card-body filter-filed">
                <div class="form-group">
                    <div class="custom-control custom-checkbox">
                        <input type="checkbox" class="form-check-input" name="tv_params" id="SmartTV" value="Smart TV: есть">
                        <label class="custom-control-label" for="SmartTV">SmartTV</label>
                    </div>
                    <div class="custom-control custom-checkbox">
                        <input type="checkbox" class="form-check-input" name="tv_params" id="WiFI" value="Поддержка Wi-Fi: есть">
                        <label class="custom-control-label" for="WiFI">Wi-FI</label>
                    </div>
                    <#--<div class="custom-control custom-checkbox">
                        <input type="checkbox" class="form-check-input" name="tv_params" id="3D" value="3D">
                        <label class="custom-control-label" for="3D">3D</label>
                    </div>-->
                    <div class="custom-control custom-checkbox">
                        <input type="checkbox" class="form-check-input" name="tv_params" id="DVB-T2" value="Приём DVB-T2: есть">
                        <label class="custom-control-label" for="DVB-T2">Приём DVB-T2</label>
                    </div>
                    <div class="custom-control custom-checkbox">
                        <input type="checkbox" class="form-check-input" name="tv_params" id="curved" value="Изогнутый экран: есть">
                        <label class="custom-control-label" for="curved">Изогнутый экран</label>
                    </div>
                </div>
            </div>
        </div>
    </div>
</#if>

<#if url?contains("кабели_тв")>
    <div>
        <button class="btn btn-secondary btn-block filter-button" type="button" data-toggle="collapse" data-target="#tv-cables1" aria-expanded="false">
            <span>Тип кабеля</span>
        </button>
        <div class="collapse" id="tv-cables1">
            <div class="card card-body filter-filed">
                <div class="form-group">
                    <div class="custom-control custom-checkbox">
                        <input type="checkbox" class="form-check-input mb-1" name="tv-cables-type" id="Кабель" value="Тип: кабель">
                        <label class="custom-control-label" for="Кабель">Кабель</label>
                    </div>
                    <div class="custom-control custom-checkbox">
                        <input type="checkbox" class="form-check-input mb-1" name="tv-cables-type" id="Переходник" value="Тип: переходник">
                        <label class="custom-control-label" for="Переходник">Переходник</label>
                    </div>
                    <div class="custom-control custom-checkbox">
                        <input type="checkbox" class="form-check-input mb-1" name="tv-cables-type" id="Удлинитель" value="Тип: удлинитель">
                        <label class="custom-control-label" for="Удлинитель">Удлинитель</label>
                    </div>
                </div>
            </div>
        </div>

        <button class="btn btn-secondary btn-block filter-button" type="button" data-toggle="collapse" data-target="#tv-cables2" aria-expanded="false">
            <span>Разъемы</span>
        </button>
        <div class="collapse" id="tv-cables2">
            <div class="card card-body filter-filed">
                <div class="form-group">
                    <div class="custom-control custom-checkbox">
                        <input type="checkbox" class="form-check-input mb-1" name="tv-cables" id="HDMI" value="HDMI - HDMI">
                        <label class="custom-control-label" for="HDMI">HDMI</label>
                    </div>
                    <div class="custom-control custom-checkbox">
                        <input type="checkbox" class="form-check-input mb-1" name="tv-cables" id="Jack 3.5 (m)" value="Jack 3.5 (m) - Jack 3.5 (m)">
                        <label class="custom-control-label" for="Jack 3.5 (m)">AUX</label>
                    </div>
                    <div class="custom-control custom-checkbox">
                        <input type="checkbox" class="form-check-input mb-1" name="tv-cables" id="Jack 3.5" value="Jack 3.5 (m) - Jack 3.5 (f)">
                        <label class="custom-control-label" for="Jack 3.5">Jack 3.5 (m) - Jack 3.5 (f)</label>
                    </div>
                    <div class="custom-control custom-checkbox">
                        <input type="checkbox" class="form-check-input mb-1" name="tv-cables" id="Coax" value="Coax - Coax">
                        <label class="custom-control-label" for="Coax">Coax - Coax</label>
                    </div>
                    <div class="custom-control custom-checkbox">
                        <input type="checkbox" class="form-check-input mb-1" name="tv-cables" id="SCART" value="SCART - SCART">
                        <label class="custom-control-label" for="SCART">SCART - SCART</label>
                    </div>
                    <div class="custom-control custom-checkbox">
                        <input type="checkbox" class="form-check-input mb-1" name="tv-cables" id="SCART - 3xRCA" value="SCART - 3xRCA">
                        <label class="custom-control-label" for="SCART - 3xRCA">SCART - 3xRCA</label>
                    </div>
                    <div class="custom-control custom-checkbox">
                        <input type="checkbox" class="form-check-input mb-1" name="tv-cables" id="Coax - F-штекер" value="Coax - F-штекер">
                        <label class="custom-control-label" for="Coax - F-штекер">Coax - F-штекер</label>
                    </div>
                    <div class="custom-control custom-checkbox">
                        <input type="checkbox" class="form-check-input mb-1" name="tv-cables" id="3xRCA - Jack 3.5" value="3xRCA - Jack 3.5">
                        <label class="custom-control-label" for="3xRCA - Jack 3.5">3xRCA - Jack 3.5</label>
                    </div>
                    <div class="custom-control custom-checkbox">
                        <input type="checkbox" class="form-check-input mb-1" name="tv-cables" id="2xJack 3.5 (f) - Jack 3.5 (m)" value="2xJack 3.5 (f) - Jack 3.5 (m)">
                        <label class="custom-control-label" for="2xJack 3.5 (f) - Jack 3.5 (m)">2xJack 3.5 (f) - Jack 3.5 (m)</label>
                    </div>
                    <div class="custom-control custom-checkbox">
                        <input type="checkbox" class="form-check-input mb-1" name="tv-cables" id="ODT Toslink - ODT Toslink" value="ODT Toslink - ODT Toslink">
                        <label class="custom-control-label" for="ODT Toslink - ODT Toslink">ODT Toslink - ODT Toslink</label>
                    </div>
                </div>
            </div>
        </div>

        <button class="btn btn-secondary btn-block filter-button" type="button" data-toggle="collapse" data-target="#tv-cables3" aria-expanded="false">
            <span>Длинна кабеля</span>
        </button>
        <div class="collapse" id="tv-cables3">
            <div class="card card-body filter-filed">
                <div class="form-group">
                    <div class="custom-control custom-checkbox">
                        <input type="checkbox" class="form-check-input mb-1" name="tv-cables-length" id="1 Метр" value="Длина кабеля: 1;">
                        <label class="custom-control-label" for="1 Метр">1 метр</label>
                    </div>
                    <div class="custom-control custom-checkbox">
                        <input type="checkbox" class="form-check-input mb-1" name="tv-cables-length" id="1.5 Метра" value="Длина кабеля: 1,5;">
                        <label class="custom-control-label" for="1.5 Метра">1.5 метра</label>
                    </div>
                    <div class="custom-control custom-checkbox">
                        <input type="checkbox" class="form-check-input mb-1" name="tv-cables-length" id="1.8 Метра" value="Длина кабеля: 1,8;">
                        <label class="custom-control-label" for="1.8 Метра">1.8 метра</label>
                    </div>
                    <div class="custom-control custom-checkbox">
                        <input type="checkbox" class="form-check-input mb-1" name="tv-cables-length" id="2 Метра" value="Длина кабеля: 2;">
                        <label class="custom-control-label" for="2 Метра">2 метра</label>
                    </div>
                    <div class="custom-control custom-checkbox">
                        <input type="checkbox" class="form-check-input mb-1" name="tv-cables-length" id="3 Метра" value="Длина кабеля: 3;">
                        <label class="custom-control-label" for="3 Метра">3 метра</label>
                    </div>
                    <div class="custom-control custom-checkbox">
                        <input type="checkbox" class="form-check-input mb-1" name="tv-cables-length" id="5 Метра" value="Длина кабеля: 5;">
                        <label class="custom-control-label" for="5 Метра">5 метров</label>
                    </div>
                </div>
            </div>
        </div>
    </div>
</#if>

<#if url?contains("кронштейны_тв")>
    <div>
        <button class="btn btn-secondary btn-block filter-button" type="button" data-toggle="collapse" data-target="#tv-brackets1" aria-expanded="false">
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
                                    <input type="checkbox" class="form-check-input" name="tv-brackets-type" id="Наклонно-поворотный" value="Тип кронштейна: наклонно-поворотный">
                                    <label class="custom-control-label" for="Наклонно-поворотный">Наклонно-поворотный</label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="form-check-input" name="tv-brackets-type" id="Наклонный" value="Тип кронштейна: наклонный">
                                    <label class="custom-control-label" for="Наклонный">Наклонный</label>

                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="form-check-input" name="tv-brackets-type" id="Поворотный" value="Тип кронштейна: поворотный">
                                    <label class="custom-control-label" for="Поворотный">Поворотный</label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="form-check-input" name="tv-brackets-type" id="Фиксированный" value="Тип кронштейна: фиксированный">
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
                                    <input type="checkbox" class="form-check-input" name="tv-brackets-mount" id="Настенный" value="Способ крепления: настенный">
                                    <label class="custom-control-label" for="Настенный">Настенный</label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="form-check-input" name="tv-brackets-mount" id="Настольный" value="Способ крепления: настольный">
                                    <label class="custom-control-label" for="Настольный">Настольный</label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <button class="btn btn-secondary btn-block filter-button" type="button" data-toggle="collapse" data-target="#tv-brackets2" aria-expanded="false">
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
                            <input type="text" id="tv-brackets-load_min" name="tv-brackets-load_min" class="form-control" placeholder="От кг">
                        </div>
                        <div class="col-5">
                            <input type="text" id="tv-brackets-load_max" name="tv-brackets-load_max" class="form-control" placeholder="До кг">
                        </div>
                    </div>
                </div>
                <div class="md-form input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text md-addon">Максимальная диагональ</span>
                    </div>
                    <div class="row">
                        <div class="col-5">
                            <input type="text" id="tv-brackets-diag_min" name="tv-brackets-diag_min" class="form-control" placeholder="От дюйм">
                        </div>
                        <div class="col-5">
                            <input type="text" id="tv-brackets-diag_max" name="tv-brackets-diag_max" class="form-control" placeholder="До дюйм">
                        </div>
                    </div>
                </div>
                <div class="md-form input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text md-addon">Расстояние от стены</span>
                    </div>
                    <div class="row">
                        <div class="col-5">
                            <input type="text" id="tvBracketsWallDistMin" name="tvBracketsWallDistMin" class="form-control" placeholder="От мм">
                        </div>
                        <div class="col-5">
                            <input type="text" id="tvBracketsWallDistMax" name="tvBracketsWallDistMax" class="form-control" placeholder="До мм">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</#if>

<#if url?contains("музыкальные_центры")>
    <div>
        <button class="btn btn-secondary btn-block filter-button" type="button" data-toggle="collapse" data-target="#tv-brackets1" aria-expanded="false">
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
                                    <input type="checkbox" class="form-check-input" name="muzCenterType" id="мидисистема" value="Тип: мидисистема">
                                    <label class="custom-control-label" for="мидисистема">Миди-система</label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="form-check-input" name="muzCenterType" id="микросистема" value="Тип: микросистема">
                                    <label class="custom-control-label" for="микросистема">Микро-система</label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="form-check-input" name="muzCenterType" id="минисистема" value="Тип: минисистема">
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
                                    <input type="checkbox" class="form-check-input" name="muzCenterMainBlock" id="компонентная система" value="Основной блок: компонентная система">
                                    <label class="custom-control-label" for="компонентная система">Компонентная система</label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="form-check-input" name="muzCenterMainBlock" id="моноблок" value="Основной блок: моноблок">
                                    <label class="custom-control-label" for="моноблок">Моноблок</label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="form-check-input" name="muzCenterMainBlock" id="одноблочная система" value="Основной блок: одноблочная система">
                                    <label class="custom-control-label" for="одноблочная система">Одноблочная система</label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <button class="btn btn-secondary btn-block filter-button" type="button" data-toggle="collapse" data-target="#tv-brackets2" aria-expanded="false">
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
                            <input type="text" id="muzCenterPowerMin" class="form-control" placeholder="От Ватт">
                        </div>
                        <div class="col-5">
                            <input type="text" id="muzCenterPowerMax" class="form-control" placeholder="До Ватт">
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
                                <input type="checkbox" class="form-check-input" name="muzCenterAcoustic" id="аккустика1.0" value="Комплект акустических систем: 1.0">
                                <label class="custom-control-label" for="аккустика1.0">1.0</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="muzCenterAcoustic" id="аккустика2.0" value="Комплект акустических систем: 2.0">
                                <label class="custom-control-label" for="аккустика2.0">2.0</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="muzCenterAcoustic" id="аккустика2.1" value="Комплект акустических систем: 2.1">
                                <label class="custom-control-label" for="аккустика2.1">2.1</label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <button class="btn btn-secondary btn-block filter-button" type="button" data-toggle="collapse" data-target="#tv-brackets3" aria-expanded="false">
            <span>Особенности музыкульного центра</span>
        </button>
        <div class="collapse" id="tv-brackets3">
            <div class="card card-body filter-filed">
                <div class="md-form input-group">
                    <div class="container">
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="muzCenterParams" id="Эквалайзер" value="Эквалайзер: есть">
                                <label class="custom-control-label" for="Эквалайзер">Эквалайзер</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="muzCenterParams" id="Чтение с USB" value="Воспроизведение с USB-накопителей: есть">
                                <label class="custom-control-label" for="Чтение с USB">Чтение с USB</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="muzCenterParams" id="Поддержка Wi-Fi" value="Wi-Fi: есть">
                                <label class="custom-control-label" for="Поддержка Wi-Fi">Поддержка Wi-Fi</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="muzCenterParams" id="Наличие Bluetooth" value="Bluetooth: есть">
                                <label class="custom-control-label" for="Наличие Bluetooth">Наличие Bluetooth</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="muzCenterParams" id="Караоке" value="Караоке: есть">
                                <label class="custom-control-label" for="Караоке">Караоке</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="muzCenterParams" id="Часы" value="Часы: есть">
                                <label class="custom-control-label" for="Часы">Часы</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="muzCenterParams" id="Таймер" value="Таймер: есть">
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
        <button class="btn btn-secondary btn-block filter-button" type="button" data-toggle="collapse" data-target="#tv-mebel1" aria-expanded="false">
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
                                    <input type="checkbox" class="form-check-input" name="tvMebelType" id="С подвесом" value="Тип: тумба с подвесом">
                                    <label class="custom-control-label" for="С подвесом">Тумба с подвесом</label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="form-check-input" name="tvMebelType" id="Тумба без подвеса" value="Тип: тумба без подвеса">
                                    <label class="custom-control-label" for="Тумба без подвеса">Тумба без подвеса</label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <button class="btn btn-secondary btn-block filter-button" type="button" data-toggle="collapse" data-target="#tv-mebel2" aria-expanded="false">
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
                                        <input type="checkbox" class="form-check-input" name="tvMebelWidth" id="80 см" value="Ширина: 80">
                                        <label class="custom-control-label" for="80 см">80 см</label>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col">
                                    <div class="custom-control custom-checkbox">
                                        <input type="checkbox" class="form-check-input" name="tvMebelWidth" id="90 см" value="Ширина: 89,4">
                                        <label class="custom-control-label" for="90 см">90 см</label>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col">
                                    <div class="custom-control custom-checkbox">
                                        <input type="checkbox" class="form-check-input" name="tvMebelWidth" id="110 см" value="Ширина: 110">
                                        <label class="custom-control-label" for="110 см">110 см</label>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col">
                                    <div class="custom-control custom-checkbox">
                                        <input type="checkbox" class="form-check-input" name="tvMebelWidth" id="150 см" value="Ширина: 150">
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
                                        <input type="checkbox" class="form-check-input" name="tvMebelLoad" id="До 30 кг" value="Нагрузка: 30">
                                        <label class="custom-control-label" for="До 30 кг">До 30 кг</label>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col">
                                    <div class="custom-control custom-checkbox">
                                        <input type="checkbox" class="form-check-input" name="tvMebelLoad" id="До 40 кг" value="Нагрузка: 40">
                                        <label class="custom-control-label" for="До 40 кг">До 40 кг</label>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col">
                                    <div class="custom-control custom-checkbox">
                                        <input type="checkbox" class="form-check-input" name="tvMebelLoad" id="До 50 кг" value="Нагрузка: 50">
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
                                <input type="text" id="tvMebelDiagMin" class="form-control" placeholder="От дюйм">
                            </div>
                            <div class="col-5">
                                <input type="text" id="tvMebelDiagMax" class="form-control" placeholder="До дюйм">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</#if>


<button type="submit" id="filter-button" class="btn btn-primary btn-block filter-button search">Показать</button>