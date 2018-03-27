import java.math.*;

public class MinHeap {

	private String[] arr;
	
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
		
		System.out.println("\nThe in order preorder transversal of the tree is\n----------------------------");
		traverseTree(0);
		
	}
	
	private void traverseTree(int i) {
		System.out.println(arr[i]);
		if((i*2+1)<=arr.length-1) {
			traverseTree((i*2)+1);
		}
		if((i*2+2)<=arr.length-1) {
			traverseTree((i*2)+2);
		}
		
	}
	private void heapify(int index) {
		int parent = (int) (Math.floor(index-1)/2);
		if(arr[index].compareTo(arr[parent])<0) {
			String temp = arr[parent];
			arr[parent]=arr[index];
			arr[index]= temp;
			heapify(parent);
		}
		
		
	}
	public void search(String[]input) {
		long searchTime = 0;
		long searchTotal = 0;
		for(int i = 0; i<input.length; i++) {
			searchTime = System.nanoTime();
			float node = traverse(input[i], 0);
			searchTime = System.nanoTime()-searchTime;
			searchTotal+=searchTime;
			System.out.println("Searching for "+input[i]);
			if(node<9999999) {
			System.out.println("Found on Node:"+(int)node);
			
			int depth = 0;
			int exponential = 0;
			int depthtracker = 0;
			
			do {depth++;
			exponential++;
			depthtracker = (int) Math.pow(2, exponential);
			}while(depthtracker<node);
			System.out.println("The depth was "+depth--);
			if(node % 1 == 0) {
				System.out.println("The node was not a leaf");
			}else {
				System.out.println("The node was a leaf");
				
				
			}
			int size = getsize((int)node);	
			System.out.println("The size of the tree with that as it's root is "+size+"\n\n\n");
			}else {
				System.out.println("That was not found\n\n\n");
			}
			int depth = 0;
			
		}
		
		searchTotal = searchTotal/input.length;
		System.out.println("The average time to search was "+searchTotal+" nanoseconds");
	}
	
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
}
