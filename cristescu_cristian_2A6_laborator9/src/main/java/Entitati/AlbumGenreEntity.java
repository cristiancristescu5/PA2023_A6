package Entitati;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "album_genre", schema = "music")
public class AlbumGenreEntity extends AbstractEntity implements Serializable {
    @Basic
    @Column(name = "album_id", nullable = true)
    private Integer albumId;
    @Basic
    @Column(name = "genre_id", nullable = true)
    private Integer genreId;
    @Id
    private Long id;

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

        if (albumId != null ? !albumId.equals(that.albumId) : that.albumId != null) return false;
        if (genreId != null ? !genreId.equals(that.genreId) : that.genreId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = albumId != null ? albumId.hashCode() : 0;
        result = 31 * result + (genreId != null ? genreId.hashCode() : 0);
        return result;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
