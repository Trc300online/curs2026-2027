package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Jornades;
import model.Usuari;

import java.io.IOException;

/** Sota /meves/*: aquí no cal comprovar si hi ha usuari. Si s'arriba fins aquí, el filtre ja ho ha fet. */
@WebServlet("/meves/inscripcions")
public class MevesInscripcionsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Usuari usuari = (Usuari) req.getSession().getAttribute("usuari");
        req.setAttribute("inscripcions", Jornades.instancia().inscripcionsDe(usuari));
        req.getRequestDispatcher("/WEB-INF/vistes/meves.jsp").forward(req, resp);
    }
}
