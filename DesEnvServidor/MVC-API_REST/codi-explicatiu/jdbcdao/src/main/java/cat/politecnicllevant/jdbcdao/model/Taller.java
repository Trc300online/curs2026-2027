package cat.politecnicllevant.jdbcdao.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Part del model: un taller, i les regles que ha de complir tot sol. No sap res d'HTTP ni de MySQL.
 * Les regles que necessiten les dades guardades (el codi no es pot repetir) són a Jornades.
 *
 * És un JavaBean: constructor sense arguments, camps privats, getters i setters, i Serializable.
 * Així qualsevol eina el pot crear buit i omplir-lo sense conèixer la classe: EL a les JSP, el DAO
 * quan llegeix una fila, i més endavant JPA o Spring.
 *
 * places i preu són Integer i BigDecimal, no int i double: poden ser null mentre el formulari és mal omplert.
 */
public class Taller implements Serializable {

    private static final DateTimeFormatter FORMAT_DIA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final BigDecimal PREU_MAXIM = new BigDecimal("999.99");

    private String id;
    private String nom;
    private String ponent;
    private LocalDate dia;
    private Integer places;
    private BigDecimal preu;

    public Taller(String id, String nom, String ponent) {
        this.id = id;
        this.nom = nom;
        this.ponent = ponent;
    }

    public Taller(String id, String nom, String ponent, LocalDate dia, int places, BigDecimal preu) {
        this.id = id;
        this.nom = nom;
        this.ponent = ponent;
        this.dia = dia;
        this.places = places;
        this.preu = preu;
    }

    /**
     * Les regles del taller. Torna la llista d'errors: si és buida, el taller és correcte.
     * Són del model, no del Servlet: valen igual si el taller ve d'un formulari, d'un fitxer o d'una API.
     */
    public List<String> valida() {
        List<String> errors = new ArrayList<>();
        if (id == null || !id.matches("[a-z0-9-]{1,20}")) {
            errors.add("El codi ha de tenir entre 1 i 20 lletres minúscules, xifres o guions.");
        }
        if (nom == null || nom.isBlank() || nom.length() > 100) {
            errors.add("Falta el nom, o és massa llarg.");
        }
        if (ponent == null || ponent.isBlank() || ponent.length() > 100) {
            errors.add("Falta el ponent, o és massa llarg.");
        }
        if (dia == null) {
            errors.add("Falta el dia, o no és una data.");
        }
        if (places == null || places < 1 || places > 500) {
            errors.add("Les places han de ser un nombre entre 1 i 500.");
        }
        if (preu == null || preu.signum() < 0 || preu.compareTo(PREU_MAXIM) > 0 || preu.scale() > 2) {
            errors.add("El preu ha de ser entre 0 i 999,99 €, amb dos decimals com a màxim.");
        }
        return errors;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPonent() {
        return ponent;
    }

    public void setPonent(String ponent) {
        this.ponent = ponent;
    }

    public LocalDate getDia() {
        return dia;
    }

    public void setDia(LocalDate dia) {
        this.dia = dia;
    }

    public Integer getPlaces() {
        return places;
    }

    public void setPlaces(Integer places) {
        this.places = places;
    }

    public BigDecimal getPreu() {
        return preu;
    }

    public void setPreu(BigDecimal preu) {
        this.preu = preu;
    }

    // Propietats calculades: per a EL són propietats com les altres (${taller.diaText}, ${taller.gratuit}),
    // però no tenen camp ni columna

    /** El dia a la nostra manera: 24/11/2026. fmt:formatDate de JSTL no sap formatar un LocalDate. */
    public String getDiaText() {
        return dia == null ? "" : dia.format(FORMAT_DIA);
    }

    public boolean isGratuit() {
        return preu != null && preu.signum() == 0;
    }
}
