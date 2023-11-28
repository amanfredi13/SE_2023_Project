package com.group5.iftt.Model;

import javafx.application.Platform;
import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.*;

class WriteEofActionTest {
    @Test
    public void testWriteEofAction(){
        // Set up test data
        String testFilePath = "src/test/java/com/group5/iftt/Model/TestFiles/fileText.txt";
        String testStringToWrite = "TestString";

        // Create an instance of WriteEofAction
        WriteEofAction writeEofAction = new WriteEofAction(testFilePath, testStringToWrite);

        // Perform the action using Platform.runLater

            writeEofAction.startAction();

            // Verify that the string is written to the file
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

            // Clean up: Delete the test file
            try {
                Files.deleteIfExists(Paths.get(testFilePath));
            } catch (IOException e) {
                fail("Failed to delete the test file.");
            }

    }

    @Test
    public void testWriteEofActionWithEmptyString(){
        // Set up test data
        String testFilePath = "src/test/java/com/group5/iftt/Model/TestFiles/fileText.txt";
        String testStringToWrite = "";

        // Create an instance of WriteEofAction
        WriteEofAction writeEofAction = new WriteEofAction(testFilePath, testStringToWrite);

        writeEofAction.startAction();


            // Verify that an empty string is written to the file
            try (BufferedReader reader = new BufferedReader(new FileReader(testFilePath))) {
                String line = reader.readLine();
                assertNotNull(line, "File is empty.");
                assertEquals(testStringToWrite, line, "String does not match the expected value.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            // Clean up: Delete the test file
            try {
                Files.deleteIfExists(Paths.get(testFilePath));
            } catch (IOException e) {
                fail("Failed to delete the test file.");
            }

    }

}