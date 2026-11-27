package cat.politecnicllevant.jpa.controller;

import jakarta.servlet.http.HttpServletRequest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Llegeix els paràmetres d'una petició i els converteix al tipus que toca.
 * És del controlador, no del model: treballa amb HttpServletRequest, és a dir, amb HTTP.
 * La fan servir tots els Servlets que reben un formulari, i així no copiam aquests mètodes a cada un.
 *
 * Tot arriba com a String. Si un valor no es pot convertir torna null, i ja ho dirà Taller.valida():
 * així un "abc" a les places no acaba en un error 500.
 *
 * Sense public: només la fan servir els Servlets d'aquest paquet.
 */
final class Parametres {

    private Parametres() {      // només té mètodes static: no se'n fan objectes
    }

    /** El text sense espais al principi ni al final, o null si no hi és. */
    static String text(HttpServletRequest req, String nom) {
        String valor = req.getParameter(nom);
        return valor == null ? null : valor.strip();
    }

    static Integer enter(HttpServletRequest req, String nom) {
        try {
            return Integer.valueOf(text(req, nom));
        } catch (NumberFormatException e) {     // valueOf(null) també llança aquesta excepció
            return null;
        }
    }

    /** Accepta 12,50 i 12.50 */
    static BigDecimal decimal(HttpServletRequest req, String nom) {
        try {
            return new BigDecimal(text(req, nom).replace(',', '.'));
        } catch (NumberFormatException | NullPointerException e) {
            return null;
        }
    }

    /** Un <input type="date"> sempre envia la data així: 2026-11-24 */
    static LocalDate data(HttpServletRequest req, String nom) {
        try {
            return LocalDate.parse(text(req, nom));
        } catch (DateTimeParseException | NullPointerException e) {
            return null;
        }
    }
}
