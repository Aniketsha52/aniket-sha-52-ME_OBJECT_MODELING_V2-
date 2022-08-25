package com.crio.jukebox.commands;

import com.crio.jukebox.entites.User;
import com.crio.jukebox.services.IsongService;
import com.crio.jukebox.services.IuserService;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class LoadDataCommandTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Mock
    IsongService songServiceMock;
    private AutoCloseable closeable;
    @InjectMocks
    LoadDataCommand loadDataCommand;

    @BeforeEach
    void initService() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @BeforeEach
    public void setUp() {

        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    @DisplayName("execute method of loadDataCommand Should Print message To Console")
    public void execute() {
        //Arrange
        String expectedOutput = "Songs Loaded successfully";
        String file = "C:\\Users\\anike\\IdeaProjects\\ME_OBJECT_MODELING_V2_MODULE_TAKEHOME_STUB\\songs.csv";
        doThrow(new RuntimeException()).when(songServiceMock).loadData(file);

        //Act
        loadDataCommand.execute(List.of("LOAD-DATA ","file"));

        //Assert
        Assertions.assertEquals(expectedOutput, outputStreamCaptor.toString().trim());

        verify(songServiceMock,times(1)).loadData(anyString());
    }




    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

}