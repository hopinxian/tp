package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.model.ModelMethods.getIdHashSetFromPersonsList;
import static seedu.address.model.ModelMethods.getLocationIdsFromInfectedVisitList;
import static seedu.address.model.ModelMethods.getNumberOfHighRiskLocations;
import static seedu.address.model.ModelMethods.sortByValues;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIFTH;
import static seedu.address.testutil.TypicalIndexes.INDEX_FOURTH;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.person.Person;
import seedu.address.model.visit.Visit;
import seedu.address.testutil.TypicalPersons;
import seedu.address.testutil.TypicalVisits;

public class ModelMethodsTest {

    @Test
    public void getIdHashSetFromPersonsList_success() {
        List<Person> typicalPersons = TypicalPersons.getTypicalPersons();
        HashSet<Index> expectedPersonsIds = TypicalPersons.getIdsOfTypicalPersonsAsHashSet();
        assertEquals(expectedPersonsIds, getIdHashSetFromPersonsList(typicalPersons));
    }

    @Test
    public void getLocationIdsFromVisitList_success() {
        List<Visit> typicalVisits = TypicalVisits.getVisitsForTest();
        List<Index> expectedLocationIds = TypicalVisits.getLocationsIdsFormVisitsForTest();
        assertEquals(expectedLocationIds, getLocationIdsFromInfectedVisitList(typicalVisits));
    }

    @Test
    public void sortByValues_success() {
        List<Visit> visits = TypicalVisits.getVisitsForTest();
        HashMap<Index, Integer> locations = new HashMap<>();
        for (Visit visit : visits) {
            Index id = visit.getLocationId();
            if (locations.containsKey(id)) {
                locations.put(id, locations.get(id) + 1);
            } else {
                locations.put(id, 1);
            }
        }
        HashMap<Index, Integer> actualHashMap = sortByValues(locations);
        HashMap<Index, Integer> expectedHashMap = new LinkedHashMap<>();
        expectedHashMap.put(INDEX_SECOND, 4);
        expectedHashMap.put(INDEX_THIRD, 3);
        expectedHashMap.put(INDEX_FIFTH, 2);
        expectedHashMap.put(INDEX_FOURTH, 1);
        assertEquals(expectedHashMap, actualHashMap);
    }

    @Test
    public void getNumberOfHighRiskLocations_moreThanSixtyPercent_success() {
        assertEquals(40, getNumberOfHighRiskLocations(89, 100));
        assertEquals((int) (101 * 0.4), getNumberOfHighRiskLocations(101, 101));
        assertEquals((int) (29 * 0.4), getNumberOfHighRiskLocations((int) Math.ceil(29 * 0.6), 29));
    }

    @Test
    public void getNumberOfHighRiskLocations_lessThanOrEqualToFortyPercent_success() {
        assertEquals(1, getNumberOfHighRiskLocations(1, 100));
        assertEquals(40, getNumberOfHighRiskLocations(40, 100));
        assertEquals(0, getNumberOfHighRiskLocations(0, 4));
    }
}
