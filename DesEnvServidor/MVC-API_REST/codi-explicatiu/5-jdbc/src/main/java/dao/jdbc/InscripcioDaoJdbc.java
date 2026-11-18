package dao.jdbc;

import dao.DaoException;
import dao.InscripcioDao;
import model.Inscripcio;
import model.Taller;
import model.Usuari;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InscripcioDaoJdbc implements InscripcioDao {

    private static final String SELECT = """
            SELECT i.id, u.nom AS usuari, u.nom_complet, u.admin,
                   t.id AS taller, t.nom AS nom_taller, t.places, t.inscrits
            FROM inscripcio i
            JOIN usuari u ON u.nom = i.usuari
            JOIN taller t ON t.id = i.taller
            """;

    @Override
    public List<Inscripcio> totes() {
        try (Connection con = BaseDeDades.connexio();
             PreparedStatement sentencia = con.prepareStatement(SELECT + " ORDER BY i.id");
             ResultSet fila = sentencia.executeQuery()) {
            return llegeixTotes(fila);
        } catch (SQLException e) {
            throw new DaoException("No s'han pogut llegir les inscripcions", e);
        }
    }

    @Override
    public List<Inscripcio> deUsuari(Usuari usuari) {
        try (Connection con = BaseDeDades.connexio();
             PreparedStatement sentencia = con.prepareStatement(SELECT + " WHERE i.usuari = ? ORDER BY i.id")) {
            sentencia.setString(1, usuari.getNom());
            try (ResultSet fila = sentencia.executeQuery()) {
                return llegeixTotes(fila);
            }
        } catch (SQLException e) {
            throw new DaoException("No s'han pogut llegir les inscripcions", e);
        }
    }

    /**
     * Dues escriptures que han d'anar juntes: o es fan totes dues, o cap. Això és una transacció.
     */
    @Override
    public boolean inscriu(Usuari usuari, Taller taller) {
        try (Connection con = BaseDeDades.connexio()) {
            // ── PROVA 3 ─ Descomentau el throw de més avall i compilau. Inscriviu-vos: 500, però a la base de dades no ha
            //    canviat res. Ara comentau també aquest setAutoCommit: 500, i el taller té un inscrit més sense cap inscripció.
            //    Una plaça perduda per sempre. Deixau les dades bé amb l'script de sql/jornades.sql.
            con.setAutoCommit(false);       // a partir d'aquí, res no és definitiu fins al commit
            try {
                // La comprovació és dins l'UPDATE: si dues persones arriben alhora, MySQL n'atén una i després l'altra
                String ocupa = "UPDATE taller SET inscrits = inscrits + 1 WHERE id = ? AND inscrits < places";
                try (PreparedStatement sentencia = con.prepareStatement(ocupa)) {
                    sentencia.setString(1, taller.getId());
                    if (sentencia.executeUpdate() == 0) {       // cap fila canviada: ja no quedaven places
                        con.rollback();
                        return false;
                    }
                }

                // if (true) throw new SQLException("Error provocat per la PROVA 3");

                String guarda = "INSERT INTO inscripcio (usuari, taller) VALUES (?, ?)";
                try (PreparedStatement sentencia = con.prepareStatement(guarda)) {
                    sentencia.setString(1, usuari.getNom());
                    sentencia.setString(2, taller.getId());
                    sentencia.executeUpdate();
                }

                con.commit();               // ara sí: tot queda guardat de cop
                return true;
            } catch (SQLException e) {
                con.rollback();             // alguna cosa ha fallat: tot torna a com era abans
                throw e;
            }
        } catch (SQLException e) {
            throw new DaoException("No s'ha pogut fer la inscripció", e);
        }
    }

    @Override
    public boolean anulla(int id, Usuari usuari) {
        try (Connection con = BaseDeDades.connexio()) {
            con.setAutoCommit(false);
            try {
                String idTaller = null;
                // ── PROVA 4 ─ Llevau «AND usuari = ?» i el setString(2, ...) i compilau. Amb anna, canviau a F12 el camp ocult
                //    id pel d'una inscripció d'en marc. S'esborra. Els filtres no veuen de qui són les dades: és cosa d'aquest SQL.
                String cerca = "SELECT taller FROM inscripcio WHERE id = ? AND usuari = ?";
                try (PreparedStatement sentencia = con.prepareStatement(cerca)) {
                    sentencia.setInt(1, id);
                    sentencia.setString(2, usuari.getNom());
                    try (ResultSet fila = sentencia.executeQuery()) {
                        if (fila.next()) {
                            idTaller = fila.getString("taller");
                        }
                    }
                }
                if (idTaller == null) {
                    con.rollback();
                    return false;
                }

                try (PreparedStatement sentencia = con.prepareStatement("DELETE FROM inscripcio WHERE id = ?")) {
                    sentencia.setInt(1, id);
                    sentencia.executeUpdate();
                }
                try (PreparedStatement sentencia = con.prepareStatement("UPDATE taller SET inscrits = inscrits - 1 WHERE id = ?")) {
                    sentencia.setString(1, idTaller);
                    sentencia.executeUpdate();
                }

                con.commit();
                return true;
            } catch (SQLException e) {
                con.rollback();
                throw e;
            }
        } catch (SQLException e) {
            throw new DaoException("No s'ha pogut anul·lar la inscripció", e);
        }
    }

    private List<Inscripcio> llegeixTotes(ResultSet fila) throws SQLException {
        List<Inscripcio> inscripcions = new ArrayList<>();
        while (fila.next()) {
            inscripcions.add(llegeix(fila));
        }
        return inscripcions;
    }

    /** Una fila del JOIN dona tres objectes. A mà: la passa 6 ho estalviarà. */
    private Inscripcio llegeix(ResultSet fila) throws SQLException {
        Usuari usuari = new Usuari(fila.getString("usuari"), fila.getString("nom_complet"), fila.getBoolean("admin"));

        Taller taller = new Taller(fila.getString("taller"), fila.getString("nom_taller"), fila.getInt("places"));
        taller.setInscrits(fila.getInt("inscrits"));

        Inscripcio inscripcio = new Inscripcio();
        inscripcio.setId(fila.getInt("id"));
        inscripcio.setUsuari(usuari);
        inscripcio.setTaller(taller);
        return inscripcio;
    }
}
