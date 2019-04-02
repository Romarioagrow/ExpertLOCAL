<div>
    <button class="btn btn-secondary btn-lg btn-block filter-button"
            type="button"
            data-toggle="collapse"
            data-target="#filter1"
            aria-expanded="false">
        <span>Цена</span>
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
    <button class="btn btn-secondary btn-lg btn-block filter-button"
            type="button"
            data-toggle="collapse"
            data-target="#filter2"
            aria-expanded="true">
        Страна и бренд
    </button>
    <div class="collapse" id="filter2">
        <div class="card card-body filter-filed">
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text w-10" id="filter-country">Страна</span>
                </div>
                <input type="text" id="country" name="country" class="form-control" placeholder="Japan" aria-label="Default" aria-describedby="filter-country">
            </div>
            <div class="input-group">
                <div class="input-group-prepend">
                    <span class="input-group-text w-10" id="filter-brand">Бренд</span>
                </div>
                <input type="text" id="brand" name="brand" class="form-control" placeholder="Sony" aria-label="Default" aria-describedby="filter-brand">
            </div>
        </div>
    </div>
</div>
<style>
    .input-group-text {
        width: 5rem;
    }
</style>