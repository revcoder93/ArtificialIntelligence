package tree;

import java.util.ArrayList;

import main.Main;
import tiles.Tile;

public class Node {
	ArrayList<Node> children;
	Node parent;
	Tile[][] configuration;	
	public Node(Tile[][] configuration){
		children = new ArrayList<Node>();
		this.configuration = configuration;
		parent = null;
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
	
	public void print(){
		for (int i = 0; i < configuration.length; i++) {
			System.out.print("|   ");
			for (int j = 0; j < configuration[0].length; j++) {
				System.out.print(configuration[i][j].toString()+"   |   ");
			}
			System.out.println();
		}
	}
	public static void main(String[] args) {
		Node n = new Node(Main.genGrid());
		n.print();
	}
}
