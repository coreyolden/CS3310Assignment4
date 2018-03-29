import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class Main {

	/**This reads both files creates arrays for them, instantiates each type of heap/tree and calls search and input providing the arrays as parameters
	 * In the second part you can enter an N which will tell it how many random names to pull from the first input array and refill all heaps/trees with that data and
	 * search them again using the search input as a search parameter.
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
//		
//		//verify the user provided an input to store and an input to search
//		if(args.length!=2) {
//			System.out.println("You must supply two arguments.\nOne to store data, and one to look up data.");
//		}else {
//		String readFirst = args[0];
//		String readSecond = args[1];
//		
		String readFirst = "starwars_tiny.txt";
		String readSecond = "starwars_tiny.txt";
		
		
		// reads the inputs twice. once to count length and once to store in the generated arrays
		BufferedReader input = new BufferedReader(new FileReader(readFirst)); 
		String line;
		int arr1Length = 0; //the length to make the first array
		while((line=input.readLine()) != null) {
			arr1Length++;
		}
		input.close();
		String[] firstArray = new String[arr1Length-1];
		input = new BufferedReader(new FileReader(readFirst));
		for(int i = 0; i<arr1Length-1; i++){
			firstArray[i]=input.readLine();
		}
		input = new BufferedReader(new FileReader(readSecond));
		int arr2Length = 0;
		while((line=input.readLine()) != null) {
			arr2Length++;
		}
		input.close();
		String[] secondArray = new String[arr2Length-1];
		input = new BufferedReader(new FileReader(readSecond));
		for(int i = 0; i<arr2Length-1; i++){
			secondArray[i]=input.readLine();
		}
		
		//instantiate all trees and call insert and search on them
		MinHeap minH = new MinHeap();
		minH.insert(firstArray);
		minH.search(secondArray);
		System.out.println("----------------------------------------------------------\n\n\nMax Heap\n\n");
		MaxHeap maxH = new MaxHeap();
		maxH.insert(firstArray);
		//maxH.search(secondArray);
		System.out.println("----------------------------------------------------------\n\n\nBST\n\n");
		BinarySearchTree bst = new BinarySearchTree();
		bst.insert(firstArray);
		//bst.search(secondArray);
		
	
		
		
		Random rand = new Random();
		int n = 5; // input for N
		System.out.println("----------------------------------------------------------\nfilling structures with "+n+" random names.\n\n");
		String[] randomarr = new String[n];
		for(int i = 0; i<n; i++) {
			int random = rand.nextInt(firstArray.length); //generate N random names from the array
			randomarr[i]=firstArray[random];
		}
		
		MinHeap rminH = new MinHeap();
		rminH.insert(randomarr);
		rminH.search(secondArray);
		System.out.println("----------------------------------------------------------\n\n\nMax Heap\n\n");
		MaxHeap rmaxH = new MaxHeap();
		rmaxH.insert(randomarr);
		rmaxH.search(secondArray);
		System.out.println("----------------------------------------------------------\n\n\nBST\n\n");
		BinarySearchTree rbst = new BinarySearchTree();
		rbst.insert(randomarr);
		rbst.search(randomarr);
		
	//}//end if for args=2 if it fails end the program so there should be nothing between this and main.
		}//end main method
}

