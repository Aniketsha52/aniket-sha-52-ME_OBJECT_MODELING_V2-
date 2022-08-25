package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.entites.User;
import com.crio.jukebox.services.IuserService;

public class CreateUserCommand implements ICommand{
    private final IuserService userService;

    public CreateUserCommand(IuserService userService) {
        this.userService = userService;
    }


    // Sample Input Token List:- ["CREATE-USER","Kiran"]

    @Override
    public void execute(List<String> tokens) {
        String name = tokens.get(1);
        User user = userService.create(name);
        System.out.println(user);
    }

}