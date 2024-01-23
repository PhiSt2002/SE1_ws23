package org.hbrs.se1.ws23.uebung9.Document;

/**
 * @author pstrun2s
 */

public class TextDocument extends CoreDocument {

    private String content;
    private EncodingEnums encoding;

    public TextDocument(int id, String content, EncodingEnums encoding) {
        super(id);
        this.content = content;
        this.encoding = encoding;

    }

    @Override
    public int size() {
        int bytesPerChar;
        switch (encoding) {
            case UTF8:
                bytesPerChar = 1;
                break;
            case UTF16:
                bytesPerChar = 2;
                break;
            case UTF32:
                bytesPerChar = 4;
                break;
            default:
                bytesPerChar = 1;

        }

        return content.length() * bytesPerChar;

    }

}
