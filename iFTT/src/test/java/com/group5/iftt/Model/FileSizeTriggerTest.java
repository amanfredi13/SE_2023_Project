package com.group5.iftt.Model;

import com.group5.iftt.Model.Triggers.FileSizeTrigger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileSizeTriggerTest {
    @Test
    void testIsValidateWhenFileExistsAndExceedsMaxSize() {
        // Crea un oggetto FileSizeTrigger con un percorso esistente e una dimensione massima sufficientemente grande
        FileSizeTrigger trigger = new FileSizeTrigger("/Users/alessandromanfredi/IdeaProjects/Clone_9Dicembre/iFTT/src/main/java/com/group5/iftt/componenti_prog/binaries.txt", "10");

        // Verifica che isValidate restituisca true perché la dimensione del file supera quella specificata
        assertTrue(trigger.isValidate());
    }

    @Test
    void testIsValidateWhenFileExistsAndDoesNotExceedMaxSize() {
        // Crea un oggetto FileSizeTrigger con un percorso esistente e una dimensione massima maggiore della dimensione del file
        FileSizeTrigger trigger = new FileSizeTrigger("/Users/alessandromanfredi/IdeaProjects/Clone_9Dicembre/iFTT/src/main/java/com/group5/iftt/componenti_prog/binaries.txt", "10000");

        // Verifica che isValidate restituisca false perché la dimensione del file non supera quella specificata
        assertFalse(trigger.isValidate());
    }

    @Test
    void testIsValidateWhenFileDoesNotExist() {
        // Crea un oggetto FileSizeTrigger con un percorso inesistente e una dimensione massima arbitraria
        FileSizeTrigger trigger = new FileSizeTrigger("nonexistent-file.txt", "100");

        // Verifica che isValidate generi un'eccezione IllegalArgumentException perché il file non esiste
        assertThrows(IllegalArgumentException.class, trigger::isValidate);
    }

    @Test
    void testIsValidateWithInvalidMaxSize() {
        // Crea un oggetto FileSizeTrigger con un percorso esistente e una dimensione massima non valida (non numerica)
        FileSizeTrigger trigger = new FileSizeTrigger("/Users/alessandromanfredi/IdeaProjects/Clone_9Dicembre/iFTT/src/main/java/com/group5/iftt/componenti_prog/binaries.txt", "invalid");

        // Verifica che isValidate generi un'eccezione NumberFormatException perché la dimensione massima non è un numero valido
        assertThrows(NumberFormatException.class, trigger::isValidate);
    }
}