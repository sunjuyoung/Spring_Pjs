/**
1 6 11 16 21
2 7 12 17 22
3 8 13 18 23
4 9 14 19 24
5 10 15 20 25
 */
public class test01 {

    public static void main(String[] args) {
        
        int num=0;
        int [][]arr = new int[5][5];
        for(int a=0; a<5; a++){
            for(int b=0; b<5; b++){
                arr[b][a]=++num;
            }
        }

      for(int[] ar : arr){
          System.out.println(" ");
       for(int a : ar){
           System.out.printf("%3d ",a);
       }
      }
    }
}