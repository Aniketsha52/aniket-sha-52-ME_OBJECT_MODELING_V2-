package com.crio.jukebox.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import com.crio.jukebox.entites.User;

public class UserRepository implements IuserRepository {
    private final Map<String, User> userMap;



    Integer autoIncrement = 0;

    public UserRepository(){
        this.userMap = new HashMap<String,User>();
        this.autoIncrement = userMap.size();
    }
    public UserRepository(Map<String,User> userMap){
        this.userMap = new HashMap<String,User>();
        this.autoIncrement = userMap.size();
    }




    @Override
    public User save(User user) {
        if( user.getId() == null ){
            autoIncrement++;
            User u = new User(Integer.toString(autoIncrement),user.getName());
            userMap.put(u.getId(),u);
            return u;
        }
        userMap.put(user.getId(),user);

        return user;

    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public Optional<User> findById(String id) {
        return Optional.ofNullable(userMap.get(id));
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public void delete(User entity) {

    }

    @Override
    public void deleteById(String s) {

    }
    @Override
    public Optional<User> findByName(String name){
        List<User> userName = new ArrayList<>();
        for(String key : userMap.keySet()){
            if(Objects.equals(userMap.get(key).getName(), name)){
                System.out.println(userMap.get(key));
                userName.add(userMap.get(key));
                return Optional.of(userMap.get(key));
            }
        }
        return Optional.empty();

    }


}
