import java.util.ArrayList;

public class Programmer extends Person {
    private String ROLE = "Programmer";

    public Programmer(String name, String birthDate, String hobby, int id) {
        this.name = name;
        this.birthDate = birthDate;
        this.hobby = hobby;
        this.relationships.put(this, new ArrayList<>());
        this.id = id;
    }

    public String getROLE() {
        return this.ROLE;
    }
}
