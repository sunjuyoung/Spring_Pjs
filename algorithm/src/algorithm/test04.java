/**
 *1   2   3   4   5
  0   6   7   8   0
  0   0   9   0   0
  0  10  11  12   0
 13  14  15  16  17
 */
public class test04 {
    public static void main(String[] args) {

        int [][] arr = new int[5][5];
        int V = 0;
        int M =3; //중간값
      
        for(int R=0; R<M; R++){
            for(int C=R; C<(5-R); C++){
                arr[R][C]=++V;
            }
        }

        for(int R=M; R<=4; R++){
            for(int C=(4-R); C<=R; C++){
                arr[R][C]=++V;
            }
        }


        for(int[] ar : arr){
         for(int a : ar){
             System.out.printf("%3d ",a);
         }  System.out.println(" ");
        }

    }
    
}