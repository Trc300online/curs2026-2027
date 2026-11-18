package controlador;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Jornades;
import model.Usuari;

import java.io.IOException;

@WebServlet("/meves/anulla")
public class AnullaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession sessio = req.getSession();
        Usuari usuari = (Usuari) sessio.getAttribute("usuari");

        int id;
        try {
            id = Integer.parseInt(req.getParameter("id"));
        } catch (NumberFormatException e) {     // parseInt(null) també llança aquesta excepció
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "L'identificador no és vàlid.");
            return;
        }
        if (!Jornades.instancia().anulla(id, usuari)) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "No teniu cap inscripció amb aquest identificador.");
            return;
        }
        sessio.setAttribute("missatge", "Inscripció anul·lada.");
        resp.sendRedirect(req.getContextPath() + "/meves/inscripcions");
    }
}
