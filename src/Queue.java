/**
 * Defines the interface for a Queue.
 *
 * @author richard.stansbury
 */

public interface Queue<t> {


    /**
     * Adss a new value to the back of the queue.
     * @param value - value to be added.
     */
    public void enqueue(t value);


    /**
     * Returns the value from the front of queue and removes it from the queue.
     * @return value removed from front of queue.
     */
    public t dequeue();


    /**
     * Returns the value of the front element of the queue.
     * @return value of the front element of the queue.
     */
    public t front();


    /**
     * @return true if empty.  False otherwise.
     */
    public boolean isEmpty();

}
