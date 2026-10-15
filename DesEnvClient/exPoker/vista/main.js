console.log("main init");

let myWindow = null;

function init() {

    //pintarMa(ma);
    /*
    pintarBotoComprovar(function () {
        //pintar amb DOM (ventana emergente)
        const isBOM = true;
        const isWindow = true;
        anunciarResultat(comprovarMa(ma), isBOM, isWindow);
        //pintar amb BOM (use alert)
    });
    */
    pintarBotoPlay(function () {
        let cheat = prompt("vols fer trampes?").toLowerCase();

        if (cheat === "si") {
            alert("Ah... com que ets un trampós? Anem a jugar facil.")
        } else {
            alert("Ets molt honrat, però segur que perdràs!")
        }

        const baralla = initBaralla();

        const ma = mesclarIRepartirBaralla(baralla);

        myWindow = generarJoc(ma, cheat === "si", myWindow);

    });

    pintarBotoTancarJoc(function () {
        if (myWindow && !myWindow.closed) {
            myWindow.close();
            myWindow = null;
        }
    });

    pintarBotoTancarPrograma(function () {
        if (myWindow && !myWindow.closed) {
            myWindow.close();
        }
        window.close();
    });

}

init();