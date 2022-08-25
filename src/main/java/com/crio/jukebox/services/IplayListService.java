package com.crio.jukebox.services;

import java.util.List;
import com.crio.jukebox.entites.Playlist;
import com.crio.jukebox.entites.Song;

public interface IplayListService {
    public Playlist createPlaylist(String UserId, String playList_Name, List<String> listOfsongId);

    public String deletePlaylistOfGivenId(String userId, String palyList_Id);

    public Playlist addSongInPlaylist(String userId, String playlist_ID, List<String> listSongsId);

    public Playlist deleteSongInPlaylist(String userId, String playlist_ID, List<String> wantToDelete);

    public Song playPlaylist(String userId, String playlist_ID);

    public Song playSongBack(String userId);
    public Song playNextSong(String userId);

    public Song songByPreferredID(String userId, int songId);
}
