package org.hbrs.se1.ws23.uebung4.abgabe.Container;

import org.hbrs.se1.ws23.uebung4.abgabe.Exception.ContainerException;
import org.hbrs.se1.ws23.uebung4.abgabe.UserStory.UserStory;

import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @author pstrun2s
 */

public class Container {

    public static void main(String[] args) throws Exception {
        Container con = Container.getInstance();
        con.startEingabe();

    }

    private List<UserStory> liste = null;
    final static String LOCATION = "allStories.ser";
    private static Container instance = new Container();
    public static Container getInstance() {
        return instance;

    }

    public void startEingabe() throws ContainerException, Exception {
        String strInput = null;
        // Ausgabe eines Texts zur Begruessung
        System.out.println("""
                    Herzlich Willkommen im Tool zur Verwaltung von User-Stories, des Unternehmens WeissNix GmbH.
                    Nutze den Befehl \"help\" um alle Befehle aufgelistet zu bekommen.
                    """);

        // Initialisierung des Eingabe-View
        Scanner scanner = new Scanner( System.in );

        while (true) {

            System.out.print( "> "  );
            strInput = scanner.nextLine();

            // Extrahiert ein Array aus der Eingabe
            String[] strings = strInput.split(" ");
            switch (strings[0]) {
                case "help":
                    System.out.println("""
                            Hier ist eine Sammlung aller gültigen Befehle:
                            o help: Anzeige aller möglichen Befehle
                            o enter: Eingabe einer User Story, nur Ablage in den RAM-Speicher, also in die Klasse Container.
                            o store: Persistentes Abspeichern von User Stories aus einem Container-Objekt auf einen Datenträger.
                            o load: Laden von User Stories von einem Datenträger in ein Container- Objekt.
                            o dump: eine nach den berechneten Priorisierungen sortierte Ausgabe der User Stories inklusive aller eingegeben Angaben.
                            o search: Suche nach User Stories nach Projekten; Suchwort (= Bezeichnung des Projektes) wird dabei als Parameter übergeben.
                            o exit: Anwendung heruntergefahren.
                            """);
                    break;

                case "enter":
                    try {
                        int parameter = Integer.parseInt(strings[1]);
                        UserStory userStory = new UserStory();
                        userStory.setId(parameter);
                        this.addUserStory(userStory);
                        // Ohne store()
                        System.out.println("UserStory mit der ID (" + parameter + ") wurde im RAM abgelegt.");

                    } catch (NumberFormatException e) {
                        System.out.println("Der Parameter " + strings[1] + " ist kein gültiger Integer.");

                    }

                    break;

                case "store":
                    try {
                        int parameter = Integer.parseInt(strings[1]);
                        UserStory userStory = new UserStory();
                        userStory.setId(parameter);
                        this.addUserStory(userStory);
                        this.store();
                        System.out.println("UserStory mit der ID (" + parameter + ") wurde erstellt.");

                    } catch (NumberFormatException e) {
                        System.out.println("Der Parameter " + strings[1] + " ist kein gültiger Integer.");

                    }

                    break;

                case "load":
                    this.load();
                    break;

                case "dump":
                    this.startAusgabe();
                    break;

                case "search":
                    System.out.println("search");
                    break;

                case "exit":
                    scanner.close();
                    System.out.println("System wird heruntergefahren...");
                    return;

                default:
                    System.out.println("Die Eingabe ist leider ungültig. Nutze den Befehl \"help\" um alle gültigen Befehle aufgelistet zu bekommen.");
                    break;

            }

        }

    }

    public void startAusgabe() {

        // [Sortierung ausgelassen]
        Collections.sort( this.liste );

        for (UserStory story : liste) {
            System.out.println(story.toString());

        }

        List<UserStory> reduzierteListe = this.liste.stream().filter(story -> story.getProject().equals("Coll@HBRS")).filter(story -> story.getRisk() >= 5).collect(Collectors.toList());

    }

    /*
     * Methode zum Speichern der Liste. Es wird die komplette Liste
     * inklusive ihrer gespeicherten UserStory-Objekte gespeichert.
     *
     */
    private void store() throws ContainerException {
        ObjectOutputStream oos = null;
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(LOCATION);
            oos = new ObjectOutputStream(fos);

            oos.writeObject(this.liste);
            System.out.println(this.size() + " UserStory wurden erfolgreich gespeichert!");

        } catch (IOException e) {
            e.printStackTrace();
            throw new ContainerException("Fehler beim Abspeichern");

        }

    }

    public void load() {
        ObjectInputStream ois = null;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(LOCATION);
            ois = new ObjectInputStream(fis);

            // Auslesen der Liste
            Object obj = ois.readObject();
            if (obj instanceof List<?>) {
                this.liste = (List) obj;

            }

            System.out.println("Es wurden " + this.size() + " UserStory erfolgreich reingeladen!");

        } catch (IOException e) {
            System.out.println("LOG (für Admin): Datei konnte nicht gefunden werden!");

        } catch (ClassNotFoundException e) {
            System.out.println("LOG (für Admin): Liste konnte nicht extrahiert werden (ClassNotFound)!");

        } finally {
            if (ois != null) try {
                ois.close();

            } catch (IOException e) {

            }

            if (fis != null) try {
                fis.close();

            } catch (IOException e) {

            }

        }

    }

    /**
     * Methode zum Hinzufügen eines Mitarbeiters unter Wahrung der Schlüsseleigenschaft
     * @param userStory
     * @throws ContainerException
     */
    public void addUserStory (UserStory userStory) throws ContainerException {
        if (contains(userStory) == true) {
            ContainerException ex = new ContainerException("ID bereits vorhanden!");
            throw ex;

        }

        liste.add(userStory);

    }

    /**
     * Prüft, ob eine UserStory bereits vorhanden ist
     * @param userStory
     * @return
     */
    private boolean contains( UserStory userStory ) {
        int ID = userStory.getId();
        for (UserStory userStory1 : liste) {
            if (userStory1.getId() == ID) {
                return true;

            }

        }

        return false;

    }

    /**
     * Ermittlung der Anzahl von internen UserStory
     * -Objekten
     * @return
     */
    public int size() {
        return liste.size();

    }

    /**
     * Methode zur Rückgabe der aktuellen Liste mit Stories
     * Findet aktuell keine Anwendung bei Hr. P.
     * @return
     */
    public List<UserStory> getCurrentList() {
        return this.liste;

    }

    /**
     * Liefert eine bestimmte UserStory zurück
     * @param id
     * @return
     */
    private UserStory getUserStory(int id) {
        for ( UserStory userStory : liste) {
            if (id == userStory.getId()) {
                return userStory;

            }

        }

        return null;

    }

}
