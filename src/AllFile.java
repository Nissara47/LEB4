import java.io.File; 
import java.io.FileNotFoundException; 
import java.util.Scanner;

public class AllFile {

	//find File and return (math-Homework1.txt, math-Homework2.txt, math-Submit1.txt)
	public static File attachment(String fileName) {
		return new File(fileName);
	}
	
	//Read file (.txt)
	public static void readFile(File file) throws FileNotFoundException {
		try (Scanner read = new Scanner(file)) {
			while(read.hasNextLine()) {
				System.out.println(read.nextLine());			
			}
		} catch (FileNotFoundException e) {
			System.out.println("File incorrect format, Please try again...");
		}
	}
}
