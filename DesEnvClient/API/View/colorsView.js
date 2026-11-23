function pintarBoto(quadrat, fons) {
    let boto = document.createElement("button");
    boto.innerHTML = "colorize me!";

    boto.addEventListener("click", function () {
        getColors().then(function (resposta) {
            updateSquare(quadrat, "#" + resposta.color, resposta.width, resposta.height);
            squarePaint(quadrat);
            updateBackground(fons, "#" + resposta.background);
            paintBackground(fons);
        })
    });

    document.querySelector("#app").appendChild(boto);
}

function squarePaint(square) {

    document.querySelector("#square").style.width = square.ample + "px";
    document.querySelector("#square").style.height = square.alt + "px";
    document.querySelector("#square").style.backgroundColor = square.color;
}

function initSquare() {
    const square = document.createElement("div");
    square.id = "square";
    square.style.transition = "200ms";
    document.querySelector("#app").appendChild(square);
}

function paintBackground(background) {
    document.querySelector("#app    ").style.backgroundColor = background.color;
}