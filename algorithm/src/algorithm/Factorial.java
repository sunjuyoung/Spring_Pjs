package algorithm;

import java.util.Scanner;

public class Factorial {

	
	static int factorial(int a) {
		
		if(a>0)return a*factorial(a-1);
		
		return 1;
	}
	
	static void recur(int x) {
		if(x>0) {
			recur(x-1);
			System.out.println(x);
			recur(x-2);
		}
	}
	
	
	
	
	public static void main(String[]args) {
		
		recur(4);
		/*
		 * 
		 * Scanner sc = new Scanner(System.in);
		 * 
		 * 
		 * 
		 * int f = factorial(5); System.out.println(f);
		 */	
		}
}
