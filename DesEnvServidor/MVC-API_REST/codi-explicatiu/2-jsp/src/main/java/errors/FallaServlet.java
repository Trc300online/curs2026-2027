package errors;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/** Falla a posta, per veure la pàgina d'error del 500. */
@WebServlet("/falla")
public class FallaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        throw new IllegalStateException("Aquest Servlet sempre falla");
    }
}
