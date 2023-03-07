/**
 * This is an abstract class than models existing or non-existing roads.
 *
 *
 * @author Cristescu Cristian
 */
public abstract class Roads {//Cristescu Cristian A6
    protected String name;
    protected Locations a, b;
    protected float length;

    /**
     * This method returns the name of the road
     * @return the name of the road
     */
    public abstract String getName();

    /**
     * This method returns the type of the road
     * @return the type of the road
     */
    public abstract String getType();

    /**
     * This method is used to set the name of a road
     * @param name the new name of the road
     */
    public abstract void setName(String name);

    /**
     * This method is used to set the length of a road
     * @param length the new length of the road
     */
    public abstract void setLength(float length);

    /**
     * This method return the length of the road
     * @return the length of the road
     */
    public abstract float getLength();

    /**
     * This method is used to compare two objects of type Roads
     * @param obj is of type Roads
     * @return returns true if the two Roads are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Roads)) {
            return false;
        }
        Roads other = (Roads) obj;
        return name.equals(other.name);
    }
}
