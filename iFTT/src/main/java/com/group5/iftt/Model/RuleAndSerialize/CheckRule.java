package com.group5.iftt.Model.RuleAndSerialize;

import javafx.application.Platform;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
// La classe esegue il controllo delle regole in modo periodico.
public class CheckRule {
    private static CheckRule instance;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public CheckRule() {
        // Esegui il controllo ogni secondo
        scheduler.scheduleAtFixedRate(this::check, 0, 1, TimeUnit.SECONDS);
    }

    public static CheckRule getInstance() {
        if (instance == null) {
            instance = new CheckRule();
        }
        return instance;
    }

    // Questo metodo è chiamato periodicamente, esamina tutte le regole presenti in RuleService.
    // Se una regola è abilitata, non è in sleeping e il suo trigger è attivato, esegue l'azione associata alla regola
    public void check() {
        Platform.runLater(() -> {
            for (Rule rule : RuleService.getInstance()) {
                if (rule.isSleeping()) {
                    // Se la regola è in sleeping, salta il controllo
                    continue;
                }

                if (rule.isTriggered() && !rule.isActionStarted() && rule.isActive() && rule.isWakeUp()) {
                    rule.whenWakeUp();
                    rule.getAction().startAction();
                    rule.setActionStarted(true);
                    break;
                }
            }
        });
    }

    // Questo metodo interrompe il controllo periodico delle regole.
    public void stopChecking() {
        scheduler.shutdown();
    }

    public ScheduledExecutorService getScheduler() {
        return scheduler;
    }
}

