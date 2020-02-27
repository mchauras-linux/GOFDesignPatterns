package com.jpm.structural.bridge;

import java.util.*;

//client
public class QuestionBank {
	public static void main(String[] args) {

		QuestionFormat questions = new QuestionFormat("Java Language");

		questions.setQuestionBank(new JavaQuestions());// can be hooked up with other
												// question class
		// questions.setQuestionBank(new DesignPatternsQuestions());
		// questions.setQuestionBank(new CplusplusQuestions());

		questions.display();
		questions.next();

		questions.newOne("What is object? ");
		questions.newOne("What is reference type?");

		questions.displayAll();
	}
}

// abstraction
interface Question {

	public void nextQuestion();
	public void priorQuestion();
	public void newQuestion(String q);
	public void deleteQuestion(String q);
	public void displayQuestion();
	public void displayAllQuestions();
}

// implementation
class QuestionManager {

	private Question questDB; // instantiate it later
	public String catalog;

	public QuestionManager(String catalog) {
		this.catalog = catalog;
	}
	public void next() {
		questDB.nextQuestion();
	}
	public void prior() {
		questDB.priorQuestion();
	}
	public void newOne(String quest) {
		questDB.newQuestion(quest);
	}
	public void delete(String quest) {
		questDB.deleteQuestion(quest);
	}
	public void display() {
		questDB.displayQuestion();
	}
	public void displayAll() {
		System.out.println("Question Catalog: " + catalog);
		questDB.displayAllQuestions();
	}
	public Question getQuestionBank() {
		return questDB;
	}
	public void setQuestionBank(Question questDB) {
		this.questDB = questDB;
	}
}

// further implementation // refined abstration 
class QuestionFormat extends QuestionManager {

	public QuestionFormat(String catalog) {
		super(catalog);
	}
	public void displayAll() {

		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~");
		super.displayAll();
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~");
	}
}

// decoupled implementation
class JavaQuestions implements Question {

	private List<String> questions = new ArrayList<String>();
	private int current = 0;

	public JavaQuestions() {
		// load from a database and fill in the container
		questions.add("What is Java? ");
		questions.add("What is an interface? ");
		questions.add("What is cross-platform? ");
		questions.add("What is UFT-8? ");
		questions.add("What is abstract? ");
		questions.add("What is Thread? ");
		questions.add("What is multi-threading? ");

	}
	public void nextQuestion() {
		if (current <= questions.size() - 1)
			current++;
	}
	public void priorQuestion() {
		if (current > 0)
			current--;
	}
	public void newQuestion(String quest) {
		questions.add(quest);
	}
	public void deleteQuestion(String quest) {
		questions.remove(quest);
	}
	public void displayQuestion() {
		System.out.println(questions.get(current));
	}
	public void displayAllQuestions() {
		for (String quest : questions) {
			System.out.println(quest);
		}
	}
}
