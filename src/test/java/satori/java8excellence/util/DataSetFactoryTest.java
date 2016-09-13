package satori.java8excellence.util;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

import satori.java8excellence.DataSet;

public class DataSetFactoryTest {

	public static DataSet testDataSet() {
		DataSetFactory factory = new DataSetFactory();
		return factory.load(DataSetFactoryTest.class.getResourceAsStream("/data.csv"));
	}

	@Test
	public void load() throws Exception {
		assertThat("data set size", testDataSet().size(), is(equalTo(13962)));
	}
}
