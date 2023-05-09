package Main;

import Database.Database;
import Entities.AlbumGenreEntity;
import Entities.AlbumsEntity;
import Entities.ArtistsEntity;
import Entities.GenresEntity;
import Repos.*;
import com.github.javafaker.Faker;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    private static final DataRepository<AlbumsEntity, Integer> albumsRepository = new AlbumsRepository();
    private static final DataRepository<GenresEntity, Integer> genresRepository = new GenresRepository();
    private static final DataRepository<ArtistsEntity, Integer> artistsRepository = new ArtistsRepository();
    private static final AlbumGenreRepository albumGenresRepository = new AlbumGenreRepository();

    public static void main(String[] args) {
        Faker faker = new Faker();
        Set<String> genres = IntStream.rangeClosed(1, 100).distinct().mapToObj(i -> faker.music().genre()).collect(Collectors.toSet());
        Set<String> artists = IntStream.rangeClosed(1, 100).distinct().mapToObj(i -> faker.name().fullName()).collect(Collectors.toSet());
        List<String> albumsNames = IntStream.rangeClosed(1, 200).distinct().mapToObj(i -> faker.book().title()).collect(Collectors.toList());
        Logger loggerAlbums = Logger.getLogger(AlbumsRepository.class.getName());
        Logger loggerGenres = Logger.getLogger(GenresRepository.class.getName());
        Logger loggerArtists = Logger.getLogger(ArtistsRepository.class.getName());
        Logger loggerAlbumsGenre = Logger.getLogger(AlbumGenreRepository.class.getName());
        long genresTime = 0, artistTime = 0, albumTime = 0, albumGenreTime = 0;
        for (String s : genres) {
            long startTime = System.nanoTime();
            if (genresRepository.findByName(s).size() > 0) {
                continue;
            } else {
                genresRepository.create(new GenresEntity(s));
            }
            long endTime = System.nanoTime();
            long duration = (endTime - startTime) / 1000000;
            genresTime += duration;
        }
        for (String s : artists) {
            long startTime = System.nanoTime();
            artistsRepository.create(new ArtistsEntity(s));
            long endTime = System.nanoTime();
            long duration = (endTime - startTime) / 1000000;
            artistTime += duration;
        }

        for (int i = 0; i < 200; i++) {
            int lower = 1;
            int upper = ((ArtistsRepository) artistsRepository).findAll().size();
            Random randomId = new Random();
            Integer artistId = artistsRepository.findById((randomId.nextInt(upper - lower + 1) + lower)).getId();
            Random randomYear = new Random();
            Integer lower1 = 1950;
            Integer upper1 = 2023;
            Integer year = randomYear.nextInt(upper1 - lower1 + 1) + lower1;
            long startTime = System.nanoTime();
            albumsRepository.create(new AlbumsEntity(albumsNames.get(i), year.toString(), artistId));
            long endTime = System.nanoTime();
            long duration = (endTime - startTime) / 1000000;
            albumTime += duration;
        }
        List<AlbumsEntity> albumsEntities = ((AlbumsRepository) albumsRepository).findAll();
            for (AlbumsEntity albumsEntity : albumsEntities) {
                Random random = new Random();
                int lowerG = 1;
                int upperG = 5;
                int numGenres = random.nextInt(upperG - lowerG + 1) + lowerG;
                for (int i = 0; i < numGenres; i++) {
                    int lowerId = 1;
                    long startTime = System.nanoTime();
                    int upperId = ((GenresRepository) genresRepository).findAll().size();
                    int genreId = random.nextInt(upperId - lowerId + 1) + lowerId;
                    albumGenresRepository.create(new AlbumGenreEntity(albumsEntity.getId(), genreId));
                    long endTime = System.nanoTime();
                    long duration = (endTime - startTime) / 1000000;
                    albumGenreTime += duration;
                }
            }

        loggerArtists.info("Artists execution time: " + artistTime + "ms");
        loggerGenres.info("Genres execution time: " + genresTime + "ms");
        loggerAlbums.info("Albums execution time: " + albumTime + "ms");
        loggerAlbumsGenre.info("AlbumGere execution time: " + albumGenreTime + "ms");
        Database.getEntityManager().close();
        Database.closeFactory();
    }


}
