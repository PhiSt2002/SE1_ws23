package org.hbrs.se1.ws23.uebung3.Structure;

/**
 * @author pstrun2s
 */

import org.hbrs.se1.ws23.uebung3.Member.Interface.Member;
import org.hbrs.se1.ws23.uebung3.Exception.ContainerException;
import org.hbrs.se1.ws23.uebung3.persistence.PersistenceException;
import org.hbrs.se1.ws23.uebung3.persistence.PersistenceStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Container {
    public final static Container instance = new Container();
    private List<Member> memberStorage = new ArrayList<>();

    private PersistenceStrategy<Member> strategy;

    private Container() {

    }

    public static Container getInstance(){
        return instance;

    }
    public void addMember(Member member) throws ContainerException {
        if(member == null){
            throw new ContainerException("Das Member-Objekt ist null und kann daher nicht hizugefügt werden");

        }

        for (Member currentMember: memberStorage) {
            if (Objects.equals(currentMember.getID(), member.getID())){
                throw new ContainerException("Das Member-Objekt mit der ID "+ member.getID() + " ist bereits vorhanden.");

            }

        }

        memberStorage.add(member);

    }

    public String deleteMember(int memberID){
        int counter = 0;
        for (Member currentMember: memberStorage) {
            if (Objects.equals(currentMember.getID(), memberID)){
                memberStorage.remove(counter);

                return "Member-Objekt mit der ID " + memberID + " wurde erfolgreich gelöscht.";

            }

        }

        return "Member-Objekt mit der ID" + memberID + " existiert nicht im Container und konnte somit nicht gelöscht werden.";

    }

    //Antwort an die Frage "Warum ist eine Exception sinnvoller als eine Ausgabe wie hier."
    //Es ist sinnvoller beziehungsweise die Nachteile einer solchen Handhabung sind, dass man die Fehlermeldung nicht Tracebacken kann.
    //Zudem müsste man für jede Methode einen String ausgeben, ob die Methode funktioniert hat oder nicht.
    //Eine Exception muss man zudem nur einmal schreiben und kann diese überall wo sie benötigt wird in der Methode aufrufen. Eine solche Fehlermeldung durch einen String muss in jeder Methode neu implementiert werden.

    public int size(){
        return memberStorage.size();

    }

    public void setStrategy(PersistenceStrategy<Member> strategy) {
        this.strategy = strategy;

    }

    public void store() throws PersistenceException {
        if(strategy == null){
            throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet, "No strategy is set.");

        }

        strategy.save(memberStorage);

    }

    public void load() throws PersistenceException {
        if(strategy == null){
            throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet, "No strategy is set.");

        }

        memberStorage = strategy.load();

    }

    public List<Member> getCurrentList(){
        return memberStorage;

    }

}
