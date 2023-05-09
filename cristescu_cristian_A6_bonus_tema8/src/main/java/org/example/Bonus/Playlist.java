package org.example.Bonus;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private final List<AlbumGraph> albums = new ArrayList<>();
    private final String name;
    private final Timestamp time;

    public Playlist (String name, List<AlbumGraph> albums){
        this.name = name;
        this.albums.addAll(albums);
        this.time =  new Timestamp(new Date(2023, 5, 8).getTime());
    }

    public String getName() {
        return name;
    }

    public List<AlbumGraph> getAlbums() {
        return albums;
    }

    @Override
    public String toString() {
        StringBuilder album = new StringBuilder();
        album.append("Playlist: ").append(this.name).append(" ,created at ").append(time.getYear() + "-" + time.getMonth() + "-" + time.getDay()).append(", contains "+albums.size()+ " albums:\n");
        for(AlbumGraph albumGraph : albums){
            album.append("\t-").append(albumGraph.getName()).append("\n");
        }
        return album.toString();
    }
}
