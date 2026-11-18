package controlador;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Jornades;

import java.io.IOException;

/**
 * Anul·lar canvia dades, per tant només per POST (un botó de formulari, no un enllaç).
 * No té vista pròpia: després de fer la feina, redirigeix a la llista.
 */
@WebServlet("/anulla")
public class AnullaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (!Jornades.instancia().anulla(Parametres.enter(req, "id"))) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "No hi ha cap inscripció amb aquest identificador.");
            return;
        }
        resp.sendRedirect(req.getContextPath() + "/inscripcions");
    }

    // ── PROVA 2 ─ Descomentau el mètode i compilau: ara /anulla?id=1 escrit a la barra esborra la inscripció. Un enllaç
    //    en un correu, l'historial o un cercador que el visiti també. Per això un GET mai no ha de canviar dades.
    // @Override
    // protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    //     doPost(req, resp);
    // }
}
