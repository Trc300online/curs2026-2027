package resposta;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

/**
 * Un escurçador d'enllaços: /enllac/tomcat redirigeix a la documentació de Tomcat.
 * sendRedirect respon 302 amb la capçalera Location, i és el navegador qui fa una segona petició
 * a la nova adreça (per això canvia la URL de la barra).
 * Provau: /enllac/tomcat, /enllac/jakarta, /enllac/mdn, /enllac/noexisteix, /enllac/
 */
@WebServlet("/enllac/*")
public class EnllacServlet extends HttpServlet {

    private static final Map<String, String> ENLLACOS = Map.of(
            "tomcat", "https://tomcat.apache.org/tomcat-11.0-doc/",
            "jakarta", "https://jakarta.ee/specifications/servlet/6.1/",
            "mdn", "https://developer.mozilla.org/en-US/docs/Web/HTTP/Reference/Status");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pathInfo = req.getPathInfo();          // /enllac/tomcat → "/tomcat"   ·   /enllac → null
        if (pathInfo == null || pathInfo.equals("/")) {
            // Redirecció dins la mateixa aplicació: cal posar-hi el context (/servlets)
            resp.sendRedirect(req.getContextPath() + "/index.html");
            return;
        }

        String desti = ENLLACOS.get(pathInfo.substring(1));
        if (desti == null) {
            // 404 Not Found: Tomcat genera la pàgina d'error amb el nostre missatge
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "No hi ha cap enllaç amb aquest nom");
            return;
        }
        resp.sendRedirect(desti);

        // ── PROVA 5 ─ Després d'un sendRedirect la resposta ja està tancada: tornar a redirigir llança
        //             IllegalStateException i l'usuari veu un error 500 en lloc d'anar a la pàgina
        // resp.sendRedirect("https://jakarta.ee");
    }
}
