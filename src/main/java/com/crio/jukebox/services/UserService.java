package com.crio.jukebox.services;

import com.crio.jukebox.entites.User;
import com.crio.jukebox.repositories.IuserRepository;

public class UserService implements IuserService {


    private final IuserRepository userRepository;


    public UserService (IuserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public User create(String name) {
        User user = new User(name);
        return userRepository.save(user);
    }
}
