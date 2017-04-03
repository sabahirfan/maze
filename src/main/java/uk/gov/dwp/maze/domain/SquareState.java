package uk.gov.dwp.maze.domain;

/**
 * Created by sabahirfan on 31/03/2017.
 */
public enum SquareState {
    OPEN,
    VISITED,
    WALLED,
    START,
    EXIT,
    UNKNOWN;

    public static SquareState getSquareState(final char sign) {
        if (' ' == sign)
            return SquareState.OPEN;
        else if ('X' == sign)
            return SquareState.WALLED;
        else if ('S' == sign)
            return SquareState.START;
        else if ('F' == sign)
            return SquareState.EXIT;
        else if ('V' == sign)
            return SquareState.VISITED;
        else
            return SquareState.UNKNOWN;
    }
}