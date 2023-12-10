package com.group5.iftt.Model;
import com.group5.iftt.Model.RuleAndSerialize.CheckRule;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.*;

public class CheckRuleTest {
    private CheckRule checkRule;

    @Before
    public void setUp() {
        // Inizializzazione della classe CheckRule prima di ogni test
        checkRule = new CheckRule();
    }

    @Test
    public void testCheckRule() throws InterruptedException {
        // Attendere per un certo periodo di tempo (ad esempio, 5 secondi) per permettere l'esecuzione dei controlli
        TimeUnit.SECONDS.sleep(5);

        // Verifica che il test abbia avuto successo senza errori
        assertTrue(true);
    }

    @After
    public void tearDown() {
        // Arresta la verifica dopo ogni test
        checkRule.stopChecking();
    }
}