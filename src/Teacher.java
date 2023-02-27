import java.util.*;
import java.io.FileNotFoundException;

public class Teacher extends Person {
	private String teacherID;
	private String name;
	List<String> subject =  new ArrayList<String>();
	List<Class> allClass =  new ArrayList<>();
	
	//Constuctor Teacher class
	public Teacher(String teacherID, String name) {
		this.teacherID = teacherID;
		this.name = name;
		subject.add("Math MTH101");
		subject.add("Physic PHY102");
	}
	
	//Print Teacher detail
	public void teacherDetail() {
		System.out.println("------------------------------------\n");
		System.out.println("TEACHER DETAIL\n");
		System.out.printf("ID: %s\n", teacherID);
		System.out.printf("Name: %s\n", name);
		System.out.printf("Subject: %s\n", subject);
		System.out.println("\n------------------------------------");
	}
	
	//Edit assignment
	public boolean editAssignment(Class selectClass) {
		System.out.print("\nAll Assignment      : " + selectClass.decodeAssignment());
		System.out.print("\nSelect Assignment   : ");
		Scanner inputFile = new Scanner(System.in);
		String fileName = inputFile.nextLine();
		for(Assignment assign: selectClass.task) {
			if(assign.getName().equals(fileName)) {
				System.out.print("New File (.txt): ");
				String newFile = inputFile.nextLine();
				assign.setFile(AllFile.attachment(newFile));
				return true;
			} 
		}
		return false;
	}
	
	//Delete assignment
	public int deleteAssignment(Class selectClass) {		
		System.out.print("\nAll Assignment         : " + selectClass.decodeAssignment());
		System.out.print("\nSelect Assignment      : ");
		Scanner input = new Scanner(System.in);
		String fileName = input.nextLine();
		for(Assignment assign: selectClass.task) {
			if(assign.getName().equals(fileName)) {
				System.out.print("Confirm Delete (yes/no): ");
				String status = input.nextLine();
				if(status.equals("yes")) {
						selectClass.task.remove(assign);
						return 1;
				} else if(status.equals("no")) return 2;  
			}
		}
		return 3;
	}
	
	//Add student to class
	public void addMember(Class selectClass, List<Student> member) {
		System.out.print("\nStudent ID: ");
		Scanner input = new Scanner(System.in);
		String studentID = input.nextLine();
		Student newStudent = findStudent(member, studentID);
		if(newStudent == null) {
			System.out.print("Cannot find student, Please try again...\n");
		} else {
			newStudent.subjectIDList.add(selectClass.getClassID());
			selectClass.member.add(newStudent);
			System.out.println("Add Student Success...");
			System.out.println("\n-------------------------------------");
			selectClass.allMember();		
		}	
	}
	
	//Delete student from class
	public int deleteMember(Class selectClass, List<Student> member) {		
		System.out.print("\nStudent ID: ");
		Scanner input = new Scanner(System.in);
		String searchID = input.nextLine();
		for(Student student: selectClass.member) {
			if(student.getID().equals(searchID)) {
				System.out.print("Confirm Remove (yes/no): ");
				String status = input.nextLine();				
				if(status.equals("yes")) {
							student.subjectIDList.remove(selectClass.getClassID());
							selectClass.member.remove(student);
							return 1;
				} else if(status.equals("no")) return 2; 
			}
		} 
		return 3;
	}
	
	//View submit homework and add score
	public void viewSubmit(Class selectClass) throws FileNotFoundException {
		System.out.println("\nAll Assignment: " + selectClass.decodeAssignment());
		System.out.print("Select Assignment: ");
		Scanner input = new Scanner(System.in);
		String selectHomework = input.nextLine();
		int findHomework = 0;
		System.out.println("\n-------------------------------------");
		System.out.println("\nALL SUBMIT ASSIGNMMENT");
		
		//Find submit homework annd print detail
		for (Homework hw: selectClass.taskSubmit) {
			if(hw.getHomeworkName().equals(selectHomework)) {
				findHomework = 1;
				System.out.println(hw.getStudentID() + "     " + hw.getStudentName() + "     " + hw.getScore());
				System.out.println("Submit Time: "  + hw.getDate());
				System.out.print("Submit       : ");
				hw.viewHomeworkFile(hw.getHomeworkFile());
			}
		}
		System.out.println("\n-------------------------------------");
		
		//Select student and add homework score
		if(findHomework == 0) System.out.println("\nCannot find homework, Please try again...");
		else {
			int findStudent = 0;
			do {
				System.out.print("\nSelect Student (exit for break): ");
				String selectStudent = input.nextLine();
				if(findStudentByID(selectClass.member, selectStudent) == false && !selectStudent.equals("exit")) {
					System.out.println("Student ID incorrect...");
				} else {
					for (Homework hw: selectClass.taskSubmit) {
						if(hw.getStudentID().equals(selectStudent)) {
							this.addScore(selectClass, hw);
							break;
						} else if(selectStudent.equals("exit")) {
							findStudent = 1;
							break;
						} 
					} 									
				}
			} while(findStudent == 0);
			
			//Print score report
			System.out.println("\n-------------------------------------");
			selectClass.scoreTeacherReport(selectHomework);			

		}
	}
	
	//Add homework score
	public void addScore(Class selectClass, Homework submitHomework) {
		System.out.print("Score: ");
		Scanner input = new Scanner(System.in);
		double score = input.nextFloat();
		submitHomework.setScore(score);
	}
	
	//Find student by id(return Student class)
	public Student findStudent(List<Student> member, String studentName) {
		for (Student p : member) {
	        if (studentName.equals(p.getID())) {
	            return p;
	        }
	   }
		return null;
	}
	//Find student by id(return boolean)
	public boolean findStudentByID(List<Student> member, String studentID) {
		for (Student p: member) {
			if(studentID.equals(p.getID())) {
				return true;
			}
		}
		return false;
	}
	
	//Select class for use activity in program
	public Class selectClass() {
		System.out.print("Select Class From ID: ");
		Scanner input = new Scanner(System.in);
		String classID = input.nextLine();
		 for (Class subjectClass : this.allClass) {
		        if (classID.equals(subjectClass.getClassID())) {
		            return subjectClass;
		        }
		   }
		 return null;
	}
	
	//Create New Class
	public int createClass() {
		System.out.print("\nCreate Class From: ");
		System.out.print(subject);
		System.out.print("\nClass ID: ");
		Scanner input = new Scanner(System.in);
		String classID = input.nextLine();
		System.out.print("Class Name: ");
		String className = input.nextLine();
		
		//Create new class
		Class newClass = Class.getInstance(classID, className);
		//Check same class
		for(Class subjectClass: this.allClass) {
			if(classID.equals(subjectClass.getClassID()) || className.equals(subjectClass.getClassName())) {
				return 0;
			}
		}
		this.allClass.add(newClass);
		return 1;
	}
	
	//Return list class ID
	public List<String> decodeClassID() {
		List<String> newList =  new ArrayList<String>();
	    for(Class p: this.allClass) {
	    	newList.add(p.getClassID());
	    }
	    return newList;
	}
	
}
