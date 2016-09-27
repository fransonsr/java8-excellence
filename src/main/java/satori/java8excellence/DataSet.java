package satori.java8excellence;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

import java.util.List;

public class DataSet {

	private List<NameData> dataSet;

	public DataSet(List<NameData> dataSet) {
		this.dataSet = dataSet;
	}

	public int size() {
		return dataSet.size();
	}

	public List<NameData> females() {
		return dataSet.stream()
				.filter(NameData.isFemale)
				.collect(toList());
	}

	public List<NameData> males() {
		return dataSet.stream()
				.filter(NameData.isMale)
				.collect(toList());
	}

	public List<String> femaleNamesForYearInReverseRankOrder(final int year) {
		return dataSet.stream()
				.filter(NameData.isFemale.and(NameData.isBirthYear(year)))
				.sorted(comparing(NameData::getRank).reversed().thenComparing(NameData::getName))
				.map(NameData::getName)
				.distinct()
				.collect(toList());
	}

}
