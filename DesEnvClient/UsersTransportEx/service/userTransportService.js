function getUsers(){
    /*
    const usuaris = [
        new User('jgalmes', 'Joan', 'Galmés', 'Riera', null),
        new User('pprohens', 'Pere', 'Prohens', 'Riera', null),
        new User('anicolau', 'Antoni', 'Herrer', 'Riera', null)
    ]

    const usuarisH = usuaris.filter(function(usuari){
        return usuari.surname1.toLowerCase().includes('h') ||
               usuari.surname2.toLowerCase().includes('h');
    })

    return usuarisH;
     */

    return fetch("https://theteacher.codiblau.com/public/exercicis/other/usuaris/list")
        .then(function(resposta){
            return resposta.json()
        })
}


