/**
1 6 11 16 21
2 7 12 17 22
3 8 13 18 23
4 9 14 19 24
5 10 15 20 25
 */
public class test03 {
    public static void main(String[] args) {
        
        int arr[][]= new int[5][5];
       
        int VAL=0;
        int COL=0;
        do{
            int ROW=0;
            do{
                ++VAL;
                arr[ROW][COL]=VAL;
               ++ROW;
              
            }while(ROW<=4);
          
          ++COL;

        }while(COL<=4);


        for(int a=0; a<5; a++){
            for(int b=0; b<5; b++){
                System.out.printf("%3d",arr[a][b]);
            }System.out.println();
        }



        
    }

    }


    

  