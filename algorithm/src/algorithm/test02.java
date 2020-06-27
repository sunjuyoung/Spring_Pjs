

/**
 *1  0  0  0  0
  2  6  0  0  0
  3  7 10  0  0
  4  8 11 13  0
  5  9 12 14 15
 */
public class test02 {
    public static void main(String[] args) {
        

        int num=0;
        int xx=1;
        int [][]arr = new int[5][5];
        for(int a=0; a<5; a++){
            for(int b=0; b<5; b++){
                if(xx == a){
                   b+=xx-1;
                    xx++;
                    continue;   
                }
                    arr[b][a]=++num;
            }
        }



        /* 출력 */
      for(int[] ar : arr){
          System.out.println(" ");
       for(int a : ar){
           System.out.printf("%3d",a);
       }
      }





    }
    
}