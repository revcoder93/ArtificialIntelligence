package tree;

import tiles.Tile;

public class Tree {
	Node root;
	public Tree(Node n){
		root = n;
	}
	public boolean isEqual(Tile[][] c1, Tile[][] c2) {
		for (int i = 0; i < c1.length; i++) {
			for (int j = 0; j < c1[i].length; j++) {
				Tile t1 = c1[i][j];
				Tile t2 = c2[i][j];
				if(t1.getId() != t2.getId()){
					return false;
				}
			}
		}
		return true;
	}
/*Moves and returns an arraylist of all possible moves*/
//	public ArrayList<Tile[][]> move(Tile[][] c1, int i, int j) {
//		
//	}
}
