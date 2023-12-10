package com.group5.iftt.Model;

import com.group5.iftt.Model.Actions.PlayAudioFileAction;
import com.group5.iftt.Model.RuleAndSerialize.Rule;
import com.group5.iftt.Model.RuleAndSerialize.SerializeList;
import com.group5.iftt.Model.Triggers.TimeOfDayTrigger;
import javafx.collections.FXCollections;

import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

public class SerializeListTest {

    private ObservableList<Rule> ruleList;
    private SerializeList serializeList;
    private String testFilePath = "testSerializeList.txt";

    @BeforeEach
    public void setUp() {
        // Initialization of the rule list for the test
        ruleList = FXCollections.observableArrayList(
                new Rule("Rule1", new PlayAudioFileAction(), new TimeOfDayTrigger("10", "30"), "Enabled"),
                new Rule("Rule2", new PlayAudioFileAction(), new TimeOfDayTrigger("17", "30"), "Enabled")
        );

        // File path for the test
        serializeList = new SerializeList(ruleList, testFilePath);
    }

    @Test
    public void serializeAndDeserialize() {
        // Serialization
        serializeList.serialize();

        // Check that the output file exists
        File outputFile = new File(testFilePath);
        assertTrue(outputFile.exists());

        // Deserialization
        List<Rule> deserializedList = SerializeList.deserialize(testFilePath);

        // Check that the deserialized list is not null
        assertNotNull(deserializedList);

        // Check each rule in the list
        for (int i = 0; i < ruleList.size(); i++) {
            Rule originalRule = ruleList.get(i);
            Rule deserializedRule = deserializedList.get(i);

            // Print before and after the comparison
            System.out.println("Original Rule: " + originalRule);
            System.out.println("Deserialized Rule: " + deserializedRule);

            // Confronto
            if (originalRule.toString().equals(deserializedRule.toString())) {
                System.out.println("Rules at index " + i + " are equal!");
            } else {
                assertEquals(originalRule.toString(), deserializedRule.toString(),
                        "Details don't match for Rule at index " + i);
            }

        }
    }
}
