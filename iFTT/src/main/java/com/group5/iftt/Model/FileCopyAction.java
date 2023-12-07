package com.group5.iftt.Model;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class FileCopyAction implements Action, Serializable {
    private String sourceFilePath;
    private String destinationFolderPath;

    public FileCopyAction(String sourceFilePath, String destinationFolderPath){
        this.sourceFilePath= sourceFilePath;
        this.destinationFolderPath= destinationFolderPath;
    }
    public FileCopyAction() {}


    public void setCopyFile(String sourceFilePath, String destinationFolderPath) {
        this.sourceFilePath= sourceFilePath;
        this.destinationFolderPath= destinationFolderPath;
    }


    @Override
    public void startAction() {
        try {
            // Ottieni il percorso del file di origine
            Path sourcePath = Path.of(sourceFilePath);

            // Ottieni il percorso della cartella di destinazione
            Path destinationPath = Path.of(destinationFolderPath);

            // Effettua la copia del file nella cartella di destinazione
            Files.copy(sourcePath, destinationPath.resolve(sourcePath.getFileName()), StandardCopyOption.REPLACE_EXISTING);

            System.out.println("File copiato con successo.");
        } catch (IOException e) {
            System.err.println("Errore durante la copia del file: " + e.getMessage());
        }
    }

    public String getSourceFilePath() {
        return sourceFilePath;
    }

    public String getDestinationFolderPath() {
        return destinationFolderPath;
    }
    public String toString(){
        return "Copia File";
    }
}

