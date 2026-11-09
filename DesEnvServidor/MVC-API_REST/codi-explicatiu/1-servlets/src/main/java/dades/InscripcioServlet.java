package dades;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.Html;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Rep el formulari d'inscripcio.html (method="post"). Els paràmetres arriben al cos de la petició,
 * però es llegeixen igual que per GET: amb getParameter i el name de cada camp.
 * Només hi ha doPost: si escriviu /servlets/inscripcio a la barra del navegador (un GET), Tomcat torna 405.
 */
@WebServlet("/inscripcio")
public class InscripcioServlet extends HttpServlet {

    private static final Map<String, String> TALLERS = Map.of(
            "git", "Git i GitHub sense por",
            "docker", "Docker per a principiants",
            "ia", "Programar amb assistents d'IA");

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // Els accents del cos (Aina Ferrà) es llegeixen bé gràcies a <request-character-encoding> del web.xml
        String nom = req.getParameter("nom");
        String correu = req.getParameter("correu");
        String taller = req.getParameter("taller");          // radio: un sol valor (o null si no n'hi ha cap)
        String[] torns = req.getParameterValues("torns");     // checkbox: 0, 1 o més valors (null si cap)

        // El formulari ja té required, però no ens en podem fiar: qualsevol pot enviar una petició sense el formulari
        List<String> errors = new ArrayList<>();
        if (nom == null || nom.isBlank()) {
            errors.add("Falta el nom.");
        }
        if (correu == null || !correu.contains("@")) {
            errors.add("El correu no és vàlid.");
        }
        if (taller == null || !TALLERS.containsKey(taller)) {
            errors.add("Heu de triar un taller.");
        }
        if (torns == null) {
            errors.add("Marcau almenys un torn.");
        }

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"ca\"><head><meta charset=\"utf-8\"><title>Inscripció</title></head><body>");

        if (!errors.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);   // 400: la petició té dades incorrectes
            out.println("<h1>No s'ha pogut fer la inscripció</h1>");
            out.println("<ul>");
            for (String error : errors) {
                out.println("<li>" + error + "</li>");
            }
            out.println("</ul>");
            out.println("<p><a href=\"inscripcio.html\">Tornar al formulari</a></p>");
        } else {
            out.println("<h1>Inscripció rebuda</h1>");
            out.println("<p>Gràcies, <strong>" + Html.escapa(nom) + "</strong>. T'escriurem a "
                    + Html.escapa(correu) + ".</p>");
            out.println("<p>Taller: " + TALLERS.get(taller) + "</p>");
            out.println("<p>Torns: " + Html.escapa(String.join(" i ", torns)) + "</p>");
            out.println("<p><a href=\"" + req.getContextPath() + "/\">Tornar a l'inici</a></p>");
        }
        out.println("</body></html>");
    }

    // ── PROVA 4 ─ (bloc 4) Descomentau el mètode sencer: ara un GET a /inscripcio redirigeix al formulari en lloc de donar 405
    // @Override
    // protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    //     resp.sendRedirect("inscripcio.html");
    // }
}
