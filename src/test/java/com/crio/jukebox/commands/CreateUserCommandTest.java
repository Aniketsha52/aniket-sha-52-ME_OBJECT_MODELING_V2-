package com.crio.jukebox.commands;

import com.crio.jukebox.entites.User;
import com.crio.jukebox.services.IuserService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("CreateUserCommandTest")
@ExtendWith(MockitoExtension.class)
public class CreateUserCommandTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Mock
    IuserService userServiceMock;

    @InjectMocks
    CreateUserCommand createUserCommand;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    @DisplayName("execute method of CreateUserCommand Should Print newly Created User To Console")
    public void execute() {
        //Arrange
        String expectedOutput = "1 name";
        User user = new User("1","name");
        when(userServiceMock.create("name")).thenReturn(user);

        //Act
        createUserCommand.execute(List.of("CREATE-USER","name"));

        //Assert
        Assertions.assertEquals(expectedOutput, outputStreamCaptor.toString().trim());

        verify(userServiceMock,times(1)).create(anyString());
    }




    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

}
