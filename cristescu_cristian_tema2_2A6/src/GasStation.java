/**
 * This class extends the Locations class
 *
 * @author Cristescu Cristian
 */
public class GasStation extends Locations {//Cristescu Cristian A6
    private float gasPrice;

    /**
     * This is the constructor for the GasStation class
     * @param gasPrice the fuel price
     * @param name the name of the GasStation
     * @param x the x coordinate
     * @param y the y coordinate
     * @param id
     */
    public GasStation(float gasPrice, String name, float x, float y, int id) {
        this.gasPrice = gasPrice;
        this.name = name;
        this.coordinates.setX(x);
        this.coordinates.setY(y);
        this.id = id;
    }

    public float getX() {

        return this.coordinates.getX();
    }

    public float getY() {

        return this.coordinates.getY();
    }

    public String getName() {
        return this.name;
    }

    public void setX(float x) {

        this.coordinates.setX(x);
    }

    public void setY(float y) {

        this.coordinates.setY(y);
    }

    /**
     * This method is used to get the price of the fuel grom a GasStation
     *
     * @return the fuel price
     */
    public float getGasPrice() {
        return this.gasPrice;
    }

    /**
     * This method is used to set the fuel price from a GasStation
     *
     * @param gasPrice the price you want tp set
     */
    public void setGasPrice(float gasPrice) {
        if (gasPrice >= 0)
            this.gasPrice = gasPrice;
    }
}
