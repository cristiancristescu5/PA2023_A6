package Entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "genres", schema = "musicjpa")
@NamedQueries({
        @NamedQuery(name = "genres.findByName",
                query = "select e from GenresEntity e where e.name = ?1"),
        @NamedQuery(name = "genres.findById",
                query = "select e from GenresEntity e where e.id = ?1"),
        @NamedQuery(name = "genres.findAll",
                query = "select e from GenresEntity e")
}
)
public class GenresEntity extends AbstractEntity {
    @Basic
    @Column(name = "name", nullable = true, length = 25)
    private String name;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @ManyToMany
    private List<AlbumsEntity> albumsEntities;
    public GenresEntity() {

    }

    public GenresEntity(String name) {
        this.name = name;
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

        GenresEntity that = (GenresEntity) o;

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
