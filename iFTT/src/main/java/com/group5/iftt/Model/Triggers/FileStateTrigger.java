package com.group5.iftt.Model.Triggers;

import java.io.File;
import java.io.Serializable;

public class FileStateTrigger implements Trigger, Serializable {

    private String filepathDir;
    private String filename;
    public FileStateTrigger (String filepathDir, String filename){
        this.filepathDir = filepathDir;
        this.filename = filename;
    }
    public FileStateTrigger(){}
    public String toString(){ return "Esistenza File";}

    @Override
    public boolean isValidate() {//se entrambi i campi sono vuoti restituisco falso
        if (filepathDir.isEmpty() && filename.isEmpty()) {
            return false;
        } else {
            //Costruisco il path dato dalla somma della cartella in cui cercare il file + carattere separatore (cambia a seconda del sistema operativo) + nome del file
            String percorsoCompleto = filepathDir + File.separator + filename;
            //Se tale percorso esiste porta a un file mi viene restituito true
            File file = new File(percorsoCompleto);
            return file.exists();
        }
    }
    }
