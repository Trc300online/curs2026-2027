package cat.politecnicllevant.jpa.controller;

import cat.politecnicllevant.jpa.model.Taller;
import cat.politecnicllevant.jpa.model.TallerModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * GET /taller?id=git → la fitxa d'un sol taller. És pública, com la llista: no és sota /gestio/.
 * Si no hi ha cap taller amb aquest codi, 404.
 */
@WebServlet("/taller")
public class TallerServlet extends HttpServlet {

    private static final String VISTA = "/WEB-INF/taller.jsp";

    private TallerModel tallerModel;

    @Override
    public void init() throws ServletException {
        tallerModel = TallerModel.instancia();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // El codi arriba a la URL, i qualsevol el pot canviar: /taller?id=noexisteix
        Taller taller = tallerModel.taller(Parametres.text(req, "id"));

        if (taller == null) {
            // 404 i no 200 amb una pàgina buida: el recurs que s'ha demanat no existeix
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "No hi ha cap taller amb aquest codi.");
            return;
        }

        req.setAttribute("taller", taller);
        req.getRequestDispatcher(VISTA).forward(req, resp);
    }
}
