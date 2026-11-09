package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Jornades;

import java.io.IOException;

/** Pàgina pública: tothom pot veure els tallers. */
@WebServlet("/tallers")
public class TallersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("tallers", Jornades.instancia().tallers());
        req.getRequestDispatcher("/WEB-INF/vistes/tallers.jsp").forward(req, resp);
    }
}
