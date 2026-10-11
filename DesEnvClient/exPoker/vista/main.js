console.log("main init");


function init() {
    const baralla = initBaralla();
    //console.log(baralla);

    const ma = mesclarIRepartirBaralla(baralla);
    //console.log(baralla);

    pintarMa(ma);
    pintarBotoComprovar(function () {
        //pintar amb DOM (ventana emergente)
        const isBOM = true;
        const isWindow = true;
        anunciarResultat(comprovarMa(ma), isBOM, isWindow);
        //pintar amb BOM (use alert)
    });
    pintarBotoPlay(function () {
        let cheat = prompt("vols fer trampes?").toLowerCase();

        if (cheat === "si") {
            alert("Ah... com que ets un trampós? Anem a jugar facil.")
        } else {
            alert("Ets molt honrat, però segur que perdràs!")
        }

        generarJoc(ma)

    });
}

init();