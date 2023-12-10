package com.group5.iftt.Model.Actions;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class FileMoveAction implements Action, Serializable {
    private String sourceFilePath;
    private String destinationFolderPath;

    public FileMoveAction(String sourceFilePath, String destinationFolderPath) {
        this.sourceFilePath = sourceFilePath;
        this.destinationFolderPath = destinationFolderPath;
    }

    public FileMoveAction() {
    }

    public void setMoveFile(String sourceFilePath, String destinationFolderPath) {
        this.sourceFilePath = sourceFilePath;
        this.destinationFolderPath = destinationFolderPath;
    }

    @Override
    public void startAction() {
        try {
            // Ottieni il percorso del file di origine
            Path sourcePath = Path.of(sourceFilePath);

            // Ottieni il percorso della cartella di destinazione
            Path destinationPath = Path.of(destinationFolderPath);

            // Effettua lo spostamento del file nella cartella di destinazione
            Files.move(sourcePath, destinationPath.resolve(sourcePath.getFileName()), StandardCopyOption.REPLACE_EXISTING);

            System.out.println("File spostato con successo.");
        } catch (IOException e) {
            System.err.println("Errore durante lo spostamento del file: " + e.getMessage());
        }
    }

    public String toString() {
        return "Move file";
    }

    public String getSourceFilePath() {
    return sourceFilePath;
    }

    public String getDestinationFolderPath() {
        return destinationFolderPath;
    }
}

