package com.group5.iftt.Model;

import com.group5.iftt.Model.FileCopyAction;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class FileCopyActionTest {

    @Test
    void testStartAction() throws IOException {
        // Crea un oggetto FileCopyAction per il test
        String sourceFilePath = "C:/Users/admin/OneDrive/Desktop/prove/binaries.txt";
        String destinationFolderPath = "C:/Users/admin/OneDrive/Desktop/prove/cartella";
        FileCopyAction fileCopyAction = new FileCopyAction(sourceFilePath, destinationFolderPath);

        // Esegui l'azione di copia del file
        System.out.println("Eseguo l'azione di copia del file...");
        fileCopyAction.setCopyFile(sourceFilePath, destinationFolderPath);
        fileCopyAction.startAction();

        // Verifica che il file sia stato effettivamente copiato
        Path sourcePath = Paths.get(sourceFilePath);
        Path destinationPath = Paths.get(destinationFolderPath).resolve(sourcePath.getFileName());
        assertTrue(Files.exists(destinationPath), "Il file non è stato copiato correttamente");

        // Pulisci dopo il test eliminando il file copiato
        //Files.delete(destinationPath);
    }

    @Test
    void testToString() {
        // Crea un oggetto FileCopyAction
        FileCopyAction fileCopyAction = new FileCopyAction();

        // Verifica che il metodo toString restituisca la stringa attesa
        assertEquals("Copia File", fileCopyAction.toString());
        System.out.println("Il metodo toString restituisce la stringa attesa: "+fileCopyAction.toString());
    }

    @Test
    void testSetCopyFile() {
        // Crea un oggetto FileCopyAction
        FileCopyAction fileCopyAction = new FileCopyAction();

        // Imposta il file di origine e la cartella di destinazione
        String sourceFilePath = "C:/Users/admin/OneDrive/Desktop/prove/binaries.txt";
        String destinationFolderPath = "C:/Users/admin/OneDrive/Desktop/prove";
        fileCopyAction.setCopyFile(sourceFilePath, destinationFolderPath);

        // Verifica che i valori siano stati impostati correttamente utilizzando i metodi getter
        assertEquals(sourceFilePath, fileCopyAction.getSourceFilePath());
        assertEquals(destinationFolderPath, fileCopyAction.getDestinationFolderPath());
        System.out.println("Cartella di origine :"+ fileCopyAction.getSourceFilePath()+" Cartella di destinazione: "+ fileCopyAction.getDestinationFolderPath());
    }
}
