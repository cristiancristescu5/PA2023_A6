/**
 * This class extends the Locations class
 *
 * @author Cristescu Cristian
 */
public class Airport extends Locations {//Cristescu Cristian A6

    private int numberOfTerminals;

    /**
     * This is the constructor for the Airpot class
     * @param numberOfTerminals the number of therminals from the airport
     * @param x the x coordinate
     * @param y the y coordinate
     * @param name the name of the airport
     * @param id
     */
    Airport(int numberOfTerminals, float x, float y, String name, int id) {
        this.coordinates.setY(y);
        this.coordinates.setX(x);
        this.name = name;
        this.numberOfTerminals = numberOfTerminals;
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public float getX() {
        return this.coordinates.getX();
    }

    public float getY() {
        return this.coordinates.getY();
    }

    public void setX(float x) {
        this.coordinates.setX(x);
    }

    public void setY(float y) {
        this.coordinates.setY(y);
    }
}
