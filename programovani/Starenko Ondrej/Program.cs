using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace HadOlympiada
{
    class Program
    {
        static int[] directions = { 0,0 };

        static void Main(string[] args)
        {
            GameUI gameUI = new GameUI(20, 20);
            Game game = new Game(20, 20);

            game.ShowSnake();
            game.GenerateFood();

            gameUI.Refresh(game.GameArray);
            gameUI.Show();

            int [] lastdirections = {20,0 };
            while (game.Live)
            {
                Console.WriteLine("score: " + game.Snake.Count);
                Thread.Sleep(300);
               directions = zadavani();


                
                if (directions[0] != lastdirections[0] && directions[1] != lastdirections[1])
                {
                    game.zatoc(directions);
                }
                else if((directions[0] == lastdirections[0] && directions[1] == lastdirections[1]))
                    game.zatoc(lastdirections);

                lastdirections = directions;




                game.ShowSnake();

                Console.Clear();

                gameUI.Refresh(game.GameArray);
                gameUI.Show();

            } 
                

            Console.Clear();
            Console.WriteLine("GAME OVER");
            Console.ReadKey();

        }

        public static int[] zadavani()
        {
            int smerX = directions[0];
            int smerY = directions[1];
            ConsoleKeyInfo key = new ConsoleKeyInfo();

            if (Console.KeyAvailable)
            {
                 key = Console.ReadKey();
            }
            
            


            if (key.Key == ConsoleKey.UpArrow)
            {
                smerX = -1;
                smerY = 0;
            }
            else if (key.Key == ConsoleKey.LeftArrow)
            {
               smerY = -1;
                smerX = 0;
            }
            else if (key.Key == ConsoleKey.RightArrow)
            {
                smerY = 1;
                smerX = 0;
            }
            else if (key.Key == ConsoleKey.DownArrow)
            {
                smerX = 1;
                smerY = 0;
            }

            return new int[] { smerX, smerY };
        }
    }
}
