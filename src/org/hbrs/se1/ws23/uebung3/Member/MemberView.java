package org.hbrs.se1.ws23.uebung3.Member;

/**
 * @author pstrun2s
 */

import org.hbrs.se1.ws23.uebung3.Member.Interface.Member;

import java.util.List;

public class MemberView {
    public void dump(List<Member> members){
        for (Member member: members) {
            System.out.println(member.toString());

        }

    }

}
