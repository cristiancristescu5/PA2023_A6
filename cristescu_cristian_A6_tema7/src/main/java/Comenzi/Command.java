package Comenzi;

import Utilitare.Robot;

import java.util.ArrayList;
import java.util.List;

public abstract class Command {
    protected String NAME;
    protected List<Robot> robots = new ArrayList<>();
    public abstract void run();
    public abstract void run(String command) throws InterruptedException;

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

}
