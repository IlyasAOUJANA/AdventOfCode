package org.example;

import java.io.IOException;
import java.util.List;

public class Day03 {
    public static void reasult() {
        try {
            // Read Input File
            String inputFile = "src/main/resources/day03.txt";
            List<String> inputs = Utils.readInput(inputFile);
            // Part 1
            int part1Result  = solvePart1(inputs);
            System.out.println("Part 1: " + part1Result);

            // Part 2
            long part2Result  = solvePart2(inputs);
            System.out.println("Part 1: " + part2Result);

        } catch (IOException e) {
            System.err.println("Error reading input file: " + e.getMessage());
        }
    }

    private static int solvePart1(List<String> inputs) {
        int total = 0;

        for (String input : inputs) {
            int maxJoltage = findMaxJoltage(input);
            total += maxJoltage;
        }

        return total;
    }

    public static int findMaxJoltage(String input) {
        int maxJoltage = 0;
        int len = input.length();

        // Try all pairs of positions (i, j) where i < j
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                // Form a 2-digit number from digits at positions i and j
                int digit1 = input.charAt(i) - '0';
                int digit2 = input.charAt(j) - '0';
                int joltage = digit1 * 10 + digit2;

                maxJoltage = Math.max(maxJoltage, joltage);
            }
        }

        return maxJoltage;
    }

    private static long solvePart2(List<String> inputs) {
        long total = 0;

        for (String input : inputs) {
            long maxJoltage = findMaxJoltagePartTwo(input, 12);
            total += maxJoltage;
        }

        return total;
    }

    public static long findMaxJoltagePartTwo(String input, int digitsToKeep) {
        int len = input.length();
        int toRemove = len - digitsToKeep;

        StringBuilder result = new StringBuilder();
        int removed = 0;

        // Greedy approach: use a monotonic stack-like algorithm
        for (int i = 0; i < len; i++) {
            char current = input.charAt(i);

            // Remove smaller digits from result if we still have removals left
            // and the current digit is larger
            while (!result.isEmpty() &&
                    removed < toRemove &&
                    result.charAt(result.length() - 1) < current) {
                result.deleteCharAt(result.length() - 1);
                removed++;
            }

            result.append(current);
        }

        // If we haven't removed enough digits, remove from the end
        while (removed < toRemove) {
            result.deleteCharAt(result.length() - 1);
            removed++;
        }

        return Long.parseLong(result.toString());
    }
}
