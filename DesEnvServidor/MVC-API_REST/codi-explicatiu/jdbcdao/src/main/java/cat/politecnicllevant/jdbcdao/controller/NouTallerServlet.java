
package cat.politecnicllevant.jdbcdao.controller;

import cat.politecnicllevant.jdbcdao.model.TallerModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/gestio/nou-taller")
public class NouTallerServlet extends HttpServlet {

    private TallerModel tallerModel;

    @Override
    public void init() throws ServletException {
        tallerModel = TallerModel.instancia();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/WEB-INF/nou-taller.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        List<String> errors = tallerModel.crea(
                req.getParameter("id"),
                req.getParameter("nom"),
                req.getParameter("ponent"),
                req.getParameter("dia"),
                req.getParameter("places"),
                req.getParameter("preu")
        );

        if (!errors.isEmpty()) {

            req.setAttribute("errors", errors);

            req.getRequestDispatcher("/WEB-INF/nou-taller.jsp").forward(req, resp);

            return;
        }

        resp.sendRedirect(req.getContextPath() + "/tallers");
    }
}
