package com.group5.iftt;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
public class PlayAudioFileAction implements Action {
   private String filePath;
   private volatile boolean audioPlayed = false;

    public PlayAudioFileAction() {
        //path = this.setFilePath(path);
    }


    public String setFilePath(String filePath){
       return this.filePath = filePath;
    }

    public void startAction(){
        if(!audioPlayed){
            audioAction();
            audioPlayed = true;
        }

    }

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