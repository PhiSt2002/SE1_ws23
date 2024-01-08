package org.hbrs.se1.ws23.uebung4.prototype.demo.commandpattern;

public class HelpCommand implements Command {
    @Override
    public void execute() {
        System.out.println("Hier die Kommandos: help....");

    }

    @Override
    public void undo() {
        System.out.println("Nothing to Undo!");
    }
}
