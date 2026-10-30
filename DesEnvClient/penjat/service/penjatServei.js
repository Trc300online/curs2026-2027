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

    const paraulaAleatoria = paraulesDisponibles[2];
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

function jugar(lletra) {
    lletra.estat = ''
    console.log(lletra);
}