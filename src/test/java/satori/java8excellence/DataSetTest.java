package satori.java8excellence;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;

import satori.java8excellence.util.DataSetFactoryTest;

public class DataSetTest {

	private static final Comparator<Integer> REVERSE_INTEGER_COMPARATOR = new Comparator<Integer>() {
		@Override
		public int compare(Integer int1, Integer int2) {
			return int2 - int1;
		}
	};

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
	public void size_filtered_2012_FEMALE() throws Exception {
		List<String> expected = toNameDeduped(filterByYear(test.females(), 2012));

		List<String> actual = test.femaleNamesForYearInReverseRankOrder(2012);
		assertThat(actual, equalTo(expected));
	}

	private List<String> toNameDeduped(Multimap<Integer, String> map) {
		List<String> expectedNames = new ArrayList<>();
		for (Entry<Integer, String> entry : map.entries()) {
			String name = entry.getValue();
			if (!expectedNames.contains(name)) {
				expectedNames.add(name);
			}
		}
		return expectedNames;
	}

	private Multimap<Integer, String> filterByYear(List<NameData> nameData, int year) {
		Multimap<Integer, String> map = newMapWithOrderedKeysAndValuesWithReversedKeys();
		for (NameData female : nameData) {
			if (female.getBirthYear() == year) {
				map.put(female.getRank(), female.getName());
			}
		}
		return map;
	}

	private Multimap<Integer, String> newMapWithOrderedKeysAndValuesWithReversedKeys() {
		Multimap<Integer,String> map = MultimapBuilder
			.treeKeys(REVERSE_INTEGER_COMPARATOR)
			.treeSetValues()
			.build();
		return map;
	}
}
