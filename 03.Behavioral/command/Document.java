package com.jp.behavioral.command;

import java.awt.TextComponent;
import java.util.Stack;

// Receiver
public class Document{    
    private String text;
    public Document() {
       
    }
    public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}      
}

// Command
abstract class TextCommand
{
    public abstract void execute();
}
abstract class UndoableTextCommand extends TextCommand
{
    public abstract void undo();
}

// Concrete Command
class BoldCommand extends UndoableTextCommand
{
    private Document document;   
    // Receiver is passed to the command
    public BoldCommand(Document doc) 
    {
        this.document = doc;       
    }
    @Override
    public  void execute()
    {
        // make the text bold 
    }
    
    @Override
    public  void undo()
    {
        // remove the bold from the text
    }
}
//Concrete Command
class CutCommand extends UndoableTextCommand
{
    private Document document;
    private String previousText;
    public CutCommand(Document doc)
    {
        this.document = doc;
    }
    @Override
    public  void execute()
    {
        // Will cut the text from the document
    }
    
    @Override
    public  void undo()
    {
    	// Will undo the cut command
    }
}
//Concrete Command
class PasteCommand extends UndoableTextCommand
{
    private Document document;
    private String previousText;
    public PasteCommand(Document doc)
    {
        this.document = doc;
    }
    @Override
    public  void execute()
    {
        // Will cut the text from the document
    }
    
    @Override
    public  void undo()
    {
    	// Will undo the cut command
    }
}
// Invoker
class CommandManager
{
    private Stack commandStack = new Stack();

    public void executeCommand(TextCommand command)
    {
    	command.execute();
        if (command instanceof UndoableTextCommand)
        {
            commandStack.push(command);
        }    
    }

    public void undo()
    {
        if (commandStack.size() > 0)
        {
            UndoableTextCommand command = (UndoableTextCommand)commandStack.pop();
            command.undo();
        }
    }
}

// Client
class DocumentClient
{
	public static void main(String[] args) {
		Document document = new Document();
		CommandManager commandManager = new CommandManager();
		commandManager.executeCommand(new CutCommand(document));
		commandManager.executeCommand(new PasteCommand(document));
		commandManager.executeCommand(new BoldCommand(document));
		commandManager.undo();
		commandManager.executeCommand(new PasteCommand(document));
		commandManager.executeCommand(new CutCommand(document));
		commandManager.executeCommand(new PasteCommand(document));
		commandManager.executeCommand(new BoldCommand(document));
		commandManager.undo();
		commandManager.undo();
		commandManager.undo();
		
	}
}
