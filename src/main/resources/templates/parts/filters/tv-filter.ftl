<div>
    <button class="btn btn-secondary btn-block filter-button" type="button" data-toggle="collapse" data-target="#tv-filter1" aria-expanded="false">
        <span>Особенности экрана</span>
    </button>
    <div class="collapse" id="tv-filter1">
        <div class="card card-body filter-filed">
            <div>
                <div class="input-group mb-3">
                    <h5>Диагональ</h5>
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
                    <input type="checkbox" class="custom-control-input" id="720p">
                    <label class="custom-control-label" for="720p">720p HD</label>
                </div>
                <div class="custom-control custom-checkbox">
                    <input type="checkbox" class="custom-control-input" id="1080p">
                    <label class="custom-control-label" for="1080p">1080p Full HD</label>
                </div>
                <div class="custom-control custom-checkbox">
                    <input type="checkbox" class="custom-control-input" id="4K">
                    <label class="custom-control-label" for="4K">4K UHD</label>
                </div>
            </div>
            <div class="mt-2">
                <h5>Особенности</h5>
                <div class="custom-control custom-checkbox">
                    <input type="checkbox" class="custom-control-input" id="3D">
                    <label class="custom-control-label" for="3D">3D</label>
                </div>
                <div class="custom-control custom-checkbox">
                    <input type="checkbox" class="custom-control-input" id="curved">
                    <label class="custom-control-label" for="curved">Изогнутый экран</label>
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
                        <input type="checkbox" class="custom-control-input" id="SmartTV">
                        <label class="custom-control-label" for="SmartTV">SmartTV</label>
                    </div>
                    <div class="custom-control custom-checkbox">
                        <input type="checkbox" class="custom-control-input" id="WiFI">
                        <label class="custom-control-label" for="WiFI">Wi-FI</label>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>