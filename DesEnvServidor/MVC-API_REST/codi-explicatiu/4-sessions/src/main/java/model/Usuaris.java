package model;

import java.util.Map;

/**
 * Els usuaris que poden entrar. A la passa 5 seran a una base de dades.
 * Contrasenyes en clar dins el codi: només per aprendre, mai en una aplicació de veres.
 */
public class Usuaris {

    private static final Map<String, String> CONTRASENYES = Map.of(
            "anna", "anna123",
            "marc", "marc123");

    /** Torna true si el nom existeix i la contrasenya és la seva. */
    public static boolean autentica(String nom, String contrasenya) {
        return nom != null && contrasenya != null && contrasenya.equals(CONTRASENYES.get(nom));
    }
}
