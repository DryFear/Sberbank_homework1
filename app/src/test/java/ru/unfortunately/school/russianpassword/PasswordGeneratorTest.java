package ru.unfortunately.school.russianpassword;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PasswordGeneratorTest {

    @Test
    public void generate() {
        for (int i = 0; i < 30; i++) {
            assertEquals(i, PasswordGenerator.generate(true, true, true, i).length());
        }
    }
}