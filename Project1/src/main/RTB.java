package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;
import java.util.Stack;
import tiles.Tile;
import tree.Node;
import tree.Tree;

public class RTB extends SearchProblem {
	ArrayList<Node> children;
	ArrayList<Node> result;
	Queue<Node> queueBfs;
	Stack<Node> stackDfs;
	Stack<Node> stackId;
	int limit;

	public RTB(Node node, String strategy, boolean visualize) {
		search(node.getConfiguration(), strategy, visualize);
	}

	public ArrayList<Node> fillResult(Node x, ArrayList<Node> a) {
		if (x.getParent() == null) {
			a.add(x.getParent());
			return a;
		} else {
			a.add(x.getParent());
			fillResult(x.getParent(), a);
		}
		return a;
	}

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
			iterativeDeepening(grid, visualize, limit);
			break;
		case "A*":
			aStar(grid, visualize);
			break;
		case "Greedy":
			greedy(grid, visualize);
			break;
		default:
			break;
		}
	}

	private void iterativeDeepening(Tile[][] grid, boolean visualize, int limit) {
		int limitNow = 0;
		while (limitNow <= limit) {
			int levels = 0;
			Node nodeId = new Node(grid);
			Tree treeId = new Tree(nodeId);
			stackId.push(treeId.getRoot());
			while (!stackDfs.isEmpty()) {
				Node node = (Node) stackId.pop();
				if (node.goalTest()) {
					System.out.println("the sequence using Breadth First Search is: ");
					result = fillResult(node, result);
					Collections.reverse(result);
					for (int j = 0; j < result.size(); j++) {
						result.get(j).print();
					}
					System.out.println("");

					return;
				} else {
					if (levels < limitNow) {
						children = node.getChildren();
						levels++;
						for (int k = 0; k < children.size(); k++) {
							stackId.push(children.get(k));
						}
					}
				}
			}
			limitNow++;
		}
		children = null;
		result = null;
	}

	private void depthFirstSearch(Tile[][] grid, boolean visualize) {
		Node nodeDfs = new Node(grid);
		Tree treeDfs = new Tree(nodeDfs);
		stackDfs.push(treeDfs.getRoot());
		while (!stackDfs.isEmpty()) {
			Node node = (Node) stackDfs.pop();
			if (node.goalTest()) {
				System.out.println("the sequence using Breadth First Search is: ");
				result = fillResult(node, result);
				Collections.reverse(result);
				for (int i = 0; i < result.size(); i++) {
					result.get(i).print();
				}
				System.out.println("");
				return;
			} else {
				children = node.getChildren();
				for (int i = 0; i < children.size(); i++) {
					stackDfs.push(children.get(i));
				}
			}
			break;
		}
		children = null;
		result = null;
	}

	private void breadthFirstSearch(Tile[][] grid, boolean visualize) {
		Node nodeBfs = new Node(grid);
		Tree treeBfs = new Tree(nodeBfs);
		queueBfs.offer(treeBfs.getRoot());
		while (!queueBfs.isEmpty()) {
			Node node = (Node) queueBfs.poll();
			if (node.goalTest()) {
				System.out.println("the sequence using Breadth First Search is: ");
				result = fillResult(node, result);
				Collections.reverse(result);
				for (int i = 0; i < result.size(); i++) {
					result.get(i).print();
				}
				System.out.println("");
				return;
			} else {
				children = node.getChildren();
				for (int i = 0; i < children.size(); i++) {
					queueBfs.offer(children.get(i));
				}
			}
		}
		children = null;
		result = null;
	}
	
	private void greedy(Tile[][] grid, boolean visualize) {
		// TODO Auto-generated method stub

	}

	private void aStar(Tile[][] grid, boolean visualize) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		Node n = new Node(Main.genGrid());
		Tree t = new Tree(n);
		t.getRoot().print();
		System.out.println("---------------------------------------------------------------");
		
		if(!t.getRoot().getChildren().isEmpty())
		t.getRoot().getChildren().get(1).print();
	}
}