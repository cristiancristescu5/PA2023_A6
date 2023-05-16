package EntitiesJDBC;

public class Album implements Entity {
    private Integer id;
    private String name;
    private Integer artistId;
    private Integer year;
    public Album(Integer id, String name, Integer year, Integer artistId) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.artistId = artistId;
    }

    public Album(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Album(Integer id,String name, Integer artistId) {
        this.name = name;
        this.id = id;
        this.artistId = artistId;
    }

    public Album(String name, Integer year, Integer artistId) {
        this.year = year;
        this.name = name;
        this.artistId = artistId;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    public Integer getArtistId() {
        return artistId;
    }

    public Integer getYear() {
        return year;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArtistId(Integer artistId) {
        this.artistId = artistId;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}
