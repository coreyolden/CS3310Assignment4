import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		
//		if(args.length!=2) {
//			System.out.println("You must supply two arguments.\nOne to store data, and one to look up data.");
//		}else {
		//String readFirst = args[0];
		//String readSecond = args[1];
		
		String readFirst = "starwars_tiny.txt";
		String readSecond = "starwars.txt";
		
		
		
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
		for(int i = 0; i<arr1Length-1; i++){
			secondArray[i]=input.readLine();
		}
		
		
//		MinHeap minH = new MinHeap();
//		minH.insert(firstArray);
//		minH.search(secondArray);
//		
		MaxHeap maxH = new MaxHeap();
		maxH.insert(firstArray);
		
		
	}//end if for args=2 if it fails end the program so there should be nothing between this and main.
	//}//end main method

}
