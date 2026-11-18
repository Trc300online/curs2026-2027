package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Jornades;

import java.io.IOException;

/** La llista de totes les inscripcions, amb un botó per anul·lar-les. */
@WebServlet("/inscripcions")
public class InscripcionsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("inscripcions", Jornades.instancia().inscripcions());
        req.getRequestDispatcher("/WEB-INF/vistes/inscripcions.jsp").forward(req, resp);
    }
}
