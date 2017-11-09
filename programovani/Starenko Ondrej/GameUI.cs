using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HadOlympiada
{
    class GameUI
    {
        public char[,] GameArray { get; set; }

        public char NothingDefinition { get; set; }

        public char  WallDefinition { get; set; }

        public char FoodDefiniton { get; set; }

        public char SnakeDefinition { get; set; }

        public char poisonedFoodDefinition { get; set; }

        public GameUI(int leghtX, int LeghtY)
        {
            NothingDefinition = ' ';
            WallDefinition = 'X';
            FoodDefiniton = 'o';
            SnakeDefinition = '#';
            poisonedFoodDefinition = 'q';
            

            GameArray = new char[leghtX, LeghtY];

            for (int i = 0; i < GameArray.GetLength(0); i++)
            {
                for (int j = 0; j < GameArray.GetLength(1); j++)
                {
                    GameArray[i, j] = NothingDefinition;
                }

            }
        }

        /// <summary>
        /// Vykreslí pole
        /// </summary>
        public void Show()
        {
            for (int i = 0; i < GameArray.GetLength(0); i++)
            {
                for (int j = 0; j < GameArray.GetLength(1); j++)
                {
                    Console.Write(GameArray[i, j]);
                }
                Console.WriteLine();
            }
        }

        /// <summary>
        /// Načte do pole správné data pro vykreslení
        /// </summary>
        /// <param name="array"></param>
        public void Refresh(Box[,] array)
        {
            for (int i = 0; i < GameArray.GetLength(0); i++)
            {
                for (int j = 0; j < GameArray.GetLength(1); j++)
                {
                    if (array[i, j] == Box.Nothing)
                        GameArray[i, j] = NothingDefinition;
                    else if (array[i, j] == Box.Wall)
                        GameArray[i, j] = WallDefinition;
                    else if (array[i, j] == Box.Food)
                        GameArray[i, j] = FoodDefiniton;
                    else if (array[i, j] == Box.Snake)
                        GameArray[i, j] = SnakeDefinition;
                    else if (array[i, j] == Box.poisonedFood)
                        GameArray[i, j] = poisonedFoodDefinition;
                }
                Console.WriteLine();
            }
        }

    }
}
