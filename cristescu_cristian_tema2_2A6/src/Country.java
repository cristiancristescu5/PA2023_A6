/**
 * This class extends the Roads class
 *
 * @author Cristescu Cristian
 */
public class Country extends Roads {//Cristescu Cristian A6
    private String TYPE = "Country";
    /**
     * This is the constructor for the Highway class
     * @param a The type is Locations. This the starting location of the road.
     * @param b The type is Locations. This is the ending location of the road
     * @param name The name of the road
     * @param length The length of the road
     */
    Country(Locations a, Locations b, String name, float length) {
        this.name = name;
        this.a = a;
        this.b = b;
        this.length = length;
    }

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.TYPE;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public float getLength() {
        return this.length;
    }

}
