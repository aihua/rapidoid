package org.rapidoid.jackson;

/*
 * #%L
 * rapidoid-jackson
 * %%
 * Copyright (C) 2014 - 2015 Nikolche Mihajlovski and contributors
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.rapidoid.io.IO;
import org.rapidoid.test.TestCommons;
import org.rapidoid.util.U;

import com.fasterxml.jackson.core.type.TypeReference;

class Persons extends TypeReference<List<Person>> {}

/**
 * @author Nikolche Mihajlovski
 * @since 4.1.0
 */
public class YAMLTest extends TestCommons {

	private final TypeReference<List<Person>> personList = new TypeReference<List<Person>>() {};

	@Test
	public void parseMap() {
		String yaml = IO.load("test.yaml");
		Map<String, Object> data = YAML.parseMap(yaml);
		eq(data, U.map("aa", 1, "bb", "2am", "cc", U.map("x", true, "z", false)));
	}

	@Test
	public void parseBeans() {
		String yaml = IO.load("persons.yaml");

		List<Person> persons = YAML.parse(yaml, personList);
		eq(persons.size(), 2);

		Person p1 = persons.get(0);
		eq(p1.id, 123);
		eq(p1.name, "John Doe");
		eq(p1.age, 50);

		Person p2 = persons.get(1);
		eq(p2.name, "Highlander");
		eq(p2.age, 900);
	}

}
