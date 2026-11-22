package cat.politecnicllevant.jdbcdao.dao.jdbc;

import cat.politecnicllevant.jdbcdao.dao.TallerDAO;
import cat.politecnicllevant.jdbcdao.model.Taller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TallerDaoJdbcImpl implements TallerDAO {
    @Override
    public List<Taller> getAll() {

        String sql = "SELECT * from taller";

        try(
                Connection con = BaseDeDades.connexio();
                PreparedStatement query = con.prepareStatement(sql);
                ResultSet files = query.executeQuery();
        ) {

            List<Taller> tallers = new ArrayList<>();
            while (files.next()) {
                Taller taller = new Taller();

                taller.setId(files.getString("id"));
                taller.setNom(files.getString("nom"));
                taller.setPonent(files.getString("ponent"));
                taller.setDia(files.getObject("dia", LocalDate.class));
                taller.setPlaces(files.getInt("places"));
                taller.setPreu(files.getBigDecimal("preu"));

                tallers.add(taller);
            }
            return tallers;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Taller getById(String id) {
        String sql = "SELECT * FROM taller WHERE id = ?";
        try (
                Connection con = BaseDeDades.connexio();
                PreparedStatement query = con.prepareStatement(sql)
        ) {
            query.setString(1, id);
            try (ResultSet files = query.executeQuery()) {
                if (!files.next()) {
                    return null;
                }
                Taller taller = new Taller();
                taller.setId(files.getString("id"));
                taller.setNom(files.getString("nom"));
                taller.setPonent(files.getString("ponent"));
                taller.setDia(files.getObject("dia", LocalDate.class));
                taller.setPlaces(files.getInt("places"));
                taller.setPreu(files.getBigDecimal("preu"));
                return taller;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insert(Taller taller) {
        String sql = "INSERT INTO taller (id, nom, ponent, dia, places, preu) VALUES (?, ?, ?, ?, ?, ?)";
        try (
                Connection con = BaseDeDades.connexio();
                PreparedStatement query = con.prepareStatement(sql)
        ) {
            query.setString(1, taller.getId());
            query.setString(2, taller.getNom());
            query.setString(3, taller.getPonent());
            query.setObject(4, taller.getDia());
            query.setInt(5, taller.getPlaces());
            query.setBigDecimal(6, taller.getPreu());
            query.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
