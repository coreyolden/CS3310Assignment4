import java.math.*;

public class MinHeap {

	private String[] arr;
	
	/**insert each element from the insert array into the tree and heapify. calculate total and individual insert time. calculate the lowest common ancestor 
	 * and the diameter. 
	 * 
	 * @param input
	 */
	public void insert(String[] input) {
		arr = new String[input.length];
		long insertTime = System.nanoTime();
		for(int i = 0; i<input.length;i++) {	//insert all data
			arr[i]=input[i];
		}
		for(int i = 0; i<arr.length; i++) {
			int parent = (int) (Math.floor(i-1)/2);
			if(arr[i].compareTo(arr[parent])<0) {
				
				heapify(i);
			}
		}
		insertTime = System.nanoTime()-insertTime;
		insertTime = insertTime/arr.length;
		System.out.println("The average time to insert one name was "+insertTime+" Nanoseconds");
		
		
		
		//to get lowest common ancestor put the names in the lcaFirst and lcaSecond variables.
				String lcaFirst = "QUI-GON";
				float firstlca= traverse(lcaFirst, 0);
				String lcaSecond = "LUKE";
				float secondlca = traverse(lcaSecond, 0);
				if(firstlca==9999999 ||secondlca==9999999){
					System.out.println("you have to search names actually in the heap");
				}else {
				String lca = getLCA((int)firstlca, (int)secondlca);
				System.out.println("The lowest common ancestor between "+lcaFirst+" and "+lcaSecond+" is "+lca);
				}
				System.out.println("\nThe largest diameter is "+getDiameter());
		System.out.println("\nThe in order preorder transversal of the tree is\n----------------------------");
		traverseTree(0);
		
	}
	/**print out the tree in a preordered traversal 
	 * 
	 * @param i
	 */
	private void traverseTree(int i) {
		System.out.println(arr[i]);
		if((i*2+1)<=arr.length-1) {
			traverseTree((i*2)+1);
		}
		if((i*2+2)<=arr.length-1) {
			traverseTree((i*2)+2);
		}
		
	}
	/**start at inserted node and swap it with parent if the parent is greater than it
	 * 
	 * @param index
	 */
	private void heapify(int index) {
		int parent = (int) (Math.floor(index-1)/2);
		if(arr[index].compareTo(arr[parent])<0) {
			String temp = arr[parent];
			arr[parent]=arr[index];
			arr[index]= temp;
			heapify(parent);
		}
		
		
	}
	/**for each element in the search array search the heap with traverse and look at the returned float to calculate if it was found and if so if it was a leaf
	 * or not. calculate the individual search time and print that as well as the total search time.
	 * 
	 * @param input
	 */
	public void search(String[]input) {
		long searchTime = 0;
		long searchTotal = 0;
		for(int i = 0; i<input.length; i++) {
			searchTime = System.nanoTime();
			float node = traverse(input[i], 0);
			searchTime = System.nanoTime()-searchTime;
			searchTotal+=searchTime;
			System.out.println("\n\n\nSearching for "+input[i]);
			if(node<9999999) {
			System.out.println("Found on Node:"+(int)node);
			
			System.out.println("The depth was "+getDepth((int) node));
			if(node % 1 == 0) {
				System.out.println("The node was not a leaf");
			}else {
				System.out.println("The node was a leaf");
				
				
			}
			int size = getsize((int)node);	
			System.out.println("The size of the tree with that as it's root is "+size);
			System.out.println("The search took "+searchTime+" nanoseconds");
			}else {
				System.out.println("That was not found");
				System.out.println("The search took "+searchTime+" nanoseconds");
			}
			int depth = 0;
			
			
		}
		
		searchTotal = searchTotal/input.length;
		System.out.println("\n\n\nThe average time to search was "+searchTotal+" nanoseconds");
	}
	private int getDepth(int found) {
		int depth = 0;
		int depthminexpon = 1;
		int depthmaxexpon=2;
		if(found ==0) {
			return depth;
		}else {
			int mindepth=1;
			int maxdepth=3;
			depth++;
			while(found>=mindepth && found<maxdepth) {
				depth++;
				 mindepth = (int) Math.pow(2,depthminexpon);
				 maxdepth = (int) Math.pow(2,depthmaxexpon);
			}
		}
		return depth;
	}
	
	/**traverse down the heap until you find the node you want. if the node is a leaf add .1 onto the end to show its a leaf 
	 * 
	 * @param input
	 * @param i
	 * @return
	 */
	private float traverse(String input, int i) {
		float depth = 9999999;
		float depthLeft = 9999999;
		float depthRight = 9999999;
		
		
		//base case it is what I'm looking for
		
		if (input.compareTo(arr[i])==0) {
			
			depth = (float) i;
		}
		
		if(depth<9999999) { //it has been found
			if(((i*2)+1)>arr.length-1) {
				depth = (float) (depth+0.1); //add a .1 onto the end to tell me its a node
			}
			
		}else {  //the string was not found so search its children
		if(((i*2)+1)<=arr.length-1) {
			 depthLeft =traverse(input, ((i*2)+1));
		}
		
		if(((i*2)+2)<=arr.length-1) {
			depthRight =traverse(input, ((i*2)+2)); 
		}
		}
		
		if(depthLeft <depth) {
			depth=depthLeft;
		}
		if(depthRight<depth) {
			depth = depthRight;
		}
		
		
		return depth;
	}
	/**get size of subarray 
	 * 
	 * @param i
	 * @return
	 */
	private int getsize(int i) {
		if(arr[i]==null) {
			return 0;
		}
		int size = 1;
		int sizeLeft = 0;
		int sizeRight = 0;
		if(((i*2)+1)<=arr.length-1) {
			 sizeLeft =getsize(((i*2)+1));
		}
		
		if(((i*2)+2)<=arr.length-1) {
			sizeRight =getsize(((i*2)+2)); 
		}
		size=size+sizeLeft+sizeRight;
		
		return size;
		
		
	}
	/**find the height of the names and back up till they are in the same level. compare them and if they are not the same then back each to the parent and
	 * compare again until they are the same.
	 * 
	 * @param first
	 * @param second
	 * @return
	 */
	private String getLCA(int first, int second) {
		
		String lca = null;
		if(first==second) {
			lca=arr[first];
		}
		else if(first>second) {
			while(first>second) {
			first = (int) (Math.floor(first-1)/2);
			}
			while(first!=second) {
				second = (int) (Math.floor(second-1)/2);
				if(first==second) {
					break;
				}else {
					first=(int) (Math.floor(first-1)/2);
				}
			}
			lca=arr[first];
		}else if(second>first) {
			while(second>first) {
				second = (int) (Math.floor(first-1)/2);
			}
			while(second!=first) {
				first = (int) (Math.floor(first-1)/2);
				if(first==second) {
					break;
				}else {
					second=(int) (Math.floor(second-1)/2);
				}
			}
			lca=arr[second];
		}
			
		return lca;
	}
	
	/**traverse all the way left and all the way right and keep a counter to find the diameter. 
	 * 
	 * @return
	 */
	private int getDiameter() {
		int diameter = 0;
		int first = 0;
		int second = 0;
		while(first<arr.length) {
			first=((first*2)+1);
			diameter++;
		}
		diameter--;
		while(second<arr.length) {
			second=((second*2)+2);
			diameter++;
		}
		second=((second*2)+2);
		if(second<=arr.length) {
			diameter++;
		}
		
		return diameter;
	}
}
