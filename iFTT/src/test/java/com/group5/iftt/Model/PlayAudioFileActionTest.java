package com.group5.iftt.Model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class PlayAudioFileActionTest {
    private  String validPath = "/com/group5/iftt/AudioPerTest/notifica.wav";

    @Test  //Test per assicurarci che il file path venga preso correttamente
    void setFilePath() {
        PlayAudioFileAction audioAction = new PlayAudioFileAction();
        assertEquals(validPath, audioAction.setFilePath(validPath));
    }

    @Test //Test riproduzione audio
    void ValidAudioFilePlaysAudio() {
        PlayAudioFileAction audioAction = new PlayAudioFileAction();
        audioAction.setFilePath(validPath);

        assertDoesNotThrow(() -> audioAction.audioAction(), "Expected no exception to be thrown");

        assertTrue(audioAction.audioPlayed);
    }


    }

