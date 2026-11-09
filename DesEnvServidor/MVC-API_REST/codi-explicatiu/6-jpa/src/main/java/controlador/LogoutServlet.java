package controlador;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/** Sortir: canvia l'estat, per tant és un POST. */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // false: si no hi ha sessió, no en cream una només per esborrar-la
        HttpSession sessio = req.getSession(false);
        if (sessio != null) {
            sessio.invalidate();
        }
        resp.sendRedirect(req.getContextPath() + "/tallers");
    }
}
