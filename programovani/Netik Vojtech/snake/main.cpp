#include "base/common.h"
#define SIZE 10
int snakelen = 2;
int input;
int posy = SIZE/2;
int posx = SIZE/2;
int y = 0;
int x = 0;
int moves = 0;

int map[SIZE][SIZE] = {
	{0,0,0,0,0,0,0,0,0,0,},
	{0,0,0,0,0,0,0,0,0,0,},
	{0,0,0,0,0,0,0,0,0,0,},
	{0,0,0,0,0,0,0,0,0,0,},
	{0,0,0,0,0,0,0,0,0,0,},
	{0,0,0,0,0,0,0,0,0,0,},
	{0,0,0,0,0,0,0,0,0,0,},
	{0,0,0,0,0,0,0,0,0,0,},
	{0,0,0,0,0,0,0,0,0,0,},
	{0,0,0,0,0,0,0,0,0,0,},
};

int getmap(int a, int b){
	if(a>=SIZE)a=0;
	if(b>=SIZE)b=0;
	if(a<0)a=SIZE-1;
	if(b<0)b=SIZE-1;
	return map[a][b];
}

void moveTo(int a, int b){
	if(a>=SIZE)a=0;
	if(b>=SIZE)b=0;
	if(a<0)a=SIZE-1;
	if(b<0)b=SIZE-1;

	posy=a;
	posx=b;
	map[a][b]=1;
	for(y=0; y<SIZE; y++){
		for(x=0; x<SIZE; x++){
			if(map[y][x]>0){
				mvprintw(y, x, "0");
				map[y][x]+=1;
			}
			if(map[y][x]>=snakelen+2){
				map[y][x]=0;
			}
			if(map[y][x]==0)mvprintw(y ,x , " ");
		}
	}
}

void foodgen(){
	int x = 0;
	srand (time(NULL));
	int foodx = 0;
	int foody = 0;
	while(x==0){
		foody = rand() % (SIZE);
		foodx = rand() % (SIZE);
		if(map[foody][foodx]>0){
			x=0;
		}else if(map[foody][foodx]==0){
			map[foody][foodx]=-1;
			mvprintw(foody, foodx, ".");
			x=1;
		}
	}
	
}

void snakemove(int a, int b){
	if(getmap(a, b)==-1){
		snakelen+=1;

		moveTo(a, b);
		
		foodgen();
	}else moveTo(a, b);
	moves+=1;
}

int main(){
	int a;
	int b;
	initW();
	curs_set(0);
	foodgen();
	for(a=0; a<=SIZE; a++){
		mvprintw(a, SIZE, "#");
		mvprintw(SIZE, a, "#");
	}
	mvprintw(0, SIZE+2, "S N A K E");
	mvprintw(2, SIZE+2, "Controls");
	mvprintw(3, SIZE+2, "Arrow keys - movement | 'q' - quit");
	
	map[posy][posx]=2;
	mvprintw(posy,posx,"0");
	while(1){
		input = getch();
		if(input!=ERR){
			if(input=='q')break;
			if(input==KEY_DOWN){
				if(getmap(posy+1, posx)>0){
					mvprintw(5, SIZE+2, "G A M E   O V E R");
					refresh();
					break;
				}
				else snakemove(posy+1, posx);
			}
			if(input==KEY_UP){
				if(getmap(posy-1, posx)>0){
					mvprintw(5, SIZE+2, "G A M E   O V E R");
					refresh();
					break;
				}else snakemove(posy-1, posx);
			}
			if(input==KEY_LEFT){
				if(getmap(posy, posx-1)>0){
					mvprintw(5, SIZE+2, "G A M E   O V E R");
					refresh();
					break;
				}
				else snakemove(posy, posx-1);
				
			}
			if(input==KEY_RIGHT){
				if(getmap(posy, posx+1)>0){
					mvprintw(5, SIZE+2, "G A M E   O V E R");
					refresh();
					break;
				}
				else snakemove(posy, posx+1);
			}
		}
		mvprintw(SIZE+2, 0, "Score: %d", snakelen-2);
		mvprintw(SIZE+3, 0, "Moves: %d", moves);
		
		if(snakelen==SIZE*SIZE){
			mvprintw(5, SIZE+2, "Y O U   H A V E   W O N");
			refresh();
			break;
		}

		timespec *tm = new timespec();
		tm->tv_sec = 0;
		tm->tv_nsec = 1000000;
		nanosleep(tm, 0);
		delete tm;

		refresh();
	}
	endW();
	return 0;
}
