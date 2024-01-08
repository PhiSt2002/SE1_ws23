package org.hbrs.se1.ws23.uebung8.Travel;

/**
 * @author pstrun2s
 */

public class TravelProviderAdapter implements ITravelProvider {
    private ExternalTravelProvider externalProvider;

    public TravelProviderAdapter(ExternalTravelProvider provider) {
        this.externalProvider = provider;

    }

    @Override
    public TravelInfo getTravelInfo(String destination) {
        ExternalTravelInfo externalInfo = externalProvider.getTravelInformation(destination);
        // Wandelt ExternalTravelInfo in TravelInfo um
        return new TravelInfo(externalInfo);

    }

}
