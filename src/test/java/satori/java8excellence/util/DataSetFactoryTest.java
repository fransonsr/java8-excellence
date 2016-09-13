package satori.java8excellence.util;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.util.Set;

import org.junit.Test;

import satori.java8excellence.NameData;

public class DataSetFactoryTest {

	public static Set<NameData> testDataSet() {
		DataSetFactory factory = new DataSetFactory();
		Set<NameData> data = factory.load(DataSetFactoryTest.class.getResourceAsStream("/data.csv"));
		return data;
	}

	@Test
	public void load() throws Exception {
		assertThat("data set size", testDataSet(), hasSize(13962));
	}
}
