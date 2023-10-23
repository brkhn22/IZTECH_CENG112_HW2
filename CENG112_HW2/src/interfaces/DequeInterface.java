package interfaces;

public interface DequeInterface<T> {
	/*
	 Adds a new entry to the front/back of this deque.
	 @param newEntry An object to be added.
	*/
	public void addToFront(T newEntry);
	public void addToBack(T newEntry);
	
	/*
	 Removes and returns the front/back entry of this deque.
	 @return An object at the front/back of this deque.
	 @throws EmptyQueueException if the deque is empty before the operations.
	*/
	public T removeFront();
	public T removeBack();
	
	/*
	 Retrieves the front/back entry of this deque.
	 @return An object at the front/back of this deque.
	 @throws EmptyQueueException if the deque is empty before the operations.
	 */
	public T getFront();
	public T getBack();
	
	/*
	 Checks whether this deque is empty or not.
	 @return True if deque is empty, False if deque has any objects.
	 */
	public boolean isEmpty();

	/*
	 Removes all entries from this deque.
	 */
	public void clear();
}
