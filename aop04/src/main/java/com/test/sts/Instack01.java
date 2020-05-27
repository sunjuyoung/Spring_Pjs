package com.test.sts;

public class Instack01 {

	private int max;
	private int ptr;
	private int[] stk;

	
	public class EmptyStack extends RuntimeException{
		public  EmptyStack() {System.out.println("비었습니다");}
	}
	
	public class FullStack extends RuntimeException{
		public FullStack () {System.out.println("가득찼습니다");}
	}
	
	public Instack01() {
		super();
	}
	
	public Instack01(int capacity) {
	
		ptr=0;
		max=capacity;
		try {
		stk = new int[max];
			
		}catch(Exception e) {
			
		}
	}
	
	
	public int push(int x) throws FullStack{
		if(ptr>=max) {
			throw new FullStack();
		}
		return stk[ptr++]= x;
	}
	
	public int pop() throws EmptyStack {
		if(ptr<=0) {
			throw new EmptyStack();
		}
		return stk[--ptr];
	}
	
	public int peek() {
		if(ptr <=0) {
			throw new EmptyStack();
		}
		
		return stk[ptr -1];
	}
	
	
}
