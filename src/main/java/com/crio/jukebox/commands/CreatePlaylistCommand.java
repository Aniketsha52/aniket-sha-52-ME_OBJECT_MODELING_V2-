package com.crio.jukebox.commands;

import com.crio.jukebox.entites.Playlist;
import com.crio.jukebox.exception.PlaylistNotFoundException;
import com.crio.jukebox.services.IplayListService;

import java.util.Arrays;
import java.util.List;

public class CreatePlaylistCommand implements ICommand{

    private final IplayListService playListService;

    public CreatePlaylistCommand(IplayListService playListService) {
        this.playListService = playListService;
    }

    // Sample Input Token List:- [CREATE-PLAYLIST "1"  "MY_PLAYLIST_1", "[1 2 3 4 5]"]
    @Override
    public void execute(List<String> tokens) throws PlaylistNotFoundException {
        String userId = tokens.get(1);
        String playlistName = tokens.get(2);

        List<String> listOfSongId = Arrays.asList(tokens.get(3).split(","));

        try {
            Playlist playlist = playListService.createPlaylist(userId,playlistName,listOfSongId);
            System.out.println(playlist);
        } catch (PlaylistNotFoundException e) {
            throw new PlaylistNotFoundException("Some Requested Songs Not Available. Please try again.");
        }


    }
}
