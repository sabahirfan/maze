package uk.gov.dwp.maze;

import uk.gov.dwp.maze.domain.Block;
import uk.gov.dwp.maze.domain.BlockState;
import uk.gov.dwp.maze.domain.Path;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sabahirfan on 02/04/2017.
 */
public class Maze {

    private final Block[][] maze;
    private final boolean[][] explored;
    private final Path path;
    private final int height;
    private final int width;

    /**
     * @param maze
     */
    public Maze(final Block[][] maze) {
        if (maze == null)
            throw new IllegalArgumentException("Cannot have null map in Maze");
        this.maze = maze;
        this.path = new Path();
        this.height = maze.length;
        this.width = maze[0].length;
        this.explored = new boolean[height][width];
    }

    /**
     * Get the path of the very final route.
     *
     * @return the Path stack that leads to solution.
     */
    public Path getPath() {
        return path;
    }

    /**
     * Mark a coordinate using the passed in flag.
     *
     * @param x       the row
     * @param y       the column
     * @param visited the flag whether this coordinate is visited.
     */
    public void markVisited(int x, int y, boolean visited) {
        if (!validateCoordinates(x, y))
            throw new IllegalArgumentException("Coordinates x: " + x + ", y: " + y + " is not valid");
        explored[x][y] = visited;
        path.addPath(maze[x][y]);
    }

    /**
     * Whether the coordinate block has been explored already.
     *
     * @param x the row
     * @param y the column
     * @return true - explored or otherwise false
     */
    public boolean isExplored(final int x, final int y) {
        return explored[x][y];
    }

    public boolean[][] getExplored() {
        return explored;
    }

    /**
     * @return instance of the current maze 2-d array, this is not null safe.
     */
    public Block[][] getBlock() {
        return this.maze;
    }

    /**
     * coordinates start from 0 ...
     *
     * @param x the row
     * @param y the column
     * @return the block located at the specific x and y coordinate.
     */
    public Block getBlock(final int x, final int y) {
        if (!validateCoordinates(x, y))
            return null;
        return maze[x][y];
    }

    /**
     * Get the ending block
     *
     * @return {@link Block} represents the ending point.
     */
    public Block getExitBlock() {
        List<Block> exits = findBlock(BlockState.FINISH);
        return exits.isEmpty() ? null : exits.get(0);
    }

    /**
     * Get the starting block
     *
     * @return {@link Block} represents the starting point.
     */
    public Block getStartBlock() {
        List<Block> starts = findBlock(BlockState.START);
        return starts.isEmpty() ? null : starts.get(0);
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getOpenSpacesCount() {
        return findBlock(BlockState.OPEN).size();
    }

    public int getWallCount() {
        return findBlock(BlockState.WALLED).size();
    }

    public int getStartCount() {
        return findBlock(BlockState.START).size();
    }

    public int getExitCount() {
        return findBlock(BlockState.FINISH).size();
    }

    /**
     * To count blocks for a specific state.
     *
     * @param state {@link BlockState}
     * @return the occurrences of the BlockState in the map.
     */
    private List<Block> findBlock(final BlockState state) {
        List<Block> results = new ArrayList<Block>();
        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < getWidth(); j++) {
                Block s = getBlock(i, j);
                if (s.getState() == state) {
                    results.add(s);
                }
            }
        }
        return results;
    }

    /**
     * Validates whether the coordinates are with in the range of Maze.
     *
     * @param row
     * @param col
     * @return true if valid coordinates, false otherwise
     */
    private boolean validateCoordinates(int row, int col) {
        return !(row < 0 || row >= getHeight() || col < 0 || col >= getWidth());
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(getWidth() * getHeight());
        for (int row = 0; row < getHeight(); row++) {
            for (int col = 0; col < getWidth(); col++) {
                if (maze[row][col].isWalled()) {
                    result.append('X');
                } else if (maze[row][col].isStart()) {
                    result.append('S');
                } else if (maze[row][col].isExit()) {
                    result.append('F');
                } else if (explored[row][col]) {
                    result.append('*');
                } else {
                    result.append(' ');
                }
            }
            result.append('\n');
        }
        return result.toString();
    }
}

