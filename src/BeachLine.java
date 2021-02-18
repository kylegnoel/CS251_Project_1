/**
 * The class representing a map.
 */
public class BeachLine extends Grid
{
    /**
     * Constructor of the class.
     */
    public BeachLine(String filepath)
    {
        super(filepath);
    }


    /**
     * Finds the beach line by starting the search at (i, j).
     * TO DO BY STUDENT
     */
    public Pair[] findBeachLine(int i, int j) throws EmptyQueueException
    {
        if (grid[i][j] == 1) {

        } else {
            Pair firstLand = firstLand(i, j);
            System.out.println(firstLand.a + " " + firstLand.b);
        }
        return null;
    }

    public void findLine(int i, int j, Queue line) {
        
    }

    public boolean isInland(int i, int j) {
        try  {
            return (grid[i-1][j] & grid[i-1][j+1] & grid[i][j+1] & grid[i+1][j+1] &
                    grid[i+1][j] & grid[i+1][j-1] & grid[i][j-1] & grid[i-1][j-1]) == 1;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    public Pair firstLand(int i, int j) throws EmptyQueueException {
        Queue line = new Queue();
        line.enqueue(new Pair(i, j));
        if (i - 1 >= 0) {
            if (grid[i - 1][j] == 1)
                return new Pair(i-1, j);
            line.enqueue(new Pair(i-1, j));
        }
        if (j + 1 < grid[i].length){
            if (grid[i][j+1] == 1)
                return new Pair(i, j+1);
            line.enqueue(new Pair(i, j + 1));
        }
        if (i + 1 < grid.length) {
            if (grid[i+1][j] == 1)
                return new Pair(i+1,j);
            line.enqueue(new Pair(i + 1, j));
        }
        if (j - 1 >= 0) {
            if (grid[i][j-1] == 1)
                return new Pair(i, j-1);
            line.enqueue(new Pair(i, j - 1));
        }
        grid[i][j] = -1;
        if (line.isEmpty())
            throw new EmptyQueueException();
        line.dequeue();
        while (grid[i][j] != 1) {
            if (line.isEmpty())
                throw new EmptyQueueException();
            Pair current = (Pair) line.dequeue();
            i = current.a;
            j = current.b;
            grid[i][j] = -1;
            if (i - 1 >= 0) {
                if (grid[i-1][j] == 0)
                    line.enqueue(new Pair(i-1, j));
                else if (grid[i-1][j] == 1)
                    return new Pair(i-1,j);
            }

            if (j + 1 < grid[i].length) {
                if (grid[i][j+1] == 0)
                    line.enqueue(new Pair(i, j + 1));
                else if (grid[i][j+1] == 1)
                    return new Pair(i,j+1);
            }

            if (i + 1 < grid.length) {
                if (grid[i + 1][j] == 0)
                    line.enqueue(new Pair(i+1, j));
                else if(grid[i + 1][j] == 1)
                    return new Pair(i+1, j);
            }

            if (j - 1 >= 0) {
                if (grid[i][j - 1] == 0)
                    line.enqueue(new Pair(i, j -1));
                else if (grid[i][j - 1] == 1)
                    return new Pair(i, j - 1);
            }
        }
        return null;
    }


    /**
     * Prints the content of the map.
     */
    public void printMap() {
        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nColumns; j++) {
                if (grid[i][j] == 0)
                    System.out.print("\u2591 ");
                else if (grid[i][j] == 1)
                    System.out.print("\u2593 ");
                else {
                    System.out.print(grid[i][j]);
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        try {

            BeachLine B = new BeachLine(args[0]);

            if (args.length < 3) {
                B.printMap();
                return;
            }

            int a = Integer.parseInt(args[1]);
            int b = Integer.parseInt(args[2]);
            Pair[] line = B.findBeachLine(a, b);
            System.out.println(line.length);
            for (int i = 0; i < line.length; i++) {
                System.out.println(line[i].a + " " + line[i].b);
            }

        } catch (EmptyQueueException e) {
            System.out.println("Error in Queue implementation");
        }
    }
}
