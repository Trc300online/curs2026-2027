package cat.politecnicllevant.jpa.controller;

import cat.politecnicllevant.jpa.model.Usuaris;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * GET  → el formulari d'entrada
 * POST → si el nom i la contrasenya són bons, guardam el nom a la sessió i tornam a la llista
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final String VISTA = "/WEB-INF/login.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(VISTA).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nom = req.getParameter("nom");
        String contrasenya = req.getParameter("contrasenya");

        if (!Usuaris.autentica(nom, contrasenya)) {
            // El mateix missatge si falla el nom o la contrasenya. La contrasenya no es torna mai a la vista
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);   // 401
            req.setAttribute("error", "Usuari o contrasenya incorrectes.");
            req.setAttribute("nom", nom);
            req.getRequestDispatcher(VISTA).forward(req, resp);
            return;
        }

        HttpSession sessio = req.getSession();   // ha d'existir abans de canviar-li l'id
        req.changeSessionId();                   // un id nou en entrar: ningú no pot conèixer l'anterior
        sessio.setAttribute("usuari", nom);      // això és «haver entrat»

        // Redirect: entrar canvia l'estat, i F5 no ha de tornar a enviar la contrasenya
        resp.sendRedirect(req.getContextPath() + "/tallers");
    }
}
