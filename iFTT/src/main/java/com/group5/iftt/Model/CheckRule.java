package com.group5.iftt.Model;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CheckRule {
    private static CheckRule instance ;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);


    CheckRule() {
        // Esegui il controllo ogni secondo
        scheduler.scheduleAtFixedRate(this::check, 0, 1, TimeUnit.SECONDS);
    }

    public static CheckRule getInstance() {
        if (instance == null) {
            instance = new CheckRule();
        }
        return instance;
    }

    private void check() {
        Platform.runLater(() -> {
            for (Rule rule : RuleService.getInstance()) {
                if (rule.isTriggered() && !rule.isActionStarted() && rule.isActive()) {
                    rule.getAction().startAction();
                    rule.setActionStarted(true);
                    break;
                }
            }
        });
    }

    public void stopChecking() {
        scheduler.shutdown();
    }




}

