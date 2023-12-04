import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Day4_2 {
    static  String URL = "input.txt";
    public static void main(String[] args) {
        List<String> allData = loadData();
        List<String> numbersData = new ArrayList<>();
        allData.forEach(line -> numbersData.add(line.split(":")[1]));

        int sum = 0;
        List<Integer> iterations = new ArrayList<>();
        numbersData.forEach(data -> iterations.add(1));

        for (String data: numbersData) {
            String newData = data.replaceAll("  ", " ").trim();
            List<String> winners = new ArrayList<>(List.of(newData.split("\\|")[0].split(" ")));
            List<String> yourNumbers = List.of(newData.split("\\|")[1].split(" "));


            winners.retainAll(yourNumbers);

            for (int i = numbersData.indexOf(data) +1  ; i < numbersData.indexOf(data) + winners.size() +1 ; i++) {
                iterations.set(i , iterations.get(i) + iterations.get(numbersData.indexOf(data)));
            }
            System.out.println(winners.size());

        }
        for (int num: iterations) {
            sum += num;
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
    static List<String> loadDemoData() {
        return List.of("Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53",
        "Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19",
        "Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1",
        "Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83",
        "Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36",
        "Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11");
    }
}
