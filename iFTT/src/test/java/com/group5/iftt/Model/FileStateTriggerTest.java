package com.group5.iftt.Model;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileStateTriggerTest {
    @Test
    void testIsValidateWhenFileExists() {
        // Crea un oggetto FileStateTrigger con un percorso esistente e un nome di file esistente
        FileStateTrigger trigger = new FileStateTrigger("/Users/alessandromanfredi/IdeaProjects/cloned_project_8/iFTT/src/main/java/com/group5/iftt/componenti_prog", "binaries.txt");
        // Verifica che isValidate restituisca true perché il file esiste
        assertTrue(trigger.isValidate());
    }

    @Test
    void testIsValidateWhenFileDoesNotExist() {
        // Crea un oggetto FileStateTrigger con un percorso esistente e un nome di file inesistente
        FileStateTrigger trigger = new FileStateTrigger("iFTT/src/main/java/com/group5/iftt/componenti_prog", "nonExistingFile.txt");

        // Verifica che isValidate restituisca false perché il file non esiste
        assertFalse(trigger.isValidate());
    }

    @Test
    void testIsValidateWithEmptyPathAndFileName() {
        // Crea un oggetto FileStateTrigger con path e nome del file vuoti
        FileStateTrigger trigger = new FileStateTrigger("", "");

        // Verifica che isValidate restituisca false perché il percorso e il nome del file sono vuoti
        assertFalse(trigger.isValidate());
    }

    @Test
    void testToString() {
        // Crea un oggetto FileStateTrigger
        FileStateTrigger trigger = new FileStateTrigger("/path/to/directory", "exampleFile.txt");

        // Verifica che il metodo toString restituisca la stringa attesa
        assertEquals("Esistenza File", trigger.toString());
    }
}