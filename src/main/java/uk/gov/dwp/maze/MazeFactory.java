package uk.gov.dwp.maze;

import uk.gov.dwp.maze.domain.Block;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by sabahirfan on 31/03/2017.
 */
public class MazeFactory {

    public static Block[][] buildMazeMapFromString(String inputString) {
        if (inputString == null)
            return null;

        String[] lines = inputString.split("[\r]?\n");
        int height = lines.length;
        int width = lines[0].length();
        int startCount = 0;
        int exitCount = 0;
        Block[][] blocks = new Block[height][width];

        for (int row = 0; row < height; row++) {
            if (lines[row].length() != width) {
                throw new IllegalArgumentException("line " + (row + 1) + " wrong length "
                        + lines[row].length() + ", should be " + width);
            }

            for (int col = 0; col < width; col++) {
                Block block = new Block(row, col, lines[row].charAt(col));
                blocks[row][col] = block;
                if (block.isStart())
                    startCount++;

                if (block.isExit())
                    exitCount++;
            }
        }

        if (startCount != 1 || exitCount != 1)
            throw new IllegalArgumentException("Invalid map data - should have one and only one Start point 'S' and one and only one exit 'F'");

        return blocks;
    }

    public static Block[][] buildMazeMap(File inputFile) {
        if (inputFile == null) {
            throw new IllegalArgumentException("Invalid input file - file input cannot be null");
        }

        if (!inputFile.exists()) {
            throw new IllegalArgumentException("Invalid input file - file does not exist");
        }

        return buildMazeMapFromString(readLine(inputFile));
    }

    private static String readLine(File inputFile) {
        String line, mazeText = "";

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            while ((line = br.readLine()) != null) {
                mazeText += line + "\n";
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        if (mazeText == null || mazeText.trim().length() == 0) {
            throw new IllegalArgumentException("Invalid input file - empty lines");
        }
        return mazeText;
    }
}
