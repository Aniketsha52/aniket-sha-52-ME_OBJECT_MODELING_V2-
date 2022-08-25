package com.crio.jukebox.commands;

import com.crio.jukebox.entites.User;
import com.crio.jukebox.services.IplayListService;
import com.crio.jukebox.services.IuserService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@DisplayName("Delete playlist Command")
@ExtendWith(MockitoExtension.class)
class DeletePlaylistCommandTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Mock
    IuserService userServiceMock;

    @Mock
    IplayListService playListServiceMock;

    @InjectMocks
    DeletePlaylistCommand deletePlaylistCommand;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    @DisplayName("execute method of deletePlaylistCommand Should Print newly message To Console")
    public void execute() {
        //Arrange
        String expectedOutput = "1 name";
        User user = new User("1","name");
        when(userServiceMock.create("name")).thenReturn(user);

        //Act
        //createUserCommand.execute(List.of("CREATE-USER","name"));

        //Assert
        Assertions.assertEquals(expectedOutput, outputStreamCaptor.toString().trim());

        verify(userServiceMock,times(1)).create(anyString());
    }




    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

}