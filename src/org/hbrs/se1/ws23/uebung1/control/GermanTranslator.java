package org.hbrs.se1.ws23.uebung1.control;

public class GermanTranslator implements Translator {

	public String date = "Okt/2023"; // Default-Wert

	/**
	 * Methode zur Übersetzung einer Zahl in eine String-Repraesentation
	 */
	public String translateNumber( int number ) throws IllegalArgumentException {
		String[] strings = {"eins", "zwei", "drei", "vier", "fünf", "sechs", "sieben", "acht", "neun", "zehn"};
		if (number < 1 || number > 10) {
			throw new IllegalArgumentException("Übersetzung der Zahl " + number + " nicht möglich (" + version + ")");

		}

		return strings[number - 1];

	}

	/**
	 * Objektmethode der Klasse GermanTranslator zur Ausgabe einer Info.
	 */
	public void printInfo(){
		System.out.println( "GermanTranslator v" + version + ", erzeugt am " + this.date );

	}

	/**
	 * Setzen des Datums, wann der Uebersetzer erzeugt wurde (Format: Monat/Jahr (Beispiel: "Okt/2022"))
	 * Das Datum sollte system-intern durch eine Control-Klasse gesetzt werden und nicht von externen View-Klassen
	 */

	public void setDate( String date ) {
		this.date = date;

	}

}
