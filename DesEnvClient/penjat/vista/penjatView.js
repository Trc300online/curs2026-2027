function pintarPenjat(penjat) {

    pintarImatge();
    pintarParaula(penjat.paraula);
    pintarLletres(penjat);



}

function pintarImatge() {

    if (document.querySelector("#imgatge")) {

        document.querySelector("#imgatge").remove();

    }

    const imatge = document.createElement("img");
    imatge.setAttribute("id", "imgatge");

    let errorCount = getErrorCount();

    if (typeof errorCount === 'number' && errorCount >= 7 ) {
        imatge.src = `assets/7.PNG`;
    } else {
        imatge.src = `assets/${errorCount}.PNG`;
    }

    document.querySelector('#app').appendChild(imatge);
}

function pintarParaula(paraula) {

    if (document.querySelector("#paraulaSlot")) {

        document.querySelector("#paraulaSlot").remove();

    }

    const paraulaSlot = document.createElement("div");
    paraulaSlot.setAttribute("id", "paraulaSlot");

    for (const lletra of paraula) {
        const l = document.createElement("span");

        if (lletra.estat === 'DESCONEGUT' || lletra.estat === 'ERROR') {
            l.innerText = ' _ ';
        } else {
            l.innerText = ` ${lletra.valor} `;
        }

        paraulaSlot.appendChild(l);
    }
    document.querySelector('#app').appendChild(paraulaSlot);


}

function pintarLletres(penjat) {

    if (document.querySelector("#lletresSlot")) {

        document.querySelector("#lletresSlot").remove();

    }

    const lletres = penjat.lletres;
    const lletresSlot = document.createElement("div");

    lletresSlot.setAttribute("id", "lletresSlot");
    let errorCount = getErrorCount();

    for (const lletra of lletres) {
        const boto = document.createElement("button");
        boto.innerText = lletra.valor;
        boto.style.margin = "2px";
        boto.style.border = "1px solid black";

        if (typeof errorCount === 'number' && errorCount >= 7 ) {
            boto.disabled = true;
            console.log("diasbled");
        }

        if (lletra.estat === 'DESCONEGUT') {
            boto.style.backgroundColor = 'blue'
            boto.style.color = 'white'

        } else if (lletra.estat === 'ACERT') {
            boto.style.backgroundColor = 'green'
            boto.style.color = 'black'
        } else {
            boto.style.backgroundColor = 'red'
            boto.style.color = 'black'
        }

        boto.addEventListener('click', function () {
            jugar(lletra, penjat.paraula);
            pintarImatge();
            pintarParaula(penjat.paraula);
            pintarLletres(penjat);
            pintarResultat(penjat);
        })

        lletresSlot.appendChild(boto);
    }

    document.querySelector('#app').appendChild(lletresSlot);
}

function pintarResultat(penjat) {
    let msg = document.createElement("div");
    msg.style.color = "red";
    msg.style.fontWeight = "bold";
    let errorCount = getErrorCount();

    if (comprovarVictoria(penjat)) {
        msg.innerText = "Has Guanyat!!";
        document.querySelector("#app").insertBefore(msg, document.querySelector("#lletresSlot"));
    } else if (typeof errorCount === 'number' && errorCount >= 7) {
        msg.innerText = "Has Perdut!!";
        document.querySelector("#app").insertBefore(msg, document.querySelector("#lletresSlot"));
    }
}