function Cronometro(/*hora, minut, segon, milisegon*/) {
    const dataInicial = Date.now();
    this.hora = function () {
        const ara= new Date();

        const diffTime = ara - dataInicial;
        return diffTime /
    };

    /* this.hora = hora;
    this.minut = minut;
    this.segon = segon;
    this.milisegon = milisegon;*/
}