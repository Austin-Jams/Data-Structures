package SPLT_A4;

public class SPLT implements SPLT_Interface {
	private BST_Node root;
	private int size;

	public SPLT() {
		this.size = 0;
	}

	public BST_Node getRoot() { 
		return root;
	}

	
	private void splay(String node) {

		BST_Node r = root;
		BST_Node par, g, gg;
		par = g = gg = null;
		boolean ggBool = true;

		while (true) {
			if (r == null || node.compareTo(r.getData()) == 0) {
				break;

			} else if (r.left != null && node.compareTo(r.getData()) < 0) {

				if (node.compareTo(r.left.getData()) == 0) {
					r = rotateLeft(r);
				}

				else if (r.left.left != null && node.compareTo(r.left.left.getData()) == 0) {
					g = r;
					par = r.left;
					r = rotateLeft(g);
					r = rotateLeft(par);
					ggBool = true;
				}

				else if (r.left.right != null && node.compareTo(r.left.right.getData()) == 0) {
					g = r;
					par = r.left;
					g.left = rotateRight(par);
					r = rotateLeft(g);
					ggBool = true;
				}

		
				else if (node.compareTo(r.getData()) < 0) {
					gg = r;
					r = r.left;
				}

	
			} else if (r.right != null && node.compareTo(r.getData()) > 0) {

				
				if (node.compareTo(r.right.getData()) == 0) {
					r = rotateRight(r);
				}

		
				else if (r.right.right != null && node.compareTo(r.right.right.getData()) == 0) {
					g = r;
					par = r.right;
					r = rotateRight(g);
					r = rotateRight(par);
					ggBool = true;
				}

				// zig-zag
				else if (r.right.left != null && node.compareTo(r.right.left.getData()) == 0) {
					g = r;
					par = r.right;
					g.right = rotateLeft(par);
					r = rotateRight(g);
					ggBool = true;
				}

				// neither
				else if (node.compareTo(r.getData()) > 0) {
					gg = r;
					r = r.right;
				}

				// neither imbalance
			} else if ((r.left == null && node.compareTo(r.getData()) < 0)
					|| (r.right == null && node.compareTo(r.getData()) > 0)) {
				node = r.getData();
				r = root;
				gg = null;
			}

			if (ggBool && gg != null) {
				int compare = r.getData().compareTo(gg.getData());
				if (compare < 0) {
					gg.left = r;
				} else if (compare > 0) {
					gg.right = r;
				}
				r = root;
				gg = null;
				ggBool = false;
			}
		}
		root = r;
	}

	private BST_Node rotateLeft(BST_Node t) {
		BST_Node newT = t.left;
		t.left = newT.right;
		newT.right = t;
		return newT;
	}

	private BST_Node rotateRight(BST_Node t) {
		BST_Node newT = t.right;
		t.right = newT.left;
		newT.left = t;
		return newT;
	}

	public void insert(String node_text) {
		if (root == null) {
			root = new BST_Node(node_text);
			size++;
		}
		if (root.insertNode(node_text)) {
			splay(node_text);
			size++;
		} else if (root.insertNode(node_text) == false) {
			splay(node_text);
		}
	}

	public void remove(String node_text) {
		if (this.contains(node_text) != false) {
			splay(node_text);
			root.removeNode(node_text);
			size--;
			if (size == 0) {
				root = null;
			}
		}
	}

	public boolean empty() {
		return size == 0;
	}

	public boolean contains(String node_text) {
		if (this.empty()) {
			return false;
		}
		splay(node_text);
		return root.containsNode(node_text);

	}

	public int size() {
		return size;
	}

	public int height() {
		if (root == null) {
			return -1;
		} else {
			return root.getHeight();
		}
	}

	public String findMin() {
		if (root == null) {
			return null;
		}

		if (root != null) {
			while (root.left != null) {
				root = root.left;
			}
			splay(root.getData());
			return root.getData();
		}

		return root.findMin().data;
	}

	public String findMax() {
		if (root == null) {
			return null;
		}

		if (root != null) {
			while (root.right != null) {
				root = root.right;
			}
			splay(root.getData());
			return root.getData();
		}

		return root.findMax().data;
	}

}

	  


