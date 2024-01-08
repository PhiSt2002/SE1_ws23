package org.hbrs.se1.ws23.uebung8.Travel;

/**
 * @author pstrun2s
 */

public class TravelInfo {
    private String destination;
    private String details;

    public TravelInfo(ExternalTravelInfo externalInfo) {
        // Wandeln Sie hier die Daten aus ExternalTravelInfo in ein für Ihre Anwendung geeignetes Format um.
        this.destination = externalInfo.getDestination();
        this.details = externalInfo.getDetails();

    }

    // Getter und Setter Methoden für destination und details
    public String getDestination() {
        return destination;

    }

    public void setDestination(String destination) {
        this.destination = destination;

    }

    public String getDetails() {
        return details;

    }

    public void setDetails(String details) {
        this.details = details;

    }

}

