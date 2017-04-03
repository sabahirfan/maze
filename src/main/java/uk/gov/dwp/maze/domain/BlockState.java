package uk.gov.dwp.maze.domain;

/**
 * This enum represents different possible states of the unit in two dimensional Maze.
 * <p>
 * Created by sabahirfan on 31/03/2017.
 */
public enum BlockState {
    OPEN,
    VISITED,
    WALLED,
    START,
    FINISH,
    UNKNOWN;

    /**
     * Converts character to respective Enum object.
     *
     * @param sign
     * @return
     */
    public static BlockState getBlockState(final char sign) {
        if (' ' == sign)
            return BlockState.OPEN;
        else if ('X' == sign)
            return BlockState.WALLED;
        else if ('S' == sign)
            return BlockState.START;
        else if ('F' == sign)
            return BlockState.FINISH;
        else if ('V' == sign)
            return BlockState.VISITED;
        else
            return BlockState.UNKNOWN;
    }
}