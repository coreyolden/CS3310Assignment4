
public class MaxHeap {
	
	private Node root =null;
	
	/**insert each element from the insert array into the tree with the insertNode method and heapify. calculate total and individual insert time. calculate the lowest common ancestor 
	 * and the diameter.  
	 * 
	 * @param input
	 */
	public void insert(String[] input) {
		long insertTime = System.nanoTime();
		for(int i = 0; i<input.length; i++) {
			Node node = new Node();
				if(root == null) {
					node.setData(input[i]);
					node.setHeight(0);
					root = node;
				}else {
					node.setData(input[i]);
					insertNode(root,node);
					heapify(node);		
				}
		}
		insertTime = System.nanoTime()-insertTime;
		System.out.println("The average time to insert was "+(insertTime/input.length+" nanoseconds"));

		System.out.println("\nThe largest diameter is "+getDiameter());
		
		
		//to get lowest common ancestor put the names in the lcaFirst and lcaSecond variables.
		String lcaFirst = "LUKE";
		Node firstNode = searchInHeap(lcaFirst, root);
		String lcaSecond = "EMPEROR";
		Node secondNode = searchInHeap(lcaSecond, root);
		if(firstNode.getData().compareTo("notFound")==0||secondNode.getData().compareTo("notFound")==0) {
			System.out.println("you have to search names actually in the heap");
		}else {
		Node lca = getLCA(firstNode, secondNode);
		System.out.println("The lowest common ancestor between "+lcaFirst+" and "+lcaSecond+" is "+lca.getData());
		}
		
		
		
		System.out.println("\nThe in order postorder transversal of the tree is\n----------------------------");
		traverseHeap(root);
		
	}
	/**recursively insert the node. If the node you are looking at has no left node then set the node as a left child and set the height of the current node to 1.
	 * If the current node has no right child then set the node to the right child and set the height int to 1. If the node has left and right children then recursively
	 * call the children and have them return their heights to the height int and then compare it to the current nodes int and if greater then update the height
	 * of the current node and if not then do nothing with it. Return the current node.
	 * 
	 * @param current
	 * @param node
	 * @return
	 */
	private int insertNode(Node current, Node node) {
		int height=0;
		if(current.getLeft()==null) { //if the node has no left child then make the new node its left child
			current.setLeft(node);
			node.setParent(current);
			height=1;
		}else if(current.getRight()==null) { //if the node has no right child then make the new node its right child
			current.setRight(node);
			node.setParent(current);
			height=1;
		}else if(current.getRight().getHeight()<(current.getLeft().getHeight())-1){
			height=1+insertNode(current.getRight(), node);
		}else {
			height = 1+insertNode(current.getLeft(), node);
			
		}
		if(height>current.getHeight()) {
			current.setHeight(height);
		}
		return current.getHeight();
	}
	
	/**if the child is greater than the parent then swap the data 
	 * 
	 * @param node
	 */
	private void heapify(Node node) {
		//System.out.println(node.getData());
		if(node.getParent()!=null) {
		Node parent = node.getParent();
		if(node.getData().compareTo(parent.getData())>0) {
			String tempData = node.getData(); //get data from node
			node.setData(parent.getData()); //swap height
			parent.setData(tempData);
			heapify(parent);
		}
		}
		
	}
	
	/**for each name in the input array then search in the heap with the searchInHeap method and return a node. if the node has a value of "notFound" then the node
	 * was not found and print that out. If the node was found then check the height to see if its a leaf and check the depth with getDepth.
	 * print the individual search time and average search time. 
	 * 
	 * @param input
	 */
	public void search(String[] input) {
		long totalSearch = 0;
		for(int i = 0; i<input.length; i++) {
			System.out.println("\n\n\n\nSearching for "+input[i]);
			long singleSearch = System.nanoTime();
			Node node = searchInHeap(input[i], root);
			singleSearch = System.nanoTime()-singleSearch;
			totalSearch+=singleSearch;
			if(node.getData().compareTo("notFound")==0) {
				System.out.println("That was not found in the heap");
			}else {
				System.out.println("Found at a depth of "+getDepth(node));
				if(node.getHeight()==0) {
					System.out.println("The node is a leaf\nThe size of the subtree with this node as a root is 0");
				}else {System.out.println("The node is not a leaf\nThe size of the subtree with this node as a root is "+getSubTree(node));}
			}
			
			System.out.println("The time for the search was "+singleSearch+" nanoseconds");
		}
		totalSearch = totalSearch/input.length;
		System.out.println("\n\nThe average search time was "+totalSearch);
		
	}
	
	/**takes the current node and the search string as parameters. generate a new node with data of "notFound" and height of 9999999. If the string is found then height 
	 * will be set to lower than that and if not it'll keep looking. If multiple copies are found it will be taken care of due to the fact that it compares
	 * left and right child heights and only returns the lower one.
	 * 
	 * @param toFind
	 * @param node
	 * @return
	 */
	public Node searchInHeap(String toFind, Node node) {
		Node newnode = new Node();
		newnode.setData("notFound");
		newnode.setHeight(99999);
		if(node == null) {
			return newnode;
		}
		if(node.getData().compareTo(toFind)==0) {
			newnode = node;
		}
			Node nodeLeft=searchInHeap(toFind, node.getLeft());
			Node nodeRight=searchInHeap(toFind, node.getRight());
		
		
		if(nodeLeft.getData().compareTo(toFind)==0&&nodeRight.getData().compareTo(toFind)==0) {
			if(nodeLeft.getHeight()<nodeRight.getHeight()) {
				newnode = nodeLeft;
			}else {newnode = nodeRight;}
		}
		else if(nodeLeft.getData().compareTo(toFind)==0) {
			newnode = nodeLeft;
		}else if(nodeRight.getData().compareTo(toFind)==0) {
			newnode = nodeRight;
		}
		
		
		return newnode;
		
	}
	
	/**prints the heap in a post order traversal 
	 * 
	 * @param node
	 */
	private void traverseHeap(Node node) {
		if(node != null) {
		traverseHeap(node.getLeft());
		traverseHeap(node.getRight());
		System.out.println(node.getData());
		}
	}
	/**generates the size of the subtree with the found node as a root
	 * 
	 * @param node
	 * @return
	 */
	private int getSubTree(Node node) {
		int size = 0; 
		if(node != null) { //if the node is null it doesnt do this so it returns 0
			size=1;
			size+=getSubTree(node.getLeft());
			size+=getSubTree(node.getRight());
		}
		return size;
	}
	
	/**get the largest diameter possible by adding the height of the roots left and right children +2. because the tree is height balanced this should return
	 * the correct answer because there wont be any anomalies.
	 * 
	 * @return
	 */
	private int getDiameter() {
		int height=0;
		if(root.getLeft()==null) {
			
		}else {
		height += 1+root.getLeft().getHeight();
		if(root.getRight()!= null) {
		height += 1+root.getRight().getHeight();
		}
		}
		return height;
	}
	/**backs the deeper node up until the nodes are at the same level and then until the nodes are the same node make each node its parent which is the LCA.
	 * 
	 * @param first
	 * @param second
	 * @return
	 */
	private Node getLCA(Node first, Node second) {
		Node node;
		if(first.getHeight()<second.getHeight()) {
			while(first.getHeight()<second.getHeight()) {
				first = first.getParent();
			}
		}
		if(second.getHeight()<first.getHeight()) {
			while(second.getHeight()<first.getHeight()) {
				second = second.getParent();
			}
		}
		while(first!=second) {
			if(first.getParent()!=null) {
				first=first.getParent();}
				if(second.getParent()!=null) {
				second=second.getParent();
				}
		}
		node=first;
		return node;
	}
	/**takes the node as input and sets it to its parent until it hits the root. increment the depth counter each time it does this and return the depth counter 
	 * at the end.
	 * 
	 * @param current
	 * @return
	 */
	private int getDepth(Node current) {
		Node parent;
		int depth=0;
		while(current.getParent()!=null) {
			current = current.getParent();
			depth++;
		}
		return depth;
	}
}
