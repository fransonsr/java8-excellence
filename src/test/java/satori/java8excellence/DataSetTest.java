package satori.java8excellence;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

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
	public void size_filtered_2014_FEMALE() throws Exception {
		int highestRank = Integer.MIN_VALUE;
		String firstName = null;
		int counter = 0;
		for (NameData female : test.females()) {
			if (female.getBirthYear() == 2012) {
				counter++;
				if (female.getRank() > highestRank) {
					highestRank = female.getRank();
					firstName = female.getName();
				}
			}
		}

		int year = 2012;
		List<String> names = test.femaleNamesForYearInReverseRankOrder(year);
		assertThat(names, hasSize(counter));
		assertThat(names.get(0), is(firstName));
	}

}
