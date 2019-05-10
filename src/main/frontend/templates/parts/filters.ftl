<div>
    <button class="btn btn-secondary btn-block filter-button"
            type="button" data-toggle="collapse" data-target="#filter1" aria-expanded="false"><span>Цена и бренд</span>
    </button>
    <div class="collapse" id="filter1">
        <div class="card card-body filter-filed">
            <h5>Цена</h5>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text">От</span>
                </div>
                <input type="text" id="sortmin" name="sortmin" placeholder="₽" class="form-control" aria-label="Amount (to the nearest dollar)">
            </div>
            <div class="input-group">
                <input type="text" id="sortmax" name="sortmax" placeholder="₽" class="form-control" aria-label="Amount (to the nearest dollar)">
                <div class="input-group-append">
                    <span class="input-group-text">До</span>
                </div>
            </div>
            <h5 class="mt-2">Бренд</h5>
            <div class="col-md-12" style="margin-top: -15px !important;">
                <select class="mdb-select colorful-select dropdown-primary md-form"  multiple>
                    <#if url?contains("tv")>
                        <option value="" disabled selected>Выберите бренд</option>
                        <option name="brand" value="DOFFLER">DOFFLER</option>
                        <option name="brand" value="HARPER">HARPER</option>
                        <option name="brand" value="LG">LG</option>
                        <option name="brand" value="ORION">ORION</option>
                        <option name="brand" value="SAMSUNG">SAMSUNG</option>
                        <option name="brand" value="TOSHIBA">TOSHIBA</option>
                        <option name="brand" value="AMCV">AMCV</option>
                        <option name="brand" value="BBK">BBK</option>
                        <option name="brand" value="AKAI">AKAI</option>
                    </#if>
                    <#if url?contains("stoves")>
                        <option name="brand" value="brand1">GRETA</option>
                        <option name="brand" value="brand2">DARINA</option>
                    </#if>
                </select>
            </div>
        </div>
    </div>
</div>

<#if url?contains("tv")>
    <div>
        <button class="btn btn-secondary btn-block filter-button" type="button" data-toggle="collapse" data-target="#tv-filter1" aria-expanded="false">
            <span>Характеристики экрана</span>
        </button>
        <div class="collapse" id="tv-filter1">
            <div class="card card-body filter-filed">
                <div>
                    <h5 style="margin-bottom: -15px !important;">Диагональ</h5>
                    <div class="input-group mb-1">
                        <br><input type="text" id="diag_min" name="diag_min" placeholder="Не меньше" class="form-control mt-2" style="width: auto">
                    </div>
                    <div class="input-group">
                        <input type="text" id="diag_max" name="diag_max" placeholder="Не больше" class="form-control">
                        <div class="input-group-append">
                            <span class="input-group-text">Дюйм</span>
                        </div>
                    </div>
                </div>
                <div class="mt-2">
                    <h5>Разрешение экрана</h5>
                    <div class="custom-control custom-checkbox">
                        <input type="checkbox" class="form-check-input" name="tv_resolution" id="720p" value="720p HD">
                        <label class="custom-control-label" for="720p">720p HD</label>
                    </div>
                    <div class="custom-control custom-checkbox">
                        <input type="checkbox" class="form-check-input" name="tv_resolution" id="1080p" value="1080p FullHD">
                        <label class="custom-control-label" for="1080p">1080p Full HD</label>
                    </div>
                    <div class="custom-control custom-checkbox">
                        <input type="checkbox" class="form-check-input" name="tv_resolution" id="4K" value="4K UHD">
                        <label class="custom-control-label" for="4K">4K UHD</label>
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
                            <input type="checkbox" class="form-check-input" name="tv_params" id="WiFI" value="Wi-Fi">
                            <label class="custom-control-label" for="WiFI">Wi-FI</label>
                        </div>
                        <div class="custom-control custom-checkbox">
                            <input type="checkbox" class="form-check-input" name="tv_params" id="3D" value="3D">
                            <label class="custom-control-label" for="3D">3D</label>
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