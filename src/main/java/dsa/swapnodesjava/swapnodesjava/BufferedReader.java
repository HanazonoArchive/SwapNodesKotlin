package dsa.swapnodesjava.swapnodesjava;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BufferedReader {

    public List<int[]> readAndParseFile(java.io.BufferedReader reader) throws IOException {
        List<int[]> pairs = new ArrayList<>();

        // Use BufferedReader to read file content
        StringBuilder content = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            content.append(line).append("\n");
        }

        // Split the content by new lines to handle each line separately
        String[] lines = content.toString().trim().split("\n");

        for (String ln : lines) {
            // Split by spaces and trim whitespace for each line
            String[] parts = ln.trim().split(" ");

            // Convert each part to an integer and add as a pair (2-item array)
            if (parts.length == 2) { // Ensure there are exactly two parts
                int[] pair = new int[2];
                pair[0] = Integer.parseInt(parts[0]);
                pair[1] = Integer.parseInt(parts[1]);
                pairs.add(pair); // Add the pair to the list
            }
        }

        return pairs;
    }

}
