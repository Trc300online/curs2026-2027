function pintarMa(ma) {
    const div = document.getElementById("app")

    for (const carta of ma) {
        const imgName = cardMapToImage(carta);

        const imatge = document.createElement("img");
        imatge.src = "../assets/cards/" + imgName;
        imatge.width = 150;
        div.append(imatge);
    }
}

function cardMapToImage(carta) {
    let nom = '';
    switch (carta.nombre) {
        case 'j': nom += 'jack'; break;
        case 'k': nom += 'king'; break;
        case 'q': nom += 'queen'; break;
        case 'as': nom += 'ace'; break;
        default: nom += carta.nombre
    }

    nom += "_of_";

    switch (carta.pal) {
        case "cor": nom += "hearts"; break;
        case "pica": nom += "spades"; break;
        case "diamants": nom += "diamonds"; break;
        case "trebol": nom += "clubs"; break;
    }

    nom += ".png";

    console.log("nom de la imatge", nom)

    return nom;
}

function pintarBotoPlay(comprovarFn) {
    const div = document.getElementById("app");
    const boto = document.createElement("button");
    boto.innerHTML = "comprovar";
    boto.addEventListener('click', comprovarFn)

        /*
        boto.addEventListener('click', function () {
            console.log("playing")
        });
        */

    div.append(boto);
}

function anunciarResultat(comprovarMaFn) {
    const titol  = document.createElement("h1");
    const div = document.getElementById("app");

    if (comprovarMaFn) {
        titol.innerHTML = "Has guanyat!!!!!!!!"
    } else {
        titol.innerHTML = "Has perdut..."
    }

    div.append(titol);
}