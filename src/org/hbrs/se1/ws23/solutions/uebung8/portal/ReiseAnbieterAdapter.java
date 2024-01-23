package org.hbrs.se1.ws23.solutions.uebung8.portal;
import org.hbrs.se1.ws23.solutions.uebung8.reiseanbieter.*;

public class ReiseAnbieterAdapter implements ExternalHotelSucheInterface {
	
	private ReiseAnbieter system; // = new ... hier ausgelassen

	@Override
	public SuchErgebnis suche(SuchAuftrag auftrag) {
		QueryObject queryObject = this.transformIN(auftrag);
		QueryResult queryResult = system.executeQuery(queryObject );
		SuchErgebnis suchErgebnis = this.transformOUT( queryResult );
		return suchErgebnis;

	}
	
	private QueryObject transformIN( SuchAuftrag auftrag ) {
		// Transformation SuchAuftrag --> QueryObject
		return null;
	}
	
	private SuchErgebnis transformOUT( QueryResult result ) {
		// Transformation QueryResult --> SuchErgebnis
		return null;
	}



}
