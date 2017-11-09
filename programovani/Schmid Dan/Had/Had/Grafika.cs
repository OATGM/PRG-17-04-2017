using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Had
{
    static class Grafika
    {
        public static void VypisHraciPole(int pocetBodu)
        {
            Console.Clear();
            Console.WriteLine(HraciPole.VratTextHraciPole());
            Console.WriteLine("Vaše skóre: " + pocetBodu);
        }
    }
}
