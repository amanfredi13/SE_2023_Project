package com.group5.iftt;
import org.junit.jupiter.api.Test;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import static org.junit.jupiter.api.Assertions.*;

public class TimeOfDayTriggerTest {
        @Test   //mi assicuro che orari uguali mi restituiscano vero
        void isValidateShouldReturnTrueWhenTriggerTimeMatchesCurrentTime() {
            // Arrange

            LocalTime currentTime = LocalTime.now().truncatedTo(ChronoUnit.MINUTES);
            String currentHour = String.valueOf(currentTime.getHour());
            String currentMinute = String.valueOf(currentTime.getMinute());
            currentMinute = currentMinute.length() == 1 ? "0" + currentMinute : currentMinute;
            currentHour = currentHour.length() == 1 ? "0" + currentHour : currentHour;
            TimeOfDayTrigger timeOfDayTrigger = new TimeOfDayTrigger(currentHour, currentMinute);
            // Act
            boolean result = timeOfDayTrigger.isValidate();

            // Assert
            assertTrue(result);
        }

        @Test //mi assicuro che orari diversi mi restituiscano false
        void isValidateShouldReturnFalseWhenTriggerTimeDoesNotMatchCurrentTime() {
            // Arrange
            LocalTime currentTime = LocalTime.now().truncatedTo(ChronoUnit.MINUTES);
            String currentHour = String.valueOf(currentTime.getHour());
            String currentMinute = String.valueOf(currentTime.getMinute() + 1); // Adding 1 minute to make it different
            currentMinute = currentMinute.length() == 1 ? "0" + currentMinute : currentMinute;
            currentHour = currentHour.length() == 1 ? "0" + currentHour : currentHour;
            TimeOfDayTrigger timeOfDayTrigger = new TimeOfDayTrigger(currentHour, currentMinute);
            // Act
            boolean result = timeOfDayTrigger.isValidate();

            // Assert
            assertFalse(result);
        }
    }
