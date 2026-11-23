let xhr = new XMLHttpRequest();

xhr.open("GET", "https://theteacher.codiblau.com/public/exercicis/other/color?min=100&max=500");


function getColors() {
    return fetch("https://theteacher.codiblau.com/public/exercicis/other/color?min=200&max=700")
        .then(function (resposta) {
            return resposta.json();
        })
}

function updateSquare(square, color, width, height) {
    square.color = color;
    square.alt = width;
    square.ample = height;
}

function updateBackground(background, color) {
    background.color = color;
}