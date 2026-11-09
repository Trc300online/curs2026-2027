package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Inscripcio;
import model.Jornades;

import java.io.IOException;
import java.util.List;

/**
 * GET mostra el formulari. POST crea la inscripció i aplica Post/Redirect/Get:
 * si ha anat bé no pinta res, sinó que redirigeix a la confirmació.
 */
@WebServlet("/inscripcio")
public class InscripcioServlet extends HttpServlet {

    private static final String FORMULARI = "/WEB-INF/vistes/inscripcio.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("tallers", Jornades.instancia().tallers());
        req.setAttribute("inscripcio", new Inscripcio());
        req.getRequestDispatcher(FORMULARI).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Jornades jornades = Jornades.instancia();

        // 1. Els paràmetres (text del client) passen a ser un objecte del model
        Inscripcio inscripcio = new Inscripcio();
        inscripcio.setNom(req.getParameter("nom"));
        inscripcio.setCorreu(req.getParameter("correu"));
        inscripcio.setTaller(jornades.taller(req.getParameter("taller")));

        // 2. Les regles són del model
        List<String> errors = jornades.inscriu(inscripcio);

        // 3. Amb errors: forward al formulari, amb les dades que l'usuari havia escrit
        if (!errors.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            req.setAttribute("errors", errors);
            req.setAttribute("inscripcio", inscripcio);
            req.setAttribute("tallers", jornades.tallers());
            req.getRequestDispatcher(FORMULARI).forward(req, resp);
            return;
        }

        // 4. Tot correcte: redirect. La darrera petició del navegador serà un GET, i F5 no repetirà la inscripció
        resp.sendRedirect(req.getContextPath() + "/confirmacio?id=" + inscripcio.getId());

        // ── PROVA 1 ─ Comentau el sendRedirect i descomentau les dues línies següents. Compilau, inscriviu-vos i premeu F5
        //    a la confirmació diverses vegades: el navegador torna a enviar el POST, i a /inscripcions hi ha duplicats.
        // req.setAttribute("inscripcio", inscripcio);
        // req.getRequestDispatcher("/WEB-INF/vistes/confirmacio.jsp").forward(req, resp);
    }
}
