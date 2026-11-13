package cat.politecnicllevant.jdbcdao.controller;

import cat.politecnicllevant.jdbcdao.dao.TallerDAO;
import cat.politecnicllevant.jdbcdao.dao.TallerDAOMemoryImpl;
import cat.politecnicllevant.jdbcdao.model.Taller;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/tallers")
public class TallersServlet extends HttpServlet {

    private TallerDAO modelTallers;

    @Override
    public void init() throws ServletException {
        modelTallers = new TallerDAOMemoryImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Taller> tallers = modelTallers.getAll();
        req.setAttribute("tallers", tallers);

        req.getRequestDispatcher("/WEB-INF/tallers.jsp").forward(req, resp);

    }
}
