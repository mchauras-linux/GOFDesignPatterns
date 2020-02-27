package com.creational.builder;

//Abstract Builder
abstract class TextConverter {
	abstract void convertCharacter(char c);

	abstract void convertParagraph();
}

// Product
class ASCIIText {
	public void append(char c) { 
		// Implement the code here
	}
}

// Product
class HtmlText {
	public void append(char c) { 
		// Implement the code here
	}
}

// Concrete Builder
class ASCIIConverter extends TextConverter {
	ASCIIText asciiTextObj;// resulting product

	@Override
	/*
	 * converts a character to target representation and appends to the
	 * resulting
	 */
	public void convertCharacter(char c) {
		char asciiChar = new Character(c).charValue();
		// gets the ascii character
		asciiTextObj.append(asciiChar);
	}

	@Override
	public void convertParagraph() {
	}

	ASCIIText getResult() {
		return asciiTextObj;
	}
}

// Concrete Builder
class HtmlConverter extends TextConverter {
	HtmlText htmlTextObj;// resulting product

	@Override
	/*
	 * converts a character to target representation and appends to the
	 * resulting
	 */
	public void convertCharacter(char c) {
		char asciiChar = new Character(c).charValue();
		// gets the ascii character
		htmlTextObj.append(asciiChar);
	}

	@Override
	public void convertParagraph() {
	}

	HtmlText getResult() {
		return htmlTextObj;
	}
}

// This class abstracts the document object
class Document {
	static int value;
	char token;

	public char getNextToken() {
		// Get the next token
		return token;
	}
}

// Director
class RTFReader {
	private static final char EOF = '0'; // Delimitor for End of File
	final char CHAR = 'c';
	final char PARA = 'p';
	char t;
	TextConverter builder;

	RTFReader(TextConverter obj) {
		builder = obj;
	}

	void parseRTF(Document doc) {
		while ((t = doc.getNextToken()) != EOF) {
			switch (t) {
			case CHAR:
				builder.convertCharacter(t);
			case PARA:
				builder.convertParagraph();
			}
		}
	}
}

// Client
public class TextConverterClient {

	void createASCIIText(Document doc) {
		ASCIIConverter asciiBuilder = new ASCIIConverter();
		RTFReader rtfReader = new RTFReader(asciiBuilder);
		rtfReader.parseRTF(doc);
		ASCIIText asciiText = asciiBuilder.getResult();
	}

	public static void main(String args[]) {
		TextConverterClient client = new TextConverterClient();
		Document doc = new Document();
		client.createASCIIText(doc);
		System.out.println("This is an example of Builder Pattern");
	}
}
