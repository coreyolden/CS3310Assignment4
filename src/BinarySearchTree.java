
public class BinarySearchTree {

	private Node root =null;
	
	/**insert each element from the insert array with the insert node method calculate total and individual insert time. calculate the lowest common ancestor 
	 * and the diameter.  
	 * 
	 * @param arr
	 */
	public void insert(String[] arr) {
		int middle = arr.length/2;
		Node newnode = new Node();
		newnode.setData(arr[middle]);
		newnode.setHeight(0);
		root=newnode;
		long totalInsert = 0;
		for(int i=0; i<arr.length; i++) {
			
			if(i!=middle) {
				Node node = new Node();
				node.setData(arr[i]);
				node.setHeight(0);
				long singleInsert = System.nanoTime();
				insertNode(node,root);
				singleInsert=System.nanoTime()-singleInsert;
				totalInsert+=singleInsert;
			}
		}
		
		totalInsert = totalInsert/arr.length;
		System.out.println("The average time to insert one name was "+totalInsert+" Nanoseconds");
		//to get lowest common ancestor put the names in the lcaFirst and lcaSecond variables.
				String lcaFirst = "LUKE";
				Node firstNode = searchInTree(lcaFirst, root);
				String lcaSecond = "YODA";
				Node secondNode = searchInTree(lcaSecond, root);
				if(firstNode.getData().compareTo("notFound")==0||secondNode.getData().compareTo("notFound")==0) {
					System.out.println("you have to search names actually in the heap");
				}else {
				Node lca = getLCA(firstNode, secondNode);
				System.out.println("The lowest common ancestor between "+lcaFirst+" and "+lcaSecond+" is "+lca.getData());
				}
		
		System.out.println("\nThe largest diameter is "+getDiameter());
		System.out.println("\nThe in order inorder transversal of the tree is\n----------------------------");
		traverseTree(root);
	}
	/**prints the tree in an inorder traversal 
	 * 
	 * @param node
	 */
	private void traverseTree(Node node) {
		if(node != null) {
			traverseTree(node.getLeft());
			System.out.println(node.getData());
			traverseTree(node.getRight());
			}
		}
	
	/**if the input node is less than the current node then check if the current node has a left child. if not set the node as the left child and if so recurse down.
	 * if the input node data is greater than the current node then check if the current node has a right child and if not then recurse down.
	 * 
	 * @param node
	 * @param current
	 * @return
	 */
	private int insertNode(Node node, Node current) {
		int height = 0;
		if(node.getData().compareTo(current.getData())<=0) {
			if(current.getLeft() == null) {
				current.setLeft(node);
				node.setParent(current);
				height = 1;
			}else{
				height = 1+insertNode(node, current.getLeft());
			}
		}
		else {
			if(current.getRight()==null) {
				current.setRight(node);
				node.setParent(current);
				height = 1;
			}else {
				height = 1+insertNode(node, current.getRight());
			}
		}
		if(height>current.getHeight()) {
			current.setHeight(height);
		}
		return current.getHeight();
	}
	/**for each element in the search array call searchInTree to recursively search. if the node returned has a value of "notFound" then print that it wasnt found
	 * if it was found use getdepth to find the depth, use getsubtree to the subtree the subtree with the returned node as its root. print the individual
	 * search time and average search time. 
	 * 
	 * @param arr
	 */
	public void search(String[] arr) {
		long totalSearch=0;
		for(int i = 0; i<arr.length; i++) {
			System.out.println("\n\n\n\nSearching for "+arr[i]);
			long singleSearch = System.nanoTime();
			Node searchNode = searchInTree(arr[i], root);
			singleSearch = System.nanoTime()-singleSearch;
			totalSearch+=singleSearch;
			if(searchNode.getData().compareTo("notFound")==0) {
				System.out.println("That was not found in the tree");
			}else {
				System.out.println("Found at a depth of "+getDepth(searchNode));
				if(searchNode.getHeight()==0) {
					System.out.println("The node is a leaf\nThe size of the subtree with this node as a root is 0");
				}else {System.out.println("The node is not a leaf\nThe size of the subtree with this node as a root is "+getSubTree(searchNode));}
			}
			
			System.out.println("The time for the search was "+singleSearch+" nanoseconds");
		}
		totalSearch = totalSearch/arr.length;
		System.out.println("\n\nThe average search time was "+totalSearch);
	}
	/**if node is null return a node with data value "notFound" if node is found return it, if node is less than current then recurse to left child
	 * if node is greater than current then recurse right. 
	 * 
	 * @param tofind
	 * @param current
	 * @return
	 */
	private Node searchInTree(String tofind, Node current) {
		Node node = new Node();
		node.setData("notFound");
		if(current != null) {
		if(node!=null) {
			if(tofind.compareTo(current.getData())==0) {
				node=current;
			}else if(tofind.compareTo(current.getData())<0) {
				node = searchInTree(tofind, current.getLeft());
			}else if(tofind.compareTo(current.getData())>0) {
				node = searchInTree(tofind, current.getRight());
			}
		}
		}
		return node;
	}
	/**recursively traverse the subtree with the found tree as a root and return the height from each node which gives the size of the subtree
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
