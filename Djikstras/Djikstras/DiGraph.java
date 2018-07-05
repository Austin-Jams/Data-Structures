package A6_Dijkstra;

import java.util.*;

public class DiGraph implements DiGraph_Interface {
	ArrayList<Edge> edges_arrayList = new ArrayList<Edge>();
	ArrayList<Node> vertices_arrayList = new ArrayList<Node>();

	public int edge_size;
	public int node_size;

	public DiGraph() { 
	}

	public boolean addNode(long idNum, String label) {
		if (idNum < 0 || label == null) {
			
			return false;
		}

		for (int i = 0; i < vertices_arrayList.size(); i++) {
			
			Node holder;
			holder = vertices_arrayList.get(i);
			
			if (idNum == holder.getId() || label == holder.getLabel()) {
				return false;
			}
		}

		Node node = new Node(idNum, label);
		
		vertices_arrayList.add(node);
		
		node_size++;
		
		return true;
	}

	public boolean delNode(String label) {
		
		for (int i = 0; i < vertices_arrayList.size(); i++) {
			if (label == vertices_arrayList.get(i).getLabel()) {
				vertices_arrayList.remove(i);
				
				node_size--;
				return true;
			}
		}
		
		return false;
	}

	
	public boolean addEdge(long idNum, String input_label, String output_label, long weight, String edge_label) {
		
		boolean has_input = false;
		
		boolean has_output = false;

		if (idNum < 0) {
			return false;
		}

		for (int i = 0; i < edges_arrayList.size(); i++) {
			Edge holder;
			holder = edges_arrayList.get(i);
			if (idNum == holder.getId()) {
				return false;
			}
		}

		for (int i = 0; i < vertices_arrayList.size(); i++) {
			
			Node holder;
			holder = vertices_arrayList.get(i);

			if (input_label == holder.getLabel()) {
				has_input = true;
			}

			if (output_label == holder.getLabel()) {
				has_output = true;
			}
		}

		if (has_input == false || has_output == false) {
			return false;
			
		}

		for (int i = 0; i < edges_arrayList.size(); i++) {
			if (input_label == edges_arrayList.get(i).getInputLabel() && output_label == edges_arrayList.get(i).getOutputLabel()) {
				return false;
				
			}
		}

		
		Edge edge = new Edge(idNum, edge_label, input_label, output_label, weight);
		
		edges_arrayList.add(edge);
		edge_size++;

		for (int i = 0; i < vertices_arrayList.size(); i++) {
			if (output_label == vertices_arrayList.get(i).getLabel()) {
				vertices_arrayList.get(i).incrementDegree();
			}
		}

		return true;
	}
	
	public boolean delEdge(String input_label, String output_label) {
		
		for (int i = 0; i < edges_arrayList.size(); i++) {
			if (input_label == edges_arrayList.get(i).getInputLabel() && output_label == edges_arrayList.get(i).getOutputLabel()) {
				edges_arrayList.remove(i);
				edge_size--;

				for (int l = 0; l < vertices_arrayList.size(); l++) {
					if (output_label == vertices_arrayList.get(l).getLabel()) {
						vertices_arrayList.get(l).decrementDegree();
					}
				}

				return true;
			}
		}
		return false;
		
	}

	
	public long numNodes() {
		return node_size;
	}

	
	public long numEdges() {
		return edge_size;
	}

	//A5 topo sort
	
	public String[] topoSort() {
		
		ArrayList<String> sort = new ArrayList<String>();
		
		ArrayList<Node> node_test = new ArrayList<Node>();
		ArrayList<Edge> edge_test = new ArrayList<Edge>();

		for (int i = 0; i < vertices_arrayList.size(); i++) {
			node_test.add(vertices_arrayList.get(i));
		}

		for (int i = 0; i < edges_arrayList.size(); i++) {
			edge_test.add(edges_arrayList.get(i));
		}

		int count = 0;
		
		while (sort.size() < vertices_arrayList.size()) {
			count++;
			for (int i = 0; i < node_test.size(); i++) {
				boolean father = false;

				for (int b = 0; b < edge_test.size(); b++) {
					if (edge_test.get(b).getInputLabel().compareTo(node_test.get(i).getLabel()) == 0) {
					
						father = true;
					}
				}

				if (father == false) {
					sort.add(node_test.get(i).getLabel());
					for (int j = 0; j < edge_test.size(); j++) {
						if (edge_test.get(j).getOutputLabel().compareTo(node_test.get(i).getLabel()) == 0) {
							edge_test.remove(j);
							j--;
						}
					}
					node_test.remove(i);
					i = 0;

				}

			}
			
			if (count > vertices_arrayList.size()) {
				return null;
			}

		}

		String[] topological_sort = new String[sort.size()];

		for (int i = 0; i < sort.size(); i++) {
			topological_sort[i] = sort.get(i);
		}

		return topological_sort;
	}

	public Node labelToNode(String label) {
		Node node = null;
		
		for (int i = 0; i < vertices_arrayList.size(); i++) {
			
			if (vertices_arrayList.get(i).getLabel() == label) {
				node = vertices_arrayList.get(i);
			}
		}
		return node;
	}


	public ShortestPathInfo[] shortestPath(String label) {

		Node startVert = labelToNode(label);
		
		
		MinBinHeap priority_queue = new MinBinHeap();
		
		ArrayList<ShortestPathInfo> shortest_path = new ArrayList<ShortestPathInfo>();


		for (int i = 0; i < vertices_arrayList.size(); i++) {
			vertices_arrayList.get(i).setLength(Integer.MAX_VALUE);
			vertices_arrayList.get(i).hasBeenVisited(false);
		}

		
		startVert.setLength(0);

		
		for (int i = 0; i < vertices_arrayList.size(); i++) {
			Node currentent = vertices_arrayList.get(i);

			for (int j = 0; j < edges_arrayList.size(); j++) {
				if (currentent.getLabel() == edges_arrayList.get(j).getInputLabel()) {
					currentent.addPointer(labelToNode(edges_arrayList.get(j).getOutputLabel()));
				}
			}
		}


		priority_queue.insert(new EntryPair(startVert));

		while (priority_queue.isEmpty() == false) {

			Node current = priority_queue.getMin().getNode();
			long current_length = current.getLength();
			
			priority_queue.delMin();
			
			current.hasBeenVisited(true);
			
			shortest_path.add (new ShortestPathInfo (current.getLabel(), current.getLength()));

		
			for (int i = 0; i < current.getPointers().size(); i++) {

				Node node_pointer = current.getPointers().get(i);
				
				long edge_weight = 0;

				

				for (int j = 0; j < edges_arrayList.size(); j++) {
					if (current.getLabel() == edges_arrayList.get(j).getInputLabel()
							&& node_pointer.getLabel() == edges_arrayList.get(j).getOutputLabel()) {
						
						edge_weight = edges_arrayList.get(j).getWeight();
					}
				}


				if ((node_pointer.getLength() > (current_length + edge_weight)) && (node_pointer.getHasBeenVisited() == false)) {
					
						node_pointer.setLength(current_length + edge_weight);
						
						priority_queue.insert(new EntryPair(node_pointer));
				}
			}
		}

		ShortestPathInfo[] temp_array = new ShortestPathInfo[vertices_arrayList.size()];

		for (int i = 0; i < vertices_arrayList.size(); i++) {
			
			int verify = 0;
			
			for (int j = 0; j < shortest_path.size(); j++) {
				if (vertices_arrayList.get(i).getLabel() == shortest_path.get(j).getDest()) {
					
					verify = 1;
				}
			}

			if (verify == 0) {
			
				vertices_arrayList.get(i).hasBeenVisited(true);
				vertices_arrayList.get(i).setLength(-1);
				
				temp_array[i] = new ShortestPathInfo(vertices_arrayList.get(i).getLabel(), vertices_arrayList.get(i).getLength());
			}
		}

		for (int i = 0; i < vertices_arrayList.size(); i++) {
			for (int j = 0; j < shortest_path.size(); j++) {
				if (shortest_path.get(j).getDest() == vertices_arrayList.get(i).getLabel()) {
					temp_array[i] = shortest_path.get(j);
				}
			}
		}

		return temp_array;

	}


}

