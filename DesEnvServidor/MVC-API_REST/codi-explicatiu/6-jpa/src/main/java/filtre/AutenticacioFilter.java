package filtre;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Qui sou? Protegeix tot el que hi ha sota /meves/* i /admin/* (els patrons són a web.xml).
 * Així cap Servlet no ha de repetir la comprovació, i cap Servlet nou no se n'oblidarà.
 */
public class AutenticacioFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        // false: si no hi ha sessió, no en cream una. Només volem mirar
        HttpSession sessio = req.getSession(false);

        if (sessio == null || sessio.getAttribute("usuari") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;     // no cridam chain.doFilter: la petició no arriba al Servlet
        }

        // Que el navegador no guardi una còpia: després de sortir, el botó d'enrere no ha de mostrar pàgines privades
        resp.setHeader("Cache-Control", "no-store");
        chain.doFilter(req, resp);
    }
}
