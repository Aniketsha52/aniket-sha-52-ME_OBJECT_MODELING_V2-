package com.crio.jukebox.repositories;

import java.util.List;
import com.crio.jukebox.entites.Song;

public interface ISongRepository extends CRUDRepository<Song, String> {


    public List<Song> findAllSongLevelWise(Song song);


}