package dao.jdbc;

import dao.DaoException;
import dao.UsuariDao;
import model.Usuari;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuariDaoJdbc implements UsuariDao {

    @Override
    public Usuari autentica(String nom, String contrasenya) {
        // Els ? són forats: el controlador hi posa els valors com a dades, mai com a SQL
        String sql = "SELECT nom, nom_complet, admin FROM usuari WHERE nom = ? AND contrasenya = ?";

        // ── PROVA 2 ─ Comentau el sql de dalt i les dues línies setString, i descomentau aquesta. Compilau i entrau amb
        //    l'usuari  admin' --   (acaba amb un espai) i qualsevol contrasenya. Ja sou l'administradora! El -- comenta la resta.
        // String sql = "SELECT nom, nom_complet, admin FROM usuari WHERE nom = '" + nom + "' AND contrasenya = '" + contrasenya + "'";

        // try-with-resources: tot el que s'obre aquí es tanca sol, en acabar o si hi ha una excepció
        try (Connection con = BaseDeDades.connexio();
             PreparedStatement sentencia = con.prepareStatement(sql)) {
            sentencia.setString(1, nom);
            sentencia.setString(2, contrasenya);

            try (ResultSet fila = sentencia.executeQuery()) {
                if (!fila.next()) {
                    return null;
                }
                return new Usuari(fila.getString("nom"), fila.getString("nom_complet"), fila.getBoolean("admin"));
            }
        } catch (SQLException e) {
            throw new DaoException("No s'ha pogut comprovar l'usuari", e);
        }
    }
}
