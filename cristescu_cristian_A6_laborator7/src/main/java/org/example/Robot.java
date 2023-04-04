package org.example;

import java.util.List;

public class Robot implements Runnable {
    private String name;
    private boolean running;
    Exploration explore;

    public Robot(String name) {
        this.name = name;
        explore = new Exploration();
    }

    public void killRobot() {
        this.running = false;
        System.out.println("Robotul "+this.name+" s-a oprit din parcurgerea matricei.");
    }
    public boolean getStatus(){
        return this.running;
    }
    public void killRobotWhenFinished(){
        List[][] list = new List[explore.getMap().getSize()][explore.getMap().getSize()];
        list = explore.getMap().getMatrix();
        for(int i = 0 ; i < explore.getMap().getSize() ;i++){
            for(int j = 0 ; j < explore.getMap().getSize();j++){
                if(list[i][j].isEmpty()){
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

    @Override
    public void run() {
        running = true;
        while (running) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                killRobot();
            }
            int row, col;
            row = (int) (Math.random() * explore.getMap().getSize());
            col = (int) (Math.random() * explore.getMap().getSize());
            explore.getMap().visit(row, col, this);
            System.out.print(this.name);
            System.out.println();

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                killRobot();
            }
            killRobotWhenFinished();
        }

    }
}
