package satori.java8excellence;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

import satori.java8excellence.NameData.Ethnicity;

public class DataSet {

    private List<NameData> dataSet;

    public DataSet(List<NameData> dataSet) {
        this.dataSet = dataSet;
    }

    public List<NameData> dataSet() {
        return dataSet;
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
            .sorted(comparing(NameData::getRank).reversed()
                .thenComparing(NameData::getName))
            .map(NameData::getName)
            .distinct()
            .collect(toList());
    }

    public Map<String, Set<String>> uniqueMaleAndFemaleNames() {
        return dataSet.stream()
                      .collect(groupingBy(NameData::getGender,
                         mapping(nameData -> nameData.getName().toLowerCase(), toSet())));
    }

    public Map<String, Map<Ethnicity, List<String>>> topMaleFemaleNames(final int count) {
        return dataSet.stream()
                    .distinct()
                    .collect(groupingBy(NameData::getGender,
                        groupingBy(NameData.ethnicityMapping, collectingAndThen(
                            toCollection(() -> new ConcurrentSkipListSet<NameData>(byRankCountAndName)),
                                list -> list.stream()
                                    .map(nameData -> nameData.getName().toLowerCase())
                                    .distinct()
                                    .limit(count)
                                    .collect(toList())))));
    }

    private static Comparator<NameData> byRankCountAndName = comparing(NameData::getRank).thenComparing(NameData::getCount).thenComparing(NameData::getName, String::compareToIgnoreCase);

}
