package primer;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;

/**
 * El primer Servlet: respon a GET /hola amb una pàgina HTML.
 * No hi ha main: és Tomcat qui crea l'objecte i crida doGet cada vegada que arriba una petició.
 * Recarregau la pàgina: l'hora canvia perquè la resposta es genera de nou (contingut dinàmic).
 */
@WebServlet("/hola")
public class HolaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        // ── PROVA 1 ─ Amb text/plain el navegador mostra les etiquetes tal qual: no les interpreta com a HTML
        // resp.setContentType("text/plain;charset=UTF-8");

        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"ca\">");
        out.println("<head><meta charset=\"utf-8\"><title>Hola</title></head>");
        out.println("<body>");
        out.println("<h1>Hola, món!</h1>");
        out.println("<p>Al servidor són les " + LocalTime.now().withNano(0) + ".</p>");
        out.println("</body>");
        out.println("</html>");

        // ── PROVA 2 ─ El missatge surt a la consola de Tomcat (la del docker compose up), no al navegador: el codi s'executa al servidor
        // System.out.println("HolaServlet ha atès un " + req.getMethod() + " de " + req.getRemoteAddr());
    }
}
