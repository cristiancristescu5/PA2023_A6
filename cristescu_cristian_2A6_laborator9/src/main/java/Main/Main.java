package Main;

import Entitati.ArtistsEntity;
import Repos.ArtistsRepository;
import Repos.DataRepository;
import Test.TestJPA;


import java.util.List;

public class Main {
    public static DataRepository<ArtistsEntity, Integer> dataRepository = new ArtistsRepository();
    public static void main(String[] args){
        TestJPA testJPA = new TestJPA();
        List<ArtistsEntity> artistsEntities = dataRepository.findByName("The Beatles");
        for(ArtistsEntity artistsEntity : artistsEntities){
            System.out.println(artistsEntity.getName() + " " + artistsEntity.getId());
        }
        ArtistsEntity artistsEntity = new ArtistsEntity("Bau");
        dataRepository.create(artistsEntity);
        testJPA.testJPA();
    }
}
