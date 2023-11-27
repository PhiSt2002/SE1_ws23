package org.hbrs.se1.ws23.solutions.uebung3.persistence;

import java.io.*;
import java.util.List;

public class PersistenceStrategyStream<E> implements PersistenceStrategy<E> {

    // URL der Datei, in der die Objekte gespeichert werden
    private String LOCATION = "objects.ser";

    private ObjectOutputStream oos = null;
    private FileOutputStream fos = null;

    private FileInputStream fis = null;
    private ObjectInputStream ois = null;

    // Used only for testing purposes, if the location should be changed
    // Example: Location is a directory
    public void setLOCATION(String LOCATION) {
        this.LOCATION = LOCATION;
    }


    @Override
    public void openConnection() throws PersistenceException {
        try {
            fis = new FileInputStream( LOCATION );
        } catch (FileNotFoundException e) {
            throw new PersistenceException( PersistenceException.ExceptionType.ConnectionNotAvailable
                    , "Error in opening the connection, File could not be found");
        }
        try {
            ois = new ObjectInputStream(  fis  );
        } catch (IOException e) {
            throw new PersistenceException( PersistenceException.ExceptionType.ConnectionNotAvailable
                    , "Error in opening the connection, problems with the stream");
        }
        //outputstreams are handled inside store()
    }

    @Override
    public void closeConnection() throws PersistenceException {
        try {
            // Closing the inputstreams for loading
            if (ois != null) ois.close();
            if (fis != null) fis.close();

            //outputstreams are handled inside store()
        } catch( IOException e ) {
            // Lazy solution: catching the exception of any closing activity ;-)
            throw new PersistenceException(PersistenceException.ExceptionType.ClosingFailure , "error while closing connections");
        }
    }

    @Override
    /**
     * Method for saving a list of Member-objects to a disk (HDD)
     */
    public void save(List<E> list) throws PersistenceException {
        // Write the objects to stream
        try {
            fos = new FileOutputStream( LOCATION );
            oos = new ObjectOutputStream( fos );

            System.out.println( "LOG: Es wurden " +  list.size() + " Member-Objekte wurden erfolgreich gespeichert!");
            oos.writeObject( list );

            oos.close();
            fos.close();
        }
        catch (IOException e) {
            // Koennte man ausgeben für interne Debugs: e.printStackTrace();
            // Chain of Responsibility: Hochtragen der Exception in Richtung Ausgabe (UI)
            // Uebergabe in ein lesbares Format fuer den Benutzer
            e.printStackTrace();
            throw new PersistenceException( PersistenceException.ExceptionType.SaveFailure , "Fehler beim Speichern der Datei!");
        }
    }

    @Override
    /**
     * Method for loading a list of Member-objects from a disk (HDD)
     * Some coding examples comes for free :-)
     */
    public List<E> load() throws PersistenceException {

        // Load the objects from stream
        List<E> list = null;

        try {
            // Auslesen der Liste
            Object obj = ois.readObject();
            if (obj instanceof List<?>) {
                list = (List) obj;
            }
            System.out.println("LOG: Es wurden " + list.size() + " User Stories erfolgreich reingeladen!");
            return list;
        }
        catch (IOException e) {
            // Sup-Optimal, da Exeception in Form eines unlesbaren Stake-Traces ausgegeben wird
            e.printStackTrace();
            throw new PersistenceException( PersistenceException.ExceptionType.LoadFailure , "Fehler beim Laden der Datei!");
        }
        catch (ClassNotFoundException e) {
            // Chain of Responsbility erfuellt, durch Throw der Exceotion kann UI
            // benachrichtigt werden!
            throw new PersistenceException( PersistenceException.ExceptionType.LoadFailure , "Fehler beim Laden der Datei! Class not found!");
        }
    }
}
