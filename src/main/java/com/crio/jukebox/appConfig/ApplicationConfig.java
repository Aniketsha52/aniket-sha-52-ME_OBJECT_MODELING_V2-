package com.crio.jukebox.appConfig;

import com.crio.jukebox.commands.CreatePlaylistCommand;
import com.crio.jukebox.commands.DeletePlaylistCommand;
import com.crio.jukebox.commands.LoadDataCommand;
import com.crio.jukebox.commands.PlayNextSongCommand;
import com.crio.jukebox.commands.PlayPlaylistCommand;
import com.crio.jukebox.commands.PlayPreferredIdSongCommand;
import com.crio.jukebox.commands.PlaySongBackCommand;
import com.crio.jukebox.commands.ToaddSongCommand;
import com.crio.jukebox.commands.TodeleteSongInPlaylistCommand;
import com.crio.jukebox.repositories.IPlaylistRepository;
import com.crio.jukebox.repositories.ISongRepository;
import com.crio.jukebox.repositories.IuserRepository;
import com.crio.jukebox.repositories.PlaylistRepository;
import com.crio.jukebox.repositories.SongRepository;
import com.crio.jukebox.repositories.UserRepository;
import com.crio.jukebox.services.IplayListService;
import com.crio.jukebox.services.IsongService;
import com.crio.jukebox.services.IuserService;
import com.crio.jukebox.services.PlaylistServices;
import com.crio.jukebox.services.SongService;
import com.crio.jukebox.services.UserService;
import com.crio.jukebox.commands.*;

public class ApplicationConfig {
    private final IuserRepository userRepository = new UserRepository();
    private final ISongRepository songRepository = new SongRepository();
    private final IPlaylistRepository playlistRepository = new PlaylistRepository();

    private final IuserService userService = new UserService(userRepository);
    private final IsongService songService = new SongService(songRepository);
    private final IplayListService playListService = new PlaylistServices(userRepository,songRepository,playlistRepository);

    private final CreateUserCommand createUserCommand = new CreateUserCommand(userService);
    private final LoadDataCommand loadDataCommand = new LoadDataCommand(songService);
    private final CreatePlaylistCommand createPlaylistCommand = new CreatePlaylistCommand(playListService);
    private final DeletePlaylistCommand deletePlaylistCommand = new DeletePlaylistCommand(playListService);
    private final ToaddSongCommand toaddSongCommand = new ToaddSongCommand(playListService);
    private final TodeleteSongInPlaylistCommand todeleteSongInPlaylistCommand = new TodeleteSongInPlaylistCommand(playListService);
    private final PlayPlaylistCommand playPlaylistCommand = new PlayPlaylistCommand(playListService);
    private final PlaySongBackCommand playSongBackCommand = new PlaySongBackCommand(playListService);
    private final PlayNextSongCommand playNextSongCommand = new PlayNextSongCommand(playListService);
    private final PlayPreferredIdSongCommand playPreferredIdSongCommand = new PlayPreferredIdSongCommand(playListService);

    private final CommandInvoker commandInvoker = new CommandInvoker();

    public CommandInvoker getCommandInvoker(){
        commandInvoker.register("LOAD-DATA",loadDataCommand);
        commandInvoker.register("CREATE-USER",createUserCommand);
        commandInvoker.register("CREATE-PLAYLIST",createPlaylistCommand);
        commandInvoker.register("DELETE-PLAYLIST",deletePlaylistCommand);
        commandInvoker.register("MODIFY-PLAYLIST ADD",toaddSongCommand);
        commandInvoker.register("MODIFY-PLAYLIST DELETE", todeleteSongInPlaylistCommand);
        commandInvoker.register("PLAY-PLAYLIST",playPlaylistCommand);
        commandInvoker.register("PLAY-SONG BACK",playSongBackCommand);
        commandInvoker.register("PLAY-SONG NEXT",playNextSongCommand);
        commandInvoker.register("PLAY-SONG PREFERREDID", playPreferredIdSongCommand);

        return commandInvoker;
    }



}
