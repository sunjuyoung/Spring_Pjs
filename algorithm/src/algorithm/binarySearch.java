package algorithm;

import java.util.Arrays;
import java.util.Scanner;

public class binarySearch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		System.out.println("요솟수 :");
		int num = sc.nextInt();
		
		int []a= new int[num];
		
		for(int i=0; i<num; i++) {
			a[i] = (int)(Math.random()*30);
		}
		Arrays.sort(a);
		
		
		
		
		
		
		
		for(int i=0; i<num; i++) {
			System.out.println(" "+a[i]);
		}
		
		System.out.println("찾을 값:");
		int key = sc.nextInt();
		
		System.out.println(binarySearch(a, key, num));
		

	}
	
	
	static int binarySearch(int[] a, int key, int n) {
		
		int pl = 0;
		int pr = n-1;
		
		
		while(true) {
			int pc = (pl+pr)/2;
			if(key == a[pc]) {
				return key;
			}else if(a[pc]<key) {
				pl=pc+1;
			}else {
				pr = pc-1;
			}
			
			if(pl<=pr) {
				return -1;
			}
		
		}
	
		
		
	}

}
