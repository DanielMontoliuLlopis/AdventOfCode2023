import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    final static String URL = "input.txt";
    final static String IGNORED_CHARACTER = "a";
    final static String CHARACTER_TO_SUM = "b";

    final static int FIRST_POS = 0;
    final static int PREVIOUS_OR_NEXT_POS = 1;

    public static void main(String[] args) {
        List<String> data = loadData();
        int sum = FIRST_POS;
        List<String> preparedData = new ArrayList<>();
        for (String preparing:
             data) {
            String lineCharacters = preparing.replaceAll
                    ("\\.", IGNORED_CHARACTER).replaceAll("[^0-9|a]", CHARACTER_TO_SUM) + IGNORED_CHARACTER;
            preparedData.add(lineCharacters);
        }


        for (int i = FIRST_POS; i < preparedData.size(); i++) {
            String lineCharacters = preparedData.get(i);

            int firstNumIndex = -1;
            for (int j = FIRST_POS; j < lineCharacters.length(); j++) {
                if (Character.isDigit(lineCharacters.charAt(j)) && firstNumIndex == -1) {
                    firstNumIndex = j;
                }

                if ((!Character.isDigit(lineCharacters.charAt(j)) || lineCharacters.charAt(j) == '.')
                        && firstNumIndex != -1) {
                    int lastNumIndex = j;
                    String num = lineCharacters.substring(firstNumIndex, lastNumIndex);
                    if (i != FIRST_POS) {
                        int valueToPlus = checkAndSumPreviousLine(firstNumIndex, lastNumIndex, preparedData, i, num);
                        if(valueToPlus != 0) {
                            sum += valueToPlus;
                            firstNumIndex = -1;
                            continue;
                        }
                    }

                    if (i + 1 < preparedData.size()) {
                        int valueToPlus = checkAndSumNextLine(firstNumIndex, lastNumIndex, preparedData, i, num);
                        if(valueToPlus != 0) {
                            sum += valueToPlus;
                            firstNumIndex = -1;
                            continue;
                        }
                    }
                    int valueToPlus = checkPreviousAndNextCharacter(firstNumIndex, lastNumIndex, preparedData, i, num);
                    if(valueToPlus != 0) {
                        sum += valueToPlus;
                    }

                    firstNumIndex = -1;
                }

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

        return List.of(
               "........",
        ".24....4",
"......*."
                       );

    }
    static int stringContainsTheCharacter(String stringToCheck, int num) {
        if (stringToCheck.contains(CHARACTER_TO_SUM)) {
            return num;
        }
        return 0;
    }

    static int checkAndSumPreviousLine(int firstNumIndex, int lastNumIndex, List<String> preparedData, int i, String num) {
        String previousLine = preparedData.get(i - PREVIOUS_OR_NEXT_POS);
        if (firstNumIndex != FIRST_POS) {
            if (lastNumIndex < previousLine.length()) {
                if (previousLine.substring(firstNumIndex - PREVIOUS_OR_NEXT_POS, lastNumIndex + PREVIOUS_OR_NEXT_POS)
                        .contains(CHARACTER_TO_SUM)) {
                    return Integer.parseInt(num);

                }
            } else {
                if (previousLine.substring(firstNumIndex - PREVIOUS_OR_NEXT_POS)
                        .contains(CHARACTER_TO_SUM)) {
                    return Integer.parseInt(num);
                }
            }
        } else {
            if (lastNumIndex < preparedData.get(i - PREVIOUS_OR_NEXT_POS).length()) {
                if (previousLine.substring(FIRST_POS, lastNumIndex + PREVIOUS_OR_NEXT_POS)
                        .contains(CHARACTER_TO_SUM)) {
                    return Integer.parseInt(num);

                }
            } else {
                if (previousLine.contains(CHARACTER_TO_SUM)) {
                    return Integer.parseInt(num);
                }
            }
        }
        return 0;
    }

    static int checkAndSumNextLine(int firstNumIndex, int lastNumIndex, List<String> preparedData, int i, String num) {
        String nextLine = preparedData.get(i + 1);
        if (firstNumIndex != FIRST_POS) {
            if (lastNumIndex + PREVIOUS_OR_NEXT_POS < nextLine
                    .length()) {
                if (nextLine
                        .substring(firstNumIndex - PREVIOUS_OR_NEXT_POS, lastNumIndex + PREVIOUS_OR_NEXT_POS)
                        .contains(CHARACTER_TO_SUM)) {
                    return Integer.parseInt(num);
                }
            } else {
                if (nextLine
                        .substring(firstNumIndex - PREVIOUS_OR_NEXT_POS)
                        .contains(CHARACTER_TO_SUM)) {
                    return Integer.parseInt(num);
                }
            }
        } else {
            if (lastNumIndex < nextLine.length()) {
                if (preparedData.get(i + PREVIOUS_OR_NEXT_POS)
                        .substring(FIRST_POS, lastNumIndex + PREVIOUS_OR_NEXT_POS)
                        .contains(CHARACTER_TO_SUM)) {
                    return Integer.parseInt(num);
                }
            } else {
                if (nextLine.contains(CHARACTER_TO_SUM)) {
                    return Integer.parseInt(num);
                }
            }
        }
        return 0;
    }

    static int checkPreviousAndNextCharacter(int firstNumIndex, int lastNumIndex, List<String> preparedData, int i, String num) {
        String line = preparedData.get(i);
        if (firstNumIndex != FIRST_POS) {
            if (lastNumIndex < line.length()) {
                if (line.substring(firstNumIndex - PREVIOUS_OR_NEXT_POS, lastNumIndex + PREVIOUS_OR_NEXT_POS)
                        .contains(CHARACTER_TO_SUM)) {
                    return Integer.parseInt(num);
                }
            } else {
                if (line.substring(firstNumIndex - PREVIOUS_OR_NEXT_POS).contains(CHARACTER_TO_SUM)) {
                   return Integer.parseInt(num);
                }
            }
        } else {
            if (lastNumIndex < line.length()) {
                if (line.substring(FIRST_POS, lastNumIndex + PREVIOUS_OR_NEXT_POS).contains(CHARACTER_TO_SUM)) {
                    return Integer.parseInt(num);
                }
            } else {
                if (line.contains(CHARACTER_TO_SUM)) {
                    return Integer.parseInt(num);

                }
            }
        }
        return 0;
    }
}
