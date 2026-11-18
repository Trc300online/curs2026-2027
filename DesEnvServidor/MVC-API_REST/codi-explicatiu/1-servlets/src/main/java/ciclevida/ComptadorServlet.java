package ciclevida;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * El cicle de vida: Tomcat crea UN sol objecte d'aquest Servlet, crida init() una vegada,
 * doGet() a cada petició (cadascuna en un fil diferent) i destroy() quan s'atura o es torna a desplegar l'aplicació.
 * Mirau la consola de Tomcat i obriu /comptador des de dos navegadors diferents: el comptador és compartit.
 */
@WebServlet("/comptador")
// ── PROVA 6 ─ Canviau l'anotació per aquesta: init() s'executa en desplegar l'aplicació, abans de la primera petició
// @WebServlet(value = "/comptador", loadOnStartup = 1)
public class ComptadorServlet extends HttpServlet {

    // Un camp d'un Servlet és compartit per TOTS els usuaris i TOTS els fils alhora.
    // AtomicInteger garanteix que dues peticions simultànies no perdin cap increment (amb un int es podrien perdre).
    private final AtomicInteger visites = new AtomicInteger();
    private LocalTime creat;

    @Override
    public void init() {
        creat = LocalTime.now().withNano(0);
        System.out.println("init(): Tomcat ha creat el ComptadorServlet a les " + creat);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int numero = visites.incrementAndGet();          // variable local: cada petició té la seva
        String fil = Thread.currentThread().getName();
        System.out.println("doGet(): visita " + numero + " atesa pel fil " + fil);

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"ca\"><head><meta charset=\"utf-8\"><title>Comptador</title></head><body>");
        out.println("<h1>Visita número " + numero + "</h1>");
        out.println("<p>Aquest objecte Servlet existeix des de les " + creat + ".</p>");
        out.println("<p>T'ha atès el fil <code>" + fil + "</code>.</p>");
        out.println("<p><a href=\"" + req.getContextPath() + "/\">Tornar a l'inici</a></p>");
        out.println("</body></html>");
    }

    @Override
    public void destroy() {
        System.out.println("destroy(): s'elimina el ComptadorServlet després de " + visites.get() + " visites");
    }
}
