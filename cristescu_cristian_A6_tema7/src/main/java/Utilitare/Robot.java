package Utilitare;

import java.util.List;

public class Robot implements Runnable {
    private String name;
    private boolean running;
    private long sleepingTime;
    Exploration explore;
    private int col = -1 ;
    private int line = -1;
    private int extractedTokens = 0;

    public int getCol() {
        return col;
    }

    public Exploration getExplore() {
        return explore;
    }

    public void setExtractedTokens(int extractedTokens) {
        this.extractedTokens = extractedTokens;
    }

    public int getExtractedTokens() {
        return extractedTokens;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public Robot(String name, long sleepingTime, Exploration explore) {
        this.name = name;
        this.explore = explore;
        this.sleepingTime = sleepingTime;
    }

    public void killRobot() {
        this.running = false;
        System.out.println("Robotul " + this.name + " s-a oprit din parcurgerea matricei. A extras "+ this.getExtractedTokens()+" tokeni!");
    }

    public boolean getStatus() {
        return this.running;
    }

    public void killRobotWhenFinished() {
        List<Token>[][] list;
        list = explore.getMap().getMatrix();
        for (int i = 0; i < explore.getMap().getSize(); i++) {
            for (int j = 0; j < explore.getMap().getSize(); j++) {
                if (list[i][j].isEmpty()) {
                    return;
                }
            }
        }
        killRobot();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void goToSleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
            System.out.println(this.name + ": M-am odihnit pentru " + seconds + " secunde.");
        } catch (InterruptedException e) {
            killRobot();
        }
    }

    @Override
    public void run() {
        running = true;
        if(col<0) {
            col = explore.getMap().getExploring();
            while (running && col < explore.getSize()) {
                if (explore.getMap().visitSystematic(col, this)) {
                    try {
                        Thread.sleep(sleepingTime);
                    } catch (InterruptedException e) {
                        killRobot();
                    }
                    col++;

                }else {
                    col++;
                }
                killRobotWhenFinished();
            }
        }
        if(col>0){
            while(running && col<explore.getSize()){
                if(explore.getMap().visitSystematic(col, this)){
                    try{
                        Thread.sleep(sleepingTime);
                    }catch (InterruptedException e){
                        killRobot();
                    }
                    col++;
                }else {
                    col++;
                }
                killRobotWhenFinished();
            }
        }

    }
}
