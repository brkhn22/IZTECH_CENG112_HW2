package implementations;

import java.util.EmptyStackException;

import interfaces.QueueInterface;

public class ArrayQueue<T> implements QueueInterface<T> {
	
	private T[] queue;
	private int frontIndex;
	private int backIndex;
	private boolean initialized = false;
	private static final int DEFAULT_CAPACITY = 50;
	private static final int MAX_CAPACITY = 10000;
	
	public ArrayQueue() {
		this(DEFAULT_CAPACITY);
	}
	
	public ArrayQueue(int initialCapacity) {
		checkCapacity(initialCapacity);
		@SuppressWarnings("unchecked")
		T[] tempQueue = (T[]) new Object[initialCapacity + 1];
		this.queue = tempQueue;
		initialized = true;
		frontIndex = 0;
		backIndex = initialCapacity;
	}
	
	@Override
	public void enqueue(T newEntry) {
		checkInitialization();
		ensureCapacity();
		backIndex = (backIndex + 1) % queue.length;
		queue[backIndex] = newEntry;
	}

	@Override
	public T dequeue() {
		checkInitialization();
		if (isEmpty())
			throw new EmptyStackException();
		else {
			T front = queue[frontIndex];
			queue[frontIndex] = null;
			frontIndex = (frontIndex + 1) % queue.length;
			return front;
		}
	}

	@Override
	public T getFront() {
		checkInitialization();
		if (isEmpty())
			throw new EmptyStackException();
		else
			return queue[frontIndex];
	}
	
	private void ensureCapacity() {
		if (frontIndex == ((backIndex + 2) % queue.length)) {
			T[] oldQueue = queue;
			int oldSize = oldQueue.length;
			int newSize = 2 * oldSize;
			checkCapacity(newSize);
			
			@SuppressWarnings("unchecked")
			T[] tempQueue = (T[]) new Object[newSize];
			queue = tempQueue;
			for (int index = 0; index < oldSize - 1; index++) {
				queue[index] = oldQueue[frontIndex];
				frontIndex = (frontIndex + 1) % oldSize;
			}
			frontIndex = 0;
			backIndex = oldSize - 2;
		}
	}
	
	private void checkCapacity(int capacity) {
        if (capacity > MAX_CAPACITY) {
            throw new IllegalStateException("would exceed list capacity");
        }
    }

	@Override
	public boolean isEmpty() {
		return frontIndex == ((backIndex + 1) % queue.length);
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}
	
	private void checkInitialization() {
		if (!initialized) 
			throw new SecurityException("The ArrayList is not Initiazed properly.");
	}

}
