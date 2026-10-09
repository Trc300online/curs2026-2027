/**
 * Tot el que l'app guarda té un identificador. Cada classe tria de quin tipus és el seu:
 * les etapes fan servir un String («E1») i els refugis, un número de registre (201).
 */
public interface Identificable<ID> {
    ID getId();
}
