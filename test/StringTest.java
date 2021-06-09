import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;
public class StringTest {

    @BeforeAll
    static void beforeAll() {
        System.out.println("Initialize connection to database");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("Close connection to database");
    }

    @BeforeEach
    void beforeEach(TestInfo info){
        System.out.println("Initialize Test Data for test: " + info.getDisplayName());
    }
    @AfterEach
    void afterEach(TestInfo info){
        System.out.println("Clean up Test Data after test: " + info.getDisplayName());
    }

    @Test
    void lengthBasic() {
        int actualLength = "Memet".length();
        int expectedLength = 5;
        assertEquals(expectedLength,actualLength);
    }

    @Test
    @DisplayName("When length is null, throw an exception")
    void lengthException() {
        String str = null;
        assertThrows(NullPointerException.class,
                ()->{
                         str.length();
                                         }
                                         );
    }

    @Test
    void lengthGreaterThanZero() {
        assertTrue("ABCD".length()>0);
        assertTrue("ABC".length()>0);
        assertTrue("A".length()>0);
        assertTrue("DEF".length()>0);

    }
    @ParameterizedTest
    @ValueSource(strings = {"ABCD",  "ABC", "A", "DEF"})
    void lengthGreaterThanZeroUsingParameterizedTest(String str) {
        assertTrue(str.length()>0);
    }

    @ParameterizedTest(name = "{0} when uppercased is {1}")
    @CsvSource(value = {"abcd, ABCD","abc, ABC","'',''","abcdefg, ABCDEFG"})
    void uppercase(String word, String capitalizedWord) {
        assertEquals(capitalizedWord,word.toUpperCase());
    }

    @ParameterizedTest(name = "{0} length is {1}")
    @CsvSource(value = {"abcd, 4","abc, 3","'',0","abcdefg, 7"})
    void length(String word, int expectedLength) {
        assertEquals(expectedLength,word.length());
    }
    @ParameterizedTest(name = "{1} substring of {0}")
    @CsvSource(value = {"organize , ganize","delicate, licate",
            "treasurer,easurer","reaction, action","radio, dio"})
    void substring(String word, String substr){
        assertEquals(substr, word.substring(2));
    }

    @Test
    void toUpperCase() {
        String str = "abcd";
        String result =str.toUpperCase();
        assertNotNull(result);
        assertEquals("ABCD",result);
    }

    @Test
    @RepeatedTest(2)
    void containsBasic() {
    assertFalse("abcdefgh".contains("ijk"));

    }

    @Test
    void splitBasic() {
        String str = "abc def ghi";
        String[] actualResult = str.split(" ");
        String[] expectedResult = new String[]{"abc", "def", "ghi"};
        assertArrayEquals(expectedResult,actualResult);
    }
}
