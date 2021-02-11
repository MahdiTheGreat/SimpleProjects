#include <stdio.h>
#include <time.h>
#include <malloc.h>
#include <stdlib.h>
#include <string.h>

struct game{

    int p;
    int c;
    int t;
    char player[20];
    int chance[7];
    int leftoff;
    int max;

};

struct problem
{
    char *story;
    char *op1;
    int p1;
    int c1;
    int t1;
    char *op2;
    int p2;
    int c2;
    int t2;
    struct problem *next;
    int possible;
};
struct random{
    int num;
    struct random* next;
};

struct random* randboot(){
    struct random r0;
    struct random r1;
    struct random r2;
    struct random r3;
    struct random r4;
    struct random r5;
    struct random r6;
    r0.num=0;
    r1.num=1;
    r2.num=2;
    r3.num=3;
    r4.num=4;
    r5.num=5;
    r0.num=6;
    r0.next=&r1;
    r1.next=&r2;
    r2.next=&r3;
    r3.next=&r4;
    r4.next=&r5;
    r5.next=&r6;
    r6.next=NULL;
    struct random*pr0=&r0;
    return pr0;
}

int random(int max){

    int num;
    time_t t = time(NULL);
    srand(t);
    num =rand() % (max + 1 - 0) + 0;
    return num;
}

struct problem* boot(){

    FILE *f,*fp;
    char line[200];
    struct problem *head;
    head  = NULL;
    char **adress;
    adress = (char **) malloc(7 * sizeof(char *));
    for (int j = 0; j < 10; j++)
        adress[j] = (char *) malloc(200 * sizeof(char));
    f = fopen("C:\\Projectfiles\\CHOICES.txt", "r");
    char c;
    int i = 0, j;
    int k = 0;
    int m;
    while (i != 7) {
        fgets(adress[i], 200, f);
        if (i < 6) {
            m = strlen(adress[i]);
            adress[i][m - 1] = '\0';
        }
        i++;
    }

    struct problem *node = malloc(sizeof(struct problem));
    while (k < 7) {
        fp = fopen(adress[k], "r+");
        fgets(line, sizeof(line), fp);
        node->story = strdup(line);//note : strdup is not standard function
        fgets(line, sizeof(line), fp);
        node->op1 = strdup(line);//note : strdup is not standard function
        fgets(line, sizeof(line), fp);
        node->p1 = atoi(line);//note : strdup is not standard function
        fgets(line, sizeof(line), fp);
        node->c1 = atoi(line);//note : strdup is not standard function
        fgets(line, sizeof(line), fp);
        node->t1 = atoi(line);//note : strdup is not standard function
        fgets(line, sizeof(line), fp);
        node->op2 = strdup(line);//note : strdup is not standard function
        fgets(line, sizeof(line), fp);
        node->p2 = atoi(line);//note : strdup is not standard function
        fgets(line, sizeof(line), fp);
        node->c2 = atoi(line);//note : strdup is not standard function
        fgets(line, sizeof(line), fp);
        node->t2 = atoi(line);//note : strdup is not standard function
        node->possible=3;
        if (head == NULL) {
            head = node;
        }
        fclose(fp);
        k++;
        if (k <= 6) {
            node->next = malloc(sizeof(struct problem));
            node = node->next;
        }
    }

    return head;


}
void array(struct problem* arr[7],struct problem* head){
    struct problem* current;
    current=head;
    for(int i=0;i<7;i++){
        if(i==0)current=head;
        else current=current->next;
        arr[i]=current;
    }
}

void fplayername(struct game* main){

    char playername[20];

    printf("enter your name: "); gets(playername);

    for(int i=0;i<20;i++)main->player[i]=playername[i];
}

void mainmenu(char* player){
    printf("welcome %s,please select one of the options below",player);
    int answer;
    printf("\n[1]start a newgame\n[2]resume a game");
}

void newgame(struct game* main){

    main->p=50;
    main->c=50;
    main->t=50;
    main->max=6;
    for(int i=0;i<7;i++)main->chance[i]=3;
    main->leftoff=1;
}

void playerstatus(struct game* main){

    printf("people:%d court:%d treasury:%d\n",main->p,main->c,main->t);
}

int fproblem(struct problem*arr[7],struct game *main){

    int o=0;
    int c=0;
    int j=0;
    for(int i=0;i<7;i++)if(arr[i]->possible==0)j++;
    if(j==7)for(int i=0;i<7;i++)arr[i]->possible=3;
    do {
        time_t t = time(NULL);
        srand(t);
        o = rand() % (6 + 1 - 0) + 0;
    }while(arr[o]->possible<=0);
    arr[o]->possible--;
    printf("%s\n[1]%s\n[2]%s\n",arr[o]->story,arr[o]->op1,arr[o]->op2);
    scanf("%d",&c);
    switch(c){
        case 1: {
            main->p += arr[o]->p1;
            main->c += arr[o]->c1;
            main->t += arr[o]->t1;
            break;
        }
        case 2: {
            main->p += arr[o]->p2;
            main->c += arr[o]->c2;
            main->t += arr[o]->t2;
            break;
        }
        case 3:{
            return 3;

        }
    }
    return 0;
}

int check(struct game main) {

    struct game *pmain=&main;
    if(pmain->p<0)pmain->p=0;
    if(pmain->p>100)pmain->p=100;
    if(pmain->c<0)pmain->c=0;
    if(pmain->c>100)pmain->c=100;
    if(pmain->t<0)pmain->t=0;
    if(pmain->t>100)pmain->t=100;
    if (((pmain->p + pmain->c + pmain->t) / 3) < 10) {
        pmain->leftoff=0;
        printf("you have lost\n");
        return 3;
    }
    if ((pmain->p <= 0 )|| (pmain->c <= 0) ||( pmain->t <= 0)) {
        pmain->leftoff=0;
        printf("you have lost\n");
        return 3;
    }

    return 0;
}

int main() {
    struct problem *head;
    struct random* r0;
    struct game main;
    struct game *pmain = &main;
    int o;
    struct problem* arr[7];
    head=boot();
    r0=randboot();
    array(arr,head);
    fplayername(pmain);
    mainmenu(main.player);
    scanf("%d",&o);
    if(o==1)newgame(pmain);
    if(o==2) {
        FILE *fp2;
        struct game loadtest;
        struct problem* current2;
        fp2 = fopen("C:\\save\\save.bin", "r+b");
        fread(&loadtest, sizeof(struct game), 1, fp2);

        if (loadtest.leftoff == 0) {
            newgame(pmain);
        } else {
            main.p = loadtest.p;
            main.c = loadtest.c;
            main.t = loadtest.t;
            for(int i=0;i<20;i++)main.player[i]=loadtest.player[i];
            for (int j = 0; j < 7; j++)main.chance[j] = loadtest.chance[j];
            main.leftoff=loadtest.leftoff;
            for(int i=0;i<7;i++){
                if(i==0)current2=head;
                else current2=current2->next;
                current2->possible=main.chance[i];
            }
        }
    }

    while(o!=3) {
        playerstatus(pmain);
        printf("\npassed playerstatus\n");
        o=fproblem(arr,&main);
        if(o==3)break;
        o=check(main);
    }

    FILE* fp1;
    int o1;
    printf("what do you want to do?\n[1]save\n[2]exit game");
    scanf("%d",&o1);
    if(o1==1) {
        int c;
        c=check(main);
        if(c==3)main.leftoff=0;
        for(int i=0;i<7;i++)main.chance[i]=0;
        for(int i=0;i<7;i++)main.chance[i]=arr[i]->possible;
        fp1 = fopen("C:\\save\\save.bin", "w+b");
        fwrite(&main, sizeof(struct game), 1, fp1);
    }
    if(o1==2){
        printf("goodbye %s",main.player);
    }

}
