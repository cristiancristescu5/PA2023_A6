drop table artists;
/
CREATE TABLE artists (
  name varchar(25),
  id int primary key auto_increment
  
  );
/
drop table genres;
/
CREATE TABLE genres (
  name varchar(25),
  id int primary key auto_increment
  
  );
/
drop table albums;
/
CREATE TABLE albums (
  ID int not null primary key auto_increment,
  release_year varchar(25),
  title varchar(25) ,
  artist_id int,
  CONSTRAINT FK_PersonOrder FOREIGN KEY (artist_id)
    REFERENCES artists(id)
  
  );
/
drop table album_genre;
/
CREATE TABLE album_genre (
  album_id int,
  genre_id int ,
  CONSTRAINT FK_PersonOrde2 FOREIGN KEY (album_id)
    REFERENCES albums(id),
    CONSTRAINT FK_PersonOrder3 FOREIGN KEY (genre_id)
    REFERENCES genres(id)
  );

