package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Utils {
    /**
     * Reads Input From File
     */
    public static List<String> readInput(String filename) throws IOException {
        List<String> lines = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines;
    }

    public static String readIdRangeInput(String filename) throws IOException {
        return Files.readString(Path.of(filename)).trim();
    }
}
