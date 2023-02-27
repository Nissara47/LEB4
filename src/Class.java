import java.io.FileNotFoundException;
import java.util.*;

public class Class {
	private String subjectID;
	private String subjectName;
	private String semester;
	private String information;
	private static Class instance;
	List<Student> member =  new ArrayList<>();  
	List<Assignment> task =  new ArrayList<>();  
	List<Homework> taskSubmit =  new ArrayList<>();
	
	//Constructor Class
	public Class() {
		
	}
	
	public Class(String subjectID, String subjectName) {
		this.subjectID = subjectID;
		this.subjectName = subjectName;
		this.semester = "1/22";
		this.information = 	"Subject for semester 1";
	}
	
	//Print Class detail
	public void classDetail() {
		System.out.println("\nCLASS DETAIL\n");
		System.out.println("Class ID   : " + subjectID);
		System.out.println("Class Name : " + subjectName);
		System.out.println("Semester   : " + semester);
		System.out.println("Description: " + information);
		System.out.println("Member     : " + this.decodeMember());
		System.out.println("Assignment : " + this.decodeAssignment());
		System.out.println("\n------------------------------------");
	}
	
	//Create Assignment
	public void createAssignment() {
		//input Assignment details
		Scanner input_assign = new Scanner(System.in);
		System.out.print("\nID                    : ");
		String id = input_assign.nextLine();
		System.out.print("Assignment Name       : ");
		String name = input_assign.nextLine();
		System.out.print("Description           : ");
		String problem = input_assign.nextLine();
		System.out.print("Attachment File (.txt): ");
		String attachmentName = input_assign.nextLine();
		System.out.print("Due-Date (next n day) : ");
		int dueDate = input_assign.nextInt();
		
		//Create Assignment and add to assignment list
		Assignment newTask = new Assignment(id, name, problem,attachmentName, dueDate);
		task.add(newTask);
		System.out.print("Create Assignment Succes...\n");
	}
	
	//Print student score (select assignment)
	public void scoreTeacherReport(String homeworkName) {
		double sum = 0;
		List<Float> score =  new ArrayList<>();
		List<String> nameSubmit =  new ArrayList<>();
		System.out.println("\nREPORT SCORE");
		//Check score student in assignment submit list
		for(Homework hw: taskSubmit) {
			if(hw.getHomeworkName().equals(homeworkName)) {
				System.out.println(hw.getStudentID() + "    " + hw.getStudentName() + "    " + hw.getScore());
				sum = sum + hw.getScore();
				score.add((float) hw.getScore());		
				nameSubmit.add(hw.getStudentName());
			} 
		}
		//Check student not submit assignment and set score 0
		for(Student student: member) {
			if(nameSubmit.indexOf(student.getName()) == -1) {
				System.out.println(student.getID() + "    " + student.getName() + "    " + "0.0");
				score.add((float) 0);
			}
		}
		//Calculate max, min, mean score
		System.out.println("Max     : " + Collections.max(score));
		System.out.println("Min     : " + Collections.min(score));
		System.out.println("Mean    : " + sum/member.size());
	}
	
	//Print detail all assignment in class
	public void allAssignment() throws FileNotFoundException {
		System.out.println("\nAll Assignment: " + this.subjectID);
		for(Assignment assign: task) {
			System.out.println(assign.getID() + "     " + assign.getName() + "     " + assign.getDesciption() + "     " + assign.getTime());
			System.out.print("File:  ");
			assign.readFile();
		}
	}
	
	//Print all member in class
	public void allMember() {
		System.out.println("\nAll MEMBER IN CLASS");
		for(Student student: member) {
			System.out.println(student.getID() + "     " + student.getDepartment() + "     " + student.getName());
		}
	}
	
	//Print homework or assignment submit detail
	public void allSubmitAssignment(Student student) throws FileNotFoundException {
		System.out.println("All ASSIGNMENT SUBMIT");
		for(Homework hw: this.taskSubmit) {
			if(student.getName().equals(hw.getStudentName())) {
				System.out.println(hw.getHomeworkName() + "     " + hw.getHomeworkFile());	
				System.out.print("Submit: ");	
				hw.viewHomeworkFile(hw.getHomeworkFile());
				hw.setDate();
			}
		}
	}

	//Print all assignment time submit, status submit, score
	public void scoreBoard(Student student) throws FileNotFoundException {
		String status = "Not Submit";
		double score = 0;
		System.out.println("Score Board : " + student.getName());
		System.out.println("All Submit Assignment: " + this.subjectID);
		//Check assignment with list homework submit
		for(Assignment assign: this.task) {
			for(Homework hw: this.taskSubmit) {
				if(student.getName().equals(hw.getStudentName()) && hw.getHomeworkName().equals(assign.getName())) {
					status = "Already Submit";
					score = hw.getScore();
				}
			}
			System.out.println(assign.getID() + "     " + assign.getName() + "     " + assign.getTime() + "     " + status + "     " + "     " + score);
			status = "Not Submit";
			score = 0;
		}
	}
	
	public boolean findAssignment(String assignName) {
		for(Assignment assign: task) {
			if(assign.getName().equals(assignName)) {
				return true;
			}
		}
		return false;
	}
	
	public String getClassID() {
		return subjectID;
	}
	
	public String getClassName() {
		return subjectName;
	}
	
	//Return list assignment name
	public List<String> decodeAssignment() {
		List<String> newList =  new ArrayList<String>();
	    for(Assignment assign: this.task) {
	    	newList.add(assign.getName());
	    }
	    return newList;
	}
	
	//Return list member ID
	public List<String> decodeMember() {
		List<String> newList =  new ArrayList<String>();
	    for(Student student: this.member) {
	    	newList.add(student.getID());
	    }
	    return newList;
	}
	
	//Singleton Pattern
	public static Class getInstance(String id, String name) {
        if (instance == null) 
        	instance = new Class(id, name);  
        return instance;
    }
}
