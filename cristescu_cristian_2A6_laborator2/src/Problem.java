import java.util.ArrayList;
import java.util.List;

public class Problem {
    private List<Location> locations=new ArrayList<Location>();

    private List<Road> roads=new ArrayList<>();


    public void addLocations(Location location){
        this.locations.add(location);
    }

    public void addRoads(Road road){
        this.roads.add(road);
    }


    public void getRoads(){
        for(Location a : locations){
            System.out.println(a);
        }
    }

    public void getLocations(){
        for(Road r : roads){
            System.out.println(r);

        }
    }

    public void printProblem(){
        System.out.println("Problem:");
        for(Location a : locations)
            System.out.println(a);
        for(Road r : roads)
            System.out.println(r);
    }



}
