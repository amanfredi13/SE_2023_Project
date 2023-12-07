package com.group5.iftt.Model;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class FileMoveActionTest {

    @Test
    void testStartAction() throws IOException {
        // Crea un oggetto FileMoveAction per il test
        String sourceFilePath = "C:/Users/admin/OneDrive/Desktop/prove/binaries.txt";
        String destinationFolderPath = "C:/Users/admin/OneDrive/Desktop/prove/cartella";
        FileMoveAction fileMoveAction = new FileMoveAction(sourceFilePath, destinationFolderPath);

      fileMoveAction.setMoveFile(sourceFilePath, destinationFolderPath);


        // Esegui l'azione di spostamento del file
        System.out.println("Eseguo l'azione di spostamento del file...");
        fileMoveAction.startAction();

        // Verifica che il file sia stato effettivamente spostato
        Path sourcePath = Paths.get(sourceFilePath);
        Path destinationPath = Paths.get(destinationFolderPath).resolve(sourcePath.getFileName());
        assertTrue(Files.exists(destinationPath), "Il file non è stato spostato correttamente");

        // Pulisci dopo il test eliminando il file spostato
        //Files.delete(destinationPath);
    }

    @Test
    void testToString() {
        // Crea un oggetto FileMoveAction
        FileMoveAction fileMoveAction = new FileMoveAction();

        // Verifica che il metodo toString restituisca la stringa attesa
        assertEquals("Sposta File", fileMoveAction.toString());
        System.out.println("Il metodo toString restituisce la stringa attesa: "+fileMoveAction.toString());
    }

    @Test
    void testSetMoveFile() {
        // Crea un oggetto FileMoveAction
        FileMoveAction fileMoveAction = new FileMoveAction();

        // Imposta il file di origine e la cartella di destinazione
        String sourceFilePath = "C:/Users/admin/OneDrive/Desktop/prove/binaries.txt";
        String destinationFolderPath = "C:/Users/admin/OneDrive/Desktop/prove/cartella";
        fileMoveAction.setMoveFile(sourceFilePath, destinationFolderPath);

        // Verifica che i valori siano stati impostati correttamente
        assertEquals(sourceFilePath, fileMoveAction.getSourceFilePath());
        assertEquals(destinationFolderPath, fileMoveAction.getDestinationFolderPath());
        System.out.println("Cartella di origine: "+ fileMoveAction.getSourceFilePath()+" Cartella di destinazione: "+ fileMoveAction.getDestinationFolderPath());
    }
}

