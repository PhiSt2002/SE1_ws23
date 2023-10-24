package org.hbrs.se1.ws23.uebung2.Data;

import org.hbrs.se1.ws23.uebung2.Exception.ContainerException;
import org.hbrs.se1.ws23.uebung2.Interface.Member;

import java.util.ArrayList;
import java.util.Objects;


/**
 * @author pstrun2s
 */

public class Container {
    private final ArrayList<Member> storage;

    public Container(){
        storage = new ArrayList<>();
    }

    public void addMember(Member member) throws ContainerException {
        if(member == null){
            throw new ContainerException("Das Member-Objekt ist null und kann daher nicht hinzugefügt werden");

        }

        for (Member currentMember: storage) {
            if (Objects.equals(currentMember.getID(), member.getID())){
                throw new ContainerException("Das Member-Objekt mit der ID " + member.getID() + " ist bereits vorhanden.");

            }

        }

        storage.add(member);

    }

    public String deleteMember(int memberID){
        int counter = 0;
        for (Member currentMember: storage) {
            if (Objects.equals(currentMember.getID(), memberID)){
                storage.remove(counter);
                return "Member-Objekt mit der ID " + memberID + " wurde erfolgreich gelöscht.";

            }

        }

        return "Member-Objekt mit der ID" + memberID + " existiert nicht im Container und konnte somit nicht gelöscht werden.";

    }

    public void dump(){
        for(Member currentMember: storage){
            System.out.println(currentMember.toString());

        }

    }

    public int size(){
        return storage.size();

    }

}
