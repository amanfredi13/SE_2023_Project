package com.group5.iftt.Model;

import com.group5.iftt.Model.Triggers.FileSizeTrigger;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FileSizeTriggerTest {
    @Test
    public void testIsValidateWhenFileExistsAndExceedsMaxSize() {
        // Crea un oggetto FileSizeTrigger con un percorso esistente e una dimensione massima troppo grande
        FileSizeTrigger trigger = new FileSizeTrigger("src/test/componenti_test/ExceedSizefile.txt", "10000");

        // Verifica che isValidate restituisca false perché la dimensione del file supera quella specificata
        assertFalse(trigger.isValidate());
    }

    @Test
    public void testIsValidateWhenFileExistsAndDoesNotExceedMaxSize() {
        // Crea un oggetto FileSizeTrigger con un percorso esistente e una dimensione massima maggiore della dimensione del file
        FileSizeTrigger trigger = new FileSizeTrigger("src/test/componenti_test/binariesMax.txt", "10");

        // Verifica che isValidate restituisca true perché la dimensione del file non supera quella specificata
        assertTrue(trigger.isValidate());
    }

    @Test
    public void testIsValidateWhenFileDoesNotExist() {
        // Crea un oggetto FileSizeTrigger con un percorso inesistente e una dimensione massima arbitraria
        FileSizeTrigger trigger = new FileSizeTrigger("nonexistent-file.txt", "100");

        // Verifica che isValidate generi un'eccezione IllegalArgumentException perché il file non esiste
        assertThrows(IllegalArgumentException.class, trigger::isValidate);
    }

    @Test
    public void testConstructorWithInvalidMaxSize() {
        // Verifica che il costruttore generi un'eccezione IllegalArgumentException con una dimensione massima nulla o negativa.
        assertThrows(IllegalArgumentException.class, () -> new FileSizeTrigger("src/test/componenti_test/binaries.txt", "0").isValidate());
        assertThrows(IllegalArgumentException.class, () -> new FileSizeTrigger("src/test/componenti_test/binaries.txt", "-10").isValidate());
    }

    @Test
    public void testIsValidateWithInvalidMaxSize() {
        // Crea un oggetto FileSizeTrigger con un percorso esistente e una dimensione massima non valida (non numerica)
        FileSizeTrigger trigger = new FileSizeTrigger("src/test/componenti_test/binariesMax.txt", "invalid");

        // Verifica che isValidate generi un'eccezione IllegalArgumentException perché la dimensione massima non è un numero valido
        assertThrows(NumberFormatException.class, trigger::isValidate);
    }
}