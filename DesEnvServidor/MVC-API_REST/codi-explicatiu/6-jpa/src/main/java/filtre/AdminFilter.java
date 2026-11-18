package filtre;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Usuari;

import java.io.IOException;

/** Teniu permís? Ja sabem qui sou, perquè a web.xml AutenticacioFilter va abans que aquest. */
public class AdminFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        Usuari usuari = (Usuari) req.getSession().getAttribute("usuari");

        if (!usuari.isAdmin()) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Aquesta pàgina és només per a l'administració.");
            return;
        }
        chain.doFilter(req, resp);
    }
}
