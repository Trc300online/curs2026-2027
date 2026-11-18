package etiquetes;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Jornades;

import java.io.IOException;

/** El Servlet només deixa la llista a la petició. El bucle i les condicions són a tallers.jsp, amb JSTL. */
@WebServlet("/tallers")
public class TallersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("tallers", Jornades.tallers());
        req.getRequestDispatcher("/WEB-INF/vistes/tallers.jsp").forward(req, resp);
    }
}
