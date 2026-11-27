package cat.politecnicllevant.jpa.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Només POST: sortir canvia l'estat. Sense doGet, un GET /logout torna 405,
 * i un enllaç o el navegador precarregant la pàgina no vos poden fer sortir.
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession sessio = req.getSession(false);   // false: no en cream una només per esborrar-la
        if (sessio != null) {
            sessio.invalidate();
        }
        resp.sendRedirect(req.getContextPath() + "/tallers");
    }
}
