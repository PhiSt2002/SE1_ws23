package org.hbrs.se1.ws23.uebung4.prototype.improvements.view;
import org.hbrs.se1.ws23.uebung4.prototype.improvements.model.UserStory;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class UserStoryView {
    public void startAusgabe(  List<UserStory> liste , String suchParameter ) {

        // Es gibt noch einen Compile-Error, den Hr. P nicht versteht.
        // Die Ausgabe funktioniert noch nicht so recht

        // [Sortierung ausgelassen]
        Collections.sort( liste );

          // [Variante mit forEach-Methode / Streams (--> Kapitel 9, Lösung Übung Nr. 2)?
        //  Gerne auch mit Beachtung der neuen US1
        // (Filterung Projekt = "ein Wert (z.B. Coll@HBRS)" ( und ggf. Risiko >=5, wenn gewünscht )
        List<UserStory> reduzierteListe = liste.stream()
                .filter( story -> story.getProject().equals( suchParameter ) )
                .collect( Collectors.toList() );

        for (UserStory story : reduzierteListe) {
            System.out.println(story.toString());
        }
    }
}
