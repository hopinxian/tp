package seedu.address.model.location;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_BOB_LOCATION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.testutil.TypicalLocations.ALICE_LOCATION;
import static seedu.address.testutil.TypicalLocations.BOB_LOCATION;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.LocationBuilder;

public class LocationTest {
    @Test
    public void isSameLocation() {
        // same object -> returns true
        assertTrue(ALICE_LOCATION.isSameLocation(ALICE_LOCATION));

        // null -> returns false
        assertFalse(ALICE_LOCATION.isSameLocation(null));

        // different name, same address -> returns false
        Location editedAlice = new LocationBuilder(ALICE_LOCATION).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE_LOCATION.isSameLocation(editedAlice));

        // same name, different address -> returns true
        editedAlice = new LocationBuilder(ALICE_LOCATION).withAddress(VALID_ADDRESS_BOB).build();
        assertTrue(ALICE_LOCATION.isSameLocation(editedAlice));
    }

    @Test
    public void isSameId() {
        // same object -> returns true
        assertTrue(ALICE_LOCATION.isSameId(ALICE_LOCATION));

        // null -> returns false
        assertFalse(ALICE_LOCATION.isSameId(null));

        // different id, same name, same address -> returns false
        Location editedAlice = new LocationBuilder(ALICE_LOCATION).withId(VALID_ID_BOB_LOCATION).build();
        assertFalse(ALICE_LOCATION.isSameId(editedAlice));

        // same id, different name, different address -> returns true
        editedAlice = new LocationBuilder(ALICE_LOCATION).withAddress(VALID_ADDRESS_BOB)
                .withName(VALID_NAME_BOB).build();
        assertTrue(ALICE_LOCATION.isSameId(editedAlice));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Location aliceCopy = new LocationBuilder(ALICE_LOCATION).build();
        assertTrue(ALICE_LOCATION.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE_LOCATION.equals(ALICE_LOCATION));

        // null -> returns false
        assertFalse(ALICE_LOCATION.equals(null));

        // different type -> returns false
        assertFalse(ALICE_LOCATION.equals(5));

        // different location -> returns false
        assertFalse(ALICE_LOCATION.equals(BOB_LOCATION));

        // different name -> returns false
        Location editedAlice = new LocationBuilder(ALICE_LOCATION).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE_LOCATION.equals(editedAlice));

        // different address -> returns false
        editedAlice = new LocationBuilder(ALICE_LOCATION).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ALICE_LOCATION.equals(editedAlice));

        // different id -> returns false
        editedAlice = new LocationBuilder(ALICE_LOCATION).withId(VALID_ID_BOB_LOCATION).build();
        assertFalse(ALICE_LOCATION.equals(editedAlice));
    }
}
