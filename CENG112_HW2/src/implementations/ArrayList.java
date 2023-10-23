package implementations;

import java.util.Arrays;

import interfaces.ListInterface;

public class ArrayList<T> implements ListInterface<T> {
	
	private T[] list;
	private int numberOfEntries;
	private boolean initialized = false;
	private static final int DEFAULT_CAPACITY = 25;
	private static final int MAX_CAPACITY = 10000;
	
	public ArrayList() {
		this(DEFAULT_CAPACITY);
	}
	
	public ArrayList(int initialCapacity) {
		if (initialCapacity < DEFAULT_CAPACITY) 
			initialCapacity = DEFAULT_CAPACITY;
		else
			checkCapacity(initialCapacity);
		
		@SuppressWarnings("unchecked")
		T[] templist = (T[]) new Object[initialCapacity + 1];
		list = templist;
		numberOfEntries = 0;
		initialized = true;
	}

	@Override
	public void add(T newEntry) {
		checkInitialization();
		list[numberOfEntries + 1] = newEntry;
		numberOfEntries++;
		ensureCapacity();
	}
	
	private void ensureCapacity() {
		int capacity = list.length - 1;
		if (numberOfEntries >= capacity) {
			int newCapacity = 2 * capacity;
			checkCapacity(newCapacity);
			list = Arrays.copyOf(list, newCapacity + 1);
		}
	}
	
	private void checkCapacity(int capacity) {
        if (capacity > MAX_CAPACITY) {
            throw new IllegalStateException("would exceed list capacity");
        }
    }

	@Override
	public void add(int givenPosition, T newEntry) {
		checkInitialization();
		if ((givenPosition >= 1) && (givenPosition <= numberOfEntries + 1)) {
			if (givenPosition <= numberOfEntries)
				makeRoom(givenPosition);
			list[givenPosition] = newEntry;
			numberOfEntries++;
			ensureCapacity();
		}
		else
			throw new IndexOutOfBoundsException("Given position of add's new entry is out of bonds.");
	}
	
	private void makeRoom(int givenPosition) {
		assert (givenPosition >= 1) && (givenPosition <= numberOfEntries + 1);
		int newIndex = givenPosition;
		int lastIndex = numberOfEntries;
		for (int index = lastIndex; index >= newIndex; index--)
			list[index + 1] = list[index];
	}

	@Override
	public T remove(int givenPosition) {
		checkInitialization();
		if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
			assert !isEmpty();
			T result = list[givenPosition];
			if (givenPosition < numberOfEntries)
				removeGap(givenPosition);
			numberOfEntries--;
			return result;
		}
		else
			throw new IndexOutOfBoundsException("Illegal position given to remove operation.");
	}
	
	private void removeGap(int givenPosition) {
		assert (givenPosition >= 1) && (givenPosition < numberOfEntries);
		int removedIndex = givenPosition;
		int lastIndex = numberOfEntries;
		for (int index = removedIndex; index < lastIndex; index++)
			list[index] = list[index + 1];
		list[lastIndex] = null;
	}

	@Override
	public T replace(int givenPosition, T newEntry) {
		checkInitialization();
		if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
			assert !isEmpty();
			T originalEntry = list[givenPosition];
			list[givenPosition] = newEntry;
			return originalEntry;
		}
		else
			throw new IndexOutOfBoundsException("Illegal position given to replace operation.");
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public T getEntry(int givenPosition) {
		checkInitialization();
		if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
			assert !isEmpty();
			return list[givenPosition];
		}
		else
			throw new IndexOutOfBoundsException("Illegal position given to getEntry operation.");
	}

	@Override
	public T[] toArray() {
		checkInitialization();
		
		@SuppressWarnings("unchecked")
		T[] result = (T[]) new Object[numberOfEntries];
		for (int index = 0; index < numberOfEntries; index++) {
			result[index] = list[index + 1];
		}
		return result;
	}

	@Override
	public boolean contains(T anEntry) {
		checkInitialization();
		boolean found = false;
		int index = 1;
		while (!found && (index <= numberOfEntries)) {
			if (anEntry.equals(list[index]))
				found = true;
			index++;
		}
		return found;
	}

	@Override
	public int getLength() {
		return numberOfEntries;
	}

	@Override
	public boolean isEmpty() {
		return numberOfEntries == 0;
	}
	
	private void checkInitialization() {
		if (!initialized) 
			throw new SecurityException("The ArrayList is not Initiazed properly.");
	}

}
