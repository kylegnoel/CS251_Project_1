public class Stack<Item>
{
    // The pointer to the first node of the stack
    private StackNode<Item> first;

    /**
     * Constructor of the class.
     * TO DO BY STUDENT
     */
    public Stack(StackNode<Item> first) {
        this.first = first;
    }

    /**
     * Indicates whether the stack is empty or not.
     * TO DO BY STUDENT
     */
    public boolean isEmpty() {
        return (first.item == null);
    }

    /**
     * Peeks the item at the top of the stack.
     * TO DO BY STUDENT
     */
    public Item peek() throws EmptyStackException {
        if (isEmpty())
            throw new EmptyStackException();
        if (size() == 1)
            return first.item;
        StackNode<Item> temp = first;
        while (temp.next != null) {
            temp = temp.next;
        }
        return temp.item;
    }

    /**
     * Pops the item at the top of the stack.
     * TO DO BY STUDENT
     */
    public Item pop() throws EmptyStackException {
        if (isEmpty())
            throw new EmptyStackException();
        if (size() == 1) {
            return first.item;
        }
        StackNode<Item> temp = first;
        while (temp.next.next != null) {
            temp = temp.next;
        }
        Item result = temp.next.item;
        temp.next = null;
        return result;
    }

    /**
     * Pushes an item into the stack.
     * TO DO BY STUDENT
     */
    public void push(Item item) {
        StackNode<Item> node = new StackNode<>(item);
        if (isEmpty()) {
            first = node;
            return;
        }

        StackNode<Item> temp = first;
        while(temp.next != null) {
            temp = temp.next;
        }
        temp.next = node;
    }

    /**
     * Returns the number of items in the stack.
     * TO DO BY STUDENT
     */
    public int size() {
        if (isEmpty())
            return 0;
        StackNode<Item> temp = first;
        int count = 0;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        return count;
    }

}
