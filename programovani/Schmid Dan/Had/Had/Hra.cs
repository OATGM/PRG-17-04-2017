using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Threading;

namespace Had
{
    class Hra
    {
        private Had had;

        public Hra()
        {
            had = new Had();
            HraciPole.VytvorHraciPole();
        }

        public void Hraj()
        {
            HraciPole.UmistiJablko();
            while (true)
            {
                HraciPole.HraciPlocha[had.PosY[0], had.PosX[0]] = '8';
                for (int i = had.PocetBodu; i > 0; i--)
                {
                    HraciPole.HraciPlocha[had.PosY[i], had.PosX[i]] = '0';
                }
                Grafika.VypisHraciPole(had.PocetBodu);
                if (!(had.Pohyb(Smer()))) break;
            }

            Console.WriteLine(              "************           GAME OVER!                **************");
                
        }

        private int Smer()
        {
            int direction = 0;
            ConsoleKeyInfo key = Console.ReadKey();
            switch (key.Key)
            {
                case ConsoleKey.UpArrow:
                    {
                        direction = Direction.Up;
                    break;
                    }
                case ConsoleKey.DownArrow:
                    {
                        direction = Direction.Down;
                        break;
                    }
                case ConsoleKey.RightArrow:
                    {
                        direction = Direction.Right;
                        break;
                    }
                case ConsoleKey.LeftArrow:
                    {
                        direction = Direction.Left;
                        break;
                    }
            }

            return direction;
        }
    }
}
