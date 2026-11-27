package cat.politecnicllevant.jpa.controller;

import cat.politecnicllevant.jpa.model.Taller;
import cat.politecnicllevant.jpa.model.TallerModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

/**
 * GET  → el formulari buit
 * POST → paràmetres → Taller → el model el valida i el guarda
 *        Amb errors: forward al formulari. Correcte: redirect a GET /tallers (Post/Redirect/Get)
 *
 * Aquest Servlet no comprova la sessió: és sota /gestio/, i AutenticacioFilter ja ho ha fet abans.
 * El doPost és el que abans era a TallersServlet.
 */
@WebServlet("/gestio/nou-taller")
public class NouTallerServlet extends HttpServlet {

    private static final String VISTA = "/WEB-INF/nou-taller.jsp";

    private TallerModel tallerModel;

    @Override
    public void init() throws ServletException {
        tallerModel = TallerModel.instancia();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(VISTA).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 1. Del text de la petició a un objecte del model. Parametres fa les conversions (Integer, BigDecimal, LocalDate)
        Taller taller = new Taller();
        taller.setId(Parametres.text(req, "id"));
        taller.setNom(Parametres.text(req, "nom"));
        taller.setPonent(Parametres.text(req, "ponent"));
        taller.setDia(Parametres.data(req, "dia"));
        taller.setPlaces(Parametres.enter(req, "places"));
        taller.setPreu(Parametres.decimal(req, "preu"));

        // 2. Les regles i el guardar són del model
        List<String> errors = tallerModel.afegeix(taller);

        // 3. Amb errors → FORWARD al formulari, amb els errors i el que l'usuari havia escrit.
        //    Ara el formulari és tot sol a la pàgina: ja no cal tornar a carregar la llista
        if (!errors.isEmpty()) {
            req.setAttribute("errors", errors);
            req.setAttribute("nouTaller", taller);
            req.getRequestDispatcher(VISTA).forward(req, resp);
            return;
        }

        // AMPLIACIÓ. Un atribut de la request no arribaria a la llista: el redirect fa que el navegador demani
        // una petició nova. La sessió sí que hi arriba. Aquí la sessió segur que existeix: ho ha mirat el filtre
        req.getSession().setAttribute("missatge", "Taller «" + taller.getNom() + "» creat correctament.");

        // 4. Guardat → REDIRECT. El navegador fa un GET /tallers nou, i F5 ja no torna a enviar el POST
        resp.sendRedirect(req.getContextPath() + "/tallers");
    }
}
