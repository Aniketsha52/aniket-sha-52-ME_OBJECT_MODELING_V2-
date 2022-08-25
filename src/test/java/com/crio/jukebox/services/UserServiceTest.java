package com.crio.jukebox.services;

import com.crio.jukebox.entites.User;
import com.crio.jukebox.repositories.IuserRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@DisplayName("UserServiceTest")
@ExtendWith(MockitoExtension.class)
class UserServiceTest {


    @Mock
    private IuserRepository userRepositoryMock;
    @InjectMocks
    private  UserService userService;



    @Test
    @DisplayName("create method should create User")
    public void create_ShouldReturnUser() {
        //Arrange
        User expectedUser = new User("1", "Yakshit");
        when(userRepositoryMock.save(any(User.class))).thenReturn(expectedUser);

        //Act
        User actualUser = userService.create("Yakshit");

        //Assert
        Assertions.assertEquals(expectedUser, actualUser);
        verify(userRepositoryMock,times(1)).save(any(User.class));

    }
}
