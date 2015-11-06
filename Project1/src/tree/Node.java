package tree;

import java.util.ArrayList;

import main.Main;
import tiles.Tile;

public class Node {
	ArrayList<Node> children;
	Node parent;
	Tile[][] configuration;

	public Node(Tile[][] configuration) {
		children = new ArrayList<Node>();
		this.configuration = configuration;
		parent = null;
	}

	public void addChild(Node n) {
		children.add(n);
	}

	public ArrayList<Node> getChildren() {
		return children;
	}

	public void setParent(Node n) {
		parent = n;
	}

	public Node getParent() {
		return parent;
	}

	public void setConfiguration(Tile[][] c) {
		configuration = c;
	}

	public Tile[][] getConfiguration() {
		return configuration;
	}

	public boolean hasChildren() {
		return children.size() > 0;
	}
	/*Checks if a node's configuration is equal to another's*/
	public boolean isEqual(Tile[][] c1) {
		for (int i = 0; i < c1.length; i++) {
			for (int j = 0; j < c1[i].length; j++) {
				Tile t1 = c1[i][j];
				Tile t2 = this.getConfiguration()[i][j];
				if (t1.getId() != t2.getId()) {
					return false;
				}
			}
		}
		return true;
	}
	/*Prints the configuration of the node*/
	public void print() {
		for (int i = 0; i < configuration.length; i++) {
			System.out.print("|");
			for (int j = 0; j < configuration[0].length; j++) {
				System.out.print(configuration[i][j].toString() + "|");
			}
			System.out.println();
			System.out.println();
		}
	}
	/*Tests if the current node has a configuration that is a goal*/
	public boolean goalTest() {
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
		// Check if there's no initial tile
		if (initialTile == null)
			return false;

		// Iterate to check if there's a path from the initial tile to the goal tile
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

	public static void main(String[] args) {
		Node n = new Node(Main.genGrid());
		n.print();
	}
}
