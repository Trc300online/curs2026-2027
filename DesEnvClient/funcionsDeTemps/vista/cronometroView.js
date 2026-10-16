function pintarCronometre(cronometro) {
    const hours = leadZero(cronometro.hora, 2);
    const minutes = leadZero(cronometro.minut, 2);
    const seconds = leadZero(cronometro.segon, 2);
    const milliseconds = leadZero(cronometro.milisegon, 3);

    const div = document.querySelector("#cronometro");
    div.innerText = `${hours}:${minutes}:${seconds}.${milliseconds}`;
}

function pintarCronometreInit(cronometro) {
    let time;

    const hours = leadZero(cronometro.hora, 2);
    const minutes = leadZero(cronometro.minut, 2);
    const seconds = leadZero(cronometro.segon, 2);
    const milliseconds = leadZero(cronometro.milisegon, 3);

    let div = document.createElement("div");
    div.id = "cronometro";
    let p = document.createElement("p");
    div.innerText = `${hours}:${minutes}:${seconds}.${milliseconds}`;

    document.querySelector("#app").appendChild(div);

    const start = document.createElement("button");
    const pause = document.createElement("button");
    const stop = document.createElement("button");

    start.textContent = "Start";
    pause.textContent = "Pause";
    stop.textContent = "Stop";

    document.querySelector("#app").appendChild(start);
    document.querySelector("#app").appendChild(pause);
    document.querySelector("#app").appendChild(stop);

    start.addEventListener("click", function () {
        setInterval(function () {
            cronometro.segon++;
            pintarCronometre(cronometro);
            }, 1000)
    })

}

function leadZero(hora, amount) {
    let result = "";
    let numeroString = "" + hora;
    if (numeroString.length < amount) {
        for (let x = numeroString.length; x < amount; x++) {
            result += "0";
        }
    }
    return result + hora;
}
