package com.crio.jukebox.entites.repositories;

import com.crio.jukebox.entites.User;
import com.crio.jukebox.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {


    private UserRepository userRepository;

    @BeforeEach
    void setup(){
        final Map<String,User> userMap = new HashMap<String, User>(){
            {
                put("1",new User("1", "user1"));
                put("2",new User("2", "user2"));
                put("3",new User("3", "user3"));
            }
        };
        userRepository = new UserRepository(userMap);
    }


    @Test
    @DisplayName("save method should create and return new User")
    public void saveUser() {
        final User u = new User("user4");
        User expectedUser = new User("4","user4");
        User actualUser = userRepository.save(u);

        Assertions.assertEquals(expectedUser,actualUser);

    }

    @Test
    @DisplayName("findById method should return User Given Id")
    public void findById_ShouldReturnUser_GivenUserId(){
        //Arrange
        String expectedUserId = "3";
        //Act
        Optional<User> actualUser = userRepository.findById(expectedUserId);

        //Assert
        Assertions.assertEquals(expectedUserId,actualUser.get().getId());
    }
}
