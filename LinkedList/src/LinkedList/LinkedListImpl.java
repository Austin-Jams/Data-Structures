package LinkedListA0;

public class LinkedListImpl implements LIST_Interface {
	  Node root;//this will be the entry point to your linked list (the head)
	  
	  public LinkedListImpl(){//this constructor is needed for testing purposes. Please don't modify!
	    root=new Node(0); //Note that the root's data is not a true part of your data set!
	  }
	  
	  //implement all methods in interface, and include the getRoot method we made for testing purposes. Feel free to implement private helper methods!
	  
	  public Node getRoot(){ //leave this method as is, used by the grader to grab your linkedList easily.
	    return root;
	  }

	public boolean insert(Node n, int index) {
		if (index < 0 || index > size()){
			return false;
		}
		else{
		Node current = root;
		for(int i=0 ; i < index; i++){
			current = current.next;
		}
		n.next = current.next;
		current.next = n;	
		return true;
		}
	}

	public boolean remove(int index) {
		if(index < 0 || index > size()){
			return false;
		}
		else{
		Node current = root;
			for(int i =0; i<index; i++){
				current = current.next;
				}
			current.next = current.getNext().getNext();
			return true;
			}
		}

	public Node get(int index) {
		Node current = root;
		for( int i = 0; i<index;i++){
			current.getNext();
		}
		return current.getNext();
	}
	public int size() {
		int size = 0;
		for(Node n = root; n.next != null; n = n.next){
			size++;
		}
		
		
		return size;
	}
	public boolean isEmpty() {
		if(size() == 0){
			return true;
			}
		return false;
		}
			
		
		
	
	public void clear() {
		Node current = root;
		current.next = null;
	}
	}
