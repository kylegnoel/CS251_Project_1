public class Queue<Item>
{
    Item[] array;
    int front;
    int back;

    /**
     * Constructor of the class.
     * TO DO BY STUDENT
     */
    public Queue() {
        this.front = 0;
        this.back = 0;
    }

    /**
     * Constructor of the class with initial capacity.
     * TO DO BY STUDENT
     */
    public Queue(int n) {
        this.array = (Item[]) new Object[n];
        this.front = 0;
        this.back = 0;
    }

    /**
     * Dequeue the next item.
     * TO DO BY STUDENT
     */
    public Item dequeue() throws EmptyQueueException {
        return null;
    }

    /**
     * Enqueues an item.
     * TO DO BY STUDENT
     */
    public void enqueue(Item item) {
        // if the array is empty, then just put item in the 0 index
        if (array[front] == null)
            array[front] = item;

        // else put the item in the back of the array
        array[back] = item;

        // increment the back pointer if back + 1 is valid and empty
        if (back + 1 < array.length && array[back + 1] == null)
            back += 1;

        if (back + 1 >= array.length && array[0] == null)
            back = 0;

        if (back + 1 >= array.length && array[0] != null)
            // resize the array
            back = back;

    }

    /**
     * Returns the array with the content of the queue.
     * DO NOT DELETE OR MODIFY. REQUIRED FOR TESTING AND GRADING. ANY MODIFICATION OF THIS FUNCTION
     * WILL BE CONSIDERED ACADEMIC DISHONESTY AND PENALIZED WITH 0 FOR THE ENTIRE PROJECT.
     */
    public Item[] getArray()
    {
        return array;
    }

    /**
     * Indicates whether the queue is empty or not.
     * TO DO BY STUDENT
     */
    public boolean isEmpty() {
        if (array == null)
            return true;
        for (Item i : array) {
            if (i != null)
                return false;
        }
        return true;
    }

    /**
     * Peeks the next item to be dequeued.
     * TO DO BY STUDENT
     */
    public Item peek() throws EmptyQueueException {
        return null;
    }

    /**
     * Returns the size of the queue.
     * TO DO BY STUDENT
     */
    public int size() {
        if (isEmpty())
            return 0;
        int count = 0;
        for (Item i : array) {
            if (i != null)
                count++;
        }
        return count;
    }

}
