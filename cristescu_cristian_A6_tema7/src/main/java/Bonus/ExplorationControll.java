package Bonus;
import java.util.ArrayList;
import java.util.List;

public class ExplorationControll {
    private List<RobotGraph> robots = new ArrayList<>();
    private ExplorationGraph explorationGraph;
    private List<Thread> threads = new ArrayList<>();


    public ExplorationControll(ExplorationGraph explorationGraph) {
        this.explorationGraph = explorationGraph;
    }

    public void addRobot(RobotGraph r) {
        for (RobotGraph t : robots) {
            if (t.equals(r)) {
                System.out.println("Robotelul " + r.getName() + " deja exista!");
                return;
            }
        }
        robots.add(r);
        Thread t = new Thread(r);
        t.setName(r.getName());
        threads.add(t);
    }
    public void startRobots(){
        for(Thread t : threads){
            t.start();
        }
    }
    public ExplorationGraph getExplorationGraph() {
        return explorationGraph;
    }

    public void setExplorationGraph(ExplorationGraph explorationGraph) {
        this.explorationGraph = explorationGraph;
    }
}

