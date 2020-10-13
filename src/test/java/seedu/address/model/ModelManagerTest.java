package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.ModelPredicate.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalLocations.CARL_LOCATION;
import static seedu.address.testutil.TypicalLocations.DANIEL_LOCATION;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.location.LocationBook;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.PersonBook;
import seedu.address.model.visit.Visit;
import seedu.address.model.visit.VisitBook;
import seedu.address.testutil.AddressBookBuilder;
import seedu.address.testutil.LocationBookBuilder;
import seedu.address.testutil.VisitBookBuilder;
import seedu.address.testutil.VisitBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new PersonBook(), new PersonBook(modelManager.getPersonBook()));
        assertEquals(new LocationBook(), new LocationBook(modelManager.getLocationBook()));
        assertEquals(new VisitBook(), new VisitBook(modelManager.getVisitBook()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setPersonBookFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setPersonBookFilePath(Paths.get("new/address/book/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setAddressBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setPersonBookFilePath(null));
    }

    @Test
    public void setAddressBookFilePath_validPath_setsAddressBookFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setPersonBookFilePath(path);
        assertEquals(path, modelManager.getPersonBookFilePath());
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        modelManager.addPerson(ALICE);
        assertTrue(modelManager.hasPerson(ALICE));
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredPersonList().remove(0));
    }

    @Test
    public void setLocationBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setLocationBookFilePath(null));
    }

    @Test
    public void setLocationBookFilePath_validPath_setsLocationBookFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setLocationBookFilePath(path);
        assertEquals(path, modelManager.getLocationBookFilePath());
    }

    @Test
    public void hasLocation_nullLocation_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasLocation(null));
    }

    @Test
    public void hasLocation_locationNotInLocationBook_returnsFalse() {
        assertFalse(modelManager.hasLocation(CARL_LOCATION));
    }

    @Test
    public void hasLocation_locationInAddressBook_returnsTrue() {
        modelManager.addLocation(CARL_LOCATION);
        assertTrue(modelManager.hasLocation(CARL_LOCATION));
    }

    @Test
    public void getFilteredLocationList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredLocationList().remove(0));
    }

    @Test
    public void setVisitBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setLocationBookFilePath(null));
    }

    @Test
    public void setVisitBookFilePath_validPath_setsVisitBookFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setVisitBookFilePath(path);
        assertEquals(path, modelManager.getVisitBookFilePath());
    }

    @Test
    public void hasVisit_nullLocation_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasVisit(null));
    }

    @Test
    public void hasVisit_visitNotInVisitBook_returnsFalse() {
        Visit sampleA = new VisitBuilder().withPersonId(INDEX_FIRST).withLocationId(INDEX_SECOND).withDate("2020-09-09").build();
        assertFalse(modelManager.hasVisit(sampleA));
    }

    @Test
    public void hasVisit_visitInAddressBook_returnsTrue() {
        Visit sampleA = new VisitBuilder().withPersonId(INDEX_FIRST).withLocationId(INDEX_SECOND).withDate("2020-02-09").build();
        modelManager.addVisit(sampleA);
        assertTrue(modelManager.hasVisit(sampleA));
    }

    @Test
    public void equals() {
        PersonBook personBook = new AddressBookBuilder().withPerson(ALICE).withPerson(BENSON).build();
        PersonBook differentPersonBook = new PersonBook();
        LocationBook locationBook = new LocationBookBuilder().withLocation(CARL_LOCATION).withLocation(DANIEL_LOCATION)
                .build();
        LocationBook differentLocationBook = new LocationBook();

        Visit sampleB = new VisitBuilder().withPersonId(INDEX_FIRST).withLocationId(INDEX_SECOND).withDate("2020-02-09").build();
        VisitBook visitBook = new VisitBookBuilder().withVisit(sampleB).build();
        VisitBook differentVisitBook = new VisitBook();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(personBook, locationBook, visitBook, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(personBook, locationBook, visitBook, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different personBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentPersonBook, locationBook, visitBook, userPrefs)));

        // different locationBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(personBook, differentLocationBook, visitBook, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(personBook, locationBook, visitBook, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setPersonBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(personBook, locationBook, visitBook, differentUserPrefs)));
    }
}
