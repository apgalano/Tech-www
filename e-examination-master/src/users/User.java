package users;

public  class User {

	String firstName;
	String lastName;
	
	String username;
	String password;
	
	protected int ID;
	public String toString(){
		return "User: " + username;
	}
	public User(){
		
	}
	public User(String firstName,String lastName,String username,String password){
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
	}
	public User(String firstName,String lastName){
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	/* set and get methods */
	public String getFirstName(){
		return firstName;
	}
	public void setFirstName(String firstName){
		this.firstName = firstName;
	}
	
	public String getLastName(){
		return lastName;
	}
	public void setLastName(String lastName){
		this.lastName = lastName;

	}
	

	public String getUsername(){
		return username;
	}
	public void setUsername(String username){
		this.username = username;
	}
	
	public String getPassword(){
		return password;
	}
	public void setPassword(String pass){
		password = pass;
	}
	
	public int getID(){
		return ID;
	}
	public void setID(int id){
		ID = id;
	}
	
}
