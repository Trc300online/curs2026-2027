
package cat.politecnicllevant.jdbcdao.controller;

import cat.politecnicllevant.jdbcdao.model.Usuaris;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.getRequestDispatcher("/WEB-INF/login.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String nom = req.getParameter("nom");
        String contrasenya = req.getParameter("contrasenya");

        if (!Usuaris.autentica(nom, contrasenya)) {

            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

            req.setAttribute("error",
                    "Usuari o contrasenya incorrectes.");

            req.setAttribute("nom", nom);

            req.getRequestDispatcher("/WEB-INF/login.jsp")
                    .forward(req, resp);

            return;
        }

        HttpSession sessio = req.getSession();

        req.changeSessionId();

        sessio.setAttribute("usuari", nom);

        resp.sendRedirect(req.getContextPath() + "/tallers");
    }
}
