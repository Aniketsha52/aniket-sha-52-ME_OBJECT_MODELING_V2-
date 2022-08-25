package com.crio.jukebox.services;

import com.crio.jukebox.entites.Playlist;
import com.crio.jukebox.entites.Song;
import com.crio.jukebox.entites.User;
import com.crio.jukebox.exception.PlaylistNotFoundException;
import com.crio.jukebox.exception.UserNotFoundException;
import com.crio.jukebox.repositories.IPlaylistRepository;
import com.crio.jukebox.repositories.ISongRepository;
import com.crio.jukebox.repositories.IuserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@DisplayName("PlaylistService")
@ExtendWith(MockitoExtension.class)

class PlaylistServicesTest {

    @Mock
    private IuserRepository userRepositoryMock;
    @Mock
    private ISongRepository SongRepositoryMock;
    @InjectMocks
    private PlaylistServices playListService;
    private AutoCloseable closeable;

    @Mock
    private IPlaylistRepository PlaylistRepositoryMock;
    @Mock
    private IsongService songServiceMock;
    @BeforeEach
    void initService() {
        closeable = MockitoAnnotations.openMocks(this);
    }


    @Test
    @DisplayName("create method Should Throw UserNotFoundException If No Creator User Found")
    public void create_ShouldThrowUserNotFoundException() {
        //Arrange
        when(userRepositoryMock.findByName(anyString())).thenReturn(Optional.empty());

        //Act and Assert
        Assertions.assertThrows(UserNotFoundException.class, () -> playListService.createPlaylist("2", "playlist_4", new ArrayList<>()));
        verify(userRepositoryMock, times(1)).findByName(anyString());
    }


    @Test
    @DisplayName("createPlaylist should create playlist")
    void createPlaylist() {
        final LinkedList<Song> songList = new LinkedList<Song>() {
            {
                List<String> artists4 = new ArrayList<>();
                artists4.add("Ed Sheeran");
                artists4.add("Skrillex");
                add(new Song("4", "Way To Break My Heart", "Pop", "No.6 Collaborations Project", "Ed Sheeran", artists4));
                List<String> artist5 = new ArrayList<>();
                artist5.add("Ed Sheeran");
                artist5.add("Chance The Rapper");
                artist5.add("PnB Rock");
                add(new Song("5", "Cross Me", "Pop", "No.6 Collaborations Project", "Ed Sheeran", artist5));
            }
        };

        final User user = new User("4", "user4");
        Playlist expectedPlaylist = new Playlist("4", user.getId(), "playlist_4", songList);
        when(userRepositoryMock.findById(user.getId())).thenReturn(Optional.of(user));
        when(PlaylistRepositoryMock.save(any(Playlist.class))).thenReturn(expectedPlaylist);
        doThrow(new RuntimeException()).when(songServiceMock).loadData(anyString());
        final List<String> songsId = new ArrayList<>();
        songsId.add("4");
        songsId.add("5");


        Playlist actualContest = playListService.createPlaylist(user.getId(), "playlist_4", songsId);

        Assertions.assertEquals(expectedPlaylist.getSongs().size(), actualContest.getSongs().size());
        verify(userRepositoryMock, times(1)).findByName(anyString());
        verify(PlaylistRepositoryMock, times(1)).save(any(Playlist.class));
    }

    @Test
    @DisplayName("playPlaylist method Should Throw NoPLaylistFoundException If No Contest Found")
    public void playPlalit_ShouldThrowNPlaylistNotFoundException() {
        //Arrange
        when(PlaylistRepositoryMock.findById(anyString())).thenReturn(Optional.empty());
        //Act and Assert
        Assertions.assertThrows(PlaylistNotFoundException.class, () -> playListService.playPlaylist("1", "PlaylistCreator1"));
        verify(PlaylistRepositoryMock, times(1)).findById(anyString());
    }
}
