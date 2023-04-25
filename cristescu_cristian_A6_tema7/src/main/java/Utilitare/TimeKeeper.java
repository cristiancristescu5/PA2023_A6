package Utilitare;
import java.util.ArrayList;
import java.util.List;

public class TimeKeeper implements Runnable {
    protected long executionTime;
    protected boolean running = false;
    protected List<Robot> robots = new ArrayList<>();

    public long getExecutionTime() {
        return executionTime;
    }
    public TimeKeeper(List<Robot> robots){
        this.robots.addAll(robots);
    }
    public TimeKeeper(){

    }
    public void stopTimer(){
        for(Robot t : robots){
            if (t.getStatus()) {
                this.running = true;
                return;
            }
        }
        this.running=false;
        System.out.println("Roboteii au functionat pentru "+ executionTime/1000+" secunde!");
    }

    public void startTimer(){
        this.running=true;
    }
    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {

        }
        long startTime = System.currentTimeMillis();
        this.startTimer();
        while (running){
            executionTime=System.currentTimeMillis()-startTime;
            try {
                Thread.sleep(1);
            }catch (InterruptedException e){

            }
            stopTimer();
        }
    }
}
