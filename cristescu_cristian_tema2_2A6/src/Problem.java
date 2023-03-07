import java.util.*;
import java.util.List;

/**
 * This class describes the instance of the "Best Route Problem"
 */
public class Problem {
    //Cristescu Cristian A6
    List<Locations> locations = new ArrayList<>();
    HashMap<Locations, ArrayList<Locations>> adjacencyList = new HashMap<>();//hashmap folosit pentru a stoca listele de adiacenta pentru fiecare locatie
    List<Roads> roads = new ArrayList<>();

    /**
     * This method adds new locations to the problem
     * @param l the location to be added
     */
    public void addLocation(Locations l) {
        for (Locations a : locations) {
            if (a.equals(l)) {
                System.out.println("Locatie cu acelasi nume deja existenta");
                return;
            }
        }
        locations.add(l);
        adjacencyList.put(l, new ArrayList<>());
    }

    /**
     * This method adds new roads to the problem
     *
     * @param r the road to be added
     */
    public void addRoads(Roads r) {
        for (Roads r1 : roads) {
            if (r1.equals(r)) {
                System.out.println("Drum cu acelasi nume deja existent");
                return;
            }
        }
        roads.add(r);
        int ok = 0;
        int ok1 = 0;
        for (Locations i : adjacencyList.keySet()) {
            if (i.equals(r.a)) {
                ok = 1;
            }
            if (i.equals(r.b)) {
                ok1 = 1;
            }
        }
        if (ok == 0) {
            adjacencyList.put(r.a, new ArrayList<>());
        }
        if (ok1 == 0) {
            adjacencyList.put(r.b, new ArrayList<>());
        }
        adjacencyList.get(r.a).add(r.b);
        adjacencyList.get(r.b).add(r.a);
    }

    /**
     * This method prints all the roads of the problem
     */
    public void printRoutes() {
        for (Roads r : roads) {
            System.out.println(r.getName() + " " + r.getType() + " " + r.getLength() + " " + r.a.getName() + " " + r.b.getName());
        }
    }

    /**
     * This method is used to print all locations of the problem
     */
    public void printLocation() {

        for (Locations i : adjacencyList.keySet()) {
            System.out.print(i.getName() + ": ");
            for (Locations j : adjacencyList.get(i)) {
                System.out.print(j.getName() + " ");
            }
            System.out.println();
        }

    }

    /**
     * This method validates the arguments of the problem(locations and roads) and verifies if all the locations are connected
     * @return true if all the locations are connected, false otherwise
     */

    public boolean isValid() {//DFS
        boolean[] visited = new boolean[locations.size()];
        for (int i = 0; i < locations.size(); i++) {
            visited[i] = false;
        }
        Locations a = locations.get(0);

        DFS(a, adjacencyList, visited);


        for (boolean b : visited) {
            if (!b) {
                return false;
            }
        }

        return true;
    }

    /**
     * This method is used by the isValid method, and performs a DFS search
     * @param source the location to start from
     * @param list a hashmap that stores for every location, the locations that is connected with
     * @param visited this array is used to mark the visited locations
     */
    public void DFS(Locations source, HashMap<Locations, ArrayList<Locations>> list, boolean[] visited) {
        System.out.println(source.name);
        visited[source.id] = true;
        for (Locations l : list.keySet())
            for (int i = 0; i < list.get(l).size(); i++) {
                Locations neighbour = list.get(l).get(i);
                if (!visited[neighbour.id]) {
                    DFS(neighbour, list, visited);
                }
            }
    }

    /**
     * This method is used to verify if it is possible to go from location a to location b, using the given roads. This method uses the DFS algorithm to verify if all the locations indicated by the roads, including a and b, are connected
     * @param a the starting location
     * @param b the ending location
     * @param roads the roads
     * @return true if we can go from a to b with the given roads, false otherwise
     */
    public boolean findLocationUsingRoads(Locations a, Locations b, ArrayList<Roads> roads) {
        HashMap<Locations, ArrayList<Locations>> locationsToGo = new HashMap<>();
        boolean[] visited = new boolean[locations.size()];

        for (int i = 0; i < visited.length; i++) {
            visited[i] = false;
        }

        for (Roads r : roads) {
            locationsToGo.put(r.a, new ArrayList<>());
            locationsToGo.put(r.b, new ArrayList<>());
        }

        locationsToGo.put(a,new ArrayList<>());
        locationsToGo.put(b, new ArrayList<>());

        for (Roads r : roads) {
            locationsToGo.get(r.a).add(r.b);
            locationsToGo.get(r.b).add(r.a);
        }

        DFS(a,locationsToGo,visited);

        for(Locations l : locationsToGo.keySet()){
            if(!visited[l.id])
                return false;
        }
//
//        if (locationsToGo.get(a).size() != 1) {
//            return false;
//        }
//
//        if (locationsToGo.get(b).size() != 1) {
//            return false;
//        }
//
//        for (Locations l : locationsToGo.keySet()) {
//            if (!l.equals(a) && !l.equals(b)) {
//                if (locationsToGo.get(l).size() != 2) {
//                    return false;
//                }
//            }
//        }


        return true;
    }

    public static void main(String[] args) {
        int index=0;
        Problem p = new Problem();
        Locations a = new Airport(10, 2.0f, 2.0f, "Iasi", index);
        index++;
        Locations b = new Airport(10, 3.0f, 4.0f, "Botosani", index);
        index++;
        Locations c = new GasStation(10.0f, "Petrom", 2.0f, 5.0f, index);
        index++;
        Locations d = new City(1_000_000, "Vaslui", 3.0f, 7.0f, index);
        index++;
        Locations e = new City(100_000, "Bacau", 5.0f, 7.0f, index);
        index++;
        Locations f = new Airport(15, 10.0f, 11.0f, "Suceava_Airport", index);
        index++;
        Locations g = new City(100_890, "Suceava", 5.0f, 16.0f, index);
        index++;
        p.addLocation(a);
        p.addLocation(b);
        p.addLocation(c);
        p.addLocation(d);
        p.addLocation(e);
        p.addLocation(f);
        p.addLocation(g);
        Roads r1 = new Highway(a, b, "A10", 100.0f);
        Roads r2 = new Highway(c, d, "A100", 105.0f);
        Roads r3 = new Highway(a, d, "A1000", 1000.0f);
        Roads r4 = new Express(e, f, "DN240", 240.5f);
        Roads r5 = new Country(c, e, "DJ10", 100.0f);
        Roads r6 = new Express(a, e, "DJ230f", 350.0f);
        Roads r7 = new Country(g, f, "DJ1000", 150.6f);
        p.addRoads(r1);
        p.addRoads(r2);
        p.addRoads(r3);
        p.addRoads(r4);
        p.addRoads(r5);
        p.addRoads(r6);
        p.addRoads(r7);

        p.printRoutes();
        p.printLocation();

        ArrayList<Roads> roads1 = new ArrayList<>();
        roads1.add(r1);
        roads1.add(r2);
        roads1.add(r3);
        System.out.println(p.findLocationUsingRoads(a, g, roads1));

        System.out.println(p.isValid());
    }

}