package A6_Dijkstra;

public class EntryPair {
	
	public Node node;
	
	public String value;
	public int priority;
	

	public EntryPair(String value, int priority) {
		this.value = value;
		this.priority = priority;
	}

	public EntryPair(Node node) {
		value = node.getLabel();
		priority = (int)node.getLength();
		this.node = node;
	}

	public String getValue() {
		return value;
	}

	public int getPriority() {
		return priority;
	}

	public Node getNode() {
		return node;
	}
}