function Cronometro(/*hora, minut, segon, milisegon*/) {
    let dataInicial = Date.now();

    function diffTime() {
        const ara= new Date();
        return ara - dataInicial;
    }

    this.reset = function() {
        dataInicial = Date.now();
    }

    this.elapsed = function() {
        return diffTime();
    }

    this.restore = function(ms) {
        dataInicial = new Date(Date.now() - ms);
    }

    this.hora = function () {
        return Math.floor(diffTime()/(1000*60*60));
    };
    this.minut = function () {
        return Math.floor(diffTime()/(1000*60)%60);
    };
    this.segon = function () {
        return Math.floor(diffTime()/(1000)%60);
    };
    this.milisegon = function () {
        return diffTime()%1000;
    };

    /* this.hora = hora;
    this.minut = minut;
    this.segon = segon;
    this.milisegon = milisegon;*/
}