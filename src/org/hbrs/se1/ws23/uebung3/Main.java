package org.hbrs.se1.ws23.uebung3;

import org.hbrs.se1.ws23.uebung3.Member.Interface.Member;
import org.hbrs.se1.ws23.uebung3.Structure.Container;
import org.hbrs.se1.ws23.uebung3.persistence.PersistenceStrategy;

/**
 * @author pstrun2s
 */

public class Main {

    public static void setStrategy(PersistenceStrategy<Member> persistenceStrategy){
        Container.getInstance().setStrategy(persistenceStrategy);

    }

}
