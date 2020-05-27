package com.test.sts;

public class IntStack {
	private int max;
	private int[] stk;
	private int ptr;
	
	public class EmptyStackException extends RuntimeException{
		public EmptyStackException() {}
	}
	
	public class FullStackException extends RuntimeException{
		public FullStackException() {}
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
	
	public boolean isFull() {
		return ptr >=max;
	}

	
	public void dump() {
		if(ptr<=0) {
			System.out.println("스택이 비어있습니다");
		}
	}
	
	
	
}
