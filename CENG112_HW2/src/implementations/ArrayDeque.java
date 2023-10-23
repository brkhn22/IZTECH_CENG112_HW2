package implementations;

import java.util.EmptyStackException;

import interfaces.DequeInterface;

public class ArrayDeque<T> implements DequeInterface<T> {
	// A Deque that behaves like queue + stack.
	// circular array with 1 unused location.
	private T[] deque;
	private int frontIndex;
	private int backIndex;
	private static final int DEFAULT_CAPACITY = 50;
	
	public ArrayDeque() {
		this(DEFAULT_CAPACITY);
	}
	
	@SuppressWarnings("unchecked")
	public ArrayDeque(int capacity) {
		deque = (T[])new Object[capacity+1]; // one unused location added
		frontIndex = 0;
		backIndex = capacity;
	}
	
	@Override
	public void addToFront(T newEntry) {
		ensureCapacity();
		if(frontIndex <= 0)
			frontIndex+=deque.length;
		frontIndex -= 1;
		deque[frontIndex] = newEntry;
	}

	@Override
	public void addToBack(T newEntry) {
		ensureCapacity();
		backIndex = (backIndex +1) % deque.length;
		deque[backIndex] = newEntry;
		
	}

	@Override
	public T removeFront() {
		if(isEmpty())
			throw new EmptyStackException();
		else {
			T frontEntry = deque[frontIndex];
			deque[frontIndex] = null;
			frontIndex = (frontIndex + 1) % deque.length;
			return frontEntry;
		}
	}

	@Override
	public T removeBack() {
		if(isEmpty())
			throw new EmptyStackException();
		else {
			T backEntry = deque[backIndex];
			deque[backIndex] = null;
			if(backIndex<=0)
				backIndex += deque.length;
			backIndex = (backIndex - 1) % deque.length;
			return backEntry;
		}
	}

	@Override
	public T getFront() {
		if(isEmpty()) throw new EmptyStackException();
		else return deque[frontIndex];
	}

	@Override
	public T getBack() {
		if(isEmpty()) throw new EmptyStackException();
		else return deque[backIndex];
	}

	@Override
	public boolean isEmpty() {
		return frontIndex == (backIndex +1) % deque.length;
	}

	@Override
	public void clear() {
		while(!isEmpty())
			removeFront();
	}
	
	private void ensureCapacity() {
		// 1 spot shall stay empty.
		if(frontIndex == (backIndex + 2) % deque.length)
		{
			T[] oldQueue = deque;
			int oldSize = deque.length;
			int newSize = oldSize*2;
			@SuppressWarnings("unchecked")
			T[] tempQueue = (T[]) new Object[newSize];
			deque = tempQueue;
			for(int i = 0; i < oldSize - 1; i++) {
				deque[i] = oldQueue[frontIndex];
				frontIndex = (frontIndex + 1) % oldSize;
 			}
			frontIndex = 0;
			backIndex = oldSize-2; 
		}
	}

}
