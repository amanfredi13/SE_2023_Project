package com.group5.iftt.Model;


import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class FileDeleteActionTest {

    @Test
    void testStartAction() throws IOException {
        // Crea un oggetto FileDeleteAction per il test
        String filePath = "C:/Users/admin/OneDrive/Desktop/prove/binaries.txt";
        FileDeleteAction fileDeleteAction = new FileDeleteAction(filePath);


        fileDeleteAction.setFilePath(filePath);
        // Esegui l'azione di eliminazione del file
        System.out.println("Eseguo l'azione di eliminazione del file...");
        fileDeleteAction.startAction();

        // Verifica che il file sia stato effettivamente eliminato
        assertFalse(Files.exists(Path.of(filePath)), "Il file non è stato eliminato correttamente");
    }

    @Test
    void testToString() {
        // Crea un oggetto FileDeleteAction
        FileDeleteAction fileDeleteAction = new FileDeleteAction();

        // Verifica che il metodo toString restituisca la stringa attesa
        assertEquals("Elimina File", fileDeleteAction.toString());
        System.out.println("Il metodo toString restituisce la stringa attesa: "+ fileDeleteAction.toString());
    }

    @Test
    void testSetFilePath() {
        // Crea un oggetto FileDeleteAction
        FileDeleteAction fileDeleteAction = new FileDeleteAction();

        // Imposta il percorso del file
        String filePath = "C:/Users/admin/OneDrive/Desktop/prove/binaries.txt";
        fileDeleteAction.setFilePath(filePath);

        // Verifica che il valore sia stato impostato correttamente
        assertEquals(filePath, fileDeleteAction.getFilePath());
        System.out.println("Percorso del file da eliminiare: "+fileDeleteAction.getFilePath());
    }
}

