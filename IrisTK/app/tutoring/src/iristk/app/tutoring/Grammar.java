package iristk.app.tutoring;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class Grammar {
	
	private Map<String, Integer> numbers = new HashMap<>();
	private String[] randomSentences;
	
	public Grammar() {
		createGrammar();
		createRandomSentences();
	}
	
	public Integer getGrammar(String key) {
		if (numbers.keySet().contains(key))
			return numbers.get(key);
		else
			return null;
	}
	
	public String getRandomResponse() {
		int r = new Random().nextInt(randomSentences.length);
		return randomSentences[r];
	}
	
	private void createRandomSentences() {
		String[]  sentencesArray = {"You could try counting on your fingers.",
				"Try to see this problem as if you had to sum 5 apples and 5 bananas.",
				"Think again, I'm sure you can find it!"};
		randomSentences = sentencesArray;
	}
	
	private void createGrammar() {
		numbers.put("one", 1);
		numbers.put("two", 2);
		numbers.put("three", 3);
		numbers.put("four", 4);
		numbers.put("five", 5);
		numbers.put("six", 6);
		numbers.put("seven", 7);
		numbers.put("eight", 8);
		numbers.put("nine", 9);
		numbers.put("ten", 10);
		numbers.put("eleven", 11);
		numbers.put("twelve", 12);
		numbers.put("thirteen", 13);
		numbers.put("fourteen", 14);
		numbers.put("fifteen", 15);
		numbers.put("sixteen", 16);
		numbers.put("seventeen", 17);
		numbers.put("eighteen", 18);
		numbers.put("nineteen", 19);
		numbers.put("twenty", 20);
		numbers.put("One", 1);
		numbers.put("Two", 2);
		numbers.put("Three", 3);
		numbers.put("Four", 4);
		numbers.put("Five", 5);
		numbers.put("Six", 6);
		numbers.put("Seven", 7);
		numbers.put("Eight", 8);
		numbers.put("Nine", 9);
		numbers.put("Ten", 10);
		numbers.put("Eleven", 11);
		numbers.put("Twelve", 12);
		numbers.put("Thirteen", 13);
		numbers.put("Fourteen", 14);
		numbers.put("Fifteen", 15);
		numbers.put("Sixteen", 16);
		numbers.put("Seventeen", 17);
		numbers.put("Eighteen", 18);
		numbers.put("Nineteen", 19);
		numbers.put("Twenty", 20);
	}

}
