import java.io.File;
import java.io.FileNotFoundException;
import java.time.*;

public class Assignment {
	private String assignmentID;
	private String assignmentName;
	private String problems;
	private File assignmentFile;
	private LocalDateTime dueDate;
	
	//Assignment is file that teacher submit
	//Constructor Assignment Class
	public Assignment(String assignmentID, String assignmentName, String problems, String fileName,int duedate) {
		this.assignmentID = assignmentID;
		this.assignmentName = assignmentName;
		this.problems = problems;
		this.assignmentFile = AllFile.attachment(fileName);
		
		LocalDate dt = LocalDate.now().plusDays(duedate);
		this.dueDate = dt.atTime(23, 59, 59);
	}
	
	public String getID() {
		return assignmentID;
	}
	
	public String getName() {
		return assignmentName;
	}
	
	public String getDesciption() {
		return problems;
	}
	
	public LocalDateTime getTime() {
		return dueDate;
	}
	
	public void setName(String newName) {
		this.assignmentName = newName;
	}
	
	public void setFile(File file) {
		this.assignmentFile = file;
	}
	
	public void readFile() throws FileNotFoundException {
		AllFile.readFile(assignmentFile);
	}
	
}
