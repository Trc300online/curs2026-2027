package controlador;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Jornades;
import model.Taller;
import model.Usuari;

import java.io.IOException;

/** Inscriure's: l'usuari no surt d'un formulari, sinó de la sessió. Ningú no es pot fer passar per un altre. */
@WebServlet("/meves/inscriu")
public class InscriuServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession sessio = req.getSession();
        Usuari usuari = (Usuari) sessio.getAttribute("usuari");

        String error = Jornades.instancia().inscriu(usuari, req.getParameter("taller"));

        // Després hi ha un redirect, i un atribut de la petició es perdria. La capçalera el mostra i l'esborra de la sessió
        sessio.setAttribute("missatge", error != null ? error : "Inscripció feta.");
        resp.sendRedirect(req.getContextPath() + "/meves/inscripcions");
    }
}
