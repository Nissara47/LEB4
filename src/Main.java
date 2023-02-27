import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		int i = 0;
		//Create user for test program
		Teacher user_teacher = new Teacher("62070501000", "Harry Potter");
		Student user_student1 = new Student("01", "Percy Jackson");
		Student user_student2 = new Student("02", "Taylor Swift ");
		Student user_student3 = new Student("03", "John Lennon  ");
		do {
			Person user = Person.getInstance();
			//add all student to list
			user.allStudent.add(user_student1);
			user.allStudent.add(user_student2);
			user.allStudent.add(user_student3);
			user.login();
			if(user.role.equals("incorrect")) {
				System.out.println("Username or Password Incorrect, Plaese try again");
			} else if(user.role.equals("teacher")) {
				//Teacher Login
				int command;
				user_teacher.teacherDetail();
				Class newClass = new Class();
				do {
					//Check class for use all command
					if(user_teacher.allClass.isEmpty()) {
						System.out.println("\nCOMMAND CLASS: NO CLASS");
						System.out.println("Please create class (command 6)");
					} else if(newClass == null || newClass.getClassID() == null) {
						System.out.println("\nCOMMAND: NO CLASS");
						System.out.println("Please select class (command 7)");
					} else System.out.println("\nCOMMAND CLASS: " + newClass.getClassName());
					//Select command
					System.out.print("\nCreate Assignment: 1");
					System.out.print("          Edit Assignment         : 2");
					System.out.print("\nDelete Assignment: 3");
					System.out.print("          Add Member              : 4");
					System.out.print("\nDelete Member    : 5");
					System.out.print("          Create Class            : 6");
					System.out.print("\nSelect Class     : 7");
					System.out.print("          View Submit Assignment  : 8");
					System.out.print("\nView Score       : 9");
					System.out.print("          Logout                  : 10");
					System.out.print("\nPlease Select Command: ");
					Scanner select = new Scanner(System.in);
					command = select.nextInt();
					System.out.println("\n-------------------------------------");
					
					if(command == 1) {
						//Create Assignment
						if(newClass.getClassID() != null) {
							System.out.print("\nCREATE ASSIGNMENT\n");
							newClass.classDetail();
							newClass.createAssignment();
							System.out.println("\n-------------------------------------");
							newClass.allAssignment(); 
							System.out.println("\n-------------------------------------");														
						} 
						
					} else if(command == 2) {
						//Edit Assignment
						if(newClass.getClassID() != null) {
							System.out.print("\nEDIT ASSIGNMENT\n");
							newClass.classDetail();
							boolean find = user_teacher.editAssignment(newClass);
							if(find == true) {
								System.out.print("Edit Assignment Success...\n");
								System.out.println("\n-------------------------------------");
								newClass.allAssignment();
							} else System.out.println("Cannot find assignment, Please create assignment (command 1)");
							System.out.println("\n-------------------------------------");
						} 
						
					} else if(command == 3) {
						//Delete Assignment
						if(newClass.getClassID() != null) {
							System.out.print("\nDELETE ASSIGNMENT\n");
							newClass.classDetail();
							int status = user_teacher.deleteAssignment(newClass);
							if(status == 1)	{
								System.out.print("Delete Assignment Success...\n");
								System.out.println("\n-------------------------------------");
								newClass.allAssignment();
							} else if(status == 2) System.out.println("Back to select command...");
							else System.out.println("Cannot find assignment, Please create assignment (command 1)");	
							System.out.println("\n-------------------------------------");
						} 

					} else if(command == 4) {
						//Add student to class
						if(newClass.getClassID() != null) {
							System.out.print("\nADD STUDENT\n");
							newClass.classDetail();
							user_teacher.addMember(newClass, user.allStudent);
							System.out.println("\n-------------------------------------");		
						} 
						
					} else if(command == 5) {
						//Delete student from class
						if(newClass.getClassID() != null) {
							System.out.print("\nREMOVE STUDENT\n");
							newClass.classDetail();
							int status = user_teacher.deleteMember(newClass, user.allStudent);
							if(status == 1)	{
								System.out.print("Remove Student Success...\n");
								System.out.println("\n-------------------------------------");
								newClass.allMember();
							}						
							else if(status == 2) System.out.println("Back to select command...");
							else System.out.println("Student not a member this class, Please add member (command 4)");	
							System.out.println("\n-------------------------------------");
						}
						
					} else if(command == 6) {
						//Create class
						System.out.print("\nCREATE CLASS\n");
						int check = user_teacher.createClass();
						if(check == 0) {
							System.out.println("classID or className is already to use...");
						} else {
							System.out.println("Create Class Success...");							
						}
						System.out.println("\n-------------------------------------");
						
					} else if(command == 7) {
						//Select class
						if(!user_teacher.allClass.isEmpty()) {
							System.out.println("\nSELECT CLASS\n");
							System.out.println("Class: " + user_teacher.decodeClassID());
							Class selectNewClass = user_teacher.selectClass();
							if(selectNewClass == null) {
								System.out.println("Class ID incorrect, Please try again...");
								System.out.println("\n-------------------------------------");
							} else {
								newClass = selectNewClass;
								System.out.println("Select Class Success...");
								System.out.println("\n-------------------------------------");
							}			
						} 
						
					} else if(command == 8) {
						//View assignment and score
						if(newClass.getClassID() != null) {
							System.out.print("\nVIEW ASSIGNMENT & SCORE\n");
							newClass.classDetail();
							user_teacher.viewSubmit(newClass);
							System.out.println("\n-------------------------------------");
						} 
						
					} else if(command == 9) {
						//View student score
						if(newClass.getClassID() != null) {
							System.out.print("\nVIEW SCORE\n");
							newClass.allAssignment();
							System.out.println("\n-------------------------------------\n");
							System.out.print("Select Assignment: ");
							Scanner input = new Scanner(System.in);
							String assign = input.nextLine();
							boolean find = newClass.findAssignment(assign);
							if(find == true) {
								newClass.scoreTeacherReport(assign);								
							} else {
								System.out.println("Assignment name incorrect...");
							}
							System.out.println("\n-------------------------------------");
						} 
						
					} else if(command == 10) {
						//Logout
						user.logout();
						
					}
				} while (command != 10);
				
			} else if (user.role.equals("student")) {
				int command;
				Student user_student = new Student();
				//Check account student login
				if(user.username.equals("student1")) {
					user_student = user_student1;
				} else if(user.username.equals("student2")) {
					user_student = user_student2;
				} else if(user.username.equals("student3")) {
					user_student = user_student3;
				}
				//Print student detail
				user_student.studentDetail();
				Class newClass = new Class();
				
					do {
						//Check class for use all command
						if(user_student.subjectIDList.isEmpty()) {
							System.out.println("COMMAND");
							System.out.println("Cannot find subject, Please contact your Aj or P'Yao");
						} else if(newClass.getClassID() == null) {
							System.out.println("\nCOMMAND");
							System.out.println("Please select class (command 3)");
						} else System.out.println("\nCOMMAND CLASS: " + newClass.getClassName());
						//Select command
						System.out.print("\nSubmit Assignment      : 1");
						System.out.print("\nEdit Submit            : 2");
						System.out.print("\nSelect Class           : 3");
						System.out.print("\nView Assignment & Score: 4");
						System.out.print("\nLogout                 : 5");
						System.out.print("\nPlease Select Command: ");
						Scanner select = new Scanner(System.in);
						command = select.nextInt();	
						System.out.println("\n--------------------------------\n");
						
						if(command == 1) {
							//Submit assignment
							if(newClass.getClassID() != null) {
								System.out.println("SUBMIT ASSIGNMENT");
								newClass.allAssignment();
								user_student.selectHomework(newClass);															
							}
							
						} else if(command == 2) {
							//Edit assignment
							if(newClass.getClassID() != null) {
								System.out.println("EDIT ASSIGNMENT\n");
								newClass.allSubmitAssignment(user_student);
								System.out.println("\n--------------------------------");
								boolean find = user_student.editHomework(newClass);
								if(find == false) {
									System.out.println("Cannot find homework...");
								} else {
									System.out.println("Edit Assignment Success...");
								}
								System.out.println("\n--------------------------------");
							}
								
						} else if(command == 3) {
							//Select class
							if(!user_student.subjectIDList.isEmpty()) {
								System.out.println("SELECT CLASS\n");
								System.out.println("Class: " + user_student.subjectIDList);
								Class selectNewClass = user_teacher.selectClass();
								if(selectNewClass == null) {
									System.out.println("Class ID incorrect, Please try again...");
								} else {
									newClass = selectNewClass;
									System.out.println("Select Class Success...");
								}
								System.out.println("\n--------------------------------");								
							} 
							
						} else if(command == 4) {
							//View assignment and score
							if(newClass.getClassID() != null) {
								System.out.println("VIEW ASSIGNMENT & SCORE\n");
								newClass.scoreBoard(user_student);	
								System.out.println("\n--------------------------------\n");
							}
							
						} else if(command == 5) {
							//Logout
							user.logout();
							
						}	
					} while(command != 5);
				
			} 
		} while(i == 0);
	}	
}
