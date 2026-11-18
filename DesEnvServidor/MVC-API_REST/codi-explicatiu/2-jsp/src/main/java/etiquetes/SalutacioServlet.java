package etiquetes;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/** Passa el nom a la JSP tal com arriba. Escapar-lo és feina de la vista: mirau salutacio.jsp. */
@WebServlet("/salutacio")
public class SalutacioServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("nom", req.getParameter("nom"));
        req.getRequestDispatcher("/WEB-INF/vistes/salutacio.jsp").forward(req, resp);
    }
}
