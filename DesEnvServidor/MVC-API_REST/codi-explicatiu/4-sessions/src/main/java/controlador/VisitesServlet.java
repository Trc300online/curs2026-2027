package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * La sessió més senzilla: un comptador de visites que recorda cada navegador pel seu compte.
 * Provau-ho amb una finestra d'incògnit al costat: té una sessió diferent.
 */
@WebServlet("/visites")
public class VisitesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Torna la sessió d'aquest navegador. Si encara no en té, en crea una
        HttpSession sessio = req.getSession();

        Integer visites = (Integer) sessio.getAttribute("visites");
        visites = (visites == null) ? 1 : visites + 1;
        sessio.setAttribute("visites", visites);

        req.getRequestDispatcher("/WEB-INF/vistes/visites.jsp").forward(req, resp);
    }
}
