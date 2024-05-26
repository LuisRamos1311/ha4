/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package htw.berlin.wi.prog2;

import htw.berlin.wi.prog2.parsing.CountingInputParser;
import htw.berlin.wi.prog2.parsing.ExtendableInputParser;
import htw.berlin.wi.prog2.domain.BurgerBuilder;
import htw.berlin.wi.prog2.ui.CommandLineUI;
import htw.berlin.wi.prog2.ui.UserInputWrapper;

public class BurgerBotApp {
    public static void main(String[] args) {
        ExtendableInputParser parser = new CountingInputParser();
        BurgerBuilder builder = new BurgerBuilder(BurgerBuilder.CreationStyle.PRECOMPUTED);
        CommandLineUI clui = new CommandLineUI(new UserInputWrapper(System.in, System.out), builder, parser);
        clui.launch();
    }
}
