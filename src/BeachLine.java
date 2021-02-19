
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
        if (grid[i][j] != 1) {
            Pair firstLand = firstLand(i, j);
            i = firstLand.a;
            j = firstLand.b;
        }

        Queue line = new Queue();
        findLine(i, j, line);
        Pair[] ans = new Pair[line.size()];
        int z = 0;
        while (!line.isEmpty()) {
            ans[z] = (Pair) line.dequeue();
            z++;
        }
        return ans;
    }

    public void findLine(int i, int j, Queue line) {
        while (isInLand(i,j))
            j++;
        Pair firstShore = firstShore(i,j);
        if (firstShore == null)
            return;
        line.enqueue(firstShore);
        i = firstShore.a;
        j = firstShore.b;
        grid[i][j] = -2;

        boolean isDone = false;

        while (!isDone) {
            // GO RIGHT
//            printMap();
//            System.out.println();
            // if can go down and right, go down
            if (isShore(i+1, j) && grid[i + 1][j] != -2 && isShore(i, j + 1) && grid[i][j+1] != -2) {
                i++;
                line.enqueue(new Pair(i, j));
                grid[i][j] = -2;
                continue;
            }
            if (isShore(i, j + 1) && grid[i][j + 1] != -2) {
                j++;
                line.enqueue(new Pair(i, j));
                grid[i][j] = -2;
                continue;
            }
            // If can go down and left, go left
            if (isShore(i+1, j) && grid[i + 1][j] != -2 && isShore(i, j - 1) && grid[i][j-1] != -2) {
                j--;
                line.enqueue(new Pair(i,j));
                grid[i][j] = -2;
                continue;
            }
            // GO DOWN
            if (isShore(i + 1, j) && grid[i + 1][j] != -2) {
                i++;
                line.enqueue(new Pair(i, j));
                grid[i][j] = -2;
                continue;
            }


            if (isShore(i-1, j) && grid[i - 1][j] != -2) {
                i--;
                line.enqueue(new Pair(i, j));
                grid[i][j] = -2;
                continue;
            }
            if (isShore(i, j-1) && grid[i][j - 1] != -2) {
                j--;
                line.enqueue(new Pair(i, j));
                grid[i][j] = -2;
                continue;
            }
            isDone = true;
        }


    }

    public Pair firstShore(int i, int j) {
        if (grid[i][j] != 1)
            return null;
        if (grid[i-1][j] == 0 || grid[i-1][j] == -1)
            return new Pair(i-1, j);
        if (grid[i][j+1] == 0 || grid[i][j+1] == -1)
            return new Pair(i, j+1);
        if (grid[i+1][j] == 0 || grid[i+1][j] == -1)
            return new Pair(i+1,j);
        if (grid[i][j-1] == 0 || grid[i][j-1] == -1)
            return new Pair(i, j-1);
        return null;
    }

    public boolean isInLand(int i, int j) {
        if (i + 1 >= grid.length || i - 1 < 0 || j + 1 >= grid[i].length || j - 1 < 0)
            return false;
        return (grid[i-1][j] & grid[i-1][j+1] & grid[i][j+1] & grid[i+1][j+1] &
                grid[i+1][j] & grid[i+1][j-1] & grid[i][j-1] & grid[i-1][j-1]) == 1;
    }

    public boolean isShore(int i, int j) {
        try {
            if (i - 1 >= 0 && grid[i-1][j] == 1 && grid[i][j] != 1)
                return true;
            if (i - 1 >= 0 && j + 1 < grid[i-1].length && grid[i-1][j+1] == 1 && grid[i][j] != 1)
                return true;
            if (j + 1 < grid[i].length && grid[i][j+1] == 1 && grid[i][j] != 1)
                return true;
            if (i + 1 < grid.length && j + 1 < grid[i+1].length && grid[i+1][j+1] == 1 && grid[i][j] != 1)
                return true;
            if (i + 1 < grid.length && grid[i+1][j] == 1 && grid[i][j] != 1)
                return true;
            if (i + 1 < grid.length && j - 1 >= 0 && grid[i+1][j-1] == 1 && grid[i][j] != 1)
                return true;
            if (j - 1 >= 0 && grid[i][j-1] == 1 && grid[i][j] != 1)
                return true;
            if (i - 1 >= 0 && j - 1 >= 0 && grid[i-1][j-1] == 1 && grid[i][j] != 1)
                return true;
        } catch (IndexOutOfBoundsException ignored) {

        }
        return false;
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
//            printMap();
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
