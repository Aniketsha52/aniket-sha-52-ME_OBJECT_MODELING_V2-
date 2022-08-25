package com.crio.jukebox.commands;

import com.crio.jukebox.entites.Playlist;
import com.crio.jukebox.entites.Song;
import com.crio.jukebox.exception.PlaylistNotFoundException;
import com.crio.jukebox.services.IplayListService;

import java.util.List;

public class PlayPlaylistCommand implements ICommand{
    private final IplayListService playListService;

    public PlayPlaylistCommand(IplayListService playListService) {
        this.playListService = playListService;
    }
    // Sample Input Token List:- ["PLAY-PLAYLIST" "1" "1" ]

    @Override
    public void execute(List<String> tokens)throws PlaylistNotFoundException {
        String userId = tokens.get(1);
        String playlistId = tokens.get(2);

        try {
            Song playSong = playListService.playPlaylist(userId,playlistId);
            System.out.println("Current Song Playing");
            System.out.println(playSong.getSongName());
            System.out.println(playSong.getAlbum());
            System.out.println(playSong.getArtists());
        } catch (PlaylistNotFoundException e) {
            throw new RuntimeException("Playlist is empty.");
        }
    }
}