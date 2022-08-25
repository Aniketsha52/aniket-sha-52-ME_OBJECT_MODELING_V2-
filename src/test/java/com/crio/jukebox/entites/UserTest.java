package com.crio.jukebox.entites;

import com.crio.jukebox.repositories.IPlaylistRepository;
import com.crio.jukebox.repositories.IuserRepository;
import com.crio.jukebox.repositories.PlaylistRepository;
import com.crio.jukebox.repositories.UserRepository;
import com.crio.jukebox.services.IuserService;
import com.crio.jukebox.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    @BeforeEach
    public  void userService(){
        iuserRepository = new UserRepository();
        userService = new UserService(iuserRepository);
        playlistRepository = new PlaylistRepository();
    }
    private IuserService userService;
    private IuserRepository iuserRepository;
    private IPlaylistRepository playlistRepository;

    @Test
    @DisplayName("checkIfPlaylistExists should Return False If No playlist is Found")
    void checkIfPlaylistExists() {

            //Arrange
            String id = "1";
            String name = "playList_1";
            LinkedList<Song> songs =  new LinkedList<Song>(){
                {
                    add(new Song("1", "Song1","genres1","Album1","artist1",new ArrayList<>()));
                    add(new Song("1", "song2","genres2","Album2","artist2",new ArrayList<>()));
                }
            };

            User creator = new User("1","Yakshit");
            Playlist playlist = new Playlist(id, creator.getId(),name,songs);
            User user = new User("2",name);

            //Act
            Assertions.assertFalse(user.checkIfPlaylistExists(playlist));
        }


}
