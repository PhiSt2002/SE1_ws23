package org.hbrs.se1.ws23.uebung3.test;

/**
 * @author pstrun2s
 */

import org.hbrs.se1.ws23.uebung3.Client.Client;
import org.hbrs.se1.ws23.uebung3.Exception.ContainerException;
import org.hbrs.se1.ws23.uebung3.Member.Interface.Member;
import org.hbrs.se1.ws23.uebung3.Structure.Container;
import org.hbrs.se1.ws23.uebung3.Member.MemberObject;

import org.hbrs.se1.ws23.uebung3.Main;

import org.hbrs.se1.ws23.uebung3.persistence.PersistenceException;
import org.hbrs.se1.ws23.uebung3.persistence.PersistenceStrategyMongoDB;
import org.hbrs.se1.ws23.uebung3.persistence.PersistenceStrategyStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class ContainerTest {

    private final Container container = Container.getInstance();
    private final Member[] members = {new MemberObject(1), new MemberObject(2), new MemberObject(3)};
    private final Client client = new Client();

    @BeforeEach
    void setup(){
        for (Member member: members) {
            container.deleteMember(member.getID());

        }

    }

    @Test
    void testClientFuncunality(){
        assertEquals(0, container.size());
        client.addMember(1);
        assertEquals(1,container.size());
        client.addMember(1);
        assertEquals(1, container.size());
        client.deleteMember(2);
        assertEquals(1, container.size());
        client.dump();
        assertEquals(1,container.size());
        client.deleteMember(1);
        assertEquals(0,container.size());
        client.dump();
        assertEquals(0,container.size());
        client.addMember(1);
        client.addMember(2);
        client.addMember(3);
        assertEquals(3, container.size());
        client.dump();

    }

    @Test
    void testeOhneStrategie(){
        try {
            Main.setStrategy(null);
            container.addMember(members[0]);
            container.addMember(members[1]);
            container.addMember(members[2]);
            assertThrows(PersistenceException.class, container::store);

        } catch (ContainerException e) {
            e.printStackTrace();

        }

    }

    @Test
    void testeMitMongoDBStrategie(){
        try{
            Main.setStrategy(new PersistenceStrategyMongoDB<>());
            container.addMember(members[0]);
            container.addMember(members[1]);
            container.addMember(members[2]);
            assertThrows(UnsupportedOperationException.class, container::store);

        } catch (ContainerException e){
            e.printStackTrace();

        }

    }

    @Test
    void testeMitFehlerhafterLocation(){
        PersistenceStrategyStream<Member> persistenceStrategy = new PersistenceStrategyStream<>();
        persistenceStrategy.setLocation("/project/test/objects.ser");
        Main.setStrategy(persistenceStrategy);

        try{
            container.addMember(members[0]);
            container.addMember(members[1]);
            container.addMember(members[2]);
            assertThrows(PersistenceException.class, container::load);

        } catch (ContainerException e){
            e.printStackTrace();

        }

    }

    @Test
    void testeKorrektesSzenario(){
        Main.setStrategy(new PersistenceStrategyStream<>());

        try{
            container.addMember(members[0]);
            assertEquals(1, container.size());
            container.addMember(members[1]);
            container.addMember(members[2]);
            container.store();
            assertEquals(3, container.size());
            container.deleteMember(11);
            assertEquals(2, container.size());
            container.deleteMember(22);
            container.deleteMember(33);
            assertEquals(0, container.size());
            container.load();
            assertEquals(3, container.size());

        }catch (ContainerException | PersistenceException e){
            e.printStackTrace();

        }

    }

}

