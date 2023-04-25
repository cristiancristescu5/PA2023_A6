package Comenzi;

import Utilitare.Exploration;
import Utilitare.Robot;
import Utilitare.TimeKeeper;

import java.util.List;

public class StartCommand extends Command {
    Exploration exploration;

    public StartCommand(List<Robot> robotList, Exploration exploration) {
        this.exploration = exploration;
        this.NAME = "start";
        this.robots = robotList;
    }

    @Override
    public void run() {
        if (!exploration.getMap().isExplored()) {
            TimeKeeper timer = new TimeKeeper(robots);
            Thread thread = new Thread(timer);
            thread.setDaemon(true);
            thread.start();
            for (Robot r : robots) {
                System.out.println("Pornesc robotul " + r.getName());
                Thread t = new Thread(r);
                t.start();
            }
        } else {
            TimeKeeper timer = new TimeKeeper(robots);
            exploration.resetMap();
            Thread time = new Thread(timer);
            time.setDaemon(true);
            time.start();
            for (Robot r : robots) {
                if (!r.getStatus()) {
                    System.out.println("Pornesc robotul " + r.getName());
                    Thread t = new Thread(r);
                    r.setCol(-1);
                    r.setExtractedTokens(0);
                    t.start();
                }
            }
        }
    }

    public void run(String command) {
        String[] words = command.split(" ");
        if (words.length != 2) {
            System.out.println("Comanda start invalida. Comanda start are forma: start <nume robot>.");
            return;
        }
        boolean found = false;
        for (Robot r : robots) {
            if (r.getName().equals(words[1])) {
                found = true;
            }
        }
        if (!found) {
            System.out.println("Robotul cu numele " + words[1] + " nu a fost gasit!");
        }
        for (Robot r : robots) {
            if (r.getName().equals(words[1]) && !r.getExplore().getMap().isExplored()) {
                if (r.getStatus()) {
                    System.out.println("Robotul " + r.getName() + " deja parcurge matricea!");
                    return;
                } else {
                    TimeKeeper timer = new TimeKeeper(robots);
                    Thread time = new Thread(timer);
                    time.setDaemon(true);
                    time.start();
                    System.out.println("Pornesc robotul " + r.getName() + "!");
                    Thread t = new Thread(r);
                    t.start();
                    return;
                }
            } else {
                if (r.getName().equals(words[1]) && r.getExplore().getMap().isExplored()) {
                    System.out.println("Pornesc robotul " + r.getName() + "!");
                    TimeKeeper timer = new TimeKeeper(robots);
                    Thread time = new Thread(timer);
                    time.setDaemon(true);
                    r.getExplore().resetMap();
                    r.setCol(-1);
                    r.setExtractedTokens(0);
                    Thread t = new Thread(r);
                    time.start();
                    t.start();
                    return;
                }
            }
        }
        System.out.println("Robotul " + words[1] + " nu a fost gasit!");
        System.out.println("Nu am putut porni robotul. Nu exista robot cu numele " + words[1] + ".");
    }
}
