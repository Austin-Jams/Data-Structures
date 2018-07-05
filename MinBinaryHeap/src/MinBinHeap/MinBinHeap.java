package MinBinHeap_A3;

public class MinBinHeap implements Heap_Interface {
  private EntryPair[] array; //load this array
  private int size;
  private static final int arraySize = 10000; //Everything in the array will initially 
                                              //be null. This is ok! Just build out 
                                              //from array[1]

  public MinBinHeap() {
    this.array = new EntryPair[arraySize];
    array[0] = new EntryPair(null, -100000); //0th will be unused for simplicity 
                                             //of child/parent computations...
                                             //the book/animation page both do this.
  }
    
  public EntryPair[] getHeap() { 
    return this.array;
  }


public void insert(EntryPair entry) {
	int pos = ++size;
	for(; pos > 0 && entry.priority < array[pos/2].priority; pos /= 2){
		array[pos] = array[pos/2];
	}
	array[pos] = entry;
	
}


public void delMin() {
if(size == 0){
	return;
}
array[1] = array[size--];
bubbleDown(1);
}


public EntryPair getMin() {
	
	return array[1]; 
	
}


public int size() {
	return size;
}


public void build(EntryPair[] entries) {
	size = entries.length;
	for(int i = 1; i <= entries.length; i++){
		array[i] = entries[i-1];
	}
	for(int i = size / 2; i > 0; i--){
		bubbleDown(i);
	}
	
	
}
private void bubbleDown(int i){
	int small;
	EntryPair tmp = array[i];
	for(; i * 2 <= size; i =small){
		small = i *2;
		if(small != size && array[small+1].priority < array[small].priority){
			small++;
		}
			if(array[small].priority < tmp.priority){
				array[i]=array[small];
			}else{
				break;
			}
		}
		array[i] = tmp;
	}
	
	
}


