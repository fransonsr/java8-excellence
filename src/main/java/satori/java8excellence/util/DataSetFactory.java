package satori.java8excellence.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.prefs.CsvPreference;

import satori.java8excellence.DataSet;
import satori.java8excellence.NameData;

public class DataSetFactory {

	public DataSet load(InputStream inputStream) {
		return new DataSet(loadFromStream(inputStream));
	}

	private List<NameData> loadFromStream(InputStream inputStream) {
		List<NameData> data = new ArrayList<>();

		CsvPreference preferences = CsvPreference.STANDARD_PREFERENCE;
		Reader reader = new BufferedReader(new InputStreamReader(inputStream));
		try (CsvBeanReader beanReader = new CsvBeanReader(reader, preferences)) {
			String[] header = beanReader.getHeader(true);
			CellProcessor[] processors = new CellProcessor[] {
					new ParseInt(), // birthYear
					new NotNull(), // gender
					new NotNull(), // ethnicity
					new NotNull(), // name
					new ParseInt(), // count
					new ParseInt(), // rank
			};
			NameData nameData;
			while ((nameData = beanReader.read(NameData.class, header, processors)) != null) {
				data.add(nameData);
			}
		} catch (Exception e) {
			throw new RuntimeException("Unable to load data set", e);
		}

		return data;
	}

}
