package com.group5.iftt.Model;
import com.group5.iftt.Model.Action;
import javafx.application.Platform;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
public class PlayAudioFileAction implements Action {
   private String filePath;
   public volatile boolean audioPlayed = false;

    public PlayAudioFileAction(){

    }


    public String setFilePath(String filePath){
       return this.filePath = filePath;
    }

    public void startAction(){
        if(!audioPlayed){
                audioAction();
        }}

    public void audioAction() {
        File audioFile = new File(filePath);

        try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile)) {
            DataLine.Info info = new DataLine.Info(Clip.class, audioInputStream.getFormat());
            Clip clip = (Clip) AudioSystem.getLine(info);
            clip.open(audioInputStream);
            clip.start();
            audioPlayed = true;
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            e.printStackTrace();
            System.err.println("Errore durante la riproduzione dell'audio " + e.getMessage());
        }
    }



    public String toString(){
        return "Riproduzione Audio";
    }

}