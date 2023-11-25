package com.group5.iftt;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
public class PlayAudioFileAction implements Action {
    private String filePath;

    public PlayAudioFileAction() {

    }


    public void startAction(){

    }

    public void audioAction(String filePath) {

        boolean audioPlayed = false;
        File audioFile = new File(filePath);

        try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile)) {
            DataLine.Info info = new DataLine.Info(Clip.class, audioInputStream.getFormat());
            Clip clip = (Clip) AudioSystem.getLine(info);
            clip.open(audioInputStream);
            clip.start();
            audioPlayed = true;  // Imposta la variabile a true dopo la riproduzione
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }



    public String toString(){
        return "Riproduzione Audio";
    }

}