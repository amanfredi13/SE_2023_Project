package com.group5.iftt.Model.Actions;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
//Classe per l'azione di scrittura stringa a fine file
public class WriteEofAction implements Action, Serializable {
    private String filepath;
    private String stringToWrite;

    public WriteEofAction(String filepath, String stringToWrite) {
        this.filepath = filepath;
        this.stringToWrite = stringToWrite;

    }

    public WriteEofAction() {
    }

    @Override
    public void startAction() {
        // Scrivi la stringa alla fine del file
        try {
            Path percorso = Paths.get(filepath);
            if (!Files.exists(percorso)) {
                throw new IOException("Il percorso del file non esiste: " + percorso);
            }

            // Usa un BufferedWriter per scrivere alla fine del file
            BufferedWriter writer = new BufferedWriter(new FileWriter(percorso.toString(), true));

            // Scrivi la stringa alla fine del file
            writer.write(stringToWrite);
            writer.newLine(); // Aggiungi una nuova riga dopo la stringa
            writer.close();
            System.out.println("Stringa aggiunta con successo al file.");
        } catch (IOException e) {
            // Gestisci le eccezioni IO (ad esempio, file non trovato, permessi insufficienti, ecc.)
            System.err.println("Errore durante la scrittura del file: " + e.getMessage());
        }

    }
        public String toString () {
            return "Write EOF";
        }
    }



