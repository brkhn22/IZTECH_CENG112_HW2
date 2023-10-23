package interfaces;

public interface ListInterface<T> {
	/* Adds a new entry to the end of this list.
	 * Entries currently in the list are unaffected.
	 * The list's size is increased by 1.
	 * @param newEntry An object to be added as a new entry.
	 */
	public void add(T newEntry);
	
	/* Adds a new entry at the given position in list.
	 * Entries originally at and above the specified position
	 * are at the next higher position within the list.
	 * The list's size is increased by 1.
	 * @param givenPosition the position that object would be located into this list.
	 * @param newEntry An object to be added as a new entry.
	 * @throws IndexOutOfBoundException if either givenPosition < 1 or newPosition > getLength(). 
	 */
	public void add(int givenPosition, T newEntry);
	
	/* Removes the entry at the given position of this list.
	 * Entries originally at positions higher than the given
	 * position are at the next lower position within the list,
	 * and the list's size is increased by 1.
	 * @param givenPosition the position that object would be removed from this list.
	 * @throws IndexOutOfBoundException if either givenPosition < 1 or newPosition > getLength(). 
	 */
	public T remove(int givenPosition);
	
	/*
	 * Replaces the entry at the givenPosition with newEntry.
	 * @param givenPosition the position newEntry will be located.
	 * @param newEntry an Object that would take place of the givenPosition.
	 * @return an Entry that was at the given position before the operations.
	 * IndexOutOfBoundException if either givenPosition < 1 or newPosition > getLength().
	 */
	public T replace(int givenPosition, T newEntry);
	
	/*
	 * Removes all entries from this list.
	 */
	public void clear();
	
	/* Retrieves the entry at the given position of this list.
	 * Entries are not affected by this operation.
	 * @param givenPosition the position that object would be removed from this list.
	 * @throws IndexOutOfBoundException if either givenPosition < 1 or newPosition > getLength(). 
	 */
	public T getEntry(int givenPosition);
	
	/*
	 * Retrieves all entries that are in the list in order in which
	 * they occur in the list.
	 * @return A newly located array of all the entries in this list.
	 * If the list is empty, the returned array is empty.
	 */
	public T[] toArray();
	
	/*
	 * Sees whether this list contains given entry or not.
	 * @param entry An object that is the desired entry.
	 * @return True if given entry is in the list or False if not.
	 */
	public boolean contains(T entry);
	
	/*
	 * Gets the length of this list.
	 * @return Integer value of the number of entries in the list.
	 */
	public int getLength();
	
	/*
	 * Sees whether this list is empty.
	 * @return True if list contains any objects or False if not.
	 */
	public boolean isEmpty();
}
