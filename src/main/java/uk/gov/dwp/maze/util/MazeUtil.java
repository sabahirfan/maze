package uk.gov.dwp.maze.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.gov.dwp.maze.Explorer;
import uk.gov.dwp.maze.domain.Block;
import uk.gov.dwp.maze.domain.BlockState;

/**
 * Created by sabahirfan on 31/03/2017.
 */
public class MazeUtil {

    private static final Logger log = LoggerFactory.getLogger(MazeUtil.class);

    public static Block findBlockByState(Block[][] blocks, final BlockState state) {

        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks[0].length; j++) {
                Block s = blocks[i][j];
                if (s.getState() == state)
                    return s;
            }
        }
        return null;
    }

    /**
     * To reset the exit block to any point chosen in the map. This is for testing purpose only.
     *
     * @param blocks            the maze map.
     * @param existingExitPoint the existing exit block in the maze map.
     * @param xOrdinal          the new x ordinal for the new exit block
     * @param yOrdinal          the new y ordinal for the new exit block.
     * @param state             the state the previous exit point becomes to
     */
    public static void setExitBlock(Block[][] blocks, Block existingExitPoint, int xOrdinal, int yOrdinal, BlockState state) {
        int yLength = blocks[0].length - 1;
        if (xOrdinal < 0 || xOrdinal > blocks.length - 1 || yOrdinal < 0 || yOrdinal > yLength) {
            return; // does nothing.
        }

        int x = existingExitPoint.getRow();
        int y = existingExitPoint.getColumn();

        //replacing existing block with a wall.
        blocks[x][y] = new Block(x, y, state);

        //setting the new x and y the new exit point.
        blocks[xOrdinal][yOrdinal] = new Block(xOrdinal, yOrdinal, 'F');
        // log.info(blocks.toString());
    }

    public static void setBlock(Block[][] blocks, int xOrdinal, int yOrdinal, BlockState state) {
        blocks[xOrdinal][yOrdinal] = new Block(xOrdinal, yOrdinal, state);
    }
}
