package org.example;

import org.example.Bonus.AlbumGraph;
import org.example.Bonus.BronKerbosch;
import org.example.Bonus.Playlist;
import org.example.Classes.*;
import org.example.DAOClasses.*;
import org.graph4j.Edge;
import org.graph4j.Graph;
import org.graph4j.GraphBuilder;
import org.graph4j.alg.clique.BronKerboschCliqueIterator;
import org.graph4j.util.Clique;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;


public class Main {
    public static DataDAO albums = new AlbumDAO();
    public static BondsDAO bonds = new BondsDAO();
    public static Database database = new Database();
    public static List<AlbumGraph> albumGraphs = new ArrayList<>();
    public static BronKerbosch bronKerbosch = new BronKerbosch();
    public static Graph<AlbumGraph, Edge<AlbumGraph>> playlist = GraphBuilder.empty().estimatedNumVertices(100).estimatedAvgDegree(100).buildGraph();
    public static List<Playlist> playlists = new ArrayList<>();

    public static boolean isCommon(AlbumGraph a, AlbumGraph b) {
        if (a.equals(b)) {
            return true;
        }
        if (a.getYear().equals(b.getYear())) {
            return true;
        }
        if (a.getArtistId().equals(b.getArtistId())) {
            return true;
        }
        for (Integer i : a.getGenres()) {
            for (Integer j : b.getGenres()) {
                if (i.equals(j)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void createGraph() {
        for (AlbumGraph albumGraph : albumGraphs) {
            playlist.addVertex(albumGraph);
        }
        for (AlbumGraph i : albumGraphs) {
            for (AlbumGraph j : albumGraphs) {
                Edge<AlbumGraph>[] edges = playlist.edgesOf(playlist.findVertex(i));
                if (!i.equals(j)) {
                    boolean found = false;
                    for (int k = 0; k < edges.length; k++) {
                        if (edges[k].target() == playlist.findVertex(j)) {
                            found = true;
                        }
                    }
                    if (!found && isCommon(i, j)) {
                        playlist.addEdge(i, j);
                    }
                }
            }
        }

    }


    public static void main(String[] args) throws IOException, SQLException, InterruptedException {
        try {
            List<Entity> albumList = albums.findAll(database);
            for (Entity album : albumList) {
                albumGraphs.add(new AlbumGraph(album.getId(),
                        album.getName(),
                        bonds.findGenres(database,
                                album.getId()),
                        ((Album) album).getArtistId(),
                        ((Album) album).getYear()));
            }
            createGraph();
            Set<AlbumGraph> R = new HashSet<>();
            Set<AlbumGraph> P = new HashSet<>();
            Set<AlbumGraph> X = new HashSet<>();
            for (AlbumGraph album : albumGraphs) {
                P.add(album);
            }
            bronKerbosch.bronKerbosch(R, P, X, playlist.complement());

            playlists = bronKerbosch.getPlaylists();
            for (Playlist p : playlists) {
                System.out.println(p.toString());
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        database.closeConnection();
    }
}