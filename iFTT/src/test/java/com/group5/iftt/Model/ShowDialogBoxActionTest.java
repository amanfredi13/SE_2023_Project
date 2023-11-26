package com.group5.iftt.Model;
import com.group5.iftt.Main;
import com.group5.iftt.Model.ShowDialogBoxAction;

import javafx.application.Application;
import javafx.application.Platform;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;
import static org.testfx.util.WaitForAsyncUtils.waitForFxEvents;


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
