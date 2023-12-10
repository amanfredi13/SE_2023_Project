package com.group5.iftt.Model.RuleAndSerialize;
import com.group5.iftt.Model.RuleAndSerialize.Rule;
import javafx.collections.ObservableList;
import java.io.*;
import java.util.ArrayList;


public class SerializeList implements Serializable{
    private  ObservableList<Rule> rule_list;
    private  String filepath;

    public SerializeList(ObservableList<Rule> rule_list, String filepath) {
        this.rule_list = rule_list;
        this.filepath = filepath;
    }

    public void serialize() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filepath))) {
            ArrayList<Rule> serializableList = new ArrayList<>(rule_list); //Creo arrayList di appoggio.
            oos.writeObject(serializableList); //Scrivo nella lista di appoggio.
        } catch (IOException e) {
            System.out.printf("Problema IOException in SerializeList");
        }
    }

    public static <Rule extends Serializable> ArrayList<Rule> deserialize(String filePath) {
        ArrayList<Rule> deserializedList = new ArrayList(); //Creo lista di appoggio.
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            deserializedList = (ArrayList<Rule>) ois.readObject(); //Leggo gli oggetti nel file di testo con i dati serializzati.
            return deserializedList; //Restitusico dati letti.
        } catch (FileNotFoundException e) {
            System.out.printf("Errore: File non trovato. ");
        } catch (EOFException e) {
            System.out.printf("Il file da cui leggere i dati serializzati è vuoto. ");
        }catch (IOException | ClassNotFoundException e) {
            System.out.printf("Errore: Problema con l'I/O. ");
        }
        // Se si verificano errori o semplicemente il file non è mai stato scritto e quindi non ci sono dati da deserializzare, restituisci una lista vuota.
        System.out.printf("Restituisco una lista vuota. ");
        return deserializedList;
    }
}
