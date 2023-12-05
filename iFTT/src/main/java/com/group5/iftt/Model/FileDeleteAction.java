package com.group5.iftt.Model;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileDeleteAction implements Action, Serializable {
    private String filePath;

    public FileDeleteAction(String filePath) {
        this.filePath = filePath;
    }

    public FileDeleteAction() {
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void startAction() {
        try {
            // Ottieni il percorso del file
            Path filePath = Path.of(this.filePath);

            // Elimina il file
            Files.delete(filePath);

            System.out.println("File eliminato con successo.");
        } catch (IOException e) {
            System.err.println("Errore durante l'eliminazione del file: " + e.getMessage());
        }
    }

    public String toString() {
        return "Elimina File";
    }
}
