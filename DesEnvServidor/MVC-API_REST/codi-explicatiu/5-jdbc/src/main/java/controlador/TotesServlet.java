package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Jornades;

import java.io.IOException;

/** Sota /admin/*: només hi arriba qui ha passat els dos filtres. */
@WebServlet("/admin/inscripcions")
public class TotesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("inscripcions", Jornades.instancia().inscripcions());
        req.getRequestDispatcher("/WEB-INF/vistes/totes.jsp").forward(req, resp);
    }
}
