/**
 * This class extends the Locations class
 *
 * @author Cristescu Cristian
 */
public class City extends Locations {//Cristescu Cristian A6
    private long population;

    /**
     * This is the constructor for the City class
     * @param population the population of a City
     * @param name the name of the City
     * @param x the x coordinate
     * @param y the y coordinate
     * @param id
     */
    public City(long population, String name, float x, float y, int id) {
        this.population = population;
        this.name = name;
        this.coordinates.setX(x);
        this.coordinates.setY(y);
        this.id = id;
    }

    /**
     * This method is used to get the population of a city
     * @return the population of a city
     */
    public long getPopulation() {
        return population;
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
