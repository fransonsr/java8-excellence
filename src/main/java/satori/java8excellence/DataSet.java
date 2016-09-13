package satori.java8excellence;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

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
		return filter(nd -> "FEMALE".equals(nd.getGender()));
	}

	/**
	 * NameData filtered by "MALE".
	 * @return males
	 */
	public List<NameData> males() {
		return filter(nd -> "MALE".equals(nd.getGender()));
	}

	/**
	 * Return a filtered subset of the dataset, according to the Predicate.
	 * @param predicate
	 * @return filtered list
	 */
	public  List<NameData> filter(Predicate<NameData> predicate) {
		List<NameData> filtered = new ArrayList<>();

		dataSet.forEach(nd -> {
			if (predicate.test(nd)) {
				filtered.add(nd);
			}
		});

		return filtered;
	}

}
