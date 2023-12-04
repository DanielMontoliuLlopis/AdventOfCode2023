package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static String URL = "input.txt";

    public static void main(String[] args) {
        List<String> data = new ArrayList<>(loadData());
        List<String> preparedData = new ArrayList<>();
        data.forEach(line ->{
            preparedData.add("a"+line.replaceAll("[*]", "b").replaceAll("[^0-9|b]", "a")+"a");
        });

        List<StringNumber> numbersList = new ArrayList<>();

        preparedData.forEach(line ->{
            int firstPosNum = -1;
            int lastPosNum = -1;
            for (int i = 0; i < line.length(); i++) {
                if(Character.isDigit(line.charAt(i)) && firstPosNum == -1 ){
                    firstPosNum = i;
                }

                if(!Character.isDigit(line.charAt(i)) && firstPosNum != -1 ){
                    lastPosNum = i;
                    numbersList.add(new StringNumber(line.substring(firstPosNum, lastPosNum),preparedData.indexOf(line), firstPosNum, lastPosNum ));
                    firstPosNum = -1;
                }
            }
        });

        int sum = 0;
        for (String stringPrepared:
             preparedData) {
            for (int i = 0; i < stringPrepared.length(); i++) {
                if(stringPrepared.charAt(i) == 'b'){
                    int finalI = i;
                    List<StringNumber> adjacentsNums = numbersList.stream().filter(number ->
                            ((number.line - preparedData.indexOf(stringPrepared) <= 1 && number.line - preparedData.indexOf(stringPrepared) >= -1)
                                    ) &&
                                    ((number.initialIndex - finalI <= 1 && number.initialIndex - finalI >= -1)
                                            || (number.finalIndex - finalI <= 1 && number.finalIndex - finalI > -1))).toList();
                    System.out.println(adjacentsNums.size());
                    if(adjacentsNums.size() == 2) {
                        int mult ;
                       mult = Integer.parseInt(adjacentsNums.get(0).value) * Integer.parseInt(adjacentsNums.get(1).value);
                        sum += mult;
                    }
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
}



class StringNumber {
    String value;
    int line;
     int initialIndex;
     int finalIndex;

    public StringNumber(String value, int line, int initialIndex, int finalIndex) {
        this.value = value;
        this.line = line;
        this.initialIndex = initialIndex;
        this.finalIndex = finalIndex;
    }
    
}
