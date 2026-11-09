package model;

import java.util.List;

/**
 * Les dades de les jornades, de moment escrites aquí mateix. A la passa 3 aquest paper el farà el model de veres.
 */
public final class Jornades {

    private static final List<Taller> TALLERS = List.of(
            new Taller("git", "Git i GitHub sense por", "Aina Ferrà", 20, 14, 0),
            new Taller("docker", "Docker per a principiants", "Pau Mir", 15, 15, 12.5),
            new Taller("ia", "Programar amb assistents d'IA", "Marta Riera", 30, 28, 15),
            new Taller("xss", "Seguretat web: XSS & companyia", "Joan Serra", 12, 3, 5));

    private Jornades() {
    }

    public static List<Taller> tallers() {
        return TALLERS;
    }

    /** Torna el taller amb aquest id, o null si no n'hi ha cap. */
    public static Taller cerca(String id) {
        for (Taller taller : TALLERS) {
            if (taller.getId().equals(id)) {
                return taller;
            }
        }
        return null;
    }
}
