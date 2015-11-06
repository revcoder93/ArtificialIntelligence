package tree;

import java.util.ArrayList;

import main.Main;
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

	/* Generates all the states in the state space */
	public void generateTree() {
		for (int i = 0; i < stateSpace.size(); i++) {
			expand(stateSpace.get(i));
		}
	}

	/* Expands a node */
	public void expand(Node parent) {
		Tile[][] c1 = parent.getConfiguration();
		for (int i = 0; i < c1.length; i++) {
			for (int j = 0; j < c1[0].length; j++) {
				if (c1[i][j].isBlank()) {
					ArrayList<Tile[][]> moves = move(c1, i, j);
					// System.out.println(moves.size());
					for (int k = 0; k < moves.size(); k++) {
						Node n = new Node(moves.get(k));
						n.setParent(parent);
						parent.addChild(n);
						Node n1 = parent;
						/*
						 * Checks if a node that originated from a move has the
						 * same configuration as one of it's parents
						 */
						while (true) {
							if (n1 != null) {
								if (n1.isEqual(n.getConfiguration())) {
									break;
								} else {
									System.out.println("parent");
									n1 = n1.parent;
								}
							} else {
								stateSpace.add(n);
								System.out.println("Added");
								break;
							}
						}
					}
				}
			}
		}
	}

	/*
	 * Returns an array of all configurations generated from possible moves of a
	 * given configuration and position of a blank tile
	 */
	public ArrayList<Tile[][]> move(Tile[][] configuration, int i, int j) {
		Tile blank = configuration[i][j];
		Tile temp;
		Tile[][] c2 = configuration;
		ArrayList<Tile[][]> ret = new ArrayList<Tile[][]>();
		Node n = new Node(c2);
		n.print();
		System.out.println("==============================================x");
		if (i > 0) {
			temp = configuration[i - 1][j];
			if (temp.isBlock() || (temp.isPath() && !temp.isFixed())) {
				c2[i][j] = temp;
				c2[i - 1][j] = blank;
				ret.add(c2);
			}
			n = new Node(c2);
			n.print();
			System.out.println("==============================================y");
		}
		c2 = configuration;
		if (i < configuration.length - 1) {
			temp = configuration[i + 1][j];
			if (temp.isBlock() || (temp.isPath() && !temp.isFixed())) {
				c2[i][j] = temp;
				c2[i + 1][j] = blank;
				ret.add(c2);
			}
			n = new Node(c2);
			n.print();
			System.out.println("==============================================z");
		}
		c2 = configuration;
		if (j > 0) {
			temp = configuration[i][j - 1];
			if (temp.isBlock() || (temp.isPath() && !temp.isFixed())) {
				c2[i][j] = temp;
				c2[i][j - 1] = blank;
				ret.add(c2);
			}
			n = new Node(c2);
			n.print();
			System.out.println("==============================================w");
		}
		c2 = configuration;
		if (j < configuration[i].length - 1) {
			temp = configuration[i][j + 1];
			if (temp.isBlock() || (temp.isPath() && !temp.isFixed())) {
				c2[i][j] = temp;
				c2[i][j + 1] = blank;
				ret.add(c2);
			}
			n = new Node(c2);
			n.print();
			System.out.println("==============================================v");
		}
		return ret;
	}

	public Node getRoot() {
		return root;
	}

	public static void main(String[] args) {
		Tree t = new Tree(new Node(Main.genGrid()));
//		t.getRoot().print();
		// System.out.println("========================================================================================");
		// System.out.println(t.getRoot().getChildren().size());
		// System.out.println(t.stateSpace.size());
	}
}