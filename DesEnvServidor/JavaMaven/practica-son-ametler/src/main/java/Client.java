/**
 * Un client de l'agroturisme. Aquesta classe JA ESTÀ BEN ENCAPSULADA: una companya la va arreglar.
 * Feis-la servir de model per a Habitacio i Reserva.
 *
 * Tots els atributs són privats. El DNI és final i no té setter: no ha de canviar mai.
 * El nom i el correu es poden canviar, però només amb un setter que comprova el valor nou.
 */
public class Client {
    private final String dni;
    private String nom;
    private String email;

    public Client(String dni, String nom, String email) {
        if (dni == null || dni.length() != 9) {
            throw new IllegalArgumentException("El DNI ha de tenir 9 caràcters");
        }
        this.dni = dni;
        this.nom = textObligatori(nom, "nom");
        this.email = emailValid(email);
    }

    public String getDni() {
        return dni;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = textObligatori(nom, "nom");
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = emailValid(email);
    }

    // Mètodes privats auxiliars: el constructor i els setters fan servir la mateixa comprovació,
    // escrita una sola vegada.
    private static String textObligatori(String text, String camp) {
        if (text == null || text.isBlank()) {
            throw new IllegalArgumentException("El camp " + camp + " és obligatori");
        }
        return text;
    }

    private static String emailValid(String email) {
        textObligatori(email, "email");
        if (!email.contains("@")) {
            throw new IllegalArgumentException("El correu ha de contenir una @");
        }
        return email;
    }

    @Override
    public String toString() {
        return nom + " (" + dni + ")";
    }
}
