package com.group5.iftt.Model;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;


public class ShowDialogBoxActionTest {


    @Test
    public void testSetMessage() {
        ShowDialogBoxAction showDialogBoxAction = new ShowDialogBoxAction();
        String expectedMessage = "Test Message";

        showDialogBoxAction.setMessage(expectedMessage);

        // Verifica che il messaggio sia stato settato correttamente
        assertEquals(expectedMessage, showDialogBoxAction.getMessage());
    }

}
