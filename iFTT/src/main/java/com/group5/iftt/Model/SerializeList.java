package com.group5.iftt.Model;
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
            ArrayList<Rule> serializableList = new ArrayList<>(rule_list);
            oos.writeObject(serializableList);
        } catch (IOException e) {
            System.out.printf("Problema IOException in SerializeList");
        }
    }

    public static <Rule extends Serializable> ArrayList<Rule> deserialize(String filePath) {
        ArrayList<Rule> deserializedList = new ArrayList();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
             deserializedList = (ArrayList<Rule>) ois.readObject();

                 return deserializedList;

        } catch (FileNotFoundException e) {
            // Il file non esiste
            e.printStackTrace();
        } catch (EOFException e) {
            System.out.printf("Il file da cui leggere i dati serializzati è vuoto");
            e.printStackTrace();
        }catch (IOException | ClassNotFoundException e) {
            // Gestione delle eccezioni durante la deserializzazione
            e.printStackTrace();
        }
        // Se si verificano errori, restituisci una lista vuota
        System.out.printf("Restituisco una lista vuota");
        return deserializedList;
    }
}
