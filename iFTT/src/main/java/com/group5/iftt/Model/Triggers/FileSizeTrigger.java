package com.group5.iftt.Model.Triggers;
import java.io.File;
import java.io.Serializable;

public class FileSizeTrigger implements Trigger, Serializable {
    private  String filePath;
    private String maxSizeInBytes;

    public FileSizeTrigger(String filePath, String maxSizeInBytes){
        this.filePath = filePath;
        this.maxSizeInBytes = maxSizeInBytes;
    }
    public FileSizeTrigger(){}
    @Override
    public boolean isValidate() {
        // Verifica se il file esiste
        File file = new File(filePath);
        if (!file.exists()) {
            throw new IllegalArgumentException("Il file non esiste.");
        }
        // Ottieni la dimensione del file in byte
        long fileSizeInBytes = file.length();

        // Verifica se la dimensione supera quella specificata
        return fileSizeInBytes > Integer.parseInt(maxSizeInBytes) ;
    }

    public String toString(){
        return "File size";
    }
}
