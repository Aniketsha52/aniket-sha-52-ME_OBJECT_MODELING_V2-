package com.crio.jukebox.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import com.crio.jukebox.entites.Song;
import com.crio.jukebox.repositories.ISongRepository;

public class SongService implements IsongService {

    private final ISongRepository iSongRepository;

    public SongService(ISongRepository iSongRepository) {
        this.iSongRepository = iSongRepository;
    }






    @Override
    public void loadData(String fileName) throws IllegalAccessError {

        try (BufferedReader fileReader
                     = new BufferedReader(new FileReader(fileName))) {
            String line = "";

            //Read the file line by line
            while ((line = fileReader.readLine()) != null) {
                //Get all tokens available in line
                String[] tokens = line.split(",");

                Song song = new Song(tokens[0], tokens[1], tokens[2], tokens[3],tokens[4],Arrays.asList(tokens[5].split("#")));
                iSongRepository.save(song);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
