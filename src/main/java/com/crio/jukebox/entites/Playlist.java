package com.crio.jukebox.entites;

import java.util.LinkedList;

public class Playlist extends BaseEntity{
    private   String userId;
    private String playlistId;
    private final String playList_Name;
    private final LinkedList<Song> listNameSong;

    public Playlist (Playlist playlist){
        this(playlist.playlistId, playlist.userId, playlist.playList_Name,playlist.listNameSong);
    }


    public Playlist(String playlistId, String userId, String playList_Name, LinkedList<Song> listNameSong){
        this.userId = userId;
        this.playlistId = playlistId;
        this.playList_Name = playList_Name;

        this.listNameSong= listNameSong;
    }
    public Playlist(String userId,String playList_Name, LinkedList<Song> listNameSong){
        this.playList_Name = playList_Name;
        this.listNameSong = listNameSong;
        this.userId = userId;
    }

    public String getPlaylistId(){
        return playlistId;
    }

    public String getPlayList_Name() {
        return playList_Name;
    }





    public String getUserId() {
        return userId;
    }

    public LinkedList<Song> getSongs() {
        return listNameSong;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }




    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Playlist other = (Playlist) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }


    @Override
    public String toString(){
        return "PlayList [id=" + playlistId + "]";
    }
}

