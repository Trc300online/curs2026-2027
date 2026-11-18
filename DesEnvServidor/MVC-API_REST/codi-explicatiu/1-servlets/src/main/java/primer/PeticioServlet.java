package primer;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.Html;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;

/**
 * Mostra què porta una petició HTTP, tal com la veu el Servlet (l'objecte HttpServletRequest).
 * El patró /peticio/* fa que també respongui a /peticio/el/que/sigui: la part de més és el «path info».
 * Provau: /peticio, /peticio/cursos/7, /peticio?nom=Aina&nom=Pau&idioma=en
 */
@WebServlet("/peticio/*")
public class PeticioServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"ca\"><head><meta charset=\"utf-8\"><title>La petició</title></head><body>");
        out.println("<h1>Què porta aquesta petició?</h1>");

        out.println("<h2>La URL, a trossos</h2>");
        out.println("<table border=\"1\" cellpadding=\"6\">");
        fila(out, "getMethod()", req.getMethod());
        fila(out, "getRequestURL()", req.getRequestURL().toString());
        fila(out, "getRequestURI()", req.getRequestURI());
        fila(out, "getContextPath()", req.getContextPath() + "   ← l'aplicació");
        fila(out, "getServletPath()", req.getServletPath() + "   ← el Servlet");
        fila(out, "getPathInfo()", req.getPathInfo());
        fila(out, "getQueryString()", req.getQueryString());
        out.println("</table>");

        out.println("<h2>Paràmetres</h2>");
        out.println("<table border=\"1\" cellpadding=\"6\">");
        for (String nom : Collections.list(req.getParameterNames())) {
            // getParameterValues torna tots els valors: ?nom=Aina&nom=Pau → [Aina, Pau]
            fila(out, nom, Arrays.toString(req.getParameterValues(nom)));
        }
        out.println("</table>");

        out.println("<h2>Capçaleres que envia el navegador</h2>");
        out.println("<table border=\"1\" cellpadding=\"6\">");
        for (String nom : Collections.list(req.getHeaderNames())) {
            fila(out, nom, req.getHeader(nom));
        }
        out.println("</table>");

        out.println("<p><a href=\"" + req.getContextPath() + "/\">Tornar a l'inici</a></p>");
        out.println("</body></html>");
    }

    private static void fila(PrintWriter out, String nom, String valor) {
        out.println("<tr><th align=\"left\"><code>" + Html.escapa(nom) + "</code></th>"
                + "<td>" + (valor == null ? "<i>null</i>" : Html.escapa(valor)) + "</td></tr>");
    }
}
