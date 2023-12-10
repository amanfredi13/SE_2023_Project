package com.group5.iftt.Model;

import com.group5.iftt.Model.Actions.ExecuteProgramAction;
import com.group5.iftt.Model.Actions.WriteEofAction;
import javafx.application.Platform;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.*;

public class WriteEofActionTest {
    @Test
    public void testWriteEofAction(){
        // Set up test data
        String testFilePath = "src/test/componenti_test/fileText.txt";
        String testStringToWrite = "TestString";
        WriteEofAction writeEofAction = new WriteEofAction(testFilePath, testStringToWrite);

            writeEofAction.startAction();

            // Verifico che la stringa sia scritta sul file
            try (BufferedReader reader = new BufferedReader(new FileReader(testFilePath))) {
                String line;
                boolean stringFound = false;
                while ((line = reader.readLine()) != null) {
                    if (line.equals(testStringToWrite)) {
                        stringFound = true;
                        break;
                    }
                }
                assertTrue(stringFound, "String not found in the file.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

    }

    @Test
    public void testWriteEofActionWithEmptyString() {
        // Set up test data
        String testFilePath = "src/test/componenti_test/fileText.txt";
        String testStringToWrite = "";
        WriteEofAction writeEofAction = new WriteEofAction(testFilePath, testStringToWrite);


        writeEofAction.startAction();

        // Verifico che anche il caso di scrittura di una stringa vuota sia permesso anche se già da UI ci si assicura che tale campo non sia vuoto.
        try (BufferedReader reader = new BufferedReader(new FileReader(testFilePath))) {
            String line = reader.readLine();
            assertNotNull(line, "File is empty.");
            assertEquals(testStringToWrite, line, "String does not match the expected value.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testWriteEofActionWithNonExistentFile() {
        // Imposto variabili per il test
        String nonExistentFilePath = "src/test/componenti_test/nonExistentFile.txt";
        String testStringToWrite = "TestString";
        WriteEofAction writeEofAction = new WriteEofAction(nonExistentFilePath, testStringToWrite);

        // Redirect System.err per fare testing
        ByteArrayOutputStream errorStream = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errorStream));

        //Eseguo l'azione
        writeEofAction.startAction();

        // Restore System.err
        System.setErr(System.err);

        String expectedError = "Errore durante la scrittura del file: Il percorso del file non esiste: src/test/componenti_test/nonExistentFile.txt\n";
        assertEquals(expectedError, errorStream.toString());
    }

}