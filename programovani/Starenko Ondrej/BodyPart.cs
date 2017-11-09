using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HadOlympiada
{
    class BodyPart
    {
        public int X { get; set; }
        public int Y { get; set; }

        public BodyPart PreviousBodyPart { get; set; }

        public BodyPart(int x, int y)
        {
            X = x;
            Y = y;
        }

        public BodyPart()
        {

        }
        public BodyPart(BodyPart previous, int x, int y)
        {
            PreviousBodyPart = previous;
            X = previous.X + x;
            Y = previous.Y + y;
        }
    }
}
