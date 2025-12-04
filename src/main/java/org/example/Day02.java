package org.example;

import java.io.IOException;
import java.util.Arrays;

public class Day02 {

    public static void reasult() {
        try {
            // Read Input File
            String inputFile = "src/main/resources/day02.txt";
            String input = Utils.readIdRangeInput(inputFile);

            // Part 1
            long part1Result = solvePart1(input);
            System.out.println("Part 1: " + part1Result);

            // Part 2
            long part2Result = solvePart2(input);
            System.out.println("Part 2: " + part2Result);

        } catch (IOException e) {
            System.err.println("Error reading input file: " + e.getMessage());
        }
    }

    private static long solvePart1(String data) {
        return sumInvalidIdsP1(data);
    }

    private static long sumInvalidIdsP1(String input) {
        long totalSum = 0;
        String[] ranges = input.split(",");

        for (String range : ranges) {
            String[] parts = range.split("-");
            long start = Long.parseLong(parts[0]);
            long end = Long.parseLong(parts[1]);

            totalSum += sumInvalidIdsInRangeP1(start, end);
        }

        return totalSum;
    }

    private static long sumInvalidIdsInRangeP1(long start, long end) {
        long sum = 0;

        for (long id = start; id <= end; id++) {
            if (isInvalidIdP1(id)) {
                sum += id;
            }
        }

        return sum;
    }

    private static boolean isInvalidIdP1(long id) {
        String idStr = String.valueOf(id);
        int len = idStr.length();

        // Must have even length to be split in half
        if (len % 2 != 0) {
            return false;
        }

        int mid = len / 2;
        String firstHalf = idStr.substring(0, mid);
        String secondHalf = idStr.substring(mid);

        // Check if both halves are equal
        return firstHalf.equals(secondHalf);
    }

    private static long solvePart2(String data) {
        return sumInvalidIdsP2(data);
    }

    private static long sumInvalidIdsP2(String input) {
        long totalSum = 0;
        String[] ranges = input.split(",");

        for (String range : ranges) {
            String[] parts = range.split("-");
            long start = Long.parseLong(parts[0]);
            long end = Long.parseLong(parts[1]);

            totalSum += sumInvalidIdsInRangeP2(start, end);
        }

        return totalSum;
    }

    private static long sumInvalidIdsInRangeP2(long start, long end) {
        long sum = 0;

        for (long id = start; id <= end; id++) {
            if (isInvalidIdP2(id)) {
                sum += id;
            }
        }

        return sum;
    }

    public static boolean isInvalidIdP2(long id) {
        String idStr = String.valueOf(id);
        int len = idStr.length();

        // Try all possible pattern lengths (from 1 to len/2)
        for (int patternLen = 1; patternLen <= len / 2; patternLen++) {
            // Check if the length is divisible by pattern length
            if (len % patternLen == 0) {
                String pattern = idStr.substring(0, patternLen);
                boolean isValid = true;

                // Check if the entire string is made of this pattern repeated
                for (int i = patternLen; i < len; i += patternLen) {
                    String segment = idStr.substring(i, i + patternLen);
                    if (!segment.equals(pattern)) {
                        isValid = false;
                        break;
                    }
                }

                // If we found a repeating pattern, it's invalid
                if (isValid) {
                    return true;
                }
            }
        }

        return false;
    }
}
