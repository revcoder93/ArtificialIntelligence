package main;

import tiles.Tile;
import tree.Node;
import tree.Tree;

public class RTB extends SearchProblem {

	@Override
	public void search(Tile[][] grid, String strategy, boolean visualize) {

		switch (strategy) {
		case "BFS":
			breadthFirstSearch(grid, visualize);
			break;
		case "DFS":
			depthFirstSearch(grid, visualize);
			break;
		case "ID":
			iterativeDeepening(grid, visualize);
			break;
		case "A*":
			aStar(grid, visualize);
		case "Greedy":
			greedy(grid, visualize);
		default:
			break;
		}
	}

	private void greedy(Tile[][] grid, boolean visualize) {
		// TODO Auto-generated method stub
		Node node = new Node(grid);
		Tree tree = new Tree(node);
		//et3amel w fe kolo bardo e3mel el 2 steps elly foo2 dool 3shan ykoon m3ak tree
		
	}

	private void aStar(Tile[][] grid, boolean visualize) {
		// TODO Auto-generated method stub
		
	}

	private void iterativeDeepening(Tile[][] grid, boolean visualize) {
		// TODO Auto-generated method stub

	}

	private void depthFirstSearch(Tile[][] grid, boolean visualize) {
		// TODO Auto-generated method stub

	}

	private void breadthFirstSearch(Tile[][] grid, boolean visualize) {
		// TODO Auto-generated method stub

	}
}
