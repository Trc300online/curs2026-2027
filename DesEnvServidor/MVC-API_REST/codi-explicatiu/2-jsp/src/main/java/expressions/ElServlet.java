package expressions;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Jornades;

import java.io.IOException;
import java.util.Map;

/** Deixa a la petició dades de tipus diferents, perquè el.jsp les mostri amb l'Expression Language. */
@WebServlet("/el")
public class ElServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("curs", "DWES");
        req.setAttribute("edat", 20);
        req.setAttribute("taller", Jornades.cerca("git"));       // un objecte
        req.setAttribute("tallers", Jornades.tallers());         // una llista
        req.setAttribute("sales", Map.of("git", "Aula 12", "docker", "Sala d'actes"));   // un mapa
        req.setAttribute("buit", null);

        // El mateix nom a tres àmbits: ${qui} tria el més petit que el tengui
        req.getServletContext().setAttribute("qui", "aplicació (tothom)");
        req.getSession().setAttribute("qui", "sessió (aquest usuari)");
        // ── PROVA 6 ─ Comentau la línia següent i compilau: ara ${qui} troba el de la sessió
        req.setAttribute("qui", "petició (només aquesta)");

        req.getRequestDispatcher("/WEB-INF/vistes/el.jsp").forward(req, resp);
    }
}
