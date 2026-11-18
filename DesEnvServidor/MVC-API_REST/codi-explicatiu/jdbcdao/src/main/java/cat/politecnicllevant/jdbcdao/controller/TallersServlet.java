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

        String id = req.getParameter("id");
        String nom = req.getParameter("nom");
        String ponent = req.getParameter("ponent");
        LocalDate dia = LocalDate.parse(req.getParameter("dia"));
        Integer places = Integer.valueOf(req.getParameter("places"));
        BigDecimal preu = new BigDecimal(req.getParameter("preu"));


    }
}
