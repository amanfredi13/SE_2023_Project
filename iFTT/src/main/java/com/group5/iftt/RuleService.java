package com.group5.iftt;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import static javafx.collections.FXCollections.observableList;

public final class RuleService {
    private static ObservableList<Rule> rule_list; //all'inizio serviva
    private RuleService(){
    rule_list = FXCollections.observableArrayList();
    }
    public static ObservableList<Rule> getInstance() {
        if(rule_list == null){
             rule_list = FXCollections.observableArrayList();

        }
        return rule_list;
    }
    public void add(Rule rule){
        rule_list.add(rule);
    }

    public void remove(Rule rule) {
        rule_list.remove(rule);
    }


}
