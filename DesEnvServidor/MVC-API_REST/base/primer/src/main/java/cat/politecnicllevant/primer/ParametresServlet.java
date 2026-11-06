package cat.politecnicllevant.primer;

import jakarta.servlet.ServletException;
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

        //String param = req.getParameter("id");
/*
        if (id >= 0 && id < Alumnes.size()) {
            writer.println("<li>" + Alumnes.get(id) + "</li>");
        } else {
            writer.println("<h1> L'index de alumne no existeix </h1>");
        }
        */
        /*versio correcta*/

       /* try {
            int id = Integer.parseInt(param);
            writer.println("<li>" + Alumnes.get(id) + "</li>");
        } catch (NumberFormatException nfe) {
            writer.println("<h1> L'index no es un valor valid</h1>");
        } catch (IndexOutOfBoundsException ioobe) {
            writer.println("<h1> L'index de alumne no existeix </h1>");
        }*/
    }

    List<List> llistaInscripcions = new ArrayList<>();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ArrayList<Object> inscripcio = new ArrayList();

        String nom = req.getParameter("nom");
        int edat = Integer.parseInt(req.getParameter("edat"));
        String estudis = req.getParameter("estudis");

        inscripcio.add(nom);
        inscripcio.add(edat);
        inscripcio.add(estudis);

        llistaInscripcions.add(inscripcio);

        resp.setContentType("text/html;cahrset=UTF-8");

        PrintWriter writer = resp.getWriter();

        for (int i = 0; i < llistaInscripcions.size(); i++) {
            writer.println("<li> nom: " + llistaInscripcions.get(i).get(0) +
                    ", edat: " + llistaInscripcions.get(i).get(1) +
                    ", estudis: " + llistaInscripcions.get(i).get(2) + "</li>");
        }

        writer.println("<a href='formulari.html'>");
        writer.println("<button type='button'>Tornar al formulari</button>");
        writer.println("</a>");
    }
}
