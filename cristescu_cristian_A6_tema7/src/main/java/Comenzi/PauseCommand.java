package Comenzi;

import Utilitare.Robot;

import java.util.List;

public class PauseCommand extends Command {
    public PauseCommand(List<Robot> robots) {
        this.NAME = "pause";
        this.robots = robots;
    }

    @Override
    public void run() {
        for (Robot r : robots) {
            if (r.getStatus()) {
                System.out.println("Intrerup executia robotului " + r.getName() + " pentru 3 secunde");
                r.goToSleep(3);
            }
        }
    }

    public void run(String command) {
        String[] words = command.split(" ");

        if (words.length != 3) {
            System.out.println("Comanda pause invalida. Comanda are forma: pause <nume robot> <numar de secunde>.");
            return;
        }
        int pauseSeconds;
        boolean found = false;
        for (Robot r : robots) {
            if (words[1].equals(r.getName())) {
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Robotul "+words[1]+ " nu a fost gasit.");
            return;
        }
        try {
            pauseSeconds = Integer.parseInt(words[2]);
        } catch (Exception e) {
            System.out.println("Numarul de secunde este invalid. Comanda are forma: pause <nume robot> <numar de secunde>.");
            return;
        }
        for (Robot r : robots) {
            if (r.getName().equals(words[1]) && r.getStatus()) {
                System.out.println("Intrerup executia robotului " + r.getName() + " pentru " + pauseSeconds + " secunde.");
                r.goToSleep(pauseSeconds);
                return;
            }
        }

        System.out.println("Nu s-a putut intrerupe robotul, nu exista sau nu si-a inceput executia.");
    }
}
