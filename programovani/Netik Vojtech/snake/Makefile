OUTPUT:= run

FILES:= main.cpp base/common.c base/menu.cpp myMenu.cpp
OBJS:= main.o common.o menu.o myMenu.o
GPPFLAGS=-lncurses -std=c++11

all:

	g++ -c $(FILES) $(GPPFLAGS)
	g++ -o $(OUTPUT) $(OBJS) $(GPPFLAGS)
	
clean:
	rm -f run *.o
