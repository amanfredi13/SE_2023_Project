package com.group5.iftt.Model;

import com.group5.iftt.Model.Actions.PlayAudioFileAction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayAudioFileActionTest {

    @Test
    public void audioAction_PlaysAudioWithoutExceptions() {
        // Specifica il percorso del file audio di test
        String validPath = "src/test/componenti_test/notifica.wav";

        // Crea un'istanza di PlayAudioFileAction con il percorso specificato
        PlayAudioFileAction audioAction = new PlayAudioFileAction(validPath);

        // Verifica che l'azione audio non lanci eccezioni
        assertDoesNotThrow(() -> audioAction.audioAction(), "Expected no exception to be thrown");

        // Verifica che la variabile audioPlayed sia impostata su true dopo l'azione
        assertTrue(audioAction.isAudioPlayed(), "Expected audio to be played");
    }

    @Test
    public void startAction_PlaysAudioWithoutExceptions() {
        // Specifica il percorso del file audio di test
        String validPath = "src/test/componenti_test/notifica.wav";

        // Crea un'istanza di PlayAudioFileAction con il percorso specificato
        PlayAudioFileAction audioAction = new PlayAudioFileAction(validPath);

        // Verifica che l'azione audio non lanci eccezioni
        assertDoesNotThrow(() -> audioAction.startAction(), "Expected no exception to be thrown");

        // Verifica che la variabile audioPlayed sia impostata su true dopo l'azione
        assertTrue(audioAction.isAudioPlayed(), "Expected audio to be played");
    }

    @Test
    public void toString_ReturnsCorrectString() {
        // Crea un'istanza di PlayAudioFileAction senza specificare il percorso
        PlayAudioFileAction audioAction = new PlayAudioFileAction();

        // Verifica che il metodo toString restituisca la stringa corretta
        assertEquals("Play audio", audioAction.toString(), "Expected correct string representation");
    }
}

