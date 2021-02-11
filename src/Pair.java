public class Pair
{
    // The first value of the pair.
    public int a;

    // The second value of the pair.
    public int b;

    /**
     * Constructor of the class.
     */
    public Pair(int a, int b)
    {
        this.a = a;
        this.b = b;
    }

    public boolean equals(Pair o) {
        return a == o.a && b == o.b;
    }

    public String toString() {
        return "( " + a + ", " + b + " )";
    }
}
