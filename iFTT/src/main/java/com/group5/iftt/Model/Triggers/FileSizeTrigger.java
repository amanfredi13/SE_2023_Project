package com.group5.iftt.Model.Triggers;
import java.io.File;
import java.io.Serializable;

public class FileSizeTrigger implements Trigger, Serializable {
    private  String filePath;
    private String maxSizeInBytes;

    private final long BYTE_MAX_SIZE = 1000;

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
        //Verifica che l'utente non abbia inserito un numero minore o uguale a 0
        if(Integer.parseInt(maxSizeInBytes)<=0){
            throw new IllegalArgumentException("La dimensione del file non è un numero valido.");
        }

        // Ottieni la dimensione del file in byte come long dal momento che potrebbe essere un numero grande
        long fileSizeInBytes = file.length();
        //Se la dimensione del file è più grande della massima dimensione allora restituisco false
        if(fileSizeInBytes>BYTE_MAX_SIZE){
            return false;
        }
        try {
            // Verifica se fileSizeInBytes è convertibile in un numero e maggiore di 0
            long fileSize = Long.parseLong(String.valueOf(fileSizeInBytes));
            if (fileSize<=0 ) {
                throw new IllegalArgumentException("La dimensione del file non è un numero valido.");
            }

            // Verifica se la dimensione supera quella specificata
            long maxSize = Long.parseLong(maxSizeInBytes);
            return fileSize > maxSize;
        } catch (NumberFormatException e) {
            throw new NumberFormatException("La dimensione del file non è un numero valido.");
        }
    }

    public String toString(){
        return "File size";
    }
}
