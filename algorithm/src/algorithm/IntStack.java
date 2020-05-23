package algorithm;

public class IntStack {
	private int max;
	private int[] stk;
	private int ptr;
	
	public class EmptyStackException extends RuntimeException{
		public EmptyStackException() {System.out.println("가득찼어염");}
	}
	
	public class FullStackException extends RuntimeException{
		public FullStackException() {System.out.println("비어있어염");}
	}
	
	public IntStack(int capacity) {
		ptr = 0;
		max = capacity;
		try {
			stk = new int[max];
		}catch(OutOfMemoryError e) {
			max=0;
		}
	}
	
	public int push(int x) throws FullStackException{
		if(ptr>=max) {
			throw new FullStackException();
		}
		
		return stk[ptr++] = x;
		
		
	}
	
	public int pop() throws EmptyStackException{
		if(ptr<=0) {
			throw new EmptyStackException();
		}
		
		return stk[--ptr];
		
	}
	
	
	public int peek() {
	
		if(ptr == 0) {
			throw new EmptyStackException();
		}
		
		return stk[ptr -1];
	}
	
	
	
	
	

}
