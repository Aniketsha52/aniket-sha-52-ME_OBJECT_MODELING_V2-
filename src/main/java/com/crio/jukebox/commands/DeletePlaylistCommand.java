package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.exception.PlaylistNotFoundException;
import com.crio.jukebox.services.IplayListService;

public class DeletePlaylistCommand implements ICommand{

    private final IplayListService playListService;

    public DeletePlaylistCommand(IplayListService playListService) {
        this.playListService = playListService;
    }


    // Sample Input Token List:- ["DELETE-PLAYLIST"," USER_ID","Playlist-ID"]
    @Override
    public void execute(List<String> tokens) throws PlaylistNotFoundException {
        String userId = tokens.get(1);
        String playListId = tokens.get(2);
        try {
            playListService.deletePlaylistOfGivenId(userId,playListId);
            System.out.println("Delete Successful");
        } catch (PlaylistNotFoundException e) {
            throw new PlaylistNotFoundException("Playlist Not Found");
        }

    }
}
