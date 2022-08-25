package com.crio.jukebox.entites;

import java.util.List;

public class Song extends BaseEntity {
    private final String songId;
    private final String songName;
    private final String genres;

    private final String album;

    private final String artistName;


    private final List<String> artists;

    public Song(String songId){
        this.songId = songId;
        this.songName = getSongName();
        this.genres = getGenres();
        this.artists = getArtists();
        this.artistName = getArtistName();
        this.album = getAlbum();
    }



    public Song(Song song){
        this(song.songId,song.songName,song.genres,song.album,song.artistName,song.artists);
    }



    public Song(String songId, String songName, String genres,   String album, String artistName,  List<String> artists) {
        this.songId = songId;
        this.genres = genres;
        this.songName = songName;
        this.album = album;
        this.artistName = artistName;
        this.artists = artists;
    }

    public Song(String songName,String genres, String album,String artistName,List<String> artists) {
        this.songId = getSongId();
        this.songName = songName;
        this.genres = genres;
        this.album = album;
        this.artists = artists;
        this.artistName = artistName;
    }

    public String getSongId() {

        return songId;
    }

    public String getSongName() {
        return  songName;
    }
    public String getGenres() {
        return genres;
    }
    public String getAlbum() {
        return album;
    }

    public String getArtistName() {
        return artistName;
    }

    public List<String> getArtists() {
        return artists;
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
        Song other = (Song) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }


    @Override
    public String toString() {
        return "current song playing song  {" + songName + "}, Album -{" + album + "}, Artists -{"+ artists +"}";
    }



}