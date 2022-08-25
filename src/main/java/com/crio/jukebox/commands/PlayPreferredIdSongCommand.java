package com.crio.jukebox.commands;

import com.crio.jukebox.entites.Song;
import com.crio.jukebox.exception.SongNotFoundException;
import com.crio.jukebox.services.IplayListService;

import java.util.List;

public class PlayPreferredIdSongCommand implements ICommand{

    private final IplayListService playListService;

    public PlayPreferredIdSongCommand(IplayListService playListService) {
        this.playListService = playListService;
    }

    // Sample Input Token List:- ["PLAY-SONG" "USER_ID"  songId"]

    @Override
    public void execute(List<String> tokens) throws SongNotFoundException {
        String userId = tokens.get(1);

        int id = Integer.parseInt(tokens.get(2));

        try {
            Song playSong = playListService.songByPreferredID(userId,id);
            System.out.println("Current Song Playing");
            System.out.println(playSong.getSongName());
            System.out.println(playSong.getAlbum());
            System.out.println(playSong.getArtists());

        } catch (SongNotFoundException e) {
            throw new SongNotFoundException("Song Not Found in the current active playlist.");
        }

    }
}
