package org.example;

import java.io.IOException;
import java.util.List;

public class Day01 {
    /**
     * Reads Input From File
     */
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
        int position = 50; // Starting position
        int zeroCount = 0;

        for (String rotation : rotations) {
            char direction = rotation.charAt(0);
            int distance = Integer.parseInt(rotation.substring(1));

            // Apply rotation
            if (direction == 'L') {
                position = (position - distance) % 100;
            } else { // direction == 'R'
                position = (position + distance) % 100;
            }

            // Handle negative wraparound
            if (position < 0) {
                position += 100;
            }

            // Check if dial points to 0
            if (position == 0) {
                zeroCount++;
            }
        }

        return zeroCount;
    }

    private static int solvePart2(List<String> rotations) {
        int position = 50;     // starting position
        int zeroCount = 0;    // we count zeros, including those in-between

        for (String rotation : rotations) {
            char direction = rotation.charAt(0);
            int distance = Integer.parseInt(rotation.substring(1));

            for (int i = 0; i < distance; i++) {
                if (direction == 'L') {
                    position = (position - 1 + 100) % 100;
                } else { // R
                    position = (position + 1) % 100;
                }

                if (position == 0) {
                    zeroCount++;
                }
            }
        }

        return zeroCount;
    }
}
