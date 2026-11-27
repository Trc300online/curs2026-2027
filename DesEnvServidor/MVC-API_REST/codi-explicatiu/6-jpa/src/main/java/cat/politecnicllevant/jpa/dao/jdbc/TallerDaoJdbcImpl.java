package cat.politecnicllevant.jpa.dao.jdbc;

import cat.politecnicllevant.jpa.dao.TallerDao;
import cat.politecnicllevant.jpa.model.Taller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TallerDaoJdbcImpl implements TallerDao {
    @Override
    public List<Taller> getAll() {

        String sql = "SELECT * from taller";

        try(
                Connection conn = BaseDeDades.connexio();
                PreparedStatement query = conn.prepareStatement(sql);
                ResultSet files = query.executeQuery();
                ) {

            List<Taller> tallers = new ArrayList<>();
            while (files.next()) {
                tallers.add(llegeix(files));
            }

            return tallers;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Taller getById(String id) {

        String sql = "SELECT * from taller WHERE id = ?";

        try (
                Connection conn = BaseDeDades.connexio();
                PreparedStatement query = conn.prepareStatement(sql);
                ) {

            query.setString(1, id);     // el codi ve de la URL: sempre amb ?, mai enganxat al text

            try (ResultSet files = query.executeQuery()) {
                // Per clau primària hi ha una fila o cap: if i no while
                return files.next() ? llegeix(files) : null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean insert(Taller taller) {

        String sql = "INSERT INTO taller (id, nom, ponent, dia, places, preu) VALUES (?, ?, ?, ?, ?, ?)";

        try (
                Connection conn = BaseDeDades.connexio();
                PreparedStatement query = conn.prepareStatement(sql);
                ) {

            query.setString(1, taller.getId());
            query.setString(2, taller.getNom());
            query.setString(3, taller.getPonent());
            query.setObject(4, taller.getDia());            // LocalDate → DATE
            query.setInt(5, taller.getPlaces());
            query.setBigDecimal(6, taller.getPreu());

            query.executeUpdate();      // executeUpdate i no executeQuery: no llegeix, canvia dades
            return true;

        } catch (SQLIntegrityConstraintViolationException e) {
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Copia la fila on és el ResultSet a un Taller nou. La fan servir getAll i getById: així les sis columnes
     * només es copien en un lloc.
     */
    private Taller llegeix(ResultSet files) throws SQLException {
        Taller taller = new Taller();

        taller.setId(files.getString("id"));
        taller.setNom(files.getString("nom"));
        taller.setPonent(files.getString("ponent"));
        taller.setDia(files.getObject("dia", LocalDate.class));
        taller.setPlaces(files.getInt("places"));
        taller.setPreu(files.getBigDecimal("preu"));

        return taller;
    }
}
