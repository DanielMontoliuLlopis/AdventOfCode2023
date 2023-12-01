
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day1 {

    static final HashMap<String, String> MAP = new HashMap<>();
    static final String URL = "";
    static void initMap() {
        MAP.put("one","1");
        MAP.put("two","2");
        MAP.put("three","3");
        MAP.put("four","4");
        MAP.put("five","5");
        MAP.put("six","6");
        MAP.put("seven","7");
        MAP.put("eight","8");
        MAP.put("nine","9");
    }

    static List<String> loadData () {
        try {
            BufferedReader br = new BufferedReader(new FileReader(URL));
            List<String> list = new ArrayList<>();
            try {
                String line = br.readLine();

                while (line != null) {
                    list.add(line);
                    line = br.readLine();
                }

            } finally {
                br.close();
            }
            return list;
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return List.of();
    }

    public static void main(String[] args) {
        initMap();
        List<String> list = loadData();
        String onlyNumbersRegex = "[^0-9]";

        List<String> replacedStrings = new ArrayList<>();
        for (String line:
                list) {
            String newWordFirst = getFirstDigit(line);
            String newWordLast = getLastDigit(line);

            newWordFirst = newWordFirst.replaceAll(onlyNumbersRegex, "");
            newWordLast = newWordLast.replaceAll(onlyNumbersRegex, "");
            replacedStrings.add(newWordFirst.charAt(0) + "" + newWordLast.charAt(newWordLast.length()-1));
        }

        long sum = 0;
        for(String number: replacedStrings) {
            int num = Integer.parseInt(number);
            sum += num;
        }
        System.out.println(sum);
    }

    static String getFirstDigit(String line) {
        String changeFirstDigit = line;
        for (int i = 0; i <  changeFirstDigit.length(); i++) {

            for(Map.Entry<String, String> numberString:
                    MAP.entrySet()) {
                if(subStringContainsKey(changeFirstDigit.substring(0, i + 1), numberString.getKey())){
                    changeFirstDigit = changeFirstDigit.replaceFirst(numberString.getKey(), numberString.getValue());
                    return changeFirstDigit;
                }
                if(Character.isDigit(changeFirstDigit.charAt(i))) {
                    return changeFirstDigit;
                }
            }
        }
        return changeFirstDigit;
    }

    static String getLastDigit(String line) {
        String changeLastDigit = line;
        for (int i = changeLastDigit.length() - 1; i >= 0; i--) {
            for(Map.Entry<String, String> numberString:
                    MAP.entrySet()) {
                if(subStringContainsKey(changeLastDigit.substring(i), numberString.getKey())){
                    changeLastDigit = changeLastDigit.replace(numberString.getKey(), numberString.getValue());
                    return changeLastDigit;
                }
                if(Character.isDigit(changeLastDigit.charAt(i))) {
                    return changeLastDigit;
                }
            }
        }
        return changeLastDigit;
    }

    static boolean subStringContainsKey (String text, String textToFind) { return text.contains(textToFind);}
}
