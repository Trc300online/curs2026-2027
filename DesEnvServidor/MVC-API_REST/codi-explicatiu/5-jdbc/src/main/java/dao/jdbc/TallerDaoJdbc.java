package dao.jdbc;

import dao.DaoException;
import dao.TallerDao;
import model.Taller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TallerDaoJdbc implements TallerDao {

    @Override
    public List<Taller> tots() {
        String sql = "SELECT id, nom, places, inscrits FROM taller ORDER BY nom";

        try (Connection con = BaseDeDades.connexio();
             PreparedStatement sentencia = con.prepareStatement(sql);
             ResultSet fila = sentencia.executeQuery()) {
            List<Taller> tallers = new ArrayList<>();
            while (fila.next()) {       // next() avança a la fila següent, i torna false si no n'hi ha més
                tallers.add(llegeix(fila));
            }
            return tallers;
        } catch (SQLException e) {
            throw new DaoException("No s'han pogut llegir els tallers", e);
        }
    }

    @Override
    public Taller cerca(String id) {
        String sql = "SELECT id, nom, places, inscrits FROM taller WHERE id = ?";

        try (Connection con = BaseDeDades.connexio();
             PreparedStatement sentencia = con.prepareStatement(sql)) {
            sentencia.setString(1, id);
            try (ResultSet fila = sentencia.executeQuery()) {
                return fila.next() ? llegeix(fila) : null;
            }
        } catch (SQLException e) {
            throw new DaoException("No s'ha pogut llegir el taller", e);
        }
    }

    /** D'una fila a un objecte, columna a columna. Això és el que JPA farà per nosaltres a la passa 6. */
    private Taller llegeix(ResultSet fila) throws SQLException {
        Taller taller = new Taller();
        taller.setId(fila.getString("id"));
        taller.setNom(fila.getString("nom"));
        taller.setPlaces(fila.getInt("places"));
        taller.setInscrits(fila.getInt("inscrits"));
        return taller;
    }
}
