package A6_Dijkstra;

public interface Heap_Interface {
  void insert(EntryPair entry);
  EntryPair delMin();
  EntryPair getMin();
  int size();
  void build(EntryPair [] entries);
  EntryPair[] getHeap();
}
