package com.crio.jukebox.repositories;

import com.crio.jukebox.entites.Playlist;
import com.crio.jukebox.entites.Song;

import java.util.*;
import java.util.stream.Collectors;

public class PlaylistRepository implements IPlaylistRepository{

  private final Map<String,Playlist> playlistMap;
  private Integer autoIncrement = 0;

    public PlaylistRepository(){
        playlistMap = new HashMap<String,Playlist>();
    }
    public PlaylistRepository(Map<String,Playlist> playlistMap){
        this.playlistMap = new HashMap<String,Playlist>();
        this.autoIncrement = playlistMap.size();
    }

    public PlaylistRepository(Map<String, Playlist> playlistMap, Integer autoIncrement) {
        this.playlistMap = playlistMap;
        this.autoIncrement = playlistMap.size();
    }


    @Override
    public Playlist save(Playlist playlist) {
        if( playlist.getId() == null ){
            ++autoIncrement;
            Playlist p = new Playlist(Integer.toString(autoIncrement),playlist.getUserId(),playlist.getPlayList_Name(),playlist.getSongs());
            playlistMap.put(p.getPlaylistId(),p);
            return p;
        }
        playlistMap.put(playlist.getPlaylistId(),playlist);
        return playlist;
    }

    @Override
    public List<Playlist> findAll() {
        return new ArrayList<>(playlistMap.values());
    }

    @Override
    public Optional<Playlist> findById(String id) {
        return Optional.ofNullable(playlistMap.get(id));
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public void delete(Playlist entity) {

    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public List<Playlist> findAllPlaylistLevelWise(Playlist playlist){
        return playlistMap.values().
                stream().filter(id -> playlist.equals(id.getPlaylistId())).collect(Collectors.toList());
    }


}