/**
 * The class representing a maze.
 */
public class Maze extends Grid{
    private Stack path;
    /**
     * Constructor of the class.
     */
    public Maze(String filepath) {
        super(filepath);
        this.path = new Stack();
    }

    /**
     * Finds a path in the maze from (i1, j1) to (i2, j2). If no path then return null.
     * TO DO BY STUDENT
     */
    public Pair[] findPath(int i1, int j1, int i2, int j2) throws EmptyStackException {
        Pair start = new Pair(i1, j1);
        int open = grid[i1][j1];
        path.push(start);
        grid[i1][j1] = -1;
        while (!path.isEmpty()) {
            if (i1 == i2 && j1 == j2) {
                Pair[] result = new Pair[path.size()];
                for (int i = path.size() - 1; i >= 0; i--) {
                    result[i] = (Pair) path.pop();
                }
                return result;
            }
            Pair next;
            try {
                if ((open & Direction.EAST.getBit()) != 0 && grid[i1][j1+1] != -1) {
                    j1 += 1;
                } else if ((open & Direction.SOUTH.getBit()) != 0 && grid[i1+1][j1] != -1) {
                    i1 += 1;
                } else if ((open & Direction.WEST.getBit()) != 0 && grid[i1][j1-1] != -1) {
                    j1 -= 1;
                } else if ((open & Direction.NORTH.getBit()) != 0 && grid[i1-1][j1] != -1) {
                    i1 -= 1;
                } else {
                    path.pop();
                    next = (Pair) path.peek();
                    i1 = next.a;
                    j1 = next.b;
                    continue;
                }
                next = new Pair(i1, j1);
                path.push(next);
                grid[i1][j1] = -1;
            } catch (IndexOutOfBoundsException ignored) {

            }

        }
        return null;
    }


    /**
     * Prints the contents of the maze.
     */
    public void printMaze()
    {
        for (int i = 0; i < nRows; i += 1)
        {
            for (int j = 0; j < nColumns; j += 1)
            {
                if ((grid[i][j] & Direction.NORTH.getBit()) == 0)
                {
                    System.out.print("+---");
                }
                else
                {
                    System.out.print("+   ");
                }
            }

            System.out.println("+");

            for (int j = 0; j < nColumns; j += 1)
            {
                if ((grid[i][j] & Direction.WEST.getBit()) == 0)
                {
                    System.out.print("|   ");
                }
                else
                {
                    System.out.print("    ");
                }
            }

            System.out.println("|");
        }

        for (int j = 0; j < nColumns; j += 1)
        {
            System.out.print("+---");
        }

        System.out.println("+");
    }

    public static void main(String[] args) {
        try {

            Maze m = new Maze(args[0]);

            if (args.length < 5) {
                m.printMaze();
                return;
            }

            int i1 = Integer.parseInt(args[1]);
            int j1 = Integer.parseInt(args[2]);
            int i2 = Integer.parseInt(args[3]);
            int j2 = Integer.parseInt(args[4]);

            Pair[] path = m.findPath(i1, j1, i2, j2);
            if (path == null) {
                System.out.println("no path");
                return;
            }

            System.out.println(path.length);
            for (int i = 0; i < path.length; i++) {
                System.out.println(path[i].a + " " + path[i].b);
            }

        } catch (EmptyStackException e) {
            System.out.println("Error in Queue implementation");
        }
    }
}
