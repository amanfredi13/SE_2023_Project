package com.group5.iftt.Model;

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
    public boolean isValidate() {
        String percorsoCompleto = filepathDir + File.separator + filename;
        File file = new File(percorsoCompleto);
        return file.exists();
    }
}
