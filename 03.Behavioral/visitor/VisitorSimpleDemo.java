package com.jp.behavioral.visitor;

import java.util.ArrayList;
import java.util.List;

public class VisitorSimpleDemo {

	public static void main(String[] args) {
		// Setup structure
		ObjectStructure o = new ObjectStructure();
		o.attach(new ConcreteElementA());
		o.attach(new ConcreteElementB());

		// Create visitor objects
		ConcreteVisitor1 v1 = new ConcreteVisitor1();
		ConcreteVisitor2 v2 = new ConcreteVisitor2();

		// Structure accepting visitors
		o.accept(v1);
		o.accept(v2);

		// Wait for user

	}

}

abstract class Visitor {
	public abstract void visitConcreteElementA(ConcreteElementA concreteElementA);

	public abstract void visitConcreteElementB(ConcreteElementB concreteElementB);

}
// i card generator 
class ConcreteVisitor1 extends Visitor

{
	@Override
	public void visitConcreteElementA(ConcreteElementA concreteElementA) {
		System.out.println(concreteElementA.getClass().getName()
				+ " visited by " + this.getClass().getName());
	}

	@Override
	public void visitConcreteElementB(ConcreteElementB concreteElementB) {
		System.out.println(concreteElementB.getClass().getName()
				+ " visited by " + this.getClass().getName());

	}

}

// / A 'ConcreteVisitor' class (Printing greeting cards) 
class ConcreteVisitor2 extends Visitor

{
	@Override
	public void visitConcreteElementA(ConcreteElementA concreteElementA) {
		System.out.println(concreteElementA.getClass().getName()
				+ " visited by " + this.getClass().getName());
	}

	@Override
	public void visitConcreteElementB(ConcreteElementB concreteElementB) {
		System.out.println(concreteElementB.getClass().getName()
				+ " visited by " + this.getClass().getName());

	}

}

abstract class Element {
	public abstract void accept(Visitor visitor);
}

// / A 'ConcreteElement' class
class ConcreteElementA extends Element {
	@Override
	public void accept(Visitor visitor) {
		visitor.visitConcreteElementA(this);

	}

	public void OperationA() {

	}

}


// / A 'ConcreteElement' class
class ConcreteElementB extends Element

{
	@Override
	public void accept(Visitor visitor) {
		visitor.visitConcreteElementB(this);
	}

	public void OperationB() {

	}

}

// / The 'ObjectStructure' class

class ObjectStructure {

	private List<Element> _elements = new ArrayList<Element>();

	public void attach(Element element) {
		_elements.add(element);
	}

	public void detach(Element element) {
		_elements.remove(element);

	}

	public void accept(Visitor visitor) {
		for (Element element : _elements) {
			element.accept(visitor);
		}
	}

}
