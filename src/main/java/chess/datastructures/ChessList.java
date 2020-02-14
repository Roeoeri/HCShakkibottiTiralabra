package chess.datastructures;

public class ChessList {
	
	private Object[] list;
	private  int SIZE = 30;
	
	public ChessList() {
		this.list = new Object[SIZE];
	}
	
	
	private void growSize() {
		int NEWSIZE = SIZE*2;
		Object[] newList = new Object[NEWSIZE];
		for(int i = 0; i< SIZE; i++) {
			newList[i] = list[i];	
		}
		list = newList;
		SIZE = NEWSIZE;
	}

}
