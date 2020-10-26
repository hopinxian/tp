package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_ID;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.VisitBuilder.DEFAULT_LOCATION;
import static seedu.address.testutil.VisitBuilder.DEFAULT_PERSON;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ModelStub;
import seedu.address.model.attribute.Id;
import seedu.address.model.location.Location;
import seedu.address.model.person.Person;

public class IndexIdPairTest {
    private String personIdString = " idp/S123";
    private String locationIdString = " idl/L123";
    private String indexString = "1";
    private String outOfBoundIndex = "2";
    private String invalidIndex = "0";
    private String invalidId = " idp/   ";
    private String empty = "";
    private String personIdNotExistString = " idp/not exist";
    private String locationIdNotExistString = " idl/not exist";
    private Id personId = new Id("S123");
    private Id locationId = new Id("L123");
    private Id idNotExist = new Id("not exist");

    private ArgumentMultimap argMultimap;
    private IndexIdPair indexIdPair;
    private ModelStubForPair modelStub = new ModelStubForPair(DEFAULT_LOCATION, DEFAULT_PERSON);

    @Test
    public void checkIndexOrIdOnly_indexOrIdOnly_true() {
        // person Id only
        argMultimap = ArgumentTokenizer.tokenize(personIdString, PREFIX_PERSON_ID);
        assertTrue(IndexIdPair.checkIndexOrIdOnly(argMultimap, PREFIX_PERSON_ID));

        // location Id only
        argMultimap = ArgumentTokenizer.tokenize(locationIdString, PREFIX_LOCATION_ID);
        assertTrue(IndexIdPair.checkIndexOrIdOnly(argMultimap, PREFIX_LOCATION_ID));
    }

    @Test
    public void checkIndexOrIdOnly_indexAndIdOrNeither_false() {
        // person Id and person Index
        argMultimap = ArgumentTokenizer.tokenize(indexString + personIdString, PREFIX_PERSON_ID);
        assertFalse(IndexIdPair.checkIndexOrIdOnly(argMultimap, PREFIX_PERSON_ID));

        // location Id and location Index
        argMultimap = ArgumentTokenizer.tokenize(indexString + locationIdString, PREFIX_LOCATION_ID);
        assertFalse(IndexIdPair.checkIndexOrIdOnly(argMultimap, PREFIX_LOCATION_ID));

        // empty string
        argMultimap = ArgumentTokenizer.tokenize(empty, PREFIX_LOCATION_ID);
        assertFalse(IndexIdPair.checkIndexOrIdOnly(argMultimap, PREFIX_LOCATION_ID));
    }

    @Test
    public void checkIndexOrIdOnly_wrongPrefix_false() {
        argMultimap = ArgumentTokenizer.tokenize(personIdString, PREFIX_PERSON_ID);
        assertFalse(IndexIdPair.checkIndexOrIdOnly(argMultimap, PREFIX_LOCATION_ID));

        argMultimap = ArgumentTokenizer.tokenize(locationIdString, PREFIX_LOCATION_ID);
        assertFalse(IndexIdPair.checkIndexOrIdOnly(argMultimap, PREFIX_PERSON_ID));
    }

    @Test
    public void constructor_nullValues_throwNullPointerException() {
        argMultimap = ArgumentTokenizer.tokenize(personIdString, PREFIX_PERSON_ID);
        assertThrows(NullPointerException.class, () -> new IndexIdPair(null, null));
        assertThrows(NullPointerException.class, () -> new IndexIdPair(null, PREFIX_PERSON_ID));
        assertThrows(NullPointerException.class, () -> new IndexIdPair(argMultimap, null));
    }

    @Test
    public void constructor_validArgs_success() {
        // person Id
        argMultimap = ArgumentTokenizer.tokenize(personIdString, PREFIX_PERSON_ID);
        IndexIdPair expectedPersonPair = new IndexIdPair(null, personId, PREFIX_PERSON_ID);
        try {
            assertEquals(new IndexIdPair(argMultimap, PREFIX_PERSON_ID), expectedPersonPair);
        } catch (ParseException pe) {
            throw new IllegalArgumentException("Invalid userInput.", pe);
        }

        // location index
        argMultimap = ArgumentTokenizer.tokenize(indexString, PREFIX_LOCATION_ID);
        IndexIdPair expectedLocationPair = new IndexIdPair(INDEX_FIRST, null, PREFIX_LOCATION_ID);
        try {
            assertEquals(new IndexIdPair(argMultimap, PREFIX_LOCATION_ID), expectedLocationPair);
        } catch (ParseException pe) {
            throw new IllegalArgumentException("Invalid userInput.", pe);
        }
    }

    @Test
    public void constructor_invalidArgs_throwParseException() {
        // invalid Id
        argMultimap = ArgumentTokenizer.tokenize(invalidId, PREFIX_PERSON_ID);
        assertThrows(ParseException.class, Id.MESSAGE_CONSTRAINTS, () ->
                new IndexIdPair(argMultimap, PREFIX_PERSON_ID));

        // invalid Index
        argMultimap = ArgumentTokenizer.tokenize(invalidIndex, PREFIX_PERSON_ID);
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, () ->
                new IndexIdPair(argMultimap, PREFIX_PERSON_ID));

    }

    @Test
    public void constructor_noIndexNorId_throwParseException() {
        argMultimap = ArgumentTokenizer.tokenize(empty, PREFIX_PERSON_ID);
        assertThrows(ParseException.class, IndexIdPair.MESSAGE_NO_ID_NOR_INDEX, () ->
                new IndexIdPair(argMultimap, PREFIX_PERSON_ID));
    }

    @Test
    public void getPersonFromPair_success() {
        // using Id
        argMultimap = ArgumentTokenizer.tokenize(personIdString, PREFIX_PERSON_ID);
        try {
            indexIdPair = new IndexIdPair(argMultimap, PREFIX_PERSON_ID);
            Person person = indexIdPair.getPersonFromPair(modelStub);
            assertEquals(person, DEFAULT_PERSON);
        } catch (Exception e) {
            assert false : "Test failed!";
        }

        // using index
        argMultimap = ArgumentTokenizer.tokenize(indexString, PREFIX_PERSON_ID);
        try {
            indexIdPair = new IndexIdPair(argMultimap, PREFIX_PERSON_ID);
            Person person = indexIdPair.getPersonFromPair(modelStub);
            assertEquals(person, DEFAULT_PERSON);
        } catch (Exception e) {
            assert false : "Test failed!";
        }
    }

    @Test
    public void getPersonFromPair_invalidUse_throwCommandException() {
        argMultimap = ArgumentTokenizer.tokenize(personIdString, PREFIX_PERSON_ID);
        try {
            indexIdPair = new IndexIdPair(argMultimap, PREFIX_PERSON_ID);
            assertThrows(CommandException.class, IndexIdPair.MESSAGE_INVALID_LOCATION_COMMAND_USE, () ->
                    indexIdPair.getLocationFromPair(modelStub));
        } catch (ParseException e) {
            assert false : "Test failed!";
        }
    }

    @Test
    public void getPersonFromPair_outOfBoundIndex_throwCommandException() {
        argMultimap = ArgumentTokenizer.tokenize(outOfBoundIndex, PREFIX_PERSON_ID);
        try {
            indexIdPair = new IndexIdPair(argMultimap, PREFIX_PERSON_ID);
            assertThrows(CommandException.class, Messages.MESSAGE_INVALID_PERSON_INDEX, () ->
                    indexIdPair.getPersonFromPair(modelStub));
        } catch (ParseException e) {
            assert false : "Test failed!";
        }
    }

    @Test
    public void getPersonFromPair_invalidId_throwCommandException() {
        argMultimap = ArgumentTokenizer.tokenize(personIdNotExistString, PREFIX_PERSON_ID);
        try {
            indexIdPair = new IndexIdPair(argMultimap, PREFIX_PERSON_ID);
            assertThrows(CommandException.class, Messages.MESSAGE_INVALID_PERSON_ID, () ->
                    indexIdPair.getPersonFromPair(modelStub));
        } catch (ParseException e) {
            assert false : "Test failed!";
        }
    }

    @Test
    public void getLocationFromPair_success() {
        // using Id
        argMultimap = ArgumentTokenizer.tokenize(locationIdString, PREFIX_LOCATION_ID);
        try {
            indexIdPair = new IndexIdPair(argMultimap, PREFIX_LOCATION_ID);
            Location location = indexIdPair.getLocationFromPair(modelStub);
            assertEquals(location, DEFAULT_LOCATION);
        } catch (Exception e) {
            assert false : "Test failed!";
        }

        // using index
        argMultimap = ArgumentTokenizer.tokenize(indexString, PREFIX_LOCATION_ID);
        try {
            indexIdPair = new IndexIdPair(argMultimap, PREFIX_LOCATION_ID);
            Location location = indexIdPair.getLocationFromPair(modelStub);
            assertEquals(location, DEFAULT_LOCATION);
        } catch (Exception e) {
            assert false : "Test failed!";
        }
    }

    @Test
    public void getLocationFromPair_invalidUse_throwCommandException() {
        argMultimap = ArgumentTokenizer.tokenize(locationIdString, PREFIX_LOCATION_ID);
        try {
            indexIdPair = new IndexIdPair(argMultimap, PREFIX_LOCATION_ID);
            assertThrows(CommandException.class, IndexIdPair.MESSAGE_INVALID_PERSON_COMMAND_USE, () ->
                    indexIdPair.getPersonFromPair(modelStub));
        } catch (ParseException e) {
            assert false : "Test failed!";
        }
    }

    @Test
    public void getLocationFromPair_outOfBoundIndex_throwCommandException() {
        argMultimap = ArgumentTokenizer.tokenize(outOfBoundIndex, PREFIX_LOCATION_ID);
        try {
            indexIdPair = new IndexIdPair(argMultimap, PREFIX_LOCATION_ID);
            assertThrows(CommandException.class, Messages.MESSAGE_INVALID_LOCATION_INDEX, () ->
                    indexIdPair.getLocationFromPair(modelStub));
        } catch (ParseException e) {
            assert false : "Test failed!";
        }
    }

    @Test
    public void getLocationFromPair_invalidId_throwCommandException() {
        argMultimap = ArgumentTokenizer.tokenize(locationIdNotExistString, PREFIX_LOCATION_ID);
        try {
            indexIdPair = new IndexIdPair(argMultimap, PREFIX_LOCATION_ID);
            assertThrows(CommandException.class, Messages.MESSAGE_INVALID_LOCATION_ID, () ->
                    indexIdPair.getLocationFromPair(modelStub));
        } catch (ParseException e) {
            assert false : "Test failed!";
        }
    }

    /**
     * A Model stub that contains a single person and location.
     */
    private class ModelStubForPair extends ModelStub {
        private final Location location;
        private final Person person;

        ModelStubForPair(Location location, Person person) {
            requireNonNull(location);
            requireNonNull(person);
            this.location = location;
            this.person = person;
        }

        @Override
        public Person getPersonFromIndex(Index index) {
            return person;
        }

        @Override
        public Location getLocationFromIndex(Index index) {
            return location;
        }

        @Override
        public Person getPersonById(Id id) {
            return person;
        }

        @Override
        public Location getLocationById(Id id) {
            return location;
        }

        @Override
        public boolean hasPersonId(Id id) {
            if (id.equals(idNotExist)) {
                return false;
            }
            return true;
        }

        @Override
        public boolean hasLocationId(Id id) {
            if (id.equals(idNotExist)) {
                return false;
            }
            return true;
        }

        @Override
        public ObservableList<Person> getSortedPersonList() {
            ObservableList<Person> ls = FXCollections.observableArrayList();
            ls.addAll(person);
            return ls;
        }

        @Override
        public ObservableList<Location> getSortedLocationList() {
            ObservableList<Location> ls = FXCollections.observableArrayList();
            ls.addAll(location);
            return ls;
        }
    }
}
