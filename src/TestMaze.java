import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TestMaze
{
	public static double test1() {
		return runTest("maze_input_5_5.txt", "part1_test1_soln.txt", 0, 0, 4, 4);
	}
	public static double test2() {
		return runTest("maze_input_5_5.txt", "part1_test2_soln.txt", 0, 4, 4, 0);
	}

	public static double test3() {
		return runTest("maze_input_15_20.txt", "part1_test3_soln.txt", 7, 8, 1, 12);
	}


	public static double runTest(String mazeFile, String solnFile, int i1, int j1, int i2, int j2) {
		Maze M = new Maze(mazeFile);

		// Run student's solution
		Pair[] studentAnswer = null;
		try {
			studentAnswer = M.findPath(i1, j1, i2, j2);
		} catch (EmptyStackException e) {
			System.out.println("Caught EmptyStackException");
			return 0.0;
		}

		// Read solution file
		Pair[] TASolution = readSolution(solnFile);

		// Compare solutions
		if (compareSolutions(studentAnswer, TASolution))
			return 1.0;
		else
			return 0.0;
	}

	public static Pair[] readSolution(String filepath) {
		assert filepath != null && !filepath.equals("") : "Wrong filepath";

		Pair[] pairs = null;

		try {
			// Open the file
			BufferedReader reader = new BufferedReader(new FileReader(filepath));

			// Read the first line
			String line = reader.readLine();

			// Check if no path
			if (line.equals("no path"))
				return null;

			// If no path, it contains the number pairs in the path
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

	public static boolean compareSolutions(Pair[] studentAnswer, Pair[] TASolution){
		if(studentAnswer==null && TASolution==null) return true;
		else if(studentAnswer==null && TASolution!=null) return false;
		else if(studentAnswer!=null && TASolution==null) return false;
		else {
			if (studentAnswer.length != TASolution.length) return false;
			for (int i = 0; i < TASolution.length; i++) {
				if (studentAnswer[i].a != TASolution[i].a) return false;
				if (studentAnswer[i].b != TASolution[i].b) return false;
			}
		}
		return true;
	}

	public static void main(String[] args) throws EmptyStackException {
		double total = 0.0;
		String testFileName="";


//		// Test 1
//		double score;
		double score = TestMaze.test1();
		total += score;
		System.out.println("Test 1 Score: " + score);

		// Test 2
		score = TestMaze.test2();
		total += score;
		System.out.println("Test 2 Score: " + score);
		
		// Test 3
		score = TestMaze.test3();
		total += score;
		System.out.println("Test 3 Score: " + score);


		System.out.println("Part 1 Total Score: " + total);
	}
}
