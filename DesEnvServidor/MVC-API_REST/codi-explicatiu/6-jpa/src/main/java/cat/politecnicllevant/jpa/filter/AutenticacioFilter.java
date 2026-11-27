package cat.politecnicllevant.jpa.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Tot el que hi ha sota /gestio/ només és per a qui ha entrat: el GET del formulari i també el POST que crea.
 * /tallers, /login i /logout queden fora. Si /login passàs pel filtre, tendríem un bucle de redireccions.
 */
@WebFilter("/gestio/*")
public class AutenticacioFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        HttpSession sessio = req.getSession(false);     // false: només volem mirar

        if (sessio == null || sessio.getAttribute("usuari") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;                                     // sense doFilter: la petició no arriba al Servlet
        }
        chain.doFilter(req, resp);
    }
}
