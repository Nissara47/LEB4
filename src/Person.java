import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Person {
	protected String username;
	private String password;
	protected String role;
	private static Person instance;
	List<Student> allStudent =  new ArrayList<>();
	
	//Constructor Person Class
	public Person() {
		
	}
	
	//Login
	public void login() {
		Scanner input = new Scanner(System.in);
		System.out.println("\nLEB4 PROGRAM");
		System.out.print("Enter Username : ");
		username = input.nextLine();
		System.out.print("Enter Password : ");
		password = input.nextLine();
		
		if(username.equals("teacher") && password.equals("12345")) {
			role = "teacher";
			System.out.print("Teacher Login Complete...\n\n");
		} else if(username.equals("student1") || username.equals("student2") || username.equals("student3") && password.equals("12345")) {
			role = "student";
			System.out.print("Student Login Complete...\n\n");
		} else {
			role = "incorrect";
			System.out.print("Login Incomplete...\n\n");
		}
		
	}
	
	//Logout
	public void logout() {
		role = "logout";
		System.out.print("Logout Complete...\n\n");
	}
	
	//Singleton Pattern
	public static Person getInstance() {
        if (instance == null)
            instance = new Person();
        return instance;
    }

}
