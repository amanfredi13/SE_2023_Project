package com.group5.iftt.Model;

import com.group5.iftt.Model.Actions.ExecuteProgramAction;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class ExecuteProgramActionTest {
    @Test
   public void testStartAction_SuccessfulExecution() {
        String programPath = "src/test/componenti_test/stampaNome.sh";
        String parameters = "Alessandro"; //Nome valido.
        ExecuteProgramAction executeProgramAction = new ExecuteProgramAction(programPath, parameters);

        // Redirect System.out per fare testing
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        executeProgramAction.startAction();

        // Restore System.out
        System.setOut(System.out);

        String expectedOutput = "Script eseguito con successo.\n";
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testStartAction_FailedExecution() {

        String programPath = "src/test/componenti_test/stampaNome.sh";
        String parameters = "Andrea"; //Nome per cui lo script restituisce codice di errore 1.
        ExecuteProgramAction executeProgramAction = new ExecuteProgramAction(programPath, parameters);

        // Redirect System.err per fare testing
        ByteArrayOutputStream errorStream = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errorStream));

        executeProgramAction.startAction();

        // Restore System.err
        System.setErr(System.err);

        String expectedError = "Errore durante l'esecuzione dello script. Codice di uscita: 1\n";
        assertEquals(expectedError, errorStream.toString());
    }
    @Test
    public void testStartAction_NullProgramPath() {
        String parameters = "Alessandro"; // Nome valido.
        ExecuteProgramAction executeProgramAction = new ExecuteProgramAction(null, parameters);

        // Redirect System.err per fare testing
        ByteArrayOutputStream errorStream = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errorStream));

        executeProgramAction.startAction();

        // Restore System.err
        System.setErr(System.err);

        String expectedError = "Errore durante l'esecuzione dello script. Codice di uscita: 1\n";
        assertEquals(expectedError, errorStream.toString());
    }

    @Test
    public void testStartAction_EmptyProgramPath() {
        String parameters = "Alessandro"; // Nome valido.
        ExecuteProgramAction executeProgramAction = new ExecuteProgramAction("", parameters);

        // Redirect System.err per fare testing
        ByteArrayOutputStream errorStream = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errorStream));

        executeProgramAction.startAction();

        // Restore System.err
        System.setErr(System.err);

        String expectedError = "Errore durante l'esecuzione dello script. Codice di uscita: 1\n";
        assertEquals(expectedError, errorStream.toString());
    }

}