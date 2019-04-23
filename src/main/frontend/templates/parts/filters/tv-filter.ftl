<div>
    <button class="btn btn-secondary btn-block filter-button" type="button" data-toggle="collapse" data-target="#tv-filter1" aria-expanded="false">
        <span>Характеристики экрана</span>
    </button>
    <div class="collapse" id="tv-filter1">
        <div class="card card-body filter-filed">
            <div>
                <h5>Диагональ</h5>
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
                    <input type="checkbox" class="custom-control-input" name="tv_resolution" id="720p" value="720">
                    <label class="custom-control-label" for="720p">720p HD</label>
                </div>
                <div class="custom-control custom-checkbox">
                    <input type="checkbox" class="custom-control-input" name="tv_resolution" id="1080p" value="1080">
                    <label class="custom-control-label" for="1080p">1080p Full HD</label>
                </div>
                <div class="custom-control custom-checkbox">
                    <input type="checkbox" class="custom-control-input" name="tv_resolution" id="4K" value="4K">
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
                        <input type="checkbox" class="custom-control-input" name="tv_params" id="SmartTV" value="SmartTV">
                        <label class="custom-control-label" for="SmartTV">SmartTV</label>
                    </div>
                    <div class="custom-control custom-checkbox">
                        <input type="checkbox" class="custom-control-input" name="tv_params" id="WiFI" value="Wi-Fi">
                        <label class="custom-control-label" for="WiFI">Wi-FI</label>
                    </div>
                    <div class="custom-control custom-checkbox">
                        <input type="checkbox" class="custom-control-input" name="tv_params" id="3D" value="3D">
                        <label class="custom-control-label" for="3D">3D</label>
                    </div>
                    <div class="custom-control custom-checkbox">
                        <input type="checkbox" class="custom-control-input" name="tv_params" id="curved" value="Curved">
                        <label class="custom-control-label" for="curved">Изогнутый экран</label>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>