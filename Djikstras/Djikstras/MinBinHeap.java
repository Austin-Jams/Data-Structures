package A6_Dijkstra;


public class MinBinHeap implements Heap_Interface {
	private EntryPair[] array; // load this array
	private int size;
	private static final int arraySize = 10000; 
	public MinBinHeap() {
		this.array = new EntryPair[arraySize];
		array[0] = new EntryPair(null, -100000); 
	}
	public EntryPair[] getHeap() {
		return this.array;
	}
	public void insert(EntryPair entry) {

		int hole = ++size;


		for (array[0] = entry; entry.getPriority() < (array[hole / 2].getPriority()); hole /= 2) {

			array[hole] = array[hole / 2];

		}

		
		array[hole] = entry;

	}

	
	public EntryPair delMin() {

		EntryPair minItem = getMin();
		array[1] = array[size--];
		percolateDown(1);
		return minItem;

	}

	
	
	
	private void percolateDown(int hole) {
		
		
		
		int child;
		EntryPair temporary = array[hole];

		for (; hole * 2 <= size; hole = child) {
			child = hole * 2;
			if (child != size && array[child + 1].getPriority() < array[child].getPriority()) {
				child++;
			}
			if (array[child].getPriority() < temporary.getPriority()) {
				array[hole] = array[child];
			} else {
				break;
			}
		}
		array[hole] = temporary;
	}

	
	

	public EntryPair getMin() {
		if (size == 0) {
			return null;
		}
		return array[1];
	}

	public int size() {

		return this.size;
	}

	public void build(EntryPair[] entries) {

		size = entries.length;

		int i = 1;
		for (EntryPair entry : entries) {
			array[i++] = entry;
		}
		buildHeap();
	}

	private void buildHeap() {
		for (int i = size / 2; i > 0; i--) {
			percolateDown(i);
		}
	}



	public boolean isEmpty() {
		if (size == 0) {
			return true;
		} else {
		return false;
		}
	}

}