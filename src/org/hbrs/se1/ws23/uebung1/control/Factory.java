package org.hbrs.se1.ws23.uebung1.control;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author pstrun2s
 */

public class Factory {
    public static Translator createGermanTranslator() {
        Translator translator = new GermanTranslator();
        return translator;

    }

}
