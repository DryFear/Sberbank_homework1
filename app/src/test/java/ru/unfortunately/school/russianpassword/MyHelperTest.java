package ru.unfortunately.school.russianpassword;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MyHelperTest {

    private static final String[] RUS = {"й", "ц", "у", "к", "е", "н"};
    private static final String[] ENG = {"q", "w", "e", "r", "t", "y"};

    public static final String[] SOURCES = {
            "",
            "некуцй",
            "ЙЦУКЕН",
            "зщщшгн"
    };

    public static final String[] RESULTS = {
            "",
            "ytrewq",
            "QWERTY",
            "зщщшгy"
    };

    private MyHelper helper;

    @Before
    public void setUp() throws Exception {
        helper = new MyHelper(RUS, ENG);
    }

    @Test
    public void convert() {
        assertTrue("Error in the test", SOURCES.length == RESULTS.length);
        for (int i = 0; i < SOURCES.length; i++) {
            assertEquals(RESULTS[i], helper.convert(SOURCES[i]));
        }
    }
}