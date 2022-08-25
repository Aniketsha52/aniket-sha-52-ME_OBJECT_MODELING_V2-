package com.crio.jukebox.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import com.crio.jukebox.entites.Song;

public class SongRepository implements  ISongRepository{

    private final Map<String, Song> sonagMap;
    private Integer autoIncrement;

    public SongRepository(){
        sonagMap = new HashMap<String,Song>();
    }

    public SongRepository(Map<String,Song> sonagMap){
        this.sonagMap = new HashMap<String,Song>();
        this.autoIncrement = sonagMap.size();
    }

    public SongRepository(Map<String, Song> sonagMap, Integer autoIncrement) {
        this.sonagMap = sonagMap;
        this.autoIncrement = sonagMap.size();
    }


    @Override
    public Song save(Song song) {
        if(song.getSongId() == null){
            autoIncrement++;
            Song s = new Song(Integer.toString(autoIncrement),song.getSongName(), song.getGenres(),song.getAlbum(),song.getArtistName(),song.getArtists());
            sonagMap.put(s.getSongId(),s);
            return song;
        }
        sonagMap.put(song.getSongId(),song);
        return song;
    }

    @Override
    public List<Song> findAll() {
        return new ArrayList<>(sonagMap.values());
    }

    @Override
    public Optional<Song> findById(String id) {
        return Optional.ofNullable(sonagMap.get(id));
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public void delete(Song entity) {

    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public List<Song> findAllSongLevelWise(Song song) {

        return sonagMap.values()
                .stream().filter(lvl -> song.getSongId().equals(lvl.getSongId()))
                .collect(Collectors.toList());

    }


}