package cat.politecnicllevant.jdbcdao.model;

import cat.politecnicllevant.jdbcdao.dao.TallerDAO;
import cat.politecnicllevant.jdbcdao.dao.jdbc.TallerDaoJdbcImpl;
import cat.politecnicllevant.jdbcdao.dao.memory.TallerDAOMemoryImpl;

import java.util.List;
import java.util.ArrayList;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class TallerModel {

    private static final TallerModel INSTANCIA = new TallerModel();

    public static TallerModel instancia() {
        return INSTANCIA;
    }

    private TallerDAO tallerDAO = new TallerDaoJdbcImpl();

    public List<Taller> tallers() {
        return tallerDAO.getAll();
    }

    public Taller taller(String id) {
        return tallerDAO.getById(id);
    }

    public List<String> crea(String id, String nom, String ponent,
                             String diaText, String placesText, String preuText) {
        List<String> errorsConversio = new ArrayList<>();

        LocalDate dia = null;
        Integer places = null;
        BigDecimal preu = null;

        try {
            if (diaText != null && !diaText.isBlank()) {
                dia = LocalDate.parse(diaText);
            }
        } catch (DateTimeParseException e) {
            errorsConversio.add("El dia no té un format de data vàlid.");
        }

        try {
            if (placesText != null && !placesText.isBlank()) {
                places = Integer.valueOf(placesText);
            }
        } catch (NumberFormatException e) {
            errorsConversio.add("El nombre de places ha de ser un nombre enter.");
        }

        try {
            if (preuText != null && !preuText.isBlank()) {
                preu = new BigDecimal(preuText);
            }
        } catch (NumberFormatException e) {
            errorsConversio.add("El preu ha de ser un nombre decimal vàlid.");
        }

        Taller nouTaller = new Taller();
        nouTaller.setId(id);
        nouTaller.setNom(nom);
        nouTaller.setPonent(ponent);
        nouTaller.setDia(dia);
        nouTaller.setPlaces(places);
        nouTaller.setPreu(preu);

        List<String> errors = new ArrayList<>(errorsConversio);
        errors.addAll(nouTaller.valida());

        if (!errors.isEmpty()) {
            return errors;
        }

        if (tallerDAO.getById(id) != null) {
            errors.add("Ja existeix un taller amb aquest identificador.");
            return errors;
        }

        tallerDAO.insert(nouTaller);
        return errors;
    }
}
