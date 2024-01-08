package org.hbrs.se1.ws23.uebung4.abgabe.UserStory;

import java.io.Serializable;

public class UserStory implements Comparable<UserStory>, Serializable {

    String titel;
    int aufwand = 0;
    int id = 0;
    int mehrwert = 0;
    int risk = 0;
    int strafe = 0;
    double prio = 0.0;

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    String project;


    public UserStory(int id, String titel, int mehrwert, int strafe,
                     int aufwand, int risk, double prio) {
        this.id = id;
        this.titel = titel;
        this.mehrwert = mehrwert;
        this.strafe = strafe;
        this.aufwand = aufwand;
        this.risk = risk;
        this.prio = prio;
    }

    public UserStory() {
    }

    public double getPrio() {
        return prio;
    }

    public void setPrio(double prio) {
        this.prio = prio;
    }

    public String getTitel() {
        return titel;
    }
    public void setTitel(String titel) {
        this.titel = titel;
    }
    public int getAufwand() {
        return aufwand;
    }
    public void setAufwand(int aufwand) {
        this.aufwand = aufwand;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getMehrwert() {
        return mehrwert;
    }
    public void setMehrwert(int mehrwert) {
        this.mehrwert = mehrwert;
    }
    public int getRisk() {
        return risk;
    }
    public void setRisk(int risk) {
        this.risk = risk;
    }
    public int getStrafe() {
        return strafe;
    }
    public void setStrafe(int strafe) {
        this.strafe = strafe;
    }

    /*
     * Methode zum Vergleich zweier UserStories.
     * Vergleich ist implementiert auf Basis des Vergleichs
     * von zwei Prio-Werten.
     */
    public int compareTo(UserStory input) {
        if ( input.getPrio() == this.getPrio() ) {
            return 0;
        }

        if ( input.getPrio() > this.getPrio() ) {
            return 1;
        }
        else return -1;
    }

}

