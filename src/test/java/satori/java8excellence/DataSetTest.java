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
	public void size() throws Exception {
		int total = test.males().size() + test.females().size();
		assertThat(total, is(test.size()));
	}

}
