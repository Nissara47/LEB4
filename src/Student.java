import java.io.File;
import java.util.*;

public class Student extends Person {
	private String studentID;
	private String studentName;
	private String department;
	List<String> subjectIDList =  new ArrayList<>();
	
	//Constructor Student class
	public Student() {
		
	}
	
	public Student(String studentID, String studentName) {
		this.studentID = studentID;
		this.studentName = studentName;
		this.department = "Computer Engineer";
	}
	
	//Print student detail
	public void studentDetail() {
		System.out.println("------------------------------------\n");
		System.out.println("STUDENT DETAIL\n");
		System.out.printf("ID        : %s\n", studentID);
		System.out.printf("Name      : %s\n", studentName);
		System.out.printf("Department: %s\n", department);
		System.out.println("Subject  : " + subjectIDList);
		System.out.println("\n------------------------------------");
	}
	
	//Select assignment for submit homework
	public void selectHomework(Class selectClass) {
		System.out.println("\n------------------------------------\n");
		System.out.print("Select Homework: ");
		Scanner input = new Scanner(System.in);
		String selectHomework = input.nextLine();
		if(findAssignment(selectClass, selectHomework) == true) {
			for(Assignment assign: selectClass.task) {
				if(assign.getName().equals(selectHomework)) {
					this.submitHomework(selectClass, selectHomework);
					System.out.println("Submit Assignment Success (View Status Command 4)");
				}
			}			
		} else System.out.println("Cannot find this Assignment...");
		System.out.println("\n------------------------------------");
	}
	
	//Select homework file and submit
	public void submitHomework(Class selectClass, String homeworkName) {
		System.out.print("Select File Submit: ");
		Scanner input = new Scanner(System.in);
		String hwFileName = input.nextLine();
		File homeworkFile = AllFile.attachment(hwFileName);
		
		//Create new homework and add to list submit homework
		Homework newHomework = new Homework(studentID, studentName, homeworkName, homeworkFile);
		selectClass.taskSubmit.add(newHomework);
	}
	
	//Find assignment
	public boolean findAssignment(Class selectClass, String assignName) {
		for(Assignment assign: selectClass.task) {
			if(assignName.equals(assign.getName())) {
				return true;
			}
		}
		return false;
	}
	
	//Edit Homework
	public boolean editHomework(Class selectClass) {
		System.out.print("\nSearch Homework: ");
		Scanner inputFile = new Scanner(System.in);
		String fileName = inputFile.nextLine();
		for(Homework hw: selectClass.taskSubmit) {
			if(hw.getHomeworkName().equals(fileName)) {
				System.out.print("New File (.txt): ");
				String newFile = inputFile.nextLine();
				hw.setFileHomework(AllFile.attachment(newFile));
				return true; 
			} 
		}
		return false;
	}
	
	public String getName() {
		return studentName;
	}
	
	public String getID() {
		return studentID;
	}
	
	public String getDepartment() {
		return department;
	}
	
	public void setSubject(String subjectID) {
		subjectIDList.add(subjectID);
	}
	
	
}
