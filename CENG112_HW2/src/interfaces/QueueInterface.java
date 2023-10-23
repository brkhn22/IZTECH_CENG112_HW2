package interfaces;

public interface QueueInterface<T> {

	/*
	 * Adds a new entry to the back of this queue.
	 * @param newEntry An object to be added.
	 */
	public void enqueue(T newEntry);
	
	/*
	 * Removes and returns the first entry of this queue.
	 * @return An object that appeared first in queue.
	 * @throws EmptyQueueException if the queue is empty before the operation.
	 */
	public T dequeue();
	
	/*
	 * Retrieves the first entry of this queue.
	 * @return An object that appeared first in queue.
	 * @throws EmptyQueueException if the queue is empty.
	 */
	public T getFront();
	
	/*
	 * Detects whether this queue is empty.
	 * @return True if queues is empty or False if not empty.
	 */
	public boolean isEmpty();
	
	/*
	 * Removes all entries from this queue.
	 */
	public void clear();
	
}
