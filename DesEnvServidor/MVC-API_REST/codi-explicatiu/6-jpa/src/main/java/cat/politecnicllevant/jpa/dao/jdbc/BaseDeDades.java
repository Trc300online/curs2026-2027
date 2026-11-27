package cat.politecnicllevant.jpa.dao.jdbc;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.SQLException;

final class BaseDeDades {

    private static final MysqlDataSource FONT = new MysqlDataSource();

    static {

        FONT.setUrl("jdbc:mysql://mysql:3306/tallers");
        FONT.setUser("dwes");
        FONT.setPassword("dwes");
    }

    static Connection connexio() throws SQLException {
        return FONT.getConnection();
    }
}
