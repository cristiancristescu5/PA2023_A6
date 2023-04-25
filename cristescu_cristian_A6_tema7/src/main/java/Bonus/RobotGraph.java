package Bonus;

public class RobotGraph implements Runnable {
    private String name;
    private boolean running = false;
    ExplorationControll explore;


    public RobotGraph(String name, ExplorationControll explore) {
        this.name = name;
        this.explore = explore;
    }

    public void killRobot() {
        this.running = false;
        System.out.println("Robotul " + this.name + " s-a oprit din parcurgerea grafului.");
    }

    public boolean getStatus() {
        return this.running;
    }

    public void setStatus(boolean status) {
        this.running = status;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public void run() {
        for (SharedMemoryGraph s : explore.getExplorationGraph().getSubgraphs()) {
            if (explore.getExplorationGraph().visit(s, this)) {
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else {
                System.out.println(s.getSharedGraph().toString() + " a fost deja vizitat!");
            }
        }
    }

}
