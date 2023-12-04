package com.group5.iftt.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SerializeListTest {

    private ObservableList<Rule> ruleList;
    private SerializeList serializeList;
    private String testFilePath = "testSerializeList.txt";



    @BeforeEach
    void setUp() {
        // Inizializzazione della lista di regole per il test
        ruleList = FXCollections.observableArrayList(
                new Rule("Rule1", "Condition1", new PlayAudioFileAction(), "Enabled", "10", "30"),
                new Rule("Rule2", "Condition2", new PlayAudioFileAction(), "Enabled", "17", "30")

        );

        // Percorso del file di test
        serializeList = new SerializeList(ruleList, testFilePath);
    }

    @Test
    void serializeAndDeserialize() {
        // Serializzazione
        serializeList.serialize();

        // Verifica che il file di output esista
        File outputFile = new File(testFilePath);
        assertTrue(outputFile.exists());

        // Deserializzazione
        List<Rule> deserializedList = SerializeList.deserialize(testFilePath);

        // Verifica che la lista deserializzata non sia nulla
        assertNotNull(deserializedList);


        for (int i = 0; i < ruleList.size(); i++) {
                Rule originalRule = ruleList.get(i);
                Rule deserializedRule = deserializedList.get(i);

                // Stampa il prima e dopo del confronto
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