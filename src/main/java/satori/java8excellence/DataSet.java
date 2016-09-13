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
		List<NameData> filtered = new ArrayList<>();

		for (NameData nameData : dataSet) {
			if ("FEMALE".equals(nameData.getGender())) {
				filtered.add(nameData);
			}
		}

		return filtered;
	}

	/**
	 * NameData filtered by "MALE".
	 * @return males
	 */
	public List<NameData> males() {
		List<NameData> filtered = new ArrayList<>();

		for (NameData nameData : dataSet) {
			if ("MALE".equals(nameData.getGender())) {
				filtered.add(nameData);
			}
		}

		return filtered;
	}

}
