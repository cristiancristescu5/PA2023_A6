package Utilitare;
import Comenzi.PauseCommand;
import Comenzi.StartCommand;
import Comenzi.StopCommand;
import java.util.ArrayList;
import java.util.List;

public class Exploration {
    private static int size = 10;
    private static final SharedMemory mem = new SharedMemory(size);
    private ExplorationMap map = new ExplorationMap(size);
    private final List<Robot> robots = new ArrayList<>();
    private static StartCommand start;
    private static StopCommand stop;
    private static PauseCommand pause;
    private boolean isRunning = true;

    public void addRobot(Robot r) {
        for (Robot r1 : robots) {
            if (r1.equals(r)) {
                return;
            }
        }
        robots.add(r);
    }

    public ExplorationMap getMap() {
        return this.map;
    }
    public void resetMap(){
        map = new ExplorationMap(size);
    }

    public void exit() {
        isRunning = false;
        for (Robot r : robots) {
            if (r.getStatus()) {
                r.killRobot();
            }
        }
    }

    public StartCommand getStart() {
        return start;
    }

    public StopCommand getStop() {
        return stop;
    }

    public PauseCommand getPause() {
        return pause;
    }

    public void getRobots() {
        System.out.println("Lista de roboti disponibili:");
        for (Robot r : robots) {
            System.out.print(r.getName() + " ");
        }
        System.out.println();
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        Exploration.size = size;
    }

    public static void main(String[] args) throws InterruptedException {
//        JGraphTTest test = new JGraphTTest(10);
//        Exploration explore = new Exploration();
//        ExplorationControll explorationController = new ExplorationControll(new ExplorationGraph(2,8,5));
//        long sleeping = 3000;
//        explore.addRobot(new Robot("Wall-E", sleeping, explore));
//        explore.addRobot(new Robot("R2D2", sleeping, explore));
//        explore.addRobot(new Robot("Optimus-Prime", sleeping, explore));
//        explore.addRobot(new Robot("B", sleeping, explore));

//        explorationController.addRobot(new RobotGraph("Wall-E", explorationController));
//        explorationController.addRobot(new RobotGraph("Bau", explorationController));
//        explorationController.addRobot(new RobotGraph("Optimus-Prime", explorationController));
//        explorationController.addRobot(new RobotGraph("R2D2", explorationController));

        //explorationController.startRobots();

//        stop = new StopCommand(explore.robots);
//        start = new StartCommand(explore.robots, explore);
//        pause = new PauseCommand(explore.robots);
//        System.out.println("Lista de comenzi disponibile:");
//        System.out.println("-comanda start: start <nume robot> || start || start timer;");
//        System.out.println("-comanda pause: pause <nume robot> <numar de secunde> || pause;");
//        System.out.println("-comanda stop: stop <nume robot> || stop;");
//        System.out.println("-afisare : afiseaza.");
//        System.out.println("Roboti disponibili:");
//        explore.getRobots();
//        while (explore.isRunning) {
//            Scanner scan = new Scanner(System.in);
//            String command = scan.nextLine();
//            String[] words = command.split(" ");
//            boolean executedCommand = false;
//            if (words[0].equals("start") && words.length == 1 && !executedCommand) {
//                explore.getStart().run();
//                executedCommand = true;
//            }
//
//            if (words[0].equals("stop") && words.length == 1 && !executedCommand) {
//                explore.getStop().run();
//                executedCommand = true;
//            }
//
//            if (words[0].equals("start") && words.length > 1 && !executedCommand) {
//                explore.getStart().run(command);
//                executedCommand = true;
//            }
//
//            if (words[0].equals("stop") && words.length > 1 && !executedCommand) {
//                explore.getStop().run(command);
//                executedCommand = true;
//            }
//
//            if (words[0].equals("pause") && words.length == 1 && !executedCommand) {
//                explore.getPause().run();
//                executedCommand = true;
//            }
//
//            if (words[0].equals("pause") && words.length > 1 && !executedCommand) {
//                explore.getPause().run(command);
//                executedCommand = true;
//            }
//
//            if (command.equals("exit") && !executedCommand) {
//                System.out.println("Se inchide programul, se opresc robotii");
//                explore.exit();
//                executedCommand = true;
//            }
//
//            if(command.equals("afiseaza") && !executedCommand){
//                System.out.println(explore.getMap().toString());
//                executedCommand=true;
//            }
//
//            if (!executedCommand) {
//                System.out.println("Comanda invalida");
//            }
//        }
    }
}