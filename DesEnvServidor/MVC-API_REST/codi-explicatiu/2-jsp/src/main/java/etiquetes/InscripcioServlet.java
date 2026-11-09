package etiquetes;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Jornades;
import model.Taller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * La mateixa inscripció que a 1-servlets, però sense cap println:
 *   GET  → mostra el formulari
 *   POST → valida. Si hi ha errors, torna a mostrar el formulari amb el que l'usuari havia escrit.
 *          Si no n'hi ha, mostra la confirmació.
 * De moment no guarda res: això (i evitar que F5 reenviï el formulari) és per a la passa 3.
 */
@WebServlet("/inscripcio")
public class InscripcioServlet extends HttpServlet {

    private static final String FORMULARI = "/WEB-INF/vistes/inscripcio.jsp";
    private static final String CONFIRMACIO = "/WEB-INF/vistes/confirmacio.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("tallers", Jornades.tallers());
        req.setAttribute("tornsTriats", List.of());
        req.getRequestDispatcher(FORMULARI).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String nom = req.getParameter("nom");
        String correu = req.getParameter("correu");
        Taller taller = Jornades.cerca(req.getParameter("taller"));
        String[] torns = req.getParameterValues("torns");

        List<String> errors = new ArrayList<>();
        if (nom == null || nom.isBlank()) {
            errors.add("Falta el nom.");
        }
        if (correu == null || !correu.contains("@")) {
            errors.add("El correu no és vàlid.");
        }
        if (taller == null) {
            errors.add("Heu de triar un taller.");
        } else if (taller.isPle()) {
            errors.add("El taller «" + taller.getNom() + "» ja és ple.");
        }
        if (torns == null) {
            errors.add("Marcau almenys un torn.");
        }

        // Tant el formulari com la confirmació usen aquestes dades
        req.setAttribute("nom", nom);
        req.setAttribute("correu", correu);
        req.setAttribute("taller", taller);
        req.setAttribute("tornsTriats", torns == null ? List.of() : List.of(torns));

        if (!errors.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            req.setAttribute("errors", errors);
            req.setAttribute("tallers", Jornades.tallers());
            req.getRequestDispatcher(FORMULARI).forward(req, resp);
            return;
        }
        req.getRequestDispatcher(CONFIRMACIO).forward(req, resp);
    }
}
