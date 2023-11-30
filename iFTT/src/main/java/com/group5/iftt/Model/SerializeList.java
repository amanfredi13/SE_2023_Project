package com.group5.iftt.Model;
import javafx.collections.ObservableList;
import java.io.*;
import java.util.ArrayList;

//Classe che si occupa della serializzazione degli oggetti Rule
public class SerializeList implements Serializable{
    private final ObservableList<Rule> rule_list;
    private final String filepath;

    public SerializeList(ObservableList<Rule> rule_list, String filepath) {
        this.rule_list = rule_list;
        this.filepath = filepath;
    }

    public void serialize() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filepath))) {
            ArrayList<Rule> serializableList = new ArrayList<>(rule_list);
            oos.writeObject(serializableList);
        } catch (IOException e) {
            System.out.print("Problema IOException in SerializeList");
        }
    }

    public static <Rule extends Serializable> ArrayList<Rule> deserialize(String filePath) {
        ArrayList<Rule> deserializedList = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
             deserializedList = (ArrayList<Rule>) ois.readObject();
             return deserializedList;
        } catch (FileNotFoundException e) {
            // Il file non esiste
            System.out.print("Errore: File non esistente");
        } catch (EOFException e) {
        //In questo caso non faccio niente o posso informare l'utente che inizierò a scrivere da un file vuoto.
        }catch (IOException | ClassNotFoundException e) {
            // Gestione delle eccezioni durante la deserializzazione
            System.out.print("Errore: Deserializzazione non riuscita");
        }
        // Se si verificano errori o semplicemente è la prima volta che scrivo sul file, restituisci una lista vuota.
        return deserializedList;
    }
}
