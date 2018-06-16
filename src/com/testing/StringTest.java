package com.testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class StringTest {
	
	@BeforeAll
	static void beforeAll() {
		System.out.println("BeforeAll");
	}
	
	@AfterAll
	static void afterAll() {
		System.out.println("AfterAll");
	}
	
	@BeforeEach
	void beforeEach() {
		System.out.println("BeforeEach");
	}
	
	@AfterEach
	void afterEach() {
		System.out.println("AfterEach");
	}

	@Test
	void arrayTest() {
		
		String str = "abc def ghi";
		String actualResult[] = str.split(" ");
		String expectedResult[] = new String[] {"abc", "def", "ghi"};
		
		assertArrayEquals(expectedResult, actualResult);
	} 
	
	@Test
	@DisplayName("When length is null, throws an exception.")
	void exceptionTest() {
		
		String str = null;		
		assertThrows(NullPointerException.class, 
				() -> {
					str.length();
				}
				);
	} 
	
	@ParameterizedTest
	@ValueSource(strings = {"ABCD", "ABC", "A", "DEF"})
	void parametersTest(String str) {		
		assertTrue(str.length() > 0);
	}
	
	@ParameterizedTest
	@CsvSource(value = {"abcd, ABCD", "abc, ABC", "'',''", "def, DEF"})
	void parametersTest2(String word, String capitalizedWord) {		
		assertEquals(capitalizedWord, word.toUpperCase());
	}

	@ParameterizedTest(name = "{0} to upper case is {1}")
	@CsvSource(value = {"abcd, ABCD", "abc, ABC", "'',''", "def, DEF"})
	void parametersTest3(String word, String capitalizedWord) {		
		assertEquals(capitalizedWord, word.toUpperCase());
	}
	
	@Test
	@RepeatedTest(10) // Since JUnit5
	void repetitiveTest() {
		System.out.println("Repetitive Test");
	}
	
	@Test
	void performanceTest() {  // Since Junit5
		assertTimeout(Duration.ofSeconds(5), // expected duration
				() -> {
					for (int i = 0; i <= 10; i++) { // operation to be tested
						int j = i;
						System.out.println(j);
					}
				}
				);
	}
	
	@Test
	@Disabled // @Ignore in Junit4
	void disableTest() {
		System.out.println("Disable Test");
	}
	
	private String str;
	
	@Nested
	class NestedTests {
		
		@BeforeEach
		void setToEmpty() {
			str = "";
		}
		
		@Test
		void lengthIsZero() {
			assertEquals(0, str.length());
		}
		
		@Test
		void uppercaseIsEmpty() {
			assertEquals("", str.toUpperCase());
		}
	}
}
