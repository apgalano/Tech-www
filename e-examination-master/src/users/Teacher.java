package users;

import java.util.ArrayList;


public class Teacher extends User{

	ArrayList<String> exams = new ArrayList<String>();
	public Teacher(){
		
	}
	public Teacher(String firstName,String lastName,String username,String password){
		super(firstName,lastName,username,password);
	}
	
	public void addExam(String e){
		exams.add(e);
	}
	public boolean hasExams(){
		return !(exams.isEmpty());
	}
	
	public int examSize(){
		return exams.size();
	}
	
	public String getExamTitle(int index){
		return exams.get(index);
	}
	
	public boolean removeExam(String e){
		return exams.remove(e);
	}
}
