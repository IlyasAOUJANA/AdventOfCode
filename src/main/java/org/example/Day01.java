package org.example;

import java.io.IOException;
import java.util.List;

public class Day01 {

    public static void reasult() {
        try {
            // Read Input File
            String inputFile = "src/main/resources/day01.txt";
            List<String> lines = Utils.readInput(inputFile);

            // Part 1
            int part1Result = solvePart1(lines);
            System.out.println("Part 1: " + part1Result);

            // Part 2
            int part2Result = solvePart2(lines);
            System.out.println("Part 2: " + part2Result);
        } catch (IOException e) {
            System.err.println("Error reading input file: " + e.getMessage());
        }
    }

    private static int solvePart1(List<String> rotations) {
        int zeroCount = 0;
        int position = 50;
//        System.out.println(rotations);

        for (String rotation : rotations){
            String rotationType = rotation.substring(0,1);
            int distance = Integer.parseInt(rotation.substring(1));
//            System.out.println(rotation);
//            System.out.println(rotationType);
//            System.out.println(distance);

            if (rotationType.equals("L")) {
                position = (position - distance) % 100;
            } else if (rotationType.equals("R")) {
                position = (position + distance) % 100;
            }

            if (position == 0) {
                zeroCount++;
            }
        }

        return zeroCount;
    }

    private static int solvePart2(List<String> rotations) {
        int zeroCount = 0;
        int position = 50;
//        System.out.println(rotations);

        for (String rotation : rotations){
            String rotationType = rotation.substring(0,1);
            int distance = Integer.parseInt(rotation.substring(1));
//            System.out.println(rotation);
//            System.out.println(rotationType);
//            System.out.println(distance);

            for (int i=0; i<distance; i++) {
                if (rotationType.equals("L")) {
                    position = (position - 1) % 100;
                } else if (rotationType.equals("R")) {
                    position = (position + 1) % 100;
                }

                if (position == 0)
                    zeroCount++;
            }

        }

        return zeroCount;
    }
}
