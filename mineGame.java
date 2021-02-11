
import java.util.*;
public class Main {
    public static void main(String args[]) {

        Scanner scanner = new Scanner(System.in);
        int y = scanner.nextInt();
        int x = scanner.nextInt();
        String line[]=new String[y];
        char matrix[][] = new char[y][x];
        char relfection[][] = new char [y][x];
        scanner.nextLine();
        for(int k=0;k<y;k++){
            line[k]=scanner.nextLine();
        }
        //System.out.println("flag1");
        for(int i=0;i<y;i++){
            for(int j=0;j<x;j++){
                matrix[i][j]=line[i].charAt(j);
            }
        }
        //System.out.println("flag2");
        int sum=0;
        char sumc='0';
        int k;
        int l;
        int i;
        int j;
        for( i=0;i<y;i++) {
            for (  j = 0; j < x; j++) {
                for( k=i-1;k<=i+1;k++) {
                    for (l = j - 1; l <= j + 1; l++) {
                        if (k >= y || k < 0 || l >= x || l < 0)continue;
                        else if(matrix[k][l] == '*') sum++;

                    }
                }

                int redix=10;
                sumc=Character.forDigit(sum,redix);
                //if(k>y || k<0 || l>x || l<0)continue;
                relfection[i][j]=sumc;
                sum=0;

            }



        }
        for(int o=0;o<y;o++){
            for(int z=0;z<x;z++){
                if(matrix[o][z]=='*'){
                    relfection[o][z]=matrix[o][z];
                }
            }
        }
        //System.out.println("flag3");
        for(int w=0;w<y;w++){
            for (int q=0;q<x;q++){
                System.out.print(relfection[w][q]);
            }
            System.out.println();
        }
        //System.out.println("flag4");












    }

}
