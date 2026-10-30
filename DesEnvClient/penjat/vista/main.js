function init() {
    const lletres = initLletres();
    const paraula = initParaula(lletres);
    const penjat = initPenjat(paraula, lletres);
    pintarPenjat(penjat);
}

init()