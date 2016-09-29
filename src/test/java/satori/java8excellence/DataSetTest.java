package satori.java8excellence;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import satori.java8excellence.NameData.Ethnicity;
import satori.java8excellence.util.DataSetFactoryTest;

public class DataSetTest {

	private DataSet test;

	@Before
	public void setup() throws Exception {
		test = DataSetFactoryTest.testDataSet();
	}

	@Test
	public void females() throws Exception {
		List<NameData> list = test.females();
		assertThat(list, hasSize(7139));
	}

	@Test
	public void males() throws Exception {
		List<NameData> list = test.males();
		assertThat(list, hasSize(6823));
	}

	@Test
	public void size_consistsOfOnlyMalesAndFemales() throws Exception {
		int total = test.males().size() + test.females().size();
		assertThat(total, is(test.size()));
	}

	@Test
	public void uniqueMaleAndFemaleNames() throws Exception {
		Set<String> maleNames = uniqueNamesToLowerCase(test.males());
        Set<String> femaleNames = uniqueNamesToLowerCase(test.females());

        Map<String, Set<String>> actualNames = test.uniqueMaleAndFemaleNames();

		assertThat("males", actualNames.get("MALE"), hasSize(maleNames.size()));
		assertThat("females", actualNames.get("FEMALE"), hasSize(femaleNames.size()));
	}

    private Set<String> uniqueNamesToLowerCase(List<NameData> list) {
        Set<String> names = new HashSet<>();
        for (NameData data : list) {
			names.add(data.getName().toLowerCase());
		}
        return names;
    }

	@Test
    public void topNamesForEthnicity() throws Exception {
        Map<NameData.Ethnicity, List<String>> topMaleNames = topNames(orderEntries(ethnicityMap(test.males())), 3);
        Map<NameData.Ethnicity, List<String>> topFemaleNames = topNames(orderEntries(ethnicityMap(test.females())), 3);

        Map<NameData.Ethnicity, List<String>> actualMaleNames = new HashMap<>();
        Map<NameData.Ethnicity, List<String>> actualFemaleNames = new HashMap<>();


        Map<String, Map<NameData.Ethnicity, List<String>>> actual = test.topMaleFemaleNames(3);

        actual.entrySet().forEach(genderEntry -> {
            String gender = genderEntry.getKey();
            Map<Ethnicity, List<String>> ethnicityMap = genderEntry.getValue();
            System.out.println("Gender: " + gender);
            ethnicityMap.entrySet().forEach(ethnicityEntry -> {
                Ethnicity ethnicity = ethnicityEntry.getKey();
                List<String> names = ethnicityEntry.getValue();
                System.out.println("\tEthnicity: " + ethnicity);
                names.stream()
                    .forEach(name -> {
                        System.out.println("\t\tName: " + name);
                        Map<NameData.Ethnicity, List<String>> map = "MALE".equals(gender) ? actualMaleNames : actualFemaleNames;
                        listForNames(ethnicity, map).add(name);
                    });
            });
        });

        assertThat(actualMaleNames, is(equalTo(topMaleNames)));
        assertThat(actualFemaleNames, is(equalTo(topFemaleNames)));

    }

    private Map<NameData.Ethnicity, List<String>> topNames(Map<NameData.Ethnicity, List<NameData>> map, int size) {
        Map<NameData.Ethnicity, List<String>> topNames = new HashMap<>();
        for (Entry<Ethnicity, List<NameData>> entry : map.entrySet()) {
            topNames.put(entry.getKey(), new ArrayList<String>());
            for (int i = 0; i < size; i++) {
                NameData nameData = entry.getValue().get(i);
                topNames.get(entry.getKey()).add(nameData.getName().toLowerCase());
            }
        }
        return topNames;
    }

    private Map<NameData.Ethnicity, List<NameData>> orderEntries(Map<NameData.Ethnicity, List<NameData>> map) {
        for (Entry<Ethnicity, List<NameData>> entry : map.entrySet()) {
            List<NameData> list = entry.getValue();
            Collections.sort(list, RANK_COUNT_NAME_COMPARATOR);
        }

        return map;
    }

	private static final Comparator<NameData> RANK_COUNT_NAME_COMPARATOR = new Comparator<NameData>() {
        @Override
        public int compare(NameData nameData1, NameData nameData2) {
            if (nameData1.getRank() == nameData2.getRank()) {
                if (nameData1.getCount() == nameData2.getCount()) {
                    return nameData1.getName().toLowerCase().compareTo(nameData2.getName().toLowerCase());
                }

                return nameData2.getCount() - nameData1.getCount();
            }

            return nameData2.getRank() - nameData1.getRank();
        }
    };

    private Map<NameData.Ethnicity, List<NameData>> ethnicityMap(List<NameData> list) {
        Map<NameData.Ethnicity, List<NameData>> map = new HashMap<>();
        for (NameData nameData : list) {
            Ethnicity ethnicity = NameData.Ethnicity.fromString(nameData.getEthnicity());
            listFor(ethnicity, map).add(nameData);
        }
        return map;
    }

	static List<NameData> listFor(NameData.Ethnicity ethnicity, Map<NameData.Ethnicity, List<NameData>> map) {
	    List<NameData> list = map.get(ethnicity);
	    if(list == null) {
	        list = new ArrayList<>();
	    }

	    return list;
	}

	static List<String> listForNames(NameData.Ethnicity ethnicity, Map<NameData.Ethnicity, List<String>> map) {
	    List<String> list = map.get(ethnicity);
	    if(list == null) {
	        list = new ArrayList<>();
	    }

	    return list;
	}

}
