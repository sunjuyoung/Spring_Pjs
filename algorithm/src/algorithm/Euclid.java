package algorithm;

import java.util.Scanner;

//두 정수의 최대공약수 유클리드 호제법
public class Euclid {

	static int gcd(int x, int y) {
		
		if(y==0) {
			return x;
		}
		
		return gcd(y,x%y);
	}
	
	
	public static void main(String [] args) {
		Scanner sc = new Scanner(System.in);
		
		
		
		System.out.println("첫번째 정수");
		int x = sc.nextInt();
		System.out.println("두번째 정수");
		int y = sc.nextInt();
		
		System.out.println(gcd(x, y));
		
		
	}
}
