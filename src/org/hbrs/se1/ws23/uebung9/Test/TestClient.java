package org.hbrs.se1.ws23.uebung9.Test;

/**
 * @author pstrun2s
 */

import org.hbrs.se1.ws23.uebung9.Document.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestClient {

    private ComplexDocument complexDoc;
    private TextDocument textDoc;
    private GraphicDocument graphicDoc;

    @BeforeEach
    public void setUp() {
        complexDoc = new ComplexDocument(1);
        textDoc = new TextDocument(2,"Hello World", EncodingEnums.UTF8);
        graphicDoc = new GraphicDocument(3,"http://example.com/image.jpg");

        complexDoc.addDocument(textDoc);
        complexDoc.addDocument(graphicDoc);
    }

    @Test
    public void testDocumentSizeAfterAdding() {
        int expectedSize = textDoc.size() + graphicDoc.size();
        assertEquals(expectedSize, complexDoc.size());
    }

    @Test
    public void testDocumentSizeAfterRemoving() {
        complexDoc.removeDocument(textDoc);

        assertEquals(graphicDoc.size(), complexDoc.size());
    }

}
