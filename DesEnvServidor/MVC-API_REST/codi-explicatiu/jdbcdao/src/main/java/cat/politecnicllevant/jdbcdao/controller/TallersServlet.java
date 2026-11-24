package cat.politecnicllevant.jdbcdao.controller;

import cat.politecnicllevant.jdbcdao.model.Taller;
import cat.politecnicllevant.jdbcdao.model.TallerModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

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

        HttpSession sessio = req.getSession(false);

        String usuari = null;

        if (sessio != null) {
            usuari = (String) sessio.getAttribute("usuari");
        }

        req.setAttribute("usuari", usuari);

        req.getRequestDispatcher("/WEB-INF/tallers.jsp").forward(req, resp);

    }
}
