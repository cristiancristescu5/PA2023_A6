package Comenzi;

import Utilitare.Robot;

import java.util.List;


public class StopCommand extends Command {
    public StopCommand(List<Robot> robotList) {
        this.NAME = "stop";
        this.robots = robotList;
    }

    @Override
    public void run() {
        for (Robot r : robots) {
            if(r.getStatus()) {
                r.killRobot();
            }else{
                System.out.println("Robotul " + r.getName()+" nu si-a inceput inca executia.");
            }
        }
    }

    public void run(String command) {
        String[] words = command.split(" ");
        if(words.length!=2){
            System.out.println("Comanda invalida. Comanda are forma: stop <nume robot>");
            return;
        }
        for (Robot r : robots) {
            if (r.getStatus() && r.getName().equals(words[1])) {
                try {
                    r.killRobot();
                }catch (NullPointerException e){
                    System.out.println("Nu s-a putut opri robotelul "+words[1]+"!");
                }
                return;
            }
            if(!r.getStatus() && r.getName().equals(words[1])){
                System.out.println("Robotul "+ r.getName() +" nu poate fi oprit deoarece nu si-a inceput inca executia");
                return;
            }
        }
        System.out.println("Robotul cu numele "+words[1]+" nu exista.");
    }
}
