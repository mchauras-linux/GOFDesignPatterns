package com.jp.behavioral.interpreter;

import java.util.StringTokenizer;

abstract class RuleExpression {
	abstract public boolean interpret(String str);
}

class TerminalExpression extends RuleExpression {
	private String literal = null;

	public TerminalExpression(String str) {
		literal = str;
	}

	public boolean interpret(String str) {
		StringTokenizer st = new StringTokenizer(str);
		while (st.hasMoreTokens()) {
			String test = st.nextToken();
			if (test.equals(literal)) {
				return true;
			}
		}
		return false;
	}
}

class OrExpression extends RuleExpression {
	private RuleExpression expression1 = null;
	private RuleExpression expression2 = null;

	public OrExpression(RuleExpression expression1, RuleExpression expression2) {
		this.expression1 = expression1;
		this.expression2 = expression2;
	}

	public boolean interpret(String str) {
		return expression1.interpret(str) || expression2.interpret(str);
	}
}

class AndExpression extends RuleExpression {
	private RuleExpression expression1 = null;
	private RuleExpression expression2 = null;

	public AndExpression(RuleExpression expression1, RuleExpression expression2) {
		this.expression1 = expression1;
		this.expression2 = expression2;
	}

	public boolean interpret(String str) {
		return expression1.interpret(str) && expression2.interpret(str);
	}
}

public class RuleValidator {

	static RuleExpression buildInterpreterTree() {
		// Literal
		RuleExpression terminal1 = new TerminalExpression("John");
		RuleExpression terminal2 = new TerminalExpression("Henry");
		RuleExpression terminal3 = new TerminalExpression("Mary");
		RuleExpression terminal4 = new TerminalExpression("Owen");
		// Henry or Mary
		RuleExpression alternation1 = new OrExpression(terminal2, terminal3);
		// John or (Henry or Mary)
		RuleExpression alternation2 = new OrExpression(terminal1, alternation1);
		// Owen and (John or (Henry or Mary))
		return new AndExpression(terminal4, alternation2);
	}

	/**
	 * * main method - build the interpreter * and then interpret a specific
	 * sequence *
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String context = "Mary Owen";
		RuleExpression define = buildInterpreterTree();
		System.out.println(context + " is " + define.interpret(context));
	}
}
