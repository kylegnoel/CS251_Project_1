
public class Queue<Item>
{
    Item[] array;
    private int front;
    private int back;

    /**
     * Constructor of the class.
     * TO DO BY STUDENT
     */
    public Queue() {
        this.front = 0;
        this.back = 0;
        this.array = (Item[]) new Object[1];
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
        if (isEmpty())
            throw new EmptyQueueException();
        Item result = array[front];
        array[front] = null;
        if (front + 1 < array.length)
            front++;
        else if (front + 1 == back) {
            front = 0; back = 0;
        } else if (front + 1 >= array.length) {
            front = 0;
        }
        return result;
    }

    /**
     * Enqueues an item.
     * TO DO BY STUDENT
     */
    public void enqueue(Item item)  {
        // if the array is empty, then just put item in the 0 index
        if (front == back)
            array[front] = item;
        else
            // else put the item in the back of the array
            array[back] = item;

        // increment the back pointer if back + 1 is valid and empty
        if (back + 1 < array.length && array[back + 1] == null)
            back += 1;

        else if (back + 1 >= array.length && array[0] == null)
            back = 0;

        else if ((back + 1 >= array.length && front == 0) || (back < front && back + 1 == front)) {

            // resize the array to twice its size since the current one is full

            Item[] temp = getArray();
            array = (Item[]) new Object[temp.length * 2];

            int j = 0;
            if (front < back) {
                for (int i = front; i <= back; i++, j++) {
                    if (temp[i] != null)
                        array[j] = temp[i];
                }
            } else {
                for (int i = front; i < temp.length; i++, j++) {
                    array[j] = temp[i];
                }
                for (int i = 0; i < front; i++, j++) {
                    array[j] = temp[i];
                }
            }
            if (array.length != 0) {
                front = 0;
            }
            back = j;

        }

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
