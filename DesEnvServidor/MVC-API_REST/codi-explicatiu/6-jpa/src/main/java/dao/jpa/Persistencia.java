package dao.jpa;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collections;

/**
 * La fàbrica d'EntityManager llegeix persistence.xml i comprova les taules: és lenta, i n'hi ha prou amb una.
 * Es crea la primera vegada que algú la necessita: si MySQL encara no està a punt, la petició següent ho torna a provar.
 * Quan Tomcat atura l'aplicació, un ServletContextListener la tanca, i amb ella les connexions.
 */
@WebListener
public class Persistencia implements ServletContextListener {

    private static EntityManagerFactory fabrica;

    static synchronized EntityManagerFactory fabrica() {
        if (fabrica == null) {
            fabrica = Persistence.createEntityManagerFactory("jornades");
        }
        return fabrica;
    }

    @Override
    public synchronized void contextDestroyed(ServletContextEvent event) {
        if (fabrica != null) {
            fabrica.close();
        }
        // El controlador de MySQL es va registrar tot sol. El llevam perquè cada recàrrega no deixi res enrere
        try {
            for (Driver controlador : Collections.list(DriverManager.getDrivers())) {
                DriverManager.deregisterDriver(controlador);
            }
        } catch (SQLException e) {
            event.getServletContext().log("No s'ha pogut llevar el controlador", e);
        }
    }
}
