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

function pintarBotoComprovar(comprovarFn) {
    const div = document.getElementById("app");
    const boto = document.createElement("button");
    boto.innerHTML = "comprovar";
    boto.addEventListener('click', comprovarFn)

    div.append(boto);
}

function pintarBotoPlay(generarPromptFn) {
    const div = document.getElementById("app");
    const boto = document.createElement("button");
    boto.innerHTML = "Jugar";
    boto.addEventListener('click', generarPromptFn)

    div.append(boto);
}

function pintarBotoTancarJoc(tancarFn) {
    const div = document.getElementById("app");
    const boto = document.createElement("button");

    boto.textContent = "Tancar finestra del joc";
    boto.addEventListener("click", tancarFn);

    div.append(boto);
}

function pintarBotoTancarPrograma(tancarFn) {
    const div = document.getElementById("app");
    const boto = document.createElement("button");

    boto.textContent = "Tancar tot el programa";
    boto.addEventListener("click", tancarFn);

    div.append(boto);
}


function anunciarResultat(comprovarMaFn, isBOM, isWindow, myWindow, div) {
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
          //ANUNCIAR RESULTAT NON-BOM
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
            finestra.style.display = "none";
            location.reload();
        } );

        finestra.appendChild(botoTancar);

   // }
}

function generarJoc(ma, cheat, finestraActual) {
    let myWindow = finestraActual;

    if (!myWindow || myWindow.closed) {
        myWindow = window.open(
            "",
            "",
            "width=1000,height=500,left=300,top=150"
        );
    }

    if (!myWindow) {
        alert("No s'ha pogut obrir la finestra del joc.");
        return null;
    }

    myWindow.document.body.innerHTML = "";

    const div = myWindow.document.createElement("div");
    div.id = "app";
    myWindow.document.body.append(div);

    for (const carta of ma) {
        const imgName = cardMapToImage(carta);
        const imatge = myWindow.document.createElement("img");

        imatge.src = new URL(
            "assets/cards/" + imgName,
            window.location.href
        ).href;

        imatge.width = 150;
        div.append(imatge);
    }


    const botoReload = myWindow.document.createElement("button");
    botoReload.textContent = "Tornar a jugar";

    botoReload.addEventListener("click", function () {
        const novaBaralla = initBaralla();
        const novaMa = mesclarIRepartirBaralla(novaBaralla);
        generarJoc(novaMa, cheat, myWindow);
    });

    div.appendChild(botoReload);

    const missatge = myWindow.document.createElement("h1");
    missatge.textContent = (cheat || comprovarMa(ma))
        ? "Has guanyat!!!"
        : "Has perdut...";
    missatge.style.color = "red";

    div.appendChild(missatge);


    myWindow.focus();

    return myWindow;

/*
    const myWindow = window.open("", "", "width=1000,height=500");
    let div = myWindow.document.createElement("div");
    div.innerHTML = "<div id='app'></div>";
    myWindow.document.body.append(div);

    const divApp = myWindow.document.getElementById("app")

    for (const carta of ma) {
        const imgName = cardMapToImage(carta);

        const imatge = myWindow.document.createElement("img");
        imatge.src = new URL(
            `assets/cards/${imgName}`,
            window.location.href
        ).href;
        imatge.width = 150;
        divApp.append(imatge);
    }

    const botoComprovar = myWindow.document.createElement("button");
    botoComprovar.textContent = "Comprovar";

    botoComprovar.addEventListener("click", function () {
        anunciarResultat(cheat || comprovarMa(ma), true, true, myWindow);
    });

    divApp.append(botoComprovar);

    myWindow.focus();
 */
}