package tree;

import java.util.ArrayList;

import tiles.Tile;

public class Tree {
	Node root;
	ArrayList<Node> stateSpace;

	public Tree(Node n) {
		this.root = n;
		stateSpace = new ArrayList<Node>();
		stateSpace.add(root);
		generateTree();
	}

	public void generateTree() {
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
						parent.addChild(n);
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
		if (j < configuration[i].length - 1) {
			temp = configuration[i][j + 1];
			if (temp.isBlock() || (temp.isPath() && !temp.isFixed())) {
				c2[i][j] = temp;
				c2[i][j + 1] = blank;
				ret.add(c2);
			}
		}
		return ret;
	}

	public Node getRoot() {
		return root;
	}
}