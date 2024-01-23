package org.hbrs.se1.ws23.uebung9.Document;

/**
 * @author pstrun2s
 */

public class GraphicDocument extends CoreDocument {

    private String url;

    public GraphicDocument(int id, String url) {
        super(id);
        this.url = url;

    }

    @Override
    public int size() {
        return 1200;

    }

}
