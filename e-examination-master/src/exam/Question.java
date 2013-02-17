package exam;

public class Question {

	String TEST_ID;
	String question;
	
	String[] answers = null;
	int correct;
	
	public Question(){
		
	}
	
	public Question(String TEST_ID,String question,String answer_1,String answer_2,
			String answer_3,String answer_4
			,int correct){
		this.TEST_ID = TEST_ID;
		this.question = question;
		this.correct = correct;
		
		answers = new String[4];
		
		this.answers[0] = answer_1;
		this.answers[1] = answer_2;
		this.answers[2] = answer_3;
		this.answers[3] = answer_4;
	}
	public String getTestID(){
		return TEST_ID;
	}
	public void setTestID(String id){
		this.TEST_ID = id;
	}
	
	public String getQuestion(){
		return question;
	}
	public void setQuestion(String q){
		this.question = q;
	}
	
	public int getAnswerIndex(){
		return correct;
	}
	public void setAnswerIndex(int index){
		this.correct = index;
	}
	
	public String getAnswer(int index){
		if(answers == null)
			return null;
		
		return answers[index];
	}
	public int getCorrectAnswer(){
		return correct;
	}
	
}
