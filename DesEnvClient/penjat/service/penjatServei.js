function initLletres() {
    const abecedari = "abcdefghijklmnopqrstuvwxyz";
    const lletres = [];
    for (const lletra of abecedari) {
        lletres.push(new Lletra(lletra, 'DESCONEGUT'));
    }

    return lletres;
}

function initParaula(lletres) {
    const paraulesDisponibles = ["windows", "macosx", "linux", "java", "informatica", "programacio"]

    const paraulaAleatoria = paraulesDisponibles[0].split("");
    const resultat = [];

    for (const lletraParaula of paraulaAleatoria) {
        for (const lletra of lletres) {
            if (lletraParaula === lletra.valor) {
                resultat.push(lletra);
            }
        }
    }

    return resultat;
}

function initPenjat(paraula, lletres) {
    return new Penjat(paraula, lletres);
}

let errorCount = 1;

function jugar(lletra, paraula) {

    for (const l of paraula) {
        if (l === lletra) {
            lletra.estat = 'ACERT';
        }
    }

    if (lletra.estat !== 'ACERT') {
        lletra.estat = 'ERROR';
        errorCount++;
    }
}

function getErrorCount() {
    return errorCount;
}

function comprovarVictoria(penjat) {
    return paraulaCompleta(penjat);
}

function paraulaCompleta(penjat) {
    let lletraCounter = 0;

    for (const lletra of penjat.paraula) {

        if (lletra.estat === "ACERT") {
            lletraCounter++;
        }
    }
    console.log(lletraCounter);
console.log(penjat.paraula.length);
    return lletraCounter === penjat.paraula.length;
}