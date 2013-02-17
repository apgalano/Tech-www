package users;

import java.util.ArrayList;

import exam.Exam;


public class Student extends User{

	ArrayList<String> examsToTake = new ArrayList<String>();
	ArrayList<String> examsTaken = new ArrayList<String>();
	
	
	public Student(){
		
	}
	public Student(String firstName,String lastName,int ID,String username,String password){
		super(firstName,lastName,username,password);
	}
	
	public  void addExamToTake(String e){
		examsToTake.add(e);
	}
	public void addExamTaken(String e){
		examsTaken.add(e);	
	}
	
	public void removeExamToTake(String e){
		examsToTake.remove(e);
	}
	public void removeExamTaken(String e){
		examsTaken.remove(e);
	}
	
	public String getExamTaken(int index){
		return examsTaken.get(index);
	}
	public String getExamToTake(int index){
		return examsToTake.get(index);
	}
	
	public boolean hasTakenExams(){
		return !(examsTaken.isEmpty());
	}
	public boolean hasAvailableExams(){
		return !(examsToTake.isEmpty());
	}
	public int getExamsToTakeSize(){
		return examsToTake.size();
	}
	public int getExamsTakenSize(){
		return examsTaken.size();
	}

}
