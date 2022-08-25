package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.exception.SongNotFoundException;
import com.crio.jukebox.services.IsongService;

public class LoadDataCommand implements  ICommand{

    // Sample Input Token List:- ["LOAD-DATA" "Input csv file "]
    private final IsongService songService;

    public LoadDataCommand(IsongService songService) {
        this.songService = songService;
    }

    @Override
    public void execute(List<String> tokens) throws SongNotFoundException {
        String csvFile = tokens.get(1);

        try {
            songService.loadData(csvFile);
            System.out.println("Songs Loaded successfully");
        } catch (SongNotFoundException e) {
            throw new SongNotFoundException("Song not found!");
        }

    }
}