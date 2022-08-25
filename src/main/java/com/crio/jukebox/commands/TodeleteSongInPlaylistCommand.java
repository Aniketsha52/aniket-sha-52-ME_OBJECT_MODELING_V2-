package com.crio.jukebox.commands;

import com.crio.jukebox.entites.Playlist;
import com.crio.jukebox.exception.SongNotFoundException;
import com.crio.jukebox.services.IplayListService;

import java.util.Arrays;
import java.util.List;

public class TodeleteSongInPlaylistCommand implements ICommand{

    private final IplayListService playListService;

    public TodeleteSongInPlaylistCommand(IplayListService playListService) {
        this.playListService = playListService;
    }

    // Sample Input Token List:- ["MODIFY-PLAYLIST" "DELETE-SONG" "1" "1" [3 4]]

    @Override
    public void execute(List<String> tokens) {
        String userId = tokens.get(2);
        String playlistId = tokens.get(3);

        List<String> listofSong = Arrays.asList(tokens.get(4));

        try {
            Playlist playlist = playListService.deleteSongInPlaylist(userId,playlistId,listofSong);
            System.out.println(playlist.getPlaylistId());
            System.out.println(playlist.getPlayList_Name());
            System.out.println(playlist.getSongs());
        } catch (SongNotFoundException e) {
            throw new SongNotFoundException("Some Requested Songs Not Available. Please try again.");
        }

    }
}