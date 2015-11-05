package tree;

import java.util.ArrayList;

import tiles.Tile;

public class Tree {
	Node root;

	public Tree(Node n) {
		root = n;
	}

	public boolean isEqual(Tile[][] c1, Tile[][] c2) {
		for (int i = 0; i < c1.length; i++) {
			for (int j = 0; j < c1[i].length; j++) {
				Tile t1 = c1[i][j];
				Tile t2 = c2[i][j];
				if (t1.getId() != t2.getId()) {
					return false;
				}
			}
		}
		return true;
	}
	/* Moves and returns an arraylist of all possible moves */
	 public ArrayList<Tile[][]> move(Tile[][] configuration, int i, int j) {
		 Tile blank = configuration[i][j];
		 Tile temp;
		 Tile[][] c2 = configuration;
		 ArrayList<Tile[][]> ret = new ArrayList<Tile[][]>();
		 if(i > 0){
			 temp = configuration[i-1][j];
			 if(temp.isBlock() || (temp.isPath() && !temp.isFixed())){
				 c2[i][j] = temp;
				 c2[i-1][j] = blank;
				 ret.add(c2);
			 }
		 }
		 c2 = configuration;
		 if(i < configuration.length-1){
			 temp = configuration[i+1][j];
			 if(temp.isBlock() || (temp.isPath() && !temp.isFixed())){
				 c2[i][j] = temp;
				 c2[i+1][j] = blank;
				 ret.add(c2);
			 }
		 }
		 c2 = configuration;
		 if(j > 0){
			 temp = configuration[i][j-1];
			 if(temp.isBlock() || (temp.isPath() && !temp.isFixed())){
				 c2[i][j] = temp;
				 c2[i][j-1] = blank;
				 ret.add(c2);
			 }
		 }
		 c2 = configuration;
		 if(i < configuration[i].length-1){
			 temp = configuration[i][j+1];
			 if(temp.isBlock() || (temp.isPath() && !temp.isFixed())){
				 c2[i][j] = temp;
				 c2[i][j+1] = blank;
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
				if ((currentTileI + 1) > configuration.length) {
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
