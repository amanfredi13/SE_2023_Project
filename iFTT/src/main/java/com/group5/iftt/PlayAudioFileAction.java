package com.group5.iftt;

public class PlayAudioFileAction implements Action {
    private String filePath;

    public PlayAudioFileAction(String filePath) {
        this.filePath = filePath;
    }

    public void audioAction(String filePath){
        //CODICE PER ESEGUIRE AUDIO
    }

    public void startAction(){
        //CODICE PER RIPRODURRE IL FILE AUDIO
    }

    public String toString(){
        return "Riproduzione Audio";
    }

}