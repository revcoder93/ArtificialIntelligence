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
	ArrayList<Node> arrayListGreedy;

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
			iterativeDeepening(grid, visualize);
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

	private void greedy(Tile[][] grid, boolean visualize) {
		// TODO Auto-generated method stub

		Node nodeBfs = new Node(grid);
		Tree treeBfs = new Tree(nodeBfs);
		int index = 0;
		arrayListGreedy.add(treeBfs.getRoot());
		Node nRes = arrayListGreedy.get(0);
		while (!arrayListGreedy.isEmpty()) {
			Node n2;
			for (int i = 1; i < arrayListGreedy.size(); i++) {
				n2 = arrayListGreedy.get(i);
				if (nRes.getHeuristic() > n2.getHeuristic()) {
					nRes = arrayListGreedy.get(i);
					index = i;
				}
			}
			Node node = arrayListGreedy.remove(index);
			if (node.goalTest()) {
				System.out.println("the sequence using Breadth First Search is: ");
				result = fillResult(node, result);
				Collections.reverse(result);
				for (int i = 0; i < result.size(); i++) {
					result.get(i).print();
				}
				System.out.println("");
				children = null;
				result = null;
				return;
			} else {
				children = node.getChildren();
				for (int i = 0; i < children.size(); i++) {
					arrayListGreedy.add(children.get(i));
				}
			}
		}
	}

	private void aStar(Tile[][] grid, boolean visualize) {
		// TODO Auto-generated method stub
		Node nodeBfs = new Node(grid);
		Tree treeBfs = new Tree(nodeBfs);
		int index = 0;
		arrayListGreedy.add(treeBfs.getRoot());
		Node nRes = arrayListGreedy.get(0);
		while (!arrayListGreedy.isEmpty()) {
			Node n2;
			for (int i = 1; i < arrayListGreedy.size(); i++) {
				n2 = arrayListGreedy.get(i);
				if ((nRes.getHeuristic() + nRes.getPathCost()) > (n2.getHeuristic() + n2.getPathCost())) {
					nRes = arrayListGreedy.get(i);
					index = i;
				}
			}
			Node node = arrayListGreedy.remove(index);
			if (node.goalTest()) {
				System.out.println("the sequence using Breadth First Search is: ");
				result = fillResult(node, result);
				Collections.reverse(result);
				for (int i = 0; i < result.size(); i++) {
					result.get(i).print();
				}
				System.out.println("");
				children = null;
				result = null;
				return;
			} else {
				children = node.getChildren();
				for (int i = 0; i < children.size(); i++) {
					arrayListGreedy.add(children.get(i));
				}
			}
		}
	}

	private void iterativeDeepening(Tile[][] grid, boolean visualize) {
		// TODO Auto-generated method stub

		int limitNow = 0;
		Node nodeId = new Node(grid);
		Tree treeId = new Tree(nodeId);
		while (true) {
			int levels = 0;
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
					children = null;
					result = null;
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
			stackId.clear();
		}
	}

	private void depthFirstSearch(Tile[][] grid, boolean visualize) {
		// TODO Auto-generated method stub

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
				children = null;
				result = null;
				return;
			} else {
				children = node.getChildren();
				for (int i = 0; i < children.size(); i++) {
					stackDfs.push(children.get(i));
				}
			}
		}
	}

	private void breadthFirstSearch(Tile[][] grid, boolean visualize) {
		// TODO Auto-generated method stub

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
				children = null;
				result = null;
				return;
			} else {
				children = node.getChildren();
				for (int i = 0; i < children.size(); i++) {
					queueBfs.offer(children.get(i));
				}
			}
		}
	}

	// public static void main(String[] args) {
	// Node n = new Node(Main.genGrid());
	// Tree t = new Tree(n);
	// t.getRoot().print();
	// System.out.println("---------------------------------------------------------------");
	//
	// t.getRoot().getChildren().get(1).print();
	// }
}