package algorithm;

import java.util.Scanner;

public class Search01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("요솟수 : ");
		int num = sc.nextInt();
		int x[] = new int[num];
		
		for(int a=0; a<num; a++) {
			System.out.println("a["+a+"] :" );
			x[a] = sc.nextInt();
		}
		
		System.out.println("검색할 값");
		int key = sc.nextInt();
		
		System.out.println(key+"는"+seqSearch(key, x,num)+"에 있습니다");

	}

	
	
	static int seqSearch(int key, int x[],int num) {
		
	
		
		for(int i=0; i<num; i++) {
			if(key == x[i]) {
				return i;
			}
		}
		
		
		
		return -1;
	}
	
	
}
