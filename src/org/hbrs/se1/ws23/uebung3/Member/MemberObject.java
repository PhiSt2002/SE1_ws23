package org.hbrs.se1.ws23.uebung3.Member;

import org.hbrs.se1.ws23.uebung3.Member.Interface.Member;

import java.io.Serializable;

/**
 * @author pstrun2s
 */

public class MemberObject implements Member, Serializable {
    private final int ID;

    public MemberObject(int ID) {
        this.ID = ID;

    }

    @Override
    public Integer getID() {
        return ID;

    }

    @Override
    public String toString() {
        return "Member (ID = " + ID + " )";

    }

}
