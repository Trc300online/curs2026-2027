package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Usuaris;

import java.io.IOException;

/** Entrar: si el nom i la contrasenya són bons, guardam el nom a la sessió. */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final String FORMULARI = "/WEB-INF/vistes/login.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher(FORMULARI).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String nom = req.getParameter("nom");
        String contrasenya = req.getParameter("contrasenya");

        if (!Usuaris.autentica(nom, contrasenya)) {
            // El mateix missatge si falla el nom o la contrasenya: no donam pistes de quins usuaris existeixen
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);   // 401
            req.setAttribute("error", "Usuari o contrasenya incorrectes.");
            req.setAttribute("nom", nom);
            req.getRequestDispatcher(FORMULARI).forward(req, resp);
            return;
        }

        // La sessió d'aquest navegador. Si encara no en té, Tomcat la crea i li envia la cookie JSESSIONID
        HttpSession sessio = req.getSession();

        // Regenerem la sessionid
        req.changeSessionId();

        // Aquí ja hem entrat, tenim una sessió vàlida i ja podem carregar dades a la sessió
        sessio.setAttribute("usuari", nom);

        resp.sendRedirect(req.getContextPath() + "/privat/perfil");
    }
}
