package tree;

import java.util.ArrayList;

import tiles.Tile;

public class Node {
	ArrayList<Node> children;
	Node parent;
	Tile[][] configuration;	
	public Node(Tile[][] configuration){
		children = new ArrayList<Node>();
		this.configuration = configuration;
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
	public boolean hasChildren(){
		return children.size() > 0;
	}
}
