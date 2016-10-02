package satori.java8excellence;

import java.util.function.Function;
import java.util.function.Predicate;

public class NameData {

    public static enum Ethnicity {
        HISPANIC,
        WHITE,
        ASIAN,
        BLACK,
        OTHER;

        public static Ethnicity fromString(String value) {
            switch(value) {
                case "HISPANIC" :
                    return HISPANIC;
                case "WHITE NON HISPANIC" :
                case "WHITE NON HISP" :
                    return WHITE;
                case "ASIAN AND PACIFIC ISLANDER" :
                case "ASIAN AND PACI" :
                    return ASIAN;
                case "BLACK NON HISPANIC" :
                case "BLACK NON HISP" :
                    return BLACK;
                default :
                    return OTHER;
            }
        }
    }

	public static final Predicate<NameData> isMale = nameData -> "MALE".equals(nameData.gender);
	public static final Predicate<NameData> isFemale = nameData -> "FEMALE".equals(nameData.gender);
	public static final Predicate<NameData> isBirthYear(int year) { return nameData -> year == nameData.birthYear; }
	public static final Function<NameData, Ethnicity> ethnicityMapping = nameData -> Ethnicity.fromString(nameData.getEthnicity());

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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + birthYear;
        result = prime * result + count;
        result = prime * result + ((ethnicity == null) ? 0 : Ethnicity.fromString(ethnicity).hashCode());
        result = prime * result + ((gender == null) ? 0 : gender.hashCode());
        result = prime * result + ((name == null) ? 0 : name.toLowerCase().hashCode());
        result = prime * result + rank;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        NameData other = (NameData) obj;
        if (birthYear != other.birthYear)
            return false;
        if (count != other.count)
            return false;
        if (ethnicity == null) {
            if (other.ethnicity != null)
                return false;
        }
        else if (!Ethnicity.fromString(ethnicity).equals(Ethnicity.fromString(other.ethnicity)))
            return false;
        if (gender == null) {
            if (other.gender != null)
                return false;
        }
        else if (!gender.equals(other.gender))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        }
        else if (!name.equalsIgnoreCase(other.name))
            return false;
        if (rank != other.rank)
            return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("NameData [birthYear=")
            .append(birthYear)
            .append(", gender=")
            .append(gender)
            .append(", ethnicity=")
            .append(ethnicity)
            .append(", name=")
            .append(name)
            .append(", count=")
            .append(count)
            .append(", rank=")
            .append(rank)
            .append("]");
        return builder.toString();
    }



}
