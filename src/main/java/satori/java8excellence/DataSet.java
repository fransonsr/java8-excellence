package satori.java8excellence;

import java.util.List;
import java.util.stream.Collectors;

public class DataSet {

	private List<NameData> dataSet;

	public DataSet(List<NameData> dataSet) {
		this.dataSet = dataSet;
	}

	/**
	 * Size of the data set.
	 * @return size
	 */
	public int size() {
		return dataSet.size();
	}

	/**
	 * NameData filtered by "FEMALE".
	 * @return females
	 */
	public List<NameData> females() {
		return dataSet.stream()
				.filter(nd -> "FEMALE".equals(nd.getGender()))
				.collect(Collectors.toList());
	}

	/**
	 * NameData filtered by "MALE".
	 * @return males
	 */
	public List<NameData> males() {
		return dataSet.stream()
				.filter(nd -> "MALE".equals(nd.getGender()))
				.collect(Collectors.toList());
	}

}
