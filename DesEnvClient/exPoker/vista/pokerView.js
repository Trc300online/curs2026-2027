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

function anunciarResultat(comprovarMaFn, isBOM, isWindow) {
/*
    if (isBOM && isWindow){

        myWindow = window.open("", "", "width=500,height=500");

        const h1 = myWindow.document.createElement("h1");
        if (comprovarMaFn) {
            h1.innerHTML = "Has guanyat!!!!!!!!"
        } else {
            h1.innerHTML = "Has perdut..."
        }
        myWindow.document.body.append(h1);

    } else if (isBOM) {

        if (comprovarMaFn) {
            alert("Has guanyat!!!!!!!!");
        } else {
            alert("Has perdut...");
        }

    } if (!isBOM && !isWindow) {
*/
        const missatge = document.createElement("p");
        missatge.innerText = (comprovarMaFn) ? "Has guanyat!!!" : "Has perdut...";
        missatge.style.color = "red";
        missatge.style.fontSize = "50px";

        const finestra = document.createElement("div");
        finestra.style.backgroundColor = "gray";
        finestra.style.width = "400px";
        finestra.style.height = "300px";
        finestra.style.borderColor = "blue";
        finestra.style.position = "relative";
        finestra.appendChild(missatge);

        document.querySelector("#app").appendChild(finestra);

        const botoTancar = document.createElement("div");
        botoTancar.innerText = "X";
        botoTancar.style.backgroundColor = "red";
        botoTancar.style.fontSize = "50px";
        botoTancar.style.width = "50px";
        botoTancar.style.height = "50px";
        botoTancar.style.position = "absolute";
        botoTancar.style.top = "0px";
        botoTancar.style.right = "0px";
        botoTancar.addEventListener("click", function () {
            /*console.log("Click a tancar finestra");
            finestra.style.display = "none";*/
            location.reload();
        } );

        finestra.appendChild(botoTancar);

   // }
}
