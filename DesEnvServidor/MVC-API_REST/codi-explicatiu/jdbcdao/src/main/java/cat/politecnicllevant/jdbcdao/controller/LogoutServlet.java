
package cat.politecnicllevant.jdbcdao.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        HttpSession sessio = req.getSession(false);

        if (sessio != null) {
            sessio.invalidate();
        }

        resp.sendRedirect(req.getContextPath() + "/tallers");
    }
}
