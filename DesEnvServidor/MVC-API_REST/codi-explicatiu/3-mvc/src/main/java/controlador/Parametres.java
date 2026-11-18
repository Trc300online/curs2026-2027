package controlador;

import jakarta.servlet.http.HttpServletRequest;

/** Convertir un paràmetre a enter sense que un valor dolent acabi en un 500. */
final class Parametres {

    private Parametres() {
    }

    /** Torna el paràmetre com a enter, o -1 si no hi és o no és un nombre. */
    static int enter(HttpServletRequest req, String nom) {
        try {
            return Integer.parseInt(req.getParameter(nom));
        } catch (NumberFormatException e) {     // parseInt(null) també llança aquesta excepció
            return -1;
        }
    }
}
