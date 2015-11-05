package main;

import tiles.Tile;

public class Main {
	public static Tile[][] genGrid() {
		int width = 0;
		int height = 0;
		while (true) {
			width = (int) ((Math.random() * 10) % 5) + 1;
			height = (int) ((Math.random() * 10) % 5) + 1;
			if (width > 2 && height > 2) {
				break;
			}
		}
		System.out.println(width + ", " + height + "\n");
		Tile[][] grid = new Tile[height][width];
		int iLocation = (int) ((Math.random() * 10) % height);
		int jLocation = (int) ((Math.random() * 10) % width);
		grid[iLocation][jLocation] = new Tile(4, 1);
		while (true) {
			iLocation = (int) ((Math.random() * 10) % height);
			jLocation = (int) ((Math.random() * 10) % width);
			if(grid[iLocation][jLocation] == null){
			grid[iLocation][jLocation] = new Tile(5, 2);
			break;
			}
		}
		int id = 3;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (grid[i][j] == null) {
					int randType = (int) ((Math.random() * 10) % 4);
					grid[i][j] = new Tile(randType, id);
					id++;
				}
			}
		}
		return grid;
	}

	// public static void main(String[] args) {
	// genGrid();
	// }
}
