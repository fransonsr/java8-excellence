package satori.java8excellence;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

public class NameDataTest {

	@Test
	public void predicate_isFemale() throws Exception {
		NameData instance = new NameData();
		instance.setGender("FEMALE");

		assertThat(NameData.isFemale.test(instance), is(true));
		assertThat(NameData.isMale.test(instance), is(false));
	}

	@Test
	public void predicate_isMale() throws Exception {
		NameData instance = new NameData();
		instance.setGender("MALE");

		assertThat(NameData.isMale.test(instance), is(true));
		assertThat(NameData.isFemale.test(instance), is(false));
	}
}
