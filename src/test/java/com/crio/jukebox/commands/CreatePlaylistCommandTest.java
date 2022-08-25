package com.crio.jukebox.commands;

import com.crio.jukebox.entites.Playlist;
import com.crio.jukebox.entites.Song;
import com.crio.jukebox.entites.User;
import com.crio.jukebox.services.IplayListService;
import com.crio.jukebox.services.IuserService;
import com.crio.jukebox.services.PlaylistServices;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class CreatePlaylistCommandTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Mock
    IuserService userServiceMock;
    @Mock
    PlaylistServices playListServiceMock;

    @InjectMocks
    CreatePlaylistCommand createPlaylistCommand;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    @DisplayName("execute method of CreatePlaylistCommand Should Print newly Created playlist To Console")
    public void execute() {
        //Arrange
        String expectedOutput = "Playlist ID - 1";
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
        Playlist playlist = new Playlist("1","name",songList);
        ArrayList<String> songId = new ArrayList<>(); songId.add("4"); songId.add("5");

        when(playListServiceMock.createPlaylist("1","name",songId)).thenReturn(playlist);

        //Act
        createPlaylistCommand.execute(List.of("CREATE-PLAYLIST","1","name", "4,5"));

        //Assert
        Assertions.assertEquals(expectedOutput, outputStreamCaptor.toString().trim());

        verify(playListServiceMock,times(1)).createPlaylist(playlist.getUserId(),playlist.getPlayList_Name(),songId);
    }




    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

}
