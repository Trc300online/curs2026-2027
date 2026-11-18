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
        return null;
    }
}
