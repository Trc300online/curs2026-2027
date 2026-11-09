package dao;

import model.Usuari;

/** Què es pot fer amb els usuaris guardats. La interfície no diu com: ni SQL ni MySQL. */
public interface UsuariDao {

    /** Torna l'usuari amb aquest nom i aquesta contrasenya, o null si no n'hi ha cap. */
    Usuari autentica(String nom, String contrasenya);
}
