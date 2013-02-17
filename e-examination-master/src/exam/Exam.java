package exam;
import exam.*;

import java.util.ArrayList;
public class Exam {

	String author;
	String TEST_ID;
	float average;
	ArrayList<Question> questions = new ArrayList<Question>();
	
	public Exam(){
		
	}
	
	public void addQuestion(Question q){
		questions.add(q);
	}
	public Question getQuestion(int index){
		if( index > questions.size())
			return null;
		return questions.get(index);
	}
	
	public boolean hasQuestions(){
		return !(questions.isEmpty());
	}
	
	public int questionsSize(){
		return questions.size();
	}
	public String getAuthor(){
		return author;
	}
	public void setAuthor(String a){
		this.author = a;
	}
	
	public String getTestID(){
		return this.TEST_ID;
	}
	public void setTestID(String test_name){
		this.TEST_ID = test_name;
	}
	
	public float getAverage(){
		return average;
		
	}
	public void setAverage(float a){
		this.average = a;
	}
}
