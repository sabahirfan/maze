package uk.gov.dwp.maze.domain;

import java.util.Stack;

/**
 * This class stacks the path explored from Start to Finish.
 * <p>
 * Created by sabahirfan on 31/03/2017.
 */
public class Path {

    private final Stack<Block> paths;

    public Path() {
        paths = new Stack<Block>();
    }


    /**
     * Going backward explored when reach dead-end, goes one step back.
     *
     * @return the previously visited block {@link Block}
     */
    public Block getPreviousExploredBlock() {
        if (!paths.isEmpty())
            paths.pop();
        return paths.isEmpty() ? null : paths.pop();
    }

    /**
     * Add Block to path stack
     *
     * @param block {@link Block}
     */
    public void addPath(Block block) {
        paths.push(block);
    }

    /**
     * Check the passed Block is on the path
     *
     * @param block {@link Block}
     * @return true if passed Block in on path, false otherwise
     */
    public boolean isOnPath(Block block) {
        return paths.contains(block);
    }

    /**
     * Returns the path stack populated
     *
     * @return the {@link java.util.Stack<Block>} populated.
     */
    public Stack<Block> getPaths() {
        return paths;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Total Steps: " + paths.size() + " Details: ");
        for (Block s : paths) {
            builder.append("(X:" + s.getRow() + ", Y" + s.getColumn() + ") ");
        }
        return builder.toString();
    }
}