package Entitati;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "artists", schema = "music")
@NamedQueries({
        @NamedQuery(name = "artists.findAll",
        query = "select e from ArtistsEntity e order by e.name"),
        @NamedQuery(name = "artists.findByName",
         query = "select e from ArtistsEntity e where e.name = ?1"),
        @NamedQuery(name = "artists.findById",
        query = "select e from ArtistsEntity e where e.id = ?1")
})
public class ArtistsEntity extends AbstractEntity implements Serializable {
    @Basic
    @Column(name = "name", nullable = true, length = 100)
    private String name;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    public ArtistsEntity(String name) {
        this.name = name;
    }

    public ArtistsEntity() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArtistsEntity that = (ArtistsEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + id;
        return result;
    }
}
