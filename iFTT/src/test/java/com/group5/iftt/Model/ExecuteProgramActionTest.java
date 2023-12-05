package com.group5.iftt.Model;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ExecuteProgramActionTest {
    @Test
    void testStartAction_SuccessfulExecution() {
        // Arrange
        String programPath = "/Users/alessandromanfredi/Desktop/script_prova/stampaNome.sh";
        String parameters = "Hello, World!";
        ExecuteProgramAction executeProgramAction = new ExecuteProgramAction(programPath, parameters);

        // Redirect System.out for testing
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Act
        executeProgramAction.startAction();

        // Restore System.out
        System.setOut(System.out);

        // Assert
        String expectedOutput = "Script eseguito con successo.\n";
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    void testStartAction_FailedExecution() {
        // Arrange
        String programPath = "/Users/alessandromanfredi/Desktop/script_prova/failScript.sh";
        String parameters = "ciao"; //devo fare uno script di prova che so che non ccetta un certo parametro e dovrebbe funzionare
        ExecuteProgramAction executeProgramAction = new ExecuteProgramAction(programPath, parameters);

        // Redirect System.err for testing
        ByteArrayOutputStream errorStream = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errorStream));

        // Act
        executeProgramAction.startAction();

        // Restore System.err
        System.setErr(System.err);

        // Assert
        String expectedError = "Errore durante l'esecuzione dello script. Codice di uscita: 1";
        assertEquals(expectedError, errorStream.toString());
    }



}