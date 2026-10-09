console.log("main init");


function init() {
    const baralla = initBaralla();
    //console.log(baralla);

    const ma = mesclarIRepartirBaralla(baralla);
    //console.log(baralla);

    pintarMa(ma);
    pintarBotoPlay(function () {
        //pintar amb DOM (ventana emergente)
        const isBOM = true;
        const isWindow = true;
        anunciarResultat(comprovarMa(ma), isBOM, isWindow);
        //pintar amb BOM (use alert)
    });
}

init();