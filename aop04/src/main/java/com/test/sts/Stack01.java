package com.test.sts;

import java.util.Scanner;

public class Stack01 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
	
		Instack01 in = new Instack01(4);
		
		System.out.println("크기");
		int max = sc.nextInt();
		
		boolean result = true;
		while(result) {
		System.out.println("1.푸시  2.팝  3.확인  4.종료");
		int number = sc.nextInt();	
		
			switch(number) {
			case 1 : System.out.println("");
				break;
			case 2: 
				break;
			case 3 : 
				break;
			case 4 : System.out.println("종료"); result = false;
			break;
				
			
			}
			
			
			
			
		}
		
		

	}

	

}
