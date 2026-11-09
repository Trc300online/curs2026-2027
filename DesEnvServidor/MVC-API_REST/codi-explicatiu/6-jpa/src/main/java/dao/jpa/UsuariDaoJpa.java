package dao.jpa;

import dao.DaoException;
import dao.UsuariDao;
import jakarta.persistence.PersistenceException;
import model.Usuari;

public class UsuariDaoJpa implements UsuariDao {

    @Override
    public Usuari autentica(String nom, String contrasenya) {
        // JPQL: s'assembla a SQL, però parla de classes i camps, no de taules i columnes.
        // Amb «new» cream un Usuari només amb el que cal: així la contrasenya no arriba a la sessió
        String jpql = "SELECT new model.Usuari(u.nom, u.nomComplet, u.admin) FROM Usuari u "
                + "WHERE u.nom = :nom AND u.contrasenya = :contrasenya";

        // ── PROVA 4 ─ JPA no vos salva si enganxau text. Comentau el jpql de dalt i les dues línies setParameter, i
        //    descomentau aquesta. Compilau i entrau amb l'usuari  admin' OR 'a'='a  i qualsevol contrasenya. Sou l'administradora.
        // String jpql = "SELECT new model.Usuari(u.nom, u.nomComplet, u.admin) FROM Usuari u WHERE u.nom = '" + nom + "' AND u.contrasenya = '" + contrasenya + "'";

        try {
            return Persistencia.fabrica().callInTransaction(em ->
                    em.createQuery(jpql, Usuari.class)
                            .setParameter("nom", nom)
                            .setParameter("contrasenya", contrasenya)
                            .getSingleResultOrNull());
        } catch (PersistenceException e) {
            throw new DaoException("No s'ha pogut comprovar l'usuari", e);
        }
    }
}
