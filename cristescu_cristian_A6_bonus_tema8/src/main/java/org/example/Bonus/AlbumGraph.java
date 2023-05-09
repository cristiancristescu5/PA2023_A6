package org.example.Bonus;

import java.util.List;

public class AlbumGraph {
    private Integer id;
    private String name;
    private List<Integer> genres;
    private Integer artistId;
    private Integer year;

    public AlbumGraph(Integer id, String name, List<Integer> genres, Integer artistId, Integer year) {
        this.id = id;
        this.name = name;
        this.genres = genres;
        this.artistId = artistId;
        this.year = year;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getGenres() {
        return genres;
    }

    public void setGenres(List<Integer> genres) {
        this.genres = genres;
    }

    public Integer getArtistId() {
        return artistId;
    }

    public void setArtistId(Integer artistId) {
        this.artistId = artistId;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public boolean getGenreID(Integer id){
        for(Integer i : genres){
            if(id.equals(i)){
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return this.id + " " + this.name + " " + this.year + " " + this.artistId + " " + this.genres.toString();
    }
}
