import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Day2_1 {

    final static String URL = "input.txt";
    final static String RED = "red";
    final static String BLUE = "blue";
    final static String GREEN = "green";

    final static int MAX_RED_COUNT = 12;
    final static int MAX_BLUE_COUNT = 14;
    final static int MAX_GREEN_COUNT = 13;

    final static String ONLY_NUMBERS_REGEX = "[^0-9]";
    final static String IDENTIFIER_SPLITER = ":";
    final static String SETS_SPLITER = ";";
    final static String NUMBERS_SPLITER = ",";
    final static String EMPTY_STRING = "";



    public static void main(String[] args) {
        List<String> list = loadData();
        List<List<String>> splitedList = new ArrayList<>();
        for (String line:
             list) {
            splitedList.add(List.of(line.split(IDENTIFIER_SPLITER)));
        }

        int sum = 0;

        for (List<String> splitedLine:
                splitedList) {
            List<String> sets = List.of(splitedLine.get(1).split(SETS_SPLITER));
            boolean allTrue = sumAndCheckSets(sets);
            if(allTrue){
                sum += Integer.parseInt(splitedLine.get(0).replaceAll(ONLY_NUMBERS_REGEX, EMPTY_STRING));
            }

        }
        System.out.println(sum);
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

    static List<String> loadMockData () {

        return List.of("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green",
        "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue",
        "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red",
        "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red",
        "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green");
    }

    static boolean sumAndCheckSets(List<String> sets) {
        for (String set:
                sets) {
            int blueCount = 0;
            int redCount = 0;
            int greenCount = 0;
            List <String> numberAndColor = List.of(set.split(NUMBERS_SPLITER));
            for (String option:
                    numberAndColor) {
                if (option.contains(RED)){
                    redCount += Integer.parseInt(option.replaceAll(ONLY_NUMBERS_REGEX, EMPTY_STRING));
                }

                if (option.contains(BLUE)){
                    blueCount += Integer.parseInt(option.replaceAll(ONLY_NUMBERS_REGEX, EMPTY_STRING));
                }

                if (option.contains(GREEN)){
                    greenCount += Integer.parseInt(option.replaceAll(ONLY_NUMBERS_REGEX, EMPTY_STRING));
                }
            }

            if(redCount > MAX_RED_COUNT || greenCount > MAX_GREEN_COUNT || blueCount > MAX_BLUE_COUNT){
                return false;
            }
        }
        return true;
    }
}
