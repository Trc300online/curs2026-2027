package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Jornades;
import model.Usuari;

import java.io.IOException;

/** Entrar: comprova les credencials amb el model i, si són correctes, posa l'usuari a la sessió. */
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
        Usuari usuari = Jornades.instancia().autentica(nom, req.getParameter("contrasenya"));

        if (usuari == null) {
            // El mateix missatge si falla el nom o la contrasenya: no donam pistes de quins usuaris existeixen
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            req.setAttribute("error", "Usuari o contrasenya incorrectes.");
            req.setAttribute("nom", nom);
            req.getRequestDispatcher(FORMULARI).forward(req, resp);
            return;
        }

        // ── PROVA 2 ─ Mirau la cookie JSESSIONID a F12 > Aplicació abans i després d'entrar: canvia. Comentau aquesta línia
        //    i compilau: ara no canvia. Qui hagués aconseguit que usàssiu un id conegut seguiria dins amb el vostre usuari.
        HttpSession sessio = req.getSession();     // ha d'existir abans de canviar-li l'id
        req.changeSessionId();
        sessio.setAttribute("usuari", usuari);

        resp.sendRedirect(req.getContextPath() + "/meves/inscripcions");
    }
}
