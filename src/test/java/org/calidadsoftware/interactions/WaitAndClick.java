package org.calidadsoftware.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.*;
import net.serenitybdd.screenplay.actions.Click;

import java.time.Duration;

/**
 * Interaction that waits for an element to be clickable before clicking
 * Provides robust synchronization for dynamic UI elements
 */
public class WaitAndClick implements Interaction {

    private final Target target;
    private final int timeoutSeconds;

    private WaitAndClick(Target target, int timeoutSeconds) {
        this.target = target;
        this.timeoutSeconds = timeoutSeconds;
    }

    public static WaitAndClick on(Target target) {
        return new WaitAndClick(target, 10);
    }

    public static WaitAndClick on(Target target, int timeoutSeconds) {
        return new WaitAndClick(target, timeoutSeconds);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(target, isVisible())
                        .forNoMoreThan(Duration.ofSeconds(timeoutSeconds)),
                WaitUntil.the(target, isEnabled())
                        .forNoMoreThan(Duration.ofSeconds(timeoutSeconds)),
                Click.on(target)
        );
    }

    public static WaitAndClick onButton(Target target) {
        return Tasks.instrumented(WaitAndClick.class, target, 10);
    }
}
