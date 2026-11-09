package servletjsp;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Forward i redirect, un al costat de l'altre. Tots dos acaben a la mateixa JSP, però:
 *   /anar?com=forward   → una sola petició: la JSP encara veu l'atribut, i la URL no canvia
 *   /anar?com=redirect  → dues peticions: l'atribut s'ha perdut amb la primera, i la URL canvia a /desti
 */
@WebServlet("/anar")
public class AnarServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("missatge", "Hola des d'AnarServlet");

        if ("redirect".equals(req.getParameter("com"))) {
            resp.sendRedirect(req.getContextPath() + "/desti");
        } else {
            req.getRequestDispatcher("/WEB-INF/vistes/desti.jsp").forward(req, resp);
        }
    }
}
