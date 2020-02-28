package chess.datastructures;

import java.util.Iterator;


public class ChessList<E> implements Iterable<E>{
	
	private E[] list;
	private int SIZE = 30;
	private int pointer = 0;
	
	public ChessList() {
		list = (E[])new Object[SIZE];
	}
	
	public void add(E e) {
		list[pointer] = e;
		pointer ++;
		if(pointer == SIZE){
			growSize();
		}
	}
	
	public E get(int index) {
		return list[index];
	}
	
	public int size() {
		return this.pointer;
	}
	private void growSize() {
		int NEWSIZE = SIZE*2;
		E[] newList = (E[]) new Object[NEWSIZE];
		for(int i = 0; i< SIZE; i++) {
			newList[i] = list[i];	
		}
		list = newList;
		SIZE = NEWSIZE;
	}
	
	public Iterator<E> iterator(){
		return new ChessIterator();
	}
	
	class ChessIterator implements Iterator<E>{
		private int index = 0;
		
		public boolean hasNext() {
			return index < size();
		}
		
		public E next() {
			return (E) get(index++);
		}
		public void remove() {
			
		}
	}
	
	
}
