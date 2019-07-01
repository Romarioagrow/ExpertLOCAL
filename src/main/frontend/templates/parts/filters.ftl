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
                    <div>
                        <div class="custom-control custom-checkbox">
                            <input type="checkbox" class="form-check-input" name="tv_params" id="SmartTV" value="SmartTV">
                            <label class="custom-control-label" for="SmartTV">SmartTV</label>
                        </div>
                        <div class="custom-control custom-checkbox">
                            <input type="checkbox" class="form-check-input" name="tv_params" id="WiFI" value="Поддержка Wi-Fi: есть">
                            <label class="custom-control-label" for="WiFI">Wi-FI</label>
                        </div>
                        <div class="custom-control custom-checkbox">
                            <input type="checkbox" class="form-check-input" name="tv_params" id="3D" value="3D">
                            <label class="custom-control-label" for="3D">3D</label>
                        </div>
                        <div class="custom-control custom-checkbox">
                            <input type="checkbox" class="form-check-input" name="tv_params" id="DVB-T2" value="Приём DVB-T2: есть">
                            <label class="custom-control-label" for="3D">Приём DVB-T2</label>
                        </div>
                        <div class="custom-control custom-checkbox">
                            <input type="checkbox" class="form-check-input" name="tv_params" id="curved" value="Curved">
                            <label class="custom-control-label" for="curved">Изогнутый экран</label>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</#if>

<#if url?contains("fridges")>
    <div>
        <button class="btn btn-secondary btn-block filter-button" type="button" data-toggle="collapse" data-target="#tv-filter2" aria-expanded="false">
            <span>Количество камер</span>
        </button>
        <div class="collapse" id="tv-filter2">
            <div class="card card-body filter-filed">
                <div class="form-group">
                    <div>
                        <div class="custom-control custom-checkbox">
                            <input type="checkbox" class="custom-control-input" name="sort_options" id="SmartTV">
                            <label class="custom-control-label" for="SmartTV">Однокамерные</label>
                        </div>
                        <div class="custom-control custom-checkbox">
                            <input type="checkbox" class="custom-control-input" name="sort_options" id="WiFI">
                            <label class="custom-control-label" for="WiFI">Двухкамерные</label>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</#if>

<#if url?contains("stoves")>
    <div>
        <button class="btn btn-secondary btn-block filter-button" type="button" data-toggle="collapse" data-target="#tv-filter1" aria-expanded="false">
            <span>Габариты плиты</span>
        </button>
        <div class="collapse" id="tv-filter1">
            <div class="card card-body filter-filed">
                <div class="mt-2">
                    <h5>Ширина плиты</h5>
                    <div class="custom-control custom-checkbox">
                        <input type="checkbox" class="custom-control-input" name="stove_width" id="stove_width" value="Ширина до 50см">
                        <label class="custom-control-label" for="stove_width">Ширина до 50см</label>
                    </div>
                </div>
            </div>
        </div>
    </div>
</#if>

<button type="submit" id="filter-button" class="btn btn-primary btn-block filter-button search">Показать</button>