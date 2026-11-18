package resposta;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * La mateixa informació en tres formats: el tipus MIME (setContentType) diu al client què li arriba.
 * Provau: /hora, /hora?format=text, /hora?format=json, /hora?format=xml
 * Obriu les eines de desenvolupador (F12 > Xarxa) i mirau la capçalera Content-Type de cada resposta.
 */
@WebServlet("/hora")
public class HoraServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String format = req.getParameter("format");
        if (format == null) {
            format = "html";
        }
        LocalDate avui = LocalDate.now();
        LocalTime ara = LocalTime.now().withNano(0);

        switch (format) {
            case "html" -> {
                resp.setContentType("text/html;charset=UTF-8");
                PrintWriter out = resp.getWriter();
                out.println("<!DOCTYPE html>");
                out.println("<html lang=\"ca\"><head><meta charset=\"utf-8\"><title>Hora</title></head><body>");
                out.println("<p>Avui és <strong>" + avui + "</strong> i són les <strong>" + ara + "</strong>.</p>");
                out.println("</body></html>");
            }
            case "text" -> {
                resp.setContentType("text/plain;charset=UTF-8");
                resp.getWriter().println("Avui és " + avui + " i són les " + ara + ".");
            }
            case "json" -> {
                // Només dades: qui les rep decideix com mostrar-les (recordau MVC vs API REST)
                resp.setContentType("application/json;charset=UTF-8");
                resp.getWriter().println("{\"data\": \"" + avui + "\", \"hora\": \"" + ara + "\"}");
            }
            // 400 Bad Request: l'error és del client, que ha demanat un format que no existeix
            default -> resp.sendError(HttpServletResponse.SC_BAD_REQUEST,
                    "Format desconegut. Usau html, text o json");
        }
    }
}
