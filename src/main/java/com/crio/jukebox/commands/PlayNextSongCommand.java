package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.entites.Song;
import com.crio.jukebox.services.IplayListService;

public class PlayNextSongCommand implements ICommand{

    private final IplayListService playListService;

    public PlayNextSongCommand(IplayListService playListService) {
        this.playListService = playListService;
    }

    // Sample Input Token List:- ["PLAY-SONG" "USER_ID"  "NEXT"]

    @Override
    public void execute(List<String> tokens) {
        String userId = tokens.get(1);

        Song playSong = playListService.playNextSong(userId);
        System.out.println(playSong.getSongName());
        System.out.println(playSong.getAlbum());
        System.out.println(playSong.getArtists());
    }

}
