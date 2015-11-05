package main;

import tiles.Tile;

public abstract class SearchProblem {
	public abstract void search(Tile[][] grid, String strategy, boolean visualize);
}
