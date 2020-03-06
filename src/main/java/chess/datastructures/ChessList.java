package chess.datastructures;

import java.util.Iterator;


public class ChessList<E> implements Iterable<E>{
	
	private E[] list;
	private int SIZE = 30;
	private int pointer = 0;
	
	public ChessList() {
		list = (E[])new Object[SIZE];
	}
	
	
	/** Lisää parametrina annetun elementin listaan.
	 * @param e lisättävä elementti.
	 */
	public void add(E e) {
		list[pointer] = e;
		pointer ++;
		if(pointer == SIZE){
			growSize();
		}
	}
	
	
	/** Palauttaa listassa indeksissä olevan olion.
	 * @param index haettava indeksi.
	 * @return E Indeksissä oleva olio.
	 */
	public E get(int index) {
		return list[index];
	}
	
	
	/** Palauttaa listan koon.
	 * @return int Listan koko.
	 */
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
	
	
	/**  
	 * @return Iterator<E> Iteraattori.
	 */
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
