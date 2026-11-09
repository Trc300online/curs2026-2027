package cat.politecnicllevant.primer;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ParametresServlet extends HttpServlet {

    List<Inscripcio> llistaInscripcions = new ArrayList<>();

    @Override
    public void init() throws ServletException {
        llistaInscripcions.add(new Inscripcio("Toni", 22, "grau superior"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("inscripcions", llistaInscripcions);

        //req.getRequestDispatcher("/WEB-INF/inscripcions.jsp").forward(req, resp);
        req.getRequestDispatcher("/WEB-INF/inscripcions2.jsp").forward(req, resp);

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String nom = req.getParameter("nom");
        String estudis = switch (String.valueOf(req.getParameter("estudis"))) {
            case "grau-mitja" -> "grau mitja";
            case "grau-superior" -> "grau superior";
            case "grau-especialitzacio" -> "grau especialitzacio";
            default -> null;
        };

        try {
            int edat = Integer.parseInt(req.getParameter("edat"));

            if (nom != null && !nom.isBlank() && estudis !=null) {
                llistaInscripcions.add(new Inscripcio(nom, edat, estudis));
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            req.setAttribute("error", "error");
            //req.getRequestDispatcher("/WEB-INF/inscripcions.jsp").forward(req, resp);
            req.getRequestDispatcher("/WEB-INF/inscripcions2.jsp").forward(req, resp);
            return;
        }

        resp.sendRedirect("parametres");
    }
}
