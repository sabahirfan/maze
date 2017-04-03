package uk.gov.dwp.maze.util;

import uk.gov.dwp.maze.domain.Square;
import uk.gov.dwp.maze.domain.SquareState;

/**
 * Created by sabahirfan on 31/03/2017.
 */
public class MazeUtil {

    public static Square findSquareByState(Square[][] squares, final SquareState state) {

        for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares[0].length; j++) {
                Square s = squares[i][j];
                if (s.getState() == state)
                    return s;
            }
        }
        return null;
    }

    /**
     * To reset the exit square to any point chosen in the map. This is for testing purpose only.
     *
     * @param squares the maze map.
     * @param existingExitPoint the existing exit square in the maze map.
     * @param xOrdinal the new x ordinal for the new exit square
     * @param yOrdinal the new y ordinal for the new exit square.
     * @param state the state the previous exit point becomes to
     */
    public static void setExitSquare(Square[][] squares, Square existingExitPoint, int xOrdinal, int yOrdinal, SquareState state) {
        int yLength = squares[0].length - 1;
        if (xOrdinal < 0 || xOrdinal> squares.length - 1 || yOrdinal < 0 || yOrdinal > yLength) {
            return; // does nothing.
        }

        int x = existingExitPoint.getRow();
        int y = existingExitPoint.getColumn();

        //replacing existing square with a wall.
        squares[x][y] = new Square(x, y, state);

        //setting the new x and y the new exit point.
        squares[xOrdinal][yOrdinal] = new Square(xOrdinal,yOrdinal, 'F');
        System.out.println(squares);
    }

    public static void setSquare(Square[][] squares, int xOrdinal, int yOrdinal, SquareState state) {
        squares[xOrdinal][yOrdinal] = new Square(xOrdinal, yOrdinal, state);
    }
}
