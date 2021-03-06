package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CommandResultTest {
    @Test
    public void equals() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns true
        assertTrue(commandResult.equals(new CommandResult("feedback")));
        assertTrue(commandResult.equals(new CommandResult("feedback",
                false, false, CommandResult.SWITCH_NONE)));

        // same object -> returns true
        assertTrue(commandResult.equals(commandResult));

        // null -> returns false
        assertFalse(commandResult.equals(null));

        // different types -> returns false
        assertFalse(commandResult.equals(0.5f));

        // different feedbackToUser value -> returns false
        assertFalse(commandResult.equals(new CommandResult("different")));

        // different showHelp value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback",
                true, false, CommandResult.SWITCH_NONE)));

        // different exit value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback",
                false, true, CommandResult.SWITCH_NONE)));
    }

    @Test
    public void hashcode() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult("feedback").hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("different").hashCode());

        // different showHelp value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback",
                true, false, CommandResult.SWITCH_NONE).hashCode());

        // different exit value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback",
                false, true, CommandResult.SWITCH_NONE).hashCode());
    }

    @Test
    public void isShowHelp() {
        CommandResult showHelpCommandResult =
                new CommandResult("feedback", true, false, CommandResult.SWITCH_NONE);
        CommandResult noHelpCommandResult =
                new CommandResult("feedback", false, false, CommandResult.SWITCH_NONE);
        assertTrue(showHelpCommandResult.isShowHelp());
        assertFalse(noHelpCommandResult.isShowHelp());
    }

    @Test
    public void isExit() {
        CommandResult exitCommandResult =
                new CommandResult("feedback", false, true, CommandResult.SWITCH_NONE);
        CommandResult noExitCommandResult =
                new CommandResult("feedback", false, false, CommandResult.SWITCH_NONE);
        assertTrue(exitCommandResult.isExit());
        assertFalse(noExitCommandResult.isExit());
    }

    @Test
    public void toString_success() {
        String feedback = "feedback";
        CommandResult commandResult = new CommandResult(feedback);
        assertEquals(feedback, commandResult.toString());
    }
}
