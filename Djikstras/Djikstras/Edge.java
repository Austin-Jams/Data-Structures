package A6_Dijkstra;

public class Edge {
	
	public String edge_label;
	public String input_label;
	public String output_label;
	
	public long edge_weight;
	public long idNum;
	
	
	public Edge (long idNum, String edge_label, String input_Label, String output_Label, long edge_weight) {
		
		this.idNum = idNum;
		this.edge_label = edge_label;
		this.input_label = input_Label;
		this.output_label = output_Label;
		
		this.edge_weight = edge_weight;
	}
	
	public String getInputLabel() {
		return input_label;
	}

	public String getOutputLabel() {
		return output_label;
	}

	public long getId() {
		return idNum;
	}

	public String getLabel() {
		return edge_label;
	}

	public long getWeight() {
		return edge_weight;
	}


}