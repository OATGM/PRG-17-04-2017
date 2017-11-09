using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Had
{
    class Had
    {
        private const int TELO = 500; //maximálně může mít velikost přes 500 políček
        private const int START_X = 50;
        private const int START_Y = 25;
        public int[] PosX = new int[TELO];
        public int[] PosY = new int[TELO];
        public int PocetBodu { get; private set; }

        public Had()
        {
            PocetBodu = 3;
            //Pozice prvních bodů
            for (int i = 0; i < PocetBodu; i++)
            {
                PosX[i] = START_X + i;
                PosY[i] = START_Y;
                
            }
        }

        public Boolean Pohyb(int direction)
        {
            int lastX = PosX[PocetBodu],
                lastY = PosY[PocetBodu];
            for (int i = PocetBodu; i > 0; i--)
            {
                PosX[i] = PosX[i - 1];
                PosY[i] = PosY[i - 1]; 
            }

            switch (direction)
            {
                case Direction.Up:
                    {
                        
                        PosY[0]--;
                        //prochazení zdí
                        if (PosY[0] == HraciPole.zedY) PosY[0] = 1;
                        else if (PosY[1] == 1) PosY[0] = HraciPole.zedY - 1;
                        break;
                    }
                case Direction.Down:
                    {
                        PosY[0]++;
                        if (PosY[0] == HraciPole.zedY) PosY[0] = 1;
                        else if (PosY[0] == 1) PosY[0] = HraciPole.zedY;
                        break;
                    }
                case Direction.Right:
                    {
                        PosX[0]++;
                        if (PosX[0] == HraciPole.zedX) PosX[0] = 1;
                        else if (PosX[0] == 1) PosX[0] = HraciPole.zedX;
                        break;
                    }
                case Direction.Left:
                    {
                        PosX[0]--;
                        if (PosX[0] == HraciPole.zedX) PosX[0] = 1;
                        else if (PosX[0] == 0) PosX[0] = HraciPole.zedX - 1;
                        break;
                    }
            }

            if (PosX[0] == HraciPole.jablkoX && PosY[0] == HraciPole.jablkoY)
            {
                PocetBodu++;
                HraciPole.UmistiJablko();
            }

            for (int i = PocetBodu; i > 0; i--)
            {
                if (PosX[0] == PosX[i] && PosY[0] == PosY[i]) return false; //pokud narazí sám do sebe vrátí false (konec hry)
            }

            if(!(lastY == HraciPole.zedY + 1 || lastX == HraciPole.zedX + 1 || lastX == 0 || lastY == 0))
                HraciPole.HraciPlocha[lastY, lastX] = ' ';
            HraciPole.HraciPlocha[PosY[0], PosX[0]] = '8';
            return true;
        }

        public void ZvysBody()
        {
            PocetBodu++;
        }

    }
}
