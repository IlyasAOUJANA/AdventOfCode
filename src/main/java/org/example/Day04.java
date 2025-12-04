package org.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day04 {

    // 8 directions: up, down, left, right, and 4 diagonals
    private static final int[][] DIRECTIONS = {
            {-1, 0},  // up
            {1, 0},   // down
            {0, -1},  // left
            {0, 1},   // right
            {-1, -1}, // up-left
            {-1, 1},  // up-right
            {1, -1},  // down-left
            {1, 1}    // down-right
    };

    public static void reasult() {
        try {
            // Read Input File
            String inputFile = "src/main/resources/day04.txt";
            List<String> lines = Utils.readInput(inputFile);

            // Part 1
            int part1Result  = solvePart1(lines.toArray(new String[0]));
            System.out.println("Part 1: " + part1Result);

            // Part 2
            int part2Result  = solvePart2(lines.toArray(new String[0]));
            System.out.println("Part 2: " + part2Result);

        } catch (IOException e) {
            System.err.println("Error reading input file: " + e.getMessage());
        }
    }

    private static int solvePart1(String[] inputs) {
        return countAccessibleRolls(inputs);
    }

    public static int countAccessibleRolls(String[] grid) {
        int count = 0;
        int rows = grid.length;
        int cols = grid[0].length();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row].charAt(col) == '@') {
                    int adjacentRolls = countAdjacentRollsP1(grid, row, col);
                    if (adjacentRolls < 4) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    public static int countAdjacentRollsP1(String[] grid, int row, int col) {
        int count = 0;
        int rows = grid.length;
        int cols = grid[0].length();

        // Check all 8 adjacent positions
        for (int[] dir : DIRECTIONS) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];

            // Check if position is within bounds
            if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols) {
                if (grid[newRow].charAt(newCol) == '@') {
                    count++;
                }
            }
        }

        return count;
    }

    private static int solvePart2(String[] inputs) {
        return removeAllAccessibleRolls(inputs);
    }


    public static int removeAllAccessibleRolls(String[] originalGrid) {
        // Create a mutable copy of the grid
        char[][] grid = new char[originalGrid.length][];
        for (int i = 0; i < originalGrid.length; i++) {
            grid[i] = originalGrid[i].toCharArray();
        }

        int totalRemoved = 0;
        int round = 0;

        while (true) {
            // Find all accessible rolls in this iteration
            List<int[]> toRemove = new ArrayList<>();
            int rows = grid.length;
            int cols = grid[0].length;

            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    if (grid[row][col] == '@') {
                        int adjacentRolls = countAdjacentRollsP2(grid, row, col);
                        if (adjacentRolls < 4) {
                            toRemove.add(new int[]{row, col});
                        }
                    }
                }
            }

            // If no rolls can be removed, stop
            if (toRemove.isEmpty()) {
                break;
            }

            // Remove all accessible rolls
            for (int[] pos : toRemove) {
                grid[pos[0]][pos[1]] = '.';
            }

            round++;
            totalRemoved += toRemove.size();
        }

        return totalRemoved;
    }

    public static int countAdjacentRollsP2(char[][] grid, int row, int col) {
        int count = 0;
        int rows = grid.length;
        int cols = grid[0].length;

        // Check all 8 adjacent positions
        for (int[] dir : DIRECTIONS) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];

            // Check if position is within bounds
            if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols) {
                if (grid[newRow][newCol] == '@') {
                    count++;
                }
            }
        }

        return count;
    }
}
