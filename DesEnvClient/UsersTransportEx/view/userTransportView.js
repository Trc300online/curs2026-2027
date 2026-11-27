function pintarUsuaris(usuaris) {
    const app = document.getElementById("app");

    const taula = document.createElement("table");

    const capcalera = document.createElement("thead");
    const filaCapcalera = document.createElement("tr");

    const columnes = [
        "Username",
        "Nom",
        "Cognom 1",
        "Cognom 2",
        "Nom complet",
        "Inicials",
        "Transport"
    ];

    columnes.forEach(function(columna) {
        const th = document.createElement("th");
        th.textContent = columna;

        filaCapcalera.appendChild(th);
    });

    capcalera.appendChild(filaCapcalera);
    taula.appendChild(capcalera);

    const body = document.createElement("tbody");

    for (const usuari of usuaris) {
        const trFila = document.createElement("tr");

        //Username
        const tdUsername = document.createElement("td");
        tdUsername.textContent = usuari.username;
        trFila.appendChild(tdUsername);

        //Nom
        const tdNom = document.createElement("td");
        tdNom.textContent = usuari.name
        trFila.appendChild(tdNom);

        //Cognom 1
        const tdCognom1 = document.createElement("td");
        tdCognom1.textContent = usuari.surname1
        trFila.appendChild(tdCognom1);

        //Cognom 2
        const tdCognom2 = document.createElement("td");
        tdCognom2.textContent = usuari.surname2
        trFila.appendChild(tdCognom2);

        //Nom complet
        const tdNomComplet = document.createElement("td");
        tdNomComplet.textContent =
            usuari.name + " " +
            usuari.surname1 + " " +
            usuari.surname2;


        trFila.appendChild(tdNomComplet);

        //Inicials
        const tdInicials = document.createElement("td");

        /*tdInicials.textContent =
            usuari.name.charAt(0) +
            usuari.surname1.charAt(0);


        trFila.appendChild(tdInicials);*/

        //Transport
        const tdTransport = document.createElement("td");

        if (usuari.transport !== null) {
            tdTransport.textContent = usuari.transport;
        } else {
            tdTransport.textContent = "";
        }

        trFila.appendChild(tdTransport);
        body.appendChild(trFila);
    }

    taula.appendChild(body);
    app.appendChild(taula);
}