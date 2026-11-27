package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.GestorIncidencies;

import java.io.IOException;

/** Demana les dades al model, les deixa a la petició i passa la feina a la vista. */
@WebServlet("/incidencies")
public class IncidenciesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("incidencies", GestorIncidencies.instancia().totes());
        req.getRequestDispatcher("/WEB-INF/vistes/llista.jsp").forward(req, resp);
    }
}
