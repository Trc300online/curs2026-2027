package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.GestorIncidencies;
import model.Incidencia;

import java.io.IOException;
import java.util.List;

/** GET mostra el formulari. POST crea la incidència i aplica Post/Redirect/Get. */
@WebServlet("/incidencies/nova")
public class NovaIncidenciaServlet extends HttpServlet {

    private static final String FORMULARI = "/WEB-INF/vistes/formulari.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        mostraFormulari(new Incidencia(), req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Els paràmetres passen a ser un objecte del model
        Incidencia incidencia = new Incidencia();
        incidencia.setAula(req.getParameter("aula"));
        incidencia.setDescripcio(req.getParameter("descripcio"));
        incidencia.setPrioritat(req.getParameter("prioritat"));

        // Les regles són del model
        List<String> errors = GestorIncidencies.instancia().crea(incidencia);

        if (!errors.isEmpty()) {
            // Forward: la mateixa petició, amb els errors i el que l'usuari havia escrit
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            req.setAttribute("errors", errors);
            mostraFormulari(incidencia, req, resp);
            return;
        }
        // Redirect: la darrera petició del navegador és un GET, i F5 no crea cap altra incidència
        resp.sendRedirect(req.getContextPath() + "/incidencies");
    }

    private void mostraFormulari(Incidencia incidencia, HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("incidencia", incidencia);
        req.setAttribute("prioritats", GestorIncidencies.instancia().prioritats());
        req.getRequestDispatcher(FORMULARI).forward(req, resp);
    }
}
