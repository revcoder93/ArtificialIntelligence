package tree;

import java.util.ArrayList;

import tiles.Tile;

public class Node {
	ArrayList<Node> children;
	Node parent;
	Tile[][] configuration;
	
	public Node(){
		children = new ArrayList<Node>();
	}
	public void addChild(Node n){
		children.add(n);
	}
	public ArrayList<Node> getChildren(){
		return children;
	}
	public void setParent(Node n){
		parent = n;
	}
	public Node getParent(){
		return parent;
	}
	public void setConfiguration(Tile[][] c){
		configuration = c;
	}
	public Tile[][] getConfiguration(){
		return configuration;
	}
}
