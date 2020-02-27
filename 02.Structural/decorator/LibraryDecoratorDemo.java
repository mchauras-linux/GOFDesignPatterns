package com.jpm.structural.decorator;

import java.util.ArrayList;
import java.util.List;

// Decorator Design Pattern.
public class LibraryDecoratorDemo {

	public static void main(String[] args) {
		// Create book
		Book book = new Book("Worley", "Inside ASP.NET", 10);
		book.Display();
		// Create video
		Video video = new Video("Spielberg", "Jaws", 23, 92);
		video.Display();

		// Make video borrowable, then borrow and display
		System.out.println("\nMaking video borrowable:");
		Borrowable borrowvideo = new Borrowable(video);
		borrowvideo.BorrowItem("Customer #1");
		borrowvideo.BorrowItem("Customer #2");
		borrowvideo.Display();

	}

}

// / The 'Component' abstract class
abstract class LibraryItem {
	private int numCopies;

	public int getNumCopies() {
		return numCopies;
	}

	public void setNumCopies(int numCopies) {
		this.numCopies = numCopies;
	}

	// abstract method to be implemented by concrete classes
	public abstract void Display();
}

// The 'ConcreteComponent' class
class Book extends LibraryItem {
	private String author;
	private String title;

	// Constructor
	public Book(String author, String title, int numCopies) {
		this.author = author;
		this.title = title;
		setNumCopies(numCopies);
	}

	@Override
	public void Display() {
		System.out.println("\nBook ------ ");
		System.out.println(" Author: " + author);
		System.out.println(" Title: " + title);
		System.out.println(" # Copies: " + getNumCopies());
	}
}

// / The 'ConcreteComponent' class
class Video extends LibraryItem {
	private String director;
	private String title;
	private int playTime;

	// Constructor
	public Video(String director, String title, int numCopies, int playTime) {
		this.director = director;
		this.title = title;
		setNumCopies(numCopies);
		this.playTime = playTime;
	}

	@Override
	public void Display() {
		System.out.println("\nVideo ----- ");
		System.out.println(" Film Director: " + director);
		System.out.println(" Title: " + title);
		System.out.println(" # Copies: " + getNumCopies());
		System.out.println(" Playtime: " + playTime);
	}

}

// / The 'Decorator' abstract class
abstract class Decorator extends LibraryItem {
	protected LibraryItem libraryItem;

	// Constructor
	public Decorator(LibraryItem libraryItem) {
		this.libraryItem = libraryItem;
	}

	@Override
	public void Display() {
		libraryItem.Display();
	}
}

// / The 'ConcreteDecorator' class
class Borrowable extends Decorator {
	protected List<String> borrowers = new ArrayList<String>();

	// Constructor
	public Borrowable(LibraryItem libraryItem) {
		super(libraryItem);
	}

	public void BorrowItem(String name) {
		borrowers.add(name);
		libraryItem.setNumCopies(libraryItem.getNumCopies() - 1);
	}

	public void ReturnItem(String name) {
		borrowers.remove(name);
		libraryItem.setNumCopies(libraryItem.getNumCopies() + 1);
	}

	@Override
	public void Display() {
		super.Display();
		for (String borrower : borrowers) {
			System.out.println(" borrower: " + borrower);
		}
	}

}
/*
Output - 
Book ------ 
Author: Worley
Title: Inside ASP.NET
# Copies: 10

Video ----- 
Film Director: Spielberg
Title: Jaws
# Copies: 23
Playtime: 92

Making video borrowable:

Video ----- 
Film Director: Spielberg
Title: Jaws
# Copies: 21
Playtime: 92
borrower: Customer #1
borrower: Customer #2
*/