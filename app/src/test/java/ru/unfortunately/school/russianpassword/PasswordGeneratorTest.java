package ru.unfortunately.school.russianpassword;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class PasswordGeneratorTest {

    @Test
    public void generate() {
        assertEquals(0, PasswordGenerator.generate(true, true, true, 0).length());
        Random random = new Random();
        for (int i = 0; i < 30; i++) {
            int len = random.nextInt(30);
            assertEquals(len, PasswordGenerator.generate(true, true, true, len).length());
        }
    }
}