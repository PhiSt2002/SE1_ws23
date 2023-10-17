package org.hbrs.se1.ws23.uebung1.test;

import org.hbrs.se1.ws23.uebung1.control.GermanTranslator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GermanTranslatorTest {

    @Test
    void aPositiveTestEins() {
        GermanTranslator translator = new GermanTranslator();
        String value = translator.translateNumber(1);
        assertEquals(value, "eins");

    }

    @Test
    void aPositiveTestFünf() {
        GermanTranslator translator = new GermanTranslator();
        String value = translator.translateNumber(5);
        assertEquals(value, "fünf");

    }



    // Negative Tests:

    @Test
    void aNegativeTestNull() {
        GermanTranslator translator = new GermanTranslator();
        String value = translator.translateNumber(0);
        assertEquals(value, "null");

    }

    @Test
    void aNegativeTestMinusFünf() {
        GermanTranslator translator = new GermanTranslator();
        String value = translator.translateNumber(-5);
        assertEquals(value, "-fünf");

    }

    @Test
    void aNegativeTestFünfzehn() {
        GermanTranslator translator = new GermanTranslator();
        String value = translator.translateNumber(15);
        assertEquals(value, "fünfzehn");

    }

}