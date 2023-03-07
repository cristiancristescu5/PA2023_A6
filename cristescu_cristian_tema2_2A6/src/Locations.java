/**
 * This is an abstract class that models existing or non-existing locations.
 *
 *
 * @author Cristescu Cristian
 */
public abstract class Locations {//Cristescu Cristian A6
    protected String name;
    protected int id;
    protected Coordinates coordinates = new Coordinates();

    /**
     * This method is for setting the x coordinate of a location
     * @param x is of type float
     */
    public abstract void setX(float x);

    /**
     * This method is used to get the name of a location
     * @return the name of the location
     */
    public abstract String getName();

    /**
     * This method is for setting the x coordinate of a location
     *
     * @param y is of type float
     */
    public abstract void setY(float y);

    /**
     * This method is used to get the y coordinate of a location
     * @return the x  coordinate
     */
    public abstract float getX();

    /**
     * This method is used to get the y coordinate of a location
     * @return the y coordinate
     */
    public abstract float getY();

    /**
     * This method is used to compare two objects of type Locations
     * @param obj is of type Locations
     * @return true if two objects are equal or false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Locations)) {
            return false;
        }
        Locations other = (Locations) obj;
        return name.equals(other.name);
    }
}
