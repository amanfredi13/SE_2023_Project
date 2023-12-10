package com.group5.iftt.Model;

import com.group5.iftt.Model.Actions.Action;
import com.group5.iftt.Model.Actions.ShowDialogBoxAction;
import com.group5.iftt.Model.RuleAndSerialize.Rule;
import com.group5.iftt.Model.Triggers.TimeOfDayTrigger;
import com.group5.iftt.Model.Triggers.Trigger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RuleTest {

    @Test
    public void testSettersAndGetters() {
        Action action1 = new ShowDialogBoxAction();
        Action action2 = new ShowDialogBoxAction();
        Trigger trigger1 = new TimeOfDayTrigger("12", "30");
        Trigger trigger2 = new TimeOfDayTrigger("14", "45");

        Rule rule = new Rule("TestRule", action1, trigger1, "Enabled");

        System.out.println("Original Rule:\n" + rule);

        rule.setName("UpdatedName");
        rule.setAction(action2);
        rule.setTrigger(trigger2);
        rule.setStatus("Disabled");

        System.out.println("Updated Rule:\n" + rule);

        assertEquals("UpdatedName", rule.getName());
        assertEquals(action2, rule.getAction());
        assertEquals(trigger2, rule.getTrigger());
        assertEquals("Disabled", rule.getStatus());
        assertFalse(rule.isActive());

        // Print success message
        System.out.println("testSettersAndGetters passed successfully!\n");
    }

    @Test
    public void testActionStarted() {
        Rule rule = new Rule("TestRule", new ShowDialogBoxAction(), new TimeOfDayTrigger("12", "30"), "Enabled");
        assertFalse(rule.isActionStarted());

        System.out.println("Rule before setting ActionStarted:\n" + rule.toString());

        rule.setActionStarted(true);
        assertTrue(rule.isActionStarted());

        System.out.println("Rule after setting ActionStarted to true:\n" + rule.toString());

        rule.setActionStarted(false);
        assertFalse(rule.isActionStarted());

        System.out.println("Rule after setting ActionStarted to false:\n" + rule.toString());

        // Print success message
        System.out.println("testActionStarted passed successfully!\n");
    }

}
