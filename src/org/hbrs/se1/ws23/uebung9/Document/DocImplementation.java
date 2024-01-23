package org.hbrs.se1.ws23.uebung9.Document;

/**
 * @author pstrun2s
 */

public abstract class DocImplementation implements Document {
    private int id;

    public DocImplementation(int id) {
        this.id = id;

    }

    public int get() {
        return id;

    }

    public void set(int newID) {
        id = newID;

    }

}
