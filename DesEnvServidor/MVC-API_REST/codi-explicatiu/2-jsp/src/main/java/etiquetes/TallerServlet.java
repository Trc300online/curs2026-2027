package etiquetes;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Jornades;
import model.Taller;

import java.io.IOException;

/** /taller?id=git. Si no existeix, sendError: ho pinta la pàgina d'error que diu el web.xml. */
@WebServlet("/taller")
public class TallerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Taller taller = Jornades.cerca(req.getParameter("id"));
        if (taller == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "No hi ha cap taller amb aquest identificador.");
            return;
        }
        req.setAttribute("taller", taller);
        req.getRequestDispatcher("/WEB-INF/vistes/taller.jsp").forward(req, resp);
    }
}
