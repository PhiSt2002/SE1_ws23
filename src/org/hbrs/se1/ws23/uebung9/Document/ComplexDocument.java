package org.hbrs.se1.ws23.uebung9.Document;

/**
 * @author pstrun2s
 */

import java.util.ArrayList;
import java.util.List;

public class ComplexDocument extends DocImplementation {

    private List<Document> documents = new ArrayList<>();

    public ComplexDocument(int id) {
        super(id);

    }

    public void addDocument(Document newDoc) {
        documents.add(newDoc);

    }

    public void removeDocument(Document delteDoc) {
        documents.remove(delteDoc);

    }

    @Override
    public int size() {
        int totalSize = 0;
        for (Document doc : documents) {
            if (doc instanceof CoreDocument) {
                CoreDocument coreDoc = (CoreDocument) doc;
                totalSize += coreDoc.size();

            }

        }

        return totalSize;

    }

}
