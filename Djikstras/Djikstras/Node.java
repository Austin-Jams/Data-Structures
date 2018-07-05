package A6_Dijkstra;

import java.util.*;

public class Node {

	public long idNum;
	public String label;
	
	public long length;
	public int degree;
	
	boolean been_visited;
	
	ArrayList<Node> reference = new ArrayList<Node>();

	public Node (long idNum, String label) {
		this.idNum = idNum;
		this.label = label;
	}

	public void addInfo (long idNum, String label) {
		this.idNum = idNum;
		this.label = label;
	}

	public long getId() {
		return idNum;
	}

	public String getLabel() {
		return label;
	}

	public int getDegree() {
		return degree;
	}

	public void setLength(long distance) {
		length = distance;
	}

	public void hasBeenVisited(boolean a) {
		been_visited = a;
	}

	public long getLength() {
		return length;
	}

	public boolean getHasBeenVisited() {
		return been_visited;
	}

	public void addPointer(Node v) {
		reference.add(v);
	}

	public ArrayList<Node> getPointers() {
		return reference;
	}
	
	public void incrementDegree() {
		degree++;
	}

	public void decrementDegree() {
		degree--;
	}

}