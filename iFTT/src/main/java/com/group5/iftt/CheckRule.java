package com.group5.iftt;

import javafx.application.Platform;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CheckRule {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public CheckRule() {
        System.out.println("CheckRule inizializzata");
        // Esegui il controllo ogni minuto
        scheduler.scheduleAtFixedRate(this::check, 0, 1, TimeUnit.SECONDS);
    }

    private void check() {
        System.out.println("check rule.........");
        Platform.runLater(() -> {
                    for (Rule rule : RuleService.getInstance()) {
                        System.out.println("Check rule:" + rule.getName());
                        if (rule.isTriggered() && !rule.isActionStarted()) {
                            System.out.println("trigger azione rule start:" + rule.getName());
                            rule.getAction().startAction();
                            rule.setActionStarted(true);

                            break;
                        }
                    }

                }
        );
    }

    public  void stopChecking(){
        scheduler.shutdown();
    }
}

