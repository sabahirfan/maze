package uk.gov.dwp.maze;

import uk.gov.dwp.maze.domain.Block;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by sabahirfan on 02/04/2017.
 */
public class Explorer {

    private boolean backward;
    private boolean done;
    private final Maze maze;

    public Explorer(Maze maze) {
        if (maze == null)
            throw new IllegalArgumentException("Null maze in explorer.");

        this.maze = maze;
    }

    public void exploreMaze() {
        Block startBlock = maze.getStartBlock();
        if (move(startBlock.getRow(), startBlock.getColumn())) {
            System.out.println(maze);
        }
    }


    /**
     * Builds the accumulated paths lead to the 'F' point as String
     *
     * @return
     */
    public String printExplorersPath() {
        StringBuilder result = new StringBuilder(maze.getWidth() * maze.getHeight());

        Block[][] map = maze.getBlock();
        for (int row = 0; row < maze.getHeight(); row++) {
            for (int col = 0; col < maze.getWidth(); col++) {

                if (map[row][col].isWalled()) {
                    result.append('X');
                } else if (map[row][col].isStart()) {
                    result.append('S');
                } else if (map[row][col].isExit()) {
                    result.append('F');
                } else if (maze.getPath().isOnPath(maze.getBlock(row, col))) {
                    result.append('*');
                } else {
                    result.append(' ');
                }
            }
            result.append('\n');
        }
        System.out.println(result.toString());
        return result.toString();
    }

    /**
     * Get the previous explored block.
     *
     * @return {@link Block} previously explored in current path.
     */
    public Block getPreviousExplored() {
        return maze.getPath().getPreviousExploredBlock();
    }

    /**
     * This move method continually seek for the next available movement until it finds the final happy path.
     *
     * @param row the row ordinal
     * @param col the column ordinal
     * @return true if there is open movement.
     */
    private boolean move(final int row, final int col) {
        //System.out.println(maze);
        Block currentBlock = maze.getBlock(row, col);

        if (currentBlock.isWalled() && !backward) {
            return false;
        } else if (maze.isExplored(row, col) && !backward) {
            return false;
        } else if (currentBlock.isExit()) {
            maze.markVisited(row, col, true);
            // found the exit 'F', need to notify the system to start recording the path backwards
            done = true;
            return true;
        } else {
            // can hit dead end if goes into this block - so we need to going backward until it hits another open route.
            maze.markVisited(row, col, true);
            Block nextBlock = turn(row, col);

            if (nextBlock != null) {
                if (move(nextBlock.getRow(), nextBlock.getColumn())) {
                    System.out.println(maze);
                    printExplorersPath();
                    return !done;
                }
            } else {
                // get previous explored block
                Block previousExplored = getPreviousExplored();
                this.backward = true;
                if (previousExplored != null && move(previousExplored.getRow(), previousExplored.getColumn())) {
                    System.out.println(maze);
                    return !done;
                }
            }
        }
        return false;
    }

    /**
     * Randomly turn left/right, may also choose forward/backward
     *
     * @param row row ordinal
     * @param col column ordinal
     * @return the next Block {@link Block} to move, null if there are not open block in the next move.
     */
    private Block turn(final int row, final int col) {
        Set<Block> openBlocks = getMovementOptions(maze, row, col);
        // if there are more than one open blocks in front randomly pick one up.
        int size = openBlocks.size();
        if (size > 0) {
            Random generator = new Random();
            int index = generator.nextInt(size);
            return openBlocks.toArray(new Block[size])[index];
        }
        return null;
    }

    /**
     * Finding the adjacent empty spaces to the current block.
     * <p>
     *
     * @param currentRow row ordinal
     * @param currentCol column ordinal
     * @return a set {@link java.util.Set< Block >} of open Blocks
     */
    public Set<Block> getMovementOptions(Maze maze, final int currentRow, final int currentCol) {
        Set<Block> openBlocks = new HashSet<>();

        // Check and add left option
        Block blockLeft = maze.getBlock(currentRow, currentCol - 1);
        if (blockLeft.isOpen() &&
                !maze.isExplored(currentRow, currentCol - 1)) {
            openBlocks.add(blockLeft);
        }
        // Check and add up option
        Block blockUp = maze.getBlock(currentRow - 1, currentCol);
        if (blockUp.isOpen() &&
                !maze.isExplored(currentRow - 1, currentCol)) {
            openBlocks.add(blockUp);
        }
        // Check and add Down option
        Block blockDown = maze.getBlock(currentRow + 1, currentCol);
        if (blockDown.isOpen() &&
                !maze.isExplored(currentRow + 1, currentCol)) {
            openBlocks.add(blockDown);
        }
        // Check and add right option
        Block blockRight = maze.getBlock(currentRow, currentCol + 1);
        if (blockRight.isOpen() &&
                !maze.isExplored(currentRow, currentCol + 1)) {
            openBlocks.add(blockRight);
        }

        return openBlocks;
    }


}
