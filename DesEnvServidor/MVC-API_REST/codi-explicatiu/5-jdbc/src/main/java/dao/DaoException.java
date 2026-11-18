package dao;

/**
 * Un error d'accés a les dades. La resta de l'aplicació no ha de saber res de SQL, ni tampoc de SQLException.
 * No és obligatori capturar-la: si no ho fa ningú, Tomcat mostra la pàgina d'error 500.
 */
public class DaoException extends RuntimeException {

    public DaoException(String missatge, Throwable causa) {
        super(missatge + ": " + causa.getMessage(), causa);
    }
}
