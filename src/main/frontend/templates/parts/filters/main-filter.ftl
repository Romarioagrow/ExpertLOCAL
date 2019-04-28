<div>
    <button class="btn btn-secondary btn-block filter-button"
            type="button" data-toggle="collapse" data-target="#filter1" aria-expanded="false"><span>Цена</span>
    </button>
    <div class="collapse" id="filter1">
        <div class="card card-body filter-filed">
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
        </div>
    </div>
</div>
<div>
    <button class="btn btn-secondary btn-block filter-button"
            type="button" data-toggle="collapse" data-target="#manufacturer-filter" aria-expanded="true">Страна и бренд
    </button>
    <div class="collapse" id="manufacturer-filter">
        <div class="card card-body filter-filed">
            <h5>Страна</h5>
            <div class="col-md-12">
                <select class="mdb-select colorful-select dropdown-primary md-form" multiple>
                    <option value="" disabled selected>Выберите страну</option>
                    <option name="country" value="Россия">Россия</option>
                    <option name="country" value="Япония">Япония</option>
                    <option name="country" value="Южная Корея">Южная Корея</option>
                    <option name="country" value="Китай">Китай</option>
                    <option name="country" value="Тайвань">Тайвань</option>
                </select>
            </div>
            <h5>Бренд</h5>
            <div class="col-md-12"> <#--if url?contains("tv")-->
                <select class="mdb-select colorful-select dropdown-primary md-form" multiple>
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
                </select>
            </div>
        </div>
    </div>
</div>
