package org.hbrs.se1.ws23.uebung2.test;

import org.hbrs.se1.ws23.uebung2.Data.Container;
import org.hbrs.se1.ws23.uebung2.Entities.ConcreteMember;
import org.hbrs.se1.ws23.uebung2.Exception.ContainerException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author pstrun2s
 */

public class ContainerTest {

    private Container container;
    private final ConcreteMember[] members = {new ConcreteMember(11), new ConcreteMember(22), new ConcreteMember(33)};

    @BeforeEach
    void setup(){
        container = new Container();
    }

    @AfterEach
    void teardown(){
        container = null;
    }

    @Test
    void testeInitisalisierung() {
        assertEquals(0, container.size());

    }

    @Test
    void testeUebergangVon0zu1zu2() throws ContainerException {
        container.addMember(new ConcreteMember(1));
        assertEquals(1, container.size());

        container.addMember(new ConcreteMember(2));
        assertEquals(2, container.size());
    }

    @Test
    void testeUebergangVon2zu1zu0() throws ContainerException {
        container.addMember(new ConcreteMember(1));
        container.addMember(new ConcreteMember(2));
        assertEquals(2, container.size());

        container.deleteMember(1);
        assertEquals(1, container.size());

        container.deleteMember(2);
        assertEquals(0, container.size());
    }

    @Test
    void testeUebergangVon1zu1MitSizeUndDump() throws ContainerException {
        container.addMember(new ConcreteMember(1));
        assertEquals(1, container.size());

        container.dump();  // Überprüfen Sie die Konsolenausgabe manuell
    }

    @Test
    void testeUebergangVon1zu1MitAddUndDelete() throws ContainerException {
        container.addMember(new ConcreteMember(1));
        assertEquals(1, container.size());

        container.deleteMember(1);
        assertEquals(0, container.size());

        container.addMember(new ConcreteMember(1));
        assertEquals(1, container.size());
    }

    @Test
    void testeEingabeVonNull() {
        assertThrows(NullPointerException.class, () -> {
            container.addMember(null);
        });
    }

}
