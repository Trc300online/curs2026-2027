package dao.jdbc;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/** D'on surten les connexions. Només aquesta classe sap on és la base de dades. */
final class BaseDeDades {

    private static final MysqlDataSource FONT = new MysqlDataSource();

    static {
        // «mysql» és el nom del servei a compose.yaml: dins Docker, els contenidors es troben pel nom
        FONT.setUrl("jdbc:mysql://mysql:3306/jornades");
        FONT.setUser("dwes");
        // ── PROVA 1 ─ Canviau la contrasenya, compilau i obriu /tallers: 500, «Access denied». Tornau-la a posar. Després
        //    aturau la base de dades amb docker compose stop mysql: 500, «Communications link failure».
        //    Tornau-la a engegar amb docker compose start mysql: tot torna a funcionar, sense tocar res més.
        FONT.setPassword("dwes");
    }

    private BaseDeDades() {
    }

    /**
     * Cada crida obre una connexió nova, i qui la demana l'ha de tancar. Per aprendre, va bé.
     * A un servidor real, un pool en reutilitza unes quantes, perquè obrir-ne és lent. Spring Boot ja en duu un: HikariCP.
     */
    static Connection connexio() throws SQLException {
        return FONT.getConnection();
    }
}
