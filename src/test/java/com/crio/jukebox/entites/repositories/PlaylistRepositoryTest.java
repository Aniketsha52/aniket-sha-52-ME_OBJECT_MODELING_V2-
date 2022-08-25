package com.crio.jukebox.entites.repositories;

import com.crio.jukebox.entites.Playlist;
import com.crio.jukebox.entites.Song;
import com.crio.jukebox.entites.User;
import com.crio.jukebox.repositories.PlaylistRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class PlaylistRepositoryTest {
    private PlaylistRepository playlistRepository;
    @BeforeEach
    void setup(){
        final LinkedList<Song> songList1 = new LinkedList<Song>(){
            {
                add(new Song("1", "SongName1","songgenres1","songalbum1","artistName1", new ArrayList<>()));
            }
        };
        final LinkedList<Song> songList2 = new LinkedList<Song>(){
            {
                add(new Song("2","SongName2","songgenres1", "songAlbum2","artistName",new ArrayList<>()));
            }
        };
        String user1 = "1";
        String user2 = "2";

        String user3 = "3";
        final Map<String,Playlist> playlistMap = new HashMap<String,Playlist>(){
            {
                put("1", new Playlist("1",user1,"Playlist_1",songList1));
                put("2", new Playlist("2",user2,"Playlist_2",songList2));
                put("3", new Playlist("3",user3,"Playlist_3",songList2));
            }
        };
        playlistRepository = new PlaylistRepository(playlistMap);
    }


    @Test
    @DisplayName("save method should create and return new playlist")
    public void savePlaylist(){
        //Arrange

        final LinkedList<Song> songList3 = new LinkedList<Song>(){
            {
                add(new Song("4","SongName4", "songgenres1","songAlbum4","artistName4",new ArrayList<>()));
            }
        };
        String user4 = "4";
        Playlist playlist4 = new Playlist(user4,"playlist_4",songList3);
        Playlist expectedContest = new Playlist("4",user4,"playlist_4",songList3);
        //Act
        Playlist actualContest = playlistRepository.save(playlist4);
        //Assert
        Assertions.assertEquals(expectedContest,actualContest);
    }

}
