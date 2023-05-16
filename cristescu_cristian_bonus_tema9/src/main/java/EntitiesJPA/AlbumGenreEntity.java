package EntitiesJPA;

import jakarta.persistence.*;

@Entity
@Table(name = "album_genre", schema = "musicjpa")
@NamedQueries({
        @NamedQuery(name = "albumgenre.findById",
                query = "select e from AlbumGenreEntity e where e.id = ?1"),
        @NamedQuery(name = "albumgenre.findByAlbumId",
                query = "select e from AlbumGenreEntity e where e.albumId = ?1"),
        @NamedQuery(name = "albumgenre.findByGenreId",
                query = "select e from AlbumGenreEntity e where e.genreId=?1")
})
public class AlbumGenreEntity extends AbstractEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "album_id", nullable = true)
    private Integer albumId;
    @Basic
    @Column(name = "genre_id", nullable = true)
    private Integer genreId;
    public AlbumGenreEntity(){

    }
    public AlbumGenreEntity(Integer albumId, Integer genreId){
        this.albumId = albumId;
        this.genreId = genreId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    public Integer getGenreId() {
        return genreId;
    }

    public void setGenreId(Integer genreId) {
        this.genreId = genreId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AlbumGenreEntity that = (AlbumGenreEntity) o;

        if (id != that.id) return false;
        if (albumId != null ? !albumId.equals(that.albumId) : that.albumId != null) return false;
        if (genreId != null ? !genreId.equals(that.genreId) : that.genreId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (albumId != null ? albumId.hashCode() : 0);
        result = 31 * result + (genreId != null ? genreId.hashCode() : 0);
        return result;
    }
}
