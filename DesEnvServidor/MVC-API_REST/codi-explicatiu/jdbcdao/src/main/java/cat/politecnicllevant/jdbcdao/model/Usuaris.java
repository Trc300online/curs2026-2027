
package cat.politecnicllevant.jdbcdao.model;

import java.util.HashMap;
import java.util.Map;

public class Usuaris {

    private static final Map<String, String> usuaris = new HashMap<>();

    static {
        usuaris.put("Toni Riera", "123456789");
        usuaris.put("Joan Garcia", "987654321");
    }

    public static boolean autentica(String nom, String contrasenya) {

        return nom != null && contrasenya != null && contrasenya.equals(usuaris.get(nom));
    }
}
