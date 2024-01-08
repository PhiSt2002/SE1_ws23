package org.hbrs.se1.ws23.uebung8.Travel;

/**
 * @author pstrun2s
 */

public class TravelService {
    private ITravelProvider travelProvider;

    public TravelService(ITravelProvider provider) {
        this.travelProvider = provider;

    }

    public void processTravelRequest(String destination) {
        TravelInfo info = travelProvider.getTravelInfo(destination);
        // Verarbeitet die Reiseinformationen

    }

}
