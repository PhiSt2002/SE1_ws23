package org.hbrs.se1.ws23.uebung4.prototype.demo.commandpattern;

public interface Command {
    public void execute(); // ggf. weitere Paramter
    public void undo();
}
