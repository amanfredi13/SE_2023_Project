package com.group5.iftt.Model;

import com.group5.iftt.Model.Actions.PlayAudioFileAction;
import com.group5.iftt.Model.Actions.ShowDialogBoxAction;
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
        // Inizializzazione rulelist per il test
        ruleList = FXCollections.observableArrayList(
                new Rule("Rule1", new PlayAudioFileAction(), new TimeOfDayTrigger("10", "30"), "Enabled"),
                new Rule("Rule2", new PlayAudioFileAction(), new TimeOfDayTrigger("17", "30"), "Enabled"),
                new Rule("Rule3", new ShowDialogBoxAction(), new TimeOfDayTrigger("18","30"), "Enabled")
        );


        serializeList = new SerializeList(ruleList, testFilePath);
    }

    @Test
    public void serializeAndDeserialize() {

        serializeList.serialize();

        // Controllo che l'output file esista
        File outputFile = new File(testFilePath);
        assertTrue(outputFile.exists());

        //Deserializzo
        List<Rule> deserializedList = SerializeList.deserialize(testFilePath);

        // Mi assicuro che non sia nulla
        assertNotNull(deserializedList);

        // Controllo ogni regola nella lista
        for (int i = 0; i < ruleList.size(); i++) {
            Rule originalRule = ruleList.get(i);
            Rule deserializedRule = deserializedList.get(i);


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

    @Test
    public void testDeserializeEmptyFile() {
        // Create an empty file
        File emptyFile = new File("emptySerializeList.txt");
        SerializeList.deserialize(emptyFile.getPath());

        // Ensure that no exceptions are thrown
        assertTrue(true);
    }

    @Test
    public void testDeserializeNonExistentFile() {
        // Tentativo di accesso a un file non esistente
        List<Rule> deserializedList = SerializeList.deserialize("nonExistentFile.txt");

        // Mi assicuro che la lista di deserealizzazione sia vuota
        assertTrue(deserializedList.isEmpty());
    }

    @Test
    public void testDeserializeInvalidData() {
        // Prendo un file con dati non deserializzabili
        File invalidDataFile = new File("iFTT/src/test/componenti_test/ExceedSizefile.txt");
        SerializeList.deserialize(invalidDataFile.getPath());

       //Mi assicuro che non si sollevino eccezioni nel caso in cui provo a deserializzare un file senza dati serializzabili
        assertTrue(true);
    }


}
