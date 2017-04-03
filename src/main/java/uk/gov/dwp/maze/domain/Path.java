package uk.gov.dwp.maze.domain;

import java.util.Stack;

/**
 * Created by sabahirfan on 31/03/2017.
 */
public class Path {

    private final Stack<Square> paths;

    public Path() {
        paths = new Stack<Square>();
    }

    /**
     * Going backward explored when reach dead-end.
     */
    public Square getPreviousExploredSquare() {
        if (!paths.isEmpty())
            paths.pop();
        return paths.isEmpty() ? null : paths.pop();
    }

    public void addPath(Square square) {
        paths.push(square);
    }

    public boolean isOnPath(Square square) {
        return paths.contains(square);
    }

    public Stack<Square> getPaths() {
        return paths;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Square s : paths) {
            builder.append("X" + s.getRow() + ":Y" + s.getColumn());
        }
        return builder.toString();
    }
}