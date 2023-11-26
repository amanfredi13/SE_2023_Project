package com.group5.iftt.Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RuleTest {


    @Test
    void testSettersAndGetters() {
        Action action1 = new MockAction();
        Action action2 = new MockAction();
        TimeOfDayTrigger timeTrigger1 = new TimeOfDayTrigger("12", "30");
        TimeOfDayTrigger timeTrigger2 = new TimeOfDayTrigger("14", "45");

        Rule rule = new Rule("TestRule", "TestCondition", action1, "Enabled", "12", "30");

        rule.setName("UpdatedName");
        rule.setCondition("UpdatedCondition");
        rule.setAction(action2);
        rule.setStatus("Disabled");
        rule.setTimeTrigger(timeTrigger2);

        assertEquals("UpdatedName", rule.getName());
        assertEquals("UpdatedCondition", rule.getCondition());
        assertEquals(action2, rule.getAction());
        assertEquals("Disabled", rule.getStatus());
        assertEquals(timeTrigger2, rule.getTimeTrigger());
        assertFalse(rule.isActive());
    }

    @Test
    void testActionStarted() {
        Rule rule = new Rule("TestRule", "TestCondition", new MockAction(), "Enabled", "12", "30");

        assertFalse(rule.isActionStarted());

        rule.setActionStarted(true);

        assertTrue(rule.isActionStarted());

        rule.setActionStarted(false);

        assertFalse(rule.isActionStarted());
    }

    private static class MockAction implements Action {
        @Override
        public void startAction() {}
    }
}