package util;

/**
 * Escapar vol dir canviar els caràcters especials de l'HTML per la seva entitat (&lt; en lloc de <).
 * Així, el text que arriba del client es mostra com a text i mai no s'interpreta com a codi HTML o JavaScript.
 * Quan arribin les JSP, això ho farà l'etiqueta <c:out> per nosaltres.
 */
public final class Html {

    private Html() {
    }

    public static String escapa(String text) {
        if (text == null) {
            return "";
        }
        return text.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;");
    }
}
