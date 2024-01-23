package org.hbrs.se1.ws23.uebung9.Document;

/**
 * @author pstrun2s
 */

public enum EncodingEnums {
    UTF8("UTF-8"),
    UTF16("UTF-16"),
    UTF32("UTF-32");

    private final String encoding;

    EncodingEnums(String encoding) {
        this.encoding = encoding;

    }

    public String getType() {
        return encoding;

    }

}
