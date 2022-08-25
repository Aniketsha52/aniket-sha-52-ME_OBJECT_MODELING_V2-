package com.crio.jukebox.commands;

import com.crio.jukebox.entites.Song;
import com.crio.jukebox.services.IplayListService;

import java.util.List;

public class PlaySongBackCommand implements ICommand{

    private final IplayListService playListService;

    public PlaySongBackCommand(IplayListService playListService) {
        this.playListService = playListService;
    }
    // Sample Input Token List:- ["PLAY-SONG" "USER_ID"  "BACK"]
    @Override
    public void execute(List<String> tokens) {
        String userId = tokens.get(1);

        Song playSong = playListService.playSongBack(userId);
        System.out.println(playSong.getSongName());
        System.out.println(playSong.getAlbum());
        System.out.println(playSong.getArtists());
    }
}