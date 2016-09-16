package satori.java8excellence;

import java.util.function.Predicate;

public class NameData {

	public static final Predicate<NameData> isMale = nameData -> "MALE".equals(nameData.gender);
	public static final Predicate<NameData> isFemale = nameData -> "FEMALE".equals(nameData.gender);
	public static final Predicate<NameData> isBirthYear(int year) { return nameData -> year == nameData.birthYear; }

	private int birthYear;
	private String gender;
	private String ethnicity;
	private String name;
	private int count;
	private int rank;

	public int getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEthnicity() {
		return ethnicity;
	}

	public void setEthnicity(String ethnicity) {
		this.ethnicity = ethnicity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

}
