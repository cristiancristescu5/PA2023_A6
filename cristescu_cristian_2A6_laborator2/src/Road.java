public class Road {
    private String name;
    private RoadTypes type;
    private Location a;
    private Location b;

    public Road(String name, RoadTypes type, Location a, Location b) {
        this.name = name;
        this.type = type;
        this.a = a;
        this.b = b;
    }

    public String getName() {
        return this.name;
    }

    public RoadTypes getType() {
        return this.type;
    }

    public Location getA() {
        return this.a;
    }

    public Location getB() {
        return this.b;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(RoadTypes type) {
        this.type = type;
    }

    public void setA(Location a) {
        this.a = a;
    }

    public void setB(Location b) {
        this.b = b;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Road)) {
            return false;
        }
        Road other = (Road) obj;
        return name.equals(other.name);
    }

    public String toString(){
        return name+" "+type+" "+a.getLocation();
    }
}


