package cat.politecnicllevant.jdbcdao.controller;

import cat.politecnicllevant.jdbcdao.model.Taller;
import cat.politecnicllevant.jdbcdao.model.TallerModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/taller")
public class TallerServlet extends HttpServlet {

    TallerModel tallerModel;

    @Override
    public void init() throws ServletException {
        tallerModel = TallerModel.instancia();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Taller> tallers = tallerModel.tallers();


        req.setAttribute("tallers", tallers);

        req.getRequestDispatcher("/WEB-INF/taller.jsp").forward(req, resp);

    }
}
