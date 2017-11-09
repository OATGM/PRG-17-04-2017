using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;


namespace snake
{
    class Program
    {
        static int[,] pole = new int[60, 20];
        static int x = 30;
        static int y = 10;

        static void Main(string[] args)
        {
            vypis_pole();
            Console.WriteLine("");
            pole[x, y] += 1;
            vypis_pole();
            while (true)
            {
                Console.ForegroundColor = ConsoleColor.Green;
                pohyb_hada();
                Console.ForegroundColor = ConsoleColor.White;
                Console.WriteLine("");
                vypis_pole();
            }


            Console.ReadKey();
        }
        static string vypis_pole()
        {
            string s = "";
            for (int cyklus = 0; cyklus < pole.GetLength(1); cyklus++)
            {
                
                for (int cyklus2 = 0; cyklus2 < pole.GetLength(0); cyklus2++)
                {
                    if ((pole[cyklus2, cyklus] == 0))
                    {
                        Console.Write(".");
                    }
                    else if ((pole[cyklus2, cyklus] == 1))
                    {
                        Console.Write("x");
                    }
                    
                   
                }
                Console.WriteLine(" ");
            }
            return s;
        }
        static void pohyb_hada()
        {
            Console.WriteLine();

        start:
            Console.WriteLine("Zadejte směr pohybu. (3 = vpravo, 2 = dolů, 5 = nahoru, 1 = vlevo");
            try
            {
                
                int smer = int.Parse(Console.ReadLine());
                if (smer == 3)
                {
                    pole[x + 1, y] += 1;
                    x = x + 1;
                }
                else if (smer == 2)
                {
                    pole[x, y + 1] += 1;
                    y = y + 1;

                }
                else if (smer == 5)
                {
                    pole[x, y - 1] += 1;
                    y = y - 1;

                }
                else if (smer == 1)
                {
                    pole[x - 1, y] += 1;
                    x = x - 1;
                }
                else
                {
                    Console.WriteLine("Neplatné číslo.");
                    goto start;
                }

            }
            catch (IndexOutOfRangeException)
            {
                Console.ForegroundColor = ConsoleColor.Red;
                Console.WriteLine("Konec hry.");
            }
            catch (Exception)
            { }
        }

    }
}
    

