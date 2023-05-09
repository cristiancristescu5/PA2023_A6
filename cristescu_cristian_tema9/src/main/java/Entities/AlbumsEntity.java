package Entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "albums", schema = "musicjpa")
@NamedQueries({
        @NamedQuery(name = "albums.findByName",
                query = "select e from AlbumsEntity e where e.title = ?1"),
        @NamedQuery(name = "albums.findById",
                query = "select e from AlbumsEntity e where e.id = ?1"),
        @NamedQuery(name = "albums.findAll",
                query = "select e from AlbumsEntity e")
})
public class AlbumsEntity extends AbstractEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private int id;
    @Basic
    @Column(name = "release_year", nullable = true, length = 25)
    private String releaseYear;
    @Basic
    @Column(name = "title", nullable = true, length = 25)
    private String title;
    @Basic
    @Column(name = "artist_id", nullable = true)
    private Integer artistId;

    @OneToMany
    private List<GenresEntity> albumsEntities;

    public AlbumsEntity() {

    }

    public AlbumsEntity(String title, String releaseYear, Integer artistId) {
        this.artistId = artistId;
        this.title = title;
        this.releaseYear = releaseYear;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getArtistId() {
        return artistId;
    }

    public void setArtistId(Integer artistId) {
        this.artistId = artistId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AlbumsEntity that = (AlbumsEntity) o;

        if (id != that.id) return false;
        if (releaseYear != null ? !releaseYear.equals(that.releaseYear) : that.releaseYear != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (artistId != null ? !artistId.equals(that.artistId) : that.artistId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (releaseYear != null ? releaseYear.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (artistId != null ? artistId.hashCode() : 0);
        return result;
    }
}
