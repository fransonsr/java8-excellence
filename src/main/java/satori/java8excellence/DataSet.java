package satori.java8excellence;

import java.util.ArrayList;
import java.util.List;

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
		List<NameData> filtered = new ArrayList<>(dataSet);
		filtered.removeIf(nd -> !"FEMALE".equals(nd.getGender()));

		return filtered;
	}

	/**
	 * NameData filtered by "MALE".
	 * @return males
	 */
	public List<NameData> males() {
		List<NameData> filtered = new ArrayList<>(dataSet);
		filtered.removeIf(nd -> !"MALE".equals(nd.getGender()));

		return filtered;
	}

}
