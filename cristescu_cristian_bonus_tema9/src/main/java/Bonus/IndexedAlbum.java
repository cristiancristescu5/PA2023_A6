package Bonus;

import EntitiesJPA.AlbumsEntity;

public class IndexedAlbum {
    private AlbumsEntity albumsEntity;
    private int index;
    public IndexedAlbum(int index, AlbumsEntity albumsEntity){
        this.albumsEntity = albumsEntity;
        this.index = index;
    }

    public AlbumsEntity getAlbumsEntity() {
        return albumsEntity;
    }

    public void setAlbumsEntity(AlbumsEntity albumsEntity) {
        this.albumsEntity = albumsEntity;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
