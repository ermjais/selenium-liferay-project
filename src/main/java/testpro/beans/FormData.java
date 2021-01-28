package testpro.beans;

import java.time.LocalDate;

public class FormData {

	private String name;
	private LocalDate date;
	private boolean option1;
	private boolean option2;
	private boolean option3;
	private String chosenOption;
	private String filePath;
	
	public String toString() {
		String result = "name: " + name + "\n" + 
				"date: " + date.toString() + "\n" + 
				"option1: " + option1 + "\n" + 
				"option2: " + option2 + "\n" + 
				"option3: " + option3 + "\n" +
				"chosenOption: " + chosenOption + "\n" +
				"filePath: " + filePath + "\n" ;
		return result;		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public boolean isOption1() {
		return option1;
	}
	public void setOption1(boolean option1) {
		this.option1 = option1;
	}
	public boolean isOption2() {
		return option2;
	}
	public void setOption2(boolean option2) {
		this.option2 = option2;
	}
	public boolean isOption3() {
		return option3;
	}
	public void setOption3(boolean option3) {
		this.option3 = option3;
	}
	public String getChosenOption() {
		return chosenOption;
	}
	public void setChosenOption(String chosenOption) {
		this.chosenOption = chosenOption;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	
}
