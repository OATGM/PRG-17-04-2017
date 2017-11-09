using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Had
{
    static class HraciPole
    {
        private const int VELIKOST_Y = 40;
        private const int VELIKOST_X = 100;
        public static char[,] HraciPlocha = new char[VELIKOST_Y, VELIKOST_X];//[y, x];

        public const int zedX = VELIKOST_X - 1;
        public const int zedY = VELIKOST_Y - 1;

        private static Random random = new Random();
        public static int jablkoX;
        public static int jablkoY;


        public static void VytvorHraciPole()
        {
            for (int y = 0; y < HraciPlocha.GetLength(0); y++)
            {
                for (int x = 0; x < HraciPlocha.GetLength(1); x++)
                {
                    if (x == 0 || y == 0 || x == HraciPlocha.GetLength(1) - 1 || y == HraciPlocha.GetLength(0) - 1) HraciPlocha[y, x] = 'X';
                    else HraciPlocha[y, x] = ' ';
                }
            }
        }

        /// <summary>
        /// Vrátí vytvořené hrací pole
        /// </summary>
        /// <returns>hrací pole</returns>
        public static string VratTextHraciPole()
        {
            string pole = "";
            if (HraciPlocha != null)
            {
                for (int y = 0; y < HraciPlocha.GetLength(0); y++)
                {
                    for (int x = 0; x < HraciPlocha.GetLength(1); x++)
                    {
                        pole += HraciPlocha[y, x];
                    }
                    pole += "\n";
                }
                return pole;
            }
            else
                return null;
        }

        public static char[,] VratHraciPole()
        {
            return HraciPlocha;
        }

        public static void UmistiJablko()
        {
            jablkoX = random.Next(1, HraciPole.zedX);
            jablkoY = random.Next(1, HraciPole.zedY);
            HraciPlocha[jablkoY, jablkoX] = 'Q';
        }


    }
}
