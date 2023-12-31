package com.group5.iftt.Model;

import com.group5.iftt.Model.Actions.ShowDialogBoxAction;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShowDialogBoxActionTest {


    // Verifica che il messaggio sia stato impostato correttamente
    @Test
    public void testSetMessage() {
        ShowDialogBoxAction showDialogBoxAction = new ShowDialogBoxAction();
        String expectedMessage = "Messaggio di test";

        showDialogBoxAction.setMessage(expectedMessage);

        // Verifica che il messaggio sia stato settato correttamente
        assertEquals(expectedMessage, showDialogBoxAction.getMessage());
    }

}
