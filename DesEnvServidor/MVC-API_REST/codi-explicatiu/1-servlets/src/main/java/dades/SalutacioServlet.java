package dades;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.Html;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Paràmetres per GET: van a la URL, darrere el ?
 * Provau: /salutacio, /salutacio?nom=Aina, /salutacio?nom=Aina&idioma=en&vegades=3
 */
@WebServlet("/salutacio")
public class SalutacioServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // getParameter torna sempre un String, o null si el paràmetre no hi és
        String nom = req.getParameter("nom");
        if (nom == null || nom.isBlank()) {
            nom = "desconegut";
        }

        String idioma = req.getParameter("idioma");
        String salutacio = switch (idioma == null ? "ca" : idioma) {
            case "es" -> "Hola";
            case "en" -> "Hello";
            case "fr" -> "Bonjour";
            default -> "Bon dia";
        };

        // Els nombres també arriben com a String: cal convertir-los.
        // Amb ?vegades=tres, parseInt llança NumberFormatException i Tomcat torna un error 500 (ho arreglarem)
        String txtVegades = req.getParameter("vegades");
        int vegades = (txtVegades == null) ? 1 : Integer.parseInt(txtVegades);

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"ca\"><head><meta charset=\"utf-8\"><title>Salutació</title></head><body>");
        for (int i = 0; i < vegades; i++) {
            out.println("<h1>" + salutacio + ", " + Html.escapa(nom) + "!</h1>");
        }
        // ── PROVA 3 ─ Sense escapar, el navegador executa el que envia l'usuari. Provau ?nom=<i>Aina</i>
        //             i després ?nom=<script>alert('Has estat hackejat')</script>  (atac XSS)
        // out.println("<h1>" + salutacio + ", " + nom + "!</h1>");
        out.println("<p><a href=\"" + req.getContextPath() + "/\">Tornar a l'inici</a></p>");
        out.println("</body></html>");
    }
}
