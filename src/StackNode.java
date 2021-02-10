public class StackNode<Item>
{
    // The item of the node
    public Item item;

    // The pointer to the next node in the list representing the stack
    public StackNode<Item> next;

    /**
     * Constructor of the class.
     * @param item
     */
    public StackNode(Item item)
    {
        this.item = item;
        next = null;
    }
}
