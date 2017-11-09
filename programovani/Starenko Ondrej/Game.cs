using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HadOlympiada
{
    class Game
    {
        public Box[,] GameArray { get; set; }
        public Box box = new Box();
        public bool Live { get; set; }

        public List<BodyPart> Snake { get; set; }
        public int LeghtX { get; set; }
        public int LeghtY { get; set; }

        public Game(int _leghtX, int _LeghtY)
        {
            GameArray = new Box[_leghtX, _LeghtY];
            Live = true;

            LeghtX = _leghtX;
            LeghtY = _LeghtY;

            Snake = new List<BodyPart>();
            Snake.Add(new BodyPart(5, 5));

            FillArray();

        }

        /// <summary>
        /// Naplní pole základními daty (zdi)
        /// </summary>
        private void FillArray()
        {
            for (int i = 0; i < GameArray.GetLength(0); i++)
            {
                for (int j = 0; j < GameArray.GetLength(1); j++)
                {
                    GameArray[i, j] = Box.Nothing;

                    GameArray[LeghtX - 1, j] = Box.Wall;
                    GameArray[i, LeghtY - 1] = Box.Wall;

                }
            }

            for (int i = 0; i < GameArray.GetLength(0); i++)
            {
                GameArray[0, i] = Box.Wall;
                GameArray[i, 0] = Box.Wall;
            }

            GenerateFood();
            GenerateFood();
            GenerateFood();

        }

        /// <summary>
        /// přidává novou část a říká jestli smazat tu poslední
        /// </summary>
        /// <param name="zatoceniX"></param>
        /// <param name="zatoceniY"></param>
        /// <returns></returns>
        public bool NewHead(int zatoceniX, int zatoceniY)
        {
            var previousSnake = Snake.Last();
            BodyPart newHead = new BodyPart(Snake.Last(), zatoceniX, zatoceniY);

            //zkontroluje, jestli žije
            Live = live(newHead);

            if (GameArray[newHead.X, newHead.Y] == Box.Food)
            {//kontroluje jestli snědl jídlo, případně vygeneruje další
                Snake.Add(newHead);
                GenerateFood();
                return false;
            }
            if (GameArray[newHead.X, newHead.Y] == Box.poisonedFood)
            {//kontroluje jestli snědl otrávené jídlo, případně vygeneruje další
                Snake.Add(newHead);
                GenerateFood();
                DestroyLastBodyPart();
                return true;
            }
            else if (Snake.Count == 1)
            {
                Snake.Add(newHead);
                return true;
            }
            else if (!(newHead.X == previousSnake.X && newHead.Y == previousSnake.Y))
            {
                Snake.Add(newHead);
                return true;
            }
            return false;

        }

        public void DestroyLastBodyPart()
        {
            GameArray[Snake.First().X, Snake.First().Y] = Box.Nothing;
            Snake.Remove(Snake.First());
        }
        public void ShowSnake()
        {
            foreach (BodyPart part in Snake)
            {
                GameArray[part.X, part.Y] = Box.Snake;
            }
        }

        public void GenerateFood()
        {
            Random random = new Random();
            bool genereruj = true;

            while (genereruj)
            {
                int x = random.Next(1, LeghtX - 1);
                int y = random.Next(1, LeghtY - 1);
                genereruj = false;
                foreach (BodyPart item in Snake)
                {
                    if (item.X == x && item.Y == y)
                    {
                        genereruj = true;
                    }
                }

                if (random.Next(0,8) == 1)
                    GameArray[x, y] = Box.poisonedFood;
                else 
                    GameArray[x, y] = Box.Food;

            }
        }

        public void zatoc(int[] directions)
        {

            if (NewHead(directions[0], directions[1]))
                DestroyLastBodyPart();
        }

        public bool live(BodyPart Head)
        {
            if (GameArray[Head.X, Head.Y] == Box.Wall || (GameArray[Head.X, Head.Y] == Box.Snake && Snake.Count > 1))
            {
                return false;
            }


            return true;

        }

    }
}
