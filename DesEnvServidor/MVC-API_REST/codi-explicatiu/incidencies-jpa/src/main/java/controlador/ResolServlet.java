package controlador;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.GestorIncidencies;

import java.io.IOException;

/** Canvia dades, per tant només té doPost. No té vista pròpia: acaba amb un redirect. */
@WebServlet("/incidencies/resol")
public class ResolServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id;
        try {
            id = Integer.parseInt(req.getParameter("id"));
        } catch (NumberFormatException e) {     // parseInt(null) també llança aquesta excepció
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "L'identificador no és vàlid.");
            return;
        }
        if (!GestorIncidencies.instancia().resol(id)) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "No hi ha cap incidència amb aquest identificador.");
            return;
        }
        resp.sendRedirect(req.getContextPath() + "/incidencies");
    }
}
