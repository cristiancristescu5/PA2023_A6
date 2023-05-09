package org.example.Classes;

public class Bonds implements Entity{
    Integer id;
    Integer artistId;
    Integer genreID;

    public Bonds(Integer artistId, Integer genreID) {
        this.artistId = artistId;
        this.genreID = genreID;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getArtistId() {
        return artistId;
    }

    public void setArtistId(Integer artistId) {
        this.artistId = artistId;
    }

    public Integer getGenreID() {
        return genreID;
    }

    public void setGenreID(Integer genreID) {
        this.genreID = genreID;
    }

    @Override
    public Integer getId() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }
}
