package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.logic.commands.ListCommand.INVALID_LIST_TYPE;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalLocations.getTypicalLocationBook;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalVisits.getTypicalVisitBook;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.ListType;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ModelPredicate;
import seedu.address.model.ModelStub;
import seedu.address.model.UserPrefs;
import seedu.address.model.location.Location;
import seedu.address.model.person.Person;
import seedu.address.model.visit.Visit;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {
    private static final ListType PEOPLE_LIST = ListType.ALL_PEOPLE;
    private static final ListType LOCATIONS_LIST = ListType.ALL_LOCATIONS;
    private static final ListType VISITS_LIST = ListType.ALL_VISITS;
    private static final ListType INFECTED_LIST = ListType.ALL_INFECTED;
    private static final ListType QUARANTINED_LIST = ListType.ALL_QUARANTINED;
    private static final ListType STATISTICS_LIST = ListType.STATISTICS;
    private static final ListType HIGH_RISK_LOCATIONS_LIST = ListType.HIGH_RISK_LOCATIONS;

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), getTypicalLocationBook(),
                getTypicalVisitBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getPersonBook(), model.getLocationBook(),
                model.getVisitBook(), new UserPrefs());
    }

    @Test
    public void execute_personsListIsNotFiltered_showsSameList() {
        CommandResult expectedCommandResult = new CommandResult(ListCommand.MESSAGE_SUCCESS_ALL_PEOPLE,
                false, false, CommandResult.SWITCH_TO_VIEW_PEOPLE);
        assertCommandSuccess(new ListCommand(PEOPLE_LIST),
                model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_personsListIsFiltered_showsEverything() {
        showPersonAtIndex(model, INDEX_FIRST);
        CommandResult expectedCommandResult = new CommandResult(ListCommand.MESSAGE_SUCCESS_ALL_PEOPLE,
                false, false, CommandResult.SWITCH_TO_VIEW_PEOPLE);
        assertCommandSuccess(new ListCommand(PEOPLE_LIST),
                model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_locationsList_showsSameList() {
        CommandResult expectedCommandResult = new CommandResult(ListCommand.MESSAGE_SUCCESS_ALL_LOCATIONS,
                false, false, CommandResult.SWITCH_TO_VIEW_LOCATIONS);
        assertCommandSuccess(new ListCommand(LOCATIONS_LIST),
                model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_visitsList_showsSameList() {
        CommandResult expectedCommandResult = new CommandResult(ListCommand.MESSAGE_SUCCESS_ALL_VISITS,
                false, false, CommandResult.SWITCH_TO_VIEW_VISITS);
        assertCommandSuccess(new ListCommand(VISITS_LIST),
                model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_infectedList_showsSameList() {
        Model expectedModelInfected = expectedModel;
        expectedModelInfected.updateFilteredPersonList(ModelPredicate.PREDICATE_SHOW_ALL_INFECTED);
        CommandResult expectedCommandResult = new CommandResult(ListCommand.MESSAGE_SUCCESS_ALL_INFECTED,
                false, false, CommandResult.SWITCH_TO_VIEW_PEOPLE);
        assertCommandSuccess(new ListCommand(INFECTED_LIST),
                model, expectedCommandResult, expectedModelInfected);
    }

    @Test
    public void execute_quarantinedList_showsSameList() {
        Model expectedModelQuarantined = expectedModel;
        expectedModelQuarantined.updateFilteredPersonList(ModelPredicate.PREDICATE_SHOW_ALL_QUARANTINED);
        CommandResult expectedCommandResult = new CommandResult(ListCommand.MESSAGE_SUCCESS_ALL_QUARANTINED,
                false, false, CommandResult.SWITCH_TO_VIEW_PEOPLE);
        assertCommandSuccess(new ListCommand(QUARANTINED_LIST),
                model, expectedCommandResult, expectedModelQuarantined);
    }

    @Test
    public void execute_statistics_showsSameList() {
        String expectedMessage = ListCommand.MESSAGE_SUCCESS_STATISTICS + "\n"
            + "Total number of people: 0\n"
            + "Total number of locations: 0\n"
            + "Total number of visits: 0\n"
            + "Total number of infected people: 0\n"
            + "Total number of quarantined people: 0\n"
            + "Percentage of people infected: -\n"
            + "Percentage of people quarantined: -\n";

        model = new ModelStubWithLists();
        assertCommandSuccess(new ListCommand(STATISTICS_LIST),
                model, expectedMessage, model);
    }

    @Test
    public void execute_highRiskLocations_showsSameList() {
        Model expectedModelHighRiskLocations = expectedModel;
        expectedModelHighRiskLocations.updateFilteredLocationList(
                ModelPredicate.getPredicateForHighRiskLocations(expectedModelHighRiskLocations));
        CommandResult expectedCommandResult = new CommandResult(ListCommand.MESSAGE_SUCCESS_HIGH_RISK_LOCATIONS,
                false, false, CommandResult.SWITCH_TO_VIEW_LOCATIONS);
        assertCommandSuccess(new ListCommand(HIGH_RISK_LOCATIONS_LIST),
                model, expectedCommandResult, expectedModelHighRiskLocations);
    }

    @Test
    public void execute_invalidListType_throwsCommandException() {
        ListCommand command = new ListCommand(ListType.INVALID);
        Model model = new ModelStub();
        assertThrows(CommandException.class, String.format(INVALID_LIST_TYPE), ()
            -> command.execute(model));
    }

    @Test
    public void equals() {
        ListCommand listPersonsCommand = new ListCommand(PEOPLE_LIST);

        // same object -> returns true
        assertTrue(listPersonsCommand.equals(listPersonsCommand));

        // same values -> returns true
        ListCommand listPersonsCommandCopy = new ListCommand(PEOPLE_LIST);
        assertTrue(listPersonsCommand.equals(listPersonsCommandCopy));

        // different values -> returns false
        ListCommand differentListCommand = new ListCommand(LOCATIONS_LIST);
        assertFalse(listPersonsCommand.equals(differentListCommand));

        // different types -> returns false
        assertFalse(listPersonsCommand.equals(1));

        // null -> returns false
        assertFalse(listPersonsCommand.equals(null));
    }


    private static class ModelStubWithLists extends ModelStub {
        private ObservableList<Person> personList = FXCollections.observableList(new ArrayList<>());
        private ObservableList<Location> locationList = FXCollections.observableList(new ArrayList<>());
        private ObservableList<Visit> visitList = FXCollections.observableList(new ArrayList<>());

        @Override
        public ObservableList<Person> getSortedPersonList() {
            return personList;
        }

        @Override
        public ObservableList<Location> getSortedLocationList() {
            return locationList;
        }

        @Override
        public ObservableList<Visit> getSortedVisitList() {
            return visitList;
        }

        @Override
        public void updateFilteredPersonList(Predicate<? super Person> predicate) {}

        @Override
        public void updateFilteredLocationList(Predicate<? super Location> predicate) {}

        @Override
        public void updateFilteredVisitList(Predicate<? super Visit> predicate) {}

        @Override
        public Optional<Predicate<? super Person>> getPersonPredicate() {
            return Optional.empty();
        }

        @Override
        public Optional<Predicate<? super Location>> getLocationPredicate() {
            return Optional.empty();
        }

        @Override
        public Optional<Predicate<? super Visit>> getVisitPredicate() {
            return Optional.empty();
        }
    }
}
