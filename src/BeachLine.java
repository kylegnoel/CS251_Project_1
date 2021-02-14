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
                else
                    System.out.print("\u2593 ");
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
