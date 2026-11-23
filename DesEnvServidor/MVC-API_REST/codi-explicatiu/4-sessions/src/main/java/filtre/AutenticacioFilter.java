package filtre;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * S'executa abans de qualsevol Servlet de /privat/*. Si no heu entrat, vos envia a /login.
 * Així cap Servlet privat no ha de repetir la comprovació, i un Servlet nou sota /privat ja neix protegit.
 */
// ── PROVA 3 ─ Comentau @WebFilter i compilau. Obriu /privat/perfil en una finestra d'incògnit:
//    la pàgina privada es veu sense haver entrat, i diu «Hola, !». El Servlet no comprova res.
@WebFilter("/privat/*")
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

        // ── PROVA 4 ─ Comentau aquesta línia i compilau. Entrau: la pàgina privada surt en blanc.
        //    Sense doFilter la petició s'atura al filtre, i el Servlet no s'executa.
        chain.doFilter(req, resp);      // deixam passar la petició cap al Servlet
    }
}
