import java.util.*;
 class Matrix {
     private int rows;
     private int coloumns;
     private int matrix[][];
     public int getRows() {
         return this.rows;
     }
     public int getColoumns() {
         return this.coloumns;

     }
     public Matrix(int rows, int columns) {
         this.matrix = new int[rows][columns];
         this.rows=rows;
         this.coloumns=columns;
     }

     public void setValue(int row, int column, int value) {
         this.matrix[row][column] = value;
     }

     public int getValue(int row, int column) {
         return this.matrix[row][column];
     }

     public Matrix multiply( Matrix b/*,Matrix C*/) {

         int r1 = this.rows;
         int c1 = this.coloumns;
         int r2 = b.rows;
         int c2 = b.coloumns;
         if (c1 != r2) return null;
         ArrayList<Integer> c = new ArrayList<>();
         int sum = 0;
         Matrix C = new Matrix(r1, c2);
         for (int i = 0; i < r1; i++) {
             for(int k=0;k<c2;k++) {
                 sum = 0;
                 for (int j = 0; j < c1; ) {
                     sum += this.getValue(i, j) * b.getValue(j, k);
                     //System.out.println(this.getValue(i,j)+"x"+b.getValue(j,k));

                     j++;
                 }
                 c.add(sum);

             }

         }
         //System.out.println(c.size());
         /*for(int i=0;i<c.size();i++){
             System.out.println(c.get(i));
         }*/
         int k = 0;
         for (int i = 0; i < r1; i++) {
             for (int j = 0; j < c2; j++) {
                 C.setValue(i, j, c.get(k));
                 k++;
                 }

         }
                 return C;
         }

     public Matrix addition( Matrix b,Matrix C, char x) {
         int r1 = this.rows;
         int c1 = this.coloumns;
         int r2 = b.rows;
         int c2 = b.coloumns;
         int sum = 0;
         if (r1 != r2 || c1 != c2) return null;
         C = new Matrix(r1, c1);
         //System.out.println(C.getValue(1,1));
         if (x == '+') {
             for (int i = 0; i < r1; i++) {
                 for (int j = 0; j < c1; j++) {
                     sum = this.getValue(i, j) + b.getValue(i, j);
                     C.setValue(i, j, sum);
                     System.out.println(C.getValue(i,j));

                 }

             }

         }

         if (x == '-') {
             for (int i = 0; i < r1; i++) {
                 for (int j = 0; j < c1; j++) {
                     sum = this.getValue(i, j) - b.getValue(i, j);
                     C.setValue(i, j, sum);

                 }

             }

         }

         return C;

     }
 }

public class Main {

     public static void main(String[] args) {

         int n;
         int y;
         int x;
         int k;
         String c;
         int e=0;
         //System.out.println(C.getValue(0,0));
         Scanner scanner=new Scanner(System.in);
         n=scanner.nextInt();
         scanner.nextLine();
         y=scanner.nextInt();
         x=scanner.nextInt();
         scanner.nextLine();
         //System.out.println("passed information get");
         Matrix a=new Matrix(y,x);
         Matrix C=new Matrix(0,0);

        for(int i=0;i<y;i++){
            for(int j=0;j<x;j++){
                k=scanner.nextInt();
                a.setValue(i,j,k);

            }

            scanner.nextLine();

        }
        //System.out.println("passed value set");
        y=scanner.nextInt();
        x=scanner.nextInt();
        Matrix b=new Matrix(y,x);
        scanner.nextLine();
        //System.out.println("passed information get 2");

        for(int i=0;i<y;i++){
            for(int j=0;j<x;j++){
                k=scanner.nextInt();
                b.setValue(i,j,k);

            }

            scanner.nextLine();

        }

        c=scanner.nextLine();
        for(int i=0;i<n;i++) {
            if (c.equals("*")) {
                if (a.multiply( b) == null) {

                    e++;

                }

                C=a.multiply( b);
                if (a.multiply( b) == null)break;
                 //System.out.println(C.getValue(1,1));

            }

            if (c.equals("+")) {

                if (a.addition( b, C,'+') == null) {

                    e++;

                }

                if (a.addition( b, C,'+') == null)break;
                C = a.addition( b,C, '+');

            }

            if (c.equals("-")) {

                if (a.addition( b, C,'-') == null) {

                    e++;

                    }

                if (a.addition( b, C,'-') == null)break;
                C = a.addition( b, C,'-');
                }
            a = C;
        }
        //System.out.println("passed calculation");

        if(e==0){

                for(int q=0;q<C.getRows();q++){
                    for(int w=0;w<C.getColoumns();w++){
                        System.out.print(C.getValue(q,w)+" ");
                    }
                    System.out.println();
                }

            }

            else System.out.println("ERROR");

        }

 }

