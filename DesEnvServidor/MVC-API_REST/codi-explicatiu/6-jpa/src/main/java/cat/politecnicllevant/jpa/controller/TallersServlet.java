package cat.politecnicllevant.jpa.controller;

import cat.politecnicllevant.jpa.model.TallerModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * GET → la llista de tallers. És pública: no passa per cap filtre.
 * El formulari i el doPost ara són a NouTallerServlet, sota /gestio/.
 */
@WebServlet("/tallers")
public class TallersServlet extends HttpServlet {

    private static final String VISTA = "/WEB-INF/tallers.jsp";

    private TallerModel tallerModel;

    @Override
    public void init() throws ServletException {
        tallerModel = TallerModel.instancia();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("tallers", tallerModel.tallers());

        // false: aquí només volem saber qui és. A qui no ha entrat no li cream cap sessió
        HttpSession sessio = req.getSession(false);
        if (sessio != null) {
            // Si no ha entrat, és null, i la vista mostra l'enllaç «Entrar»
            req.setAttribute("usuari", sessio.getAttribute("usuari"));

            // AMPLIACIÓ. El missatge que ha deixat NouTallerServlet abans del redirect. El passam a la request
            // i l'esborram de la sessió: es mostra una sola vegada, i un F5 ja no el torna a mostrar
            req.setAttribute("missatge", sessio.getAttribute("missatge"));
            sessio.removeAttribute("missatge");
        }

        req.getRequestDispatcher(VISTA).forward(req, resp);
    }
}
