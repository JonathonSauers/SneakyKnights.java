// Jonathon Sauers
// jo046326
// COP 3503, Fall 2017
// SneakyKnights.java

import java.io.*;
import java.util.*;

public class SneakyKnights
{
	// Given a pre-set chess board with all knights. Checks if they are all safe.
	public static boolean allTheKnightsAreSafe(ArrayList<String> coordinateStrings, int boardSize)
	{
		Integer colNum, total = 0, kCol, kRow;

		String temp, location;
		String[] tempArray;

		Character col;

		// Creates a HashMap with the letters a-z, and their relative places, 1-26.
		Map<Character, Integer> map = new HashMap<>();

		int i = 1;
		// Populates the HashMap. Format: (a,1),(b,2),...,(z,26)
		for(char letter = 'a'; letter <= 'z'; ++letter)
		{
			map.put(letter, i++);
		}

    Map<String, Integer> chessBoard = new HashMap<>();

		// Deals with the input.
		// col holds characters, row holds integers.
		for(i = 0; i < coordinateStrings.size(); i++)
		{
			// Input goes into temp, which is then split into an array.
			// tempArray[0] holds characters and tempArray[1] holds numbers.
			temp = coordinateStrings.get(i);
			tempArray = temp.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");
			total = 0;

			// Calculates the numerical value the column represents from the given characters.
			// Uses the value of the letter (a = 1, b = 2 ,..., z = 26), and multiplies
			// each by a power of 26 relative to the amount of characters given.
			// ie. Given 'abc' --> (1 * 26^2 + 2 * 26^1 + 3 * 26^0)
			for(int j = 0; j < tempArray[0].length(); j++)
			{
				col = tempArray[0].charAt(j);
				colNum = map.get(col);
				total = total + (int)(colNum * Math.pow(26, tempArray[0].length() - j - 1));
			}

			// Location is stored: col + "-" + row
			temp = Integer.toString(total) + "-" + tempArray[1];
			location = temp;
			kCol = total;
			kRow = Integer.parseInt(tempArray[1]);

			// Checks if safe by calling placeKnights which returns
			// true if safe, false if not safe.
			if(!placeKnights(chessBoard, location, kCol, kRow, boardSize))
				return false;
		}
		return true;
	}

	// Helper method called from placeKnights to check different scenerios.
	public static boolean checkAttack(Map<String, Integer> chessBoard, Integer col,
								Integer kCol, Integer row, Integer kRow, Integer num1,
								Integer num2, int boardSize)
	{
		// Check if out of bounds, followed by checking if the knight is safe.
		// Attacks are represented by a 0.
		// Safety is represented by a 1.

		String kAttack = Integer.toString(col) + "-" + Integer.toString(row);

		if(col + num1 < 0 || row + num2 >= boardSize){}

		else if(chessBoard.get(kAttack) == null)
			chessBoard.put(kAttack, 0);

		else if(chessBoard.get(kAttack) == 1)
			return false;

		return true;
	}

	public static boolean placeKnights(Map<String, Integer> chessBoard,
			 					String location, Integer kCol, Integer kRow, int boardSize)
	{
		Integer row = 0, col = 0;

		// Makes sure it is safe to put a knight on the board, by first checking the
		// center postion, followed by the different locations a knight can move.
		// Attacks are represented by a 0.
		// Safety is represented by a 1.

		// Checks if the center value is safe.
		if(chessBoard.get(location) == null)
			chessBoard.put(location, 1);
		else if(chessBoard.get(location) != null)
			if(chessBoard.get(location) == 0)
				return false;

		// The following checks if knights can attack by calling checkAttack.
		// checkAttack returns true if safe, false if not safe.
		// col and row are updated for each different scenerio to be checked.

		// Top right option 1.
		col = kCol + 1;
		row = kRow + 2;
		if(!checkAttack(chessBoard, col, kCol, row, kRow, 1, 2, boardSize))
			return false;

		// Top right option 2.
		col = kCol + 2;
		row = kRow + 1;
		if(!checkAttack(chessBoard, col, kCol, row, kRow, 2, 1, boardSize))
			return false;

		// Top left option 1.
		col = kCol + 2;
		row = kRow - 1;
		if(!checkAttack(chessBoard, col, kCol, row, kRow, 2, -1, boardSize))
			return false;

		// Top left option 2.
		col = kCol + 1;
		row = kRow - 2;
		if(!checkAttack(chessBoard, col, kCol, row, kRow, 1, -2, boardSize))
			return false;

		// Bottom left option 1.
		col = kCol - 1;
		row = kRow - 2;
		if(!checkAttack(chessBoard, col, kCol, row, kRow, -1, -2, boardSize))
			return false;

		// Bottom left option 2.
		col = kCol - 2;
		row = kRow - 1;
		if(!checkAttack(chessBoard, col, kCol, row, kRow, -2, -1, boardSize))
			return false;

		// Bottom right option 1.
		col = kCol - 2;
		row = kRow + 1;
		if(!checkAttack(chessBoard, col, kCol, row, kRow, -2, 1, boardSize))
			return false;

		// Bottom right option 2.
		col = kCol - 1;
		row = kRow + 2;
		if(!checkAttack(chessBoard, col, kCol, row, kRow, -1, 2, boardSize))
			return false;

		// If we reach this, then all is safe.
		return true;
	}

	// How difficult I found this assignment.
	public static double difficultyRating()
	{
		return 3.5;
	}

	// How long it took me to complete this assignment.
	public static double hoursSpent()
	{
		return 10.0;
	}
}
