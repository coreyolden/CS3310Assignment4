
public class MaxHeap {
	
	private Node root =null;
	
	public void insert(String[] input) {
		for(int i = 0; i<input.length; i++) {
			Node node = new Node();
				if(root == null) {
					node.setData(input[i]);
					node.setHeight(0);
					root = node;
				}else {
					node.setData(input[i]);
					insertNode(root,node);
				}
		}
		
		System.out.println(root.getData());
		
		traverseHeap(root);
		
	}
	private int insertNode(Node parent, Node node) {
		int height=0;
		if(parent.getLeft()==null) { //if the node has no left child then make the new node its left child
			parent.setLeft(node);
			node.setParent(parent);
			height=1;
			heapify(node);
		}else if(parent.getRight()==null) { //if the node has no right child then make the new node its right child
			parent.setRight(node);
			node.setParent(parent);
			height=1;
			heapify(node);
		}else if(parent.getRight().getHeight()<(parent.getLeft().getHeight())){
			height = 1+insertNode(parent.getRight(), node);
		}else {
			height = 1+insertNode(parent.getLeft(), node);
		}
		parent.setHeight(height);
		
		return parent.getHeight();
	}
	private void heapify(Node node) {
		//System.out.println(node.getData());
		if(node.getParent()!=null) {
		Node parent = node.getParent();
		if(node.getData().compareTo(parent.getData())==0) {
		}else if(node.getData().compareTo(parent.getData())>0) {
			String tempData = node.getData();
			int tempheight = node.getHeight();
			node.setHeight(parent.getHeight());
			node.setData(parent.getData());
			parent.setData(tempData);
			parent.setHeight(tempheight);
			heapify(parent);
		}
		}
	}
	public void search(String[] input) {
		
	}
	private void traverseHeap(Node node) {
		if(node != null) {
		traverseHeap(node.getLeft());
		traverseHeap(node.getRight());
		System.out.println(node.getData()+" height "+node.getHeight());
		}
	}
	
	
	
}
