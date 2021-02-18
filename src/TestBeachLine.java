import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TestBeachLine
{
    public static double test1() {
        return runTest("map_input_10_10.txt", "part2_test1_soln.txt", 0, 0);
    }

    public static double test2() {
        return runTest("map_input_10_10.txt", "part2_test2_soln.txt", 5, 6);
    }

    public static double test3() {
        return runTest("map_input_8_8.txt", "part2_test3_soln.txt", 7, 7);
    }

    public static double test4() {
        return runTest("map_input_12_12.txt", "part2_test4_soln.txt", 4, 7);
    }

    public static double test5() {
        return runTest("map_input_64_64.txt", "part2_test5_soln.txt", 51, 45);
    }


    public static double runTest(String mapFile, String solnFile, int i, int j) {
        BeachLine B = new BeachLine(mapFile);

        // 1) Run student's solution function
        Pair[] line = null;
        try {
            line = B.findBeachLine(i, j);
        } catch (EmptyQueueException e) {
            System.out.println("Caught EmptyQueueException");
            return 0.0;
        }

        // 2) Read solution file
        Pair[] solution = readSolution(solnFile);
        if (solution == null) {
            System.out.println("Error reading solution file");
            return 0.0;
        }

        // 3) Compare student's solution with ours
        if (isSamePathOrdered(line, solution)) {
            return 1.0;
        } else if (isSamePathUnordered(line, solution)) {
            return 0.5;
        } else
            return 0.0;
    }


    public static Pair[] readSolution(String filepath) {
        assert filepath != null && !filepath.equals("") : "Wrong filepath";

        Pair[] pairs = null;

        try {
            // Open the file
            BufferedReader reader = new BufferedReader(new FileReader(filepath));

            // Read the first line, it contains the number pairs in the beach line
            String line = reader.readLine();
            int nPairs = Integer.parseInt(line);
            assert nPairs > 0 : "Wrong number of pairs";

            // Make new pairs array
            pairs = new Pair[nPairs];
            int i = 0;

            // Continue reading file and populate pairs list
            while ((line = reader.readLine()) != null) {
                assert i < nPairs : "";

                String[] values = line.split(" ");
                assert values.length == 2 : "Wrong number of values for pair";

                int a = Integer.parseInt(values[0]);
                int b = Integer.parseInt(values[1]);
                pairs[i] = new Pair(a, b);

                i++;
            }

            // Close the file
            reader.close();
        } catch(FileNotFoundException e) {
            System.err.println(e.getStackTrace());
        } catch(IOException e) {
            System.err.println(e.getStackTrace());
        }

        return pairs;
    }

    // Tests if two beach lines have the same path and order
    public static boolean isSamePathOrdered(Pair[] a, Pair[] b) {
        // Must be the same length
        if (a.length != b.length)
            return false;

        // Test if each pair is equal
        for (int i = 0; i < a.length; i++) {
            if (!a[i].equals(b[i]))
                return false;
        }

        return true;
    }

    // Tests if two beach lines have the same path, but different orders
    public static boolean isSamePathUnordered(Pair[] a, Pair[] b) {
        // Must be the same length
        if (a.length != b.length)
            return false;

        int i = 0;
        int j = 0;
        // Cycle a until we find pair matching start of b
        while (i < a.length && !a[i].equals(b[j]))
            i++;
        // No match found
        if (i >= a.length)
            return false;
        // Compare each pair in both arrays
        while (j < b.length && a[i].equals(b[j])) {
            i = (i + 1) % a.length;
            j++;
        }
        // Return whether they all match (got to the end of b)
        return j == b.length;
    }


    /**
     *
     * @param args
     */
    public static void main(String[] args)
    {
        double total = 0.0;

        // Test 1
        double score = TestBeachLine.test1();
//        double score = 0;
        total += score;
        System.out.println("Test 1 Score: " + score);

        // Test 2
        score = TestBeachLine.test2();
        total += score;
        System.out.println("Test 2 Score: " + score);

        // Test 3
        score = TestBeachLine.test3();
        total += score;
        System.out.println("Test 3 Score: " + score);

        // Test 4
        score = TestBeachLine.test4();
        total += score;
        System.out.println("Test 4 Score: " + score);

        // Test 5
        score = TestBeachLine.test5();
        total += score;
        System.out.println("Test 5 Score: " + score);

        System.out.println("Part 2 Total Score: " + total);
    }

}
