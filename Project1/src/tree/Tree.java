package tree;

import java.util.ArrayList;

import tiles.Tile;

public class Tree {
	Node root;
	ArrayList<Node> stateSpace;
	public Tree(Node n) {
		root = n;
		stateSpace = new ArrayList<Node>();
		stateSpace.add(root);
		generateTree();
	}
	public void generateTree(){
		for (int i = 0; i < stateSpace.size(); i++) {
			expand(stateSpace.get(i));
		}
	}
	public void expand(Node parent) {
		Tile[][] c1 = parent.getConfiguration();
		for (int i = 0; i < c1.length; i++) {
			for (int j = 0; j < c1[0].length; j++) {
				if (c1[i][j].isBlank()) {
					ArrayList<Tile[][]> moves = move(c1, i, j);
					for (int k = 0; k < moves.size(); k++) {
						Node n = new Node(moves.get(k));
						n.setParent(parent);
						Node n1 = parent;
						while (true) {
							if (n1 != null) {
								if (n1.isEqual(n.getConfiguration())) {
									break;
								} else {
									n1 = n1.parent;
								}
							} else {
								stateSpace.add(n);
								break;
							}
						}
					}
				}
			}
		}
	}

	/* Moves and returns an arraylist of the new configurations */
	public ArrayList<Tile[][]> move(Tile[][] configuration, int i, int j) {
		Tile blank = configuration[i][j];
		Tile temp;
		Tile[][] c2 = configuration;
		ArrayList<Tile[][]> ret = new ArrayList<Tile[][]>();
		if (i > 0) {
			temp = configuration[i - 1][j];
			if (temp.isBlock() || (temp.isPath() && !temp.isFixed())) {
				c2[i][j] = temp;
				c2[i - 1][j] = blank;
				ret.add(c2);
			}
		}
		c2 = configuration;
		if (i < configuration.length - 1) {
			temp = configuration[i + 1][j];
			if (temp.isBlock() || (temp.isPath() && !temp.isFixed())) {
				c2[i][j] = temp;
				c2[i + 1][j] = blank;
				ret.add(c2);
			}
		}
		c2 = configuration;
		if (j > 0) {
			temp = configuration[i][j - 1];
			if (temp.isBlock() || (temp.isPath() && !temp.isFixed())) {
				c2[i][j] = temp;
				c2[i][j - 1] = blank;
				ret.add(c2);
			}
		}
		c2 = configuration;
		if (i < configuration[i].length - 1) {
			temp = configuration[i][j + 1];
			if (temp.isBlock() || (temp.isPath() && !temp.isFixed())) {
				c2[i][j] = temp;
				c2[i][j + 1] = blank;
				ret.add(c2);
			}
		}
		return ret;
	}

	public boolean goalTest(Tile[][] configuration) {
		// search for the initial tile
		Tile initialTile = null;
		int initialTileI = 0;
		int initialTileJ = 0;
		for (int i = 0; i < configuration.length; i++) {
			for (int j = 0; j < configuration[i].length; j++) {
				if (configuration[i][j].isInitial()) {
					initialTile = configuration[i][j];
					initialTileI = i;
					initialTileJ = j;
					break;
				}
			}
		}
		if (initialTile == null)
			return false;

		// iterate to find if they are connected
		Tile currentTile = initialTile;
		int currentTileI = initialTileI;
		int currentTileJ = initialTileJ;
		while (true) {
			String direction = currentTile.getOut();
			switch (direction) {
			case "North":
				if ((currentTileI - 1) > 0) {
					if (configuration[currentTileI - 1][currentTileJ].getIn().equals("South")) {
						if (configuration[currentTileI - 1][currentTileJ].isGoal())
							return true;
						currentTile = configuration[currentTileI - 1][currentTileJ];
						currentTileI = currentTileI - 1;
						continue;
					} else {
						return false;
					}
				} else {
					return false;
				}
			case "South":
				if ((currentTileI + 1) < configuration.length) {
					if (configuration[currentTileI + 1][currentTileJ].getIn().equals("North")) {
						if (configuration[currentTileI + 1][currentTileJ].isGoal())
							return true;
						currentTile = configuration[currentTileI + 1][currentTileJ];
						currentTileI = currentTileI + 1;
						continue;
					} else {
						return false;
					}
				} else {
					return false;
				}
			case "East":
				if ((currentTileJ + 1) < configuration[currentTileI].length) {
					if (configuration[currentTileI][currentTileJ + 1].getIn().equals("West")) {
						if (configuration[currentTileI][currentTileJ + 1].isGoal())
							return true;
						currentTile = configuration[currentTileI][currentTileJ + 1];
						currentTileJ = currentTileJ + 1;
						continue;
					} else {
						return false;
					}
				} else {
					return false;
				}
			case "West":
				if ((currentTileJ - 1) > 0) {
					if (configuration[currentTileI][currentTileJ - 1].getIn().equals("East")) {
						if (configuration[currentTileI][currentTileJ - 1].isGoal())
							return true;
						currentTile = configuration[currentTileI][currentTileJ - 1];
						currentTileJ = currentTileJ - 1;
						continue;
					} else {
						return false;
					}
				} else {
					return false;
				}
			}
		}
	}
}