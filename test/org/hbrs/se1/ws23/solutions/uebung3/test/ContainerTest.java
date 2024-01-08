package org.hbrs.se1.ws23.solutions.uebung3.test;

import org.hbrs.se1.ws23.solutions.uebung3.*;
import org.hbrs.se1.ws23.solutions.uebung3.persistence.PersistenceException;
import org.hbrs.se1.ws23.solutions.uebung3.persistence.PersistenceStrategyMongoDB;
import org.hbrs.se1.ws23.solutions.uebung3.persistence.PersistenceStrategyStream;
import org.hbrs.se1.ws23.solutions.uebung3.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ContainerTest {

    private Container container;

    @BeforeEach
    void setUp() {
        container = Container.getInstance();
    }

    @Test
    void testNoStrategeySet() {
        try {
            container.setPersistenceStrategie(null);
            container.store();

        } catch (PersistenceException e) {
            System.out.println("Message: " + e.getMessage() );
            assertEquals( e.getMessage() , "Strategy not initialized" );
            assertEquals( PersistenceException.ExceptionType.NoStrategyIsSet ,
                    e.getExceptionTypeType()  );
        }
    }

    @Test
    void testMongoDBNotImplementedOldFashioned() {
        try {
            container.setPersistenceStrategie( new PersistenceStrategyMongoDB<Member>() );
            container.store();
        } catch (PersistenceException e) {
            assertEquals( e.getMessage() , "Not implemented!" );
            assertEquals(  e.getExceptionTypeType() , PersistenceException.ExceptionType.ImplementationNotAvailable );
        }
    }

    @Test
    void testMongoDBNotImplementedHipSolution() {
        container.setPersistenceStrategie( new PersistenceStrategyMongoDB<Member>() );
        PersistenceException e = assertThrows( PersistenceException.class , () -> container.store() );
        assertEquals( e.getMessage() , "Not implemented!"  );
        assertEquals(  e.getExceptionTypeType() , PersistenceException.
                ExceptionType.ImplementationNotAvailable );
    }

    @Test
    void testWrongLocationOfFile() {
        try {
            PersistenceStrategyStream<Member> strat = new PersistenceStrategyStream<Member>();

            // FileStreams do not like directories, so try this out ;-)
            strat.setLOCATION("/Users/saschaalda/tmp");
            container.setPersistenceStrategie( strat );
            container.store();

        } catch (PersistenceException e) {
            System.out.println("Message: " + e.getMessage() );
            assertEquals( e.getMessage() , "Error in opening the connection, File could not be found" );
            assertEquals(  PersistenceException.ExceptionType.ConnectionNotAvailable  ,
                    e.getExceptionTypeType() ) ;
        }
    }

    @Test
    void testStoreDeleteAndLoad() {
        try {
            container.setPersistenceStrategie( new PersistenceStrategyStream<Member>() );
            container.addMember(new MemberKonkret(1));

            assertEquals( 1 , container.size() );
            container.store();

            container.deleteMember(1);
            assertEquals(0 , container.size() );

            container.load();
            assertEquals( 1 , container.size() );

        } catch (PersistenceException | ContainerException e) {
            System.out.println("Message: " + e.getMessage() );
        }
    }

}