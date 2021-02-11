public enum Direction
{
    // Define the four directions in a grid
    NORTH(1, -1, 0), SOUTH(2, 1, 0), EAST(4, 0, 1), WEST(8, 0, -1);

    // Define the opposite directions
    static
    {
        NORTH.opposite = SOUTH;
        SOUTH.opposite = NORTH;
        EAST.opposite = WEST;
        WEST.opposite = EAST;
    }

    // The bit representation of the direction
    private final int bit;

    // The displacement along the rows
    private final int di;

    // The displacement along the columns
    private final int dj;

    // The opposite of the direction
    private Direction opposite;

    /**
     * Constructor of the enum.
     * @param bit
     * @param di
     * @param dj
     */
    private Direction(int bit, int di, int dj)
    {
        this.bit = bit;
        this.di = di;
        this.dj = dj;
    }

    /**
     * Returns the bit representation of the direction.
     * @return
     */
    public int getBit()
    {
        return bit;
    }

    /**
     * Returns the displacement value along the rows of the grid.
     * @return
     */
    public int getDi()
    {
        return di;
    }

    /**
     * Returns the displacement value along the columns of the grid.
     * @return
     */
    public int getDj()
    {
        return dj;
    }

    /**
     * Returns the opposite direction.
     * @return
     */
    public Direction getOpposite()
    {
        return opposite;
    }
}
