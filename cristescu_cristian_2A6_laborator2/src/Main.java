public class Main {


    public static void main(String[] args) {
        Problem p=new Problem();

        Location c1=new Location();
        c1.setName("Iasi");
        c1.setX(0.0f);
        c1.setY(0.0f);
        System.out.println(c1);

        Location c2=new Location();
        c2.setName("Vaslui");
        c2.setX(1.0f);
        c2.setY(1.0f);
        System.out.println(c2);

        Road r1=new Road("DN24", RoadTypes.EXPRESS, c1, c2 );
        System.out.println(r1);

        Location c3=new Location();
        c3.setName("Galati");
        c3.setX(2.0f);
        c3.setY(2.0f);
        System.out.println(c3);

        Road r2=new Road("A10", RoadTypes.HIGHWAY, c1, c3);
        System.out.println(r2);

        p.addLocations(c1);
        p.addLocations(c2);
        p.addLocations(c3);
        p.addRoads(r1);
        p.addRoads(r2);
        p.printProblem();


    }
}