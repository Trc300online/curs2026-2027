console.log("main init");


function init() {
    const baralla = initBaralla();
    console.log(baralla);

    const ma = mesclarIRepartirBaralla(baralla);
    console.log(baralla);

    pintarMa(ma);
    pintarBotoPlay(function () {
        anunciarResultat(comprovarMa(ma));
    });
}

init();