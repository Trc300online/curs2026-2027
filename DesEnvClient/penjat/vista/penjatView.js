function pintarPenjat(penjat) {
    const paraula = penjat.paraula;
    pintarImatge();
    pintarParaula(paraula);
    pintarLletres(penjat);

    console.log(penjat.lletres);
}

function pintarImatge() {
    const imatge = document.createElement("img");
    imatge.src= 'assets/1.PNG';

    document.querySelector('#app').appendChild(imatge);
}

function pintarParaula(paraula) {
    const paraulaSlot = document.createElement("div");

    document.querySelector('#app').appendChild(paraulaSlot);

    for (const lletra in paraula) {
        const l = document.createElement("span");
        if (lletra.estat === 'DESCONEGUT' || lletra.estat === 'ERROR') {
            l.innerText = ' _ ';
        } else {
            l.innerText = ` ${lletra.valor} `;
        }

        paraulaSlot.appendChild(l);
    }
}

function pintarLletres(penjat) {
    const lletres = penjat.lletres;
    const lletresSlot = document.createElement("div");


    for (const lletra in lletres) {
        const boto = document.createElement("button");
        boto.innerText = lletra.valor;

        if (lletra.estat === "DESCONEGUT") {
            boto.style.backgroundColor = 'blue';
        }

        boto.addEventListener('click', function () {
            jugar(lletra);
            pintarParaula(penjat.paraula);
        })

        lletresSlot.appendChild(boto);
    }

    document.querySelector('#app').appendChild(lletresSlot);

}