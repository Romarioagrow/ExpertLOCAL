<#import "../parts/template.ftl" as t>
<@t.template>
    <div class="container" style="margin-top: 5rem">
        <div class="row">
            <div class="col">
                <div class="card-group">
                    <div class="card ordered-card mb-4">
                        <div class="view overlay">
                            <img class="card-img-top" src="https://mdbootstrap.com/img/Photos/Others/images/49.jpg" alt="Card image cap">
                            <a href="/products/tv">
                                <div class="mask rgba-white-slight"></div>
                            </a>
                        </div>
                        <div class="card-body">
                            <h4 class="card-title">Телевизоры</h4>
                            <p><i class="fas"></i>
                                <a href="#" class="badge badge-light">4K</a>
                                <a href="#" class="badge badge-light">Full HD</a>
                                <a href="#" class="badge badge-light">SmartTV</a>
                                <a href="#" class="badge badge-light">Изогнутый экран</a>
                                <a href="#" class="badge badge-light">DVBT2</a>
                            </p>
                        </div>
                    </div>
                    <div class="card ordered-card mb-4">
                        <div class="view overlay">
                            <img class="card-img-top" src="https://mdbootstrap.com/img/Photos/Others/images/49.jpg" alt="Card image cap">
                            <a href="/subcats/multimedia">
                                <div class="mask rgba-white-slight"></div>
                            </a>
                        </div>
                        <div class="card-body">
                            <h4 class="card-title">Мультимедиа</h4>
                            <p><i class="fas"></i>
                                <a href="#" class="badge badge-light">Музыкальные центры</a>
                                <a href="#" class="badge badge-light">Домашние кинотеатры</a>
                                <a href="/products/projectors" class="badge badge-light">Проекторы</a>
                            </p>
                        </div>
                    </div>
                    <div class="card ordered-card mb-4">
                        <div class="view overlay">
                            <img class="card-img-top" src="https://mdbootstrap.com/img/Photos/Others/images/49.jpg" alt="Card image cap">
                            <a href="/subcats/sputnik">
                                <div class="mask rgba-white-slight"></div>
                            </a>
                        </div>
                        <div class="card-body">
                            <h4 class="card-title">Спутниковая техника</h4>
                            <p><i class="fas <#--fa-quote-left-->"></i>
                                <a href="#" class="badge badge-light">Ресиверы</a>
                                <a href="#" class="badge badge-light">Антенны</a>
                            </p>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</@t.template>
<style>
    .ordered-card {
        min-width: 20vw;
        max-width: 20vw;
        min-height: 30vh;
        max-height: 80vw;
    }
</style>