import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Grid
{
    // The grid
    protected int[][] grid;

    // The number of rows of the grid
    protected int nRows;

    // The number of columns of the grid
    protected int nColumns;

    /**
     * Constructor of the class.
     * @param filepath The path to the file with the content of the grid.
     */
    public Grid(String filepath) {
        loadFile(filepath);
    }

    /**
     * Constructor of the class.
     * @param rows The number of rows of the grid.
     * @param columns The number of columns of the grid.
     */
    public Grid(int rows, int columns)
    {
        assert rows > 0 : "Wrong number of rows";
        assert columns > 0 : "Wrong number of columns";

        nRows = rows;
        nColumns = columns;

        grid = new int[nRows][nColumns];
    }

    /**
     * Returns the number of columns of the grid.
     */
    public int getNumColumns()
    {
        return nColumns;
    }

    /**
     * Returns the number of rows of the grid.
     */
    public int getNumRows()
    {
        return nRows;
    }

    /**
     * Reads the file and populates the grid.
     */
    private void loadFile(String filepath)
    {
        assert filepath != null && !filepath.equals("") : "Wrong filepath";

        try
        {
            // Open the file
            BufferedReader reader = new BufferedReader(new FileReader(filepath));

            // Read the first line, it contains the number of rows and columns of the grid
            String line = reader.readLine();

            // Split the string, store the number of rows and columns, and initialize the grid
            String[] values = line.split(" ");
            assert values.length == 2 : "Wrong first line in input file";

            nRows = Integer.parseInt(values[0]);
            nColumns = Integer.parseInt(values[1]);
            assert nRows > 0 : "Wrong number of rows for the grid";
            assert nColumns > 0 : "Wrong number of columns for the grid";

            grid = new int[nRows][nColumns];

            int i = 0;

            // Continue reading the file and populate the grid
            while ((line = reader.readLine()) != null)
            {
                assert i < nRows : "";

                values = line.split(" ");
                assert values.length == nColumns : "Wrong number of columns in line";

                for (int j = 0; j < nColumns; j += 1)
                {
                    grid[i][j] = Integer.parseInt(values[j]);
                    assert grid[i][j] >= 0 : "Wrong grid cell value";
                }

                i += 1;
            }

            // Close the file
            reader.close();
        }
        catch(FileNotFoundException e)
        {
            System.err.println(e.getStackTrace());
        }
        catch(IOException e)
        {
            System.err.println(e.getStackTrace());
        }
    }

    /**
     * Prints the content of the grid.
     */
    public void print() {
        System.out.println(nRows + " " + nColumns);
        for (int i = 0; i < nRows; i += 1) {
            System.out.print(grid[i][0]);

            for (int j = 1; j < nColumns; j += 1) {
                System.out.print(" " + grid[i][j]);
            }

            System.out.println();
        }
    }
}