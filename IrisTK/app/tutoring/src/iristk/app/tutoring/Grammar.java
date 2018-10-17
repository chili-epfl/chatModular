package iristk.app.tutoring;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Grammar {
	
	private Map<String, Integer> numbers = new HashMap<>();
	private Set<String> words = new HashSet<>();
	
	public Grammar() {
		createGrammar();
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
		numbers.put("10", 10);
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
		
	}
	
	public Integer getGrammar(String key) {
		if (numbers.keySet().contains(key))
			return numbers.get(key);
		else
			return null;
	}

}
