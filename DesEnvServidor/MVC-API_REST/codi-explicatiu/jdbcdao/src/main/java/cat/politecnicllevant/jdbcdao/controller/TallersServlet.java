package cat.politecnicllevant.jdbcdao.controller;

import cat.politecnicllevant.jdbcdao.model.Taller;
import cat.politecnicllevant.jdbcdao.model.TallerModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/tallers")
public class TallersServlet extends HttpServlet {

    TallerModel tallerModel;

    @Override
    public void init() throws ServletException {
        tallerModel = TallerModel.instancia();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Taller> tallers = tallerModel.tallers();


        req.setAttribute("tallers", tallers);

        req.getRequestDispatcher("/WEB-INF/tallers.jsp").forward(req, resp);

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
            req.setAttribute("tallers", tallerModel.tallers());

            // Forward: conservam els errors dins la mateixa request perquè els mostri tallers.jsp.
            req.getRequestDispatcher("/WEB-INF/tallers.jsp").forward(req, resp);
            return;
        }

        // Redirect després d'inserir correctament (Post/Redirect/Get).
        resp.sendRedirect(req.getContextPath() + "/tallers");
    }
}
