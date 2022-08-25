package com.crio.jukebox.entites.repositories;

import com.crio.jukebox.entites.Song;
import com.crio.jukebox.repositories.SongRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class SongRepositoryTest {

    private SongRepository songRepository;

    @BeforeEach
    void setup(){
        final Map<String, Song> songMap = new HashMap<String,Song>(){
            {
                put("1", new Song("1", "SongName1","songgenres1","songalbum1","artistName1", new ArrayList<>()));
                put("2",new Song("2","SongName2","songgenres2", "songAlbum2","artistName",new ArrayList<>()));

            }
        };
        songRepository = new SongRepository(songMap);
    }
    @Test
    @DisplayName("save method should create and return new Song")
    public void saveSong(){
        //Arrange
        final Song Song3 = new  Song("SongName3","songgenres3","songalbum3","artistName3", new ArrayList<>()) ;
        Song expectedSong = new Song("3",  "SongName3","songgenres3","songalbum3","artistName3", new ArrayList<>());
        //Act
        Song actualSong = songRepository.save(Song3);
        //Assert
        Assertions.assertEquals(expectedSong,actualSong);
    }

}
