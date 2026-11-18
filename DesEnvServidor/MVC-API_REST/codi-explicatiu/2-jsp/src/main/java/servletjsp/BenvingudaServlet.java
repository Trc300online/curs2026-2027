package servletjsp;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalTime;

/**
 * El repartiment de feines: el Servlet llegeix la petició i decideix, i la JSP pinta.
 * No hi ha cap println: les dades passen a la JSP com a atributs de la petició.
 */
@WebServlet("/benvinguda")
public class BenvingudaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // 1. Llegir
        String nom = req.getParameter("nom");
        if (nom == null || nom.isBlank()) {
            nom = "desconegut";
        }

        // 2. Decidir
        String salutacio = LocalTime.now().getHour() < 14 ? "Bon dia" : "Bon horabaixa";

        // 3. Deixar les dades a la petició
        req.setAttribute("nom", nom);
        req.setAttribute("salutacio", salutacio);

        // 4. Passar la petició a la JSP: és dins WEB-INF, així que ningú no la pot obrir directament
        req.getRequestDispatcher("/WEB-INF/vistes/benvinguda.jsp").forward(req, resp);

        // ── PROVA 3 ─ Descomentau: un segon forward (el que passa si oblidau un return després d'un if).
        //    500: IllegalStateException: Cannot forward after response has been committed
        // req.getRequestDispatcher("/WEB-INF/vistes/benvinguda.jsp").forward(req, resp);
    }
}
