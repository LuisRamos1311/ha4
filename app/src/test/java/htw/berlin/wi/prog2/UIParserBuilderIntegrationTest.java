package htw.berlin.wi.prog2;

import htw.berlin.wi.prog2.parsing.CountingInputParser ;
import htw.berlin.wi.prog2.parsing.ExtendableInputParser;
import htw.berlin.wi.prog2.domain.BurgerBuilder;
import htw.berlin.wi.prog2.ui.CommandLineUI;
import htw.berlin.wi.prog2.ui.UserInputWrapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.AdditionalMatchers.not;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class UIParserBuilderIntegrationTest {

    @Test
    @DisplayName("should state the correct ingredients and price")
    void integrationWithCountingParser() {
        String initialQuestion = "Was möchtest du gerne bestellen?";
        UserInputWrapper input = mock(UserInputWrapper.class);
        when(input.ask(contains(initialQuestion)))
                .thenReturn("Ich hätte gerne einen Ciabatta Burger mit Gurken, Eisbergsalat, Beyond-Meat und Ketchup");
        when(input.ask(not(contains(initialQuestion)))).thenReturn("Auf Wiedersehen");

        BurgerBuilder builder = new BurgerBuilder(BurgerBuilder.CreationStyle.PRECOMPUTED);

        ExtendableInputParser parser = new CountingInputParser();

        CommandLineUI ui = new CommandLineUI(input, builder, parser);

        assertEquals("Auf Wiedersehen", ui.launch());

        String expectedQuestion =
                "In Ordnung. Dein Burger mit [Beyond-Meat, Ciabatta-Brot, Eisbergsalat als Topping, Gurken als Topping, Ketchup als Sauce] kostet 2.80 Euro. Willst du die Bestellung abschliessen?";

        verify(input).ask(expectedQuestion);
    }
}
