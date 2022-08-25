package com.crio.jukebox.entites;

import java.util.List;
import java.util.stream.Collectors;

public class User extends BaseEntity {


    private final String name;
    private  String id;

    private List <Playlist> playlists;



    public User (User user){
        this(user.id, user.name);
    }

    public User(String id, String name, List<Playlist> playlists){
        this(name,id);

        this.playlists = playlists;
    }



    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public User(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public String getId(){
        return id;
    }

    public void deletePlayList(Playlist playlist){
        playlists.removeIf(c -> c.getPlaylistId() == playlist.getPlaylistId());
    }

    public List<Playlist> getPlaylist() {
        return  playlists.stream().collect(Collectors.toList());
    }

    public boolean checkIfPlaylistExists(Playlist playlist){
        return playlists.contains(playlist);
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
        User other = (User) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return   id + " " + name + "";
    }



}
