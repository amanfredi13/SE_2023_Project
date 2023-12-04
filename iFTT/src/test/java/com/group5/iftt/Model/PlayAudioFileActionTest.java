package com.group5.iftt.Model;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayAudioFileActionTest {

    @Test
    void audioAction_PlaysAudioWithoutExceptions() {
        // Specifica il percorso del file audio di test
        String validPath = "notifica.wav";

        // Crea un'istanza di PlayAudioFileAction con il percorso specificato
        PlayAudioFileAction audioAction = new PlayAudioFileAction(validPath);

        // Verifica che l'azione audio non lanci eccezioni
        assertDoesNotThrow(() -> audioAction.audioAction(), "Expected no exception to be thrown");

        // Verifica che la variabile audioPlayed sia impostata su true dopo l'azione
        assertTrue(audioAction.audioPlayed, "Expected audio to be played");
    }

    @Test
    void startAction_PlaysAudioWithoutExceptions() {
        // Specifica il percorso del file audio di test
        String validPath = "notifica.wav";

        // Crea un'istanza di PlayAudioFileAction con il percorso specificato
        PlayAudioFileAction audioAction = new PlayAudioFileAction(validPath);

        // Verifica che l'azione audio non lanci eccezioni
        assertDoesNotThrow(() -> audioAction.startAction(), "Expected no exception to be thrown");

        // Verifica che la variabile audioPlayed sia impostata su true dopo l'azione
        assertTrue(audioAction.audioPlayed, "Expected audio to be played");
    }

    @Test
    void toString_ReturnsCorrectString() {
        // Crea un'istanza di PlayAudioFileAction senza specificare il percorso
        PlayAudioFileAction audioAction = new PlayAudioFileAction();

        // Verifica che il metodo toString restituisca la stringa corretta
        assertEquals("Riproduzione Audio", audioAction.toString(), "Expected correct string representation");
    }
}

