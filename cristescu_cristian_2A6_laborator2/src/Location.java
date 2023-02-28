public class Location {
    private String location;
    private float x, y;

    public void setName(String location) {
        this.location = location;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public String getLocation() {
        return this.location;
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Location)) {
            return false;
        }
        Location other = (Location) obj;
        return location.equals(other.location);
    }

    public String toString() {
        return location + " " + x + " " + y;
    }

}
