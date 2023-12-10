package com.group5.iftt.Model;
import com.group5.iftt.Model.Triggers.FileStateTrigger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileStateTriggerTest {
    @Test
    public void testIsValidateWhenFileExists() {
        // Crea un oggetto FileStateTrigger con un percorso esistente e un nome di file esistente
        FileStateTrigger trigger = new FileStateTrigger("src/test/componenti_test", "binaries.txt");
        // Verifica che isValidate restituisca true perché il file esiste
        assertTrue(trigger.isValidate());
    }

    @Test
    public void testIsValidateWhenFileDoesNotExist() {
        // Crea un oggetto FileStateTrigger con un percorso esistente e un nome di file inesistente
        FileStateTrigger trigger = new FileStateTrigger("src/test/componenti_test", "nonExistingFile.txt");

        // Verifica che isValidate restituisca false perché il file non esiste
        assertFalse(trigger.isValidate());
    }

    @Test
    public void testIsValidateWithEmptyPathAndFileName() {
        // Crea un oggetto FileStateTrigger con path e nome del file vuoti
        FileStateTrigger trigger = new FileStateTrigger("", "");

        // Verifica che isValidate restituisca false perché il percorso e il nome del file sono vuoti
        assertFalse(trigger.isValidate());
    }

    @Test
    public void testToString() {
        // Crea un oggetto FileStateTrigger
        FileStateTrigger trigger = new FileStateTrigger("/path/to/directory", "exampleFile.txt");

        // Verifica che il metodo toString restituisca la stringa attesa
        assertEquals("Esistenza File", trigger.toString());
    }
}