package com.jp.behavioral.template;

public class TemplateDemo {
	
	public static void main(String[] args) {
		AbstractClass c;

		c = new ConcreteClassA();
		c.TemplateMethod();

		c = new ConcreteClassB();
		c.TemplateMethod();

	}
}

// "AbstractClass"
abstract class AbstractClass {
	public abstract void PrimitiveOperation1();

	public abstract void PrimitiveOperation2();

	// The "Template method" usually defined as final 
	public final void TemplateMethod() {
		PrimitiveOperation1();
		PrimitiveOperation2();
		System.out.println("");
	}
}

// "ConcreteClass"
class ConcreteClassA extends AbstractClass {
	@Override
	public void PrimitiveOperation1() {
		System.out.println("ConcreteClassA.PrimitiveOperation1()");
	}

	@Override
	public void PrimitiveOperation2() {
		System.out.println("ConcreteClassA.PrimitiveOperation2()");
	}
}

class ConcreteClassB extends AbstractClass {
	@Override
	public void PrimitiveOperation1() {
		System.out.println("ConcreteClassB.PrimitiveOperation1()");
	}

	@Override
	public void PrimitiveOperation2() {
		System.out.println("ConcreteClassB.PrimitiveOperation2()");
	}
}