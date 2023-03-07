/**
 * This class id used to model the coordinate of a location
 *
 * @author Cristescu Cristian
 */
public class Coordinates {//Cristescu Cristian A6
    private float x, y;

    /**
     * This method returns the x coordinate of a location
     * @return the x coordinate
     */
    public float getX() {
        return this.x;
    }

    /**
     * This method returns the y coordinate of a location
     * @return the y coordinate
     */
    public float getY() {
        return this.y;
    }

    /**
     * This method changes the x coordinate of a location
     * @param x the new x coordinate of a location
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     * This method changes the y coordinate of a location
     * @param y the new y coordinate of a location
     */

    public void setY(float y) {
        this.y = y;
    }
}
