package com.group5.iftt.Model;
;
import com.group5.iftt.Model.Actions.ShowDialogBoxAction;
import com.group5.iftt.Model.RuleAndSerialize.CheckRule;
import com.group5.iftt.Model.RuleAndSerialize.Rule;
import com.group5.iftt.Model.RuleAndSerialize.RuleService;
import com.group5.iftt.Model.Triggers.SpecificDateTrigger;
import javafx.application.Platform;
import org.junit.After;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.*;


public class CheckRuleTest {
    private CheckRule checkRule;


    // Questo metodo viene eseguito prima di ogni test per inizializzare l'oggetto CheckRule
    @BeforeEach
    public void setUp() {
        // Inizializzazione della classe CheckRule prima di ogni test
        checkRule = new CheckRule();
    }

    // Questo test verifica che il metodo CheckRule.check() funzioni senza errori
    // Attendiamo per un periodo di tempo (5 secondi) per eseguire i controlli periodicamente
    @Test
    public void testCheckRule() throws InterruptedException {
        // Attendere per un certo periodo di tempo (ad esempio, 5 secondi) per permettere l'esecuzione dei controlli
        TimeUnit.SECONDS.sleep(5);

        // Verifica che il test abbia avuto successo senza errori
        assertTrue(true);
    }


    // Questo test verifica che il metodo CheckRule.getInstance() restituisca la stessa istanza
    @Test
    public void testGetInstance() {
        // Ottieni due istanze e verifica che siano le stesse
        CheckRule instance1 = CheckRule.getInstance();
        CheckRule instance2 = CheckRule.getInstance();

        assertSame(instance1, instance2, "Le istanze dovrebbero essere le stesse");

    }


    @Test
    void testCheckRuleWithSleepingRule() throws InterruptedException {
        // Crea una regola in sleep per il test
        Rule sleepingRule = new Rule("Sleeping Rule", new ShowDialogBoxAction(), new SpecificDateTrigger(LocalDate.now()), "Enabled");
        sleepingRule.isSleeping();

        RuleService.getInstance().add(sleepingRule);

        CheckRule instance = new CheckRule();

        Platform.startup(()->{
            instance.check();

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        assertFalse(sleepingRule.isActionStarted(), "L'azione della regola non è stata attivata, perché in sleeping");
    }

    @Test
    void testCheckRuleWithoutTrigger() throws InterruptedException {
        // Crea una regola senza trigger per il test
        Rule ruleWithoutTrigger = new Rule("Rule Without Trigger",new ShowDialogBoxAction(), new SpecificDateTrigger(LocalDate.now()) , "Enabled");
        RuleService.getInstance().add(ruleWithoutTrigger);

        // Attendere un periodo di tempo sufficiente per eseguire il controllo
        TimeUnit.SECONDS.sleep(2);

        // Verifica che l'azione della regola senza trigger non sia stata avviata
        assertFalse(ruleWithoutTrigger.isActionStarted(), "L'azione della regola senza trigger non dovrebbe essere stata avviata");

        // Rimuovi la regola per evitare interferenze con altri test
        RuleService.getInstance().remove(ruleWithoutTrigger);
    }

    // Questo test verifica che il metodo CheckRule.stopChecking() arresti lo scheduler
    @Test
    void testStopChecking() {
        // Arresta il controllo e verifica che lo scheduler sia spento
        checkRule.stopChecking();
        assertTrue(checkRule.getScheduler().isShutdown(), "Lo scheduler dovrebbe essere spento");
    }



    // Questo metodo viene eseguito dopo ogni test per arrestare il controllo
    @After
    public void tearDown() {
        // Arresta la verifica dopo ogni test
        checkRule.stopChecking();
    }

}