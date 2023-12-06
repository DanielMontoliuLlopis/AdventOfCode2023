package main.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day6_2 {
    static String URL = "C:/Users/llodamo/Downloads/input1.txt";
    public static void main(String[] args) {
        List<String> data = loadData();
        List<String> raceTimes = List.of(data.get(0).split(":")[1].trim().replaceAll("\\s+", ""));
        List<String> distanceRecords = List.of(data.get(1).split(":")[1].trim().replaceAll("\\s+", ""));
        List<Race> races = new ArrayList<>();

        for (int i = 0; i < raceTimes.size(); i++) {
            if(raceTimes.get(i) != "" && distanceRecords.get(i) != "")
             races.add(new Race(Long.parseLong(raceTimes.get(i)), Long.parseLong(distanceRecords.get(i))));
        }

        int result = 1;
        for (Race race:
             races) {
            result *= race.posibilitiesToBreakTheRecord();
        }
        System.out.println(result);
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

class Race {
    Long raceTime;
    Long recordDistance;

    public Race(Long raceTime, Long recordDistance) {
        this.raceTime = raceTime;
        this.recordDistance = recordDistance;
    }

    public Long posibilitiesToBreakTheRecord () {
        Long count = 0L;
        for (int i = 0; i < raceTime; i++) {
            Long timeToRun = raceTime - i;
            if(i * timeToRun > recordDistance) count++;
        }
        return  count == 0 ? 1 : count;
    }
}
