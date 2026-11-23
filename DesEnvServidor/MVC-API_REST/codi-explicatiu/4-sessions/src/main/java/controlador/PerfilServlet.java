package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * La pàgina privada. No comprova si heu entrat: ho fa AutenticacioFilter abans que s'executi.
 * Qui sou no ho diu cap paràmetre, ho diu la sessió.
 */
@WebServlet("/privat/perfil")
public class PerfilServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession sessio = req.getSession();

        // getAttribute torna Object: cal el cast. Si no hi ha res amb aquest nom, torna null
        String usuari = (String) sessio.getAttribute("usuari");

        req.setAttribute("usuari", usuari);
        req.setAttribute("id", sessio.getId());     // el mateix valor que la cookie JSESSIONID
        req.getRequestDispatcher("/WEB-INF/vistes/perfil.jsp").forward(req, resp);
    }
}
