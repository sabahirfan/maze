package uk.gov.dwp.maze.domain;

/**
 * This class represent the unit of a two dimensional Maze.
 * It holds row and column co-ordinates and the current state of the unit/block.
 * <p>
 * <p>
 * Created by sabahirfan on 31/03/2017.
 */
public class Block {

    private final int row;
    private final int column;
    private final BlockState state;

    public Block(int row, int column, char sign) {
        this.row = row;
        this.column = column;
        this.state = BlockState.getBlockState(sign);
    }

    public Block(int row, int column, BlockState state) {
        this.row = row;
        this.column = column;
        this.state = state;
    }

    public boolean isOpen() {
        return state == BlockState.OPEN ||
                state == BlockState.START ||
                state == BlockState.FINISH;
    }

    public boolean isStart() {
        return state == BlockState.START;
    }

    public boolean isExit() {
        return state == BlockState.FINISH;
    }

    public boolean isWalled() {
        return state == BlockState.WALLED;
    }

    /**
     * Whether the block on the board is represented using valid character.
     *
     * @return true or false
     */
    public boolean isValidRepresentation() {
        return isOpen() || isWalled();
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public BlockState getState() {
        return state;
    }

    public String toString() {
        return "(" + row + ", " + column + ", " + state + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Block block = (Block) o;

        if (column != block.column) return false;
        if (row != block.row) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = row;
        result = 31 * result + column;
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }
}