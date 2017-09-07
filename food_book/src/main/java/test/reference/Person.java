package test.reference;

public class Person {
	private String userName;
	private int age;
	public Person() {
		this.userName = "test";
		this.age = 10;
	}
	
	public String toString() {
		return "userName:" + userName + ",age:" + age;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
}
