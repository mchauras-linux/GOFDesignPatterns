package com.jp.behavioral.interpreter;

import java.util.*;

public class Parser {
	private String expression;
	private String token;
	private List result;
	private String interpreted;

	public Parser(String e, String t) {
		expression = e;
		token = t;
	}

	public void myParser() {
		StringTokenizer holder = new StringTokenizer(expression, token);
		String[] toBeMatched = new String[holder.countTokens()];
		int idx = 0;
		while (holder.hasMoreTokens()) {
			String item = holder.nextToken();
			int start = item.indexOf(",");
			if (start == 0) {
				item = item.substring(2);
			}
			toBeMatched[idx] = item;
			idx++;
		}
		result = Arrays.asList(toBeMatched);
	}

	public List getParseResult() {
		return result;
	}

	public void interpret() {
		StringBuffer buffer = new StringBuffer();
		ListIterator list = result.listIterator();
		while (list.hasNext()) {
			String token = (String) list.next();
			if (token.equals("SFO")) {
				token = "San Francisco";
			} else if (token.equals("CA")) {
				token = "Canada";
			}
			// ...
			buffer.append(" " + token);
		}
		interpreted = buffer.toString();
	}

	public String getInterpretedResult() {
		return interpreted;
	}

	public static void main(String[] args) {
		String source = "dest='SFO',origin='CA',day='MON'";
		String delimiter = "=,'";
		Parser parser = new Parser(source, delimiter);
		parser.myParser();
		parser.interpret();
		String result = parser.getInterpretedResult();
		System.out.println(result);
	}
}
