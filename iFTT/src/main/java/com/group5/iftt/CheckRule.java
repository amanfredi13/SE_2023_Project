package com.group5.iftt;

import javafx.application.Platform;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CheckRule {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public CheckRule() {
        // Esegui il controllo ogni secondo
        scheduler.scheduleAtFixedRate(this::check, 0, 1, TimeUnit.SECONDS);
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
                }
        );
    }

    public  void stopChecking(){
        scheduler.shutdown();
    }
}

