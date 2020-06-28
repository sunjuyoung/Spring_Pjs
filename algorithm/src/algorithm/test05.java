
/**
  1  2  3  4  5
 16 17 18 19  6
 15 24 25 20  7
 14 23 22 21  8
 13 12 11 10  9
 */
public class test05 {
    public static void main(String[] args) {

        int arr[][] = new int[5][5];
        int V = 0;
        int C = -1;
        int R = 0;
        int S = 1; //행열 증감
        int P = 0; //반복
        int K = 5; //배열 반복 횟수

        while(true){
            for( P=0; P<K; P++){
                ++V;
                C=C+S;
                arr[R][C]=V;
            }
            --K;
            if(K<=0)break;
            for(P=0; P<K; P++){
                ++V;
                R=R+S;
                arr[R][C]=V;
            }
            S=S*(-1);
        }

        for(int a=0; a<5; a++){
            for(int b=0; b<5; b++){
                System.out.printf("%3d",arr[a][b]);
            }System.out.println();
        }


    }
    
}