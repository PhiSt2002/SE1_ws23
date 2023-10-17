# 1.1 - Antworten auf die Fragen

1) _Wie kann diese Kommunikationsverbindung nun dennoch mit Hilfe einer zusätzlichen Klasse, welche die dazu notwendige Objekt-Erzeugung übernimmt, aufgebaut werden? In welchem Package sollte diese zusätzliche Klasse liegen? Bitte beachten Sie dabei auch die Hinweise bzw. Anforderungen aus den Kommentaren der Klassen, Methoden und des Interfaces._ 
   - Man erzeugt eine weiter Klasse names "Factory", welche die Objekterzeugung übernimmt.
   
####

2) _Welches Entwurfsmuster (engl.: design pattern) könnte für die Problematik der Objekt-Erzeugung verwendet werden? Was ist der software-technische Nutzen bei der Verwendung des Entwurfsmusters?_
   - Für die otpimale Lösung des Problemns kann das "Factory Method"-Muster gewählt werden.
   - Dieses Muster ermöglicht es, die Erstellung von Objekten in einer separaten Methode (Factory-Methode) zu kapseln.
   
####

3) _Wie muss man den Source Code des Interface ggf. anpassen, um mögliche auftretende Kompilierfehler zu beseitigen?_
   - Um Kompilierfehler zu vermeiden, müssen wir das Interface Translator so angepassen, dass es die Methode translateNumber(int number) deklariert, die man im Factory-Code aufruft.
   
####

4) _Was ist der Vorteil einer separaten Test-Klasse?_
   - Man trennt Produktcode vom Testcode. (Übersichlicher)
   - Man kann verschiedene Testszenarien implementieren.
   
####

5) _Was ist bei einem Blackbox-Test der Sinn von Äquivalenzklassen?_
   - Blackbox-Test sind Äquivalenzklassen Gruppen von Eingabewerten.
   - Mögliche Fehler oder Probleme in verschiedenen Eingabebereichen werden identifiziert.
   
####

6) _Warum ist ein Blackbox-Test mit JUnit auf der Klasse Client nicht unmittelbar durchführbar?_
    - Da die Methode display(int aNumber) eine interne Abhängigkeiten zu Factory und Translator hat, ist dies schwer zu testen.