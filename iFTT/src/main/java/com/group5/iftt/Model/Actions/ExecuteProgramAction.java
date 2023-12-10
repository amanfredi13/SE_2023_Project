package com.group5.iftt.Model.Actions;

import java.io.IOException;
import java.io.Serializable;


public class ExecuteProgramAction implements Action, Serializable {
    private String programPath;
    private String parameters;

    public ExecuteProgramAction(String programPath, String parameters){
        this.programPath = programPath;
        this.parameters = parameters;
    }
    //Costruttore e toString servono per mostrare le opzioni relative alla classe nell'interfaccia
    public ExecuteProgramAction() {}
    public String toString(){
        return "Execute script";
    }

    @Override
    public void startAction() {
        try {
            //Costruisci il comando dello script
            String command = programPath + " " + parameters;

            //Esegui il comando
            Process process = Runtime.getRuntime().exec(command);

            //Ottieni informazioni sull'esecuzione dello script
            int exitCode = process.waitFor();
            // Verifica il codice di uscita
            if (exitCode == 0) {
                System.out.println("Script eseguito con successo.");
            } else {
                System.err.println("Errore durante l'esecuzione dello script. Codice di uscita: " + exitCode);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
