import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;

public class Homework {
	private String studentID;
	private String studentName;
	private String homeworkName;
	private double score;
	private LocalDateTime dateSubmit;
	private File fileHomework;
	
	//Homework is file that student submit
	//Constructor Homework Class
	public Homework(String studentID, String studentName, String homeworkName, File fileHomework) {
		this.studentID = studentID;
		this.studentName = studentName;
		this.homeworkName = homeworkName;
		this.fileHomework = fileHomework;
		this.dateSubmit = LocalDateTime.now();
	}
	
	public String getStudentID() {
		return studentID;
	}
	
	public String getStudentName() {
		return studentName;
	}
	
	public String getHomeworkName() {
		return homeworkName;
	}
	
	public File getHomeworkFile() {
		return fileHomework;
	}
	
	public double getScore() {
		return score;
	}
	
	public LocalDateTime getDate() {
		return dateSubmit;
	}
	
	
	public void setScore(double score) {
		this.score = score;
	}
	
	public void setFileHomework(File file) {
		this.fileHomework = file;
	}
	
	public void setDate() {
		this.dateSubmit = LocalDateTime.now();
	}
	
	//Read .txt file
	public void viewHomeworkFile(File fileHomework) throws FileNotFoundException {
		AllFile.readFile(fileHomework);
	}
	
}
