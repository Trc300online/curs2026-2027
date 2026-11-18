package filtre;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Un filtre passa per totes les peticions del seu patró, abans i després del Servlet.
 * Aquest escriu una línia per a cada petició a la sortida de Tomcat. Mirau-ho amb: docker compose logs -f tomcat
 */
public class RegistreFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        long inici = System.currentTimeMillis();       // abans del Servlet

        chain.doFilter(req, resp);

        long ms = System.currentTimeMillis() - inici;  // després del Servlet: la resposta ja està feta
        System.out.println(req.getMethod() + " " + req.getRequestURI() + " → " + resp.getStatus() + " (" + ms + " ms)");
    }
}
