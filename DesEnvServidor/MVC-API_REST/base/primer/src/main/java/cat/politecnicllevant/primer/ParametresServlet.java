package cat.politecnicllevant.primer;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ParametresServlet extends HttpServlet {

    public List<String> Alumnes = new ArrayList<>();

    @Override
    public void init() throws ServletException {
        Alumnes.add("Lluc Enrique Barceló");
        Alumnes.add("Paulo Escanellas Otero");
        Alumnes.add("Yassine Ikken Ikkene");
        Alumnes.add("Salvador Lobaton Garcia");
        Alumnes.add("Miquel Àngel Melis Binimelis");
        Alumnes.add("Aina Muelas Rigo");
        Alumnes.add("Miguel Alejandro Paredes Panta");
        Alumnes.add("Pere Miquel Pont Riera");
        Alumnes.add("Miguel Angel Rodrigo Hervas");
        Alumnes.add("Saul Salvador Gomes");
        Alumnes.add("Joel Sastre Toledo");
        Alumnes.add("Antoni Sureda Riera");
        Alumnes.add("Toni Riera Colomar");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;cahrset=UTF-8");

        PrintWriter writer = resp.getWriter();

        int id = Integer.parseInt(req.getParameter("id"));

        if (id >= 0 && id < Alumnes.size()) {
            writer.println("<li>" + Alumnes.get(id) + "</li>");
        } else {
            writer.println("<h1> L'index de alumne no existeix </h1>");
        }
    }
}
