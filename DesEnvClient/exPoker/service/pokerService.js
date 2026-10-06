function initBaralla() {
    const numeros = ["2", "3", "4", "5", "6", "7", "8", "9", "10", "as", "j", "k", "q"]
    const palos = ["pica", "trebol", "diamants", "cor"];

    const baralla = [];

    for (let a = 0; a < numeros.length; a++) {
        for (let b = 0; b < palos.length; b++) {
            baralla.push(new Carta(numeros[a], palos[b]));
        }
    }

    return baralla;
}

function mesclarIRepartirBaralla(baralla) {

    const entrega = [];
    let carta;

    for (let x = 0; x < 5; x++) {
        let index = Math.floor(Math.random() * baralla.length);
        carta = baralla[index];
        baralla.splice(index, 1);
        entrega.push(carta);
    }

    return entrega;
}

function comprovarMa(ma) {
    console.log("comprovant la ma...");

    for (let x = 0; x < ma.length; x++) {
        for (let z = x + 1; z < ma.length; z++) {
            if (ma[x].nombre === ma[z].nombre) {
                return true;
            }
        }
    }
    return false;
}