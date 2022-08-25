package com.crio.jukebox.repositories;

import java.util.Optional;
import com.crio.jukebox.entites.User;

public interface IuserRepository extends CRUDRepository<User,String> {
    public Optional<User> findByName(String name);


}
