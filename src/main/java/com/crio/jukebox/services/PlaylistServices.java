package com.crio.jukebox.services;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import com.crio.jukebox.entites.Playlist;
import com.crio.jukebox.entites.Song;
import com.crio.jukebox.entites.User;
import com.crio.jukebox.exception.PlaylistNotFoundException;
import com.crio.jukebox.exception.SongNotFoundException;
import com.crio.jukebox.exception.UserNotFoundException;
import com.crio.jukebox.repositories.IPlaylistRepository;
import com.crio.jukebox.repositories.ISongRepository;
import com.crio.jukebox.repositories.IuserRepository;

public class PlaylistServices implements IplayListService {
    private final IuserRepository userRepository;
    private final ISongRepository songRepository;

    private final IPlaylistRepository playlistRepository;





    public PlaylistServices(IuserRepository userRepository, ISongRepository songRepository,IPlaylistRepository playlistRepository) {
        this.userRepository = userRepository;
        this.songRepository = songRepository;
        this.playlistRepository = playlistRepository;


    }



    @Override
    public Playlist createPlaylist(String userId, String playList_Name, List<String> listOfsongId) throws UserNotFoundException, IllegalAccessError, SongNotFoundException {

        final User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("Cannot Create Playlist. playList Creator for given user ID: " + userId + " not found!"));

        LinkedList<Song> songs = new LinkedList<>();

        for(String s : listOfsongId){
            Song song = songRepository.findById(s).orElseThrow(()-> new SongNotFoundException("Cannot find the song, Please enter correct SongID "+ s +" not found!"));
            songs.add(song);
        }


        if(listOfsongId.isEmpty()){
            return playlistRepository.save(new Playlist(userId,playList_Name,songs));
        }

        return playlistRepository.save(new Playlist(userId,playList_Name,songs));
    }

    @Override
    public String deletePlaylistOfGivenId(String userId, String palyList_id) throws PlaylistNotFoundException,UserNotFoundException {
        final  User user = userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException("Cannot delete Playlist. playList delettion for given user ID: " + userId + " not found!"));

        final Playlist playlist = playlistRepository.findById(palyList_id).orElseThrow(()-> new PlaylistNotFoundException("Cannot delete Playlist. playList delettion for given user ID: " + palyList_id + " not found!"));

        user.deletePlayList(playlist);

        String message = "successFully Deleted";
        return message;
    }


    @Override
    public Playlist addSongInPlaylist(String userId, String playlist_ID, List<String> listSongsAdd) throws SongNotFoundException, PlaylistNotFoundException{
        final Playlist playlist = playlistRepository.findById(playlist_ID).orElseThrow(()-> new PlaylistNotFoundException("Cannot find Playlist. add song for given playlist ID: " + playlist_ID + " not found!"));

        LinkedList<Song> songs = playlist.getSongs();

        List<Song> newSongUserWantToadd = new LinkedList<>();

        for (String songId : listSongsAdd){
          Song song = songRepository.findById(songId).orElseThrow(()-> new SongNotFoundException("Cannot find song for this song Id: " + songId + "Not found!"));
            newSongUserWantToadd.add(song);
        }


        for (Song song : newSongUserWantToadd){
            if (songs.stream().noneMatch(m -> song.getSongId().equals(m.getSongId()))){
                songs.add(song);
            }
        }
        return playlist;

    }

    @Override
    public Playlist deleteSongInPlaylist(String userId, String playlist_ID, List<String> wantToDelete){

        final Playlist playlist = playlistRepository.findById(playlist_ID).orElseThrow(()-> new PlaylistNotFoundException("Cannot delete song. song deletion for given playlist ID: " + playlist_ID + " not found!"));

        LinkedList<Song> songs = playlist.getSongs();

        //this loop will delete all the song of given song id
        for (String t1 : wantToDelete){
            songs.removeIf(data -> Objects.equals(t1, data.getSongId()));
        }
        return  playlist;
    }

    @Override
    public Song playPlaylist(String userId, String playlist_ID){
        final Playlist playlist = playlistRepository.findById(playlist_ID).orElseThrow(()-> new PlaylistNotFoundException("Cannot play song. song play for given playlist ID: " + playlist_ID + " not found!"));

         LinkedList<Song> songs = playlist.getSongs();

        return songs.getFirst();

    }

    @Override
    public Song playSongBack(String userId){
         List<Playlist> listOfPlaylist = playlistRepository.findAll();

         if(listOfPlaylist == null){
             throw new PlaylistNotFoundException("sorry There is no any active playList!");
         }

        Playlist activePlaylist = listOfPlaylist.get(0);
        String playlistId = activePlaylist.getPlaylistId();

        LinkedList<Song> songs = activePlaylist.getSongs();

         Song currentSongPlaying = playPlaylist(userId,playlistId);

         int indexOfCurrentSong = songs.indexOf(currentSongPlaying);
         if (indexOfCurrentSong == 0){
             return songs.getLast();
         }

        return songs.get(--indexOfCurrentSong);
    }

    @Override
    public Song playNextSong(String userId){
        List<Playlist> listOfPlaylist = playlistRepository.findAll();

        if(listOfPlaylist == null){
            throw new PlaylistNotFoundException("sorry There is no any active playList!");
        }
        Playlist activePlaylist = listOfPlaylist.get(0);
        String playlistId = activePlaylist.getPlaylistId();

        LinkedList<Song> songs = activePlaylist.getSongs();

        Song currentSongPlaying = playPlaylist(userId,playlistId);

        int indexOfCurrentSong = songs.indexOf(currentSongPlaying);
        if (indexOfCurrentSong == songs.size()){
            return songs.getFirst();
        }

        return songs.get(++indexOfCurrentSong);

    }
    @Override
    public Song songByPreferredID(String userId,  int songId) throws SongNotFoundException{
        final Playlist playlist = playlistRepository.findById(userId).orElseThrow(()-> new PlaylistNotFoundException("Cannot play song. song play for given user ID: " + userId + " not found!"));

        LinkedList<Song> songs = playlist.getSongs();

        Song currentSong;

        try {
            currentSong = songs.get(songId);
            return currentSong;
        } catch (SongNotFoundException e) {
            throw new SongNotFoundException("Given id of song is not available in playlist! ");
        }

    }


}

