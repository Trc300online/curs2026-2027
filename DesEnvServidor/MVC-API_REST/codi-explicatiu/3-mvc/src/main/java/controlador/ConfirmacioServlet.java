package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Inscripcio;
import model.Jornades;

import java.io.IOException;

/**
 * El «Get» de Post/Redirect/Get. Els atributs del POST ja s'han perdut, però la URL porta l'id
 * i les dades són al model. Recarregar aquesta pàgina no canvia res.
 */
@WebServlet("/confirmacio")
public class ConfirmacioServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Inscripcio inscripcio = Jornades.instancia().inscripcio(Parametres.enter(req, "id"));
        if (inscripcio == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "No hi ha cap inscripció amb aquest identificador.");
            return;
        }
        req.setAttribute("inscripcio", inscripcio);
        req.getRequestDispatcher("/WEB-INF/vistes/confirmacio.jsp").forward(req, resp);
    }
}
