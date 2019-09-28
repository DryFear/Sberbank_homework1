package ru.unfortunately.school.russianpassword;

public class MyHelper {

    private final String[] russians;
    private final String[] latin;

    public MyHelper(String[] russians, String[] latin) {
        this.russians = russians;
        this.latin = latin;

        if(russians.length != latin.length){
            throw new IllegalArgumentException();
        }
    }

    public String convert(CharSequence source){
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < source.length(); i++) {
            char c = source.charAt(i);
            String s = String.valueOf(c).toLowerCase();

            boolean flag = false;
            for (int j = 0; j < russians.length; j++) {
                if(russians[j].equals(s)){
                    result.append(Character.isUpperCase(c) ? latin[j].toUpperCase() : latin[j]);
                    flag = true;
                    break;
                }
            }
            if(!flag){
                result.append(c);
            }
        }
        return result.toString();
    }
}
