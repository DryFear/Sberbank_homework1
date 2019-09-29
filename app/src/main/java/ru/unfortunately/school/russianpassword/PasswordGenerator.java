package ru.unfortunately.school.russianpassword;

import java.util.ArrayList;
import java.util.Random;

public class PasswordGenerator {


    public static String generate(boolean caps, boolean nums, boolean symb, int lenght){
        StringBuilder result = new StringBuilder();
        ArrayList<Character> symbAscii = new ArrayList<>();

        for (int i = 97; i <= 119; i++) {
            symbAscii.add((char)i);
        }
        if(caps)
            for (int i = 65; i <= 87; i++) {
                symbAscii.add((char)i);
            }
        if(nums)
            for (int i = 48; i <= 57; i++) {
                symbAscii.add((char)i);
            }
        if(symb)
            for (int i = 58; i <= 64; i++) {
                symbAscii.add((char)i);
            }
        Random random = new Random();
        for (int i = 0; i < lenght; i++) {
            result.append(symbAscii.get(random.nextInt(symbAscii.size()-1)));
        }
        return result.toString();
    }
}
