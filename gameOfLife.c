#include <stdio.h>

#include <stdbool.h>

#include <stdlib.h>

#include <time.h>

void graph(int squ, int maxy, int maxx, int field[(2 * squ) + 1][(2 * squ) + 1]) {

    for (int i = 1; i <= 3 * (maxx+1); i++) {

        printf("=");
    }

    printf("\n");

    for (int j = 0; j <= maxy; j++) {

        for (int i = 0; i <= maxx; i++) {

            if (field[j][i] == 1)printf("|*|");

            else printf("| |");

        }
        printf("\n");

    }

    for (int i = 1; i <= 3 * (maxx+1); i++) {

        printf("=");

    }

}

int neightbor(int squ, int j, int i, int field[(2 * squ) + 1][(2 * squ) + 1]){

   int res=0;

    for(int k=j-1;k<=j+1;k++)

        for(int l=i-1;l<=i+1;l++){

            if((k>=0 && l>=0)&&(k<=2*squ && l<=2*squ))

                if(k!=j || l!=i)

                    res+=field[k][l];

        }

return res;

}

void check_cell(int squ, int j, int i, int field[(2 * squ) + 1][(2 * squ) + 1],int field2[(2 * squ) + 1][(2 * squ) + 1]) {

    int res;

    res=0;

    if (field[j][i] == 1) {

        res=neightbor(squ,j,i,field);

  if (res == 3 || res == 2) {

            field2[j][i] = 1;

        } else field2[j][i] = 0;
    }

    if (field[j][i] == 0) {

        res=neightbor(squ,j,i,field);

        if (res == 3)

            field2[j][i] = 1;

    }

}

int main() {

    int squ, times, x, y;

    static int c;
    c=0;

    static int r;
    r=0;

    scanf("%d%d", &squ, &times);

    int field[(2 * squ) + 1][(2 * squ) + 1];

    int field2[(2 * squ) + 1][(2 * squ) + 1];

    for (int j = 0; j <= (2 * squ); j++)
        for (int i = 0; i <= (2 * squ); i++)
            field[j][i] = 0;

    for (int j = 0; j <= (2 * squ); j++)
        for (int i = 0; i <= (2 * squ); i++)
            field2[j][i] = 0;


    while (1) {

        scanf("%d%d", &x, &y);
        if (((y <= squ) && (x <= squ)) && ((y>=0) && (x>=0))) field[y][x] = 1;

        else break;

    }

    y = squ;

    x = squ;

    graph(squ, y, x, field);

    for(int l=0;l<100;l++) printf("\n");

    for (int k = 1; k <= times; k++) {

        if (k % 2 != 0) {

            for (int j = 0; j <= y; j++) {

                for (int i = 0; i <= x; i++) {

                    check_cell(squ, j, i, field, field2);

                    if (field[j][i]==1) {

                        if (j == y && y != 2 * squ) y++;

                        if (i == x && x != 2 * squ) x++;

                        if(y==2*squ && r==0)
                        {
                            printf("maximum row capacity reached.\n");

                            r++;
                        }


                        if(x==2*squ && c==0 ) {

                            printf("maximum column capacity reached.\n");

                            c++;

                        }


                    }

                }

            }


            for (int j = 0; j <= 2 * squ; j++)
                for (int i = 0; i <= 2 * squ; i++)
                    field[j][i] = 0;

            graph(squ, y, x, field2);

            for(int l=0;l<100;l++) printf("\n");

        }

        else {

            for (int j = 0; j <= y; j++) {

                for (int i = 0; i <= x; i++) {

                    check_cell(squ, j, i, field2, field);

                    if (field2[j][i]==1) {

                        if (j == y && y != 2 * squ) y++;

                        if (i == x && x != 2 * squ) x++;

                        if(y==2*squ && r==0) {

                            printf("maximum row capacity reached.\n");

                            r++;

                        }

                        if(x==2*squ && c==0) {

                            printf("maximum column capacity reached.\n");

                            c++;
                        }

                    }

                }

            }

            graph(squ, y, x, field);

            for(int l=0;l<100;l++) printf("\n");

            for (int j = 0; j <= 2 * squ; j++)
                for (int i = 0; i <= 2 * squ; i++)

                    field2[j][i] = 0;

    }

    }

}
